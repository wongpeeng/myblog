package blog.dao;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.mysql.jdbc.PreparedStatement;
import blog.dbc.DataBaseConn;
import blog.vo.Blog;

public class BlogDao {
		private	DataBaseConn		dbc=null;
		private	List<Blog>		catalog=null;
		private	PreparedStatement	pstat=null;
		private	ResultSet			rs=null;
		private	String				sql=null;
		
		
	public List<Blog> queryCatalog(int pageNum,String bType){
		dbc=new DataBaseConn();
		int num=0+20*(pageNum-1);
		catalog=new LinkedList<Blog>();
		try{
			if(bType.equals("daily")){
				sql="select id,title,author,pubdate,revdate,views,cmtnum from Blog "
					+ "order by revdate desc, views desc limit ?,20"; 
			}
			else
				sql="select id,title,author,pubdate,revdate,views,cmtnum from Blog "
						+ "order by cmtnum desc, views desc limit ?,20"; 
			
			pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
			pstat.setInt(1, num);
			rs=pstat.executeQuery();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				Blog blog=new Blog();
				blog.setId(rs.getInt("id"));
				blog.setTitle(rs.getString("title"));
				blog.setAuthor(rs.getString("author"));
				blog.setPubDate(df.format(rs.getTimestamp("pubdate")));
				blog.setRevDate(df.format(rs.getTimestamp("revdate")));
				blog.setViews(rs.getInt("views"));
				blog.setCmtnum(rs.getInt("cmtnum"));
				catalog.add(blog);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			dbc.close(pstat, rs);
		}
		return catalog;
	}
		
	public Blog queryBlog(int id){
		dbc=new DataBaseConn();
		Blog blog=new Blog();
		try {
			sql="select * from Blog where id=?";
			pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
			pstat.setInt(1, id);
			rs=pstat.executeQuery();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(rs.next()){
				blog.setId(rs.getInt("id"));
				blog.setTitle(rs.getString("title"));
				blog.setAuthor(rs.getString("author"));
				blog.setPubDate(df.format(rs.getTimestamp("pubdate")));
				blog.setRevDate(df.format(rs.getTimestamp("revdate")));
				blog.setViews(rs.getInt("views")+1);
				blog.setCmtnum(rs.getInt("cmtnum"));
				blog.setContent(rs.getString("content"));
				sql="update Blog set views=views+1 where id=?";
				pstat.close();
				pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
				pstat.setInt(1, id);
				pstat.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("fail to display blog!");
			e.printStackTrace();
		}finally {
			dbc.close(pstat, rs);
		}
			return blog;
	}


}
