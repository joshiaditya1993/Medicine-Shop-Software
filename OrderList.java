import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class OrderList extends JFrame {

	private JPanel contentPane;
	private JTable order_table;
	JScrollPane jp;
	public OrderList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Database_helper db= new Database_helper();
		String url="jdbc:derby:C:\\Users\\SPEED\\db;create=true;user=admin;password=admin";
	    Connection conn = null;
	    Statement stmt = null;
	    String name[] = new String[10];
	    String vendor[]= new String[10];
	    String order_meds[]= new String[10];
	    String order_vendor[] = new String[10];
	    String vendor_address[]= new String[10];
	    String query2, query3;
	    int quantity[] = new int[10];
	    int squantity[]=new int[10];
	    int i=0,j=0,k=0;
		
		try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(url); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
	 String query1="select medname, quantity, vendor from medicine";
	try {
		stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(query1);
		while(rs.next())
		{	
			name[i]=rs.getString(1);
			quantity[i]=rs.getInt(2);
			vendor[i]=rs.getString(3);
			i++;
		}
		
		rs.close();
		
		} catch (SQLException e) {
		e.printStackTrace();
	}
		for(j=0;j<i;j++)
		{
			int dd,mm,yy;
			dd=11;
			mm=10;
			yy=114;
			dd=dd-7;
			java.sql.Date dt= new java.sql.Date(yy, mm, dd);
			System.out.println(dt.toString());
			query2="select sum(quantity) from sales where medname='"+name[j]+"' and sale_date> '"+dt+"'";
			try {
				stmt = conn.createStatement();
				ResultSet rs1=stmt.executeQuery(query2);
				if(rs1!=null)
					{
					rs1.next();
					squantity[j]=rs1.getInt(1);
					System.out.println(squantity[j]);
					}
				
				
				rs1.close();
				
				} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	
	
		
	for(j=0;j<i;j++)
		{
			if(quantity[j]<squantity[j])
			{
				order_meds[k]=name[j];
				order_vendor[k]=vendor[j];
				k++;
			}
		}
		
		for(j=0;j<k;j++)
		{
			query3="select v_address from vendor where v_name='"+order_vendor[j]+"'";
			try {
				
				
				 ResultSet rs2=stmt.executeQuery(query3);
				if(rs2!=null){
					rs2.next();
					vendor_address[j]= rs2.getString(1);
					
		
				}else {
				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		
		
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
		order_table = new JTable(data,columnNames);
		order_table.setForeground(Color.DARK_GRAY);
		Object ob[][]= new Object[k][3];
		for(i=0;i<k;i++)
		{
			ob[i][0]=order_meds[i];
			ob[i][1]=order_vendor[i];
			ob[i][2]=vendor_address[i];
		}
		String col_name[] ={"Med_Name", "Vendor","V_Address"};
		/*order_table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Kathy", "Smith", "Snowboarding", new Integer(5), Boolean.FALSE},
				{"John", "Doe", "Rowing", new Integer(3), Boolean.TRUE},
				{"Sue", "Black", "Knitting", new Integer(2), Boolean.FALSE},
				{"Jane", "White", "Speed reading", new Integer(20), Boolean.TRUE},
				{"Joe", "Brown", "Pool", new Integer(10), Boolean.FALSE},
			},
			new String[] {
				"First Name", "Last Name", "Sport", "# of Years", "Vegetarian"
			}
		)); */
		order_table.setModel(new DefaultTableModel(ob,col_name));
		order_table.setBounds(75, 186, 266, -127);
		jp=new JScrollPane(order_table);
		order_table.setFillsViewportHeight(true);
		jp.setBounds(0, 259, 433, -259);
		jp.setVisible(true);
		getContentPane().add(jp);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Menu mn= new Menu();
			}
		});
		jp.setRowHeaderView(btnBack);
		setVisible(true);
	}
}
