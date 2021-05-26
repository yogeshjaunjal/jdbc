package com.nt.jdbc;
/* write jdbc app to get employee highest salary                                                                                                                                 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest6 {

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
			//select empno,ename,job,sal where sal=(select max(sal)from emp);
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL)FROM EMP)";
			//check query is correct or not
			System.out.println(query);
			
			//Send and Execute SQL Query to DB s/w
			if(st!=null)
				rs=st.executeQuery(query);
			
			//process result set object
			
			if(rs!=null) {
				boolean flag=false;
				while(rs.next()) {
				 flag=true;
					System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"    "+rs.getString(4));
				}
				if(flag==false)
				System.out.println("no recrod  found");
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
