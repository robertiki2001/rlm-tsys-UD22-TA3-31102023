package Controllers;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;

import DB_Connection.ConnectionDB;
import Model.Cientifico;

public class CientificoDeleteController{

    // Método para eliminar un cliente de la base de datos
    public boolean eliminarCientifico(Cientifico cientifico) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionDB.getConnection();
            String sql = "DELETE FROM cientificos WHERE DNI = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, cientifico.getDni());

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
