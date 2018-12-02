package engines;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class clientConfiguration {
	
	// Application
	private final String application_title = "MaryZone 0.1";
	private final String application_name = "MaryZone: ";
	private String application_date = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
	
	// Application ComoBox: function_country
	private String[] country_names = new String[]{
			"Lietuva" // 1
	};
	
	private String[] country_resources = new String[]{
			 "/country_mdl/Lithuania.png" // 5
	};
	
	// Application ComboBox: function_city
	private String[] obj_city_lithuania = new String[]{
			"Akmenė", "Alytus", "Anykščiai", "Birštonas", "Biržai", "Druskininkai", "Elektrėnai", "Ignalina", "Jonava", //9
			"Joniškis", "Jurbarkas", "Kalvarija", "Kaunas", "Kazlų Rūda", "Kėdainiai", "Kelmė", "Klaipėda", "Kretinga", "Kupiškis", "Lazdijai", //11
			"Marijampolė", "Mažeikiai", "Molėtai", "Neringa", "Pagėgiai", "Pakruojis", "Palanga", "Panevėžys", "Pasvalys", "Plungė", //10
			"Prienai", "Radviliškis", "Raseiniai", "Rietava", "Rokiškis", "Šakiai", "Šalčininkai", "Šiauliai", "Šilalė", "Širvintai", //10
			"Skuodas", "Švenčionys", "Tauragė", "Telšiai", "Trakai", "Ukmergė", "Utena", "Varėna", "Vilkaviškis", "Vilnius",  //10
			"Visaginas", "Zarasai" //2
			
			// 52
	};
	
	private String[] resource_city_lithuania = new String[]{
			 "/city_mdl/Akmene.png", "/city_mdl/Alytus.png", "/city_mdl/Anyksciai.png", "/city_mdl/Birstonas.png", "/city_mdl/Birzai.png", "/city_mdl/Druskininkai.png", "/city_mdl/Elektrenai.png", "/city_mdl/Ignalina.png", "/city_mdl/Jonava.png", "/city_mdl/Joniskis.png", //10
			 "/city_mdl/Jurbarkas.png", "/city_mdl/Kalvarija.png", "/city_mdl/Kaunas.png", "/city_mdl/Kazlu.png", "/city_mdl/Kedainiai.png", "/city_mdl/Kelme.png", "/city_mdl/Klaipeda.png", "/city_mdl/Kretinga.png", "/city_mdl/Kupiskis.png", "/city_mdl/Lazdijai.png", //10
			 "/city_mdl/Marijampole.png", "/city_mdl/Mazeikiai.png", "/city_mdl/Moletai.png", "/city_mdl/Neringa.png", "/city_mdl/Pagegiai.png", "/city_mdl/Pakruojis.png", "/city_mdl/Palanga.png", "/city_mdl/Panevezys.png", "/city_mdl/Pasvalys.png", "/city_mdl/Plunge.png", //10
			 "/city_mdl/Prienai.png", "/city_mdl/Radviliskis.png", "/city_mdl/Raseiniai.png", "/city_mdl/Rietava.png", "/city_mdl/Rokiskis.png", "/city_mdl/Sakiai.png", "/city_mdl/Salcininkai.png", "/city_mdl/Siauliai.png", "/city_mdl/Silale.png", "/city_mdl/Sirvintai.png", //10
			 "/city_mdl/Skuodas.png", "/city_mdl/Svencionys.png", "/city_mdl/Taurage.png", "/city_mdl/Telsiai.png", "/city_mdl/Trakai.png", "/city_mdl/Ukmerge.png", "/city_mdl/Utena.png", "/city_mdl/Varena.png", "/city_mdl/Vilkaviskis.png", "/city_mdl/Vilnius.png", "/city_mdl/Visaginas.png",  "/city_mdl/Zarasai.png" //12
			 
			 // 52
	};
	
	private String priceImage = "/search_1_e.png";
	private String weightImage = "/search_2_e.png";
	
	private int[] price = new int[]{
			1,2,3,4,5,6,7,8,9,10,11,12
	};
	

	private int[] weight = new int[]{
			1,2,3,4,5,6,7,8,9,10
	};
	
	// Mysql configurations
	private final String serverName = "jdbc:mysql://localhost:3306/maryzone";
	private final String serverUsername = "root";
	private final String serverPassword = "";
	
	// LoginWindow configurations
	private String userWel = application_name + "Sveiki atvyke. :)";
	private String userError = application_name + "Neteisingai įvesti duomenys";
	private String userPassError = application_name + "Neteisingas slaptažodis.";
	private String userConnError = application_name + "Globalus Mysql serveris išjunktas.";
	
	// RegisWindow configurations
	private String regUserMatch = application_name + "Šis slapyvardis jau naudojamas.";
	private String regUserSuc = application_name + "Paskyra sukurta.";
	private String regMysqlError = application_name + "Globalus Mysql serveris išjunktas.";
	
	// RememberWindow configurations;
	private String remUsetMatches = application_name + "Vartotojas surastas.";
	private String remUserNotMatches = application_name + "Tokio vartotojo nėra.";
	private String remUserIncAnswer = application_name + "Atsakymas neteisingas.";
	private String remMysqlError = application_name + "Globalus Mysql serveris išjunktas.";
	
	// Application methods
	public String getAppTitle(){
		String obj = application_title;
		return obj;
	}
	
	public String getAppTime(){
		String obj = application_date;
		return obj;
	}
	
	
	public String getAllCountrys(int object){
		String obj = country_names[object];
		return obj;
	}
	
	public int getAllCountrys(){
		int obj = country_names.length;
		return obj;
	}
	
	public String getCountryResource(int i){
		String obj = country_resources[i];
		return obj;
	}
	
	public int getAllCountrysCount(){
		return country_names.length;
	}
	
	public String getCityLithuania(int object){
		String obj = obj_city_lithuania[object];
		return obj;
	}
	
	public String getCityResourceLithuania(int object){
		String obj = resource_city_lithuania[object];
		return obj;
	}
	
	public int getCityLithuaniaCount(){
		return obj_city_lithuania.length;
	}
	
	// ComboBox methods
	public int setPriceItem(int index){
		int obj = price[index];
		return obj;
	}
	
	public int getPriceCount(){
		int obj = price.length;
		return obj;
	}
	
	public String getImagePrice(){
		String obj = priceImage;
		return obj;
	}
	
	public int setWeightItem(int index){
		int obj = weight[index];
		return obj;
	}
	
	public int getWeightCount(){
		int obj = weight.length;
		return obj;
	}
	
	public String getImageWeight(){
		String obj = weightImage;
		return obj;
	}
	
	// Mysql methods
	public String getServerName(){
		String obj = serverName;
		return obj;
	}
	
	public String getServerUser(){
		String obj = serverUsername;
		return obj;
	}
	
	public String getServerPass(){
		String obj = serverPassword;
		return obj;
	}
	
	// LoginWindow methods
	public String getLoginc1(){
		String obj = String.format("%s", userWel);
		return obj;
	}
	
	public String getLoginc2(){
		String obj = String.format("%s", userError);
		return obj;
	}
	
	public String getLoginc3(){
		String obj = String.format("%s", userPassError);
		return obj;
	}
	
	public String getLoginc4(){
		String obj = String.format("%s", userConnError);
		return obj;
	}
	
	// RegistrationWindow methods
	public String getRegc1(){
		String obj = String.format("%s", regUserMatch);
		return obj;
	}
	
	public String getRegc2(){
		String obj = String.format("%s", regUserSuc);
		return obj;
	}
	
	public String getRegc3(){
		String obj = String.format("%s", regMysqlError);
		return obj;
	}
	
	// RememberWindow methods
	public String getRemc1(){
		String obj = String.format("%s", remUsetMatches);
		return obj;
	}
	
	public String getRemc2(){
		String obj = String.format("%s", remUserNotMatches);
		return obj;
	}
	
	public String getRemc3(){
		String obj = String.format("%s", remUserIncAnswer);
		return obj;
	}
	
	public String getRemc4(){
		String obj = String.format("%s", remMysqlError);
		return obj;
	}

}
