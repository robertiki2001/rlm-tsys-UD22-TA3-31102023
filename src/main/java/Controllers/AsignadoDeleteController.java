package Controllers;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;

import DB_Connection.ConnectionDB;
import Model.Asignado_a;

public class AsignadoDeleteController{

    // Método para eliminar un video de la base de datos
    public boolean eliminarAsignado(Asignado_a asignado_a) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionDB.getConnection();
            String sql = "DELETE FROM asignado_a WHERE proyecto = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, asignado_a.getProyecto());

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
