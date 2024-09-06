package com.example.ejemplodaoypatrones.dto;

public class Factura_ProductoDTO {
    private int idFactura;
    private int idProducto;
    private int cantidad;

    public Factura_ProductoDTO(){}

    public Factura_ProductoDTO(int idFactura, int idProducto, int cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

}
