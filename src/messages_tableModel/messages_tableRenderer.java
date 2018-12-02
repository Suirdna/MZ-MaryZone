package messages_tableModel;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;

import engines.infoMessage;

@SuppressWarnings("serial")
public class messages_tableRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
	
	JPanel panel;
	JLabel label;
	infoMessage info;
	
	public messages_tableRenderer(){
		label = new JLabel();
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setForeground(Color.BLACK);
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(label);
	}
	
	public void updateData(infoMessage obj1, boolean obj2, JTable table){
		info = obj1;
		label.setText(""
				+ "<html><font color = 'rgb(123,0,128)'>"
				+ info.getUser()
				+ ": </font>"
				+ info.getMessage());
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int columnIndex){
		infoMessage message = (infoMessage) value;
		updateData(message, isSelected, table);
		return panel;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int rowIndex, int columnIndex){
		infoMessage message = (infoMessage) value;
		updateData(message, isSelected, table);
		return panel;
	}
	
	public Object getCellEditorValue(){
		return null;
	}
}
