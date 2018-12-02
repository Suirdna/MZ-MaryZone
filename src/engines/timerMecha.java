package engines;

import java.awt.Image;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import engines.clientConfiguration;
import engines.infoUser;
import engines.infoAdvertisiment;
import engines.messageMechaServer;

public class timerMecha extends JFrame {
	
	private JPanel contentPane;
	private JLabel value, client, server;
	Integer values = 60;
	int effect = 0;
	int system = 0;
	int global_signal = 0;
	int stop_signal = 0;
	
	Connection connect1;
	Connection connect2;
	Connection connect3;
	Connection connect4;
	Connection connect5;
	Connection connect6;
	
	Thread thread1, thread2;
	
	clientConfiguration config = new clientConfiguration();
	
	protected infoAdvertisiment info;
	protected infoUser user;
	
	messageMechaServer lastComponent;
	//static timerMecha frame = new timerMecha();

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

	public void getConnection(infoAdvertisiment obj1, infoUser obj2){
		info = obj1;
		user = obj2;
		thread1 = new Thread(){
			public void run(){
				while(system <= 60){
					try{
						connect1 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
						Statement power1 = connect1.createStatement();
						ResultSet set1 = power1.executeQuery("Select * from global_advertisiment");
							while(set1.next()){
								if(set1.getInt("id") == obj1.user_advertisiment_id){
									if(set1.getString("adv_stop_signal").equals("null")){
										if(set1.getInt("adv_global_signal") == 0){
											connect2 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
											Statement power2 = connect2.createStatement();
											power2.execute("Update global_advertisiment set adv_get_username = '" + user.getUserUsername() + "', adv_get_signal = 'trueSignal' where ID = '" + obj1.user_advertisiment_id + "'");
											power2.close();
											try{
												if(connect2 != null){
													connect2.close();
												}
											}catch(Exception error){
												error.printStackTrace();
											}
										}else{
											global_signal = 1;
										}
									}else if(set1.getString("adv_stop_signal").equals("stop")){
										connect2 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
										Statement power2 = connect2.createStatement();
										power2.execute("Update global_advertisiment set adv_get_username = 'null', adv_get_signal = 'null', adv_stop_signal = 'null' where ID = '" + obj1.user_advertisiment_id + "'");
										power2.close();
										stop_signal = 1;
									}
								}
							}
						set1.close();
						power1.close();
						sleep(1000);
					}catch(Exception error){
						error.printStackTrace();
					}finally{
						if(global_signal == 0){
							if(stop_signal == 0){
								values--;
								system++;
								value.setText(values.toString());
								if(values == -1){
									values = 0;
									value.setText(values.toString());
									try{
										connect4 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
										Statement power4 = connect4.createStatement();
										power4.execute("Update global_advertisiment set adv_get_username = 'null', adv_get_signal = 'null' where id = '" + obj1.user_advertisiment_id + "'");
										power4.close();
										connect4.close();
									}catch(Exception error){
										error.printStackTrace();
									}finally{
										try{
											if(connect4 != null){
												connect4.close();
											}
										}catch(Exception error){
											error.printStackTrace();
										}
									}
									dispose();
								}
								try{
									connect3 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
									Statement power3 = connect3.createStatement();
									ResultSet set3 = power3.executeQuery("Select * from global_advertisiment");
										while(set3.next()){
											if(set3.getInt("id") == obj1.user_advertisiment_id){
												if((set3.getString("adv_get_signal")).equals("trueSignal")){
													if((set3.getString("adv_add_signal")).equals("trueSignal")){
														system = 60;
														values = 0;
														try{
															connect6 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
															Statement power = connect6.createStatement();
															power.execute("Insert into global_messages (item_id, user, messages) values ('" + info.user_advertisiment_id + "', '" + user.getUserUsername() + "', 'prisijungė!')");
															power.close();
															connect6.close();
															lastComponent = new messageMechaServer(user, info);
															lastComponent.setVisible(true);
														}catch(Exception error){
															error.printStackTrace();
														}
														
														lastComponent = new messageMechaServer(user, info);
														
														dispose();
													}else if((set3.getString("adv_add_signal")).equals("falseSignal")){
														system = 60;
														values = 0;
														try{
															connect5 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
															Statement power5 = connect5.createStatement();
															power5.execute("Update global_advertisiment set adv_get_username = 'null', adv_get_signal = 'null', adv_add_signal = 'null' where id = '" + obj1.user_advertisiment_id + "'");
															power5.close();
															connect5.close();
														}catch(Exception error){
															error.printStackTrace();
														}finally{
															try{
																if(connect5 != null){
																	connect5.close();
																}
															}catch(Exception error){
																error.printStackTrace();
															}
														}
														dispose();
													}
												}
											}
										}
									power3.close();
									set3.close();
								}catch(Exception error){
									error.printStackTrace();
								}
								finally{
									try{
										if(connect3 != null){
											connect3.close();
										}
									}catch(Exception error){
										error.printStackTrace();
									}
								}
								
								try{
									if(connect5 != null){
										connect5.close();
									}
								}catch(Exception error){
									error.printStackTrace();
								}
							}else{
								JOptionPane.showMessageDialog(null, "Pardavėjas atmetė jūsų pakvietimą.", config.getAppTitle(), JOptionPane.ERROR_MESSAGE);
								thread2.stop();
								dispose();
								thread1.stop();
							}
						}else{
							JOptionPane.showMessageDialog(null, "Atsiprašome, bet jau vyksta derybos.", config.getAppTitle(), JOptionPane.ERROR_MESSAGE);
							thread2.stop();
							dispose();
							thread1.stop();
						}
					}
				}
			}
		};
		thread1.start();
	}
	
	/**
	 * Create the frame.
	 */
	public timerMecha() {
		
		Image favicon = new ImageIcon(this.getClass().getResource("/favicon.png")).getImage();
		setIconImage(favicon);
		
		Image client_n = new ImageIcon(this.getClass().getResource("/client.png")).getImage();
		Image server_n = new ImageIcon(this.getClass().getResource("/server.png")).getImage();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 150, 74);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(11,68,70));
		
		client = new JLabel("");
		client.setIcon(new ImageIcon(client_n));
		client.setBounds(10, 6, 35, 35);
		contentPane.add(client);
		
		server = new JLabel("");
		server.setBounds(98, 4, 35, 35);
		contentPane.add(server);
		
		value = new JLabel(values.toString());
		value.setHorizontalAlignment(SwingConstants.CENTER);
		value.setFont(new Font("Arial", Font.BOLD, 20));
		value.setForeground(Color.WHITE);
		value.setBounds(10, 9, 124, 29);
		contentPane.add(value);
		
		thread2 = new Thread(){
			public void run(){
				while(values >= 0){
					if(effect == 0){
						server.setIcon(new ImageIcon(server_n));
						effect++;
					}else if(effect == 1){
						server.setIcon(null);
						effect--;
					}
					try{
						sleep(1000);
					}catch(Exception error){
						error.printStackTrace();
					}
				}
			}
		};
		thread2.start();
	}
}
