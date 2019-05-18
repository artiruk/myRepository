package com.bjsxt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bjsxt.pojo.User;
import com.bjsxt.service.UserService;
import com.bjsxt.service.impl.UserServiceImpl;

@WebServlet("/login")
public class loginServlet extends HttpServlet{
	private UserService us;
	@Override
	public void init() throws ServletException {
		WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		us=applicationContext.getBean("userService",UserServiceImpl.class);
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String code=req.getParameter("code");
		String codesession = req.getSession().getAttribute("code").toString();
		if(codesession.equals(code)){
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			User user=new User();
			user.setUname(username);
			user.setPwd(password);
			User loginUser = us.login(user);
			if(loginUser!=null){
				resp.sendRedirect("main.jsp");
			}else{
				req.setAttribute("error", "用户名或密码错误");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
		}else{
			req.setAttribute("error", "验证码错误");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}
