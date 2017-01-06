import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class Database_helper {

	static String url="jdbc:derby:C:\\Users\\SPEED\\MSSdb;create=true;user=admin;password=admin";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	void connect()
	{
		 try
	        {
	            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
	            conn = DriverManager.getConnection(url); 
	        }
	        catch (Exception except)
	        {
	            except.printStackTrace();
	        }
	}
	int login (String user,String pass)
	{
		String query="select username,pass from admin.login where username='"+user+"' and pass='"+pass+"'";
		 try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			if(rs!=null){
				return 1;
			}else{
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	
	}
	
	public void threshold(String name)
	{
		Date dt=new Date();
		//int i=date.getDate();
		//System.out.println(i);
		int dd,mm,yy;
		dd=dt.getDate();
		mm=dt.getMonth();
		yy=dt.getYear();
		MyDate mdt= new MyDate(dd,mm,yy);
		mdt.makeDate();
		System.out.println(mdt.dd);
		java.sql.Date dt1= new java.sql.Date(mdt.dd,mm,yy);
		
		//String query1="select sale_date from sales where sale_date< '"+mdt.dd+"-"+mm+"-"+yy+"'";
		//dd=dt1.getDate();
		
		//String query1="select sale_date from sales where sale_date< '"+yy+"-"+mm+"-"+dd+"'";
		
		String query2="select sale_date from sales where medname='"+name+"' and sale_date> '"+dt1+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs1=stmt.executeQuery(query2);
			if(rs1!=null){
				rs1.next();
				Date dtr=rs1.getDate(1);
				System.out.println(dtr);
			}else return;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	} 

}
