package com.example.integrador.factory;

import com.example.integrador.dao.ClienteDAO;
import com.example.integrador.dao.FacturaDAO;
import com.example.integrador.dao.Factura_ProductoDAO;
import com.example.integrador.dao.ProductoDAO;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    public abstract ClienteDAO getClienteDAO();
    public abstract ProductoDAO getProductoDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract Factura_ProductoDAO getFactura_ProductoDAO();
    //    public abstract DireccionDAO getDireccionDAO();
    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : {
                return MySQLDAOFactory.getInstance();
            }
            // esto no habría que implementarlo?
            case DERBY_JDBC: return null;
            default: return null;
        }
    }

}
