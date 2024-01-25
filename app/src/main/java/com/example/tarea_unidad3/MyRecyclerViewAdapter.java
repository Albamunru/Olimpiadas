package com.example.tarea_unidad3;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolderCultures>{
    private Context contexto;
    public List<Pais> data;
    public LayoutInflater mInflater;
    public OnClickListener mClickListener;

    public MyRecyclerViewAdapter (Context contexto, List<Pais> data){
        this.contexto = contexto;
        this.mInflater = LayoutInflater.from(contexto);
        this.data = data;
    }
    @Override
    public ViewHolderCultures onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View parten = mInflater.inflate(R.layout.recycler_view_item,parent,false);
       return new ViewHolderCultures(parten);
    }

    @Override
    public void onBindViewHolder(ViewHolderCultures holder, int position) {
       Pais paises = data.get(position);
       holder.myTextView.setText(paises.getNombrePais());
       holder.imgView.setImageResource(paises.getFotoPais());
       holder.myTextViewDos.setText("Alumnado en centro: " + String.valueOf(paises.getNumeroAlumnos()));
        holder.itemView.setOnClickListener(view -> {
            mClickListener.onClick(view, position);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickListener(OnClickListener clickListener){
        this.mClickListener = clickListener;
    }
    public Pais getItem(int id) {
        return data.get(id);
    }

    public class ViewHolderCultures extends RecyclerView.ViewHolder {

        TextView myTextView;
        TextView myTextViewDos;
        ImageView imgView;

        public ViewHolderCultures(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvRecycler);
            myTextViewDos = itemView.findViewById(R.id.numeroAlumnos);
            imgView = itemView.findViewById(R.id.imgVCulture);
        }

    }
    public interface OnClickListener {
        void onClick(View view, int position);
    }

}
