package com.example.compraventa;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<CharSequence> spnstring;
    private Button bpublicar;
    private Spinner categorias;
    private CheckBox retiro, eula;
    private SeekBar descuento;
    private Switch activDescuento;
    private TextView textP,adv,ttitulo,tcorreo,tdirc,tprecio,tcategoria;
    private EditText etitulo,ecorreo,edirc,eprecio;
    private Integer categElegida;
    private String regxEmail,regxNum,regxPlano;
    private LinearLayout retlayDir,seek;
    private int rojo,def;

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadR(){
        textP = findViewById(R.id.textPorcentaje);
        retiro = findViewById(R.id.checkRetiro);
        activDescuento = findViewById(R.id.switch1);
        descuento = findViewById(R.id.seekBarEnvios);
        eula = findViewById(R.id.checkEula);
        categorias = findViewById(R.id.spinnercategoria);
        bpublicar = findViewById(R.id.buttonPublicar);
        spnstring = ArrayAdapter.createFromResource(this, R.array.categorias, R.layout.support_simple_spinner_dropdown_item);
        categorias.setAdapter(spnstring);
        retlayDir = findViewById(R.id.DireccionTodo);
        seek = findViewById(R.id.SeekbarTodo);
        adv = findViewById(R.id.textAdv);
        regxPlano= getResources().getString(R.string.regex_tplano);
        regxNum = getResources().getString(R.string.regex_num);
        regxEmail = getResources().getString(R.string.regex_email);
        ttitulo = findViewById(R.id.textTitulo);
        etitulo = findViewById(R.id.editTextTitulo);
        tprecio = findViewById(R.id.textPrecio);
        eprecio = findViewById(R.id.editTextPrecio);
        tcorreo = findViewById(R.id.textCorreo);
        ecorreo = findViewById(R.id.editTextCorreo);
        tdirc = findViewById(R.id.textDirec);
        edirc = findViewById(R.id.editTextDireccion);
        tcategoria= findViewById(R.id.textCategoria);
        rojo = ContextCompat.getColor(this, R.color.red);
        def = ContextCompat.getColor(this, R.color.def);

    };
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.loadR();

        categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //elegir categoria;
                categElegida = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                categElegida = 0;
            }
        });
        retiro.setOnCheckedChangeListener((compoundButton, isCheck) -> {
            if (isCheck) {
                retlayDir.setVisibility(View.VISIBLE);
            } else {
                retlayDir.setVisibility(View.GONE);
            }
        });
        activDescuento.setOnCheckedChangeListener((compoundButton, isCheck) -> {
            if (isCheck) {
                adv.setVisibility(View.VISIBLE);
                seek.setVisibility(View.VISIBLE);
            } else {
                adv.setVisibility(View.GONE);
                seek.setVisibility(View.GONE);
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
                Integer text = 0;
                if (textP.getText() == text.toString()) {
                    adv.setVisibility(View.VISIBLE);
                } else {
                    adv.setVisibility(View.GONE);
                }
            }
        });
        bpublicar.setOnClickListener(view -> {
            //TODO Descrip solo toma 1 linea
            Pattern expresion = null;
            List<Boolean> boolArray = new ArrayList<Boolean>();
            switch (1) {
                case 1:
                    expresion = Pattern.compile(regxPlano);
                    if (expresion.matcher(etitulo.getText().toString()).matches()) {
                        boolArray.add(Boolean.TRUE);
                        ttitulo.setTextColor(def);
                    } else {
                        boolArray.add(Boolean.FALSE);
                        ttitulo.setTextColor(rojo);
                    }
                    //titulo
                case 2:
                    //precio y >0
                    expresion = Pattern.compile(regxNum);
                    if (expresion.matcher(eprecio.getText().toString()).matches()) {
                        boolArray.add(Boolean.TRUE);
                        tprecio.setTextColor(def);
                    } else {
                        boolArray.add(Boolean.FALSE);
                        tprecio.setTextColor(rojo);
                    }
                case 3:
                    //categ
                    if (categElegida <= 0) {
                        tcategoria.setTextColor(rojo);
                        boolArray.add(Boolean.FALSE);
                    } else {
                        tcategoria.setTextColor(def);
                        boolArray.add(Boolean.TRUE);
                    }
                case 4:
                    //retiro
                    if (retiro.isChecked()) {
                        expresion = Pattern.compile(regxPlano);
                        if (expresion.matcher(edirc.getText().toString()).matches()) {
                            tdirc.setTextColor(def);
                            boolArray.add(Boolean.TRUE);
                        } else {
                            tdirc.setTextColor(rojo);
                            boolArray.add(Boolean.FALSE);
                        }
                    }
                case 5:
                    //correo
                    expresion = Pattern.compile(regxEmail);
                    if (expresion.matcher(ecorreo.getText().toString()).matches()) {
                        tcorreo.setTextColor(def);
                        boolArray.add(Boolean.TRUE);
                    } else {
                        tcorreo.setTextColor(rojo);
                        boolArray.add(Boolean.FALSE);
                    }
                    break;
                case 6:
                    //descuento >0
                    if (activDescuento.isChecked()) {
                        Integer i=0;
                        if (Integer.parseInt(textP.getText().toString()) > i) {
                            boolArray.add(Boolean.TRUE);
                        } else {
                            boolArray.add(Boolean.FALSE);
                        }
                    }
            }
            if (boolArray.stream().allMatch(p-> p.booleanValue()==Boolean.TRUE)) {
                Toast.makeText(this, R.string.val_ok, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.val_sad, Toast.LENGTH_SHORT).show();
            }
        });
        bpublicar.setClickable(false);
        eula.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bpublicar.setClickable(b);
            }
        });
    }
}