package guest_stage;

import java.awt.Color;
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import engines.clientConfiguration;
import engines.infoUser;

import guest_stage.function_registrationwindow;
import guest_stage.function_rememberwindow;

import user_stage.function_mainwindow;

@SuppressWarnings("serial")
public class function_loginwindow extends JFrame {
	
	private JPanel contentPane;
	private JTextField check_login;
	private JPasswordField check_password;
	public JLabel func_status;
	
	static function_mainwindow main_frame;
	static function_registrationwindow registration_frame = new function_registrationwindow();
	static function_rememberwindow remember_frame = new function_rememberwindow();
	
	clientConfiguration config = new clientConfiguration();
	infoUser information;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					function_loginwindow frame = new function_loginwindow();
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
	public function_loginwindow() {
		
		super("Prisijungimas");
		Image favicon = new ImageIcon(this.getClass().getResource("/favicon.png")).getImage();
		setIconImage(favicon);

		// loginwindow FORM
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 253, 196);
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
		
		JLabel des_powered = new JLabel("");
		Image powered = new ImageIcon(this.getClass().getResource("/reg_mdl/n/des_powered.png")).getImage();
		des_powered.setIcon(new ImageIcon(powered));
		des_powered.setBounds(155, 5, 72, 30);
		contentPane.add(des_powered);
		
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
		
		JLabel func_remember = new JLabel("Pamir\u0161au Slapta\u017Eod\u012F!");
		func_remember.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				func_remember.setFont(new Font("Tahoma", Font.PLAIN, 12));
			}
			public void mouseExited(MouseEvent e){
				func_remember.setFont(new Font("Tahoma", Font.PLAIN, 11));
			}
			public void mouseClicked(MouseEvent e) {
				remember_frame.setVisible(true);
				setVisible(false);
			}
		});
		
		func_remember.setHorizontalAlignment(SwingConstants.RIGHT);
		func_remember.setFont(new Font("Tahoma", Font.PLAIN, 11));
		func_remember.setForeground(new Color(48,196,3));
		func_remember.setBounds(115, 97, 112, 14);
		contentPane.add(func_remember);
		
		check_login = new JTextField();
		check_login.setBounds(92, 43, 135, 20);
		contentPane.add(check_login);
		check_login.setColumns(10);
		
		JButton func_login = new JButton("");
		func_login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try{
					Connection connect = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass() );
					Statement prepare = connect.createStatement();
					ResultSet set = prepare.executeQuery("Select * from user_information");
					
					while(set.next()){
						if(check_login.getText().equals(set.getString("username")) && check_password.getText().equals(set.getString("password"))){
							information = new infoUser(
									set.getInt("id"),
									set.getString("username"),
									set.getString("question"),
									set.getString("answer"),
									set.getInt("reputation"),
									set.getInt("warnings")
							);
							
							try{
								Connection connect1 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
								Statement power1 = connect1.createStatement();
								power1.execute("Insert into application_statistics (application_connected) values ('" + config.getAppTime() + "')");
								power1.close();
								connect1.close();
							}catch(Exception error){
								error.printStackTrace();
							}
							
							main_frame = new function_mainwindow();
							main_frame.setInfoBridge(information);
							main_frame.setVisible(true);
							setVisible(false);
						}else if(check_login.getText().equals(set.getString("username"))){
							int syswarnings = set.getInt("warnings");
							syswarnings++;
							try{
								Connection connect1 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
								Statement power1 = connect1.createStatement();
								power1.execute("Update user_information set warnings = '" + syswarnings + "' where username = '" + check_login.getText() + "'");
								power1.close();
								connect1.close();
							}catch(Exception error){
								func_status.setText(config.getLoginc2());
							}
						}else{
							func_status.setText(config.getLoginc2());
						}
					}
					prepare.close();
					set.close();
					connect.close();
				}catch(Exception error){
					error.printStackTrace();
					func_status.setText(config.getLoginc4());
				}
			}
		});
		func_login.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				Image func_log_hover = new ImageIcon(this.getClass().getResource("/reg_mdl/h/func_login_h.png")).getImage();
				func_login.setIcon(new ImageIcon(func_log_hover));
			}
			public void mouseExited(MouseEvent e){
				Image func_log_normal = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_login_n.png")).getImage();
				func_login.setIcon(new ImageIcon(func_log_normal));
			}
		});
		Image func_log = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_login_n.png")).getImage();
		func_login.setIcon(new ImageIcon(func_log));
		func_login.setBorder(BorderFactory.createEmptyBorder());
		func_login.setBounds(10, 118, 101, 24);
		contentPane.add(func_login);
		
		JButton func_registration = new JButton("");
		func_registration.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				registration_frame.setVisible(true);
				setVisible(false);
			}
		});
		func_registration.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				Image func_registration_hover = new ImageIcon(this.getClass().getResource("/reg_mdl/h/func_registration_1_h.png")).getImage();
				func_registration.setIcon(new ImageIcon(func_registration_hover));
			}
			public void mouseExited(MouseEvent e){
				Image func_registration_normal = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_registration_1_n.png")).getImage();
				func_registration.setIcon(new ImageIcon(func_registration_normal));
			}
		});
		
		Image func_reg = new ImageIcon(this.getClass().getResource("/reg_mdl/n/func_registration_1_n.png")).getImage();
		
		check_password = new JPasswordField();
		check_password.setBounds(92, 70, 135, 20);
		contentPane.add(check_password);
		func_registration.setIcon(new ImageIcon(func_reg));
		func_registration.setBorder(BorderFactory.createEmptyBorder());
		func_registration.setBounds(115, 118, 112, 24);
		contentPane.add(func_registration);
		
		func_status = new JLabel(config.getLoginc1());
		func_status.setHorizontalAlignment(SwingConstants.CENTER);
		func_status.setForeground(Color.WHITE);
		func_status.setFont(new Font("Tahoma", Font.PLAIN, 10));
		func_status.setBounds(10, 146, 217, 14);
		contentPane.add(func_status);
		
		JLabel design_header = new JLabel("");
		Image header = new ImageIcon(this.getClass().getResource("/main_login/login_header.png")).getImage();		
		design_header.setIcon(new ImageIcon(header));
		design_header.setBounds(0, 0, 250, 35);
		contentPane.add(design_header);
		
		JLabel design_main = new JLabel("");
		Image main = new ImageIcon(this.getClass().getResource("/main_login/login_main.png")).getImage();
		design_main.setIcon(new ImageIcon(main));
		design_main.setBounds(0, 35, 250, 80);
		contentPane.add(design_main);
		
		JLabel design_bottom = new JLabel("");
		Image bottom = new ImageIcon(this.getClass().getResource("/main_login/login_bottom.png")).getImage();
		design_bottom.setIcon(new ImageIcon(bottom));
		design_bottom.setBounds(0, 115, 250, 54);
		contentPane.add(design_bottom);
	}
}
