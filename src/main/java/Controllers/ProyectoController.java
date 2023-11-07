package Controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DB_Connection.ConnectionDB;
import Model.Proyecto;

public class ProyectoController{

    // Método para insertar un cliente en la base de datos
    public boolean insertarProyecto(Proyecto proyecto) {
        Connection connection = ConnectionDB.getConnection();
        String sql = "INSERT INTO proyecto (id, nombre, horas) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, proyecto.getId());
            statement.setString(2, proyecto.getNombre());
            statement.setInt(3, proyecto.getHoras());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<Proyecto> obtenerProyectos() {
	    List<Proyecto> proyectos = new ArrayList<>();
	    
	    // Realiza una consulta SQL para obtener todos los clientes
	    String sql = "SELECT * FROM proyecto";
	    
	    try {
	    	Connection conexion = null;
	        // Obtener la conexión utilizando la clase ConnectionDB
	        conexion = ConnectionDB.getConnection();
	        Statement stmt = conexion.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        while (rs.next()) {
	        	String id = rs.getString("id");
	            String nombre = rs.getString("nombre");
	            int horas = rs.getInt("horas");
	            
	            Proyecto proyecto = new Proyecto(id, nombre, horas);
	            proyectos.add(proyecto);
	        }
	        
	        rs.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return proyectos;
	}
    
    public boolean actualizarProyecto(Proyecto proyecto) {
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionDB.getConnection();
            String sql = "UPDATE proyecto SET nombre = ?, horas = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, proyecto.getNombre());
            statement.setInt(2, proyecto.getHoras());
            statement.setString(3, proyecto.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false; 
    }
    
    public boolean eliminarProyecto(Proyecto proyecto) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionDB.getConnection();
            String sql = "DELETE FROM proyecto WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, proyecto.getId());

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                return true; // Éxito al eliminar
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
