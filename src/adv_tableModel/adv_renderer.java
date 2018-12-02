package adv_tableModel;

import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.AbstractCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.ImageIcon;

import engines.infoUser;
import engines.timerMecha;
import engines.infoAdvertisiment;

@SuppressWarnings("serial")
public class adv_renderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	
	private String colorName;
	
	JPanel panel;
	JLabel label, button;
	Image func_req_n, func_req_h, func_req_s;
	
	infoUser userInfo;
	timerMecha mecha;
	infoAdvertisiment info;
	
	int object = 0;
	int object2 = 0;
	
	public adv_renderer(infoUser obj){
		
		userInfo = obj;
		
		func_req_n = new ImageIcon(this.getClass().getResource("/func_req_n.png")).getImage();
		func_req_h = new ImageIcon(this.getClass().getResource("/func_req_h.png")).getImage();
		func_req_s = new ImageIcon(this.getClass().getResource("/func_req_s.png")).getImage();
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		button = new JLabel();
		button.setIcon(new ImageIcon(func_req_n));
		label = new JLabel();
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		
		panel.add(label);
		panel.add(button);
	}
	
	public void updateData(infoAdvertisiment obj1, boolean isSelected, JTable table){
		info = obj1;
		
		if(info.user_reputation <= 10){
			colorName = "RED";
		}else if(info.user_reputation > 10 && info.user_reputation <= 30 ){
			colorName = "ORANGE";
		}else if(info.user_reputation > 30 && info.user_reputation <= 50 ){
			colorName = "GREEN";
		}else if(info.user_reputation > 50 && info.user_reputation <= 100){
			colorName = "BLUE";
		}else if(info.user_reputation > 100){
			colorName = "PURPLE";
		}
		
		label.setText("<html>"
			+ "<p><font color = 'white'>....</font><font color = 'rgb(18,54,55)'>Pardavėjas: </font><font color = 'rgb(123,0,128)'>"
			+ info.user_username
			+ "</font></p>"
			+ "<p><font color = 'white'>....</font><font color = 'rgb(18,54,55)'>Reputacija: </font><font color = '" + colorName + "'>"
			+ info.user_reputation
			+ "</font></p>"
			+ "<p><font color = 'white'>....</font><font color = 'rgb(18,54,55)'>Kiekis: </font><font color = 'rgb(123,0,128)'>"
			+ info.user_weedWeight
			+ "</font><font size = '3'>(g)</font><font color = 'white'>...............................</font></p>"
			+ "<p><font color = 'white'>....</font><font color = 'rgb(18,54,55)'>Kaina(1g): </font><font color = 'rgb(123,0,128)'>"
			+ info.user_weedPrice
			+ "</font><font size = '2'>(€)</font></p></html>"
		);
	
		if(isSelected){
			panel.setBackground(table.getBackground());
			button.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
						mecha = null;
						mecha = new timerMecha();
						mecha.getConnection(info, userInfo);
						mecha.setVisible(true);
				}
				
				public void mousePressed(MouseEvent e){
					button.setIcon(new ImageIcon(func_req_s));
				}
				
				public void mouseEntered(MouseEvent e){
					button.setIcon(new ImageIcon(func_req_h));
				}
				public void mouseExited(MouseEvent e){
					button.setIcon(new ImageIcon(func_req_n));
				}
			});
		}else{
			panel.setBackground(table.getBackground());
		}
	}
	
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
		infoAdvertisiment info = (infoAdvertisiment) value;
		updateData(info, isSelected, table);
		return panel;
	}
	
	public Object getCellEditorValue(){
		return null;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		infoAdvertisiment info = (infoAdvertisiment) value;
		updateData(info, isSelected, table);
		return panel;
	}
}
