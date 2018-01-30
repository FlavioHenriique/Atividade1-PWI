package com.mycompany.controle;

import com.mycompany.dao.Dao;
import com.mycompany.value.Pedido;
import java.sql.SQLException;
import java.util.Date;

public class GerenciadorPedido {

    private DaoFactoryIF fabrica = null;
    private Dao<Pedido> pedidoDao = null;

    public GerenciadorPedido() throws SQLException {
        fabrica = DaoFactory.createFactory();
        pedidoDao = fabrica.criaPedidoDao();
    }

    public void adicionaPedido(int id, Date data, int cliente, float valor)
            throws SQLException {

        Pedido p = new Pedido(id, data, cliente, valor);
        pedidoDao.incluir(p);
    }

    public void removePedido(int id) throws SQLException {
        pedidoDao.deletar(id);
    }

    public boolean atualizaPedido(int id, Date data, int cliente, float valor)
            throws SQLException {
        Pedido p = new Pedido(id, data, cliente, valor);
        return pedidoDao.alterar(p);
    }
}
