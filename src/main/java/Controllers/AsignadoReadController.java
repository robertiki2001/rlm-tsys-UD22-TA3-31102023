package Controllers;

import DB_Connection.ConnectionDB;
import Model.AsignadoTable;
import Model.Asignado_a;
import Views.AsignadoViewRead;

import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class AsignadoReadController implements ActionListener{
	private AsignadoViewRead asignadoViewRead;
	
	public AsignadoReadController(Asignado_a asignado_a, AsignadoViewRead asignadoViewRead) {
		this.asignadoViewRead = asignadoViewRead;
		iniciarVistaAsignado(); // Cargar registros al crear el controlador
	}
	
	public AsignadoReadController(AsignadoViewRead videosViewRead2) {
	}

	public void iniciarVistaAsignado() {
	    List<Asignado_a> asignados = obtenerAsignado();
	    if (asignados != null && !asignados.isEmpty()) {
	    	AsignadoTable model = new AsignadoTable(asignados);
	    	asignadoViewRead.getTabla().setModel(model);
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public List<Asignado_a> obtenerAsignado() {
	    List<Asignado_a> asignados_a = new ArrayList<>();
	    
	    // Realiza una consulta SQL para obtener todos los clientes
	    String sql = "SELECT * FROM asignado_a";
	    
	    try {
	    	Connection conexion = null;
	        // Obtener la conexión utilizando la clase ConnectionDB
	        conexion = ConnectionDB.getConnection();
	        Statement stmt = conexion.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        while (rs.next()) {
	            String cientifico = rs.getString("cientifico");
	            String proyecto = rs.getString("proyecto");
	            
	            Asignado_a asignado_a = new Asignado_a(cientifico, proyecto);
	            asignados_a.add(asignado_a);
	        }
	        
	        rs.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return asignados_a;
	}

}
