package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controllers.AsignadoController;
import Model.Asignado;

public class AsignadoViewUpdate extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelCientificoId;
	private JLabel labelProyectoID;
	private JLabel titulo;
	private JButton buttonActualizarAsignado;
	private JComboBox<String> comboBoxCientificoId;
	private JComboBox<String> comboBoxProyectoId;
	private Asignado asignado;
	private AsignadoViewRead asignadoViewRead;
	private AsignadoController asignadoController;
	private JTextField textFieldIdUpdate;
	private JLabel labelId;
	
    public AsignadoViewUpdate(Asignado asignadoSeleccionado) {
    	
    	setTitle("Vista crear asignado");
		setBounds(600,450,400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		comboBoxCientificoId = new JComboBox<String>();
		comboBoxProyectoId = new JComboBox<String>();
		labelId = new JLabel("ID:");
		labelCientificoId = new JLabel("Cientifico:");
		labelProyectoID = new JLabel("Proyecto:");
		titulo = new JLabel("ACTUALIZAR ASIGNADO");
		asignadoViewRead = new AsignadoViewRead();
		asignadoController = new AsignadoController();
		asignado = asignadoSeleccionado;
		buttonActualizarAsignado = new JButton("Actualizar Asignado");
		textFieldIdUpdate = new JTextField();
		
		comboBoxCientificoId.setBounds(116, 166, 96, 22);
		comboBoxProyectoId.setBounds(116, 213, 96, 22);
		labelCientificoId.setBounds(48, 167, 200, 20);
		labelProyectoID.setBounds(48, 214, 200, 20);
		titulo.setBounds(103, 58, 294, 30);
		buttonActualizarAsignado.setBounds(68, 275, 217, 30);
		labelId.setBounds(58, 110, 200, 20);
		textFieldIdUpdate.setBounds(116, 110, 96, 20);
		textFieldIdUpdate.setColumns(10);
		textFieldIdUpdate.setEditable(false);
		
		contentPane.add(comboBoxCientificoId);
		contentPane.add(comboBoxProyectoId);
		contentPane.add(labelCientificoId);		
		contentPane.add(labelProyectoID);						
		contentPane.add(titulo);
		contentPane.add(buttonActualizarAsignado);
		contentPane.add(labelId);
		contentPane.add(textFieldIdUpdate);
		
		
		buttonActualizarAsignado.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String cientifico = (String) comboBoxCientificoId.getSelectedItem();
		    	String proyecto = (String) comboBoxProyectoId.getSelectedItem();
	                  
	            asignado.setCientifico(cientifico);
	            asignado.setProyecto(proyecto);

	            if (asignadoController.actualizarAsignado(asignado)) {
	                JOptionPane.showMessageDialog(null, "Asignado actualizado con éxito");
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al actualizar el asignado");
	            }
		    	
	            asignadoViewRead.actualizarAsignados();
	            asignadoViewRead.setVisible(true);
		        dispose();
		    }
		});
    }
    
}
