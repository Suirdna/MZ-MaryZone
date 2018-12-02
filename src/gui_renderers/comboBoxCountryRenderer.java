package gui_renderers;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.ListCellRenderer;

import engines.clientConfiguration;

@SuppressWarnings("rawtypes")
public class comboBoxCountryRenderer implements ListCellRenderer {
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
		clientConfiguration config = new clientConfiguration();
		JLabel label = new JLabel();
		label.setOpaque(true);
		label.setText(value.toString());
		
		for(int i = 0; i < config.getAllCountrysCount(); i++){
			if((label.getText()).equals(config.getAllCountrys(i))){
				label.setIcon(new ImageIcon(this.getClass().getResource(config.getCountryResource(i))));
			}
		}
		
		return label;
	}
}
