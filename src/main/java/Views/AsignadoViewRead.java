package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import Controllers.AsignadoController;
import Model.AsignadoTable;
import Model.Asignado;

public class AsignadoViewRead extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel label;
	private JButton buttonCrearAsignado;
	private JButton buttonUpdateAsignado;
	private JButton buttonDeleteAsignado;
	private JTable table;
	private JButton buttonProyectos;
	private JButton buttonCientificos;
	private AsignadoController asignadoController;
	private List<Asignado> asignados;
	private AsignadoTable model;

	public AsignadoViewRead() {

		setTitle("Vista listar Asignados");
		setBounds(500, 500, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		label = new JLabel("Lista de Asignados");
		buttonUpdateAsignado = new JButton("Editar");
		buttonDeleteAsignado = new JButton("Eliminar");
		buttonCrearAsignado = new JButton("Crear");
		buttonProyectos = new JButton("Proyectos");
		buttonCientificos = new JButton("Científicos");
		table = new JTable();
		asignadoController = new AsignadoController();
		actualizarAsignados();
		
		label.setBounds(41, 16, 200, 20);
		buttonUpdateAsignado.setBounds(63, 350, 94, 31);
		buttonDeleteAsignado.setBounds(189, 350, 108, 31);
		buttonCrearAsignado.setBounds(337, 350, 108, 31);
		buttonProyectos.setBounds(266, 11, 108, 31);
		buttonCientificos.setBounds(414, 11, 108, 31);
		table.setBounds(29, 45, 596, 259);
		
		contentPane.add(label);
		contentPane.add(buttonUpdateAsignado);
		contentPane.add(buttonDeleteAsignado);
		contentPane.add(buttonCrearAsignado);
		contentPane.add(buttonProyectos);
		contentPane.add(buttonCientificos);
		contentPane.add(table);


		buttonCrearAsignado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AsignadoViewCreate createView = new AsignadoViewCreate();
				createView.setVisible(true); 
				dispose();
			}
		});
		buttonUpdateAsignado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					AsignadoTable model = (AsignadoTable) table.getModel();
					Asignado asignadoSeleccionado = model.getAsignado(selectedRow);

					AsignadoViewUpdate updateView = new AsignadoViewUpdate(asignadoSeleccionado);
					updateView.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un asignado para actualizar");
				}
			}
		});
		
		buttonDeleteAsignado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					model = (AsignadoTable) table.getModel();
					Asignado asignadoSeleccionado = model.getAsignado(selectedRow);

					int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este asignado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

					if (option == JOptionPane.YES_OPTION) {
						if (asignadoController.eliminarAsignado(asignadoSeleccionado)) {
							JOptionPane.showMessageDialog(null, "Asignado eliminado con éxito");
							model.removeRowAt(selectedRow);
							table.setModel(model);
							table.repaint();
						} else {
							JOptionPane.showMessageDialog(null, "Error al eliminar el asignado");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un asignado para eliminar");
				}
			}
		});
	
		buttonProyectos.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	ProyectoViewRead proyectoViewRead = new ProyectoViewRead();
			    proyectoViewRead.setVisible(true);
		        dispose();
		    }
		});
		
		buttonCientificos.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	CientificoViewRead cientificoViewRead = new CientificoViewRead();
		    	cientificoViewRead.setVisible(true);
		        dispose();
		    }
		});
		
	}
	
	public void actualizarAsignados()
	{
		asignados = asignadoController.obtenerAsignados();
		
	    if (asignados != null && !asignados.isEmpty()) {
	    	model = new AsignadoTable(asignados);
	        table.setModel(model);
	    }
	}
}