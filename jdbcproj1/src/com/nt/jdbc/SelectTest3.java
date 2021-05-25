//Select.java
package com.nt.jdbc;
/*  Java App to get Employee details based on given intial characters   Employee name  */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest3 {

	public static void main(String[] args) {
    Scanner sc=null;
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
  
    
    try {
    	
    	sc=new Scanner(System.in);
    	  String initChars=null;
    	System.out.println("Enter intial character of employee::");
    	initChars=sc.next();//give s
    	
    	//convert input values as required SQl query
    			
		initChars ="'"+initChars+"%'";  //gives 's%'
    	
    	//register jdbc driver
    	//Class.forName("oracle.jdbc.driver.OracleDriver");
    	
    		//estabilish connection 
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","YOGI1", "yogi");
		  if(con!=null) {
			  System.out.println("connection establish");
		  }else {
			  System.out.println("connection is not establish");
		  }
    		
    		//create statement object
		  if(con!=null)
    		st=con.createStatement();
    		
    		   //prepare SQL Query
    		//select eno,ename,job,sal from emp where emp like 's%';
    		String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE "+initChars;
    		
    		//check query is correct or not
    		System.out.println(query);
    		
    		//to send and execute SQL query in DB s/w
    		if(st!=null)
    		rs=st.executeQuery(query);
    		
    		//process ResultSet object
    		if(rs!=null) {
    			boolean flag=false;
    			while(rs.next()) {
    				flag=true;
		    		 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"   "+rs.getString(3)+"  "+rs.getFloat(4));
    				
    			}//while end
    			if(flag==false)
    				System.out.println("NO RECORD FOUND");
    		}//if end
    		
     }//try end
    
    catch (SQLException se) {
		if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
			System.out.println("Invalid col names or table names or SQL keywords");
		se.printStackTrace();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
    
    finally {
    	
		try {
			if(rs!=null)
				rs.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}//catch
		
		try {
			if(con!=null)
			con.close();
		} catch (SQLException e2) {
           e2.printStackTrace();
		}//catch
		
		try {
			if(st!=null)
				st.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}//catch
		
		try {
			if(sc!=null)
				sc.close();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}//catch
		
	}//finally
   }//main
}//class


