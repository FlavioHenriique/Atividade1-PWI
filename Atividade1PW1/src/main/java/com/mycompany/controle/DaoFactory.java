
package com.mycompany.controle;


public class DaoFactory {
    
    public static DaoFactoryIF createFactory(){
        return new DaoFactoryBD();
    }
}
