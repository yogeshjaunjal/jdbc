package com.nt.jdbc;
//using alter add query we are add new colum in existing table

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AlterRename {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
try {
	//read input
   String	tableName=null;
	   String  oldColumnName=null;
	  String newColumnName=null;
	    sc=new Scanner(System.in);
	    System.out.println("enter Existing table Name:");
	    tableName=sc.next().toUpperCase();
	    System.out.println("enter Existing column Name:");
	    oldColumnName=sc.next().toUpperCase();
	    System.out.println("enter  new colum Name :");
	    newColumnName=sc.next().toUpperCase();
	   
	    
	    
	    //load jdbc Driver
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    
	    //establish connection
	    con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","YOGI1","yogi");
	    
	    //create jdbc object  (vehicle)
	    if(con!=null)
	    	st=con.createStatement();
	    
	    //prepre sql query 
	    //> ALTER TABLE STUDENT RENAME COLUMN SNAME TO STUDENTNAMES;
	    String query="ALTER TABLE "+tableName+" RENAME COLUMN "+oldColumnName+" TO "+newColumnName;
	    System.out.println(query);
	    
	    //send and exeute sql query to DB s/w
	     
	  int count=0;
	  if(st!=null)
		  count=st.executeUpdate(query);
	  
	  //process result
	  
	 if(count==0)
		 System.out.println("COLUMN is rename");
	 else
		 System.out.println(" COLUMN is not rename");
	 
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












