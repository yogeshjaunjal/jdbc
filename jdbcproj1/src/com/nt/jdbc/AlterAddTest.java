package com.nt.jdbc;
//using alter add query we are add new colum in existing table

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AlterAddTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
try {
	//read input
	   String tableName=null;
	   String newColumName=null;
	   String newDataType=null;
	    sc=new Scanner(System.in);
	    System.out.println("enter Existing tableName:");
	    tableName=sc.next().toUpperCase();
	    System.out.println("enter new colum Name :");
	    newColumName=sc.next().toUpperCase();
	    System.out.println("enter new colum Name with data type:");
	    newDataType=sc.next().toUpperCase();
	    
	    
	    //load jdbc Driver
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    
	    //establish connection
	    con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","YOGI1","yogi");
	    
	    //create jdbc object  (vehicle)
	    if(con!=null)
	    	st=con.createStatement();
	    
	    //prepre sql query 
	    //alter table student add srollno number(10)
	    String query="ALTER TABLE "+tableName+" ADD "+newColumName+" "+newDataType;
	    System.out.println(query);
	    
	    //send and exeute sql query to DB s/w
	     
	  int count=0;
	  if(st!=null)
		  count=st.executeUpdate(query);
	  
	  //process result
	  
	 if(count==0)
		 System.out.println("new colum is added");
	 else
		 System.out.println("new colum is not added");
	 
}//try end

catch (SQLException se) {
	if(se.getErrorCode()==1735) {
		System.out.println("invalid ALTER TABLE option");
		se.printStackTrace();
	}
	if(se.getErrorCode()>=900 && se.getErrorCode()<=999 ) {
		System.out.println("invalid identifier");
		se.printStackTrace();
	}else {
		System.out.println("invalid query");
	}
}//catch

catch (Exception e) {
	e.printStackTrace();

}//catch end

finally {


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
	try {
		if(sc!=null)
			sc.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	


}//finally

}//main
}//class












