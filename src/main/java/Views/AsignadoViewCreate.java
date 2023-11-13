package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import Controllers.AsignadoController;
import DB_Connection.FunctionsSQL;
import Model.Asignado;

public class AsignadoViewCreate extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelCientificoId;
	private JLabel labelProyectoID;
	private JLabel titulo;
	private JButton buttonCrearAsignado;
	private JButton buttonAsignado;
	private JTable table;
	private JTextField textFieldId;
	private JComboBox<String> comboBoxCientificoId;
	private JComboBox<String> comboBoxProyectoId;

	private Asignado asignado;
	private AsignadoViewRead asignadoViewRead;
	private AsignadoController asignadoController;

    public AsignadoViewCreate() {
    	setTitle("Vista crear video");
		setBounds(600,450,400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		comboBoxCientificoId = new JComboBox<String>();
		comboBoxProyectoId = new JComboBox<String>();
		labelCientificoId = new JLabel("Cientifico:");
		labelProyectoID = new JLabel("Proyecto:");
		titulo = new JLabel("REGISTO DE UN NUEVO ASIGNADO");
		buttonCrearAsignado = new JButton("Crear asignado");
		table = new JTable();
		asignadoViewRead = new AsignadoViewRead();
		asignadoController = new AsignadoController();
		buttonAsignado = new JButton("Asignado");
		
		comboBoxCientificoId.setBounds(116, 150, 96, 22);
		comboBoxProyectoId.setBounds(116, 199, 96, 22);
		labelCientificoId.setBounds(48, 151, 200, 20);
		labelProyectoID.setBounds(48, 200, 200, 20);
		titulo.setBounds(80, 82, 294, 30);
		buttonCrearAsignado.setBounds(118, 341, 149, 43);
		table.setBounds(29, 45, 596, 259);
		buttonAsignado.setBounds(236, 11, 111, 30);
		
		contentPane.add(comboBoxCientificoId);
		contentPane.add(comboBoxProyectoId);
		contentPane.add(labelCientificoId);
		contentPane.add(labelProyectoID);		
		contentPane.add(titulo);
		contentPane.add(buttonCrearAsignado);
		contentPane.add(table);
		contentPane.add(buttonAsignado);

		
		buttonAsignado.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			    asignadoViewRead.setVisible(true);
		        dispose();
		    }
		});
		
		buttonCrearAsignado.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	int id = Integer.parseInt(textFieldId.getText());
		    	String cientifico = (String) comboBoxCientificoId.getSelectedItem();
		    	String proyecto = (String) comboBoxProyectoId.getSelectedItem();
		    	
	            asignado = new Asignado(id, cientifico, proyecto);

	            if (asignadoController.insertarAsignado(asignado)) {
	                JOptionPane.showMessageDialog(null, "Asignado insertado con éxito");
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al insertar el Asignado");
	            }
	            asignadoViewRead.actualizarAsignados();
		    }
		});
		
		cargarCientificosIds(); // Metodo para cargar los IDs de los cientificos al iniciar la vista
		cargarProyectosIds();// Metodo para cargar los IDs de los proyectos al iniciar la vista
    }
    
    public void cargarCientificosIds() {
    	comboBoxCientificoId.removeAllItems();

        ArrayList<String> cientificoIds = FunctionsSQL.obtenerIdsCientificos();

        for (String dni : cientificoIds) {
        	comboBoxCientificoId.addItem(dni);
        }
    }
    
    public void cargarProyectosIds() {
    	comboBoxProyectoId.removeAllItems();

        ArrayList<String> proyectoIds = FunctionsSQL.obtenerIdsProyectos();

        for (String id : proyectoIds) {
        	comboBoxProyectoId.addItem(id);
        }
    }
    

}