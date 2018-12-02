package engines;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import engines.clientConfiguration;
import engines.infoUser;
import engines.infoAdvertisimentMessageConfirm;

import messages_tableModel.messages_tableModel;
import messages_tableModel.messages_tableRenderer;

@SuppressWarnings("serial")
public class messageMechaClient extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private Thread thread1;
	private JLabel func_accept, func_reject;
	boolean stop = true;
	JScrollPane scrollPane;
	
	private String signal = "neutral";
	
	Connection LaunchConnect1;
	Connection LaunchConnect2;
	Connection LaunchConnect3;
	Connection LaunchConnect4;
	Connection LaunchConnect5;
	Connection LaunchConnect6;
	Connection LaunchConnect7;
	Connection LaunchConnect8;
	Connection LaunchConnect9;
	Connection LaunchConnect10;
	Connection LaunchConnect11;
	Connection LaunchConnect12;
	
	@SuppressWarnings("rawtypes")
	List list;
	
	infoUser user;
	infoAdvertisimentMessageConfirm confirm;
	
	static clientConfiguration config = new clientConfiguration();
	
	Image headers = new ImageIcon(this.getClass().getResource("/main_3_window/three_header.png")).getImage();
	Image mains = new ImageIcon(this.getClass().getResource("/main_3_window/three_main.png")).getImage();
	Image bottoms = new ImageIcon(this.getClass().getResource("/main_3_window/three_bottom.png")).getImage();
	Image func_accept_n = new ImageIcon(this.getClass().getResource("/main_mdl/n/func_accept_n.png")).getImage();
	Image func_accept_h = new ImageIcon(this.getClass().getResource("/main_mdl/h/func_accept_h.png")).getImage();
	Image func_accept_sh = new ImageIcon(this.getClass().getResource("/main_mdl/sh/func_accept_sh.png")).getImage();
	Image func_reject_n = new ImageIcon(this.getClass().getResource("/main_mdl/n/func_reject_n.png")).getImage();
	Image func_reject_h = new ImageIcon(this.getClass().getResource("/main_mdl/h/func_reject_h.png")).getImage();
	Image func_reject_sh = new ImageIcon(this.getClass().getResource("/main_mdl/sh/func_reject_sh.png")).getImage();
	Image func_send_n = new ImageIcon(this.getClass().getResource("/user_write_n.png")).getImage();
	Image func_send_h = new ImageIcon(this.getClass().getResource("/user_write_h.png")).getImage();
	Image des_logo_n = new ImageIcon(this.getClass().getResource("/reg_mdl/n/des_logo.png")).getImage();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public void getSignal(){
		Thread power = new Thread(){
			public void run(){
				while(true){
					try{
						LaunchConnect9 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
						Statement power = LaunchConnect9.createStatement();
						ResultSet set = power.executeQuery("Select * from global_advertisiment");
							while(set.next()){
								if(set.getInt("id") == confirm.getADCM_ID()){
									signal = set.getString("adv_get_like_signal");
								}
							}
						set.close();
						power.close();
					}catch(Exception error){
						error.printStackTrace();
					}finally{
						try{
							if(LaunchConnect9 != null){
								LaunchConnect9.close();
							}
							sleep(1000);
						}catch(Exception error){
							error.printStackTrace();
						}
					}
				}
			}
		};
		power.start();
	}
	
	public void getContent(){
		thread1 = new Thread(){
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void run(){
				try{
					LaunchConnect1 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
					Statement power = LaunchConnect1.createStatement();
					ResultSet set = power.executeQuery("Select * from global_messages");
					
					list = new ArrayList();
					
					while(set.next()){
						if(set.getInt("item_id") == confirm.getADCM_ID()){
							list.add(new infoMessage(set.getInt("item_id"), set.getString("user"), set.getString("messages")));
						}
					}
				
					table.setModel(new messages_tableModel(list));
					table.setDefaultEditor(infoMessage.class, new messages_tableRenderer());
					table.setDefaultRenderer(infoMessage.class, new messages_tableRenderer());
					table.setRowHeight(30);
					scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
					
					set.close();
					power.close();
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
		};
		thread1.start();
	}
	
	public messageMechaClient(infoUser obj1, infoAdvertisimentMessageConfirm obj2) {
		
		super(config.getAppTitle() + " CL");
		
		Image favicon = new ImageIcon(this.getClass().getResource("/favicon.png")).getImage();
		setIconImage(favicon);
		
		user = obj1;
		confirm = obj2;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 256, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		func_accept = new JLabel("");
		func_accept.setIcon(new ImageIcon(func_accept_n));
		func_accept.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if((signal).equals("null")){
					try{
						LaunchConnect3 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
						Statement power = LaunchConnect3.createStatement();
						power.execute("Insert into global_messages (item_id, user, messages) values ('" + confirm.getADCM_ID() + "', '" + user.getUserUsername() + "', 'SUSITARĖM!')");
							try{
								LaunchConnect7 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
								Statement power1 = LaunchConnect7.createStatement();
								ResultSet set1 = power1.executeQuery("Select * from global_advertisiment");
									while(set1.next()){
										if(set1.getInt("id") == confirm.getADCM_ID()){
											try{
												LaunchConnect8 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
												Statement power2 = LaunchConnect8.createStatement();
												power2.execute("Update global_advertisiment set adv_get_idd = '0', adv_get_username = 'null', adv_add_signal = 'null', adv_get_signal = 'null', adv_stop_signal = 'null', adv_global_signal = '0', adv_add_like_signal = 'trueSignal', adv_status = 'sold' where id = '" + confirm.getADCM_ID() + "'");
												power2.close();
												LaunchConnect8.close();
											}catch(Exception error){
												error.printStackTrace();
											}
										}
									}
								set1.close();
								power1.close();
								LaunchConnect7.close();
							}catch(Exception error){
								error.printStackTrace();
							}
						power.close();
						dispose();
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
				}else if((signal).equals("trueSignal")){
					try{
						LaunchConnect3 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
						Statement power = LaunchConnect3.createStatement();
						power.execute("Insert into global_messages (item_id, user, messages) values ('" + confirm.getADCM_ID() + "', '" + user.getUserUsername() + "', 'SUSITARĖM!')");
							try{
								LaunchConnect7 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
								Statement power1 = LaunchConnect7.createStatement();
								ResultSet set1 = power1.executeQuery("Select * from global_advertisiment");
									while(set1.next()){
										if(set1.getInt("id") == confirm.getADCM_ID()){
											try{
												LaunchConnect8 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
												Statement power2 = LaunchConnect8.createStatement();
												power2.execute("Update global_advertisiment set adv_get_idd = '0', adv_get_username = 'null', adv_add_signal = 'null', adv_get_signal = 'null', adv_stop_signal = 'null', adv_global_signal = '0', adv_add_like_signal = 'trueSignal', adv_status = 'sold' where id = '" + confirm.getADCM_ID() + "'");
												power2.close();
												LaunchConnect8.close();
												try{
													int a = user.getUserReputation();
													a = a + 1;
													LaunchConnect10 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
													Statement power3 = LaunchConnect10.createStatement();
													power3.execute("Update user_information set reputation = '" + a + "' where id = '" + user.getUserId() + "'");
													power3.close();
													try{
														LaunchConnect11 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
														Statement power4 = LaunchConnect11.createStatement();
														power4.execute("Update global_advertisiment set adv_add_like_signal = 'trueSignal' where id = '" + confirm.getADCM_ID() + "'");
														power4.close();
														LaunchConnect11.close();
														dispose();
													}catch(Exception error){
														error.printStackTrace();
													}
												}catch(Exception error){
													error.printStackTrace();
												}finally{
													try{
														if(LaunchConnect10 != null){
															LaunchConnect10.close();
														}
													}catch(Exception error){
														error.printStackTrace();
													}
												}
											}catch(Exception error){
												error.printStackTrace();
											}
										}
									}
								set1.close();
								power1.close();
								LaunchConnect7.close();
							}catch(Exception error){
								error.printStackTrace();
							}
						power.close();
						dispose();
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
				}else if((signal).equals("falseSignal")){
					try{
						LaunchConnect4 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
						Statement power = LaunchConnect4.createStatement();
						ResultSet set = power.executeQuery("Select * from global_advertisiment");
							while(set.next()){
								if(set.getInt("id") == confirm.getADCM_ID()){
									try{
										LaunchConnect5 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
										Statement power0 = LaunchConnect5.createStatement();
										power0.execute("Update global_advertisiment set adv_get_username = 'null', adv_add_signal = 'null', adv_get_signal = 'null', adv_stop_signal = 'null', adv_global_signal = '0', adv_add_like_signal = 'null', adv_get_like_signal = 'null' where id = '" + confirm.getADCM_ID()  + "'");
										try{
											LaunchConnect6 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
											Statement power1 = LaunchConnect6.createStatement();
											power1.execute("Delete from global_messages where item_id = '" + confirm.getADCM_ID() + "'");
											dispose();
											power1.close();
											LaunchConnect6.close();
										}catch(Exception error){
											error.printStackTrace();
										}
										power0.close();
										LaunchConnect5.close();
									}catch(Exception error){
										error.printStackTrace();
									}
								}
							}	
						set.close();
						power.close();
					}catch(Exception error){
						error.printStackTrace();
					}finally{
						try{
							if(LaunchConnect4 != null){
								LaunchConnect4.close();
							}
						}catch(Exception error){
							error.printStackTrace();
						}
					}
				}
			}
			public void mouseEntered(MouseEvent e){
				func_accept.setIcon(new ImageIcon(func_accept_h));
			}
			public void mouseExited(MouseEvent e){
				func_accept.setIcon(new ImageIcon(func_accept_n));
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 230, 234);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.white);
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(10, 258, 187, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		func_accept.setBounds(10, 289, 103, 37);
		contentPane.add(func_accept);
		
		func_reject = new JLabel("");
		func_reject.setIcon(new ImageIcon(func_reject_n));
		func_reject.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				try{
					LaunchConnect4 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
					Statement power = LaunchConnect4.createStatement();
					ResultSet set = power.executeQuery("Select * from global_advertisiment");
						while(set.next()){
							if(set.getInt("id") == confirm.getADCM_ID()){
								try{
									LaunchConnect5 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
									Statement power0 = LaunchConnect5.createStatement();
									power0.execute("Update global_advertisiment set adv_get_username = 'null', adv_add_signal = 'null', adv_get_signal = 'null', adv_stop_signal = 'null', adv_global_signal = '0', adv_add_like_signal = 'null', adv_get_like_signal = 'null' where id = '" + confirm.getADCM_ID()  + "'");
									try{
										LaunchConnect6 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
										Statement power1 = LaunchConnect6.createStatement();
										power1.execute("Delete from global_messages where item_id = '" + confirm.getADCM_ID() + "'");
										dispose();
									}catch(Exception error){
										error.printStackTrace();
									}
									power0.close();
									LaunchConnect5.close();
								}catch(Exception error){
									error.printStackTrace();
								}
							}
						}	
					set.close();
					power.close();
				}catch(Exception error){
					error.printStackTrace();
				}finally{
					try{
						if(LaunchConnect4 != null){
							LaunchConnect4.close();
						}
					}catch(Exception error){
						error.printStackTrace();
					}
				}
			}
			public void mouseEntered(MouseEvent e){
				func_reject.setIcon(new ImageIcon(func_reject_h));
			}
			public void mouseExited(MouseEvent e){
				func_reject.setIcon(new ImageIcon(func_reject_n));
			}
		});
		func_reject.setBounds(137, 289, 103, 37);
		contentPane.add(func_reject);
		
		JLabel func_send = new JLabel("");
		func_send.setIcon(new ImageIcon(func_send_n));
		func_send.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				try{
					LaunchConnect2 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
					Statement power = LaunchConnect2.createStatement();
					power.execute("Insert into global_messages (item_id, user, messages) values ('" + confirm.getADCM_ID() + "', '" + user.getUserUsername() + "', '" + textField.getText() + "')");
					power.close();
					textField.setText("");
					getContent();
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
			public void mouseEntered(MouseEvent e){
				func_send.setIcon(new ImageIcon(func_send_h));
			}
			public void mouseExited(MouseEvent e){
				func_send.setIcon(new ImageIcon(func_send_n));
			}
		});
		func_send.setBounds(207, 256, 33, 26);
		contentPane.add(func_send);
		
		JLabel des_logo = new JLabel("");
		des_logo.setIcon(new ImageIcon(des_logo_n));
		des_logo.setBounds(70, 340, 111, 25);
		contentPane.add(des_logo);
		
		JLabel bottom = new JLabel("");
		bottom.setIcon(new ImageIcon(bottoms));
		bottom.setBounds(0, 337, 251, 30);
		contentPane.add(bottom);
		
		JLabel header = new JLabel("");
		header.setIcon(new ImageIcon(headers));
		header.setBounds(0, 0, 251, 115);
		contentPane.add(header);
		
		JLabel main = new JLabel("");
		main.setIcon(new ImageIcon(mains));
		main.setBounds(0, 114, 251, 234);
		contentPane.add(main);
		
		Thread power = new Thread(){
			public void run(){
				while(stop){
					try{
						sleep(2000);
					}catch(Exception error){
						error.printStackTrace();
					}finally{
						getContent();
					}
				}
			}
		};
		power.start();
		getSignal();
	}
}