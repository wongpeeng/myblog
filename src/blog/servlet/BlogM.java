package blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import blog.dao.BlogDao;
import blog.vo.Blog;

/**
 * Servlet implementation class content
 */
@WebServlet(description = "generate content to display", urlPatterns = { "/content" })
public class BlogM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogM() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bType=req.getParameter("blogType");
		if(bType.equals("daily")||bType.equals("weekly"))getCatalog(req,res,bType);
		if(bType.equals("show")) getBlog(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
	
	public void getCatalog(HttpServletRequest req,HttpServletResponse res,String bType)
			throws IOException,ServletException{
		BlogDao bDao=new BlogDao();
		int n=Integer.parseInt(req.getParameter("pageNum"));
		JSONArray jArray=JSONArray.fromObject(bDao.queryCatalog(n,bType));
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println(jArray.toString());
		out.flush();
		out.close();
	}
	
	public void getBlog(HttpServletRequest req,HttpServletResponse res)
			throws IOException,ServletException{
		BlogDao bDao=new BlogDao();
		int id=Integer.parseInt(req.getParameter("id"));
		Blog blog=bDao.queryBlog(id);
		req.setAttribute("blog", blog);
		RequestDispatcher dispatcher=req.getRequestDispatcher("/jsp/BlogDetail.jsp");
		dispatcher.forward(req, res);
	}
}
