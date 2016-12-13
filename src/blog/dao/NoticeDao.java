package blog.dao;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.mysql.jdbc.PreparedStatement;
import blog.dbc.DataBaseConn;
import blog.model.Notice;

public class NoticeDao {
		private	DataBaseConn		dbc=null;
		private	List<Notice>		notice=null;
		private	PreparedStatement	pstat=null;
		private	ResultSet			rs=null;
		private	String				sql=null;
		
		
	public List<Notice> queryCatalog(int pageNum){
		dbc=new DataBaseConn();
		int num=0+10*(pageNum-1);
		notice=new LinkedList<Notice>();
		try{
			sql="select id,title,author,strDate,endDate from Notice "
				+ "where endDate>=current_date() order by level desc, endDate asc limit ?,10";  
			pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
			pstat.setInt(1, num);
			rs=pstat.executeQuery();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			while(rs.next()){
				Notice note=new Notice();
				note.setId(rs.getInt("id"));
				note.setTitle(rs.getString("title"));
				note.setAuthor(rs.getString("author"));
				note.setStrDate(df.format(rs.getDate("strDate")));
				note.setEndDate(df.format(rs.getDate("endDate")));
				notice.add(note);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			dbc.close(pstat, rs);
		}
		return notice;
	}
		
	public Notice queryNotice(int id){
		Notice note=new Notice();
		dbc=new DataBaseConn();
		try {
			sql="select * from Notice where id=?";
			pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
			pstat.setInt(1, id);
			rs=pstat.executeQuery();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			if(rs.next()){
				note.setId(rs.getInt("id"));
				note.setTitle(rs.getString("title"));
				note.setAuthor(rs.getString("author"));
				note.setStrDate(df.format(rs.getDate("strDate")));
				note.setEndDate(df.format(rs.getDate("endDate")));
				note.setContent(rs.getString("content"));
			}
		} catch (Exception e) {
			System.out.println("fail to display notice!");
			e.printStackTrace();
		}finally {
			dbc.close(pstat, rs);
		}
		return note;
	}


}
