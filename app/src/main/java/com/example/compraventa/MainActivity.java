package com.example.compraventa;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<CharSequence> spnstring;
    private Button bpublicar;
    private Spinner categorias;
    private CheckBox retiro, eula;
    private SeekBar descuento;
    private Switch activDescuento;
    private TextView textP;
    private int categElegida;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categElegida = -1;
        setContentView(R.layout.activity_main);
        //busco los elementos a usar
        textP = findViewById(R.id.textPorcentaje);
        retiro = findViewById(R.id.checkRetiro);
        activDescuento = findViewById(R.id.switch1);
        descuento = findViewById(R.id.seekBarEnvios);
        eula = findViewById(R.id.checkEula);
        categorias = findViewById(R.id.spinnercategoria);
        bpublicar = findViewById(R.id.buttonPublicar);
        bpublicar.setClickable(false);
        //cargo las categorias
        spnstring = ArrayAdapter.createFromResource(this, R.array.categorias, R.layout.support_simple_spinner_dropdown_item);
        categorias.setAdapter(spnstring);
        //liseners
        categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //elegir categoria;
                categElegida = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                categElegida = -1;
            }
        });
        retiro.setOnCheckedChangeListener((compoundButton, isCheck) -> {
            LinearLayout retlay = findViewById(R.id.DireccionTodo);
            if (isCheck) {
                retlay.setVisibility(View.VISIBLE);
            } else {
                retlay.setVisibility(View.GONE);
            }
        });
        activDescuento.setOnCheckedChangeListener((compoundButton, isCheck) -> {
            LinearLayout retlay = findViewById(R.id.SeekbarTodo);
            TextView adv = findViewById(R.id.textAdv);
            if (isCheck) {
                adv.setVisibility(View.VISIBLE);
                retlay.setVisibility(View.VISIBLE);
            } else {
                adv.setVisibility(View.GONE);
                retlay.setVisibility(View.GONE);
            }
        });
        descuento.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Integer p = i;
                textP.setText(p.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                TextView adv = findViewById(R.id.textAdv);
                Integer text = 0;
                if (textP.getText() == text.toString()) {
                    adv.setVisibility(View.VISIBLE);
                } else {
                    adv.setVisibility(View.GONE);
                }
            }
        });
        eula.setOnCheckedChangeListener((compoundButton, b) -> bpublicar.setClickable(true));
        bpublicar.setOnClickListener(view -> {
            //TODO Descrip solo toma 1 linea,Boton al incio se puede presionar
            String textoPlano = "([A-Z]|[a-z]|[,.]|[\\s\\n]|[0-9])+$";
            TextView textT;
            EditText editT;
            Pattern expresion = null;
            boolean validacion = false;
            switch (1) {
                case 1:
                    editT = findViewById(R.id.editTextTitulo);
                    expresion = Pattern.compile(textoPlano);
                    textT = findViewById(R.id.textTitulo);

                    if (expresion.matcher(editT.getText().toString()).matches()) {
                        validacion = true;
                        textT.setTextColor(R.color.def);
                    } else {
                        //TODO Ver por que no cambia de color
                        validacion = false;
                        textT.setTextColor(R.color.red);
                        break;
                    }
                    //titulo
                case 2:
                    //precio y >0
                    editT = findViewById(R.id.editTextPrecio);
                    expresion = Pattern.compile("[0-9]+([,][0-9]+)?$");
                    if (expresion.matcher(editT.getText().toString()).matches() && Double.parseDouble(editT.getText().toString()) > 0.00d) {
                        validacion = true;
                    } else {
                        validacion = false;
                        break;
                    }
                case 3:
                    //categ
                    if (categElegida < 0) {
                        validacion = false;
                        break;
                    } else {
                        validacion = true;
                    }
                case 4:
                    //retiro
                    if (retiro.isChecked()) {
                        //todos los datos de retiro esta re ok
                        editT = findViewById(R.id.editTextDireccion);
                        expresion = Pattern.compile(textoPlano);
                        if (expresion.matcher(editT.getText().toString()).matches()) {
                            validacion = true;
                        } else {
                            validacion = false;
                            break;
                        }
                    }
                case 5:
                    //descuento >0
                    if (activDescuento.isChecked()) {
                        if (Integer.parseInt(textP.getText().toString()) > 0) {
                            validacion = true;
                        } else {
                            validacion = false;
                            break;
                        }
                    }
                case 6:
                    //correo
                    editT = findViewById(R.id.editTextCorreo);
                    expresion = Pattern.compile("([a-z]|[A-Z]|[0-9]|[_-])+@([a-z]|[A-Z]|[0-9]|[_-])*([a-z][A-Z])([a-z]|[A-Z]|[0-9]|[_-])*$");
                    if (expresion.matcher(editT.getText().toString()).matches()) {
                        validacion = true;
                    } else {
                        validacion = false;
                        break;
                    }
            }
            if (validacion) {
                //pasar a otra pantalla
                Toast.makeText(this, R.string.val_ok, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.val_ok, Toast.LENGTH_SHORT).show();
                //Snackbar.make(view ,R.string.error_msj, BaseTransientBottomBar.LENGTH_LONG).setAction(R.string.error_acc,null).show();
            }
        });
    }
}