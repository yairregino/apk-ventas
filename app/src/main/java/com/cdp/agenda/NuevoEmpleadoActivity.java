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

import com.cdp.agenda.db.DbEmpleados;
import com.cdp.agenda.db.DbProductos;

public class NuevoEmpleadoActivity extends AppCompatActivity {

    EditText txtNombres, txtDni, txtTelefono, txtEstado, txtUser;

    Button btnGuarda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_empleado);

        txtNombres = findViewById(R.id.txtNombres);
        txtDni = findViewById(R.id.txtDni);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEstado = findViewById(R.id.txtEstado);
        txtUser = findViewById(R.id.txtUser);

        btnGuarda = findViewById(R.id.btnGuardarEmpleado);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtDni.getText().toString().equals("") && !txtNombres.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtEstado.getText().toString().equals("") && !txtUser.getText().toString().equals("")) {

                    DbEmpleados db = new DbEmpleados(NuevoEmpleadoActivity.this);
                    long id = db.insertarEmpleado(txtDni.getText().toString(), txtNombres.getText().toString(), txtTelefono.getText().toString(), txtEstado.getText().toString(), txtUser.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoEmpleadoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoEmpleadoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoEmpleadoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void limpiar() {
        txtDni.setText("");
        txtNombres.setText("");
        txtTelefono.setText("");
        txtEstado.setText("");
        txtUser.setText("");

        Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lista();
            }
        }, 1000);
    }

    private void lista(){
        Intent intent = new Intent(this, ListaEmpleadosActivity.class);
        startActivity(intent);
    }

}