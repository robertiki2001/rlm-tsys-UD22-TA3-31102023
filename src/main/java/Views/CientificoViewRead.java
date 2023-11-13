package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import Controllers.CientificoController;
import Model.Cientifico;
import Model.CientificoTable;

public class CientificoViewRead extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel label;
	private JButton buttonCrearCientifico;
	private JButton buttonUpdateCientifico;
	private JButton buttonDeleteCientifico;
	private JTable table;
	private CientificoController cientificoController;
	private List<Cientifico> cientificos;
	private CientificoTable model;
	private JButton buttonProyectos;
	private JButton buttonAsignado;

	public CientificoViewRead() {

		setTitle("Vista listar científicos");
		setBounds(500, 500, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		label = new JLabel("Lista de científicos");
		buttonAsignado = new JButton("Asignado");
		buttonProyectos = new JButton("Proyectos");
		buttonCrearCientifico = new JButton("Crear");
		buttonDeleteCientifico = new JButton("Eliminar");
		buttonUpdateCientifico = new JButton("Editar");
		table = new JTable();
		
		label.setBounds(50, 16, 200, 20);
		buttonProyectos.setBounds(393, 11, 111, 30);
		buttonCrearCientifico.setBounds(29, 350, 120, 31);
		table.setBounds(29, 45, 596, 259);
		buttonDeleteCientifico.setBounds(162, 350, 108, 31);
		buttonUpdateCientifico.setBounds(287, 350, 112, 31);
		buttonAsignado.setBounds(214, 11, 111, 30);
		
		contentPane.add(label);
		contentPane.add(buttonUpdateCientifico);
		contentPane.add(buttonDeleteCientifico);
		contentPane.add(table);
		contentPane.add(buttonCrearCientifico);
		contentPane.add(buttonProyectos);
		contentPane.add(buttonAsignado);
		
		cientificoController = new CientificoController();
		actualizarCientificos();

		buttonCrearCientifico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CientificoViewCreate createView = new CientificoViewCreate();
				createView.setVisible(true); 
				dispose();
			}
		});
		buttonUpdateCientifico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					CientificoTable model = (CientificoTable) table.getModel();
					Cientifico cientificoSeleccionado = model.getCientifico(selectedRow);

					CientificoViewUpdate updateView = new CientificoViewUpdate(cientificoSeleccionado);
					updateView.setVisible(true);
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
					model = (CientificoTable) table.getModel();
					Cientifico cientificoSeleccionado = model.getCientifico(selectedRow);

					int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este cientifico?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

					if (option == JOptionPane.YES_OPTION) {
						if (cientificoController.eliminarCientifico(cientificoSeleccionado)) {
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
		
	
		buttonProyectos.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	ProyectoViewRead proyectoViewRead = new ProyectoViewRead();
			    proyectoViewRead.setVisible(true);
		        dispose();
		    }
		});
		
		
		
		buttonAsignado.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	AsignadoViewRead asignadoViewRead = new AsignadoViewRead();
		    	asignadoViewRead.setVisible(true);
		        dispose();
		    }
		});
		
	}
	
	public void actualizarCientificos()
	{
		cientificos = cientificoController.obtenerCientificos();
		
	    if (cientificos != null && !cientificos.isEmpty()) {
	    	model = new CientificoTable(cientificos);
	        table.setModel(model);
	    }
	}
	
	public JTable getTabla() {
		return table;
	}

}