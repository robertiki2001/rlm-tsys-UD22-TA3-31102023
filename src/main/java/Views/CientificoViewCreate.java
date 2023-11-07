package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controllers.CientificoCreateController;
import Controllers.CientificoReadController;
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
	private JButton buttonMenu;
	private JTable table;

    public CientificoViewCreate() {
    	setTitle("Vista crear cliente");
		setBounds(600,450,400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		buttonCrearCientifico = new JButton("Crear científico");
		buttonCrearCientifico.setBounds(100, 213, 217, 23);
		contentPane.add(buttonCrearCientifico);
		
		buttonCrearCientifico.addActionListener(new CientificoCreateController(this));
		
		labelDni = new JLabel("Dni:");
		labelDni.setBounds(63,103,200,20);
		contentPane.add(labelDni);
		
		labelNomApels = new JLabel("Nombre y apellidos:");
		labelNomApels.setBounds(29, 151, 200, 20);
		contentPane.add(labelNomApels);
		
		textFieldDni = new JTextField();
		textFieldDni.setBounds(126, 103, 176, 20);
		contentPane.add(textFieldDni);
		textFieldDni.setColumns(10);
		
		textFieldNomApels = new JTextField();
		textFieldNomApels.setColumns(10);
		textFieldNomApels.setBounds(126, 151, 176, 20);
		contentPane.add(textFieldNomApels);
		
		titulo = new JLabel("REGISTO DE UN NUEVO CIENTÍFICO");
		titulo.setBounds(118, 62, 294, 30);
		contentPane.add(titulo);
		
		buttonMenu = new JButton("Cientificos");
		buttonMenu.setBounds(263, 11, 111, 30);
		buttonMenu.addActionListener(new ActionListener() {
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
		contentPane.add(buttonMenu);

		
		table = new JTable();
		table.setBounds(29, 45, 596, 259);
		contentPane.add(table);

    }

    public JButton getButtonCrearCientifico() {
        return buttonCrearCientifico;
    }
    
    public JTextField getTextFieldDni() {
        return textFieldDni;
    }
    
    public JTextField getTextFieldNomApels() {
        return textFieldNomApels;
    }
    
    

}