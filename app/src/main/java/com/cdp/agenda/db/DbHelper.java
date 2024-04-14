package com.cdp.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "agenda.db";
    public static final String TABLE_VENTAS = "ventas";
    public static final String TABLE_CLIENTES = "cliente";
    public static final String TABLE_DETALLE_VENTAS = "detalle_ventas ";
    public static final String TABLE_EMPELADOS = "empleado";
    public static final String TABLE_PRODUCTOS = "producto";
    public static final String TABLE_CONTACTOS = "t_contactos";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "correo_electronico TEXT)");

        String CREATE_TABLE_CLIENTE = "CREATE TABLE "+TABLE_CLIENTES+" (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dni TEXT," +
                "nom TEXT," +
                "tel TEXT," +
                "dir TEXT," +
                "Estado TEXT);";

        String CREATE_TABLE_DETALLE_VENTAS = "CREATE TABLE "+TABLE_DETALLE_VENTAS+" (" +
                "IdDetalleVentas INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IdVentas INTEGER," +
                "IdProducto INTEGER," +
                "Cantidad INTEGER," +
                "PrecioVenta INTEGER);";

        String CREATE_TABLE_EMPLEADO = "CREATE TABLE "+TABLE_EMPELADOS+" (" +
                "IdEmpleado INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Dni TEXT NOT NULL," +
                "Nombres TEXT," +
                "Telefono TEXT," +
                "Estado TEXT," +
                "User TEXT);";

        String CREATE_TABLE_PRODUCTO = "CREATE TABLE "+TABLE_PRODUCTOS+" (" +
                "IdProducto INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nombres TEXT," +
                "Precio INTEGER," +
                "Stock INTEGER," +
                "Descripcion TEXT NOT NULL," +
                "Estado TEXT);";

        String CREATE_TABLE_VENTAS = "CREATE TABLE "+TABLE_VENTAS+" (" +
                "IdVentas INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IdCliente INTEGER," +
                "IdEmpleado INTEGER," +
                "NumeroSerie TEXT," +
                "FechaVentas DATE," +
                "Monto INTEGER," +
                "Estado TEXT);";

        sqLiteDatabase.execSQL(CREATE_TABLE_CLIENTE);
        sqLiteDatabase.execSQL(CREATE_TABLE_DETALLE_VENTAS);
        sqLiteDatabase.execSQL(CREATE_TABLE_EMPLEADO);
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCTO);
        sqLiteDatabase.execSQL(CREATE_TABLE_VENTAS);

        insertarEmpleadoDePrueba(sqLiteDatabase);
    }

    private void insertarEmpleadoDePrueba(SQLiteDatabase db) {
        // Crear un ContentValues para insertar datos
        ContentValues values = new ContentValues();
        values.put("Dni", "12345678");
        values.put("Nombres", "Empleado de Prueba");
        values.put("Telefono", "987654321");
        values.put("Estado", "Activo");
        values.put("User", "usuario_prueba");

        // Insertar el empleado de prueba en la tabla
        db.insert(TABLE_EMPELADOS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CONTACTOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CLIENTES);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PRODUCTOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_VENTAS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_EMPELADOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_DETALLE_VENTAS);

        onCreate(sqLiteDatabase);

    }
}
