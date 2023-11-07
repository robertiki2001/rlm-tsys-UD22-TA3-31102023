package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controllers.CientificoReadController;
import Model.Cientifico;

public class CientificoViewUpdate extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelNomApelsUpdate;
	private JLabel labelDniUpdate;
	private JLabel tituloUpdate;
	private JTextField textFieldNomApelsUpdate;
	private JTextField textFieldDniUpdate;
	private JButton buttonActualizarCientifico;
	private JButton buttonMenuUpdate;

    public CientificoViewUpdate(Cientifico cientificoSeleccionado) {
    	setTitle("Vista editar cientifico");
		setBounds(600,450,400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		labelDniUpdate = new JLabel("Dni:");
		labelDniUpdate.setBounds(48,103,200,20);
		contentPane.add(labelDniUpdate);
		
		labelNomApelsUpdate = new JLabel("Nombre:");
		labelNomApelsUpdate.setBounds(48, 151, 200, 20);
		contentPane.add(labelNomApelsUpdate);
		
		textFieldDniUpdate = new JTextField();
		textFieldDniUpdate.setBounds(104, 103, 176, 20);
		contentPane.add(textFieldDniUpdate);
		textFieldDniUpdate.setColumns(10);		
		
		textFieldNomApelsUpdate = new JTextField();
		textFieldNomApelsUpdate.setColumns(10);
		textFieldNomApelsUpdate.setBounds(104, 151, 176, 20);
		contentPane.add(textFieldNomApelsUpdate);
			
		tituloUpdate = new JLabel("ACTUALIZAR Cientifico");
		tituloUpdate.setBounds(100, 43, 294, 30);
		contentPane.add(tituloUpdate);
		
		buttonActualizarCientifico = new JButton("Actualizar cientifico");
		buttonActualizarCientifico.setBounds(81, 265, 217, 23);
		contentPane.add(buttonActualizarCientifico);
		
		buttonMenuUpdate = new JButton("Científicos");
		buttonMenuUpdate.setBounds(263, 11, 111, 30);
		buttonMenuUpdate.addActionListener(new ActionListener() {
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
		contentPane.add(buttonMenuUpdate);
		
    }
    
    public JButton getButtonActualizarCientifico() {
        return buttonActualizarCientifico;
    }
    
    public JTextField getTextFieldDniUpdate() {
        return textFieldDniUpdate;
    }
    
    public JTextField getTextFieldNomApelsUpdate() {
        return textFieldNomApelsUpdate;
    }
    
}