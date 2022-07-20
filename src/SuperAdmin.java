import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuperAdmin {
	private Connection con;
	Scanner sc = new Scanner(System.in);
	
	
	public SuperAdmin() {
		Database_connect db = new Database_connect();
		con = db.getConnection();
		superAdmin_menu();
	}


	private void superAdmin_menu() {
	
			System.out.println("Select Option");
			System.out.println("Press 1 to Assign role to Owner");
			System.out.println("Press 2 to LogOut");
				int choice = sc.nextInt();
				
				switch(choice) {
				case 1: 
					assign_role();
					break;
				case 2:
					exit();
					break;
				default: 
					superAdmin_menu();
					}
				}
			
			private void assign_role() {
				System.out.println("Enter User Id to assign Secretory Role to Owner: ");
				int user_id = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Name of User: ");

				String owner_name= sc.nextLine();

				String query = "update users set role_id= 3 where uid="+user_id;
			
				System.out.println("Do you really want to assign "+owner_name+" as Secretory?");
				System.out.println("Press 1 to assign");
				System.out.println("Press 2 to go back to menu");
				int choice= sc.nextInt();
				if(choice == 1) {
					try {
						  Statement stmt = con.createStatement();
						  stmt.executeUpdate(query);
						  System.out.println("Role Assigned Successfully");
					}
					
					catch(SQLException e) {
						Logger.getLogger(Database_connect.class.getName()).log(Level.SEVERE, null, e);
					}
				}else {
					superAdmin_menu();
				}	
				
				superAdmin_menu();
				
				
			}
			public void exit() {
				System.out.println("Thank You for using the application");
				System.exit(0);
			}
	}

	
