package blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import blog.dao.BlogDao;
import blog.model.Blog;
import blog.model.User;
import blog.util.CharacterReplace;

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
		if(bType.equals("newBlog")) addBlog(req,res);
		if(bType.equals("myBlog")) myBlogList(req,res);
		if(bType.equals("delBlog")) delBlog(req,res);
		if(bType.equals("edit"))	editBlog(req,res);
		if(bType.equals("updBlog")) updateBlog(req,res);
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
	
	public void addBlog(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String title=req.getParameter("title");
		String content=req.getParameter("content");
		///replace special character in title
		title=CharacterReplace.titleReplace(title);
		//replace special character in content
		content=CharacterReplace.contentReplace(content);
		BlogDao bDao=new BlogDao();
		HttpSession session=req.getSession();
		User u=(User) session.getAttribute("user");
		boolean rs=bDao.newBlog(title,u.getName(), content);
		res.setContentType("application/json; charset=utf-8");
		PrintWriter out = res.getWriter();
		JSONObject jo=new JSONObject();
		if(rs) jo.put("status", "true");
		else jo.put("status", "false");
		out.println(jo);
		out.flush();
		out.close();
	}
	
	public void myBlogList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		BlogDao bDao=new BlogDao();
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));
		HttpSession session=req.getSession();
		User u=(User) session.getAttribute("user");
		String name=u.getName();
		List<Blog> bList=new LinkedList<>();
		bList=bDao.myBlogList(pageNum,name);
		req.setAttribute("bList", bList);
		RequestDispatcher dispatcher=req.getRequestDispatcher("/jsp/MyBlogList.jsp");
		dispatcher.forward(req, res);
	}

	public void delBlog(HttpServletRequest req,HttpServletResponse res) throws ServletException ,IOException{
		BlogDao bDao=new BlogDao();
		int blogId=Integer.parseInt(req.getParameter("id"));
		boolean rs=bDao.delBlog(blogId);
		res.setContentType("application/json; charset=utf-8");
		PrintWriter out = res.getWriter();
		JSONObject jo=new JSONObject();
		if(rs) jo.put("status", "true");
		else jo.put("status", "false");
		out.println(jo);
		out.flush();
		out.close();
	}

	public void editBlog(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		BlogDao bDao=new BlogDao();
		int blogId=Integer.parseInt(req.getParameter("id"));
		Blog blog=bDao.queryBlog(blogId);
		String t=CharacterReplace.titleRestore(blog.getTitle());
		String c=CharacterReplace.contentRestore(blog.getContent());
		blog.setTitle(t);
		blog.setContent(c);
		req.setAttribute("blog", blog);
		req.setAttribute("type", "blog");
		RequestDispatcher dispatcher=req.getRequestDispatcher("/jsp/Edit.jsp");
		dispatcher.forward(req, res);
	}
	
	public void updateBlog(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		BlogDao bDao=new BlogDao();
		int blogId=Integer.parseInt(req.getParameter("id"));
		String title=req.getParameter("title");
		String content=req.getParameter("content");
		///replace special character in title
		title=CharacterReplace.titleReplace(title);
		//replace special character in content
		content=CharacterReplace.contentReplace(content);
		boolean rs=bDao.updateBlog(blogId,title,content);
		res.setContentType("application/json; charset=utf-8");
		PrintWriter out = res.getWriter();
		JSONObject jo=new JSONObject();
		if(rs) jo.put("status", "true");
		else jo.put("status", "false");
		out.println(jo);
		out.flush();
		out.close();
	}
	
}
