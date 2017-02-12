package blog.dao;
import java.sql.*;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.PreparedStatement;

import blog.dbc.DataBaseConn;
import blog.model.User;

public class UserDao {
		private	DataBaseConn		dbc=null;
		private	User				user=null;
		private	PreparedStatement	pstat=null;
		private	ResultSet			rs=null;
		private	String				sql=null;
	/*
	* <p>verify user to login<br>
	* @return is exist user is not null
	*/
	public User checkUser(String name,String pwd){
		dbc=new DataBaseConn();
		sql="select * from User where name=? and pwd=?";
		try{
			pstat=(PreparedStatement) dbc.getConn().prepareStatement(sql);
			pstat.setString(1,name);
			pstat.setString(2,pwd);
			rs=pstat.executeQuery();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(rs.next()){
				user=new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setGrp(rs.getString("grp"));
				user.setPwd(rs.getString("pwd"));
				user.setRegDate(df.format(rs.getDate("regDate")));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally{
			dbc.close(pstat,rs);
		}
		return user;
	}
	/*
	 * <p>register a new user<br>
	 * @return true->successful,false->failed
	 */
	public boolean insertUser(String name,String pwd){
		dbc=new DataBaseConn();
		String grp="user";
		sql="insert into User(name,grp,pwd) select ?,?,? from dual "
				+ "where not exists(select * from User where name=?)";
		try{
			PreparedStatement pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
			pstat.setString(1,name);
			pstat.setString(2,grp);
			pstat.setString(3,pwd);
			pstat.setString(4,name);
			int col=pstat.executeUpdate();
			if(col==0) return false;
			else return true;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
		finally{
			dbc.close(pstat,rs);
		}
	}
	/*
	 * <p>delete a user from blog system<br>
	 * with personal info.,articles,comments
	 * @return true->successful,false->failed
	 */
	public boolean deleteUser(String name,String pwd){
		dbc=new DataBaseConn();
		boolean r=true;
		Connection conn=dbc.getConn();
		try{
			conn.setAutoCommit(false);
			sql="delete from User where name=? and pwd=?";
			pstat=(PreparedStatement) dbc.getConn().prepareStatement(sql);
			pstat.setString(1, name);
			pstat.setString(2, pwd);
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
	public boolean userPwd(String name,String oldPwd,String newPwd){
		dbc=new DataBaseConn();
		boolean r=false;
		try{
			sql="update User set pwd=? where name=? and pwd=?";
			pstat=(PreparedStatement)dbc.getConn().prepareStatement(sql);
			pstat.setString(1,newPwd);
			pstat.setString(2, name);
			pstat.setString(3, oldPwd);
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

}
