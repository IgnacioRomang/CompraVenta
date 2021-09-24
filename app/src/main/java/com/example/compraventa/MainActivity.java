package com.example.compraventa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Button bpublicar;
    private ImageButton categorias;
    private CheckBox retiro, eula;
    private SeekBar descuento;
    private Switch activDescuento;
    private TextView textP,adv,ttitulo,tcorreo,tdirc,tprecio,tcategoria,tnameCat;
    private EditText etitulo,ecorreo,edirc,eprecio;
    private String regxEmail,regxNum,regxPlano;
    private LinearLayout retlayDir,seek;
    private int rojo,def;
    private Category categElegida;
    private Intent selecCat;
    private static int CODIGO_OK = 0;
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadR(){
        categElegida = null;
        textP = findViewById(R.id.textPorcentaje);
        retiro = findViewById(R.id.checkRetiro);
        activDescuento = findViewById(R.id.switch1);
        descuento = findViewById(R.id.seekBarEnvios);
        eula = findViewById(R.id.checkEula);
        bpublicar = findViewById(R.id.buttonPublicar);
        categorias = findViewById(R.id.buttonCat);
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
        tnameCat = findViewById(R.id.nameCat);
    };
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"ResourceAsColor", "ResourceType"})
    public String cargarJSON() {
        String AUX = null;
        try {
            InputStream is = this.getAssets().open("Categorias.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            AUX = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return AUX;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void presionarCat() throws IOException {
        selecCat =  new Intent(MainActivity.this, SelectorCategoria.class);
        String aux= cargarJSON();
        selecCat.putExtra("cats",aux);
        startActivityForResult(selecCat,CODIGO_OK);
    };
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.loadR();

        categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    presionarCat();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                    if (categElegida != null) {
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if( resultCode== Activity.RESULT_OK){
            if(requestCode==CODIGO_OK){
                try {
                    JSONObject ob = new JSONObject(data.getStringExtra("elegido"));
                    Category cat = new Category(ob.getString("id"),ob.getString("name"));
                    tnameCat.setText("Selección: "+cat.name+'\n'+"ID: "+cat.id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
