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
import com.cdp.agenda.entidades.Cliente;

import java.util.ArrayList;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ClienteViewHolder> {

    private ArrayList<Cliente> listaClientes;
    private ArrayList<Cliente> listaOriginal;

    public ListaClientesAdapter(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaClientes);
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_cliente, null, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        holder.viewNombres.setText("Nombres: " + listaClientes.get(position).getNom());
        holder.viewDni.setText("DNI: " + listaClientes.get(position).getDni());
        holder.viewTelefono.setText("Teléfono: " + listaClientes.get(position).getTel());
        holder.viewDireccion.setText("Dirección: " + listaClientes.get(position).getDir());
        holder.viewEstado.setText("Estado: " + listaClientes.get(position).getEstado());
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombres, viewDni, viewTelefono, viewDireccion, viewEstado;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombres = itemView.findViewById(R.id.viewNombres);
            viewDni = itemView.findViewById(R.id.viewDni);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewEstado = itemView.findViewById(R.id.viewEstado);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerClienteActivity.class);
                    intent.putExtra("ID", listaClientes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}