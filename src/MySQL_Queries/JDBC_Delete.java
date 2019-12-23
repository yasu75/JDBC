package MySQL_Queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Delete {

	public static void main(String[] args) throws SQLException {
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo"
			  		+ "?useUnicode=true"
			  		+ "&useJDBCCompliantTimezoneShift=true"
			  		+ "&useLegacyDatetimeCode=false"
			  		+ "&serverTimezone=UTC", "Yasemin", "Yasemin");
						
						System.out.println("Database connection successful!\n");
						
			//2. Create a statement			
					
			myStmt = myConn.createStatement();
			
			//3. Before the delete to display info
			
			System.out.println("Before the delete...");
			//helper method
			displayEmployee(myConn, "Robert", "Turk");
			
			//Delete
			System.out.println("\nDeleting the employee-- Robert\n");
			
			int rowAffected=myStmt.executeUpdate(
					"delete from employees "+
			"where last_name='Turk' and first_name='Robert'"
			);
			//Verify Updating
			System.out.println("AFTER THE DELETE...");
			//helper method
			displayEmployee(myConn, "Turk", "Robert");
		
			
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		finally {
			close(myConn, myStmt, myRs);
			}
		
	}
	private static void displayEmployee(Connection myConn, String firstName, String lastName) throws SQLException{
	   PreparedStatement myStmt = null;
	   ResultSet myRs = null;
	   
	   try {
		   //Prepare statement
		   myStmt = myConn
				   .prepareStatement("select last_name, first_name, email from employees where last_name=? and first_name=?");
		   myStmt.setString(1, lastName);
		   myStmt.setString(2, firstName);
		   
		   //execute SQL query
		   myRs = myStmt.executeQuery();
		   
		   //Process result set
		   while(myRs.next()){
			   String theLastName = myRs.getString("last_name");
			   String theFirstName = myRs.getString("first_name");
			   String email = myRs.getString("email");
			   
			   System.out.printf("%s, %s, %s\n", theFirstName, theLastName, email);
		   }
		
	   } catch (Exception exc) {
		exc.printStackTrace();
	      }finally {
			close(myStmt, myRs);
		}
	  
	}
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException{
		
		if(myRs !=null){
			myRs.close();
		}
		if(myStmt !=null){
			myStmt.close();
		}
		if(myConn !=null){
			myConn.close();
		}
	}
	
	private static void close(Statement myStmt, ResultSet myRs) throws SQLException{
		
		close(null, myStmt, myRs);
		
	}

}
