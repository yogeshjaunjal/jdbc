package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* non select query update emp salary increse by percentage betewen start range and end range */


public class UpdateTest2 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			float start=0.0f;
			float end=0.0f;
			int newPerSal=0;
			if(sc!=null){
				System.out.println("enter percentage of salary increse::");
				newPerSal=sc.nextInt(); //anil
			
				System.out.println("enter start range salary ::");
				start=sc.nextInt(); //
				System.out.println("enter End range salary");
				end=sc.nextInt(); //

			}//if
			//convert into SQl String
			                     
               

			//establish connection
			if(sc!=null)
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "YOGI1", "yogi");
			if(con!=null)
				System.out.println("connection is establish");
			else
				System.out.println("connection is not establish");

			//create statement object

			st=con.createStatement();

			//prepare sql query
			//  update emp set sal=sal+(sal*10/100) where sal between 1600 and 2975;

			String query=" UPDATE EMP SET SAL=SAL+(SAL*"+newPerSal+"/100) WHERE SAL BETWEEN "+start+" AND "+end;
			//check query is correct or not;
			System.out.println(query);

			//send and execute SQL query in Db s/w
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			//process the result
			if(count==0) 
				System.out.println("no record found to update");
			else 
				System.out.println("no of record that are effected::"+count);

		}//try end
		catch (SQLException se) {

			if(se.getErrorCode()>=900 && se.getErrorCode()<=999 ) {
				System.out.println("invalid sql query");
			
			}//if
			else 
					System.out.println("invalid identifier");
			se.printStackTrace();
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
				System.out.println("please  enter correct input"); 
				e.printStackTrace();
			}



		}//finally

	}//main
}//class












