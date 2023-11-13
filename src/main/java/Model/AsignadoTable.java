package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AsignadoTable extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private List<Asignado> asignados;
    private String[] columnNames = {"id", "cientifico", "proyecto"};

    public AsignadoTable(List<Asignado> asignados) {
        this.asignados = asignados;
    }
    
    public List<Asignado> getAsignado() {
		return asignados;
	}


	// Metodo para obtener el cliente en una fila específica
    public Asignado getAsignado(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < asignados.size()) {
            return asignados.get(rowIndex);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return asignados.size();
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
    	Asignado asignado_a = asignados.get(rowIndex);

        switch (columnIndex) {
        	case 0:
        		return asignado_a.getId();
            case 1:
                return asignado_a.getCientifico();
            case 2:
                return asignado_a.getProyecto();
            default:
                return null;
        }
    }  
    
    public void removeRowAt(int row) 
    {
    	List<Asignado> nuevaTabla = new ArrayList<Asignado>();
    	
    	for (int i = 0; i < this.asignados.size(); i++) 
    	{
    		if(i != row)
			nuevaTabla.add(this.asignados.get(i));
		}
    	
    	this.asignados = nuevaTabla;
    }
}
