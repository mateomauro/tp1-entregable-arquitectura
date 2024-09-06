package com.example.ejemplodaoypatrones.dto;

public class ClienteDTO {
    private int idCLiente;
    private String nombre;
    private String email;

    public ClienteDTO(){}

    public int getIdCLiente() {
        return idCLiente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public ClienteDTO(int idCLiente, String nombre, String email){
        this.idCLiente = idCLiente;
        this.nombre = nombre;
        this.email = email;
    }


}
