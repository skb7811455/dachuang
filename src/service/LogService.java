package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogService {
	public String quitUser(HttpServletRequest request){
	    HttpSession session = request.getSession();
	    if(session.getAttribute("user")==null) return "�Ѿ�ע��";
	    session.removeAttribute("user");
	    return "ע���ɹ�";
	 }
}
