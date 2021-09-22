package com.example.compraventa.modelo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.compraventa.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder{
    TextView id;
    TextView name;
    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.idCat);
        name = itemView.findViewById(R.id.nombreCat);

    }
    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
