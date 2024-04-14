package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cdp.agenda.adaptadores.ItemVentaAdapter;
import com.cdp.agenda.adaptadores.ListaClientesAdapter;
import com.cdp.agenda.db.DBventa;
import com.cdp.agenda.db.DbClientes;
import com.cdp.agenda.entidades.Cliente;
import com.cdp.agenda.entidades.Venta;

import java.util.ArrayList;

public class VentasActivity extends AppCompatActivity {

    RecyclerView listaClientes;

    ArrayList<Venta> listaArrayVentas;

    ItemVentaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);


        listaClientes = findViewById(R.id.listado_ventas_realizadas);
        listaClientes.setLayoutManager(new LinearLayoutManager(this));
        DBventa dBventa = new DBventa(VentasActivity.this);
        listaArrayVentas = dBventa.mostrarVentas();

        adapter = new ItemVentaAdapter(listaArrayVentas);
        listaClientes.setAdapter(adapter);
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
            Intent intent = new Intent(VentasActivity.this, PrincipalMenuActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}