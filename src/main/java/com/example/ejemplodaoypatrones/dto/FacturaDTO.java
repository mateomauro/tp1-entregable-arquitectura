package com.example.ejemplodaoypatrones.dto;

public class FacturaDTO {
    private int idFactura;
    //relacion con la tabla cliente
    private int idCliente;

    public FacturaDTO(){}

    public FacturaDTO(int idFactura, int idCliente){
        this.idCliente = idCliente;
        this.idFactura = idFactura;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

}
