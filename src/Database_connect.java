import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_connect {

 static Connection con = null;
	static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
	
	  public Database_connect(){
	       
	        try {
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms", "root", "admin");
		        System.out.println("Connected");
	           } catch (SQLException ex) {
	            // handle any errors
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	        }
	       
	    }
	
	    public Connection getConnection() {
	  		return con;
	    }
}
