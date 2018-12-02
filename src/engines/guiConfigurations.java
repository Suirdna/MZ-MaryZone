package engines;

import javax.swing.JComboBox;

import engines.clientConfiguration;

import gui_renderers.comboBoxPrice;
import gui_renderers.comboBoxWeight;

public class guiConfigurations {
	clientConfiguration config = new clientConfiguration();
	
	@SuppressWarnings("unchecked")
	public void autoGuiComboBox1(@SuppressWarnings("rawtypes") JComboBox obj1){
		for(int i = 0; i < config.getPriceCount(); i ++){
			obj1.addItem(config.setPriceItem(i));
		}
		
		obj1.setRenderer(new comboBoxPrice());
	}
	
	@SuppressWarnings("unchecked")
	public void autoGuiComboBox2(@SuppressWarnings("rawtypes") JComboBox obj1){
		for(int i = 0; i < config.getWeightCount(); i++){
			obj1.addItem(config.setWeightItem(i));
		}
		
		obj1.setRenderer(new comboBoxWeight());
	}
}
