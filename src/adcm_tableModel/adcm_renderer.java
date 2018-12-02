package adcm_tableModel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Image;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.AbstractCellEditor;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import engines.clientConfiguration;
import engines.infoAdvertisiment;
import engines.infoAdvertisimentMessageConfirm;
import engines.infoUser;

import engines.messageMechaClient;


@SuppressWarnings("serial")
public class adcm_renderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	
	infoAdvertisimentMessageConfirm info;
	infoAdvertisiment adv_info;
	infoUser info_user;
	
	JPanel panel;
	JLabel label, button1, button2;
	
	messageMechaClient messageClient;
	clientConfiguration config = new clientConfiguration();
	
	Connection LaunchConnect1;
	Connection LaunchConnect2;
	Connection LaunchConnect3;
	
	Image fc_n = new ImageIcon(this.getClass().getResource("/func_acc_n.png")).getImage();
	Image fc_h = new ImageIcon(this.getClass().getResource("/func_acc_h.png")).getImage();
	Image fc_s = new ImageIcon(this.getClass().getResource("/func_acc_s.png")).getImage();
	Image fd_n = new ImageIcon(this.getClass().getResource("/func_dec_n.png")).getImage();
	Image fd_h = new ImageIcon(this.getClass().getResource("/func_dec_h.png")).getImage();
	Image fd_s = new ImageIcon(this.getClass().getResource("/func_dec_s.png")).getImage();
	
	public adcm_renderer(infoUser obj1, infoAdvertisiment obj2){
		
		info_user = obj1;
		adv_info = obj2;
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		label = new JLabel();
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		button1 = new JLabel();
		button2 = new JLabel();
		
		button1.setIcon(new ImageIcon(fc_n));
		button2.setIcon(new ImageIcon(fd_n));
		
		panel.add(label);
		panel.add(button1);
		panel.add(button2);
		
	}
	
	public void updateData(infoAdvertisimentMessageConfirm obj1, boolean obj2, JTable obj3){
		info = obj1;
		label.setText("<html>"
			+ "<p><font color = 'rgb(18,54,55)'>Nuo: </font><font color = 'rgb(123,0,128)'>"
			+ info.getADCM_USERNAME()
			+ "</font></p>"
			+ "<p><font color = 'rgb(18,54,55)'>Dėl: </font><font color = 'rgb(123,0,128)'>"
			+ info.getADCM_WEIGHT() + "(g)</font><font color = 'BLACK'>/</font><font color = 'rgb(123,0,128)'>"
			+ info.getADCM_CITY()
			+ "</font><font color = 'white'>...</font></p></html>"
		);
		
		button1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				try{
					LaunchConnect1 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
					Statement power1 = LaunchConnect1.createStatement();
					ResultSet set1 = power1.executeQuery("Select * from global_advertisiment");
					
					while(set1.next()){
						if(set1.getInt("id") == info.getADCM_ID()){
							if((set1.getString("adv_get_signal")).equals("trueSignal")){
								try{
									LaunchConnect2 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
									Statement power2 = LaunchConnect2.createStatement();
									power2.execute("Update global_advertisiment set adv_add_signal = 'trueSignal' where id = '" + info.getADCM_ID() + "'");
									power2.close();
									
									try{
										LaunchConnect3 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
										Statement power3 = LaunchConnect3.createStatement();
										power3.execute("Insert into global_messages (item_id, user, messages) values('" + info.getADCM_ID() + "', '" + info_user.getUserUsername() + "', 'prisijungė!')");
										power3.close();
									}catch(Exception error){
										error.printStackTrace();
									}finally{
										try{
											if(LaunchConnect3 != null){
												LaunchConnect3.close();
											}
										}catch(Exception error){
											error.printStackTrace();
										}
									}
									
								}catch(Exception error){
									error.printStackTrace();
								}finally{
									try{
										if(LaunchConnect2 != null){
											LaunchConnect2.close();
										}
									}catch(Exception error){
										error.printStackTrace();
									}
								}
							}
						}
					}
					
					messageClient = new messageMechaClient(info_user, info);
					messageClient.setVisible(true);
					
					set1.close();
					power1.close();
				}catch(Exception error){
					error.printStackTrace();
				}finally{
					try{
						if(LaunchConnect1 != null){
							LaunchConnect1.close();
						}
					}catch(Exception error){
						error.printStackTrace();
					}
				}
				
			}
			public void mouseEntered(MouseEvent e){
				button1.setIcon(new ImageIcon(fc_h));
			}
			public void mouseExited(MouseEvent e){
				button1.setIcon(new ImageIcon(fc_n));
			}
			public void mousePressed(MouseEvent e){
				button1.setIcon(new ImageIcon(fc_s));
			}
		});
		
		button2.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				try{
					LaunchConnect1 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
					Statement power1 = LaunchConnect1.createStatement();
					ResultSet set1 = power1.executeQuery("Select * from global_advertisiment");
					
					while(set1.next()){
						if(set1.getInt("id") == info.getADCM_ID()){
							if((set1.getString("adv_get_signal")).equals("trueSignal")){
								try{
									LaunchConnect2 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
									Statement power2 = LaunchConnect2.createStatement();
									power2.execute("Update global_advertisiment set adv_stop_signal = 'stop' where id = '" + info.getADCM_ID() + "'");
									power2.close();
								}catch(Exception error){
									error.printStackTrace();
								}finally{
									try{
										if(LaunchConnect2 != null){
											LaunchConnect2.close();
										}
									}catch(Exception error){
										error.printStackTrace();
									}
								}
							}
						}
					}
					set1.close();
					power1.close();
				}catch(Exception error){
					error.printStackTrace();
				}finally{
					try{
						if(LaunchConnect1 != null){
							LaunchConnect1.close();
						}
					}catch(Exception error){
						error.printStackTrace();
					}
				}
			}
			public void mouseEntered(MouseEvent e){
				button2.setIcon(new ImageIcon(fd_h));
			}
			public void mouseExited(MouseEvent e){
				button2.setIcon(new ImageIcon(fd_n));
			}
			public void mousePressed(MouseEvent e){
				button2.setIcon(new ImageIcon(fd_s));
			}
		});
		
		if(obj2){
			panel.setBackground(obj3.getBackground());
			
		}else{
			panel.setBackground(obj3.getBackground());
		}
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
		infoAdvertisimentMessageConfirm info = (infoAdvertisimentMessageConfirm) value;
		updateData(info, isSelected, table);
		return panel;
	}
	
	@Override
	public Object getCellEditorValue() {
		return null;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocused, int row, int column){
		infoAdvertisimentMessageConfirm info = (infoAdvertisimentMessageConfirm) value;
		updateData(info, isSelected, table);
		return panel;
	}

}
