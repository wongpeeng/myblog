package blog.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.dao.AdminDao;
import blog.dao.UserDao;
import blog.model.User;
import net.sf.json.JSONObject;

public class AdminM extends HttpServlet {
	private static final long serialVersionUID=1;
	public void doGet(HttpServletRequest req,HttpServletResponse res) 
	throws IOException,ServletException{
		//add code to judge session existence
		String mType=req.getParameter("mType");
		if(mType.equals("pwd")) userPwd(req,res);
		if(mType.equals("close")) delUser(req,res);
		if(mType.equals("blog")) delBlog(req,res);
		if(mType.equals("cmt")) delCmt(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws IOException,ServletException{
		doGet(req,res);
	}


	
	public void userPwd(HttpServletRequest req,HttpServletResponse res) 
			throws IOException,ServletException{
		AdminDao aDao=new AdminDao();
		String newPwd=req.getParameter("newPwd");
		String user=req.getParameter("user");
		boolean rs=aDao.userPwd(user,newPwd);
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
		AdminDao aDao=new AdminDao();
		boolean rs=aDao.deleteUser(user);
			JSONObject jo=new JSONObject();
			if(!rs)jo.put("status", "false");
			else jo.put("status", "true");
			res.setContentType("application/json; charset=utf-8");
			PrintWriter out=res.getWriter();
			out.println(jo);
			out.flush();
			out.close();
	}
	
	public void delBlog(HttpServletRequest req,HttpServletResponse res)
			throws IOException,ServletException{
			int blog=Integer.valueOf(req.getParameter("blog"));
			AdminDao aDao=new AdminDao();
			boolean rs=aDao.deleteBlog(blog);
				JSONObject jo=new JSONObject();
				if(!rs)jo.put("status", "false");
				else jo.put("status", "true");
				res.setContentType("application/json; charset=utf-8");
				PrintWriter out=res.getWriter();
				out.println(jo);
				out.flush();
				out.close();
		}
	
	public void delCmt(HttpServletRequest req,HttpServletResponse res)
			throws IOException,ServletException{
			int cmt=Integer.valueOf(req.getParameter("cmt"));
			AdminDao aDao=new AdminDao();
			boolean rs=aDao.deleteCmt(cmt);
				JSONObject jo=new JSONObject();
				if(!rs)jo.put("status", "false");
				else jo.put("status", "true");
				res.setContentType("application/json; charset=utf-8");
				PrintWriter out=res.getWriter();
				out.println(jo);
				out.flush();
				out.close();
		}
}
