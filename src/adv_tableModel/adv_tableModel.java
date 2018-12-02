package adv_tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import engines.infoAdvertisiment;

@SuppressWarnings("serial")
public class adv_tableModel extends AbstractTableModel{
	@SuppressWarnings("rawtypes")
	List list;
	
	public adv_tableModel(@SuppressWarnings("rawtypes") List obj1){
		list = obj1;
	}
	
	public String getColumnName(int columnIndex){
		return "";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columnIndex){
		return infoAdvertisiment.class;
	}
	
	public int getColumnCount(){
		return 1;
	}
	
	public int getRowCount(){
		return (list == null)? 0:list.size();
	}
	
	public Object getValueAt(int rowIndex, int columnIndex){
		return (list == null)? null : list.get(rowIndex);
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex){
		return true;
	}
	
}

