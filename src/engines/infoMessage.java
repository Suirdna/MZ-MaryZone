package engines;

public class infoMessage {
	private int item_id;
	private String user;
	private String message;
	
	public infoMessage(int obj1, String obj2, String obj4){
		item_id = obj1;
		user = obj2;
		message = obj4;
	}
	
	public int getItemId(){
		int obj = item_id;
		return obj;
	}
	
	public String getUser(){
		String obj = user;
		return obj;
	}
	
	public String getMessage(){
		String obj = message;
		return obj;
	}
}
