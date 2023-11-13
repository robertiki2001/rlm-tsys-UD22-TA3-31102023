package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controllers.ProyectoController;
import Model.Proyecto;

public class ProyectoViewUpdate extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelId;
	private JLabel labelNombre;
	private JLabel labelHoras;
	private JLabel tituloUpdate;
	private JTextField textFieldIdUpdate;
	private JTextField textFieldNombreUpdate;
	private JTextField textFieldHorasUpdate;
	private JButton buttonActualizarProyecto;
	private JButton buttonProyecto;
	private Proyecto proyecto;
	private ProyectoViewRead proyectoViewRead;
	private ProyectoController proyectoController;

    public ProyectoViewUpdate(Proyecto proyectoSeleccionado) {
    	
    	setTitle("Vista editar Proyecto");
		setBounds(600,450,400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		labelId = new JLabel("ID:");
		labelNombre = new JLabel("Nombre:");
		labelHoras = new JLabel("Horas:");
		textFieldIdUpdate = new JTextField();
		textFieldNombreUpdate = new JTextField();
		textFieldHorasUpdate = new JTextField();
		tituloUpdate = new JLabel("ACTUALIZAR Proyecto");
		buttonActualizarProyecto = new JButton("Actualizar Proyecto");
		buttonProyecto = new JButton("Proyectos");
		proyectoViewRead = new ProyectoViewRead();
		proyectoController = new ProyectoController();
		proyecto = proyectoSeleccionado;

		labelId.setBounds(48,103,200,20);
		labelNombre.setBounds(48, 151, 200, 20);
		labelHoras.setBounds(48, 197, 200, 20);
		textFieldIdUpdate.setColumns(10);	
		textFieldIdUpdate.setBounds(104, 103, 176, 20);
		textFieldIdUpdate.setText(proyecto.getId());
		textFieldIdUpdate.setEditable(false);
		textFieldNombreUpdate.setColumns(10);
		textFieldNombreUpdate.setBounds(104, 151, 176, 20);
		textFieldNombreUpdate.setText(proyecto.getNombre());
		textFieldHorasUpdate.setColumns(10);
		textFieldHorasUpdate.setBounds(104, 197, 176, 20);
		textFieldHorasUpdate.setText(""+proyecto.getHoras());
		tituloUpdate.setBounds(100, 43, 294, 30);
		buttonActualizarProyecto.setBounds(81, 265, 217, 23);
		buttonProyecto.setBounds(263, 11, 111, 30);
		
		contentPane.add(labelId);
		contentPane.add(labelNombre);
		contentPane.add(labelHoras);
		contentPane.add(textFieldIdUpdate);
		contentPane.add(textFieldNombreUpdate);
		contentPane.add(textFieldHorasUpdate);
		contentPane.add(tituloUpdate);
		contentPane.add(buttonActualizarProyecto);
		contentPane.add(buttonProyecto);

		buttonActualizarProyecto.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String nombre = textFieldNombreUpdate.getText();
	            
	            if (nombre.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Nombre es un campo obligatorio.");
	                return;
	            }
	            
	            int horas;
	            try {
	            	horas = Integer.parseInt(textFieldHorasUpdate.getText());;
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null, "Horas debe ser un número válido.");
	                return;
	            }   
	            proyecto.setNombre(nombre);
	            proyecto.setHoras(horas);

	            if (proyectoController.actualizarProyecto(proyecto)) {
	                JOptionPane.showMessageDialog(null, "Proyecto actualizado con éxito");
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al actualizar el proyecto");
	            }
		    	
	            proyectoViewRead.actualizarProyectos();
			    proyectoViewRead.setVisible(true);
		        dispose();
		    }
		});
		
    }    
}