package blog.dao;
import java.sql.*;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.PreparedStatement;

import blog.dbc.DataBaseConn;
import blog.model.User;

public class AdminDao {
		private	DataBaseConn		dbc=null;
		private	User				user=null;
		private	PreparedStatement	pstat=null;
		private	ResultSet			rs=null;
		private	String				sql=null;

	/*
	 * <p>delete a user from blog system<br>
	 * with personal info.,articles,comments
	 * @return true->successful,false->failed
	 */
	public boolean deleteUser(String name){
		if(name.equals("wongpeeng")) return false;
		dbc=new DataBaseConn();
		boolean r=true;
		Connection conn=dbc.getConn();
		try{
			conn.setAutoCommit(false);
			sql="delete from User where name=?";
			pstat=(PreparedStatement) dbc.getConn().prepareStatement(sql);
			pstat.setString(1, name);
			pstat.executeUpdate();
			String sql2="delete from Blog where author=?";
			pstat=(PreparedStatement) dbc.getConn().prepareStatement(sql2);
			pstat.setString(1,name);
			pstat.executeUpdate();
			String sql3="delete from Comment where toPerson=? or critic=?";
			pstat=(PreparedStatement) dbc.getConn().prepareStatement(sql3);
			pstat.setString(1,name);
			pstat.setString(2, name);
			pstat.executeUpdate();
			conn.commit();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("fail to delete user!");
			try{conn.rollback();}
			catch(SQLException e2){
				System.out.println("fail to delete user and roll back!");
			};
			r=false;
		}
		finally{
			dbc.close(pstat,rs);
		}
		return r;
	}
	/*
	 * <p>update user's password and grp<br>
	 * @return true->successful,false->failed
	 */
	public boolean userPwd(String name,String newPwd){
		dbc=new DataBaseConn();
		boolean r=false;
		try{
			sql="update User set pwd=? where name=?";
			pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
			pstat.setString(1,newPwd);
			pstat.setString(2, name);
			int col=pstat.executeUpdate();
			if(col!=0)r=true;
		}catch(SQLException e){
			System.out.println("fail to update password!");
			e.printStackTrace();
		}finally{
			dbc.close(pstat, rs);
		}
		return r;
	}
	
	public boolean deleteBlog(int id){
		dbc=new DataBaseConn();
		boolean r=true;
		Connection conn=dbc.getConn();
		try{
			conn.setAutoCommit(false);
			sql="delete from Blog where id=?";
			pstat=(PreparedStatement) dbc.getConn().prepareStatement(sql);
			pstat.setInt(1,id);
			pstat.executeUpdate();
			String sql2="delete from Comment where logId=?";
			pstat=(PreparedStatement) dbc.getConn().prepareStatement(sql2);
			pstat.setInt(1,id);
			pstat.executeUpdate();
			conn.commit();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("fail to delete blog");
			try{conn.rollback();}
			catch(SQLException e2){
				System.out.println("fail to delete blog and roll back!");
			};
			r=false;
		}
		finally{
			dbc.close(pstat,rs);
		}
		return r;
	}
	
	public boolean deleteCmt(int id){
		dbc=new DataBaseConn();
		boolean r=false;
		Connection conn=dbc.getConn();
		try{
			sql="delete from Comment where id=?";
			pstat=(PreparedStatement) dbc.getConn().prepareStatement(sql);
			pstat.setInt(1,id);
			int col=pstat.executeUpdate();
			if(col!=0) r=true;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("fail to delete comment!");
		}
		finally{
			dbc.close(pstat,rs);
		}
		return r;
	}

}
