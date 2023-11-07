package Controllers;

import DB_Connection.ConnectionDB;
import Model.Cientifico;
import Model.CientificoTable;

import java.awt.event.*;
import java.sql.*;
import java.util.*;

import Views.CientificoViewRead;

public class CientificoReadController implements ActionListener{
	private CientificoViewRead cientificoViewRead;
	
	public CientificoReadController(Cientifico cientifico, CientificoViewRead cientificoViewRead) {
		this.cientificoViewRead = cientificoViewRead;
		iniciarVista(); // Cargar registros al crear el controlador
	}
	
	public CientificoReadController(CientificoViewRead clienteViewRead2) {
	}

	public void iniciarVista() {
	    List<Cientifico> cientificos = obtenerCientificos();
	    if (cientificos != null && !cientificos.isEmpty()) {
	        CientificoTable model = new CientificoTable(cientificos);
	        cientificoViewRead.getTabla().setModel(model);
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Cientifico> obtenerCientificos() {
	    List<Cientifico> cientificos = new ArrayList<>();
	    
	    // Realiza una consulta SQL para obtener todos los clientes
	    String sql = "SELECT * FROM cientificos";
	    
	    try {
	    	Connection conexion = null;
	        // Obtener la conexión utilizando la clase ConnectionDB
	        conexion = ConnectionDB.getConnection();
	        Statement stmt = conexion.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        while (rs.next()) {
	            String dni = rs.getString("dni");
	            String nomApels = rs.getString("nomApels");
	            
	            Cientifico cientifico = new Cientifico(dni, nomApels);
	            cientificos.add(cientifico);
	        }
	        
	        rs.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return cientificos;
	}

}
