package blog.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import blog.dao.UserDao;
import blog.model.User;
import net.sf.json.JSONObject;

public class UserM extends HttpServlet {
	private static final long serialVersionUID=1;
	public void doGet(HttpServletRequest req,HttpServletResponse res) 
	throws IOException,ServletException{
		//add code to judge session existence
		String click=req.getParameter("click");
		if(click.equals("guest")) guest(req,res);
		if(click.equals("login")) userLogin(req,res);
		if(click.equals("register")) userRegister(req,res);
		if(click.equals("pwd")) userPwd(req,res);
		if(click.equals("close")) delUser(req,res);
		if(click.equals("logout")) logoutUser(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws IOException,ServletException{
		doGet(req,res);
	}
/////////////////////////////////process request////////////////////////
	public void guest(HttpServletRequest req,HttpServletResponse res)
			throws IOException,ServletException{
		User user=new User();
		HttpSession session=req.getSession();
		session.setAttribute("user", user);
		RequestDispatcher dispatcher=req.getRequestDispatcher("/jsp/ShowContent.jsp");
		dispatcher.forward(req, res);
	}

	public void userLogin(HttpServletRequest req,HttpServletResponse res)
			throws IOException,ServletException{
		UserDao uDao=new UserDao();
		User user=new User();
		String userName=req.getParameter("userName");
		String userPwd=req.getParameter("userPwd");
		try{
			user=uDao.checkUser(userName,userPwd);
			if(user!=null){
				HttpSession session=req.getSession();
				session.setAttribute("user", user);
				RequestDispatcher dispatcher=req.getRequestDispatcher("/jsp/ShowContent.jsp");
				dispatcher.forward(req, res);
			}
			else{
				res.sendRedirect("index.jsp?logErr=yes");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void userRegister(HttpServletRequest req,HttpServletResponse res)
			throws IOException,ServletException{
		UserDao uDao=new UserDao();
		String userName=req.getParameter("userName");
		String userPwd=req.getParameter("userPwd");
		boolean rs=uDao.insertUser(userName,userPwd);
		if(!rs)
			res.sendRedirect("index.jsp?regErr=yes");
		else
			res.sendRedirect("index.jsp?regOK=yes");
	}
	
	public void userPwd(HttpServletRequest req,HttpServletResponse res) 
			throws IOException,ServletException{
		UserDao uDao=new UserDao();
		String oldPwd=req.getParameter("oldPwd");
		String newPwd=req.getParameter("newPwd");
		String user=req.getParameter("user");
		boolean rs=uDao.userPwd(user,oldPwd,newPwd);
		JSONObject jo=new JSONObject();
		if(rs) jo.put("status", "true");
		else	jo.put("status" ,"false");
		res.setContentType("application/json; charset=utf-8");
		PrintWriter out=res.getWriter();
		out.println(jo);
		out.flush();
		out.close();
	}
	
	public void delUser(HttpServletRequest req,HttpServletResponse res)
		throws IOException,ServletException{
		String user=req.getParameter("user");
		String pwd=req.getParameter("pwd");
		UserDao uDao=new UserDao();
		boolean rs=uDao.deleteUser(user,pwd);
			JSONObject jo=new JSONObject();
			if(!rs)jo.put("status", "false");
			else jo.put("status", "true");
			res.setContentType("application/json; charset=utf-8");
			PrintWriter out=res.getWriter();
			out.println(jo);
			out.flush();
			out.close();
	}
	public void logoutUser(HttpServletRequest req,HttpServletResponse res)
			throws IOException,ServletException{
			HttpSession session=req.getSession(false);
			session.invalidate();
				JSONObject jo=new JSONObject();
				jo.put("status", "true");
				res.setContentType("application/json; charset=utf-8");
				PrintWriter out=res.getWriter();
				out.println(jo);
				out.flush();
				out.close();
		}
}
