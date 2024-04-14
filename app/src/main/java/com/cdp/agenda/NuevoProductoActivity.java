package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdp.agenda.db.DbProductos;

public class NuevoProductoActivity extends AppCompatActivity {

    EditText txtNombre, txtPrecio, txtStock, txtDescripcion, txtEstado;
    Button btnGuarda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);

        txtNombre = findViewById(R.id.txtNombre);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtStock = findViewById(R.id.txtStock);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtEstado = findViewById(R.id.txtEstado);

        btnGuarda = findViewById(R.id.btnGuardaProducto);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtEstado.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("") && !txtNombre.getText().toString().equals("") && !txtPrecio.getText().toString().equals("") && !txtStock.getText().toString().equals("")) {

                    DbProductos db = new DbProductos(NuevoProductoActivity.this);
                    long id = db.insertarProducto(txtNombre.getText().toString(),Integer.parseInt(txtPrecio.getText().toString()), Integer.parseInt(txtStock.getText().toString()), txtDescripcion.getText().toString(), txtEstado.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoProductoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoProductoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoProductoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtNombre.setText("");
        txtEstado.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtStock.setText("");

        Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lista();
            }
        }, 1000);
    }

    private void lista(){
        Intent intent = new Intent(this, ListaProductosActivity.class);
        startActivity(intent);
    }
}