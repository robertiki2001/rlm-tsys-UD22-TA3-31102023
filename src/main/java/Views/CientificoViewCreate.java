package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controllers.CientificoController;
import Model.Cientifico;

public class CientificoViewCreate extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelDni;
	private JLabel labelNomApels;
	private JLabel titulo;
	private JTextField textFieldDni;
	private JTextField textFieldNomApels;
	private JButton buttonCrearCientifico;
	private JButton buttonCientifico;
	private Cientifico cientifico;
	private CientificoViewRead cientificoViewRead;
	private CientificoController cientificoController;
	private JTable table;

    public CientificoViewCreate() {
    	setTitle("Vista crear cliente");
		setBounds(600,450,400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		buttonCientifico = new JButton("Científicos");
		titulo = new JLabel("REGISTO DE UN NUEVO CIENTÍFICO");
		table = new JTable();
		cientificoViewRead = new CientificoViewRead();
		cientificoController = new CientificoController();
		textFieldNomApels = new JTextField();
		textFieldDni = new JTextField();
		labelNomApels = new JLabel("Nombre y apellidos:");
		labelDni = new JLabel("Dni:");
		buttonCrearCientifico = new JButton("Crear científico");

		table.setBounds(29, 45, 596, 259);
		titulo.setBounds(118, 62, 294, 30);
		textFieldNomApels.setColumns(10);
		textFieldNomApels.setBounds(126, 151, 176, 20);
		textFieldDni.setBounds(126, 103, 176, 20);
		textFieldDni.setColumns(10);
		labelNomApels.setBounds(29, 151, 200, 20);
		labelDni.setBounds(63,103,200,20);
		buttonCrearCientifico.setBounds(100, 213, 217, 23);
		buttonCientifico.setBounds(248, 11, 126, 23);
		
		contentPane.add(buttonCientifico);
		contentPane.add(buttonCrearCientifico);
		contentPane.add(labelDni);
		contentPane.add(labelNomApels);
		contentPane.add(textFieldDni);
		contentPane.add(textFieldNomApels);
		contentPane.add(titulo);	
		contentPane.add(table);
		contentPane.add(buttonCientifico);
		
		buttonCientifico.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			    cientificoViewRead.setVisible(true);
		        dispose();
		    }
		});
		
		buttonCrearCientifico.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String dni = textFieldDni.getText();
	            String nomApels = textFieldNomApels.getText();
	            
	            if (dni.isEmpty() || nomApels.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Nombre y Dni son campos obligatorios.");
	                return;
	            }            
	            cientifico = new Cientifico(dni, nomApels);

	            if (cientificoController.insertarCientifico(cientifico)) {
	               
	                JOptionPane.showMessageDialog(null, "Cientifico insertado con éxito");
	                
	                limpiarFormulario();
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al insertar el Cientifico");
	            }
	            cientificoViewRead.actualizarCientificos();
		    }
		});
    }

    private void limpiarFormulario() {
        textFieldDni.setText("");
        textFieldNomApels.setText("");
    }
}
