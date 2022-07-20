// Safwan made this code
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;


public class Secretory {
	
	private Connection con;
	Scanner sc = new Scanner(System.in);
	
	public Secretory() {
		
		Database_connect db = new Database_connect();
		con = db.getConnection();
		Secretary_Menu();
		
	}
	
	
	public void addNewGuard(){
		
		String query = "insert into staff(designation,doj,staffname) values (?,?,?)";
		//String staffid = "";
		String designation = "";
		String doj = "";
		String s_name = "";
		// Not Sure 
		//System.out.println("Enter staffid");
		//staffid = sc.next();
		// Not Sure 
				
		System.out.println("Enter Designation of staff member");
		designation = sc.next();
				
		System.out.println("Enter Date of Joining in YYYY-MM-DD Format");
		doj = sc.next();
		
		sc.nextLine();
		
		System.out.println("Enter name of staff");
		s_name = sc.nextLine();
		
		try {
			
		PreparedStatement ps = con.prepareStatement(query);
		
		//ps.setString(1,staffid);
		ps.setString(1,designation);
		ps.setString(2,doj);
		ps.setString(3,s_name);
		
		
		ps.executeUpdate();
		
		}catch(SQLException e) {
			
			Logger.getLogger(Database_connect.class.getName()).log(Level.SEVERE, null, e);
		
		}/*finally {
			
			try {
				
			con.close();
			
			}catch(SQLException e) {
				
				Logger.getLogger(Database_connect.class.getName()).log(Level.SEVERE, null, e);
			
			}
			
		}*/
	
		System.out.println("New staff member added Name:"+s_name+" Designation:"+designation+" Doj:"+doj);
		
	} 
	
	public void Accessing_Attendance_Records() {
		
		String query = "select * from attendance";
		
		try {
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			

			System.out.println("********************************");	
			while(rs.next()) {
				System.out.println("a_id: "+rs.getString(1));
				System.out.println("attendance: "+rs.getString(2));
				System.out.println("staff_id: "+rs.getString(3));
				System.out.println("a_date: "+rs.getString(4));
				System.out.println("********************************");	
			}
			
			
		}catch(SQLException e) {
			
			Logger.getLogger(Database_connect.class.getName()).log(Level.SEVERE, null, e);
		
		}
		
	}
	
	
	public void Check_Complaints() {
		

		String query = "select * from complaint";
		
		try {
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			System.out.println("********************************");	
			while(rs.next()) {
				System.out.println("c_id: "+rs.getString(1));
				System.out.println("statement: "+rs.getString(2));
				System.out.println("c_date: "+rs.getString(3));
				System.out.println("status: "+rs.getString(4));
				System.out.println("u_id: "+rs.getString(5));
				System.out.println("********************************");	
			}	
	
			
		}catch(SQLException e) {
			
			Logger.getLogger(Database_connect.class.getName()).log(Level.SEVERE, null, e);
		
		}
		
		
	}
	
	
	public void publish() {
		
		String notice_date = "";
		String notice = "";
		int publisher = 0;
			
		System.out.println("Enter publisher id");
		publisher = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter notice to be published");
		notice = sc.nextLine();
		
		System.out.println("Enter notice date in YYYY-MM-DD Format");
		notice_date = sc.nextLine();
		
		String query = "insert into notice_info(not_date,notice,publisher) values (?,?,?)";
		
		try {
			
			PreparedStatement ps = con.prepareStatement(query);
			
			//ps.setString(1,"1");
			ps.setString(1,notice_date);
			ps.setString(2,notice);
			ps.setInt(3,publisher);
			
			ps.executeUpdate();
			
			}catch(SQLException e) {
				
				Logger.getLogger(Database_connect.class.getName()).log(Level.SEVERE, null, e);
			
			}
		
		 System.out.println("The notice published is as follows:"
				+notice+" Date:"+notice_date);
	}
	
	
	public void Secretary_Menu() {
		int choice;
		do {
	
		System.out.println("Welcome Secretory");
		System.out.println("Press 1 for Adding a new guard");
		System.out.println("Press 2 for Accessing Attendance record");
		System.out.println("Press 3 for Checking complaints");
		System.out.println("Press 4 for Publishing notice");
		System.out.println("Press 5 for Exit");
		choice = sc.nextInt();
		
			switch(choice) {
			
			case 1: 
					addNewGuard();
					break;
			
			case 2: 
					Accessing_Attendance_Records();
					break;
					
			case 3:
					Check_Complaints();
					break;
					
			case 4:
					publish();
					break;
					
			case 5:
					try {
					
					con.close();
					
					}catch(SQLException e) {
						
						Logger.getLogger(Database_connect.class.getName()).log(Level.SEVERE, null, e);
					
					}
					System.out.println("Thank you for using the application");
					System.exit(0);
			
			default: System.out.println("Wrong choice Please try again");
			
			}
			
			System.out.println("For Main Menu Press 0 or any number for exit");
			
			choice = sc.nextInt();
			
			if(choice==0)
				choice = 0;
			else {
				try {
					
					con.close();
					
				}catch(SQLException e) {
						
					Logger.getLogger(Database_connect.class.getName()).log(Level.SEVERE, null, e);
					
				}
				System.out.println("Thank you for using the application");
				System.exit(0);
			}
		}while(choice<=5);
	}
	
}
