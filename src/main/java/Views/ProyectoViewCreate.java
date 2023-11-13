package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controllers.ProyectoController;
import Model.Proyecto;

public class ProyectoViewCreate extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelId;
	private JLabel labelNombre;
	private JLabel labelHoras;
	private JLabel titulo;
	private JTextField textFieldId;
	private JTextField textFieldNombre;
	private JTextField textFieldHoras;
	private JButton buttonCrearProyecto;
	private JButton buttonProyecto;
	private JTable table;
	private JTextField textField;
	private Proyecto proyecto;
	private ProyectoViewRead proyectoViewRead;
	private ProyectoController proyectoController;

    public ProyectoViewCreate() {
    	setTitle("Vista crear cliente");
		setBounds(600,450,400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		buttonCrearProyecto = new JButton("Crear Proyecto");
		labelId = new JLabel("ID:");
		labelNombre = new JLabel("Nombre:");
		labelHoras = new JLabel("Horas:");
		textFieldId = new JTextField();
		textFieldNombre = new JTextField();
		textFieldHoras = new JTextField();
		titulo = new JLabel("REGISTO DE UN NUEVO PROYECTO");
		buttonProyecto = new JButton("Proyectos");
		table = new JTable();
		textField = new JTextField();
		proyectoViewRead = new ProyectoViewRead();
		proyectoController = new ProyectoController();
		
		buttonCrearProyecto.setBounds(107, 261, 217, 23);
		labelId.setBounds(63,103,200,20);
		labelNombre.setBounds(63, 151, 200, 20);
		labelHoras.setBounds(63, 203, 200, 20);
		textFieldId.setColumns(10);
		textFieldId.setBounds(126, 103, 176, 20);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(126, 151, 176, 20);
		textFieldHoras.setColumns(10);
		textFieldHoras.setBounds(126, 203, 176, 20);
		titulo.setBounds(118, 62, 294, 30);
		buttonProyecto.setBounds(263, 11, 111, 30);
		table.setBounds(29, 45, 596, 259);
		textField.setColumns(10);
		textField.setBounds(126, 203, 176, 20);
		
		contentPane.add(buttonCrearProyecto);
		contentPane.add(labelId);
		contentPane.add(labelNombre);
		contentPane.add(labelHoras);
		contentPane.add(textFieldId);
		contentPane.add(textFieldNombre);
		contentPane.add(textFieldHoras);
		contentPane.add(titulo);
		contentPane.add(buttonProyecto);
		contentPane.add(table);
		contentPane.add(textField);
		
		buttonProyecto.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			    proyectoViewRead.setVisible(true);
		        dispose();
		    }
		});
		
		buttonCrearProyecto.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String id = textFieldId.getText();
	            String nombre = textFieldNombre.getText();
	            
	            if (id.isEmpty() || nombre.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Nombre y Id son campos obligatorios.");
	                return;
	            }
	            
	            int horas;
	            try {
	            	horas = Integer.parseInt(textFieldHoras.getText());;
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null, "Horas debe ser un número válido.");
	                return;
	            }
	            
	            proyecto = new Proyecto(id, nombre, horas);
	         
	            if (proyectoController.insertarProyecto(proyecto)) {
	                JOptionPane.showMessageDialog(null, "Proyecto insertado con éxito");
	                limpiarFormulario();
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al insertar el Proyecto");
	            }
	            proyectoViewRead.actualizarProyectos();
		    }
		});
    }

    private void limpiarFormulario() {
        textFieldId.setText("");
        textFieldNombre.setText("");
        textFieldHoras.setText("");
    }
}