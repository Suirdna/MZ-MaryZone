package user_stage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import adv_tableModel.adv_tableModel;
import adv_tableModel.adv_renderer;
import add_tableModel.add_tableModel;
import add_tableModel.add_tableRenderer;
import add_tableModel.add_cellRenderer;
import adcm_tableModel.adcm_tableModel;
import adcm_tableModel.adcm_renderer;

import engines.clientConfiguration;
import engines.guiConfigurations;
import engines.infoUser;
import engines.infoAdvertisiment;
import engines.infoAdvertisimentMessageConfirm;
import engines.infoAdd;
import engines.infoAdd.Status;

import guest_stage.function_loginwindow;

import gui_renderers.comboBoxCountryRenderer;
import gui_renderers.comboBoxCityLithuaniaRenderer;

@SuppressWarnings("serial")
public class function_mainwindow extends JFrame{
	
	private JTable table;
	private JPanel contentPane;
	private JLabel design_header, design_main, design_bottom, user_news, user_advertisiment, user_request, user_profile, user_logout, logo;
	private JPanel engine_news, engine_add, engine_answer, engine_profile, engine_messages;
	private JLabel func_messages;
	private JLabel func_password_c;
	private JLabel func_warnings;
	private JLabel mysql_status;
	private JComboBox<String> function_country, function_city;
	public JComboBox<Integer> function_price, function_weight;
	private JTable table_1;
	private JScrollPane scrollPane_1;
	private JTable table_2;
	private JLabel FirstRunBackground;
	private JLabel logo_panel_image;
	private JLabel value;
	private JLabel username_panel_text;
	private JLabel reputation_panel_text;
	private JTable table_3;
	private JTable table_4;
	private JLabel func_accept;
	private JLabel func_reject;;
	JScrollPane scrollPane;

	// Application Main Engine
	clientConfiguration config = new clientConfiguration();
	guiConfigurations gC;
	infoAdvertisiment adv_engine;
	
	// Application Frames
	static function_mainwindow main_frame = new function_mainwindow();
	function_loginwindow login_frame;
	function_changepw changepw_frame;
	
	// User Information
	protected infoUser userInfo;
	private final String status = "on_sale";
	
	// Advertisiment table
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
	
	@SuppressWarnings("rawtypes")
	List list, list2;
	
	// ChechDataMecha values
	
	private int timeVariable = 0;
	private Integer messageValue = 0;
	private String colorName;
	
	private boolean check = true;
	private boolean check2 = true;
	private int effect1 = 0;
	private int maxConnected = 0;
	private JTextField content;
	private JScrollPane scrollPane_4;
	private JLabel func_send;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try{
					
				}catch(Exception error){
					error.printStackTrace();
				}
			}
		});
	}
	
	
	public void getMaxConnectedUsers(){
		Thread power = new Thread(){
			public void run(){
				while(true){
					try{
						LaunchConnect9 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
						Statement power = LaunchConnect9.createStatement();
						ResultSet set = power.executeQuery("Select * from application_statistics");
							while(set.next()){
								if((set.getString("application_connected")).equals(config.getAppTime())){
									maxConnected++;
								}
							}	
						set.close();
						power.close();
					}catch(Exception error){
						error.printStackTrace();
					}finally{
						try{
							mysql_status.setText("<html>Š.Prisijungė: <font color = 'rgb(94,255,67'>" + maxConnected + "</font></html>");
							maxConnected = 0;
							if(LaunchConnect9 != null){
								LaunchConnect9.close();
							}	
							sleep(3000);
						}catch(Exception error){
							error.printStackTrace();
						}
					}
				}
			}
		};
		power.start();
	}
	
	public void setInfoBridge(infoUser obj1){
		userInfo = obj1;
	}
	
	public void runReport(){
		Thread power = new Thread(){
			public void run(){
				while(true){
					if(userInfo.getUserWarnings() >= 3){
						if(effect1 == 0){
							Image image = new ImageIcon(this.getClass().getResource("/user_warnings_h.png")).getImage();
							func_warnings.setIcon(new ImageIcon(image));
							effect1 = 1;
						}else{
							Image image = new ImageIcon(this.getClass().getResource("/user_warnings_s.png")).getImage();
							func_warnings.setIcon(new ImageIcon(image));
							effect1 = 0;
						}
					}else{
						if(userInfo.getUserWarnings() == 1){
							try{
								LaunchConnect10 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
								Statement power = LaunchConnect10.createStatement();
								power.execute("Update user_information set warnings = '0' where id = '" + userInfo.getUserId() + "'");
								power.close();
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
						}else if(userInfo.getUserWarnings() == 2){
							try{
								LaunchConnect10 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
								Statement power = LaunchConnect10.createStatement();
								power.execute("Update user_information set warnings = '0' where id = '" + userInfo.getUserId() + "'");
								power.close();
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
						}
					}
				
					try{
						sleep(1000);
					}catch(Exception error){
						error.printStackTrace();
					}
				}
			}
		};
		power.start();
	}
	
	public void runRBridge(){
		int id = userInfo.getUserId();
		Thread power = new Thread(){
			public void run(){
				while(true){
					try{
						if(messageValue != null){
							messageValue = 0;
						}
						
						LaunchConnect6 = DriverManager.getConnection(config.getServerName(),config.getServerUser(),config.getServerPass());
						Statement power = LaunchConnect6.createStatement();
						ResultSet set = power.executeQuery("Select * from global_advertisiment");
						
						while(set.next()){
							if(set.getInt("adv_add_idd") == id){
								if((set.getString("adv_get_signal")).equals("trueSignal")){
									messageValue++;
								}
							}
						}
						
						if(messageValue == 0){
							Image user_request_n = new ImageIcon(this.getClass().getResource("/menu_mdl/n/user_3_request_n.png")).getImage();
							user_request.setIcon(new ImageIcon(user_request_n));
							user_request.setBounds(103,5,45,46);
							value.setText("");
							getUReq();
						}else{
							Image user_func_request_n = new ImageIcon(this.getClass().getResource("/menu_mdl/n/user_3_func_request_n.png")).getImage();
							user_request.setIcon(new ImageIcon(user_func_request_n));
							user_request.setBounds(105,5,45,46);
							value.setText(messageValue.toString());
							getUReq();
						}
						
						set.close();
						power.close();
						
					}catch(Exception error){
						error.printStackTrace();
					}finally{
						try{
							if(LaunchConnect6 != null){
								LaunchConnect6.close();
							}
							sleep(3000);
						}catch(Exception error){
							error.printStackTrace();
						}
					}
				}
			}
		};
		power.start();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getUReq(){
	
		list2 = new ArrayList();
		
		try{
			LaunchConnect5 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
			Statement power = LaunchConnect5.createStatement();
			ResultSet set = power.executeQuery("Select * from global_advertisiment");
			
				while(set.next()){
					if(set.getInt("adv_add_idd") == userInfo.getUserId()){
						if(set.getString("adv_get_signal").equals("trueSignal"))
						list2.add(new infoAdvertisimentMessageConfirm(set.getInt("id"), set.getInt("adv_add_idd"), set.getInt("adv_get_idd"), set.getString("adv_get_username"), set.getInt("adv_weight"), set.getString("adv_country"), set.getString("adv_city"), set.getInt("adv_year"), set.getInt("adv_month"), set.getInt("adv_day"), set.getString("adv_add_signal"), set.getString("adv_get_signal"), set.getInt("adv_global_signal") ));
					}
				}
			
			table_2.setModel(new adcm_tableModel(list2));
			table_2.setDefaultEditor(infoAdvertisimentMessageConfirm.class, new adcm_renderer(userInfo, adv_engine));
			table_2.setDefaultRenderer(infoAdvertisimentMessageConfirm.class, new adcm_renderer(userInfo, adv_engine));
			table_2.setRowHeight(50);
		
			set.close();
			power.close();
			
		}catch(Exception error){
			error.printStackTrace();
		}finally{
			try{
				if(LaunchConnect5 != null){
					LaunchConnect5.close();
				}
			}catch(Exception error){
				error.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void runUAdv(){
		@SuppressWarnings("rawtypes")
		List a = new ArrayList();
		
		try{
			LaunchConnect3 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
			Statement power = LaunchConnect3.createStatement();
			ResultSet set = power.executeQuery("Select * from global_advertisiment");
				while(set.next()){
					if(set.getInt("adv_add_idd") == userInfo.getUserId()){
							a.add(new infoAdd(set.getInt("adv_add_idd"),set.getInt("adv_year"),set.getInt("adv_month"), set.getInt("adv_day"), set.getString("adv_city"), set.getInt("adv_weight"), Status.valueOf(set.getString("adv_status"))));
					}
				}
			table_1.setModel(new add_tableModel(a));
			table_1.setDefaultRenderer(Status.class, new add_tableRenderer());
			for(int x = 0; x < 3; x++){
				table_1.getColumnModel().getColumn(x).setCellRenderer(new add_cellRenderer());
			}
			table_1.getColumnModel().getColumn(0).setMinWidth(80);
			table_1.getColumnModel().getColumn(1).setMinWidth(70);
			set.close();
			power.close();
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
		if(gC == null){
			gC = new guiConfigurations();
			gC.autoGuiComboBox1(function_price);
			gC.autoGuiComboBox2(function_weight);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteAvdData(){
		ListIterator<infoAdvertisiment> object = list.listIterator();
		while(object.hasNext()){
			if(object.next() != null){
				object.remove();
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void runGAdv(){
		
		// Advertisiment Frame
		
		if(function_country.getSelectedItem() == null){
			for(int i = 0; i < config.getAllCountrys(); i++){
				function_country.addItem(config.getAllCountrys(i));
			}
		}
		
		function_country.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					int itemIndex = function_country.getSelectedIndex();
					changeCountry(itemIndex);
			}
		});
		function_country.setRenderer(new comboBoxCountryRenderer());
		
		try{
			LaunchConnect1 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
			Statement ready = LaunchConnect1.createStatement();
			ResultSet set = ready.executeQuery("SELECT * FROM global_advertisiment");	
			
			list = new ArrayList();
						
			if(list.size() == 0){
				while(set.next()){
					if((set.getString("adv_status")).equals("on_sale")){
						list.add(new infoAdvertisiment(set.getInt("id"),set.getString("adv_add_username"), set.getInt("adv_reputation"), set.getInt("adv_weight"), set.getInt("adv_price"), set.getString("adv_city")));
					}
				}	
			}
			
			table.setModel(new adv_tableModel(list));
			table.setDefaultRenderer(infoAdvertisiment.class, new adv_renderer(userInfo));
			table.setDefaultEditor(infoAdvertisiment.class, new adv_renderer(userInfo));
			table.setRowHeight(90);
			
			set.close();
			ready.close();
		}catch(Exception error){
			error.printStackTrace();
		}finally{
			if(LaunchConnect1 != null){
				try{
					LaunchConnect1.close();
				}catch(Exception error){
					error.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getData(String obj1, String obj2){
		
		for(int i = 0; i < list.size(); i++){
			list.remove(i);
		}
		
		try{
			LaunchConnect2 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
			Statement power = LaunchConnect2.createStatement();
			ResultSet set = power.executeQuery("Select * from global_advertisiment");
			
				while(set.next()){
					if((set.getString("adv_country")).equals(obj1)){
						if((set.getString("adv_city")).equals(obj2) ){
							if((set.getString("adv_status")).equals("on_sale")){
								list.add(new infoAdvertisiment(set.getInt("id"), set.getString("adv_add_username"), set.getInt("adv_reputation"), set.getInt("adv_weight"), set.getInt("adv_price"), set.getString("adv_city")));
							}
						}
					}
				}
			
			table.setModel(new adv_tableModel(list));
			table.setDefaultEditor(infoAdvertisiment.class, new adv_renderer(userInfo));
			table.setDefaultRenderer(infoAdvertisiment.class, new adv_renderer(userInfo));	
				
			set.close();
			power.close();
			
		}catch(Exception error){
			error.printStackTrace();
		}finally{
			if(LaunchConnect2 != null){
				try{
					LaunchConnect2.close();
				}catch(Exception error){
					error.printStackTrace();
				}
			}
		}
		
	}
	
	public void getIndex(){
		check = true;
		check2 = true;
			while(check){
				if((function_country.getSelectedItem()).equals("Lietuva")){
					check = false;
					while(check2){
						try{
							for(int i = 0; i < config.getCityLithuaniaCount(); i++){
								if((function_city.getSelectedItem()).equals(config.getCityLithuania(i))){
									String country = (String) function_country.getSelectedItem();
									String city = (String) function_city.getSelectedItem();
									getData(country, city);
									check2 = false;
								}
							}
						}catch(Exception error){
									
						}
					}
				}
			}
	}
	
	
	/*
	 * ComboBox 
	 * */
	
	@SuppressWarnings("unchecked")
	public void changeCountry(int obj1){
		if(obj1 == 0){
			deleteAvdData();
			if(function_city.getSelectedIndex() == -1){
				for(int i = 0; i < config.getCityLithuaniaCount(); i++){
					function_city.addItem(config.getCityLithuania(i));
				}
						
				function_city.setRenderer(new comboBoxCityLithuaniaRenderer());
					
				if(function_city.getSelectedIndex() == 0){
					function_city.addItemListener(new ItemListener(){
						public void itemStateChanged(ItemEvent e){
							if(function_city.getSelectedItem() != null){
								deleteAvdData();
								getIndex();
							}
						}
					});
				}
			}		
		}
	}
	
	/**
	 * Create the application.
	 */

	public function_mainwindow() {
		super("MaryZone 0.1");
		setResizable(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {

		Image favicon = new ImageIcon(this.getClass().getResource("/favicon.png")).getImage();
		setIconImage(favicon);
		
		Image function_add_n = new ImageIcon(this.getClass().getResource("/main_mdl/n/func_add_1_n.png")).getImage();
		Image function_delete_n = new ImageIcon(this.getClass().getResource("/main_mdl/n/func_delete_2_n.png")).getImage();
		Image user_news_n = new ImageIcon(this.getClass().getResource("/menu_mdl/n/user_1_news_n.png")).getImage();
		Image user_advertisiment_n = new ImageIcon(this.getClass().getResource("/menu_mdl/n/user_2_advertisiment_n.png")).getImage();
		Image user_request_n = new ImageIcon(this.getClass().getResource("/menu_mdl/n/user_3_request_n.png")).getImage();
		//Image user_func_request_n = new ImageIcon(this.getClass().getResource("../menu_mdl/n/user_3_func_request_n.png")).getImage();
		Image user_profile_n = new ImageIcon(this.getClass().getResource("/menu_mdl/n/user_4_profile_n.png")).getImage();
		Image user_logout_n = new ImageIcon(this.getClass().getResource("/menu_mdl/n/user_5_logout_n.png")).getImage();
		Image header = new ImageIcon(this.getClass().getResource("/main_3_window/three_header.png")).getImage();
		Image main = new ImageIcon(this.getClass().getResource("/main_3_window/three_main.png")).getImage();
		Image bc0 = new ImageIcon(this.getClass().getResource("/main_3_window/three_main.png")).getImage();
		Image bc1 = new ImageIcon(this.getClass().getResource("/main_3_window/three_main.png")).getImage();
		Image bc2 = new ImageIcon(this.getClass().getResource("/main_3_window/three_main.png")).getImage();
		Image mbc = new ImageIcon(this.getClass().getResource("/main_message_window/messagewindow_main.png")).getImage();
		Image fm = new ImageIcon(this.getClass().getResource("/user_messages.png")).getImage();
		Image fw = new ImageIcon(this.getClass().getResource("/user_warnings.png")).getImage();
		Image fpc = new ImageIcon(this.getClass().getResource("/user_password_c.png")).getImage();
		Image bottom = new ImageIcon(this.getClass().getResource("/main_3_window/three_bottom.png")).getImage();
		Image background = new ImageIcon(this.getClass().getResource("/background.png")).getImage();
		Image logo_panel_image_n = new ImageIcon(this.getClass().getResource("/logo_panel_image.png")).getImage();
		Image fm_h = new ImageIcon(this.getClass().getResource("/user_messages_h.png")).getImage();
		Image fw_h = new ImageIcon(this.getClass().getResource("/user_warnings_h.png")).getImage();
		Image fpc_h = new ImageIcon(this.getClass().getResource("/user_password_c_h.png")).getImage();
		Image fc_accept_n = new ImageIcon(this.getClass().getResource("/main_mdl/n/func_accept_n.png")).getImage();
		Image fc_accept_h = new ImageIcon(this.getClass().getResource("/main_mdl/h/func_accept_h.png")).getImage();
		Image fc_reject_n = new ImageIcon(this.getClass().getResource("/main_mdl/n/func_reject_n.png")).getImage();
		Image fc_reject_h = new ImageIcon(this.getClass().getResource("/main_mdl/h/func_reject_h.png")).getImage();
		Image fc_sendM_n = new ImageIcon(this.getClass().getResource("/user_write_n.png")).getImage();
		Image fc_sendM_h = new ImageIcon(this.getClass().getResource("/user_write_h.png")).getImage();
		
		setBounds(0, 0, 255, 406);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Menu ------------------------------------------------------------------------------------------------------------------------------------
		// User NEW New ----------------------------------------------------------------------------------------------------------------------------
		
		user_news = new JLabel("");
		user_news.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				engine_news.setVisible(true);
				engine_add.setVisible(false);
				engine_answer.setVisible(false);
				engine_profile.setVisible(false);
				engine_messages.setVisible(false);
				runGAdv();
			}	
			public void mouseEntered(MouseEvent arg0) {
				Image user_news_h = new ImageIcon(this.getClass().getResource("/menu_mdl/h/user_1_news_h.png")).getImage();
				user_news.setIcon(new ImageIcon(user_news_h));
			}
			public void mouseExited(MouseEvent e) {
				Image user_news_n = new ImageIcon(this.getClass().getResource("/menu_mdl/n/user_1_news_n.png")).getImage();
				user_news.setIcon(new ImageIcon(user_news_n));
			}
		});
		
		// User NEW End ----------------------------------------------------------------------------------------------------------------------------
		// User ADV Adv  ---------------------------------------------------------------------------------------------------------------------------
		
		user_advertisiment = new JLabel("");
		user_advertisiment.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				engine_news.setVisible(false);
				engine_add.setVisible(true);
				engine_answer.setVisible(false);
				engine_profile.setVisible(false);
				engine_messages.setVisible(false);
				runUAdv();
			}
			public void mouseEntered(MouseEvent e) {
				Image user_advertisiment_h = new ImageIcon(this.getClass().getResource("/menu_mdl/h/user_2_advertisiment_h.png")).getImage();
				user_advertisiment.setIcon(new ImageIcon(user_advertisiment_h));
			}
			public void mouseExited(MouseEvent e) {
				Image user_advertisiment_n = new ImageIcon(this.getClass().getResource("/menu_mdl/n/user_2_advertisiment_n.png")).getImage();
				user_advertisiment.setIcon(new ImageIcon(user_advertisiment_n));
			}
		});
		
		FirstRunBackground = new JLabel();
		FirstRunBackground.setIcon(new ImageIcon(background));
		FirstRunBackground.setBounds(0, 0, 250, 376);
		contentPane.add(FirstRunBackground);

		user_advertisiment.setIcon(new ImageIcon(user_advertisiment_n));
		user_advertisiment.setBounds(60, 11, 40, 40);
		contentPane.add(user_advertisiment);
		user_news.setIcon(new ImageIcon(user_news_n));
		user_news.setBounds(15, 11, 40, 40);
		contentPane.add(user_news);
		
		// User ADV End  ---------------------------------------------------------------------------------------------------------------------------
		// User REQ Req  ---------------------------------------------------------------------------------------------------------------------------
		
		user_request = new JLabel("");
		user_request.setVerticalAlignment(SwingConstants.BOTTOM);
		user_request.setHorizontalAlignment(SwingConstants.CENTER);
		user_request.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				engine_news.setVisible(false);
				engine_add.setVisible(false);
				engine_answer.setVisible(true);
				engine_profile.setVisible(false);
				engine_messages.setVisible(false);
				getUReq();
			}
			public void mouseEntered(MouseEvent e) {
				if(messageValue == 0){
					Image user_request_h = new ImageIcon(this.getClass().getResource("/menu_mdl/h/user_3_request_h.png")).getImage();
					user_request.setIcon(new ImageIcon(user_request_h));
					user_request.setBounds(105,5,45,46);
				}else{
					Image user_func_request_h = new ImageIcon(this.getClass().getResource("/menu_mdl/h/user_3_func_request_h.png")).getImage();
					user_request.setIcon(new ImageIcon(user_func_request_h));
					user_request.setBounds(105,5,45,46);
					value.setText(messageValue.toString());
				}
			}
			public void mouseExited(MouseEvent e) {
				if(messageValue == 0){
					Image user_request_n = new ImageIcon(this.getClass().getResource("/menu_mdl/n/user_3_request_n.png")).getImage();
					user_request.setIcon(new ImageIcon(user_request_n));
					user_request.setBounds(103,5,45,46);
				}else{
					Image user_func_request_n = new ImageIcon(this.getClass().getResource("/menu_mdl/n/user_3_func_request_n.png")).getImage();
					user_request.setIcon(new ImageIcon(user_func_request_n));
					user_request.setBounds(105,5,45,46);
					value.setText(messageValue.toString());
				}
			}
		});
		
		value = new JLabel();
		value.setFont(new Font("Arial", Font.BOLD, 9));
		value.setForeground(Color.WHITE);
		value.setHorizontalAlignment(SwingConstants.CENTER);
		value.setBounds(129, 10, 19, 14);
		contentPane.add(value);
		user_request.setIcon(new ImageIcon(user_request_n));
		user_request.setBounds(103, 5, 45, 46);
		contentPane.add(user_request);
		
		// User REQ End  ---------------------------------------------------------------------------------------------------------------------------
		// User PRO Pro  ---------------------------------------------------------------------------------------------------------------------------
		
		user_profile = new JLabel("");
		user_profile.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				engine_news.setVisible(false);
				engine_add.setVisible(false);
				engine_answer.setVisible(false);
				engine_profile.setVisible(true);
				engine_messages.setVisible(false);
				
					if(userInfo.getUserReputation() <= 10){
						colorName = "RED";
					}else if(userInfo.getUserReputation() > 10 && userInfo.getUserReputation() <= 30 ){
						colorName = "ORANGE";
					}else if(userInfo.getUserReputation() > 30 && userInfo.getUserReputation() <= 50 ){
						colorName = "GREEN";
					}else if(userInfo.getUserReputation() > 50 && userInfo.getUserReputation() <= 100){
						colorName = "BLUE";
					}else if(userInfo.getUserReputation() > 100){
						colorName = "PURPLE";
					}
				
				username_panel_text.setText("<html><font color = 'rgb(18,54,55)'>Vartotojas: </font><font color = 'rgb(123,0,128)'><font face = 'Comic Sans MS'><font size = '3'>" + userInfo.getUserUsername() + "</font></font></font></html>");
				reputation_panel_text.setText("<html><font color = 'rgb(18,54,55)'>Reputacija: </font><font color = '" + colorName + "'>" + userInfo.getUserReputation() + "</font></html>");
			}
			public void mouseEntered(MouseEvent e) {
				Image user_profile_h = new ImageIcon(this.getClass().getResource("/menu_mdl/h/user_4_profile_h.png")).getImage();
				user_profile.setIcon(new ImageIcon(user_profile_h));
			}
			public void mouseExited(MouseEvent e) {
				Image user_profile_n = new ImageIcon(this.getClass().getResource("/menu_mdl/n/user_4_profile_n.png")).getImage();
				user_profile.setIcon(new ImageIcon(user_profile_n));
			}
		});
		user_profile.setIcon(new ImageIcon(user_profile_n));
		user_profile.setBounds(150, 11, 40, 40);
		contentPane.add(user_profile);
		
		// User PRO End  ---------------------------------------------------------------------------------------------------------------------------
		// User LOG Log  ---------------------------------------------------------------------------------------------------------------------------

		user_logout = new JLabel("");
		user_logout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				login_frame = new function_loginwindow();
				login_frame.setVisible(true);
				dispose();
			}
			public void mouseEntered(MouseEvent e) {
				Image user_logout_h = new ImageIcon(this.getClass().getResource("/menu_mdl/h/user_5_logout_h.png")).getImage();
				user_logout.setIcon(new ImageIcon(user_logout_h));
				user_logout.setBounds(195, 12, 40, 40);
			}
			public void mouseExited(MouseEvent e) {
				Image user_logout_n = new ImageIcon(this.getClass().getResource("/menu_mdl/n/user_5_logout_n.png")).getImage();
				user_logout.setIcon(new ImageIcon(user_logout_n));
				user_logout.setBounds(195, 11, 40, 40);
			}
		});
		user_logout.setIcon(new ImageIcon(user_logout_n));
		user_logout.setBounds(195, 11, 40, 40);
		contentPane.add(user_logout);
		
		// User LOG End  ---------------------------------------------------------------------------------------------------------------------------
		
		function_country = new JComboBox<String>();
		function_country.setBackground(Color.WHITE);
		function_country.setVisible(false);
		function_country.setBounds(15, 59, 220, 20);
		function_country.setMaximumRowCount(4);
		contentPane.add(function_country);
		
		function_city = new JComboBox<String>();
		function_city.setBackground(Color.WHITE);
		function_city.setVisible(false);
		function_city.setBounds(15, 87, 220, 20);
		contentPane.add(function_city);
		
				// Back HED Hed  ---------------------------------------------------------------------------------------------------------------------------
				
				design_header = new JLabel("");
				design_header.setIcon(new ImageIcon(header));
				design_header.setBounds(0, 0, 251, 115);
				contentPane.add(design_header);
		
		// Back MAI End  ---------------------------------------------------------------------------------------------------------------------------
		// Back BOT Bot  ---------------------------------------------------------------------------------------------------------------------------
		
		// Back BOT End  ---------------------------------------------------------------------------------------------------------------------------
		// ------->> Constructor
		
		engine_news = new JPanel();
		engine_news.setBounds(0, 115, 249, 234);
		contentPane.add(engine_news);
		engine_news.setBackground(Color.WHITE);
		engine_news.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 251, 234);
		engine_news.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(80);
		scrollPane.setViewportView(table);
		
		engine_add = new JPanel();
		engine_add.setBounds(0, 115, 249, 234);
		contentPane.add(engine_add);
		engine_add.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 59, 220, 104);
		engine_add.add(scrollPane_1);
		
		table_1 = new JTable();		
		scrollPane_1.setViewportView(table_1);
		
		function_price = new JComboBox();
		function_price.setBackground(Color.WHITE);
		function_price.setBounds(15, 0, 220, 20);
		engine_add.add(function_price);
		
		function_weight = new JComboBox();
		function_weight.setBackground(Color.WHITE);
		function_weight.setBounds(15, 28, 220, 20);
		engine_add.add(function_weight);
		
		JButton function_add = new JButton("");
		function_add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(function_city.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Pasirink miestą apsinešeli!");
				}else{
					try{
						int year = Calendar.getInstance().get(Calendar.YEAR);
						int month = Calendar.getInstance().get(Calendar.MONTH);
						int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
						
						String city = (String)function_city.getSelectedItem();
						byte[] bytes = city.getBytes("UTF-8");
						String newCity = new String(bytes, "UTF-8");
						
						LaunchConnect4 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
						Statement power = LaunchConnect4.createStatement();
						power.executeQuery("SET NAMES UTF8");
						power.execute("Insert into global_advertisiment (adv_add_idd, adv_get_idd, adv_add_username, adv_get_username, adv_year, adv_month, adv_day, adv_reputation, adv_weight, adv_price, adv_country, adv_city, adv_add_signal, adv_get_signal, adv_stop_signal, adv_global_signal, adv_add_like_signal, adv_get_like_signal, adv_status)"
								+ "values ('" + userInfo.getUserId()  + "', '" + 0 + "', '" + userInfo.getUserUsername() + "', '" + "null" + "', '" + year + "', '" + month + "', '" + day  + "', '" + userInfo.getUserReputation() + "', '" + function_weight.getSelectedItem() + "', '" + function_price.getSelectedItem() + "', '" + function_country.getSelectedItem() + "', '" + newCity + "', '" + "null" + "', '" + "null" + "', '" + "null" + "' , '0', 'normal', 'normal', '" + status + "')");
						runUAdv();
						runGAdv();
					}catch(Exception error){
						error.printStackTrace();
					}finally{
						
					}
				}
			}
		});
		function_add.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				Image function_add_1_h = new ImageIcon(this.getClass().getResource("/main_mdl/h/func_add_1_h.png")).getImage();
				function_add.setIcon(new ImageIcon(function_add_1_h));
			}
			
			public void mouseExited(MouseEvent e){
				Image function_add_1_n = new ImageIcon(this.getClass().getResource("/main_mdl/n/func_add_1_n.png")).getImage();
				function_add.setIcon(new ImageIcon(function_add_1_n));
			}
		});
		function_add.setBorderPainted(false);
		function_add.setIcon(new ImageIcon(function_add_n));
		function_add.setBounds(15, 181, 110, 42);
		engine_add.add(function_add);
		
		JButton function_delete = new JButton("");
		function_delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					LaunchConnect5 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
					Statement power = LaunchConnect5.createStatement();
					power.execute("Delete from global_advertisiment where adv_add_idd = '" + userInfo.getUserId() + "'");
					runUAdv();
					
					power.close();
				}catch(Exception error){
					error.printStackTrace();
				}finally{
					try{
						if(LaunchConnect5 != null){
							LaunchConnect5.close();
						}
					}catch(Exception error){
						error.printStackTrace();
					}
				}
			}
		});
		function_delete.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				Image function_delete_2_h = new ImageIcon(this.getClass().getResource("/main_mdl/h/func_delete_2_h.png")).getImage();
				function_delete.setIcon(new ImageIcon(function_delete_2_h));
			}
			
			public void mouseExited(MouseEvent e){
				Image function_delete_2_n = new ImageIcon(this.getClass().getResource("/main_mdl/n/func_delete_2_n.png")).getImage();
				function_delete.setIcon(new ImageIcon(function_delete_2_n));
			}
		});
		function_delete.setBorderPainted(false);
		function_delete.setIcon(new ImageIcon(function_delete_n));
		function_delete.setBounds(125, 181, 110, 42);
		engine_add.add(function_delete);
		
		JLabel background_0 = new JLabel("");
		background_0.setIcon(new ImageIcon(bc0));
		background_0.setBounds(0, 0, 249, 234);
		engine_add.add(background_0);
		
		engine_answer = new JPanel();
		engine_answer.setBounds(0, 115, 249, 234);
		contentPane.add(engine_answer);
		engine_answer.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(15, 5, 220, 220);
		engine_answer.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		JLabel background_1 = new JLabel("");
		background_1.setIcon(new ImageIcon(bc1));
		background_1.setBounds(0, 0, 251, 234);
		engine_answer.add(background_1);
		
		engine_profile = new JPanel();
		engine_profile.setBounds(0, 115, 249, 234);
		contentPane.add(engine_profile);
		engine_profile.setLayout(null);
		
		JPanel profile_header = new JPanel();
		profile_header.setBounds(15, 5, 220, 40);
		profile_header.setBackground(Color.WHITE);
		engine_profile.add(profile_header);
		profile_header.setLayout(null);
		
		username_panel_text = new JLabel();
		username_panel_text.setFont(new Font("Tahoma", Font.PLAIN, 12));
		username_panel_text.setHorizontalAlignment(SwingConstants.CENTER);
		username_panel_text.setBounds(10, 5, 200, 14);
		profile_header.add(username_panel_text);
		
		reputation_panel_text = new JLabel();
		reputation_panel_text.setFont(new Font("Tahoma", Font.PLAIN, 12));
		reputation_panel_text.setHorizontalAlignment(SwingConstants.CENTER);
		reputation_panel_text.setBounds(10, 20, 200, 14);
		profile_header.add(reputation_panel_text);
		
		JPanel profile_main = new JPanel();
		profile_main.setBackground(Color.WHITE);
		profile_main.setBounds(15, 53, 220, 170);
		engine_profile.add(profile_main);
		profile_main.setLayout(null);
		
		logo_panel_image = new JLabel("");
		logo_panel_image.setIcon(new ImageIcon(logo_panel_image_n));
		logo_panel_image.setHorizontalAlignment(SwingConstants.CENTER);
		logo_panel_image.setBounds(40, 20, 134, 50);
		profile_main.add(logo_panel_image);
		
		JLabel info1_text = new JLabel("Powered by J.F.U group!");
		info1_text.setFont(new Font("Tahoma", Font.PLAIN, 12));
		info1_text.setForeground(new Color(18,54,55));
		info1_text.setHorizontalAlignment(SwingConstants.CENTER);
		info1_text.setBounds(10, 105, 200, 14);
		profile_main.add(info1_text);
		
		JLabel info2_text = new JLabel("„Už Cannabis dekriminalizaciją“");
		info2_text.setFont(new Font("Tahoma", Font.PLAIN, 12));
		info2_text.setForeground(new Color(18,54,55));
		info2_text.setHorizontalAlignment(SwingConstants.CENTER);
		info2_text.setBounds(10, 120, 200, 14);
		profile_main.add(info2_text);
		
		JLabel version = new JLabel("Programos versija: 0.1");
		version.setFont(new Font("Tahoma", Font.PLAIN, 12));
		version.setForeground(new Color(18,54,55));
		version.setHorizontalAlignment(SwingConstants.CENTER);
		version.setBounds(10, 80, 200, 14);
		profile_main.add(version);
		
		JLabel background_2 = new JLabel("");
		background_2.setIcon(new ImageIcon(bc2));
		background_2.setBounds(0, 0, 251, 235);
		engine_profile.add(background_2);
		
		engine_messages = new JPanel();
		engine_messages.setBounds(0, 115, 250, 234);
		engine_messages.setVisible(false);
		contentPane.add(engine_messages);
		engine_messages.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(15, 0, 220, 48);
		engine_messages.add(scrollPane_3);
		
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(15, 54, 220, 95);
		engine_messages.add(scrollPane_4);
		
		table_4 = new JTable();
		scrollPane_4.setViewportView(table_4);
		
		func_accept = new JLabel("");
		func_accept.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				JOptionPane.showMessageDialog(null, "Soon..");
			}
			public void mouseEntered(MouseEvent e){
				func_accept.setIcon(new ImageIcon(fc_accept_h));
			}
			public void mouseExited(MouseEvent e){
				func_accept.setIcon(new ImageIcon(fc_accept_n));
			}
		});
		func_accept.setIcon(new ImageIcon(fc_accept_n));
		func_accept.setBounds(15, 190, 103, 37);
		engine_messages.add(func_accept);
		
		func_reject = new JLabel("");
		func_reject.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				JOptionPane.showMessageDialog(null, "Soon..");
			}
			public void mouseEntered(MouseEvent e){
				func_reject.setIcon(new ImageIcon(fc_reject_h));
			}
			public void mouseExited(MouseEvent e){
				func_reject.setIcon(new ImageIcon(fc_reject_n));
			}
		});
		func_reject.setIcon(new ImageIcon(fc_reject_n));
		func_reject.setBounds(132, 190, 103, 37);
		engine_messages.add(func_reject);
		
		content = new JTextField();
		content.setBounds(15, 160, 173, 20);
		engine_messages.add(content);
		content.setColumns(10);
		
		func_send = new JLabel("");
		func_send.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				JOptionPane.showMessageDialog(null, "Soon..");
			}
			public void mouseEntered(MouseEvent e){
				func_send.setIcon(new ImageIcon(fc_sendM_h));
			}
			public void mouseExited(MouseEvent e){
				func_send.setIcon(new ImageIcon(fc_sendM_n));
			}
		});
		func_send.setIcon(new ImageIcon(fc_sendM_n));
		func_send.setBounds(198, 158, 33, 26);
		engine_messages.add(func_send);
		
		JLabel message_background = new JLabel("");
		message_background.setIcon(new ImageIcon(mbc));
		message_background.setBounds(0, 0, 250, 234);
		engine_messages.add(message_background);
		
		// Back HED End  ---------------------------------------------------------------------------------------------------------------------------
		// Back MAI Mai  ---------------------------------------------------------------------------------------------------------------------------

		design_main = new JLabel("");
		design_main.setIcon(new ImageIcon(main));
		design_main.setBounds(0, 115, 251, 234);
		contentPane.add(design_main);
		
		mysql_status = new JLabel("<html>Šiandien Prisijungė: <font color = 'rgb(94,255,67'>" + maxConnected + "</font> kartų!</html>");
		mysql_status.setFont(new Font("Comic Sans MS", mysql_status.getFont().getStyle() | Font.BOLD, mysql_status.getFont().getSize()));
		mysql_status.setForeground(Color.WHITE);
		mysql_status.setBounds(20, 356, 100, 14);
		contentPane.add(mysql_status);
		
		func_messages = new JLabel("");
		func_messages.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				JOptionPane.showMessageDialog(null, "Modulis funkcionuos 0.2v versijoje.");
			}
			public void mouseEntered(MouseEvent e){
				func_messages.setIcon(new ImageIcon(fm_h));
			}
			public void mouseExited(MouseEvent e){
				func_messages.setIcon(new ImageIcon(fm));
			}
		});
		func_messages.setIcon(new ImageIcon(fm));
		func_messages.setBounds(155, 354, 31, 21);
		contentPane.add(func_messages);
		
		func_password_c = new JLabel("");
		func_password_c.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				changepw_frame = new function_changepw(userInfo, main_frame);
				changepw_frame.setVisible(true);
				setVisible(false);
			}
			public void mouseEntered(MouseEvent e){
				func_password_c.setIcon(new ImageIcon(fpc_h));
			}
			public void mouseExited(MouseEvent e){
				func_password_c.setIcon(new ImageIcon(fpc));
			}
		});
		func_password_c.setIcon(new ImageIcon(fpc));
		func_password_c.setBounds(190, 355, 19, 19);
		contentPane.add(func_password_c);
		
		func_warnings = new JLabel("");
		func_warnings.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(userInfo.getUserWarnings() >= 3){
					int warningMecha = JOptionPane.showConfirmDialog(null, "Kažkas įvedė 3 kartus neteisingai slaptažodį! Ar tai buvote jūs?");
					if(warningMecha == 0){
						try{
							userInfo.setUserWarnings(0);
							LaunchConnect8 = DriverManager.getConnection(config.getServerName(), config.getServerUser(), config.getServerPass());
							Statement power = LaunchConnect8.createStatement();
							power.execute("Update user_information set warnings = '0' where id = '" + userInfo.getUserId() + "'");
							power.close();
						}catch(Exception error){
							error.printStackTrace();
						}finally{
							try{
								if(LaunchConnect8 != null){
									LaunchConnect8.close();
								}
							}catch(Exception error){
								error.printStackTrace();
							}
						}
					}else{
						JOptionPane.showMessageDialog(null, "Prašome kuo skubiau pasikeisti slaptažodį.");
					}
				}
			}
			public void mouseEntered(MouseEvent e){
				func_warnings.setIcon(new ImageIcon(fw_h));
			}
			public void mouseExited(MouseEvent e){
				func_warnings.setIcon(new ImageIcon(fw));
			}
		});
		func_warnings.setIcon(new ImageIcon(fw));
		func_warnings.setBounds(215, 355, 19, 19);
		contentPane.add(func_warnings);
		
		design_bottom = new JLabel("");
		design_bottom.setIcon(new ImageIcon(bottom));
		design_bottom.setBounds(0, 349, 251, 30);
		contentPane.add(design_bottom);
		
		Thread power = new Thread(){
			public void run(){
				while(timeVariable <= 5){
					if(timeVariable == 5){
						FirstRunBackground.setVisible(false);
						function_country.setVisible(true);
						function_city.setVisible(true);
						engine_news.setVisible(true);
						engine_add.setVisible(false);
						engine_answer.setVisible(false);
						engine_profile.setVisible(false);
						runReport();
						runGAdv();
						runRBridge();
						getMaxConnectedUsers();
					}
					
					timeVariable++;
					
					try{
						sleep(1000);
					}catch(Exception error){
						error.printStackTrace();
					}
				}
			}
		};
		power.start();	
		// <<------- Constructor
	}
}
