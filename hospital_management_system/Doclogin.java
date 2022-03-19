package com.hospdbms.pkg;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet implementation class Doclogin
 */
@WebServlet(urlPatterns="/Doclogin")
public class Doclogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Doclogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String driverName = "com.mysql.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:3306/";
		String dbName = "hospital_database";
		String userId = "root";
		String password = "";
		String pwdd=null;
		String dname=request.getParameter("uname");
		String psswd=request.getParameter("pwd");
		try {
			Class.forName(driverName);
			} catch (ClassNotFoundException e) {
				System.out.println("sadasd");
			}

			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			try{ 
				connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
				statement=connection.createStatement();
				String sql ="SELECT psswd FROM doc_info where dname='"+dname+"'";

				resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
				pwdd=resultSet.getString("psswd");
				}
		if(psswd.equalsIgnoreCase(pwdd))
		{	session.setAttribute("Name",dname);
			request.getRequestDispatcher("docpage.jsp").forward(request, response);
		}
	
		else {
			request.setAttribute("error", "Invalid username/password.");
			request.getRequestDispatcher("Doclogin.jsp").forward(request, response);
	}
	}catch(SQLException e) { 
		System.out.println("kkrk");
		e.printStackTrace();
	}
			}}
