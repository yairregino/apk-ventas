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
import com.cdp.agenda.VerEmpleadoActivity;
import com.cdp.agenda.entidades.Empleado;

import java.util.ArrayList;

public class ListaEmpleadosAdapter extends RecyclerView.Adapter<ListaEmpleadosAdapter.EmpleadoViewHolder> {

    private ArrayList<Empleado> listaEmpleados;
    private ArrayList<Empleado> listaOriginal;

    public ListaEmpleadosAdapter(ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaEmpleados);
    }

    @NonNull
    @Override
    public EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_empleado, null, false);
        return new EmpleadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadoViewHolder holder, int position) {
        holder.viewNombres.setText("Nombres: " + listaEmpleados.get(position).getNombres());
        holder.viewDni.setText("DNI: " + listaEmpleados.get(position).getDni());
        holder.viewTelefono.setText("Teléfono: " + listaEmpleados.get(position).getTelefono());
        holder.viewEstado.setText("Estado: " + listaEmpleados.get(position).getEstado());
        holder.viewUser.setText("Usuario: " + listaEmpleados.get(position).getUser());
    }

    @Override
    public int getItemCount() {
        return listaEmpleados.size();
    }

    public class EmpleadoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombres, viewDni, viewTelefono, viewEstado, viewUser;

        public EmpleadoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombres = itemView.findViewById(R.id.viewNombres);
            viewDni = itemView.findViewById(R.id.viewDni);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewEstado = itemView.findViewById(R.id.viewEstado);
            viewUser = itemView.findViewById(R.id.viewUser);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerEmpleadoActivity.class);
                    intent.putExtra("ID", listaEmpleados.get(getAdapterPosition()).getIdEmpleado());
                    context.startActivity(intent);
                }
            });
        }
    }
}