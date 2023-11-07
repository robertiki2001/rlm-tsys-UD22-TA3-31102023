package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;

import DB_Connection.ConnectionDB;
import Model.Cientifico;
import Views.CientificoViewCreate;

public class CientificoCreateController implements ActionListener {
    private CientificoViewCreate createView;

    public CientificoCreateController(CientificoViewCreate createView) {
        this.createView = createView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createView.getButtonCrearCientifico()) {
            // Obtiene los valores del formulario          
            String nomApels = createView.getTextFieldNomApels().getText();
            String dni = createView.getTextFieldDni().getText();
            
            if (dni.isEmpty() || nomApels.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nombre y Dni son campos obligatorios.");
                return;
            }
            
            // Crea un nuevo objeto Cliente con los valores
            Cientifico cientifico = new Cientifico(nomApels, dni);

            // Inserta el cliente en la base de datos
            if (insertarCientifico(cientifico)) {
                // Éxito al insertar, muestra un mensaje
                JOptionPane.showMessageDialog(null, "Científico insertado con éxito");
                // Limpia el formulario
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(null, "Error al insertar el científico");
            }
        }
    }

    // Método para insertar un cliente en la base de datos
    public boolean insertarCientifico(Cientifico cientifico) {
        Connection connection = ConnectionDB.getConnection();
        String sql = "INSERT INTO cientificos (dni, nomApels) VALUES (?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cientifico.getDni());
            statement.setString(2, cientifico.getNomApels());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void limpiarFormulario() {
        createView.getTextFieldDni().setText("");
        createView.getTextFieldNomApels().setText("");
    }
}
