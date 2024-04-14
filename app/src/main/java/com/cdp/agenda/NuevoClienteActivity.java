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

import com.cdp.agenda.db.DbClientes;
import com.cdp.agenda.db.DbEmpleados;

public class NuevoClienteActivity extends AppCompatActivity {

    EditText txtNombres, txtDni, txtTelefono, txtEstado, txtDireccion;

    Button btnGuarda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_cliente);

        txtNombres = findViewById(R.id.txtNombres);
        txtDni = findViewById(R.id.txtDni);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEstado = findViewById(R.id.txtEstado);
        txtDireccion = findViewById(R.id.txtDireccion);

        btnGuarda = findViewById(R.id.btnGuardarCliente);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtDni.getText().toString().equals("") && !txtNombres.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtEstado.getText().toString().equals("") && !txtDireccion.getText().toString().equals("")) {

                    DbClientes db = new DbClientes(NuevoClienteActivity.this);
                    long id = db.insertarCliente(txtDni.getText().toString(), txtNombres.getText().toString(), txtTelefono.getText().toString(), txtDireccion.getText().toString(), txtEstado.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoClienteActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoClienteActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoClienteActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void limpiar() {
        txtDni.setText("");
        txtNombres.setText("");
        txtTelefono.setText("");
        txtEstado.setText("");
        txtDireccion.setText("");

        Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lista();
            }
        }, 1000);
    }

    private void lista(){
        Intent intent = new Intent(this, ListaClientesActivity.class);
        startActivity(intent);
    }
}