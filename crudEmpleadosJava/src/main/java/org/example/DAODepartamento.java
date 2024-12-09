package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAODepartamento {
    Connection conn;
    DTODepartamento DTODepartamento;
    ArrayList<DTODepartamento> listaDTODepartamentos;

    public DAODepartamento() {
        conn = DB.getConexion();
        listaDTODepartamentos = new ArrayList<>();
    }

    public ArrayList<DTODepartamento> getAllDepartamentos() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM departamentos");
        ResultSet rs = ps.executeQuery();
        listaDTODepartamentos.clear();

        while (rs.next()) {
            DTODepartamento DTODepartamento = new DTODepartamento(
                    rs.getInt("id"),
                    rs.getString("nombre")
            );
            listaDTODepartamentos.add(DTODepartamento);
        }
        return listaDTODepartamentos;
    }

    public DTODepartamento getDepartamentoById(int id) throws SQLException {
        DTODepartamento DTODepartamento = null;
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM departamentos WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DTODepartamento = new DTODepartamento(
                    rs.getInt("id"),
                    rs.getString("nombre")
            );
        }
        return DTODepartamento;
    }

    public int addDepartamento(DTODepartamento DTODepartamento) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO departamentos (nombre) VALUES (?)");
        ps.setString(1, DTODepartamento.getNombre());
        return ps.executeUpdate();
    }

    public int updateDepartamento(DTODepartamento DTODepartamento) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE departamentos SET nombre = ? WHERE id = ?");
        ps.setString(1, DTODepartamento.getNombre());
        ps.setInt(2, DTODepartamento.getId());
        return ps.executeUpdate();
    }

    public int deleteDepartamentoById(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM departamentos WHERE id = ?");
        ps.setInt(1, id);
        return ps.executeUpdate();
    }

    public int deleteAllDepartamentos() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM departamentos");
        return ps.executeUpdate();
    }
}