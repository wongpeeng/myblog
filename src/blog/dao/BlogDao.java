package blog.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.mysql.jdbc.PreparedStatement;
import blog.dbc.DataBaseConn;
import blog.model.Blog;

public class BlogDao {
	private DataBaseConn dbc = null;
	private List<Blog> catalog = null;
	private PreparedStatement pstat = null;
	private ResultSet rs = null;
	private String sql = null;

	public List<Blog> queryCatalog(int pageNum, String bType) {
		dbc = new DataBaseConn();
		int num = 0 + 10 * (pageNum - 1);
		catalog = new LinkedList<Blog>();
		try {
			if (bType.equals("daily")) {
				sql = "select id,title,author,pubDate,revDate,views,cmt from Blog "
						+ "order by revDate desc, views desc limit ?,10";
			} else
				sql = "select id,title,author,pubDate,revDate,views,cmt from Blog "
						+ "order by cmt desc, views desc limit ?,10";

			pstat = (PreparedStatement) dbc.getConn().prepareStatement(sql);
			pstat.setInt(1, num);
			rs = pstat.executeQuery();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (rs.next()) {
				Blog blog = new Blog();
				blog.setId(rs.getInt("id"));
				blog.setTitle(rs.getString("title"));
				blog.setAuthor(rs.getString("author"));
				blog.setPubDate(df.format(rs.getTimestamp("pubDate")));
				blog.setRevDate(df.format(rs.getTimestamp("revDate")));
				blog.setViews(rs.getInt("views"));
				blog.setCmt(rs.getInt("cmt"));
				catalog.add(blog);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close(pstat, rs);
		}
		return catalog;
	}

	public Blog queryBlog(int id) {
		dbc = new DataBaseConn();
		Blog blog = new Blog();
		try {
			sql = "select * from Blog where id=?";
			pstat = (PreparedStatement) dbc.getConn().prepareStatement(sql);
			pstat.setInt(1, id);
			rs = pstat.executeQuery();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (rs.next()) {
				blog.setId(rs.getInt("id"));
				blog.setTitle(rs.getString("title"));
				blog.setAuthor(rs.getString("author"));
				blog.setPubDate(df.format(rs.getTimestamp("pubDate")));
				blog.setRevDate(df.format(rs.getTimestamp("revDate")));
				blog.setViews(rs.getInt("views") + 1);
				blog.setCmt(rs.getInt("cmt"));
				blog.setContent(rs.getString("content"));
				sql = "update Blog set views=views+1 where id=?";
				pstat.close();
				pstat = (PreparedStatement) dbc.getConn().prepareStatement(sql);
				pstat.setInt(1, id);
				pstat.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("fail to display blog!");
			e.printStackTrace();
		} finally {
			dbc.close(pstat, rs);
		}
		return blog;
	}

	public boolean newBlog(String title, String author, String content) {
		dbc = new DataBaseConn();
		boolean r = false;
		try {
			sql = "insert into Blog(title,author,content) values(?,?,?)";
			pstat = (PreparedStatement) dbc.getConn().prepareStatement(sql);
			pstat.setString(1, title);
			pstat.setString(2, author);
			pstat.setString(3, content);
			int c = pstat.executeUpdate();
			if (c != 0)
				r = true;
		} catch (SQLException e) {
			System.out.println("fail to insert new blog!");
			e.printStackTrace();
		} finally {
			dbc.close(pstat, rs);
		}
		return r;
	}

	public List<Blog> myBlogList(int pageNum, String name) {
		dbc = new DataBaseConn();
		int num = 0 + 10 * (pageNum - 1);
		catalog = new LinkedList<Blog>();
		try {
			sql = "select id,title,revDate,cmt from Blog " + "order by revDate desc, cmt desc limit ?,10";
			pstat = (PreparedStatement) dbc.getConn().prepareStatement(sql);
			pstat.setInt(1, num);
			rs = pstat.executeQuery();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (rs.next()) {
				Blog blog = new Blog();
				blog.setId(rs.getInt("id"));
				blog.setTitle(rs.getString("title"));
				blog.setRevDate(df.format(rs.getTimestamp("revDate")));
				blog.setCmt(rs.getInt("cmt"));
				catalog.add(blog);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close(pstat, rs);
		}
		return catalog;
	}

	public boolean delBlog(int blogId){
		dbc=new DataBaseConn();
		boolean r=false;
		try{
			sql="delete from Blog where id=?";
			pstat = (PreparedStatement) dbc.getConn().prepareStatement(sql);
			pstat.setInt(1, blogId);
			int c=pstat.executeUpdate();
			if(c!=0) r=true;
		}catch(SQLException e){
			System.out.println("failed to delete blog!");
			e.printStackTrace();
		}finally{
			dbc.close(pstat, rs);
		}
		return r;
	}
}
