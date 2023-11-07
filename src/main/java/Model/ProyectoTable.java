package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProyectoTable extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private List<Proyecto> proyectos;
    private String[] columnNames = {"id", "nombre","horas"};

    public ProyectoTable(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
    
    public List<Proyecto> getProyectos() {
		return proyectos;
	}

    @Override
    public int getRowCount() {
        return proyectos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Proyecto proyecto = proyectos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return proyecto.getId();
            case 1:
                return proyecto.getNombre();
            case 2:
                return proyecto.getHoras();
            default:
                return null;
        }
    }  

	// Metodo para obtener el Proyecto en una fila específica
    public Proyecto getProyecto(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < proyectos.size()) {
            return proyectos.get(rowIndex);
        }
        return null;
    }

	// Metodo para eliminar el Proyecto en una fila específica
    public void removeRowAt(int row) 
    {
    	List<Proyecto> nuevaTabla = new ArrayList<Proyecto>();
    	
    	for (int i = 0; i < this.proyectos.size(); i++) 
    	{
    		if(i != row)
			nuevaTabla.add(this.proyectos.get(i));
		}
    	
    	this.proyectos = nuevaTabla;
    }
}
