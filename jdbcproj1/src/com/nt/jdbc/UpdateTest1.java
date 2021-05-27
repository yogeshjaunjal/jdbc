package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* non select query update student detelis like sname and sadd and avg where sno*/


public class UpdateTest1 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			String newName=null;
			String newAdd=null;
			float newAvg=0.0f;
			int sno=0;
			if(sc!=null){
				System.out.println("enter student newname::");
				newName=sc.next(); //anil
				System.out.println("enter student newAddress::");
				newAdd=sc.next(); //anil
				System.out.println("enter student newAvg::");
				newAvg=sc.nextFloat(); //anil
				System.out.println("enter student no::");
				sno=sc.nextInt(); //anil

			}//if
			//convert into SQl String
			newName="'"+newName+"'";
			newAdd="'"+newAdd+"'";


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
			// update student set sname='anil', sadd='mah',avg=96.66 where sno=103;

			String query="update student set sname="+newName+",sadd="+newAdd+",avg="+newAvg+"WHERE SNO="+sno;
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












