package cn.yufan.infomanager.conctroller;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/add.jsp", "/index.jsp", "/update.jsp"})
public class LoginFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
		String page = req.getServletPath();
		Object login = req.getSession().getAttribute("login");
		if (page.equals("/add.jsp") || page.equals("/index.jsp")) {
			if (login == null) {
				resp.sendRedirect("/rootLogin.jsp");
			} else if (((String) login).equals("user")) {
				resp.sendRedirect("/rootLogin.jsp");
			}else{
				chain.doFilter(req,resp);
			}
		} else {
			if (login == null) {
				resp.sendRedirect("/login.jsp");
			}else if (((String) login).equals("user")) {
				resp.sendRedirect("/rootLogin.jsp");
			}else{
				chain.doFilter(req,resp);
			}
		}
	}
}
