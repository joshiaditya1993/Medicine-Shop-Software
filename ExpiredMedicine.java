import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ExpiredMedicine extends JFrame {

	private JPanel contentPane;
	private JTable expiry_table;
	JScrollPane jp1;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ExpiredMedicine() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		String query,query2;
		String name[]= new String[10];
		String vendor[]=new String[10];
		String vendor_address[]=new String[10];
		int i=0,j=0;
		String url="jdbc:derby:C:\\Users\\SPEED\\db;create=true;user=admin;password=admin";
	    Connection conn = null;
	    Statement stmt = null;
		
	    try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(url); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
		
	    int dd,mm,yy;
		dd=11;
		mm=10;
		yy=114;
		dd=dd-7;
		java.sql.Date dt= new java.sql.Date(yy, mm, dd);
		System.out.println(dt.toString());
		query="select medname, vendor from medicine where expiry< '"+dt+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs1=stmt.executeQuery(query);
			while(rs1.next())
				{
				
				name[i]=rs1.getString(1);
				vendor[i]=rs1.getString(2);
				i++;
				}
			
			
			rs1.close();
			
			} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(j=0;j<i;j++)
		{
			query2="select v_address from vendor where v_name='"+vendor[j]+"'";
			try {
				
				
				 ResultSet rs2=stmt.executeQuery(query2);
				if(rs2!=null){
					rs2.next();
					vendor_address[j]= rs2.getString(1);
					
		
				}else {
				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		Object ob[][]= new Object[i][3];
		String col_name[] ={"Med_Name", "Vendor","V_Address"};
		for(j=0;j<i;j++)
		{
			ob[j][0]=name[j];
			ob[j][1]=vendor[j];
			ob[j][2]=vendor_address[j];
			
		}
		
		
		expiry_table = new JTable();
		expiry_table.setModel(new DefaultTableModel(ob,col_name));
		expiry_table.setBounds(75, 186, 266, -127);
		jp1=new JScrollPane(expiry_table);
		expiry_table.setFillsViewportHeight(true);
		jp1.setBounds(0, 259, 433, -259);
		jp1.setVisible(true);
		getContentPane().add(jp1);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Menu mn= new Menu();
			}
		});
		jp1.setRowHeaderView(btnBack);
		setVisible(true);
	}

}
