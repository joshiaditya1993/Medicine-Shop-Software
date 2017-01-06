import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Menu extends JFrame {

	private JPanel contentPane;

	public Menu() {
			getContentPane().setLayout(null);
			
			JLabel lblWelcomeToMedical = new JLabel("ABC MEDICINE SHOP");
			lblWelcomeToMedical.setHorizontalAlignment(SwingConstants.CENTER);
			lblWelcomeToMedical.setBounds(101, 11, 209, 14);
			getContentPane().add(lblWelcomeToMedical);
			
			JButton btnGenerateOrderList = new JButton("Generate Order List");
			btnGenerateOrderList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					OrderList ol= new OrderList();
				}
			});
			btnGenerateOrderList.setBounds(124, 50, 188, 23);
			getContentPane().add(btnGenerateOrderList);
			
			JButton btnGenerateExpiredMedicine = new JButton("Generate Expired Medicine List");
			btnGenerateExpiredMedicine.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					ExpiredMedicine em=new ExpiredMedicine();
					
				}
			});
			btnGenerateExpiredMedicine.setBounds(124, 89, 188, 23);
			getContentPane().add(btnGenerateExpiredMedicine);
			
			JButton btnCreateSalesReceipt = new JButton("Create Sales Receipt");
			btnCreateSalesReceipt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					Sales s=new Sales();
				}
			});
			btnCreateSalesReceipt.setBounds(124, 135, 188, 23);
			getContentPane().add(btnCreateSalesReceipt);
			
			JButton btnLogout = new JButton("Logout");
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					Main m= new Main();
				}
			});
			btnLogout.setBounds(124, 261, 188, 23);
			getContentPane().add(btnLogout);
			setSize(400, 400);
			setVisible(true);

	}
}
