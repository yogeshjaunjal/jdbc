package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableTest {

	public static void main(String[] args) {

		Statement st=null;
		ResultSet rs=null;
		Connection con=null;
		try {
			
			//load jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "YOGI1", "yogi");
		
			//Create  Statement object (veachale)
	            if(con!=null)
				st=con.createStatement();
	            //prepere SQL query
	            String query= "CREATE TABLE EMP11 (EMPNO NUMBER(10),ENAME VARCHAR2(10),EADD VARCHAR2(10),ESAL NUMBER(10))";
	            System.out.println(query);
	            
	            //send and execute sql query in DB s/w
	           int count=0;
	         if(st!=null)
	        	count=st.executeUpdate(query);
	         
	         //process the result
	         if(count==0)
	        	 System.out.println("table is created");
	         else
	        	 System.out.println("table is not created"+count);
	 
	           
	       }//try
		catch (SQLException se) {
			if(se.getErrorCode()==955) {
				System.err.println("name is already used by an existing object");
			}
			else{
				if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("invalid sql query");
				se.printStackTrace();
			}//else
				
			}//if
		}//catch

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
			}


			


		}//finally

	}//main
}//class
