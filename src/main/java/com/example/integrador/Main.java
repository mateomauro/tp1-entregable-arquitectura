package com.example.integrador;

import com.example.integrador.dao.ClienteDAO;
import com.example.integrador.dao.FacturaDAO;
import com.example.integrador.dao.Factura_ProductoDAO;
import com.example.integrador.dao.ProductoDAO;
import com.example.integrador.factory.AbstractFactory;
import com.example.integrador.utils.HelperMySQL;

public class Main {

    public static void main(String[] args) throws Exception {
        // CREAMOS LAS TABLAS
        HelperMySQL dbMySQL = new HelperMySQL();
        dbMySQL.createTables();

        // ELEGIMOS LA BD MySQL DEL FACTORY
        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);

        // CREAMOS LOS DAO
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        FacturaDAO factura = chosenFactory.getFacturaDAO();
        ProductoDAO producto = chosenFactory.getProductoDAO();
        Factura_ProductoDAO factura_producto = chosenFactory.getFactura_ProductoDAO();

        // INSERTAMOS LOS RECURSOS CSV DADOS
        // cliente.insertClientesCSV();
        // producto.insertProductoCSV();
        // factura.insertFacturaCSV();
        // factura_producto.insertFactura_ProductoCSV();

        /*3) Escriba un programa JDBC que retorne el producto que más recaudó. Se define
        “recaudación” como cantidad de productos vendidos multiplicado por su valor.*/
        System.out.println("El producto que mas recaudo es: " + producto.getProductoQueMasRecaudo());

        /*4) Escriba un programa JDBC que imprima una lista de clientes, ordenada por a cuál se le
        facturó más.*/
        /*  para este ejercicio tuvimos 2 maneras de interpretar la consigna
        forma 1) los clientes que mas facturas se le hicieron
        forma 2) los clientes que mas facturaron en cuanto a recaudacion
        entonces decidimos hacer las dos por si acaso
         */
        System.out.println("Forma 1: " + cliente.getlistaFacturacionXcantidad());

        System.out.println("Forma 2: " + cliente.getlistaFacturacionXmonto());
        dbMySQL.closeConnection();
    }
}
