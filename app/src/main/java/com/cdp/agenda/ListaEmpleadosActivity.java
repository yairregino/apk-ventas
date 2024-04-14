package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.cdp.agenda.adaptadores.ListaEmpleadosAdapter;
import com.cdp.agenda.adaptadores.ListaProductosAdapter;
import com.cdp.agenda.db.DbEmpleados;
import com.cdp.agenda.db.DbProductos;
import com.cdp.agenda.entidades.Empleado;
import com.cdp.agenda.entidades.Producto;

import java.util.ArrayList;

public class ListaEmpleadosActivity extends AppCompatActivity {

    RecyclerView listaEmpleados;
    ArrayList<Empleado> listaArrayEmpleados;
    ListaEmpleadosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empleados);

        Button btnEmpleados = findViewById(R.id.registrar_empleado);

        listaEmpleados = findViewById(R.id.listaempleados);
        listaEmpleados.setLayoutManager(new LinearLayoutManager(this));
        DbEmpleados dbEmpleados = new DbEmpleados(ListaEmpleadosActivity.this);
        listaArrayEmpleados = new ArrayList<>();

        adapter = new ListaEmpleadosAdapter(dbEmpleados.mostrarEmpleados());
        listaEmpleados.setAdapter(adapter);

        btnEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaEmpleadosActivity.this, NuevoEmpleadoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_button) {
            Intent intent = new Intent(ListaEmpleadosActivity.this, PrincipalMenuActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}