package com.example.ejemplodaoypatrones.entities;

public class Cliente {
    private int idCLiente;
    private String nombre;
    private String email;

    public Cliente(){}

    public Cliente(String nombre, String email){
        this.nombre = nombre;
        this.email = email;
    }

    public Cliente(int idCLiente, String nombre, String email){
        this.nombre = nombre;
        this.email = email;
        this.idCLiente = idCLiente;
    }


    public int getIdCLiente() {
        return idCLiente;
    }

    public void setIdCLiente(int idCLiente) {
        this.idCLiente = idCLiente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCLiente=" + idCLiente +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
