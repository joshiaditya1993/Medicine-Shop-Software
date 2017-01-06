import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;


public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField tf_1;
	private JPasswordField tf_2;

	static String url="jdbc:derby:C:\\Users\\SPEED\\db;create=true;user=admin;password=admin";
    private static Connection conn = null;
    private static Statement stmt = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel panel = new JPanel();
		panel.setBounds(10, 0, 519, 299);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ABC MEDICINE SHOP");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(36, 11, 396, 22);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(61, 82, 83, 22);
		panel.add(lblNewLabel_1);
		
		tf_1 = new JTextField();
		tf_1.setBounds(269, 83, 86, 20);
		panel.add(tf_1);
		tf_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(58, 115, 86, 14);
		panel.add(lblNewLabel_2);
		
		JButton btnLogin = new JButton("Login");
		
		final JLabel lbl3 = new JLabel("");
		lbl3.setBounds(61, 236, 215, 14);
		panel.add(lbl3);
		btnLogin.setBounds(149, 183, 89, 23);
		panel.add(btnLogin);
		
		tf_2 = new JPasswordField();
		tf_2.setBounds(269, 114, 86, 20);
		tf_2.setEchoChar('*');
		panel.add(tf_2);
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
			/* Database_helper db= new Database_helper();
			int i;
			db.connect();
			i=db.login(tf_1.getText(), tf_2.getText());
			if(i==1)
			{
				lbl3.setText("Login succesful");
				setVisible(false);
				Menu mn=new Menu();
			}
			else
				lbl3.setText("Incorrect username/password"); */
				try
		        {
		            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		            conn = DriverManager.getConnection(url); 
		        }
		        catch (Exception except)
		        {
		            except.printStackTrace();
		        }
				String user=tf_1.getText();
				String pass=tf_2.getText();
				String query="select username,pass from login where username='"+user+"' and pass='"+pass+"'";
				//System.out.println(tf_1.getText());
				//System.out.println(tf_2.getText());
				 try {
					stmt = conn.createStatement();
					ResultSet rs=stmt.executeQuery(query);
					//rs.next();
					if(rs.next()){
						setVisible(false);
						Menu mn=new Menu();
						
					}
					else{
						lbl3.setText("Incorrect username/password"); 
						
					}
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		
		setVisible(true);		
		}
	}
