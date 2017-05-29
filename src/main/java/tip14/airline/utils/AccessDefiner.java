package tip14.airline.utils;

public class AccessDefiner {

	public static boolean isLoginAccessAvailable(String url){
		if(url.equals("/airline/login") || url.equals("/airline/registration")){
			return false;	
		}
		return true;		
	}
	
	public static boolean isLogoutAccessAvailable(String url){
		if(url.equals("/airline/") || url.equals("/airline/login") || url.equals("/airline/registration") || url.matches(".*(css|jpg|png|gif|js)$")){
			return true;	
		}
		return false;		
	}
	
	
	
}
