package MySQL_Queries;

import java.sql.*;


public class Jdbc_Test {

	public static void main(String[] args) throws SQLException {
		
		//Select
		
		
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		
		
		try{
			
		//1-Get connection to database
			myConn =DriverManager.getConnection("jdbc:mysql://localhost:3306/demo"
			  		+ "?useUnicode=true"
			  		+ "&useJDBCCompliantTimezoneShift=true"
			  		+ "&useLegacyDatetimeCode=false"
			  		+ "&serverTimezone=UTC", "Yasemin", "Yasemin");
			System.out.println("Database connection successful!\n");
					
		//2-Create statement
			
			myStmt =myConn.createStatement();
			
		//3-Execute SQL Query
			
			myRs =myStmt.executeQuery("select * from employees");
			
		//4-Process the result set
			
			while(myRs.next()){
				System.out.println(myRs.getString("last_name")+ " "+myRs.getString("first_name"));
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if(myRs != null){
				myRs.close();
			}
			if(myStmt != null){
				myStmt.close();
			}
			if(myConn != null){
				myConn.close();
			}
		}
			
	}

}
