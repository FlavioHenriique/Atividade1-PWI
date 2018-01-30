package com.mycompany.controle;

import com.mycompany.dao.Dao;
import com.mycompany.value.Cliente;
import java.sql.SQLException;

public class GerenciadorCliente {

    private DaoFactoryIF fabrica = null;
    private Dao<Cliente> clienteDao = null;

    public GerenciadorCliente() throws SQLException {
        fabrica = DaoFactory.createFactory();
        clienteDao = fabrica.criaClienteDao();
    }

    public boolean adicionaCliente(int id, String nome, String documento,
            boolean ativo, double saldo) throws SQLException {
        Cliente c = new Cliente(id, nome, documento, saldo, ativo);
        return clienteDao.incluir(c);
    }

    public void removeCliente(int id) throws SQLException {
        clienteDao.deletar(id);
    }

    public boolean atualizaCliente(int id, String nome, String documento,
            boolean ativo, double saldo) throws SQLException {

        Cliente c = new Cliente(id, nome, documento, saldo, ativo);
        return clienteDao.alterar(c);
    }
    
    public boolean buscaCliente(int id) throws SQLException{
        return clienteDao.buscar(id);
    }
    
    public void jdbc() throws SQLException{
        clienteDao.listarJDBC();
    }
}
