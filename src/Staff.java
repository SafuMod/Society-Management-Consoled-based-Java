import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Staff {
	
	private Connection con;
	Scanner sc = new Scanner(System.in);
	
	public Staff() {
		Database_connect db = new Database_connect();
		con = db.getConnection();
		staff_menu();
		}
	public void mark_attandance() {
		int staff_id=1;
		String date="";
		String query = "insert into attendance(attendance,staff_id, a_date) values (?,?,?)";
		
		System.out.println("Enter staff id: ");
		staff_id=sc.nextInt();
		
		System.out.println("Enter date: ");
		System.out.println("Please enter date only in this format- yyyy-mm-dd");
		date= sc.next();
		
		sc.nextLine();
		
		System.out.println("Do you want to mark present for "+date+" ?");
		System.out.println("Press 1 for Present");
		System.out.println("Press 2 for No Change");
		int choice= sc.nextInt();
		if (choice== 1) {
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setBoolean(1,true);
				ps.setInt(2,staff_id);
				ps.setString(3,date);
				
				ps.executeUpdate();
			}
			catch(SQLException e) {
				Logger.getLogger(Database_connect.class.getName()).log(Level.SEVERE, null, e);
				
			}
		}else {
			
			staff_menu();
		}
		
		System.out.println("Attandance Marked of "+staff_id+" for date:- "+date+".");
		staff_menu();
	}
	public void check_attandance(){
		String attendance ="";
		System.out.println("Enter the Staff ID: ");
		int staff_id=sc.nextInt();
		try {
			PreparedStatement pstmt=con.prepareStatement("select * from attendance where staff_id= "+staff_id);
			ResultSet rs=pstmt.executeQuery();  
			Statement stmt = con.createStatement();
			System.out.println("***********************************************");
			while(rs.next())
				{  
					if (rs.getBoolean(2) == true) {attendance = "Present";}

					System.out.println("Attendance ID: "+rs.getInt(1));
					System.out.println("Attendance: "+attendance);
					System.out.println("Staff ID: "+rs.getInt(3));
					System.out.println("Date: "+rs.getString(4));
					System.out.println("***********************************************");
				}
			System.out.println("Press 1 for Menu");
			System.out.println("Press 2 for logout");
			int choice = sc.nextInt();
			if(choice ==1) {
				staff_menu();
			}else {
				System.out.println("Thank you for using");
				System.exit(0);
			}
			}
			catch (SQLException e) {
			Logger.getLogger(Database_connect.class.getName()).log(Level.SEVERE, null, e);
			
		}
		 
	}
	public void check_notice(){
		String publisherName = "";
		try {

			PreparedStatement pstmt=con.prepareStatement("select * from notice_info");
			ResultSet rs=pstmt.executeQuery();  
			Statement stmt = con.createStatement();
			System.out.println("***********************************************");
				while(rs.next())
				{  
					
					ResultSet rst = stmt.executeQuery("select name from users where uid= "+rs.getInt(4));
					while(rst.next()) {
						 publisherName = rst.getString(1); 
						}

					System.out.println("Notice ID: "+rs.getInt(1));
					System.out.println("Notice Date: "+rs.getString(2));
					System.out.println("Notice: "+rs.getString(3));
					System.out.println("Publisher: "+publisherName);
					System.out.println("***********************************************");
				}
			System.out.println("Press 1 for Menu");
			System.out.println("Press 2 for logout");
			int choice = sc.nextInt();
			if(choice ==1) {
				staff_menu();
			}else {
				System.out.println("Thank you for using");
				System.exit(0);
			}
			}
			catch (SQLException e) {
			Logger.getLogger(Database_connect.class.getName()).log(Level.SEVERE, null, e);
			
		}
//		finally {
//			
//			try {
//				
//			con.close();
//			
//			}catch(SQLException e) {
//				
//				Logger.getLogger(Database_connect.class.getName()).log(Level.SEVERE, null, e);
//			
//			}
//		}  
}
public void exit() {
	System.out.println("Thank You for using");
	System.exit(0);
}

	public void staff_menu(){
		System.out.println("Select Option");
		System.out.println("Press 1 to Mark Attendance");
		System.out.println("Press 2 to Check Attendance");
		System.out.println("Press 3 to Check Notices");
		System.out.println("Press 4 to LogOut");
		
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1: 
			mark_attandance();
			break;
		case 2:
			check_attandance();
			break;
		case 3:
			check_notice();
			break;
		case 4:
			exit();
			break;
		default: 
			staff_menu();
			
		}
	}
}
