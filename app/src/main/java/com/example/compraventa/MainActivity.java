package com.example.compraventa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private EditText titulo;
    private Spinner categorias;
    private CheckBox retiro,Eula;
    private SeekBar descuento;
    private Switch activDescuento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categorias = findViewById(R.id.spinnercategoria);
        spnstring= ArrayAdapter.createFromResource(this,R.array.categorias,R.layout.support_simple_spinner_dropdown_item);
        categorias.setAdapter(spnstring);
        retiro= findViewById(R.id.checkRetiro);
        retiro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                LinearLayout retlay= findViewById(R.id.DireccionTodo);
                if (b) {
                    retlay.setVisibility(View.VISIBLE);
                } else {
                    retlay.setVisibility(View.GONE);
                }
            }
        });
        activDescuento= findViewById(R.id.switch1);
        activDescuento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                LinearLayout retlay= findViewById(R.id.SeekbarTodo);
                TextView adv = findViewById(R.id.textAdv);
                if (b) {
                    adv.setVisibility(View.VISIBLE);
                    retlay.setVisibility(View.VISIBLE);
                } else {
                    adv.setVisibility(View.GONE);
                    retlay.setVisibility(View.GONE);
                }
            }
        });
        descuento = findViewById(R.id.seekBarEnvios);
        descuento.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView textP = findViewById(R.id.textPorcentaje);
                Integer porcentaje= i;
                textP.setText(porcentaje.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                TextView adv = findViewById(R.id.textAdv);
                TextView textP = findViewById(R.id.textPorcentaje);
                Integer text = 0;
                if(textP.getText() == text.toString()){
                    adv.setVisibility(View.VISIBLE);
                }
                else{
                    adv.setVisibility(View.GONE);
                }
            }
        });
    }
}