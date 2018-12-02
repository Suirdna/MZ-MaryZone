package engines;

public class infoUser {
	
	private static int id, reputation, warnings;
	private static String username, question, answer;/* last_connect;*/
	
	public infoUser(int user_id, String user_username, String user_question, String user_answer, int user_reputation, int user_warnings){
		id = user_id;
		username = user_username;
		question = user_question;
		answer = user_answer;
		reputation = user_reputation;
		warnings = user_warnings;
		/*last_connect = user_last_connect;*/
	}
	
	public void setUserId(int user_id){
		id = user_id;
	}
	
	public int getUserId(){
		int obj = id;
		return obj;
	}
	
	public void setUserUsername(String user_username){
		username = ((!user_username.equals(""))? user_username:null);
	}
	
	public String getUserUsername(){
		String obj = username;
		return obj;
	}
	
	public void setUserQuestion(String user_question){
		question = ((!user_question.equals(""))? user_question:null);
	}
	
	public String getUserQuestion(){
		String obj = question;
		return obj;
	}
	
	public void setUserAnswer(String user_answer){
		answer = ((!user_answer.equals(""))? user_answer:null);
	}
	
	public String getUserAnswer(){
		String obj = answer;
		return obj;
	}
	
	public void setUserReputation(int user_reputation){
		reputation = (user_reputation != 0)? user_reputation:0;
	}

	public int getUserReputation(){
		int obj = reputation;
		return obj;
	}
	
	public void setUserWarnings(int user_warnings){
		warnings = (user_warnings != 0)? user_warnings:0;
	}
	
	public int getUserWarnings(){
		int obj = warnings;
		return obj;
	}
	
	/*public void setUserLastConnect(String user_last_connect){
		last_connect = user_last_connect;
	}
	
	public String getUserLastConnect(){
		String obj = last_connect;
		return obj;
	}*/
}
