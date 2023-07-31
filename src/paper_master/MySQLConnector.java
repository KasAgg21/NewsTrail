package paper_master;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnector 
{
	static Connection conn;

	public static Connection doConnect()
	{
	
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/npp","root","@Kas21mas");
			System.out.println("Connected");
		}
		catch(Exception exp)
		{
			System.out.println(exp);
		}
		return conn;
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		doConnect();

	}

}
