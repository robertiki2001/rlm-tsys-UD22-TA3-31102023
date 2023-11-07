package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import DB_Connection.ConnectionDB;
import Model.Asignado_a;
import Views.AsignadoViewCreate;

public class AsignadoCreateController implements ActionListener {
    private AsignadoViewCreate createView;

    public AsignadoCreateController(AsignadoViewCreate createView) {
        this.createView = createView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createView.getButtonCrearAsignado()) {
            // Obtiene los valores del formulario          
            String cientificoId = (String) createView.getComboBoxCientificoId().getSelectedItem();        
            String proyectoId = (String) createView.getComboBoxProyectoId().getSelectedItem();

            if (cientificoId.isEmpty() || proyectoId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El cientifico y el proyecto son campos obligatorios");
                return;
            }
            // Crea un nuevo objeto Cliente con los valores
            Asignado_a asignado_a = new Asignado_a(cientificoId, proyectoId);

            // Inserta el cliente en la base de datos
            if (insertarAsignado(asignado_a)) {
                // Éxito al insertar, muestra un mensaje
                JOptionPane.showMessageDialog(null, "Asignado con éxito");
                // Limpia el formulario
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(null, "Error al asignar el proyecto al cientifico");
            }
        }
    }

    // Método para insertar un cliente en la base de datos
    public boolean insertarAsignado(Asignado_a asignado_a) {
        Connection connection = ConnectionDB.getConnection();
        String sql = "INSERT INTO asignado_a (cientifico, proyecto) VALUES (?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, asignado_a.getCientifico());
            statement.setString(2, asignado_a.getProyecto());
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
        createView.getComboBoxCientificoId().setSelectedItem("");
        createView.getComboBoxProyectoId().setSelectedItem("");
    }
}
