package Model;

public class Asignado {

	private int id;
	private String cientifico;
	private String proyecto;
	
	public Asignado(int id, String cientifico, String proyecto) {
		this.id = id;
		this.cientifico = cientifico;
		this.proyecto = proyecto;
	}
	
	public Asignado() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCientifico() {
		return cientifico;
	}

	public void setCientifico(String cientifico) {
		this.cientifico = cientifico;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	
	

}
