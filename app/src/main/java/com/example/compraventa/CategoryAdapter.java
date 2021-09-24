package com.example.compraventa;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> implements View.OnClickListener {
    private int selecionado;
    private View.OnClickListener listener;
    private List<Category> listCat;
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista =  LayoutInflater.from(viewGroup.getContext()).inflate(com.example.compraventa.R.layout.fila_cat,viewGroup, false);
        CategoryViewHolder cat = new CategoryViewHolder(vista);
        cat.setLugar(i);
        vista.setOnClickListener(this);
        return cat;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.name.setTag(i);
        Category cat = listCat.get(i);
        categoryViewHolder.name.setText(cat.getName());
        //categoryViewHolder.id.setBackgroundColor(ContextCompat.getColor(this, R.color.idColor));
        if(i%2==0){
            categoryViewHolder.name.setBackgroundColor(Color.LTGRAY);
        }
    }
    @Override
    public int getItemCount() {
        return listCat.size();
    }

    public CategoryAdapter(List<Category> listCat) {
        this.listCat = listCat;
    }


    public List<Category> getListCat() {
        return listCat;
    }

    public void setListCat(List<Category> listCat) {
        this.listCat = listCat;
    }

    public void add(Category e){this.listCat.add(e);}

    public void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;
    }
    @Override
    public void onClick(View view) {
        if(this.listener != null){
            this.listener.onClick(view);
        }
    }
}
