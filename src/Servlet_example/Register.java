package Servlet_example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class Register
 */

@WebServlet(urlPatterns = {"/Register"})
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Register() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection cn=Dbcon.get();
			
			PreparedStatement p = (PreparedStatement) cn.prepareStatement("INSERT INTO reg (name, sex, email, pwd) VALUES (?,?,?,?)");
			p.setString(1, request.getParameter("name"));
			p.setString(2, request.getParameter("sex"));
			p.setString(3, request.getParameter("email"));
			p.setString(4, request.getParameter("pass1"));
			
			if(	request.getParameter("pass1").equals(request.getParameter("pass2"))){
				p.execute();
				response.sendRedirect("../RegistrationForm/Home.jsp");
			}
			else{
				PrintWriter writer = response.getWriter();
				writer.print("<h3> Password Mismatch </h3> ");
				writer.print("<form action=\"Home.jsp\" method=\"get\"> <input name=\"abcd\" type=\"submit\" value=\"Home\"> </form>");
				writer.print("<form action=\"Registration.htm\" method=\"get\"> <input name=\"abce\" type=\"submit\" value=\"Registration Page\"> </form>");
				//When passwords does not match
				
			}			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
