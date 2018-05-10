package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogService {
	public String quitUser(HttpServletRequest request){
	    HttpSession session = request.getSession();
	    if(session.getAttribute("user")==null) return "已经注销";
	    session.removeAttribute("user");
	    return "注销成功";
	 }
}
