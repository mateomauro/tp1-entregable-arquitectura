package com.example.integrador.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {

    void insert(T t) throws SQLException;

    void delete(int id) throws SQLException;

    void update(T t, int id) throws SQLException;

    T getOne(int id) throws SQLException;

    List<T> getAll() throws SQLException;
}
