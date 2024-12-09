package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOEmpleado {
    Connection conn;
    DTOEmpleado DTOEmpleado;
    ArrayList<DTOEmpleado> listaDTOEmpleados;

    public DAOEmpleado() {
        conn = DB.getConexion();
        listaDTOEmpleados = new ArrayList<>();
    }
    public ArrayList<DTOEmpleado> getAllEmpleados() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM empleados");
        ResultSet rs = ps.executeQuery();
        listaDTOEmpleados.clear();

        while (rs.next()) {
            DTOEmpleado DTOEmpleado = new DTOEmpleado(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("edad"),
                    rs.getInt("dpto_id")
            );
            listaDTOEmpleados.add(DTOEmpleado);
        }
        return listaDTOEmpleados;
    }

    public DTOEmpleado getEmpleadoById(int id) throws SQLException {
        DTOEmpleado DTOEmpleado = null;
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM empleados WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DTOEmpleado = new DTOEmpleado(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("edad"),
                    rs.getInt("dpto_id")
            );
        }
        return DTOEmpleado;
    }

    public ArrayList<DTOEmpleado> getEmpleadosByDepartamento(int dptoId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM empleados WHERE dpto_id = ?");
        ps.setInt(1, dptoId);
        ResultSet rs = ps.executeQuery();
        listaDTOEmpleados.clear();

        while (rs.next()) {
            DTOEmpleado DTOEmpleado = new DTOEmpleado(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("edad"),
                    rs.getInt("dpto_id")
            );
            listaDTOEmpleados.add(DTOEmpleado);
        }
        return listaDTOEmpleados;
    }

    public int addEmpleado(DTOEmpleado DTOEmpleado) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO empleados (nombre, edad, dpto_id) VALUES (?, ?, ?)");
        ps.setString(1, DTOEmpleado.getNombre());
        ps.setInt(2, DTOEmpleado.getEdad());
        ps.setInt(3, DTOEmpleado.getDpto_id());
        return ps.executeUpdate();
    }

    public int updateEmpleado(DTOEmpleado DTOEmpleado) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE empleados SET nombre = ?, edad = ?, dpto_id = ? WHERE id = ?");
        ps.setString(1, DTOEmpleado.getNombre());
        ps.setInt(2, DTOEmpleado.getEdad());
        ps.setInt(3, DTOEmpleado.getDpto_id());
        ps.setInt(4, DTOEmpleado.getId());
        return ps.executeUpdate();
    }

    public int deleteEmpleadoById(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM empleados WHERE id = ?");
        ps.setInt(1, id);
        return ps.executeUpdate();
    }

    public int deleteEmpleadosByDepartamento(int dptoId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM empleados WHERE dpto_id = ?");
        ps.setInt(1, dptoId);
        return ps.executeUpdate();
    }

    public int deleteAllEmpleados() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM empleados");
        return ps.executeUpdate();
    }
}