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

import com.cdp.agenda.db.DbClientes;
import com.cdp.agenda.db.DbEmpleados;
import com.cdp.agenda.entidades.Cliente;
import com.cdp.agenda.entidades.Empleado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerClienteActivity extends AppCompatActivity {

    EditText txtNombres, txtDni, txtTelefono, txtEstado, txtDireccion;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

    Cliente cliente;
    int id = 0;

    boolean correcto = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cliente);

        txtNombres = findViewById(R.id.txtNombres3);
        txtDni = findViewById(R.id.txtDni3);
        txtTelefono = findViewById(R.id.txtTelefono3);
        txtEstado = findViewById(R.id.txtEstado3);
        txtDireccion = findViewById(R.id.txtDireccion3);

        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuarda = findViewById(R.id.btnGuardarCliente2);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbClientes dbClientes = new DbClientes(VerClienteActivity.this);
        cliente = dbClientes.verCliente(id);

        if (cliente != null) {
            txtDni.setText(cliente.getDni());
            txtNombres.setText(cliente.getNom());
            txtTelefono.setText(cliente.getTel());
            txtEstado.setText(cliente.getEstado());
            txtDireccion.setText(cliente.getDir());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtDni.getText().toString().equals("") && !txtNombres.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtEstado.getText().toString().equals("") && !txtDireccion.getText().toString().equals("")) {

                    DbClientes db = new DbClientes(VerClienteActivity.this);
                    correcto = db.editarCliente(id, txtDni.getText().toString(), txtNombres.getText().toString(), txtTelefono.getText().toString(), txtDireccion.getText().toString(), txtEstado.getText().toString());

                    if(correcto){
                        Toast.makeText(VerClienteActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        lista();
                    } else {
                        Toast.makeText(VerClienteActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(VerClienteActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerClienteActivity.this);
                builder.setMessage("¿Desea eliminar este cliente?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbClientes.eliminarCliente(id)){
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
        Intent intent = new Intent(this, ListaClientesActivity.class);
        startActivity(intent);
    }
}