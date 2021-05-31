package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AlterModifyTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;


		try {

			//load jdbc driver
			//Class.forName(oracle.jdbc.driver,OracleDriver);

			//Establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "YOGI1", "yogi");

			//Create statement object
			if(con!=null)
				st=con.createStatement();

			//preper Sql query 

			String query="ALTER TABLE STUDENT MODIFY SNO NUMBER(20)";

			//CHECK SQL QUERY correct or wrong
			System.out.println(query);

			//send and execute sql query in DB s/w
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);


			//process result
			if(count==0) {
				System.out.println("table is modify ");
			}else {
				System.out.println("table is not modify");
			}


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
			


		}//finally

	}//main
}//class












