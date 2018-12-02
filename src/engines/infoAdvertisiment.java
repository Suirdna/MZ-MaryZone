package engines;

public class infoAdvertisiment {
	public int user_advertisiment_id;
	public String user_username;
	public int user_reputation;
	public int user_weedWeight;
	public int user_weedPrice;
	public String user_advertisiment_city;
	
	public infoAdvertisiment(int obj1, String obj2, int obj3, int obj4, int obj5, String obj6){
		user_advertisiment_id = obj1;
		user_username = obj2;
		user_reputation = obj3;
		user_weedWeight = obj4;
		user_weedPrice = obj5;
		user_advertisiment_city = obj6;
	}
	
	public int getAdvertisimentId(){
		int obj = user_advertisiment_id;
		return obj;
	}
	
	public String getUsername(){
		String obj = user_username;
		return obj;
	}
	
	public int getUserReputation(){
		int obj = user_reputation;
		return obj;
	}
	
	public int getUserWeedWeight(){
		int obj = user_weedWeight;
		return obj;
	}
	
	public int getUserWeedPrice(){
		int obj = user_weedPrice;
		return obj;
	}
	
	public String getUserAdvertisimentCity(){
		String obj = user_advertisiment_city;
		return obj;
	}
}
