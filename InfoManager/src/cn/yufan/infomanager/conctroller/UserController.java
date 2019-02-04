package cn.yufan.infomanager.conctroller;

import cn.yufan.infomanager.model.User;
import cn.yufan.infomanager.service.ServiceFactory;
import cn.yufan.infomanager.service.UserService;
import cn.yufan.infomanager.utils.Captcha;
import cn.yufan.infomanager.utils.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "*.udo")
public class UserController extends HttpServlet {

	private UserService userService = ServiceFactory.getUserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String path = req.getServletPath();
		String target = path.substring(1, path.indexOf('.'));
		try {
			Method method = this.getClass().getDeclaredMethod(target, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String phone_no = req.getParameter("phone_no");
		String email = req.getParameter("email");
		List<User> list;
		if (StringUtils.isEmpty(name) && StringUtils.isEmpty(phone_no) && StringUtils.isEmpty(email))
			list = userService.getAll();
		else
			list = userService.mixedFuzzyQuery(name, phone_no, email);
		req.setAttribute("userList", list);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Date birthday = java.sql.Date.valueOf(req.getParameter("birthday"));
		String name = req.getParameter("name");
		User user = new User(name, req.getParameter("password"), birthday, req.getParameter("email"),
				req.getParameter("phone_no"), req.getParameter("address"));
		long count = userService.countByName(name);
		if (count == 0) {
			int rows = userService.add(user);
			if (rows > 0) {
				req.setAttribute("message", "true");
			} else {
				req.setAttribute("message", "false");
			}
		} else {
			req.setAttribute("message", "occupied");
		}
		req.getRequestDispatcher("/add.jsp").forward(req, resp);
	}

	protected void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		User user = userService.getById(id);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/update.jsp").forward(req, resp);
	}

	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Date birthday = java.sql.Date.valueOf(req.getParameter("birthday"));
		String name = req.getParameter("name");
		String oldName = req.getParameter("oldName");
		User user = new User(Integer.parseInt(req.getParameter("id")), name, req.getParameter("password"), birthday,
				req.getParameter("email"), req.getParameter("phone_no"), req.getParameter("address"));
		long count = userService.countByName(name);
		req.setAttribute("user", user);
		if (!name.equals(oldName) && count >= 1) {
			req.setAttribute("message", "occupied");
		} else {
			int rows = userService.update(user);
			if (rows > 0) {
				req.setAttribute("message", "true");
			} else {
				req.setAttribute("message", "false");
			}
		}
		req.getRequestDispatcher("/update.jsp").forward(req, resp);
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		int row = userService.deleteById(id);
		PrintWriter out = resp.getWriter();
		if (row == 1) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>删除中...</title>");
			out.println("<meta http-equiv='refresh' content='2;url=http://localhost:8080/' >");
			out.println("<meta charset='UTF-8' >");
			out.println("</head>");
			out.println("<body style='background-color: aliceblue;'>");
			out.println("<p style='color:cadetblue;font-size:20px;margin:100px;'>删除成功，即将返回主页</p>");
			out.println("</body>");
			out.println("</html>");
		} else {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>删除中...</title>");
			out.println("<meta charset='UTF-8' >");
			out.println("<script>");
			out.println("alert('删除失败，点击确定返回');");
			out.println("window.history.back();");
			out.println("</script>");
			out.println("</head>");
			out.println("<body style='background-color: aliceblue;'>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		Cookie cookie = null;
		String codeIn = req.getParameter("captcha");
		String code = (String) req.getSession().getAttribute("captcha");
		if (!StringUtils.isEmpty(uid)) {
			req.getSession().setAttribute("login", "user");
			int id = Integer.parseInt(uid);
			User old_user = userService.getById(id);
			req.setAttribute("user", old_user);
			req.getRequestDispatcher("/update.jsp").forward(req, resp);
		} else {
			if (code.equals(codeIn)) {
				String name = req.getParameter("name");
				String password = req.getParameter("password");
				String expires = req.getParameter("expires");
				User user = userService.login(name, password);
				if (user != null) {
					req.getSession().setAttribute("login", "user");
					cookie = new Cookie("account", String.valueOf(user.getId()));
					if (!StringUtils.isEmpty(expires) && expires.equals("7")) {
						cookie.setMaxAge(7 * 24 * 3600);
					}
					resp.addCookie(cookie);
					req.setAttribute("user", user);
					req.getRequestDispatcher("/update.jsp").forward(req, resp);
				} else {
					resp.setContentType("text/plain");
					resp.getWriter().println("用户名或密码错误");
				}
			} else {
				resp.setContentType("text/plain");
				resp.getWriter().println("验证码错误");
			}
		}
	}

	protected void captcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Captcha captcha = Captcha.getInstance();
		String code = captcha.generateCode();
		BufferedImage image = captcha.generateImg(code);
		HttpSession session = req.getSession();
		session.setAttribute("captcha", code);
		resp.setContentType("image/jpeg");
		OutputStream out = resp.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}

	protected void rootLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("name");
		String password = req.getParameter("password");
		String rootName = getServletContext().getInitParameter("rootName");
		String rootPassword = getServletContext().getInitParameter("rootPassword");
		if (username.equals(rootName) && password.equals(rootPassword)) {
			req.getSession().setAttribute("login", "root");
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} else {
			resp.setContentType("text/plain");
			resp.getWriter().println("用户名或密码错误");
		}
	}
}

