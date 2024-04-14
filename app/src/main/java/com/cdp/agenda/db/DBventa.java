package com.cdp.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.cdp.agenda.entidades.ItemCompra;
import com.cdp.agenda.entidades.Empleado;
import com.cdp.agenda.entidades.Cliente;
import com.cdp.agenda.entidades.Producto;
import com.cdp.agenda.entidades.Venta;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DBventa extends DbHelper {

    Context context;

    public DBventa(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long guardarVenta(Empleado empleado, Cliente cliente, List<ItemCompra> items, double montoVenta) {
        String numeroSerie = this.generarNumeroSerie();

        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String fechaVentas = fecha.format(formatoFecha);

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("IdCliente", cliente.getId());
            values.put("IdEmpleado", empleado.getIdEmpleado());
            values.put("NumeroSerie",  numeroSerie);
            values.put("FechaVentas", fechaVentas);
            values.put("Monto", montoVenta);
            values.put("Estado", "1");

            id = db.insert(TABLE_VENTAS, null, values);

            if(id > 0){
                for (int i = 0; i < items.size(); i++) {
                    ContentValues values2 = new ContentValues();
                    values2.put("IdVentas", id);
                    values2.put("IdProducto", items.get(i).getIdProducto());
                    values2.put("Cantidad",  items.get(i).getCantidad());
                    values2.put("PrecioVenta",  items.get(i).getPrecio());

                    db.insert(TABLE_DETALLE_VENTAS, null, values2);
                    db.execSQL("UPDATE " + TABLE_PRODUCTOS + " SET Stock = Stock - 1 WHERE IdProducto = '" + items.get(i).getIdProducto() + "'");
                }
            }else{
                Log.d("Resultado venta", "Fail");
            }
        } catch (Exception ex) {
            ex.toString();
            Log.d("error:", ex.toString());
        }

        return id;
    }

    public ArrayList<Venta> mostrarVentas() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Venta> listaVentas = new ArrayList<>();
        Venta venta;
        Cursor cursorVenta;

        cursorVenta = db.rawQuery("SELECT " + TABLE_EMPELADOS + ".Nombres, "+
                TABLE_CLIENTES + ".nom, "+
                TABLE_VENTAS + ".FechaVentas,"+
                TABLE_VENTAS + ".Monto,"+
                TABLE_VENTAS + ".IdVentas, "+
                TABLE_VENTAS + ".NumeroSerie "+
                "FROM " + TABLE_VENTAS +
                " INNER JOIN " + TABLE_EMPELADOS +
                " ON " + TABLE_VENTAS + ".IdEmpleado = " + TABLE_EMPELADOS + ".IdEmpleado" +
                " INNER JOIN " + TABLE_CLIENTES +
                " ON " + TABLE_VENTAS + ".IdCliente = " + TABLE_CLIENTES + ".id" +
                " ORDER BY " + TABLE_VENTAS + ".NumeroSerie DESC", null);

        if (cursorVenta.moveToFirst()) {
            do {
                venta = new Venta();
                venta.setEmpleado(cursorVenta.getString(0));
                venta.setCliente(cursorVenta.getString(1));
                venta.setFecha(cursorVenta.getString(2));
                venta.setTotal(cursorVenta.getString(3));
                venta.setIdVenta(cursorVenta.getInt(4));
                venta.setConsecutivo(cursorVenta.getString(5));
                listaVentas.add(venta);
            } while (cursorVenta.moveToNext());
        }

        cursorVenta.close();

        return listaVentas;
    }

    public ArrayList<ItemCompra> verDetallesVenta(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<ItemCompra> listaDetalles = new ArrayList<>();
        ItemCompra detalle;
        Cursor cursorDetalle;

        cursorDetalle = db.rawQuery(
                "SELECT " + TABLE_PRODUCTOS + ".Nombres, "+
                        TABLE_DETALLE_VENTAS + ".IdProducto, "+
                        TABLE_DETALLE_VENTAS + ".Cantidad,"+
                        TABLE_DETALLE_VENTAS + ".PrecioVenta "+
                        "FROM " + TABLE_DETALLE_VENTAS +
                        "INNER JOIN " + TABLE_PRODUCTOS +
                        " ON " + TABLE_DETALLE_VENTAS + ".IdProducto = " + TABLE_PRODUCTOS + ".IdProducto " +
                        "WHERE " + TABLE_DETALLE_VENTAS + ".IdVentas = " + id , null);

        if (cursorDetalle.moveToFirst()) {
            do {
                detalle = new ItemCompra();
                detalle.setNombreProducto(cursorDetalle.getString(0));
                detalle.setIdProducto(cursorDetalle.getInt(1));
                detalle.setCantidad(cursorDetalle.getInt(2));
                detalle.setPrecio(cursorDetalle.getDouble(3));

                listaDetalles.add(detalle);
            } while (cursorDetalle.moveToNext());
        }

        cursorDetalle.close();

        return listaDetalles;
    }

    public String generarNumeroSerie() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT MAX(NumeroSerie) FROM "+TABLE_VENTAS+"";
        Cursor cursor = db.rawQuery(query, null);

        String nuevoNumero;

        if (cursor.moveToFirst()) {
            String ultimoNumero = cursor.getString(0);

            int numero;
            if (ultimoNumero != null) {
                numero = Integer.parseInt(ultimoNumero.substring(2)) + 1;
            } else {
                numero = 1;
            }

            nuevoNumero = String.format("%05d", numero);
        } else {
            nuevoNumero = "00001";
        }

        cursor.close();
        db.close();

        return nuevoNumero;
    }

}
