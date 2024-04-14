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

import com.cdp.agenda.adaptadores.ListaProductosAdapter;
import com.cdp.agenda.db.DbProductos;
import com.cdp.agenda.entidades.Producto;

import java.util.ArrayList;

public class ListaProductosActivity extends AppCompatActivity {

    RecyclerView listaProductos;
    ArrayList<Producto> listaArrayProductos;
    ListaProductosAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        Button btnProductos = findViewById(R.id.registrar_producto);

        listaProductos = findViewById(R.id.listaprodutos);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));
        DbProductos dbProductos = new DbProductos(ListaProductosActivity.this);
        listaArrayProductos = new ArrayList<>();

        adapter = new ListaProductosAdapter(dbProductos.mostrarProductos());
        listaProductos.setAdapter(adapter);

        btnProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaProductosActivity.this, NuevoProductoActivity.class);
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
            Intent intent = new Intent(ListaProductosActivity.this, PrincipalMenuActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}