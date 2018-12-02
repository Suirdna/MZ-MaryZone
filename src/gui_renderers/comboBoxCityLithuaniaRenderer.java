package gui_renderers;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ImageIcon;

import engines.clientConfiguration;

@SuppressWarnings({ "serial", "rawtypes" })
public class comboBoxCityLithuaniaRenderer extends JLabel implements ListCellRenderer{
	clientConfiguration config = new clientConfiguration();
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
		JLabel obj = new JLabel();
		obj.setOpaque(true);
		obj.setText(value.toString());
		
			for(int i = 0; i < 52; i++){
				if((value.toString()).equals(config.getCityLithuania(i))){
					obj.setIcon(new ImageIcon(this.getClass().getResource(config.getCityResourceLithuania(i))));
				}
			}
		
		return obj;
	}
}
