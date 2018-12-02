package gui_renderers;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ImageIcon;

import engines.clientConfiguration;

@SuppressWarnings("rawtypes")
public class comboBoxPrice implements ListCellRenderer{
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
		clientConfiguration config = new clientConfiguration();
		
		JLabel label = new JLabel();
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setText(value.toString() + " Eurai");
		
			if((value.toString()).equals("1")){
				label.setText(value.toString() + " Euras");
			}else if((value.toString()).length() == 2){
				label.setText(value.toString() + " Eurų");
			}
		label.setOpaque(true);
		
		if(value != null){
			for(int i = 0; i < config.getPriceCount(); i++ ){
				label.setIcon(new ImageIcon(this.getClass().getResource(config.getImagePrice())));
			}
		}
		
		return label;
	}
}
