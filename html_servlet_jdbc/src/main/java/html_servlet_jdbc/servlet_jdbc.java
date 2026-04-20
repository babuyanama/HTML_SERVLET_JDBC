package html_servlet_jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servlet_jdbc
 */
@WebServlet("/servlet_jdbc")
public class servlet_jdbc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet_jdbc() {
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
		int id=Integer.parseInt(request.getParameter("eid"));
		String name=request.getParameter("ename");
		double sal=Double.parseDouble(request.getParameter("esal"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servletdb", "root", "Sudheery@8807");
			PreparedStatement pst=con.prepareStatement("insert into employeedb values(?, ?, ?)");
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setDouble(3, sal);
			int x=pst.executeUpdate();
			System.out.println("Row created");
			PrintWriter pr=response.getWriter();
			pr.print(x);
			response.setContentType("text/html");
			pr.print("<a href='empdb.html'>Go back</a>");
			pr.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
