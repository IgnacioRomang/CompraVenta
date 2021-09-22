package com.example.compraventa;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.compraventa.modelo.Category;
import com.example.compraventa.modelo.CategoryAdapter;
import com.example.compraventa.modelo.CategoryViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class SelectorCategoria extends AppCompatActivity {
    Category cat;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_categoria);
        recyclerView= findViewById(R.id.listaCat);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Category> listCatReal = null;
        Integer r;
        for(int e=0;e<20;e++){
            r=e;
            cat.setId(r.toString());
            cat.setName(r.toString());
            listCatReal.add(cat);
        }
        /*String catsS = getIntent().getExtras().getString("cats");
        JSONArray listaCat = null;
        try {
            listaCat = new JSONArray(catsS);
            JSONObject objJ;
            for(int e=0;e<listaCat.length();e++){
                    objJ = listaCat.getJSONObject(e);
                    cat.setId(objJ.getString("id"));
                    cat.setName(objJ.getString("name"));
                    listCatReal.add(cat);
            }
        } catch (JSONException e) { e.printStackTrace(); }*/
        mAdapter = new CategoryAdapter(listCatReal);
        recyclerView.setAdapter(mAdapter);
    }
}