package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB_Connection.ConnectionDB;
import Model.Cientifico;
import Model.CientificoTable;
import Views.CientificoViewRead;

public class CientificoController {

	private CientificoViewRead cientificoViewRead;

	public CientificoController(Cientifico cientifico, CientificoViewRead cientificoViewRead) {
		this.cientificoViewRead = cientificoViewRead;
		iniciarVista();
	}

	public CientificoController() {
	}

	public void iniciarVista() {
		List<Cientifico> cientificos = obtenerCientificos();
		if (cientificos != null && !cientificos.isEmpty()) {
			CientificoTable model = new CientificoTable(cientificos);
			cientificoViewRead.getTabla().setModel(model);
		}
	}

	// Método para insertar un cliente en la base de datos
	public boolean insertarCientifico(Cientifico cientifico) {
		Connection connection = ConnectionDB.getConnection();
		String sql = "INSERT INTO cientificos (dni, nomApels) VALUES (?, ?)";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cientifico.getDni());
			statement.setString(2, cientifico.getNomApels());
			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Cientifico> obtenerCientificos() {
		List<Cientifico> cientificos = new ArrayList<>();
		String sql = "SELECT * FROM cientificos";

		try {
			Connection conexion = null;
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

	public boolean actualizarCientifico(Cientifico cientifico) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionDB.getConnection();
			String sql = "UPDATE cientificos SET nomApels = ? WHERE dni = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, cientifico.getNomApels());
			statement.setString(2, cientifico.getDni());

			int rowsUpdated = statement.executeUpdate();

			if (rowsUpdated > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean eliminarCientifico(Cientifico cientifico) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionDB.getConnection();
			String sql = "DELETE FROM cientificos WHERE dni = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, cientifico.getDni());

			int rowsDeleted = statement.executeUpdate();

			if (rowsDeleted > 0) {
				return true; // Éxito al eliminar
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}
