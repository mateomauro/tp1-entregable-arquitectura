package com.example.integrador;

import com.example.integrador.dao.ClienteDAO;
import com.example.integrador.dao.FacturaDAO;
import com.example.integrador.dao.Factura_ProductoDAO;
import com.example.integrador.dao.ProductoDAO;
import com.example.integrador.factory.AbstractFactory;
import com.example.integrador.utils.HelperMySQL;

public class Main {

    public static void main(String[] args) throws Exception {
        //CREAMOS LAS TABLAS
        HelperMySQL dbMySQL = new HelperMySQL();
        dbMySQL.createTables();

        //ELEGIMOS LA BD MySQL DEL FACTORY
        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);

        //CREAMOS LOS DAO
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        FacturaDAO factura = chosenFactory.getFacturaDAO();
        ProductoDAO producto = chosenFactory.getProductoDAO();
        Factura_ProductoDAO factura_producto = chosenFactory.getFactura_ProductoDAO();

        //INSERTAMOS LOS RECURSOS CSV DADOS
//        cliente.insertClientsCSV();
//        producto.insertProductCSV();
//        factura.insertFacturaCSV();
//        factura_producto.insertFactura_ProductoCSV();

        /*3) Escriba un programa JDBC que retorne el producto que más recaudó. Se define
        “recaudación” como cantidad de productos vendidos multiplicado por su valor.*/
        System.out.println("el producto que mas recaudo es: " + producto.getProductoQueMasRecaudo());

        /*4) Escriba un programa JDBC que imprima una lista de clientes, ordenada por a cuál se le
        facturó más.*/
        /*  para este ejercicio tuvimos 2 maneras de interpretar la consigna
        forma 1) los clientes que mas facturas se le hicieron
        forma 2) los clientes que mas facturaron en cuanto a recaudacion
        entonces decidimos hacer las dos por si acaso
         */
        System.out.println("Forma 1: " + cliente.getlistaFacturacionXcantidad());

        System.out.println("Forma 2: " + cliente.getlistaFacturacionXmonto());

        //ANDA BIEN EL CRUD DE CLIENTE DAO
        //cliente.insert(new Cliente("hola", "hola"));
        //cliente.delete(105);
        //cliente.update(new Cliente("mateo", "mateomaurotandil"), 2);
        //System.out.println(cliente.getOne(1));
        //System.out.println(cliente.getAll());


        //CRUD DE PRODUCTO ANDA BIEN
        //producto.insert(new Producto(4,"telefono", 2000));
        //producto.delete(101);
        //producto.update(new Producto(1,"sugus", 1100),2);
        //System.out.println(producto.getOne(2));
        //System.out.println(producto.getAll());


        //factura.insert(new Factura(4, 1));
        //factura.delete(514);
        //factura.update(new Factura(3, 2), 2);
        //System.out.println(factura.getOne(1));
        //System.out.println(factura.getAll());


        //factura_producto.insert(new Factura_Producto(3, 1, 123123));
        //factura_producto.delete(3,3);
        //factura_producto.update(new Factura_Producto(3,1, 0), 3,1);
        //System.out.println(factura_producto.getOne(1, 1));
        //System.out.println(factura_producto.getAll());
        dbMySQL.closeConnection();
    }
}
