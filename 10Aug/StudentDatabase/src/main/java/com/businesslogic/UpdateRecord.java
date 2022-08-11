package com.businesslogic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateRecord
 */
@WebServlet("/UpdateRecord")
public class UpdateRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Read data from form
				int rollno=Integer.parseInt(request.getParameter("txtrollno"));
				String name=request.getParameter("txtname");
				int marks=Integer.parseInt(request.getParameter("txtmarks"));
				
				
				//Establish Connection with database
				try {
					Connection con=DatabaseConnection.getMyConnection();	
					
					//Write Query
					
					//String str="Insert into student (rollno,name,marks) values(?,?,?)";
					String str= "update student set name=? ,marks=? where rollno=?";
					
					//to execute query create object of preparedSatement
					PreparedStatement  ps=con.prepareStatement(str);
					
					ps.setString(1, name);
					ps.setInt(2, marks);
					ps.setInt(3,rollno );
								
					//execute query
					int ans =ps.executeUpdate();
					PrintWriter out=response.getWriter();
					if(ans>0)
						out.println("Record Updated");
					else
						out.println("Record not Updated");
				
					//close connection
					con.close();
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}

	}

}
