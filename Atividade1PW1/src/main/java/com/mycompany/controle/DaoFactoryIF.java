package com.mycompany.controle;

import com.mycompany.dao.ClienteDao;
import com.mycompany.dao.PedidoDao;
import java.sql.SQLException;

public interface DaoFactoryIF {

    ClienteDao criaClienteDao() throws SQLException;

    PedidoDao criaPedidoDao() throws SQLException;
    
}
