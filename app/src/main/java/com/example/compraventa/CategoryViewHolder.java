package com.example.compraventa;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.compraventa.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder{
    int lugar;
    TextView name;

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.nombreCat);
    }

}
