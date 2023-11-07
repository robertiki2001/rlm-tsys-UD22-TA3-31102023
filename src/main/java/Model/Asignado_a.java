package Model;

public class Asignado_a {
	
	private String cientifico;
	private String proyecto;
	
	public Asignado_a(String cientifico, String proyecto) {
		this.cientifico = cientifico;
		this.proyecto = proyecto;
	}
	
	public Asignado_a() {}

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
