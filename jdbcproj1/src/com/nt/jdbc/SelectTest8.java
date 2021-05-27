package com.nt.jdbc;
/* write a jdbc app to get employee whose having nth higthest salary */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class SelectTest8{

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		
		try {
			sc=new Scanner(System.in);
			float sal=0;
			if(sc!=null) 
			System.out.println("Enter employee nth no highest  sal::");
			sal=sc.nextFloat();
			
			//LOAD JDBC 
			 //  Class.forName("oracle.jdbc.driver.OracleDriver");
				
			   //establish connection 
			   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","YOGI1","yogi");
			  //check connection
			   
			   if(con!=null)
				   System.out.println("connection is establish");
			   else
				   System.out.println("connection is not establish");
			   
			   //create statement object
			   if(con!=null)
			   st=con.createStatement();
			   
			   //preper query
			   String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)";
			   
			   //CHECK query is correct or not
			   System.out.println(query);
			   
			   //process result object
			   
			   if(st!=null)
				   rs=st.executeQuery(query);
			   
			   //Send and execte SQL query in DB s/w
			   boolean flag =false;
			   if(rs!=null) {
				   while(rs.next()) {
					   flag =true;
					   System.out.println(rs.getInt(1)+"     "+rs.getString(2)+"    "+rs.getString(3)+"     "+rs.getFloat(4));
				   }
				   if(flag==false)
					   System.out.println("please enter corrct Query ");
				   
			   }//if		
		} //try end
		catch (SQLException se) {
			
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("invalid sql query");
			se.printStackTrace();
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
