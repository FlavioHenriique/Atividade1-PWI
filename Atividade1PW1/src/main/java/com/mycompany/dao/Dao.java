
package com.mycompany.dao;

import java.sql.SQLException;
import java.util.List;


public interface Dao<T> {
    
    boolean incluir(T obj) throws SQLException;
    List<T> listar() throws SQLException;
    boolean deletar(Object obj) throws SQLException;
    boolean alterar(T obj) throws SQLException;
    boolean buscar(Object obj) throws SQLException;
    void listarJDBC() throws SQLException;
}
