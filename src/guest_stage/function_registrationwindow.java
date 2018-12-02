package guest_stage;

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

import java.util.LinkedList;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import gui_renderers.textFieldLimit;

import engines.clientConfiguration;

import guest_stage.function_loginwindow;

@SuppressWarnings("serial")
public class function_registrationwindow extends JFrame {
	static function_loginwindow loginframe;
	
	LinkedList<String> userInfo = new LinkedList<String>();
	clientConfiguration config = new clientConfiguration();
	private int check = 0;
	private int match = -1;
	
	private JPanel contentPane;
	private JTextField add_login, add_question, add_answer;
	private JPasswordField add_password;
	private JPasswordField add_repeatpsw;
	private JLabel func_status;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					function_registrationwindow frame = new function_registrationwindow();
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
	public function_registrationwindow() {
		
		super("Registracija");
		Image favicon = new ImageIcon(this.getClass().getResource("/favicon.png")).getImage();
		setIconImage(favicon);
		
		// registrationwindow FORM
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 253, 286);
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
		
		JLabel des_registration = new JLabel("");
		Image reg = new ImageIcon(this.getClass().getResource("/reg_mdl/n/des_registration.png")).getImage();
		des_registration.setIcon(new ImageIcon(reg));
		des_registration.setBounds(130, 8, 105, 30);
		contentPane.add(des_registration);
		
		JLabel label1 = new JLabel("Slapyvardis:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label1.setBounds(14, 46, 72, 14);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("Slapta\u017Eodis:");
		label2.setForeground(Color.WHITE);
		label2.setBackground(Color.WHITE);
		label2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label2.setBounds(14, 72, 72, 14);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("Pakartokite:");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label3.setBounds(14, 99, 72, 14);
		contentPane.add(label3);
		
		JLabel label4 = new JLabel("Klausimas:");
		label4.setForeground(Color.WHITE);
		label4.setBackground(Color.WHITE);
		label4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label4.setBounds(14, 126, 72, 14);
		contentPane.add(label4);
		
		JLabel label5 = new JLabel("Atsakymas:");
		label5.setForeground(Color.WHITE);
		label5.setBackground(Color.WHITE);
		label5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label5.setBounds(14, 153, 72, 14);
		contentPane.add(label5);
		
		add_login = new JTextField(10);
		add_login.setBounds(92, 43, 135, 20);
		contentPane.add(add_login);
		add_login.setDocument(new textFieldLimit(10));
		add_login.setColumns(10);
		
		add_password = new JPasswordField();
		add_password.setBounds(92, 70, 135, 20);
		contentPane.add(add_password);
		
		add_repeatpsw = new JPasswordField();
		add_repeatpsw.setBounds(92, 97, 135, 20);
		contentPane.add(add_repeatpsw);
		
		add_question = new JTextField();
		add_question.setBounds(92, 124, 135, 20);
		contentPane.add(add_question);
		add_question.setColumns(10);
		
		add_answer = new JTextField();
		add_answer.setBounds(92, 151, 135, 20);
		contentPane.add(add_answer);
		add_answer.setColumns(10);
		
		JLabel design_header = new JLabel("");
		Image header = new ImageIcon(this.getClass().getResource("/main_registration/registration_header.png")).getImage();		
		
		JButton func_registration_2 = new JButton("");
		func_registration_2.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
				try{
					Connection firstConnect = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
					Statement firstReady = firstConnect.createStatement();
					ResultSet firstSet = firstReady.executeQuery("Select * from user_information");
					
					while(firstSet.next()){
						userInfo.add(check ,firstSet.getString("username"));
						
						if((add_login.getText()).equals(userInfo.get(check))){
							func_status.setText(config.getRegc1());
							match = 1;
						}
						check++;
					}
					
					firstReady.close();
					firstSet.close();
					
					if(match < 0 && !("").equals(add_login.getText()) && !("").equals(add_password.getText()) && add_password.getText().equals(add_repeatpsw.getText())
							&& !("").equals(add_question.getText()) && !("").equals(add_answer.getText())	
							){
						
						try{
							Connection connect = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
							Statement ready = connect.createStatement();
							ready.execute("Insert into user_information (username, password, question, answer, reputation, warnings) values"
									+ "('" + add_login.getText() + "', '" + add_password.getText() +"', '" + add_question.getText() + "', '" + add_answer.getText() + "',"
											+ "'0', '0')");
							ready.close();
	
							func_status.setText(config.getRegc2());
							loginframe = new function_loginwindow();
							loginframe.setVisible(true);
							setVisible(false);
							}catch(Exception error){
								error.printStackTrace();
								func_status.setText(config.getRegc3());
							}
						}else{
							match = -1;
							if(("").equals(add_login.getText())){
								func_status.setText("MaryZone: Ne užpildei slapyvardžio!");
							}else if(!(add_repeatpsw.getText()).equals(add_password.getText())){
								func_status.setText("MaryZone: Slaptažodis nesutampa!");
							}else if(("").equals(add_question.getText())){
								func_status.setText("MaryZone: Ne užpildei klausymo!");
							}else if(("").equals(add_answer.getText())){
								func_status.setText("MaryZone: Ne užpildei atsakymo!");
							}
						}	
				}catch(Exception error){
					error.printStackTrace();
					func_status.setText(config.getRegc3());
				}
			}
		});
		func_registration_2.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				Image image = new ImageIcon(this.getClass().getResource("/reg_mdl/h/func_registration_2_h.png")).getImage();
				func_registration_2.setIcon(new ImageIcon(image));
			}
			
			public void mouseExited(MouseEvent e){
				Image image = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_registration_2_n.png")).getImage();
				func_registration_2.setIcon(new ImageIcon(image));
			}
		});
		
		Image func_reg_2 = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_registration_2_n.png")).getImage();		
		func_registration_2.setIcon(new ImageIcon(func_reg_2));
		func_registration_2.setBorder(BorderFactory.createEmptyBorder());
		func_registration_2.setBounds(10, 180, 219, 24);
		contentPane.add(func_registration_2);
		
		JButton func_back_2 = new JButton("");
		Image func_back_2_n = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_back_2_n.png")).getImage();
		func_back_2.setIcon(new ImageIcon(func_back_2_n));
		func_back_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				loginframe = new function_loginwindow();
				loginframe.setVisible(true);
				dispose();
			}
		});
		func_back_2.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				Image image = new ImageIcon(this.getClass().getResource("/reg_mdl/h/func_back_2_h.png")).getImage();
				func_back_2.setIcon(new ImageIcon(image));
			}
			
			public void mouseExited(MouseEvent e){
				Image image = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_back_2_n.png")).getImage();
				func_back_2.setIcon(new ImageIcon(image));
			}
		});
		func_back_2.setBorder(BorderFactory.createEmptyBorder());
		func_back_2.setBounds(10, 210, 219, 24);
		contentPane.add(func_back_2);
		
		func_status = new JLabel("");
		func_status.setHorizontalAlignment(SwingConstants.CENTER);
		func_status.setForeground(Color.WHITE);
		func_status.setFont(new Font("Tahoma", Font.PLAIN, 10));
		func_status.setBounds(10, 238, 219, 14);
		contentPane.add(func_status);
		
		design_header.setIcon(new ImageIcon(header));
		design_header.setBounds(0, 0, 250, 35);
		contentPane.add(design_header);
		
		JLabel design_main = new JLabel("");
		Image main = new ImageIcon(this.getClass().getResource("/main_registration/registration_main.png")).getImage();		
		design_main.setIcon(new ImageIcon(main));
		design_main.setBounds(0, 35, 250, 80);
		contentPane.add(design_main);
		
		JLabel design_bottom = new JLabel("");
		Image bottom = new ImageIcon(this.getClass().getResource("/main_registration/registration_bottom.png")).getImage();		
		design_bottom.setIcon(new ImageIcon(bottom));
		design_bottom.setBounds(0, 114, 250, 144);
		contentPane.add(design_bottom);
	
	}
}
