package add_tableModel;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class add_cellRenderer extends JLabel implements TableCellRenderer {
	
	public add_cellRenderer(){
		super.setOpaque(true);
		super.setHorizontalAlignment(CENTER);
		super.setFont(new Font("Tahoma", Font.PLAIN, 11));
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean cellHasFocus, int rowIndex, int columnIndex){
		super.setText(value.toString());
		return this;
	}
	
}
