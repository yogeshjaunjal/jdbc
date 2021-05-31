package com.nt.jdbc;
//using alter add query we are add new colum in existing table

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RenameTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
try {
	//read input
	   String  oldTableName=null;
	  String newTableName=null;
	    sc=new Scanner(System.in);
	    System.out.println("enter Existing table Name:");
	    oldTableName=sc.next().toUpperCase();
	    System.out.println("enter  new table Name :");
	    newTableName=sc.next().toUpperCase();
	   
	    
	    
	    //load jdbc Driver
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    
	    //establish connection
	    con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","YOGI1","yogi");
	    
	    //create jdbc object  (vehicle)
	    if(con!=null)
	    	st=con.createStatement();
	    
	    //prepre sql query 
	    //> RENAME STUDENT TO STUDENTDETAILS
	    String query="RENAME "+oldTableName+" TO "+newTableName;
	    System.out.println(query);
	    
	    //send and exeute sql query to DB s/w
	     
	  int count=0;
	  if(st!=null)
		  count=st.executeUpdate(query);
	  
	  //process result
	  
	 if(count==0)
		 System.out.println("table is rename");
	 else
		 System.out.println(" table is not rename");
	 
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












