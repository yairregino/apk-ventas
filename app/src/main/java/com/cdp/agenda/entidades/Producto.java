package com.cdp.agenda.entidades;

public class Producto {
    private Number IdProducto;

    private String Nombres;

    private Number Precio;

    private Number Stock;

    private String Descripcion;

    private  String Estado;

    public Number getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(Number idProducto) {
        IdProducto = idProducto;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public Number getPrecio() {
        return Precio;
    }

    public void setPrecio(Number precio) {
        Precio = precio;
    }

    public Number getStock() {
        return Stock;
    }

    public void setStock(Number stock) {
        Stock = stock;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
