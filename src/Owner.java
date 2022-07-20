import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;
	
public class Owner 
{
		private Connection con;
		Scanner sc=new Scanner(System.in);
		String c_date="";
		String comp_st="";
		int record_added;
		
		public void file_complaint(int uid)
		{
				System.out.println("Please enter your complaint statement");
				comp_st=sc.nextLine();
				System.out.println("Please enter date in 'YYYY-MM--DD' format");
				c_date=sc.nextLine();
				
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sms","root","admin");
					PreparedStatement stmt=con.prepareStatement("insert into complaint(statement,c_date,status,uid) values(?,?,?,?)");
		
						stmt.setString(1,comp_st);
						stmt.setString(2,c_date);
						stmt.setString(3,"Filed");
						stmt.setInt(4,uid);

					record_added=stmt.executeUpdate();
					
					if(record_added>0)
					{
						System.out.println("Your complaint has been successfully filed \nThank you");
					}

				}catch(Exception e)
				{
					e.printStackTrace();
				}
		}
				
				
		public void show_notice() 
		{
			System.out.println("******Notice*******");
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sms","root","admin");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from notice_info");
				while(rs.next())
				{
					System.out.println("Notice Id:"+rs.getInt(1)+"\t Notice Date:"+rs.getString(2)+"\nNotice:"+rs.getString(3));
							//"\nPublisher:"+rs.getInt(4));
				}

			}catch(Exception e)
			{
				e.printStackTrace();
			}

		}				
				public void end()
				{
					System.out.println("You are logged out");
				}				
}




