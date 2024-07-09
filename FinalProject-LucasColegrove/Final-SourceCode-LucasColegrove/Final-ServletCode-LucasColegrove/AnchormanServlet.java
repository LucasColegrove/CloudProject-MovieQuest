
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

import javax.servlet.*;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;


public class AnchormanServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       

    public AnchormanServlet() 
    {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		final PrintWriter out = response.getWriter();
		
		int id = 7;
		String name = "";
		String genre = "";
		String rating = "";
		String summary = "";
		String runtime = "";
		String director = "";
		String score = "";
		
		

		 String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		 String DB_URL = "jdbc:mysql://localhost:3306/movieDB";

		 String USER = "root";
		 String PASS = "";

		 Connection conn = null;
		 Statement stmt = null;

		 try {
			Class.forName("com.mysql.jdbc.Driver");

			 System.out.println("Connecting to database...");
			 conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);

			 System.out.println("Creating statement...");
			 stmt = (Statement) conn.createStatement();
			 String sql;
			 sql = "SELECT * FROM movie WHERE id='" + id + "';";
			 ResultSet rs = (ResultSet) stmt.executeQuery(sql);

			 while(rs.next())
			 {
				 name = rs.getString("name");
				 genre = rs.getString("genre");
				 rating = rs.getString("rating");
				 summary = rs.getString("summary");
				 runtime = rs.getString("runtime");
				 director = rs.getString("director");
				 score = rs.getString("score");
			 }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}

		String docType = "<!doctype html public \"-//w3c//dtd thml 4.0 transitional//en\">\n";
		out.println(docType + 
		"<html>\n"+
		"<head><title>Movie Info</title>\n"+
		"<link rel='stylesheet' href='test.css?v=1.0'>\n"+
		"<link href='https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700&family=Sen:wght@400;700;800&display=swap' rel='stylesheet'>\n"+
		"<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css'>\n"+
		"</head>\n"+
		"<body>\n"+
		"<div class='logo'>MovieQuest</div>\n"+
		"<button onclick='history.back()' class='back-button'>Back</button>\n"+
		"<h1 class='title' align=\"center\"><img src='MOVIE_IMAGES/7.jpg'> <br> "+ name +"</h1>\n"+
		"<div class='info-border' style='background-color: orange;'>\n"+
		"<ul>\n"+
		" <li><b>Genre</b>: " + genre+"\n"+
		" <li><b>Rating</b>: " + rating+"\n"+
		" <li><b>Summary</b>: " + summary+"\n"+
		" <li><b>Runtime</b>: " + runtime+ " minutes\n"+
		" <li><b>Director</b>: " + director+"\n"+
		" <li><b>Score</b>: " + score+"/10 <i class='fa-solid fa-star'></i>\n"+
		"</ul>\n"+
		"</div>"+
		"</body></html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}

}
