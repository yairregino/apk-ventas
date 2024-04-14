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
import com.cdp.agenda.VerClienteActivity;
import com.cdp.agenda.VerTetalleCompraActivity;
import com.cdp.agenda.entidades.Venta;
import java.util.ArrayList;
public class ItemVentaAdapter  extends RecyclerView.Adapter<ItemVentaAdapter.VentaViewHolder> {
    private ArrayList<Venta> listaVentas;

    public ItemVentaAdapter(ArrayList<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    @NonNull
    @Override
    public ItemVentaAdapter.VentaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_venta_realizada, null, false);
        return new ItemVentaAdapter.VentaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVentaAdapter.VentaViewHolder holder, int position) {
        holder.txt_cliente.setText("Cliente: " + listaVentas.get(position).getCliente());
        holder.txt_empleado.setText("Empleado: " + listaVentas.get(position).getEmpleado());
        holder.txt_fecha.setText("Fecha: " + listaVentas.get(position).getFecha());
        holder.txt_total.setText("Total: $" + listaVentas.get(position).getTotal());

        holder.boton_ver_detalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, VerTetalleCompraActivity.class);
                intent.putExtra("ID", listaVentas.get(position).getIdVenta());
                intent.putExtra("NumeroSerie", listaVentas.get(position).getConsecutivo());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaVentas.size();
    }

    public class VentaViewHolder extends RecyclerView.ViewHolder {

        TextView txt_cliente, txt_empleado, txt_fecha, txt_total, boton_ver_detalle;

        public VentaViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_cliente = itemView.findViewById(R.id.txt_cliente);
            txt_empleado = itemView.findViewById(R.id.txt_empleado);
            txt_fecha = itemView.findViewById(R.id.txt_fecha);
            txt_total = itemView.findViewById(R.id.txt_total);
            boton_ver_detalle = itemView.findViewById(R.id.boton_ver_detalle);
        }
    }

}
