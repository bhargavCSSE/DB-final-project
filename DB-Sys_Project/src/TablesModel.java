import java.util.List;
import javax.swing.table.AbstractTableModel;

class SQLTablesModel extends AbstractTableModel {

	private List<String> tables;
    private String columnName = "Tables_in_db_sys_class";

	public SQLTablesModel(List<String> tables) {
		this.tables = tables;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public int getRowCount() {
		return tables.size();
	}

	public String getColumnName() {
		return columnName;
	}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tables.get(rowIndex);
    }

}
