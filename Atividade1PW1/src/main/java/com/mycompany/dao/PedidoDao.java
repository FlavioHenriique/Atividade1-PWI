package com.mycompany.dao;

import com.mycompany.controle.ConFactory;
import com.mycompany.value.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class PedidoDao implements Dao<Pedido> {

    private Connection con;

    public PedidoDao() throws SQLException {
        ConFactory factory = new ConFactory();
        con = factory.getConnection();
    }

    @Override
    public boolean incluir(Pedido obj) throws SQLException {

        List<Pedido> pedidos = listar();
        for (Pedido p : pedidos) {
            if (p.getId() == obj.getId()) {
                return false;
            }
        }
        PreparedStatement stmt = con.prepareStatement("insert into pedido(id,data,"
                + "cliente,valor) values (?,?,?,?)");
        stmt.setInt(1, obj.getId());
        stmt.setDate(2, (java.sql.Date) obj.getData());
        stmt.setInt(3, obj.getCliente());
        stmt.setFloat(4, obj.getValor());

        stmt.execute();
        stmt.close();
        return true;
    }

    @Override
    public List<Pedido> listar() throws SQLException {

        List<Pedido> pedidos = new ArrayList<>();
        PreparedStatement stmt = con.prepareStatement("select * from pedido");
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            Pedido p = new Pedido();
            p.setCliente(resultado.getInt("cliente"));
            p.setData(resultado.getDate("data"));
            p.setId(resultado.getInt("id"));
            p.setValor(resultado.getFloat("valor"));
            pedidos.add(p);
        }
        stmt.close();
        return pedidos;
    }

    @Override
    public boolean deletar(Object obj) throws SQLException {

        List<Pedido> pedidos = listar();
        PreparedStatement stmt = con.prepareStatement("delete from "
                + "pedido where id=?");
       stmt.setInt(1, (int)obj);
       stmt.execute();
       stmt.close();
       return true;

    }

    @Override
    public boolean alterar(Pedido obj) throws SQLException {

        List<Pedido> pedidos = listar();
        PreparedStatement stmt = con.prepareStatement("update pedido set id=?,"
                + "data=?,cliente=?,valor=? where id=?");
        stmt.setInt(1, obj.getId());
        stmt.setDate(2, (Date) obj.getData());
        stmt.setInt(3, obj.getCliente());
        stmt.setFloat(4, obj.getValor());
        stmt.setInt(5, obj.getId());

        int result = stmt.executeUpdate();
        stmt.close();
        return result > 0;
    }

    @Override
    public boolean buscar(Object obj) throws SQLException {
        return true;
    }

    @Override
    public void listarJDBC() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
