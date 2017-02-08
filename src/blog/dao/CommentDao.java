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
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				Comment cmt=new Comment();
				cmt.setId(rs.getInt("id"));
				cmt.setToPerson(rs.getString("toPerson"));
				cmt.setCritic(rs.getString("critic"));
				cmt.setPubDate(df.format(rs.getTimestamp("pubDate")));
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
	
	public boolean delCmt(int cid){
		dbc=new DataBaseConn();
		boolean r=false;
		try{
			sql="delete from Comment where id=?";
			pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
			pstat.setInt(1, cid);
			int col=pstat.executeUpdate();
			if(col!=0) r=true;
		}catch(SQLException e){
			System.out.println("fail to delete comment");
			e.printStackTrace();
		}finally{
			dbc.close(pstat, rs);
		}
		return r;
	}

	public int newCmt(int blogId,String toPerson,String critic,String content,String t){
		dbc=new DataBaseConn();
		int r=-1;
		try{
			sql="insert into Comment(logId,toPerson,critic,content,ctype) values(?,?,?,?,?)";
			pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
			pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
			pstat.setInt(1, blogId);
			pstat.setString(2, toPerson);
			pstat.setString(3,critic);
			pstat.setString(4, content);
			pstat.setString(5, t);
			pstat.executeUpdate();
			rs=pstat.getGeneratedKeys();
			if(rs.next()){
				r=rs.getInt(1);
			}
			
		}catch(SQLException e){
			System.out.println("fail to create new comment!");
			e.printStackTrace();
		}finally{
			dbc.close(pstat, rs);
		}
		return r;
	}

	public boolean prosCmt(int cmtId){
		dbc=new DataBaseConn();
		boolean r=false;
		try{
			sql="update Comment set pros=pros+1 where id=?";
			pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
			pstat.setInt(1, cmtId);
			int col=pstat.executeUpdate();
			if(col!=0)r=true;
		}catch(SQLException e){
			System.out.println("fail to pros");
			e.printStackTrace();
		}finally{
			dbc.close(pstat, rs);
		}
		return r;
	}

	public boolean consCmt(int cmtId){
		dbc=new DataBaseConn();
		boolean r=false;
		try{
			sql="update Comment set cons=cons+1 where id=?";
			pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
			pstat.setInt(1, cmtId);
			int col=pstat.executeUpdate();
			if(col!=0)r=true;
		}catch(SQLException e){
			System.out.println("fail to cons");
			e.printStackTrace();
		}finally{
			dbc.close(pstat, rs);
		}
		return r;
	}
}
