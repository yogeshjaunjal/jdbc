package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/*  write jdbc app to get  count of record  from student Db table*/
    

public class SelectTest5 {

	public static void main(String[] args) {

		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
		//load jdbc driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
		//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","YOGI1","yogi");
			if(con!=null)
				System.out.println("connection establish");
			else 
				System.out.println("connection is not establish");
			
			//create statement object (vechaile)
			if(con!=null)
				st=con.createStatement();
			
		//prepare SQL Query
			//select count(*) from student;
			String query="SELECT COUNT (*) FROM STUDENT";
			//check query is correct or not
			System.out.println(query);
			
			//Send and Execute SQL Query to DB s/w
			if(st!=null)
				rs=st.executeQuery(query);
			
			//process result set object
			
			if(rs!=null) {
				rs.next();//no record inside a table
				//int count=rs.getInt(1);
			    int count=rs.getInt(1);
			    System.out.println("record cout in student DB is::"+count);
			    }//if
			
		}//try end 
		catch (SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("Please check query");
				se.printStackTrace();
			}
		}//catch end
		catch (Exception e) {
			e.printStackTrace();
		}//catch end
		
		finally {
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}//catch end
		}//finally end
		

	}//main
	
}//class
