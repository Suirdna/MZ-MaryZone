package engines;

public class infoAdvertisimentMessageConfirm {
	
	private int adcm_id;
	private int adcm_add_idd;
	private int adcm_get_idd;
	private String adcm_username;
	private int adcm_weight;
	private String adcm_country;
	private String adcm_city;
	private int adcm_year;
	private int adcm_month;
	private int adcm_day;
	private String adcm_add_signal;
	private String adcm_get_signal;
	private int adcm_global_signal;
	
	public infoAdvertisimentMessageConfirm(int obj1, int obj2, int obj3, String obj4, int obj5, String obj6, String obj7, int obj8, int obj9, int obj10, String obj11, String obj12, int obj13){
		adcm_id = obj1;
		adcm_add_idd = obj2;
		adcm_get_idd = obj3;
		adcm_username = obj4;
		adcm_weight = obj5;
		adcm_country = obj6;
		adcm_city = obj7;
		adcm_year = obj8;
		adcm_month = obj9;
		adcm_day = obj10;
		adcm_add_signal = obj11;
		adcm_get_signal = obj12;
		adcm_global_signal = obj13;
	}
	
	public int getADCM_ID(){
		int obj = adcm_id;
		return obj;
	}
	
	public int getADCM_ADD_IDD(){
		int obj = adcm_add_idd;
		return obj;
	}
	
	public int getADCM_GET_IDD(){
		int obj = adcm_get_idd;
		return obj;
	}
	
	public String getADCM_USERNAME(){
		String obj = adcm_username;
		return obj;
	}
	
	public int getADCM_WEIGHT(){
		int obj = adcm_weight;
		return obj;
	}
	
	public String getADCM_COUNTRY(){
		String obj = adcm_country;
		return obj;
	}
	
	public String getADCM_CITY(){
		String obj = adcm_city;
		return obj;
	}
	
	public int getADCM_YEAR(){
		int obj = adcm_year;
		return obj;
	}
	
	public int getADCM_MONTH(){
		int obj = adcm_month;
		return obj;
	}
	
	public int getADCM_DAY(){
		int obj = adcm_day;
		return obj;
	}
	
	public String getADCM_ADD_SIGNAL(){
		String obj = adcm_add_signal;
		return obj;
	}
	
	public String getADCM_GET_SIGNAL(){
		String obj = adcm_get_signal;
		return obj;
	}
	
	public int getADCM_GLOBAL_SIGNAL(){
		int obj = adcm_global_signal;
		return obj;
	}
}
