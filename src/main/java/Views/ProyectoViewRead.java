package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import Controllers.AsignadoReadController;
import Controllers.CientificoReadController;
import Controllers.ProyectoController;
import Model.Asignado_a;
import Model.Cientifico;
import Model.Proyecto;
import Model.ProyectoTable;

public class ProyectoViewRead extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JPanel contentPane;
	private JLabel label;
	private JButton buttonCrearProyecto;
	private JButton buttonUpdateProyecto;
	private JButton buttonDeleteProyecto;
	private JButton buttonListarAsignado;
	private JButton buttonListarCientificos;
	private Cientifico cientifico;
	private CientificoViewRead cientificoViewRead;
	private CientificoReadController cientificoReadController;
	private Asignado_a asignado_a;
	private AsignadoViewRead asignadoViewRead;
	private AsignadoReadController asignadoReadController;
	private ProyectoController proyectoController;
	private List<Proyecto> proyectos;
	private ProyectoTable model;
	
	public ProyectoViewRead() {
		
		setTitle("Vista listar científicos");
		setBounds(500, 500, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		label = new JLabel("Lista de proyectos");
		buttonUpdateProyecto = new JButton("Editar");
		buttonDeleteProyecto = new JButton("Eliminar");
		buttonCrearProyecto = new JButton("Crear proyecto");
		buttonListarAsignado = new JButton("Asignado");
		buttonListarCientificos = new JButton("Cientificos");
		table = new JTable();
		cientifico = new Cientifico();
		cientificoViewRead = new CientificoViewRead();
		cientificoReadController = new CientificoReadController(cientifico, cientificoViewRead);
		asignado_a = new Asignado_a();
		asignadoViewRead = new AsignadoViewRead();
		asignadoReadController = new AsignadoReadController(asignado_a, asignadoViewRead);
		proyectoController = new ProyectoController();
		actualizarProyectos();

		table.setBounds(29, 45, 596, 259);
		label.setBounds(54, 16, 200, 20);
		buttonUpdateProyecto.setBounds(33, 350, 108, 31);
		buttonDeleteProyecto.setBounds(162, 350, 108, 31);
		buttonCrearProyecto.setBounds(299, 350, 121, 31);
		buttonListarAsignado.setBounds(243, 11, 111, 30);
		buttonListarCientificos.setBounds(390, 11, 111, 30);
		
		contentPane.add(label);
		contentPane.add(buttonUpdateProyecto);
		contentPane.add(buttonDeleteProyecto);
		contentPane.add(buttonCrearProyecto);
		contentPane.add(buttonListarAsignado);
		contentPane.add(buttonListarCientificos);
		contentPane.add(table);
		
		buttonCrearProyecto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear una instancia de la vista ClienteViewCreate
				ProyectoViewCreate createView = new ProyectoViewCreate();
				createView.setVisible(true); // Mostrar la vista
				dispose();
			}
		});
		buttonUpdateProyecto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					ProyectoTable model = (ProyectoTable) table.getModel();
					Proyecto proyectoSeleccionado = model.getProyecto(selectedRow);

					ProyectoViewUpdate updateView = new ProyectoViewUpdate(proyectoSeleccionado);
					updateView.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un cientifico para actualizar");
				}
			}
		});
		buttonDeleteProyecto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					model = (ProyectoTable) table.getModel();
					Proyecto proyectoSeleccionado = model.getProyecto(selectedRow);

					int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este cliente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

					if (option == JOptionPane.YES_OPTION) {
						// Llama al método para eliminar el cliente
						if (proyectoController.eliminarProyecto(proyectoSeleccionado)) {
							JOptionPane.showMessageDialog(null, "Proyecto eliminado con éxito");
							model.removeRowAt(selectedRow);
							table.setModel(model);
							table.repaint();
						} else {
							JOptionPane.showMessageDialog(null, "Error al eliminar el Proyecto");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un Proyecto para eliminar");
				}
			}
		});
		buttonListarAsignado.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	asignadoReadController.iniciarVistaAsignado();
			    asignadoViewRead.setVisible(true);
		        dispose();
		    }
		});
		buttonListarCientificos.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	cientificoReadController.iniciarVista();
			    cientificoViewRead.setVisible(true);
		        dispose();
		    }
		});
	}
	
	public void actualizarProyectos()
	{
		proyectos = proyectoController.obtenerProyectos();
		
	    if (proyectos != null && !proyectos.isEmpty()) {
	    	model = new ProyectoTable(proyectos);
	        table.setModel(model);
	    }
	}
}