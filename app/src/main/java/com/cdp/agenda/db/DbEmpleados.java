package com.cdp.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cdp.agenda.entidades.Empleado;

import java.util.ArrayList;

public class DbEmpleados extends DbHelper {
    Context context;

    public DbEmpleados(Context context) {
        super(context);
        this.context = context;
    }

    public long insertarEmpleado(String dni, String nombres, String telefono, String estado, String user) {
        long id = 0;

        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Dni", dni);
            values.put("Nombres", nombres);
            values.put("Telefono", telefono);
            values.put("Estado", estado);
            values.put("User", user);

            id = db.insert(TABLE_EMPELADOS, null, values);
        } catch (Exception ex) {
            ex.toString();
            Log.d("error:", ex.toString());
        }

        return id;
    }

    public ArrayList<Empleado> mostrarEmpleados() {
        SQLiteDatabase db = getWritableDatabase();

        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        Empleado empleado;
        Cursor cursorEmpleados;

        cursorEmpleados = db.rawQuery("SELECT * FROM "+TABLE_EMPELADOS+" ORDER BY Nombres ASC", null);

        if (cursorEmpleados.moveToFirst()) {
            do {
                empleado = new Empleado();
                empleado.setIdEmpleado(cursorEmpleados.getInt(0));
                empleado.setDni(cursorEmpleados.getString(1));
                empleado.setNombres(cursorEmpleados.getString(2));
                empleado.setTelefono(cursorEmpleados.getString(3));
                empleado.setEstado(cursorEmpleados.getString(4));
                empleado.setUser(cursorEmpleados.getString(5));

                listaEmpleados.add(empleado);
            } while (cursorEmpleados.moveToNext());
        }

        cursorEmpleados.close();

        return listaEmpleados;
    }

    public Empleado verEmpleado(int id) {
        SQLiteDatabase db = getWritableDatabase();

        Empleado empleado = null;
        Cursor cursorEmpleados;

        cursorEmpleados = db.rawQuery("SELECT * FROM "+TABLE_EMPELADOS+" WHERE IdEmpleado = " + id + " LIMIT 1", null);

        if (cursorEmpleados.moveToFirst()) {
            empleado = new Empleado();
            empleado.setIdEmpleado(cursorEmpleados.getInt(0));
            empleado.setDni(cursorEmpleados.getString(1));
            empleado.setNombres(cursorEmpleados.getString(2));
            empleado.setTelefono(cursorEmpleados.getString(3));
            empleado.setEstado(cursorEmpleados.getString(4));
            empleado.setUser(cursorEmpleados.getString(5));
        }

        cursorEmpleados.close();

        return empleado;
    }

    public boolean editarEmpleado(int id, String dni, String nombres, String telefono, String estado, String user) {
        boolean correcto = false;

        SQLiteDatabase db = getWritableDatabase();

        try {
            db.execSQL("UPDATE "+TABLE_EMPELADOS+" SET Dni = '" + dni + "', Nombres = '" + nombres + "', Telefono = '" + telefono +
                    "', Estado = '" + estado + "', User = '" + user + "'  WHERE IdEmpleado='" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarEmpleado(int id) {
        boolean correcto = false;

        SQLiteDatabase db = getWritableDatabase();

        try {
            db.execSQL("DELETE FROM "+TABLE_EMPELADOS+" WHERE IdEmpleado = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean loginEmpleado(String user, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        boolean loginCorrecto = false;

        try {
            // Consultar la base de datos para encontrar el empleado con el usuario proporcionado
            cursor = db.rawQuery("SELECT * FROM " + TABLE_EMPELADOS + " WHERE User = ? AND Dni = ?", new String[]{user, password});

            // Verificar si se encontró un empleado con el usuario y contraseña proporcionados
            if (cursor != null && cursor.moveToFirst()) {
                loginCorrecto = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return loginCorrecto;
    }

}
