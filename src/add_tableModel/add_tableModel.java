package add_tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import engines.infoAdd;
import engines.infoAdd.Status;

@SuppressWarnings("serial")
public class add_tableModel extends AbstractTableModel{
	List<infoAdd> list;
	
	@SuppressWarnings("rawtypes")
	private Class columnClass[] = new Class[]{
		String.class, String.class, String.class, Status.class	
	};
	
	public add_tableModel(List<infoAdd> obj){
		list = obj;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columnIndex){
		return columnClass[columnIndex];
	}
	
	public String getColumnName(int columnIndex){
		return null;
	}
	
	public int getColumnCount(){
		return 4;
	}
	
	public int getRowCount(){
		return list.size();
	}
	
	public Object getValueAt(int rowIndex, int columnIndex){
		
		infoAdd row = list.get(rowIndex);
		
		if(columnIndex == 0){
			return row.getDate();
		}else if(columnIndex == 1){
			return row.getCity();
		}else if(columnIndex == 2){
			return row.getWeight();
		}else if(columnIndex == 3){
			return row.getStatus();
		}
		
		return this;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex){
		return false;
	}
}
