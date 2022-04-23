package Servlet_example;

import java.io.IOException;
import java.io.PrintWriter;
//import java.Servlet_example.Dbcon;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection cn = null;
		try {
			cn = Dbcon.get();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PreparedStatement p = (PreparedStatement) cn.prepareStatement("SELECT * from reg WHERE name=? AND pwd=?");
			p.setString(1, request.getParameter("uname"));
			p.setString(2, request.getParameter("pass"));
			HttpSession session=request.getSession();
            ResultSet s=p.executeQuery();
			if(s.next()){
				session.setAttribute("stat", request.getParameter("uname"));
				//PrintWriter writer = response.getWriter();
				//writer.print("<body background=\"C:/Users/Jadeer/javaworkspace/RegistrationForm/WebContentHo.jpg\" style=\"background-size:100%;\"");
				//writer.print("<h3> Hello ");
				//writer.print(request.getParameter("uname") + "! </h3>");
				response.sendRedirect("../RegistrationForm/Home.jsp");
			}
            else{
            	session.setAttribute("error", "invalid login");
            	response.sendRedirect("../RegistrationForm/Home.jsp");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//doGet(request, response);
	}
}
