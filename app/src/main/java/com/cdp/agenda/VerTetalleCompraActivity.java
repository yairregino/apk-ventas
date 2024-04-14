package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.cdp.agenda.adaptadores.ItemCompraAdaptador;
import com.cdp.agenda.db.DBventa;
import com.cdp.agenda.db.DbClientes;
import com.cdp.agenda.entidades.ItemCompra;

import java.util.List;

public class VerTetalleCompraActivity extends AppCompatActivity {

    int id = 0;
    String NumeroSerie = "";

    TextView txt_numero_serie, total_detalle_compra;

    RecyclerView listaProductos;

    ItemCompraAdaptador adaptador;

    List<ItemCompra> listaDetalles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tetalle_compra);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
                NumeroSerie = "";
            } else {
                id = extras.getInt("ID");
                NumeroSerie = extras.getString("NumeroSerie");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        txt_numero_serie = findViewById(R.id.txt_numero_serie);

        txt_numero_serie.setText("Venta #"+NumeroSerie);

        listaProductos = findViewById(R.id.lista_detalles);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));

        DBventa dBventa = new DBventa(VerTetalleCompraActivity.this);
        listaDetalles = dBventa.verDetallesVenta(id);

        adaptador = new ItemCompraAdaptador(VerTetalleCompraActivity.this, listaDetalles);
        listaProductos.setAdapter(adaptador);

        total_detalle_compra = findViewById(R.id.total_detalle_compra);

        double totalDetalle = 0;

        for (int i = 0; i < listaDetalles.size(); i++) {
            totalDetalle += listaDetalles.get(i).getPrecio() * listaDetalles.get(i).getCantidad();
        }

        total_detalle_compra.setText("$ "+totalDetalle);

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
            Intent intent = new Intent(VerTetalleCompraActivity.this, PrincipalMenuActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}