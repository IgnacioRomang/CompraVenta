package com.example.compraventa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
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

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<CharSequence> spnstring;
    private Button bpublicar;
    private Spinner categorias;
    private CheckBox retiro,eula;
    private SeekBar descuento;
    private Switch activDescuento;
    private TextView textP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //busco los elementos a usar
        textP = findViewById(R.id.textPorcentaje);
        retiro= findViewById(R.id.checkRetiro);
        activDescuento= findViewById(R.id.switch1);
        descuento = findViewById(R.id.seekBarEnvios);
        eula= findViewById(R.id.checkEula);
        categorias = findViewById(R.id.spinnercategoria);
        bpublicar = findViewById(R.id.buttonPublicar);
        //cargo las categorias
        spnstring= ArrayAdapter.createFromResource(this,R.array.categorias,R.layout.support_simple_spinner_dropdown_item);
        categorias.setAdapter(spnstring);
        //liseners
        retiro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                LinearLayout retlay= findViewById(R.id.DireccionTodo);
                if (isCheck) {
                    retlay.setVisibility(View.VISIBLE);
                } else {
                    retlay.setVisibility(View.GONE);
                }
            }
        });
        activDescuento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                LinearLayout retlay= findViewById(R.id.SeekbarTodo);
                TextView adv = findViewById(R.id.textAdv);
                if (isCheck) {
                    adv.setVisibility(View.VISIBLE);
                    retlay.setVisibility(View.VISIBLE);
                } else {
                    adv.setVisibility(View.GONE);
                    retlay.setVisibility(View.GONE);
                }
            }
        });
        descuento.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Integer porcentaje= i;
                textP.setText(porcentaje.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                TextView adv = findViewById(R.id.textAdv);
                Integer text = 0;
                if(textP.getText() == text.toString()){
                    adv.setVisibility(View.VISIBLE);
                }
                else{
                    adv.setVisibility(View.GONE);
                }
            }
        });
        eula.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    bpublicar.setClickable(true);
                }
                else{
                    bpublicar.setClickable(false);
                }
            }
        });
        bpublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //busco elementos
                EditText titulo,precio,contacto;
                titulo = findViewById(R.id.editTextTitulo);
                precio = findViewById(R.id.editTextPrecio);
                contacto = findViewById(R.id.editTextCorreo);
                    //validación
                //TODO hacer validacion
                //pantalla depepe
            }
        });
    }
}