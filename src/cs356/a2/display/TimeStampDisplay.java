package cs356.a2.display;

import java.text.SimpleDateFormat;
import java.util.Date;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserEntity;

public class TimeStampDisplay {
	
	public static String displayLastUpdatedTime(User u){
		long time = u.getLastUpdatedTime();
		return display(time);
	}
	
	public static String displayCreationTime(UserEntity u){
		long time = u.getCreationTime();
		return display(time);
	}
	
	public static String display(long time){
		Date date = new Date(time);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return dateFormat.format(date);
	}
}
