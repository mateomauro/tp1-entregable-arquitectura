package com.example.integrador.dto;

public class ClienteDTO {
    private final int idCLiente;
    private final String nombre;
    private final String email;
    private final float facturacion;

    public ClienteDTO(int idCLiente, String nombre, String email, float facturacion){
        this.idCLiente = idCLiente;
        this.nombre = nombre;
        this.email = email;
        this.facturacion = facturacion;
    }

    public int getIdCLiente() {
        return idCLiente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public float getFacturacion() {
        return facturacion;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "idCLiente=" + idCLiente +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", facturacion=" + facturacion +
                '}';
    }
}
