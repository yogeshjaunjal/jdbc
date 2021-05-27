//deletetest1.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*write a jdbc app to delete student record based studentno */

public class DeleteTest1 {

	public static void main(String[] args) {
		Scanner sc=null;
		Statement st=null;
		Connection con =null;

		try {
			//read input
			sc=new Scanner(System.in);
			int sno=0;
			if(sc!=null) {
				System.out.println("Enter student no:");
				 sno=sc.nextInt();//give 101
			}
			//convert user input into SQL string
			//city="'"+city+"'";//gives 'hyd'

			//load jdbc object
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish connection

			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","YOGI1","yogi");
			if(con!=null) {
				System.out.println("connetionn is establish");
			}else {
				System.out.println("connection is not establish");
			}
			//create statement object
			if(con!=null)
				st=con.createStatement();

			//prepere SQl query
			// DELETE  FROM STUDENT WHERE SNO=101;
			String query="DELETE  FROM STUDENT WHERE SNO="+sno;

			//CHECK query is correct or not
			System.out.println(query);

			//send and execute SQL query
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			//process the result
			if(count==0) {
				System.out.println("no record found to delete");
			}else {
				System.out.println("no of record that are effected::"+count);
			}


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
