package add_tableModel;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import javax.swing.ImageIcon;

import engines.infoAdd.Status;

@SuppressWarnings("serial")
public class add_tableRenderer extends JLabel implements TableCellRenderer{
	
	public add_tableRenderer(){
		super.setOpaque(true);
		super.setHorizontalAlignment(CENTER);
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean cellHasFocus, int row, int column){
		Status obj = (Status) value;
		
			if(obj == Status.sold){
				super.setIcon(new ImageIcon(this.getClass().getResource("/func_suc.png")));
			}else if(obj == Status.on_sale){
				super.setIcon(new ImageIcon(this.getClass().getResource("/func_uns.png")));
			}else if(obj != Status.sold && obj != Status.on_sale){
				super.setHorizontalAlignment(CENTER);
			}
		
		return this;
	}
}
