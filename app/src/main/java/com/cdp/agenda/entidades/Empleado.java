package com.cdp.agenda.entidades;

public class Empleado {

    // Atributos
    private int IdEmpleado;
    private String Dni;
    private String Nombres;
    private String Telefono;
    private String Estado;
    private String User;

    // Getters y Setters

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.IdEmpleado = idEmpleado;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        this.Dni = dni;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        this.Nombres = nombres;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        this.Estado = estado;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        this.User = user;
    }
}