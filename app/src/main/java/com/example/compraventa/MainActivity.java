package com.example.compraventa;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<CharSequence> spnstring;
    private Button bpublicar;
    private Spinner categorias;
    private CheckBox retiro,eula;
    private SeekBar descuento;
    private Switch activDescuento;
    private TextView textP;
    private int categElegida;

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
        categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categElegida= i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                categElegida=-1;
            }
        });
        retiro.setOnCheckedChangeListener((compoundButton, isCheck) -> {
            LinearLayout retlay= findViewById(R.id.DireccionTodo);
            if (isCheck) {
                retlay.setVisibility(View.VISIBLE);
            } else {
                retlay.setVisibility(View.GONE);
            }
        });
        activDescuento.setOnCheckedChangeListener((compoundButton, isCheck) -> {
            LinearLayout retlay= findViewById(R.id.SeekbarTodo);
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
                Integer p=i;
                int color = textP.getCurrentTextColor();
                textP.setText(p.toString());
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
        eula.setOnCheckedChangeListener((compoundButton, b) -> bpublicar.setClickable(b));
        bpublicar.setOnClickListener(view -> {
            //TODO hacer validacion

            boolean validacion=false;

            if(validacion){
                //pasar a otra pantalla
            }else{
                Snackbar.make(view ,R.string.error_msj, BaseTransientBottomBar.LENGTH_LONG).setAction(R.string.error_acc,null).show();
            }
        });
    }
}