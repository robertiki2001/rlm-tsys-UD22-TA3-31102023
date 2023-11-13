package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controllers.CientificoController;
import Model.Cientifico;

public class CientificoViewUpdate extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelNomApelsUpdate;
	private JLabel tituloUpdate;
	private JTextField textFieldNomApelsUpdate;
	private JButton buttonActualizarCientifico;
	private Cientifico cientifico;
	private CientificoViewRead cientificoViewRead;
	private CientificoController cientificoController;

    public CientificoViewUpdate(Cientifico cientificoSeleccionado) {
    	setTitle("Vista editar cientifico");
		setBounds(600,450,400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		buttonActualizarCientifico = new JButton("Actualizar cientifico");	
		tituloUpdate = new JLabel("ACTUALIZAR Cientifico");
		textFieldNomApelsUpdate = new JTextField();
		labelNomApelsUpdate = new JLabel("Nombre:");
		cientificoViewRead = new CientificoViewRead();
		cientificoController = new CientificoController();
		cientifico = cientificoSeleccionado;
		
		labelNomApelsUpdate.setBounds(48, 151, 200, 20);
		textFieldNomApelsUpdate.setColumns(10);
		textFieldNomApelsUpdate.setBounds(104, 151, 176, 20);
		tituloUpdate.setBounds(100, 43, 294, 30);
		buttonActualizarCientifico.setBounds(81, 265, 217, 23);
		
		contentPane.add(buttonActualizarCientifico);
		contentPane.add(tituloUpdate);
		contentPane.add(textFieldNomApelsUpdate);
		contentPane.add(labelNomApelsUpdate);
		
		
		buttonActualizarCientifico.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String nomApels = textFieldNomApelsUpdate.getText();
	            
	            if (nomApels.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Nombre es un campo obligatorio.");
	                return;
	            }                    
	            cientifico.setNomApels(nomApels);

	            if (cientificoController.actualizarCientifico(cientifico)) {
	                JOptionPane.showMessageDialog(null, "Cientifico actualizado con éxito");
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al actualizar el Cientifico");
	            }
		    	
	            cientificoViewRead.actualizarCientificos();
	            cientificoViewRead.setVisible(true);
		        dispose();
		    }
		});
		
    }    
    
}