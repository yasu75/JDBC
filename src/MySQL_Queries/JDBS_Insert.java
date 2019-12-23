package MySQL_Queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBS_Insert {

	public static void main(String[] args) throws SQLException {
		//Insert
		
		
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
		
		//3-Insert a new employee in the database
			
			System.out.println("Inserting a new employee to the database\n");
			
			int rowsAffected = myStmt.executeUpdate(
					"insert into employees"+
			        "(last_name, first_name, email, department, salary)"+
					"values"+
			        "('Turk', 'Robert','robertt@gmail.com', 'HR', '3000')"					
					);
		//4-Verify this by getting  a list of Employees
			
			myRs = myStmt.executeQuery("select * from employees order by last_name");
			
		//5-process the result set 
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
