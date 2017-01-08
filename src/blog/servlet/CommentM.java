package blog.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import blog.dao.NoticeDao;
import blog.model.Notice;

import java.io.PrintWriter;
import net.sf.json.JSONArray;

/*
 * operation types:insert delete update, query
 */
public class CommentM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String nType=req.getParameter("nType");
		if(nType.equals("list")) nList(req,res);
		if(nType.equals("show")) nShow(req,res);
		//if(nType.equals("add")) nAdd(req,res);
		//if(nType.equals("del")) nDel(req,res);
		//if(nType.equals("rev")) nRev(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
	public	void nList(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		NoticeDao nDao=new NoticeDao();
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));
		JSONArray jArray=JSONArray.fromObject(nDao.queryCatalog(pageNum));
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println(jArray.toString());
		out.flush();
		out.close();
	}
	
	public void nShow(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		NoticeDao nDao=new NoticeDao();
		int id=Integer.parseInt(req.getParameter("id"));
		Notice notice=nDao.queryNotice(id);
		req.setAttribute("notice", notice);
		RequestDispatcher dispatcher=req.getRequestDispatcher("/jsp/NoticeDetail.jsp");
		dispatcher.forward(req, res);
	}
}
