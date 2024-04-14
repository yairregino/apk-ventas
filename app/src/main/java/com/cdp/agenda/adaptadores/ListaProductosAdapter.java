package com.cdp.agenda.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.agenda.R;
import com.cdp.agenda.VerProductoActivity;
import com.cdp.agenda.entidades.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ProductoViewHolder> {

    ArrayList<Producto> listaProductos;
    ArrayList<Producto> listaOriginal;

    public ListaProductosAdapter(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaProductos);
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto, null, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.viewNombre.setText("Nombre: " + listaProductos.get(position).getNombres());
        holder.viewDescripcion.setText("Descripcion: " + listaProductos.get(position).getDescripcion());
        holder.viewEstado.setText("Estado: " + listaProductos.get(position).getEstado());
        holder.viewPrecio.setText("Precio: " + listaProductos.get(position).getPrecio().toString());
        holder.viewStock.setText("Stock: " + listaProductos.get(position).getStock().toString());
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaProductos.clear();
            listaProductos.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Producto> collecion = listaProductos.stream()
                        .filter(i -> i.getNombres().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collecion);
            } else {
                for (Producto c : listaOriginal) {
                    if (c.getNombres().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaProductos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewDescripcion, viewStock, viewEstado, viewPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewDescripcion = itemView.findViewById(R.id.viewDescripcion);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
            viewStock = itemView.findViewById(R.id.viewStock);
            viewEstado = itemView.findViewById(R.id.viewEstado);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerProductoActivity.class);
                    intent.putExtra("ID", listaProductos.get(getAdapterPosition()).getIdProducto());
                    context.startActivity(intent);
                }
            });
        }
    }
}
