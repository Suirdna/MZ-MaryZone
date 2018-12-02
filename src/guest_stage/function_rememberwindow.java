package guest_stage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import engines.clientConfiguration;

import guest_stage.function_loginwindow;

@SuppressWarnings("serial")
public class function_rememberwindow extends JFrame {

	static function_rememberwindow remember_frame = new function_rememberwindow();
	static function_loginwindow login_frame = new function_loginwindow();
	clientConfiguration config = new clientConfiguration();
	
	private int check = -1;
	
	private JPanel contentPane;
	private JTextField field_question, field_answer;
	private JTextField field_username;
	JLabel status_check;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					function_rememberwindow frame = new function_rememberwindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public function_rememberwindow() {
		
		super("Apsirūkęs?");
		Image favicon = new ImageIcon(this.getClass().getResource("/favicon.png")).getImage();
		setIconImage(favicon);
		
		// remember FORM
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 251, 243);
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
		
		JLabel des_question = new JLabel("");
		Image question = new ImageIcon(this.getClass().getResource("/reg_mdl/n/des_remember.png")).getImage();
		des_question.setIcon(new ImageIcon(question));
		des_question.setBounds(140, 9, 90, 30);
		contentPane.add(des_question);
		
		JLabel label0 = new JLabel("Slapyvardis:");
		label0.setForeground(Color.WHITE);
		label0.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label0.setBounds(14, 51, 72, 14);
		contentPane.add(label0);
		
		JLabel label1 = new JLabel("Klausimas:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label1.setBounds(14, 76, 72, 14);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("Atsakymas:");
		label2.setForeground(Color.WHITE);
		label2.setBackground(Color.WHITE);
		label2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label2.setBounds(14, 102, 72, 14);
		contentPane.add(label2);
		
		field_username = new JTextField();
		field_username.setBounds(92, 46, 135, 20);
		contentPane.add(field_username);
		field_username.setColumns(10);
		
		field_question = new JTextField();
		field_question.setBounds(92, 73, 135, 20);
		field_question.setEditable(false);
		contentPane.add(field_question);
		field_question.setColumns(10);
		
		field_answer = new JTextField();
		field_answer.setBounds(92, 100, 135, 20);
		field_answer.setEditable(false);
		contentPane.add(field_answer);
		field_answer.setColumns(10);
		
		JButton func_cancel = new JButton("");
		func_cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				login_frame.setVisible(true);
				setVisible(false);
			}
		});
		func_cancel.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				Image h = new ImageIcon(this.getClass().getResource("/reg_mdl/h/func_cancel_h.png")).getImage();
				func_cancel.setIcon(new ImageIcon(h));
			}
			public void mouseExited(MouseEvent e){
				Image n = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_cancel_n.png")).getImage();
				func_cancel.setIcon(new ImageIcon(n));
			}
		});
		Image func_can = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_cancel_n.png")).getImage();
		func_cancel.setIcon(new ImageIcon(func_can));
		func_cancel.setBorder(BorderFactory.createEmptyBorder());
		func_cancel.setBounds(10, 135, 101, 24);
		contentPane.add(func_cancel);
		
		JButton func_close = new JButton("");
		func_close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		func_close.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				Image h = new ImageIcon(this.getClass().getResource("/reg_mdl/h/func_close_h.png")).getImage();
				func_close.setIcon(new ImageIcon(h));
			}
			
			public void mouseExited(MouseEvent e){
				Image n = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_close_n.png")).getImage();
				func_close.setIcon(new ImageIcon(n));
			}
		});
		Image func_clo = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_close_n.png")).getImage();
		func_close.setIcon(new ImageIcon(func_clo));
		func_close.setBorder(BorderFactory.createEmptyBorder());
		func_close.setBounds(119, 135, 112, 24);
		contentPane.add(func_close);
		
		JButton func_check = new JButton("");
		func_check.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					Connection firstConnect = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
					Statement firstReady = firstConnect.createStatement();
					ResultSet firstSet = firstReady.executeQuery("Select username, question from user_information");
					
					while(firstSet.next()){
						if((field_username.getText()).equals(firstSet.getString("username"))){
							status_check.setText(config.getRemc1());
							field_username.setEditable(false);
							field_answer.setEditable(true);
							field_question.setText(firstSet.getString("question"));
							Image h2 = new ImageIcon(this.getClass().getResource("/reg_mdl/s/func_check_s.png")).getImage();
							func_check.setIcon(new ImageIcon(h2));
							
							func_check.addMouseListener(new MouseAdapter(){
								public void mouseClicked(MouseEvent b){
									try{
										Connection secondConnect = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
										Statement secondReady = secondConnect.createStatement();
										ResultSet secondSet = secondReady.executeQuery("Select username, password, question, answer from user_information");
										
										while(secondSet.next()){
											if((field_username.getText()).equals(secondSet.getString("username")) && (field_answer.getText()).equals(secondSet.getString("answer"))){
												status_check.setText("MaryZone: Jūsų slaptažodis: " + secondSet.getString("password"));
											}else{
												if(check > 0){
													status_check.setText(config.getRemc3());
													check++;
												}else{
													
												}
											}
										}
										secondReady.close();
										secondSet.close();
									}catch(Exception error){
										error.printStackTrace();
										status_check.setText(config.getRemc4());
									}
								}
							});
									
							func_check.addMouseListener(new MouseAdapter(){
								public void mouseEntered(MouseEvent e){
									Image sh = new ImageIcon(this.getClass().getResource("/reg_mdl/sh/func_check_sh.png")).getImage();
									func_check.setIcon(new ImageIcon(sh));
								}
								public void mouseExited(MouseEvent e){
									Image s = new ImageIcon(this.getClass().getResource("/reg_mdl/s/func_check_s.png")).getImage();
									func_check.setIcon(new ImageIcon(s));
								}
							});
						}else{
							status_check.setText(config.getRemc2());
						}
					}
					
					firstReady.close();
					firstSet.close();
				}catch(Exception error){
					status_check.setText(config.getRemc4());
				}
			}
		});
		func_check.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				Image h = new ImageIcon(this.getClass().getResource("/reg_mdl/h/func_check_h.png")).getImage();
				func_check.setIcon(new ImageIcon(h));
			}
			public void mouseExited(MouseEvent e){
				Image n = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_check_n.png")).getImage();
				func_check.setIcon(new ImageIcon(n));
			}
		});
		Image func_che = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_check_n.png")).getImage();
		func_check.setIcon(new ImageIcon(func_che));
		func_check.setBorder(BorderFactory.createEmptyBorder());
		func_check.setBounds(10, 165, 221, 24);
		contentPane.add(func_check);
		
		status_check = new JLabel("");
		status_check.setFont(new Font("Tahoma", Font.PLAIN, 10));
		status_check.setHorizontalAlignment(SwingConstants.CENTER);
		status_check.setForeground(Color.WHITE);
		status_check.setBounds(10, 193, 220, 14);
		contentPane.add(status_check);
		

		JLabel design_header = new JLabel("");
		Image header = new ImageIcon(this.getClass().getResource("/main_remember/remember_header.png")).getImage();		
		design_header.setIcon(new ImageIcon(header));
		design_header.setBounds(0, 0, 250, 35);
		contentPane.add(design_header);
		
		JLabel design_main = new JLabel("");
		Image main = new ImageIcon(this.getClass().getResource("/main_remember/remember_main.png")).getImage();
		design_main.setIcon(new ImageIcon(main));
		design_main.setBounds(0, 35, 250, 110);
		contentPane.add(design_main);
		
		JLabel design_bottom = new JLabel("");
		Image bottom = new ImageIcon(this.getClass().getResource("/main_remember/remember_bottom.png")).getImage();
		design_bottom.setIcon(new ImageIcon(bottom));
		design_bottom.setBounds(0, 145, 250, 74);
		contentPane.add(design_bottom);
	}
}
