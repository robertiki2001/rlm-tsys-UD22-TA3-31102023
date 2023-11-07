package Controllers;

import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;

import DB_Connection.ConnectionDB;
import Model.Cientifico;
import Views.CientificoViewUpdate;

public class CientificoUpdateController implements ActionListener {
    private CientificoViewUpdate updateView;
    private Cientifico cientifico;

    public CientificoUpdateController(CientificoViewUpdate updateView, Cientifico cientifico) {
        this.updateView = updateView;
        this.cientifico = cientifico;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateView.getButtonActualizarCientifico()) {
            String nomApels = updateView.getTextFieldNomApelsUpdate().getText();
            String dni = updateView.getTextFieldDniUpdate().getText();

            
            if (dni.isEmpty() || nomApels.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nombre y Dni son campos obligatorios.");
                return;
            }
            
            // Actualiza los campos del cliente con los valores modificados           
            cientifico.setNomApels(nomApels);    
            cientifico.setDni(dni);

            // Llamamos a un método para actualizar el cliente en la base de datos
            if (actualizarCientifico(cientifico)) {
                // Éxito al actualizar, muestra un mensaje
                JOptionPane.showMessageDialog(null, "Cientifico actualizado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el cientifico");
            }
        }
    }

    private boolean actualizarCientifico(Cientifico cientifico) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionDB.getConnection();
            String dni = updateView.getTextFieldDniUpdate().getText(); // Obtén el nuevo DNI

         // Comprobar si el nuevo dni ya existe en la tabla
         String checkSql = "SELECT COUNT(*) FROM cientificos WHERE dni = ?";
         PreparedStatement checkStatement = connection.prepareStatement(checkSql);
         checkStatement.setString(1, dni); // Utiliza el nuevo DNI
         ResultSet resultSet = checkStatement.executeQuery();

         resultSet.next();
         int existingRows = resultSet.getInt(1);

         // Si el nuevo dni ya existe, eliminar la fila existente
         if (existingRows > 0) {
             String deleteSql = "DELETE FROM cientificos WHERE dni = ?";
             PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
             deleteStatement.setString(1, dni); // Utiliza el nuevo DNI
             deleteStatement.executeUpdate();
         }

         // Ahora puedes proceder con la actualización
         String updateSql = "UPDATE cientificos SET nomApels = ? WHERE dni = ?";
         statement = connection.prepareStatement(updateSql);
         statement.setString(1, cientifico.getNomApels());
         statement.setString(2, dni); // Utiliza el nuevo DNI

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

}
