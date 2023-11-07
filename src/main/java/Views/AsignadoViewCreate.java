package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import Controllers.AsignadoCreateController;
import Controllers.AsignadoReadController;
import DB_Connection.FunctionsSQL;
import Model.Asignado_a;

public class AsignadoViewCreate extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelCientificoId;
	private JLabel labelProyectoID;
	private JLabel titulo;
	private JButton buttonCrearAsignado;
	private JButton buttonListarAsignado;
	private JTable table;
	private JComboBox<String> comboBoxCientificoId;
	private JComboBox<String> comboBoxProyectoId;

    public AsignadoViewCreate() {
    	setTitle("Vista crear video");
		setBounds(600,450,400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		comboBoxCientificoId = new JComboBox<String>();
		comboBoxCientificoId.setBounds(116, 150, 96, 22);
		contentPane.add(comboBoxCientificoId);
		
		comboBoxProyectoId = new JComboBox<String>();
		comboBoxProyectoId.setBounds(116, 199, 96, 22);
		contentPane.add(comboBoxProyectoId);
		
		labelCientificoId = new JLabel("Cientifico:");
		labelCientificoId.setBounds(48, 151, 200, 20);
		contentPane.add(labelCientificoId);
		
		labelProyectoID = new JLabel("Proyecto:");
		labelProyectoID.setBounds(48, 200, 200, 20);
		contentPane.add(labelProyectoID);				
			
		titulo = new JLabel("REGISTO DE UN NUEVO ASIGNADO");
		titulo.setBounds(80, 82, 294, 30);
		contentPane.add(titulo);
		
		buttonCrearAsignado = new JButton("Crear asignado");
		buttonCrearAsignado.setBounds(118, 341, 149, 43);
		contentPane.add(buttonCrearAsignado);
		
		buttonListarAsignado = new JButton("Asignado");
		buttonListarAsignado.setBounds(214, 11, 111, 30);
		buttonListarAsignado.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				// Crear una instancia del controlador ClienteReadController
		    	Asignado_a asignado_a = new Asignado_a();
		    	AsignadoViewRead asignadoViewRead = new AsignadoViewRead();
		    	AsignadoReadController readController = new AsignadoReadController(asignado_a, asignadoViewRead);
			    readController.iniciarVistaAsignado();
			    asignadoViewRead.setVisible(true);
		        dispose();
		    }
		});
		contentPane.add(buttonListarAsignado);
		
		table = new JTable();
		table.setBounds(29, 45, 596, 259);
		contentPane.add(table);
		
		buttonCrearAsignado.addActionListener(new AsignadoCreateController(this));
		
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


    public JButton getButtonCrearAsignado() {
        return buttonCrearAsignado;
    }
    
    
    public JComboBox<String> getComboBoxCientificoId() {
        return comboBoxCientificoId;
    }
    
    public JComboBox<String> getComboBoxProyectoId() {
        return comboBoxProyectoId;
    }
    
    
    
    
    
}