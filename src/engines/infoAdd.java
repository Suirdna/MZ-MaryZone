package engines;

public class infoAdd {
	
	public static enum Status{
		sold, on_sale
	}
	
	private int id;
	private int years, month, day;
	private String city;
	private int weight;
	private Status status;
	
	public infoAdd(int obj_id, int obj1_y, int obj2_m, int obj3_d, String obj_c, int obj_w, Status obj_s){
		id = obj_id;
		years = obj1_y;
		month = obj2_m;
		day = obj3_d;
		city = obj_c;
		weight = obj_w;
		status = obj_s;
	}
	
	public int getId(){
		int obj = id;
		return obj;
	}
	
	public String getDate(){
		String obj = String.format("%s.%s.%s", years, month, day);
		return obj;
	}
	
	public String getCity(){
		String obj = city;
		return obj;
	}
	
	public String getWeight(){
		String obj = String.format("(%sg)", weight);
		return obj;
	}
	
	public Status getStatus(){
		Status obj = status;
		return obj;
	}
}