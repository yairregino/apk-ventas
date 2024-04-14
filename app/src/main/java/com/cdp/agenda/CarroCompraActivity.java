package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cdp.agenda.adaptadores.ItemCompraAdaptador;
import com.cdp.agenda.adaptadores.ItemProductoAdapter;
import com.cdp.agenda.db.DBventa;
import com.cdp.agenda.db.DbClientes;
import com.cdp.agenda.db.DbEmpleados;
import com.cdp.agenda.entidades.Cliente;
import com.cdp.agenda.entidades.Empleado;
import com.cdp.agenda.entidades.ItemCompra;

import java.util.ArrayList;
import java.util.List;

public class CarroCompraActivity extends AppCompatActivity {

    List<ItemCompra> carroCompras;
    TextView tvTotal;
    RecyclerView listaProductos;

    ItemCompraAdaptador adaptador;

    ArrayList<Empleado> listaArrayEmpleados;
    ArrayList<Cliente> listaArrayEClientes;

    Button bTerminarVenta;

    Spinner spinner1;

    Spinner spinner;
    double total_venta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro_compra);

        carroCompras = (List<ItemCompra>) getIntent().getSerializableExtra("CarroCompras");

        tvTotal = findViewById(R.id.textView6);

        total_venta = 0;

        for (int i = 0; i < carroCompras.size(); i++) {
            total_venta += carroCompras.get(i).getPrecio() * carroCompras.get(i).getCantidad();
        }

        listaProductos = findViewById(R.id.lista_items_compra);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));

        adaptador = new ItemCompraAdaptador(CarroCompraActivity.this, carroCompras);
        listaProductos.setAdapter(adaptador);

        tvTotal.setText("$ "+total_venta);


        DbClientes dbClientes = new DbClientes(CarroCompraActivity.this);
        listaArrayEClientes = dbClientes.mostrarClientes();

        spinner1 = findViewById(R.id.spinner);

        ArrayAdapter<Cliente> adaptador1 = new ArrayAdapter<Cliente>(this, android.R.layout.simple_spinner_item, listaArrayEClientes) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setText(getItem(position).getNom());
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getDropDownView(position, convertView, parent);
                view.setText(getItem(position).getNom());
                return view;
            }
        };

        adaptador1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adaptador1);


        DbEmpleados dbEmpleados = new DbEmpleados(CarroCompraActivity.this);
        listaArrayEmpleados = dbEmpleados.mostrarEmpleados();
        spinner = findViewById(R.id.spinner2);

        ArrayAdapter<Empleado> adaptador = new ArrayAdapter<Empleado>(this, android.R.layout.simple_spinner_item, listaArrayEmpleados) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setText(getItem(position).getNombres());
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getDropDownView(position, convertView, parent);
                view.setText(getItem(position).getNombres());
                return view;
            }
        };

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);


        bTerminarVenta = findViewById(R.id.buttontv);

        bTerminarVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cliente cliente = (Cliente) spinner1.getSelectedItem();
                Empleado empleado = (Empleado) spinner.getSelectedItem();

                DBventa db = new DBventa(CarroCompraActivity.this);
                long id = db.guardarVenta(empleado, cliente, carroCompras, total_venta);

                if (id > 0) {
                    Toast.makeText(CarroCompraActivity.this, "VENTA REGISTRADA", Toast.LENGTH_LONG).show();
                    Handler handler = new Handler(Looper.getMainLooper());

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(CarroCompraActivity.this, CmprarActivity.class);
                            startActivity(intent);
                        }
                    }, 1000);

                } else {
                    Toast.makeText(CarroCompraActivity.this, "ERROR AL GUARDAR LA VENTA", Toast.LENGTH_LONG).show();
                }
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
            Intent intent = new Intent(CarroCompraActivity.this, PrincipalMenuActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}