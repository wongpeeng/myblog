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
import java.text.SimpleDateFormat;
import java.util.Date;

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
		if(cType.equals("prosCmt")) prosCmt(req,res);
		if(cType.equals("consCmt")) consCmt(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
	
	public  void newCmt(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		String content=req.getParameter("content");
		content=CharacterReplace.contentReplace(content);
		int bid=Integer.parseInt(req.getParameter("blogId"));
		String toPerson=req.getParameter("toPerson");
		HttpSession session=req.getSession();
		String critic=((User)session.getAttribute("user")).getName();
		String t=req.getParameter("t");
		CommentDao cDao=new CommentDao();
		int rs=cDao.newCmt(bid,toPerson,critic,content,t);
		JSONObject jo=new JSONObject();
		if(rs==-1) jo.put("status", "false");
		else	jo.put("status" ,"true");
		res.setContentType("application/json; charset=utf-8");
		PrintWriter out=res.getWriter();
		out.println(jo);
		out.flush();
		out.close();
		
	}
	public	void delCmt(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		CommentDao cDao=new CommentDao();
		int id=Integer.parseInt(req.getParameter("cid"));
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
	public  void prosCmt(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		int cmtId=Integer.parseInt(req.getParameter("cid"));
		CommentDao cDao=new CommentDao();
		boolean rs=cDao.prosCmt(cmtId);
		JSONObject jo=new JSONObject();
		if(rs) jo.put("status", "true");
		else jo.put("status", "false");
		res.setContentType("application/json; charset=utf-8");
		PrintWriter out=res.getWriter();
		out.println(jo);
		out.flush();
		out.close();
	}
	public  void consCmt(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		int cmtId=Integer.parseInt(req.getParameter("cid"));
		CommentDao cDao=new CommentDao();
		boolean rs=cDao.consCmt(cmtId);
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
