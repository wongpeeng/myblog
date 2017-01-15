package blog.dao;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.mysql.jdbc.PreparedStatement;
import blog.dbc.DataBaseConn;
import blog.model.Comment;

public class CommentDao {
		private	DataBaseConn		dbc=null;
		private	List<Comment>		comment=null;
		private	PreparedStatement	pstat=null;
		private	ResultSet			rs=null;
		private	String				sql=null;
		
		
	public List<Comment> queryComment(int logId){
		dbc=new DataBaseConn();
		comment=new LinkedList<Comment>();
		try{
			sql="select id,toPerson,critic,pubDate,content,pros,cons,ctype from Comment "
					+ "where logId=? order by pubDate asc ";  
			pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
			pstat.setInt(1, logId);
			rs=pstat.executeQuery();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			while(rs.next()){
				Comment cmt=new Comment();
				cmt.setId(rs.getInt("id"));
				cmt.setToPerson(rs.getString("toPerson"));
				cmt.setCritic(rs.getString("critic"));
				cmt.setPubDate(df.format(rs.getDate("pubDate")));
				cmt.setContent(rs.getString("content"));
				cmt.setPros(rs.getInt("pros"));
				cmt.setCons(rs.getInt("cons"));
				cmt.setCtype(rs.getString("ctype"));
				comment.add(cmt);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			dbc.close(pstat, rs);
		}
		return comment;
	}
		

}
