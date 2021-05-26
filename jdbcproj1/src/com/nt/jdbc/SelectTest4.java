package com.nt.jdbc;
/* JDBC app that gives deperment  dept table base on given deptno */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest4 {

	public static void main(String[] args) {
            Scanner sc=null;
             Connection con=null;
             Statement st=null;
             ResultSet rs=null;
             
             
            try {
				sc=new Scanner(System.in);
				int dno=0;
				if(sc!=null) {
				System.out.println("Enter dept no::");
			    dno=sc.nextInt();//given 1
				}
				//load jdbc driver class
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//establish connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","YOGI1","yogi");
				
				//create Statement object
				if(con!=null)
			     st=con.createStatement();
			     
			     //prepear SQl Query
			     //SELECT DEPTNO,DNAME,LOC FROM DEPT WHERE DEPTNO=40;
			     String query="SELECT DEPTNO,DNAME,LOC FROM DEPT WHERE DEPTNO= "+dno;
			     
			    //check query correct or not
			     System.out.println(query);
			     
			     //Send and Execute SQl Query in DB s/w
			     if(st!=null)
			         rs=st.executeQuery(query);
			     
			     //process resultset object(0 or more record)
			     
			     if(rs!=null) {
			    	 if(rs.next())
			    		 System.out.println(rs.getInt(1)+"    "+rs.getString(2)+"     "+rs.getString(3));
			    	 else
			    		 System.out.println("NO RECORD FOUND");
			    	 
			     }//if
		        }//try
            
            catch (SQLException se) {
                 if(se.getErrorCode()>=900 && se.getErrorCode()<999) {
                	 System.out.println("Incorrect query please enter correct query");
                	 se.printStackTrace();
                 }//if
              }//catch end
            catch(Exception e) {
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
            	try {
					if(sc!=null)
					 rs.close();
				} catch (Exception e) {
                   e.printStackTrace();
				}//catch end
            	
             }//finally end
	}//main end

}//class emd
