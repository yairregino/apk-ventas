package com.cdp.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cdp.agenda.entidades.Cliente;

import java.util.ArrayList;

public class DbClientes extends DbHelper {
    Context context;

    public DbClientes(Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCliente(String dni, String nom, String tel, String dir, String Estado) {
        long id = 0;

        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("dni", dni);
            values.put("nom", nom);
            values.put("tel", tel);
            values.put("dir", dir);
            values.put("Estado", Estado);

            id = db.insert(TABLE_CLIENTES, null, values);
        } catch (Exception ex) {
            ex.toString();
            Log.d("error:", ex.toString());
        }

        return id;
    }

    public ArrayList<Cliente> mostrarClientes() {
        SQLiteDatabase db = getWritableDatabase();

        ArrayList<Cliente> listaClientes = new ArrayList<>();
        Cliente cliente;
        Cursor cursorClientes;

        cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " ORDER BY nom ASC", null);

        if (cursorClientes.moveToFirst()) {
            do {
                cliente = new Cliente();
                cliente.setId(cursorClientes.getInt(0));
                cliente.setDni(cursorClientes.getString(1));
                cliente.setNom(cursorClientes.getString(2));
                cliente.setTel(cursorClientes.getString(3));
                cliente.setDir(cursorClientes.getString(4));
                cliente.setEstado(cursorClientes.getString(5));

                listaClientes.add(cliente);
            } while (cursorClientes.moveToNext());
        }

        cursorClientes.close();

        return listaClientes;
    }

    public Cliente verCliente(int id) {
        SQLiteDatabase db = getWritableDatabase();

        Cliente cliente = null;
        Cursor cursorClientes;

        cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorClientes.moveToFirst()) {
            cliente = new Cliente();
            cliente.setId(cursorClientes.getInt(0));
            cliente.setDni(cursorClientes.getString(1));
            cliente.setNom(cursorClientes.getString(2));
            cliente.setTel(cursorClientes.getString(3));
            cliente.setDir(cursorClientes.getString(4));
            cliente.setEstado(cursorClientes.getString(5));
        }

        cursorClientes.close();

        return cliente;
    }

    public boolean editarCliente(int id, String dni, String nom, String tel, String dir, String Estado) {
        boolean correcto = false;

        SQLiteDatabase db = getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CLIENTES + " SET dni = '" + dni + "', nom = '" + nom + "', tel = '" + tel +
                    "', dir = '" + dir + "', Estado = '" + Estado + "'  WHERE id='" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarCliente(int id) {
        boolean correcto = false;

        SQLiteDatabase db = getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CLIENTES + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
