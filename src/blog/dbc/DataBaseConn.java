package blog.dbc;

import java.sql.*;

public class DataBaseConn {
	private static String DBDriver="com.mysql.jdbc.Driver";
	private static String DBUrl="jdbc:mysql://localhost:3306/myblog";
	private static String DBUser="root";
	private static String DBPwd="wp19890818";
	private Connection conn=null;
	
	public Connection getConn(){
		if(conn==null){
		try{
				Class.forName(DBDriver);
				conn=DriverManager.getConnection(DBUrl,DBUser,DBPwd);
				System.out.println("loading mysql driver successfully!");
			}
			catch(Exception e){
				System.out.println("fail to load mysql driver!");
				System.out.println(e.getMessage());
			}
		}
		return conn;
	}
	
	public void close(PreparedStatement pstat,ResultSet rs){
		try{
			if(pstat!=null)pstat.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		try{
			if(rs!=null)rs.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		try{
			conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
