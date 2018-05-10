package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import net.sf.json.JSONObject;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		HttpServletResponse servletResponse = (HttpServletResponse)response;
		servletResponse.setContentType("utf-8"); 
		servletResponse.setCharacterEncoding("utf-8"); 
		HttpSession session = servletRequest.getSession(true);
		
		// ����û������URI
		String path = servletRequest.getRequestURI();
		String user=(String)session.getAttribute("user");
		System.out.println("��ǰ��¼�û�Ϊ"+user);
		System.out.println("��ǰ�û�sessionIDΪ"+session.getId());
		
		if(path.indexOf("Login")>-1){
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		
		
		if(user==null){
			JSONObject jsonObject = new JSONObject();  
			jsonObject.put("code", "0");
			jsonObject.put("message", "�û�δ��¼");
			servletResponse.getWriter().write(jsonObject.toString());
			
		}else{
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
	
}
