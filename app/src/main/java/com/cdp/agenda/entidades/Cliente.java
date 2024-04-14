package com.cdp.agenda.entidades;

public class Cliente {
    private int id;
    private String dni;
    private String nom;
    private String tel;
    private String dir;
    private String Estado;


    // Métodos get
    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNom() {
        return nom;
    }

    public String getTel() {
        return tel;
    }

    public String getDir() {
        return dir;
    }

    public String getEstado() {
        return Estado;
    }

    // Métodos set
    public void setId(int id) {
        this.id = id;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
}

