package adcm_tableModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import engines.infoAdvertisimentMessageConfirm;

@SuppressWarnings("serial")
public class adcm_tableModel extends AbstractTableModel{
	@SuppressWarnings("rawtypes")
	List list;
	
	@SuppressWarnings("rawtypes")
	public adcm_tableModel(List obj){
		list = obj;
	}
	
	public String getColumnName(int columnIndex){
		return "";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columnIndex){
		return infoAdvertisimentMessageConfirm.class;
	}
	
	public int getColumnCount(){
		return 1;
	}
	
	public int getRowCount(){
		return (list.size() != 0 )? list.size():0;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex){
		return (list != null)? list.get(rowIndex):null;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex){
		return true;
	}
	
}
