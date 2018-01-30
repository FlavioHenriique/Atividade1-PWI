package com.mycompany.dao;

import com.mycompany.controle.ConFactory;
import com.mycompany.value.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class ClienteDao implements Dao<Cliente> {

    private Connection con;

    public ClienteDao() throws SQLException {
        ConFactory factory = new ConFactory();
        con = factory.getConnection();
    }

    @Override
    public boolean incluir(Cliente obj) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("insert into cliente(id,nome,"
                + "documento,saldo,ativo) values (?,?,?,?,?)");

        List<Cliente> clientes = listar();
        for (Cliente c : clientes) {
            if (c.getId() == obj.getId()) {
                System.out.println("Este ID já está sendo utilizado.");
                return false;
            }
        }
        stmt.setInt(1, obj.getId());
        stmt.setString(2, obj.getNome());
        stmt.setString(3, obj.getDocumento());
        stmt.setDouble(4, obj.getSaldo());
        stmt.setBoolean(5, obj.isAtivo());

        stmt.execute();
        stmt.close();
        return true;
    }

    @Override
    public List<Cliente> listar() throws SQLException {
        PreparedStatement stmt = con.prepareStatement("select * from cliente");
        ResultSet resultado = stmt.executeQuery();
        List<Cliente> clientes = new ArrayList<>();
        while (resultado.next()) {
            Cliente c = new Cliente();
            c.setAtivo(resultado.getBoolean("ativo"));
            c.setDocumento(resultado.getString("documento"));
            c.setId(resultado.getInt("id"));
            c.setNome(resultado.getString("nome"));
            c.setSaldo(resultado.getFloat("saldo"));
            clientes.add(c);
        }
        stmt.close();

        return clientes;
    }

    @Override
    public boolean deletar(Object obj) throws SQLException {

        PreparedStatement stmt = con.prepareStatement("delete from cliente where id=?");
        stmt.setInt(1, (int) obj);
        stmt.execute();
        stmt.close();
        return true;
    }

    @Override
    public boolean alterar(Cliente obj) throws SQLException {

        List<Cliente> clientes = listar();
        for (Cliente c : clientes) {
            if (c.getId() == obj.getId()) {
                PreparedStatement stmt = con.prepareStatement("update cliente set id=?,"
                        + "nome=?,documento=?,saldo=?,ativo=? where id=?");
                stmt.setInt(1, obj.getId());
                stmt.setString(2, obj.getNome());
                stmt.setString(3, obj.getDocumento());
                stmt.setDouble(4, obj.getSaldo());
                stmt.setBoolean(5, obj.isAtivo());
                stmt.setInt(6, obj.getId());
                int result = stmt.executeUpdate();
                stmt.close();
                return result > 0;
            }
        }
        return false;

    }

    @Override
    public boolean buscar(Object obj) throws SQLException {
        List<Cliente> clientes = listar();
        for (Cliente c : clientes) {
            if (c.getId() == (int) obj) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void listarJDBC() throws SQLException {
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet crs = factory.createCachedRowSet();
        CachedRowSet crs2 = factory.createCachedRowSet();
        crs2.setUsername("postgres");
        crs2.setPassword("flavio22");
        crs2.setUrl("jdbc:postgresql://127.0.0.1:5432/AtividadePW1");

        crs.setUsername("postgres");
        crs.setPassword("flavio22");
        crs.setUrl("jdbc:postgresql://127.0.0.1:5432/AtividadePW1");
        crs.setCommand("select valor,cliente from pedido");
        crs.execute();
        crs2.setCommand("select id,nome from cliente");
        crs2.execute();
        
        JoinRowSet jors = factory.createJoinRowSet();
        jors.addRowSet(crs,"cliente");
        jors.addRowSet(crs2,"id");
        while(jors.next()){
                
            System.out.println(jors.getString("nome"));
        }
        
        FilteredRowSet frs = factory.createFilteredRowSet();
        
        frs.setUsername("postgres");
        frs.setPassword("flavio22");
        frs.setUrl("jdbc:postgresql://127.0.0.1:5432/AtividadePW1");
        frs.setCommand("select * from pedido");
        frs.execute();
        System.out.println("Antes do filtro:");
        while(frs.next()){
            System.out.println(frs.getFloat("valor"));
        }
        System.out.println("Depois do filtro:");
        frs.beforeFirst();
        frs.setFilter(new Filtro(90,140));
        while(frs.next()){
            System.out.println(frs.getFloat("valor"));
        }
        
    }

}
