package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controllers.AsignadoReadController;
import Controllers.CientificoDeleteController;
import Controllers.CientificoUpdateController;
import Model.Asignado_a;
import Model.Cientifico;
import Model.CientificoTable;
import Model.Proyecto;

public class CientificoViewRead extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel label;
	private JButton buttonCrearCientifico;
	private JButton buttonUpdateCientifico;
	private JButton buttonDeleteCientifico;
	private JTable table;
	private JButton buttonListarProyectos;
	private JButton buttonListarAsignado;

	public CientificoViewRead() {

		setTitle("Vista listar científicos");
		setBounds(500, 500, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		label = new JLabel("Lista de científicos");
		label.setBounds(50, 16, 200, 20);
		contentPane.add(label);

		buttonUpdateCientifico = new JButton("Editar");
		buttonUpdateCientifico.setBounds(29, 350, 112, 31);
		contentPane.add(buttonUpdateCientifico);

		buttonDeleteCientifico = new JButton("Eliminar");
		buttonDeleteCientifico.setBounds(162, 350, 108, 31);
		contentPane.add(buttonDeleteCientifico);

		buttonCrearCientifico = new JButton("Crear científico");
		buttonCrearCientifico.setBounds(299, 350, 121, 31);
		buttonCrearCientifico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear una instancia de la vista ClienteViewCreate
				CientificoViewCreate createView = new CientificoViewCreate();
				createView.setVisible(true); // Mostrar la vista
				dispose();
			}
		});
		contentPane.add(buttonCrearCientifico);
		
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
		
		buttonDeleteCientifico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					CientificoTable model = (CientificoTable) table.getModel();
					Cientifico cientificoSeleccionado = model.getCientifico(selectedRow);

					// Crea una instancia del controlador ClienteDeleteController
					CientificoDeleteController deleteController = new CientificoDeleteController();

					int option = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que deseas eliminar este cliente?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (option == JOptionPane.YES_OPTION) {
						// Llama al método para eliminar el cliente
						if (deleteController.eliminarCientifico(cientificoSeleccionado)) {
							JOptionPane.showMessageDialog(null, "Cientifico eliminado con éxito");
							model.removeRowAt(selectedRow);
							table.setModel(model);
							table.repaint();
						} else {
							JOptionPane.showMessageDialog(null, "Error al eliminar el Cientifico");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un Cientifico para eliminar");
				}
			}
		});
		
		buttonListarProyectos = new JButton("Proyectos");
		buttonListarProyectos.setBounds(393, 11, 111, 30);
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
		
	}

	public JTable getTabla() {
		return table;
	}

	public JButton getButtonDeleteCliente() {
		return buttonDeleteCientifico;
	}
}