package Servlet_example;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Dbcon {
	public static  Connection get() throws ClassNotFoundException, SQLException
	{
		Connection con;
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root" , "root");
		return con;
	}
}
