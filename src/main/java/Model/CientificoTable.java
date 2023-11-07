package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CientificoTable extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private List<Cientifico> cientificos;
    private String[] columnNames = {"dni", "nomApels"};

    public CientificoTable(List<Cientifico> cientificos) {
        this.cientificos = cientificos;
    }
    
    public List<Cientifico> getCientificos() {
		return cientificos;
	}


	// Metodo para obtener el cliente en una fila específica
    public Cientifico getCientifico(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < cientificos.size()) {
            return cientificos.get(rowIndex);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return cientificos.size();
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
    	Cientifico cientifico = cientificos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return cientifico.getDni();
            case 1:
                return cientifico.getNomApels();
            default:
                return null;
        }
    }  
    
    public void removeRowAt(int row) 
    {
    	List<Cientifico> nuevaTabla = new ArrayList<Cientifico>();
    	
    	for (int i = 0; i < this.cientificos.size(); i++) 
    	{
    		if(i != row)
			nuevaTabla.add(this.cientificos.get(i));
		}
    	
    	this.cientificos = nuevaTabla;
    }
}
