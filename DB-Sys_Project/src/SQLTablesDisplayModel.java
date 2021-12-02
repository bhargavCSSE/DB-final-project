import java.util.List;
import javax.swing.table.AbstractTableModel;

class SQLTablesDisplayModel extends AbstractTableModel {

    private String[] columnNames;
	private List<String[]> tuples; 

	public SQLTablesDisplayModel(String[] columnNames, List<String[]> tuples) {
		this.columnNames = columnNames;
		this.tuples = tuples;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return tuples.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tuples.get(rowIndex)[columnIndex];
    }

}
