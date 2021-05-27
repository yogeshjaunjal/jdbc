package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* non select query update using gives percentage  of marks of to exting avg on based on 3 city  for student*/


public class UpdateTest3 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			 String city1=null;
			 String city2=null;
			 String city3=null;
		      int newPer=0;
			if(sc!=null){
				System.out.println("enter percentaage of mark incr::");
				newPer=sc.nextInt(); //
				System.out.println("enter  first city::");
				city1=sc.next(); //  hyd
				System.out.println("enter  second city::");
				city2=sc.next(); //mah
				System.out.println("enter  third city");
				city3=sc.next();//navi mumbai

			}//if
			//convert into SQl String
			city1="'"+city1+"'";//' hyd'
			city2="'"+city2+"'";//' mah '
			city3="'"+city3+"'";//' navi mumbai'

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
			//  update student set avg=avg+(avg*10/100) where sadd in('hyd','navi mumbai','mah');
			String query="update student set avg=avg+(avg*"+newPer+"/100) where sadd in("+city1+","+city2+","+city3+")";
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












