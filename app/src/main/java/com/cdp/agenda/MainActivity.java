package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.cdp.agenda.db.DbEmpleados;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    EditText txtUsuario, txtPassword;

    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPassword = findViewById(R.id.txtPassword);
        txtUsuario = findViewById(R.id.txtUsuario);

        btnLogin = findViewById(R.id.botonLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbEmpleados empleadosdb = new DbEmpleados(MainActivity.this);
                if(!txtUsuario.getText().toString().equals("") && !txtPassword.getText().toString().equals("") ) {
                    boolean login = empleadosdb.loginEmpleado(txtUsuario.getText().toString(), txtPassword.getText().toString());
                    if(login == true){
                        Intent intent = new Intent(MainActivity.this, PrincipalMenuActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "CREDENCIALES INCORRECTAS", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}