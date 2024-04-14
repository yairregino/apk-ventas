package com.cdp.agenda.adaptadores;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.agenda.CarroCompraActivity;
import com.cdp.agenda.R;
import com.cdp.agenda.entidades.ItemCompra;
import com.cdp.agenda.entidades.Producto;

import java.io.Serializable;
import java.util.List;

public class ItemProductoAdapter extends RecyclerView.Adapter<ItemProductoAdapter.ProductosViewHolder> {

    Context context;
    TextView tvCantProductos;
    Button btnVerCarro;
    List<Producto> listaProductos;
    List<ItemCompra> carroCompra;


    private final int originalColor = 0xFF006400;
    private final int newColor = 0xFFFF0000;

    public ItemProductoAdapter(Context context, TextView tvCantProductos, Button btnVerCarro, List<Producto> listaProductos, List<ItemCompra> carroCompra) {
        this.context = context;
        this.tvCantProductos = tvCantProductos;
        this.btnVerCarro = btnVerCarro;
        this.listaProductos = listaProductos;
        this.carroCompra = carroCompra;
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_producto_compra, null, false);
        return new ItemProductoAdapter.ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder productosViewHolder, int position) {
        productosViewHolder.txt_nombre.setText(listaProductos.get(position).getNombres());
        productosViewHolder.txt_precio.setText("$ " + listaProductos.get(position).getPrecio().toString());
        productosViewHolder.txt_stock.setText("Stock: " + listaProductos.get(position).getStock());


        productosViewHolder.cbCarro.setBackgroundColor(originalColor);

        productosViewHolder.cbCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInCarro = false; // Cambiado a false

                Producto producto = listaProductos.get(position);

                ItemCompra item = new ItemCompra();
                item.setIdProducto(Integer.parseInt(producto.getIdProducto().toString()));
                item.setCantidad(1);
                item.setNombreProducto(producto.getNombres());
                item.setPrecio(Double.parseDouble(producto.getPrecio().toString()));

                int posicionEnCarro = -1;


                for (int i = 0; i < carroCompra.size(); i++) {
                    ItemCompra item_carro = carroCompra.get(i);
                    if (item_carro.getIdProducto() == Integer.parseInt(producto.getIdProducto().toString())) {
                        isInCarro = true;
                        posicionEnCarro = i;
                        break;
                    }
                }

                if (!isInCarro) {
                    productosViewHolder.cbCarro.setBackgroundColor(newColor);
                    productosViewHolder.cbCarro.setText("Eliminar Producto");
                    tvCantProductos.setText("" + (Integer.parseInt(tvCantProductos.getText().toString().trim()) + 1));
                    carroCompra.add(item);
                } else {
                    productosViewHolder.cbCarro.setBackgroundColor(originalColor);
                    productosViewHolder.cbCarro.setText("Agregar a Carrito");
                    tvCantProductos.setText("" + (Integer.parseInt(tvCantProductos.getText().toString().trim()) - 1));
                    carroCompra.remove(posicionEnCarro);
                }
            }
        });


        btnVerCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CarroCompraActivity.class);
                intent.putExtra("CarroCompras", (Serializable) carroCompra);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView txt_nombre, txt_precio, txt_stock;
        Button cbCarro;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_nombre = itemView.findViewById(R.id.txt_nombre);
            txt_precio = itemView.findViewById(R.id.txt_precio);
            txt_stock = itemView.findViewById(R.id.txt_stock);
            cbCarro = itemView.findViewById(R.id.boton_agregar_carrito);
        }
    }
}