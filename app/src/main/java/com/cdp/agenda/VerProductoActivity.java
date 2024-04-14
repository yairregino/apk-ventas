package com.cdp.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdp.agenda.db.DbProductos;
import com.cdp.agenda.entidades.Producto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerProductoActivity extends AppCompatActivity {

    EditText txtNombre, txtPrecio, txtStock, txtDescripcion, txtEstado;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

    Producto producto;
    int id = 0;

    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_producto);

        txtNombre = findViewById(R.id.txtNombre2);
        txtPrecio = findViewById(R.id.txtPrecio2);
        txtStock = findViewById(R.id.txtStock2);
        txtDescripcion = findViewById(R.id.txtDescripcion2);
        txtEstado = findViewById(R.id.txtEstado2);

        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuarda = findViewById(R.id.btnGuardaProducto);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbProductos dbProductos = new DbProductos(VerProductoActivity.this);
        producto = dbProductos.verProducto(id);

        if(producto != null){
            txtNombre.setText(producto.getNombres());
            txtDescripcion.setText(producto.getDescripcion());
            txtEstado.setText(producto.getEstado());
            txtStock.setText(producto.getStock().toString());
            txtPrecio.setText(producto.getPrecio().toString());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtEstado.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("") && !txtNombre.getText().toString().equals("") && !txtPrecio.getText().toString().equals("") && !txtStock.getText().toString().equals("")) {
                    correcto = dbProductos.editarProducto(id ,txtNombre.getText().toString(),Integer.parseInt(txtPrecio.getText().toString()), Integer.parseInt(txtStock.getText().toString()), txtDescripcion.getText().toString(), txtEstado.getText().toString());

                    if(correcto){
                        Toast.makeText(VerProductoActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        lista();
                    } else {
                        Toast.makeText(VerProductoActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(VerProductoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerProductoActivity.this);
                builder.setMessage("¿Desea eliminar este producto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbProductos.eliminarProducto(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, ListaProductosActivity.class);
        startActivity(intent);
    }
}