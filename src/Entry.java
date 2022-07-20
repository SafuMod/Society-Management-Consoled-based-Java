import java.util.*;
import java.sql.*;  
class User {
	private int user_id;
	private int role_id;
	private String mobile_no;
	private String name;
	private String password;
	
	User(int r,String m, String n, String p){
		user_id=4;
		role_id=r;
		mobile_no=m;
		name=n;
		password=p;
	}
	
	int get_userId() {
		return user_id;
	}
	int get_roleId() {
		return role_id;
	}
	String get_mobileNo() {
		return mobile_no;
	}
	String get_name() {
		return name;
	}
	String get_password() {
		return password;
	}
	
}
public class Entry{
	static int login(String mobile,String password) {
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sms","root","admin");   
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from users");  
			while(rs.next()) {
				//System.out.println(rs.getInt(1)+"  "+rs.getInt(2)+"  "+rs.getString(3)+" "+rs.getString(4)+"  "+rs.getString(5));  
				if(mobile.equals(rs.getString(3)) && password.equals(rs.getString(5))) {
					return rs.getInt(1);
				}
			}
			con.close();  
			return 0;
		}catch(Exception e){ 
			System.out.println(e);
			return 0;
		}  
	}
	static boolean register(User user) {
		String insertValues = "";
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sms","root","admin");   
			Statement stmt=con.createStatement();  
			insertValues =user.get_roleId()+", '"+ user.get_mobileNo()+"', '"+user.get_name()+"','" +user.get_password()+"'";
			String sql = "insert into users(role_id,mobile,name,password) values(" +insertValues+")";
			stmt.executeUpdate(sql); 
			con.close();  
			return true;
		}catch(Exception e){ 
			System.out.println(e);
			return false;
		}
	}
	static String findRole(int id) {
		String role="";
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sms","root","admin");   
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select role from roles where rid=(select role_id from users where uid="+id+")");
			while(rs.next()) {
				role= rs.getString(1);
			}
			con.close();  
			return role;	
		}catch(Exception e){ 
			System.out.println(e);
			return null;
		}
		
	}
	public static void main(String args[]) {
		String name,mobile_no,password;
		int ch,flag, logged_in_check=0,logged_in_id=0;
		Scanner s=new Scanner(System.in);
		
		System.out.println("************************************");
		System.out.println("Welcome to Society Management System");
		System.out.println("************************************");
		
		System.out.println("Select option:-");
		//Show choices:
		System.out.println("1.Registration");
		System.out.println("2.Login");
		do {
			System.out.println("Enter your choice:");
			ch=s.nextInt();
			switch(ch) {
				case 1:	System.out.println("Enter name:");
						s.nextLine();
						name=s.nextLine();
						System.out.println("Enter mobile no:");
						mobile_no=s.nextLine();
						System.out.println("Enter password:");
						password=s.nextLine();
						User user=new User(2,mobile_no,name,password);
						boolean ans=register(user);
						if(ans) {
							System.out.println("Successfully logged in to the system");
						}else {
							System.out.println("Error has occured");
						}
						break;
				case 2:System.out.println("Enter mobile no:");
						s.nextLine();
						mobile_no=s.nextLine();
						System.out.println("Enter password:");
						password=s.nextLine();
						logged_in_id=login(mobile_no,password);
						if(logged_in_id!=0) {
							System.out.println("Successfully logged in to system");
							logged_in_check=1;
						}else {
							System.out.println("Invalid login credentials entered");
						}
						break;
				default:System.out.println("Invalid choice entered");
				
			}
			if(logged_in_check==1) {
				break;
			}
			System.out.println("Do you still want to continue then press 0 (To terminate press any other character except 0):");
			flag=s.nextInt();
		}while(flag==0);
		
		//Find role of logged in user:
		String role=findRole(logged_in_id);
		System.out.println(role);

		if(role.equalsIgnoreCase("owner")) 
		{
			int choice,check;
			do {
				System.out.println("Choose what you would like to do:");
				System.out.println("1. File Complaint");
				System.out.println("2. Check Notice");
				System.out.println("3. Exit");
				choice=s.nextInt();
				Owner o=new Owner();
				switch(choice)
				{
					case 1: 
					o.file_complaint(logged_in_id);
					break;
					
					case 2:
						o.show_notice();
						break;
						
					case 3:
						o.end();
						break;
					
				}
				System.out.println("Do you want to continue then press 0");
				check=s.nextInt();
					
			}while(check==0);
			
		}else if(role.equalsIgnoreCase("secretory")) {
			
			Secretory sct = new Secretory();
			
		}else if(role.equalsIgnoreCase("super_admin")) {
			
			SuperAdmin sa = new SuperAdmin();
			
		}else if(role.equalsIgnoreCase("staff")) {
			
			Staff stf= new Staff();
			
		}
		
		s.close();
		
	}
	
}
