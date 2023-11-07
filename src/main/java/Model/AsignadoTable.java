package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AsignadoTable extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private List<Asignado_a> asignados_a;
    private String[] columnNames = {"cientifico", "proyecto"};

    public AsignadoTable(List<Asignado_a> asignados_a) {
        this.asignados_a = asignados_a;
    }
    
    public List<Asignado_a> getAsignado_a() {
		return asignados_a;
	}


	// Metodo para obtener el cliente en una fila específica
    public Asignado_a getAsignado_a(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < asignados_a.size()) {
            return asignados_a.get(rowIndex);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return asignados_a.size();
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
    	Asignado_a asignado_a = asignados_a.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return asignado_a.getCientifico();
            case 1:
                return asignado_a.getProyecto();
            default:
                return null;
        }
    }  
    
    public void removeRowAt(int row) 
    {
    	List<Asignado_a> nuevaTabla = new ArrayList<Asignado_a>();
    	
    	for (int i = 0; i < this.asignados_a.size(); i++) 
    	{
    		if(i != row)
			nuevaTabla.add(this.asignados_a.get(i));
		}
    	
    	this.asignados_a = nuevaTabla;
    }
}
