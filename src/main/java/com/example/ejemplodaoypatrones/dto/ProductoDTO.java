package com.example.ejemplodaoypatrones.dto;

public class ProductoDTO {
    private int idProducto;
    private String nombre;
    private float valor;


    public ProductoDTO(){}

    public ProductoDTO(int idProducto, String nombre, float valor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public float getValor() {
        return valor;
    }

}
