package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controllers.AsignadoDeleteController;
import Controllers.CientificoReadController;
import Model.AsignadoTable;
import Model.Asignado_a;
import Model.Cientifico;
import Model.Proyecto;

public class AsignadoViewRead extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel label;
	private JButton buttonCrearAsignado;
	private JButton buttonUpdateAsignado;
	private JButton buttonDeleteAsignado;
	private JTable table;
	private JButton buttonListarProyectos;
	private JButton buttonListarCientificos;

	public AsignadoViewRead() {

		setTitle("Vista listar Asignados");
		setBounds(500, 500, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		label = new JLabel("Lista de Asignados");
		label.setBounds(41, 16, 200, 20);
		contentPane.add(label);

		buttonUpdateAsignado = new JButton("Editar");
		buttonUpdateAsignado.setBounds(33, 355, 108, 20);
		contentPane.add(buttonUpdateAsignado);

		buttonDeleteAsignado = new JButton("Eliminar");
		buttonDeleteAsignado.setBounds(162, 350, 108, 31);
		contentPane.add(buttonDeleteAsignado);

		buttonCrearAsignado = new JButton("Crear Asignado");
		buttonCrearAsignado.setBounds(299, 350, 121, 31);
		buttonCrearAsignado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear una instancia de la vista ClienteViewCreate
				AsignadoViewCreate createView = new AsignadoViewCreate();
				createView.setVisible(true); // Mostrar la vista
				dispose();
			}
		});
		contentPane.add(buttonCrearAsignado);
		/*
		buttonUpdateCientifico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					CientificoTable model = (CientificoTable) table.getModel();
					Cientifico cientificoSeleccionado = model.getCientifico(selectedRow);

					CientificoViewUpdate updateView = new CientificoViewUpdate(cientificoSeleccionado);
					updateView.setVisible(true);

					CientificoUpdateController updateController = new CientificoUpdateController(updateView,
							cientificoSeleccionado);
					updateView.getButtonActualizarCientifico().addActionListener(updateController);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un cientifico para actualizar");
				}
			}
		});
		*/
		buttonDeleteAsignado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					AsignadoTable model = (AsignadoTable) table.getModel();
					Asignado_a asignadoSeleccionado = model.getAsignado_a(selectedRow);

					// Crea una instancia del controlador ClienteDeleteController
					AsignadoDeleteController deleteController = new AsignadoDeleteController();

					int option = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que deseas eliminar esta asignación?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (option == JOptionPane.YES_OPTION) {
						// Llama al método para eliminar el cliente
						if (deleteController.eliminarAsignado(asignadoSeleccionado)) {
							JOptionPane.showMessageDialog(null, "Asignación eliminada con éxito");
							model.removeRowAt(selectedRow);
							table.setModel(model);
							table.repaint();
						} else {
							JOptionPane.showMessageDialog(null, "Error al eliminar la asignación");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona una asignación para eliminar");
				}
			}
		});
		
		buttonListarProyectos = new JButton("Proyectos");
		buttonListarProyectos.setBounds(399, 11, 111, 30);
		buttonListarProyectos.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				// Crear una instancia del controlador ClienteReadController
		    	ProyectoViewRead proyectoViewRead = new ProyectoViewRead();
			    proyectoViewRead.setVisible(true);
		        dispose();
		    }
		});
		contentPane.add(buttonListarProyectos);
		
		buttonListarCientificos = new JButton("Cientificos");
		buttonListarCientificos.setBounds(230, 11, 111, 30);
		buttonListarCientificos.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				// Crear una instancia del controlador ClienteReadController
			    Cientifico cientifico = new Cientifico();
			    CientificoViewRead cientificoViewRead = new CientificoViewRead();
			    CientificoReadController readController = new CientificoReadController(cientifico, cientificoViewRead);
			    readController.iniciarVista();
			    cientificoViewRead.setVisible(true);
		        dispose();
		    }
		});
		contentPane.add(buttonListarCientificos);

		table = new JTable();
		table.setBounds(29, 45, 596, 259);
		contentPane.add(table);
		
	}

	public JTable getTabla() {
		return table;
	}

	public JButton getButtonDeleteAsignado() {
		return buttonDeleteAsignado;
	}
}