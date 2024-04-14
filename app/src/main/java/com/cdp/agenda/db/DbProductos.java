package com.cdp.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.cdp.agenda.entidades.Producto;

import java.util.ArrayList;
public class DbProductos  extends DbHelper {
    Context context;

    public DbProductos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarProducto(String nombres, Integer precio, Integer stock, String descripcion, String estado) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Nombres", nombres);
            values.put("Precio", precio);
            values.put("Stock",  stock);
            values.put("Descripcion", descripcion);
            values.put("Estado", estado);

            id = db.insert(TABLE_PRODUCTOS, null, values);
        } catch (Exception ex) {
            ex.toString();
            Log.d("error:", ex.toString());
        }

        return id;
    }

    public ArrayList<Producto> mostrarProductos() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Producto> listaProductos = new ArrayList<>();
        Producto producto;
        Cursor cursorProductos;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS + " ORDER BY Nombres ASC", null);

        if (cursorProductos.moveToFirst()) {
            do {
                producto = new Producto();
                producto.setIdProducto(cursorProductos.getInt(0));
                producto.setNombres(cursorProductos.getString(1));
                producto.setPrecio(Integer.parseInt(cursorProductos.getString(2)));
                producto.setStock(Integer.parseInt(cursorProductos.getString(3)));
                producto.setDescripcion(cursorProductos.getString(4));
                producto.setEstado(cursorProductos.getString(5));

                listaProductos.add(producto);
            } while (cursorProductos.moveToNext());
        }

        cursorProductos.close();

        return listaProductos;
    }

    public Producto verProducto(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Producto producto = null;
        Cursor cursorProductos;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS + " WHERE IdProducto = " + id + " LIMIT 1", null);

        if (cursorProductos.moveToFirst()) {
            producto = new Producto();
            producto.setIdProducto(cursorProductos.getInt(0));
            producto.setNombres(cursorProductos.getString(1));
            producto.setPrecio(Integer.parseInt(cursorProductos.getString(2)));
            producto.setStock(Integer.parseInt(cursorProductos.getString(3)));
            producto.setDescripcion(cursorProductos.getString(4));
            producto.setEstado(cursorProductos.getString(5));
        }

        cursorProductos.close();

        return producto;
    }
    public boolean editarProducto(Integer id ,String nombres, Integer precio, Integer stock, String descripcion, String estado) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_PRODUCTOS + " SET Nombres = '" + nombres + "', Precio = '" + precio + "', Stock = '" + stock + "' , Descripcion = '" + descripcion + "' , Estado = '" + estado + "'  WHERE IdProducto='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }


    public boolean eliminarProducto(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_PRODUCTOS + " WHERE IdProducto = '" + id + "'");
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
