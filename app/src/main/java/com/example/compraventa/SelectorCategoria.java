package com.example.compraventa;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectorCategoria extends AppCompatActivity {
    Category cat;
    private RecyclerView recyclerView;
    private CategoryAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_categoria);
        recyclerView= findViewById(R.id.listaCat);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<Category> listCatReal = new ArrayList<Category>();
        String catsS = getIntent().getExtras().getString("cats");
        JSONArray listaCat = null;
        try {
            listaCat = new JSONArray(catsS);
            JSONObject objJ;
            for(int e=0;e<listaCat.length();e++){
                    objJ = listaCat.getJSONObject(e);
                    cat= new Category(objJ.getString("id"),objJ.getString("name"));
                    listCatReal.add(cat);
            }
        } catch (JSONException e) { e.printStackTrace(); }
        mAdapter = new CategoryAdapter(listCatReal);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cat = mAdapter.getListCat().get(recyclerView.getChildAdapterPosition(view));
                Intent result = new Intent(SelectorCategoria.this,MainActivity.class);
                result.putExtra("elegido",cat.toString());
                setResult(Activity.RESULT_OK,result);
                Toast.makeText(getApplicationContext(),R.string.toast_selección, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}