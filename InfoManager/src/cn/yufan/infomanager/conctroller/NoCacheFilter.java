package cn.yufan.infomanager.conctroller;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/rootLogin.jsp"})
public class NoCacheFilter extends HttpFilter {
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
		resp.setHeader("cache-control","no-cache");
		resp.setHeader("pragma","no-cache");
		chain.doFilter(req,resp);
	}
}
