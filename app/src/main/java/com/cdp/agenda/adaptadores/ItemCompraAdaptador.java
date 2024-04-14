package com.cdp.agenda.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cdp.agenda.R;
import com.cdp.agenda.entidades.ItemCompra;
import java.util.List;
public class ItemCompraAdaptador extends RecyclerView.Adapter<ItemCompraAdaptador.ProductosViewHolder>  {

    Context context;
    List<ItemCompra> listaProductos;

    public ItemCompraAdaptador(Context context,  List<ItemCompra> listaProductos) {
        this.context = context;
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ItemCompraAdaptador.ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_compra, null, false);
        return new ItemCompraAdaptador.ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCompraAdaptador.ProductosViewHolder productosViewHolder, int position) {
        productosViewHolder.txt_nombre.setText(listaProductos.get(position).getNombreProducto());
        productosViewHolder.txt_precio.setText("$ " + listaProductos.get(position).getPrecio());
        productosViewHolder.txt_stock.setText(listaProductos.get(position).getCantidad()+" Und.");
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView txt_nombre, txt_precio, txt_stock;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_nombre = itemView.findViewById(R.id.txt_nombre);
            txt_precio = itemView.findViewById(R.id.txt_precio);
            txt_stock = itemView.findViewById(R.id.txt_cantidad);
        }
    }

}
