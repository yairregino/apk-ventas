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

import com.cdp.agenda.adaptadores.ListaClientesAdapter;
import com.cdp.agenda.adaptadores.ListaEmpleadosAdapter;
import com.cdp.agenda.db.DbClientes;
import com.cdp.agenda.db.DbEmpleados;
import com.cdp.agenda.entidades.Cliente;
import com.cdp.agenda.entidades.Empleado;

import java.util.ArrayList;

public class ListaClientesActivity extends AppCompatActivity {

    RecyclerView listaClientes;
    ArrayList<Cliente> listaArrayClientes;
    ListaClientesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        Button btnClientes = findViewById(R.id.registrar_cliente);


        listaClientes = findViewById(R.id.listaclientes);
        listaClientes.setLayoutManager(new LinearLayoutManager(this));
        DbClientes dbClientes = new DbClientes(ListaClientesActivity.this);
        listaArrayClientes = new ArrayList<>();

        adapter = new ListaClientesAdapter(dbClientes.mostrarClientes());
        listaClientes.setAdapter(adapter);

        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaClientesActivity.this, NuevoClienteActivity.class);
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
            Intent intent = new Intent(ListaClientesActivity.this, PrincipalMenuActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}