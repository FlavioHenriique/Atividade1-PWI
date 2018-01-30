
package com.mycompany.controle;

import com.mycompany.dao.ClienteDao;
import com.mycompany.dao.PedidoDao;
import java.sql.SQLException;


public class DaoFactoryBD implements DaoFactoryIF{

    @Override
    public ClienteDao criaClienteDao() throws SQLException{
        return new ClienteDao();
    }

    @Override
    public PedidoDao criaPedidoDao() throws SQLException{
        return new PedidoDao();
    }


    
    
}
