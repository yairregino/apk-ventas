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

import com.cdp.agenda.db.DbEmpleados;
import com.cdp.agenda.db.DbProductos;
import com.cdp.agenda.entidades.Empleado;
import com.cdp.agenda.entidades.Producto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerEmpleadoActivity extends AppCompatActivity {

    EditText txtNombres, txtDni, txtTelefono, txtEstado, txtUser;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

    Empleado empleado;
    int id = 0;

    boolean correcto = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_empleado);

        txtNombres = findViewById(R.id.txtNombres2);
        txtDni = findViewById(R.id.txtDni2);
        txtTelefono = findViewById(R.id.txtTelefono2);
        txtEstado = findViewById(R.id.txtEstado2);
        txtUser = findViewById(R.id.txtUser2);

        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuarda = findViewById(R.id.btnGuardarEmpleado2);

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

        final DbEmpleados dbEmpleados = new DbEmpleados(VerEmpleadoActivity.this);
        empleado = dbEmpleados.verEmpleado(id);

        if(empleado != null){
            txtDni.setText(empleado.getDni());
            txtNombres.setText(empleado.getNombres());
            txtTelefono.setText(empleado.getTelefono());
            txtEstado.setText(empleado.getEstado());
            txtUser.setText(empleado.getUser());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtDni.getText().toString().equals("") && !txtNombres.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtEstado.getText().toString().equals("") && !txtUser.getText().toString().equals("")) {

                    DbEmpleados db = new DbEmpleados(VerEmpleadoActivity.this);
                    correcto = db.editarEmpleado(id, txtDni.getText().toString(), txtNombres.getText().toString(), txtTelefono.getText().toString(), txtEstado.getText().toString(), txtUser.getText().toString());

                    if(correcto){
                        Toast.makeText(VerEmpleadoActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        lista();
                    } else {
                        Toast.makeText(VerEmpleadoActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(VerEmpleadoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });


        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerEmpleadoActivity.this);
                builder.setMessage("¿Desea eliminar este empleado?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbEmpleados.eliminarEmpleado(id)){
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
        Intent intent = new Intent(this, ListaEmpleadosActivity.class);
        startActivity(intent);
    }
}