package Controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DB_Connection.ConnectionDB;
import Model.Asignado;

public class AsignadoController {

	// Método para insertar un cliente en la base de datos
	public boolean insertarAsignado(Asignado asignado) {
		Connection connection = ConnectionDB.getConnection();
		String sql = "INSERT INTO asignado_a (id, cientifico, proyecto) VALUES (?, ?, ?)";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, asignado.getId());
			statement.setString(2, asignado.getCientifico());
			statement.setString(3, asignado.getProyecto());
			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Asignado> obtenerAsignados() {
		List<Asignado> asignados = new ArrayList<>();
		String sql = "SELECT * FROM asignado_a";

		try {
			Connection conexion = null;
			conexion = ConnectionDB.getConnection();
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String cientifico = rs.getString("cientifico");
				String proyecto = rs.getString("proyecto");

				Asignado asignado = new Asignado(id, cientifico, proyecto);
				asignados.add(asignado);
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return asignados;
	}

	public boolean actualizarAsignado(Asignado asignado) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionDB.getConnection();
			String sql = "UPDATE asignado_a SET cientifico = ?, proyecto = ? WHERE id = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, asignado.getCientifico());
			statement.setString(2, asignado.getProyecto());
			statement.setInt(3, asignado.getId());
			

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

	public boolean eliminarAsignado(Asignado asignado) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionDB.getConnection();
			String sql = "DELETE FROM asignado_a WHERE id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, asignado.getId());

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
