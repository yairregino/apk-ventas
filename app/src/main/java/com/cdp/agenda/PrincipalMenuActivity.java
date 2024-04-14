package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PrincipalMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_menu);

        Button btnProductos = findViewById(R.id.productos);
        Button btnEmpleados = findViewById(R.id.empleados);
        Button btnClientes = findViewById(R.id.clientes);
        Button btbnComprar = findViewById(R.id.ventas);
        Button btnListaVentas = findViewById(R.id.lista_ventas);


        btnProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalMenuActivity.this, ListaProductosActivity.class);
                startActivity(intent);
            }
        });

        btnEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalMenuActivity.this, ListaEmpleadosActivity.class);
                startActivity(intent);
            }
        });

        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalMenuActivity.this, ListaClientesActivity.class);
                startActivity(intent);
            }
        });

        btbnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalMenuActivity.this, CmprarActivity.class);
                startActivity(intent);
            }
        });

        btnListaVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalMenuActivity.this, VentasActivity.class);
                startActivity(intent);
            }
        });
    }
}