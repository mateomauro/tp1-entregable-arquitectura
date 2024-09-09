package com.example.integrador.dto;

public class ProductoDTO {
    private final int idProducto;
    private final String nombre;
    private final float valor;
    private final float recaudacion;


    public ProductoDTO(int idProducto, String nombre, float valor, float recaudacion){
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.valor = valor;
        this.recaudacion = recaudacion;
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

    public float getRecaudacion() {
        return recaudacion;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", recaudacion=" + recaudacion +
                '}';
    }
}
