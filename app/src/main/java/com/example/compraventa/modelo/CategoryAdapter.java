package com.example.compraventa.modelo;

import static android.os.Build.VERSION_CODES.R;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.compraventa.R;

import java.util.List;
import java.util.Random;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>{

    private List<Category> listCat;
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista =  LayoutInflater.from(viewGroup.getContext()).inflate(com.example.compraventa.R.layout.fila_cat,viewGroup, false);
        CategoryViewHolder cat = new CategoryViewHolder(vista);
        return cat;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.id.setTag(i);
        categoryViewHolder.name.setTag(i);
        Category cat = listCat.get(i);
        categoryViewHolder.id.setText(cat.getId());
        categoryViewHolder.name.setText(cat.getName());
        if(i%2==0){
            categoryViewHolder.id.setBackgroundColor(Color.CYAN);
            categoryViewHolder.name.setBackgroundColor(Color.LTGRAY);
        }
        else{
            categoryViewHolder.id.setBackgroundColor(Color.MAGENTA);
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
}
