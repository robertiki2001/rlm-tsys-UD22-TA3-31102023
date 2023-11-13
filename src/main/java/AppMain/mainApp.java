package AppMain;

import java.sql.SQLException;

import Controllers.CientificoController;
import DB_Connection.InicializarVariables;
import Model.Cientifico;
import Views.CientificoViewRead;

public class mainApp {

	public static void main(String[] args) throws SQLException {
		//Crea la base de datos y crea la tabla e inserciones
		InicializarVariables.inicializarVariables();
		//
	    Cientifico cientifico = new Cientifico();
	    CientificoViewRead cientificoViewRead = new CientificoViewRead();
	    CientificoController cientificoController = new CientificoController(cientifico, cientificoViewRead);
	    cientificoController.iniciarVista();
	    cientificoViewRead.setVisible(true);
	    
	}


}
