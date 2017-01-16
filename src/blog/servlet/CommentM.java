package blog.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.dao.CommentDao;
import blog.dao.NoticeDao;
import blog.model.Notice;
import blog.model.User;
import blog.util.CharacterReplace;

import java.io.PrintWriter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * operation types:insert delete update, query
 */
public class CommentM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String cType=req.getParameter("cType");
		if(cType.equals("newCmt")) newCmt(req,res);
		if(cType.equals("delCmt")) delCmt(req,res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
	
	public  void newCmt(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		String content=req.getParameter("content");
		content=CharacterReplace.contentReplace(content);
		int bid=Integer.parseInt(req.getParameter("bid"));
		String toPerson=req.getParameter("toPerson");
		HttpSession session=req.getSession();
		String critic=((User)session.getAttribute("user")).getName();
		String t=req.getParameter("t");
		CommentDao cDao=new CommentDao();
		boolean rs=cDao.newCmt(bid,toPerson,critic,content,t);
		JSONObject jo=new JSONObject();
		if(rs) jo.put("status", "true");
		else jo.put("status", "false");
		res.setContentType("application/json; charset=utf-8");
		PrintWriter out=res.getWriter();
		out.println(jo);
		out.flush();
		out.close();
		
	}
	public	void delCmt(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		CommentDao cDao=new CommentDao();
		int id=Integer.parseInt(req.getParameter("id"));
		boolean rs=cDao.delCmt(id);
		JSONObject jo=new JSONObject();
		if(rs) jo.put("status", "true");
		else jo.put("status", "false");
		res.setContentType("application/json; charset=utf-8");
		PrintWriter out=res.getWriter();
		out.println(jo);
		out.flush();
		out.close();
	}
	
	
}
