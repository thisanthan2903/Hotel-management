package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class report extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField report;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					report frame = new report();
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
	public report() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 567);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		report = new JTextField();
		report.setBounds(198, 219, 543, 38);
		contentPane.add(report);
		report.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(198, 122, 543, 43);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Booking is not available", "Billing failed", "any problem in Food service", "any problem in room service ", "", ""}));
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel(" Report");
		lblNewLabel.setBounds(353, 10, 252, 51);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblNewLabel);
		
		final JButton submit = new JButton("submit");
		submit.setBounds(245, 382, 177, 51);
		submit.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				String rep = (String) comboBox.getSelectedItem();
				String qw = report.getText();
				try {
				    Class.forName("com.mysql.cj.jdbc.Driver");
				    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","Thisanthan@123");
				    System.out.println("1");

				  //  String rep = "your_report_value"; // Assuming you have the report value stored in a variable

				    // Prepared statement to update the first null row
				    PreparedStatement st6 = con.prepareStatement("UPDATE room1 SET report1 = ? WHERE report1 IS NULL ORDER BY id LIMIT 1");
				    st6.setString(1, rep);

				    int rowaffected6 = st6.executeUpdate();
				    if(rowaffected6 > 0) {
				        JOptionPane.showMessageDialog(null,"report submited");
				    } else {
				        JOptionPane.showMessageDialog(null,"No rooms available");
				    }
				    if(qw != null) {
				    	 PreparedStatement st7 = con.prepareStatement("UPDATE room1 SET report1 = ? WHERE report1 IS NULL ORDER BY id LIMIT 1");
						    st7.setString(1, qw);
						    int rowaffected7 = st6.executeUpdate();
				    }   
				} catch(SQLException e1) {
				    e1.printStackTrace();
				} catch(ClassNotFoundException e1) {
				    e1.printStackTrace();
				} 

				
			}
			
		});
		submit.setFont(new Font("Tahoma", Font.BOLD, 24));
		contentPane.add(submit);
		
		JButton btnExit = new JButton("exit");
		btnExit.setBounds(553, 382, 177, 51);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				booking dh =new booking();
				dh.setVisible(true);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 24));
		contentPane.add(btnExit);
		
	
		
		JLabel lblEnterReport = new JLabel("Enter Report");
		lblEnterReport.setForeground(new Color(255, 0, 0));
		lblEnterReport.setBounds(10, 122, 178, 51);
		lblEnterReport.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(lblEnterReport);
		
		JLabel lblOther = new JLabel("other*");
		lblOther.setForeground(new Color(255, 0, 0));
		lblOther.setBounds(10, 206, 178, 51);
		lblOther.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(lblOther);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\niksh\\Downloads\\report.jpg"));
		lblNewLabel_1.setBounds(0, 0, 847, 472);
		contentPane.add(lblNewLabel_1);
	}
}
