package gui_renderers;

import java.awt.Font;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ImageIcon;

import engines.clientConfiguration;

@SuppressWarnings("rawtypes")
public class comboBoxWeight implements ListCellRenderer{
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
		clientConfiguration config = new clientConfiguration();
		JLabel label = new JLabel();
		label.setOpaque(true);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setText(value.toString() + " Gramai");
		
			if((value.toString()).equals("1")){
				label.setText(value.toString() + " Gramas");
			}
		
			if((value.toString()).length() == 2){
				label.setText(value.toString() + " Gramų");
			}
			
			if(value != null){
				for(int i = 0; i < config.getWeightCount(); i++){
					label.setIcon(new ImageIcon(this.getClass().getResource(config.getImageWeight())));
				}
			}
		
		return label;
	}
}
