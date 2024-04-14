package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.cdp.agenda.adaptadores.ItemProductoAdapter;
import com.cdp.agenda.adaptadores.ListaProductosAdapter;
import com.cdp.agenda.db.DbProductos;
import com.cdp.agenda.entidades.Empleado;
import com.cdp.agenda.entidades.ItemCompra;
import com.cdp.agenda.entidades.Producto;

import java.util.ArrayList;
import java.util.List;

public class CmprarActivity extends AppCompatActivity {

    RecyclerView listaProductos;
    List<Producto> listaArrayProductos;

    TextView tvCantProductos;
    Button btnVerCarro;
    ItemProductoAdapter adaptador;

    List<ItemCompra> carroCompras = new ArrayList<>();

    ArrayList<Empleado> listaArrayEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmprar);

        tvCantProductos = findViewById(R.id.tvCantProductos);
        btnVerCarro = findViewById(R.id.btnVerCarro);
        listaProductos = findViewById(R.id.lista_productos_vender);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));

        DbProductos dbProductos = new DbProductos(CmprarActivity.this);
        listaArrayProductos = new ArrayList<>();

        adaptador = new ItemProductoAdapter(CmprarActivity.this, tvCantProductos, btnVerCarro, dbProductos.mostrarProductos() , carroCompras);
        listaProductos.setAdapter(adaptador);
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
            Intent intent = new Intent(CmprarActivity.this, PrincipalMenuActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}