package com.mycompany.dao;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.RowSet;
import javax.sql.rowset.Predicate;

public class Filtro implements Predicate {

    private static final String coluna = "valor";
    private double max;
    private double min;

    public Filtro(double min, double max) {
        this.max = max;
        this.min = min;
    }

    @Override
    public boolean evaluate(RowSet rs) {

        try {
            Object value = rs.getDouble(coluna);
            double salario = Double.parseDouble(value.toString());
            if ((salario >= min) && (salario <= max)) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Filtro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean evaluate(Object value, int column) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean evaluate(Object value, String columnName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
