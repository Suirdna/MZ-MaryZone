package user_stage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import guest_stage.function_loginwindow;

import engines.clientConfiguration;
import engines.infoUser;

public class function_changepw extends JFrame {

	private JPanel contentPane;
	private JPasswordField field1;
	private JPasswordField field2;
	private JPasswordField field3;
	private JLabel status;
	
	clientConfiguration config = new clientConfiguration();
	infoUser userInfo;
	
	function_loginwindow window;
	
	Connection LaunchConnect1;
	Connection LaunchConnect2;

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
	
	public function_changepw(infoUser obj, function_mainwindow obj2) {
		
		userInfo = obj;
		
		Image favicon = new ImageIcon(this.getClass().getResource("/favicon.png")).getImage();
		setIconImage(favicon);
		
		Image func_changepw_n = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_change_pw_n.png")).getImage();
		Image func_changepw_h = new ImageIcon(this.getClass().getResource("/reg_mdl/h/func_change_pw_h.png")).getImage();
		Image func_changepw_cancel_n = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_change_pw_cancel_n.png")).getImage();
		Image func_changepw_cancel_h = new ImageIcon(this.getClass().getResource("/reg_mdl/h/func_change_pw_cancel_h.png")).getImage();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 253, 244);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel des_logo = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("/reg_mdl/n/des_logo.png")).getImage();
		des_logo.setIcon(new ImageIcon(logo));
		des_logo.setBounds(10, 5, 111, 30);
		contentPane.add(des_logo);
		
		JLabel des_changepw = new JLabel("");
		Image changepw = new ImageIcon(this.getClass().getResource("/reg_mdl/n/des_change.png")).getImage();
		des_changepw.setIcon(new ImageIcon(changepw));
		des_changepw.setBounds(130, 8, 95, 30);
		contentPane.add(des_changepw);
		
		JLabel label1 = new JLabel("D. Slaptažodis:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label1.setBounds(14, 46, 80, 14);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("N. Slaptažodis:");
		label2.setForeground(Color.WHITE);
		label2.setBackground(Color.WHITE);
		label2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label2.setBounds(14, 72, 80, 14);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("P. Slaptažodį:");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label3.setBounds(14, 99, 80, 14);
		contentPane.add(label3);
		
		field1 = new JPasswordField();
		field1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		field1.setBounds(104, 44, 133, 20);
		contentPane.add(field1);
		
		field2 = new JPasswordField();
		field2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		field2.setBounds(104, 70, 133, 20);
		contentPane.add(field2);
		
		field3 = new JPasswordField();
		field3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		field3.setBounds(104, 97, 133, 20);
		contentPane.add(field3);
		
		JButton func_changepw = new JButton("");
		func_changepw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					LaunchConnect1 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
					Statement power = LaunchConnect1.createStatement();
					ResultSet set = power.executeQuery("Select * from user_information");
					
						while(set.next()){
							if(set.getInt("id") == userInfo.getUserId()){
								if((field1.getText()).equals(set.getString("password"))){
									if(!(field2.getText()).isEmpty() && !(field3.getText()).isEmpty()){
										if((field2.getText()).equals(field3.getText())){
											try{
												LaunchConnect2 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
												Statement power2 = LaunchConnect2.createStatement();
												power2.execute("Update user_information set password = '" + field2.getText() + "' where id = '" + userInfo.getUserId() + "'");
												power2.close();
												window = new function_loginwindow();
												window.setVisible(true);
												obj2.dispose();
												dispose();
											}catch(Exception error){
												status.setText(config.getLoginc3());
											}finally{
												try{
													if(LaunchConnect2 != null){
														LaunchConnect2.close();
													}
												}catch(Exception error){
													status.setText(config.getLoginc3());
												}
											}
										}else{
											status.setText("MaryZone: Slaptažodžiai nesutampa!");
										}
									}else{
										status.setText("MaryZone: Neįvedėte naujo slaptažodžio!");
									}
								}else{
									status.setText("MaryZone: Dabartinis slaptažodis neteisingas!");
								}
							}
						}
					
					set.close();
					power.close();
				}catch(Exception error){
					status.setText(config.getLoginc3());
				}finally{
					try{
						if(LaunchConnect1 != null){
							LaunchConnect1.close();
						}
					}catch(Exception error){
						status.setText(config.getLoginc3());
					}
				}
			}
		});
		func_changepw.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				func_changepw.setIcon(new ImageIcon(func_changepw_h));
			}
			public void mouseExited(MouseEvent e){
				func_changepw.setIcon(new ImageIcon(func_changepw_n));
			}
		});
		func_changepw.setBorderPainted(false);
		func_changepw.setIcon(new ImageIcon(func_changepw_n));
		func_changepw.setBounds(15, 130, 219, 24);
		contentPane.add(func_changepw);
		
		JButton func_cancel = new JButton("");
		func_cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				obj2.setVisible(true);
				dispose();
			}
		});
		func_cancel.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				func_cancel.setIcon(new ImageIcon(func_changepw_cancel_h));
			}
			public void mouseExited(MouseEvent e){
				func_cancel.setIcon(new ImageIcon(func_changepw_cancel_n));
			}
		});
		func_cancel.setBorderPainted(false);
		func_cancel.setIcon(new ImageIcon(func_changepw_cancel_n));
		func_cancel.setBorderPainted(false);
		func_cancel.setBounds(15, 160, 219, 24);
		contentPane.add(func_cancel);
		
		status = new JLabel("");
		status.setForeground(Color.WHITE);
		status.setFont(new Font("Tahoma", Font.PLAIN, 10));
		status.setHorizontalAlignment(SwingConstants.CENTER);
		status.setBounds(10, 193, 227, 14);
		contentPane.add(status);
		
		JLabel design_header = new JLabel("");
		Image header = new ImageIcon(this.getClass().getResource("/main_change/change_header.png")).getImage();
		design_header.setIcon(new ImageIcon(header));
		design_header.setBounds(0, 0, 250, 35);
		contentPane.add(design_header);
		
		JLabel design_main = new JLabel("");
		Image main = new ImageIcon(this.getClass().getResource("/main_change/change_main.png")).getImage();		
		design_main.setIcon(new ImageIcon(main));
		design_main.setBounds(0, 35, 250, 80);
		contentPane.add(design_main);
		
		JLabel design_bottom = new JLabel("");
		Image bottom = new ImageIcon(this.getClass().getResource("/main_change/change_bottom.png")).getImage();		
		design_bottom.setIcon(new ImageIcon(bottom));
		design_bottom.setBounds(0, 114, 250, 101);
		contentPane.add(design_bottom);
	}
}
