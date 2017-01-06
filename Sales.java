import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class Sales extends JFrame {

	private JPanel contentPane;
	private JTextField tf_q;
	private JTable table;
	
	static String url="jdbc:derby:C:\\Users\\SPEED\\db;create=true;user=admin;password=admin";
    private static Connection conn = null;
    private static Statement stmt = null;
    private JTextField tf_t;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Sales() {
		String name[] = new String[10];
		int i=0,j=0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Medicine");
		lblNewLabel.setBounds(33, 38, 110, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(33, 76, 46, 14);
		contentPane.add(lblQuantity);
		
		tf_q = new JTextField();
		tf_q.setBounds(89, 73, 86, 20);
		contentPane.add(tf_q);
		tf_q.setColumns(10);
		
		final JLabel lblq = new JLabel("");
		lblq.setBounds(33, 127, 174, 14);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(33, 153, 355, 75);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(126, 35, 110, 20);
		
	    try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(url); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
		
	    String query="select * from medicine";
		 try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			if(rs!=null){
				
				while(rs.next())
				{	
					name[i]=rs.getString(1);
					i++;
				}
			
			}else{
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 for(j=0;j<i;j++)
		 {
			 comboBox.addItem(name[j]);
		 }
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Print");
		btnNewButton.setBounds(299, 240, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String med= new String();
				String medname= new String();
				int price=0;
				int quantity=0;
				int total=0;
				int flag=0;
				int medquant,medquant1;
				try{
				 quantity=Integer.parseInt(tf_q.getText());
				}
				catch(Exception e)
						{
					lblq.setText("Enter integer quantity");
				}
				
				try
		        {
		            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		            conn = DriverManager.getConnection(url); 
		        }
		        catch (Exception except)
		        {
		            except.printStackTrace();
		        }
				
			   medname= comboBox.getSelectedItem().toString();
			   System.out.println(medname);
				String query2="select quantity from medicine where medname='"+medname+"'";
				 try {
					stmt = conn.createStatement();
					ResultSet rs=stmt.executeQuery(query2);
					
					if(rs!=null){
						rs.next();
						medquant=rs.getInt(1);
						System.out.println(medquant+"");
						medquant1=Integer.parseInt(tf_q.getText());
						if(medquant1> medquant)
						{
							lblq.setText("Quantity not available");
						}
					
					else{
						
						System.out.println("here"); 
						flag=1;						  
					}
					}
					rs.close();
					stmt.close();
				conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(flag==1)
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
					
				    String query3="update medicine set quantity=quantity-"+quantity;
					 try {
						stmt = conn.createStatement();
						int u=stmt.executeUpdate(query3);
						System.out.println(u+"");
						
						stmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					 String query4="insert into sales(medname,sale_date,quantity) values('"+medname+"','2014-11-11',"+quantity+")";
					
					 try
				        {
				            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
				            conn = DriverManager.getConnection(url); 
				        }
				        catch (Exception except)
				        {
				            except.printStackTrace();
				        }
					 
					 try {
							stmt = conn.createStatement();
							int v=stmt.executeUpdate(query4);
							System.out.println(v+"");
							
							stmt.close();
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					 
					 String query5="select price from medicine where medname='"+comboBox.getSelectedItem().toString()+"'";
					 
					 try
				        {
				            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
				            conn = DriverManager.getConnection(url); 
				        }
				        catch (Exception except)
				        {
				            except.printStackTrace();
				        }
					 
					 
					 try {
							stmt = conn.createStatement();
							ResultSet rs3=stmt.executeQuery(query5);
							rs3.next();
							price= rs3.getInt(1);
							
							stmt.close();
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					 
					
					
						 total=Integer.parseInt(tf_t.getText());
			
					 total=total+(price*quantity);
					 
					 tf_t.setText(total+"");
					 
					med="\n"+ comboBox.getSelectedItem().toString()+" Qty:"+tf_q.getText()+" Price:"+price;
					
					textArea.append(med); 
					 
					 
					 
					 
					 
				}
			}
		});
		btnAdd.setBounds(217, 118, 89, 23);
		contentPane.add(btnAdd);
		
		/*table = new JTable();
		table.setBounds(84, 203, 152, -59);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Code", "Name", "Unit Price", "Total", "Quantity"
			}
		));
		table.setBackground(Color.BLACK);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(table); */
		
		
		
		
		contentPane.add(textArea);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(262, 76, 46, 14);
		contentPane.add(lblTotal);
		
		tf_t = new JTextField();
		tf_t.setText("0");
		tf_t.setEditable(false);
		tf_t.setBounds(302, 73, 86, 20);
		contentPane.add(tf_t);
		tf_t.setColumns(10);
		
		lblq.setVisible(true);
		contentPane.add(lblq);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Menu menu= new Menu();
				
			}
		});
		btnNewButton_1.setBounds(74, 240, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblAbcMedicineShop = new JLabel("ABC MEDICINE SHOP");
		lblAbcMedicineShop.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbcMedicineShop.setBounds(110, 11, 202, 14);
		contentPane.add(lblAbcMedicineShop);
		setVisible(true); 
	}
}
