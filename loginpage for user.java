package hotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JRadioButton;

public class loginpage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	JLabel lbltxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginpage frame = new loginpage();
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
	public loginpage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 766, 508);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("show");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNewRadioButton.isSelected()) {
					pass.setEchoChar((char)0);
				}
				else {
					pass.setEchoChar('*');
				}
			}	
		});
		rdbtnNewRadioButton.setFont(new Font("Bell MT", Font.BOLD, 24));
		rdbtnNewRadioButton.setBounds(564, 222, 103, 21);
		contentPane.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel = new JLabel("HOTEL MANAGEMENT");
		lblNewLabel.setBounds(227, 38, 319, 38);
		lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD, 31));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USERNAME");
		lblNewLabel_1.setBounds(31, 146, 183, 29);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setBounds(31, 217, 183, 29);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		contentPane.add(lblNewLabel_1_1);
		
		user = new JTextField();
		user.setBounds(277, 155, 249, 20);
		contentPane.add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setToolTipText("strong password");
		pass.setBounds(277, 226, 249, 19);
		contentPane.add(pass);
		
		final JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setBounds(329, 306, 140, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String us = user.getText();
					//@SuppressWarnings("deprecation")
					String ps = pass.getText();
					System.out.println(us);
					Class.forName("com.mysql.cj.jdbc.Driver");
					System.out.println(ps);
					Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","Thisanthan@123");
					System.out.println("1");
					Statement st = con.createStatement();
					System.out.println("1");
					String sql = "Select * from access";
					System.out.println("1");
					ResultSet rs = st.executeQuery(sql);
					System.out.println("1");
					while(rs.next()){
					String username = rs.getString("username");
					System.out.println("1-");
					String password = rs.getString("password");
					System.out.println("1-");

					if(us.equals(username) && ps.equals(password)){
				     new booking().setVisible(true);
					System.out.println("true");
					SwingUtilities.windowForComponent(btnNewButton).dispose();
					lbltxt.setText("");
					break;
					}
					else{
					user.setText("");
					pass.setText("");
					System.out.println("false");
					lbltxt.setText("invalid username,password");
					continue;
					}
				}}
				catch(Exception o) {
					JOptionPane.showMessageDialog(btnNewButton, this, "Error while establishing connection failed",0);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(btnNewButton);
		
	    lbltxt = new JLabel("");
	    lbltxt.setBounds(142, 368, 459, 62);
		lbltxt.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contentPane.add(lbltxt);
		
		final JButton btnsignup = new JButton("sign up");
		btnsignup.setBounds(69, 306, 129, 29);
		btnsignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new create().setVisible(true);
				SwingUtilities.windowForComponent(btnsignup).dispose();
				
			}
		});
		btnsignup.setFont(new Font("Tahoma", Font.BOLD, 24));
		contentPane.add(btnsignup);
		
		JButton btnNewButton_1 = new JButton("forget password");
		btnNewButton_1.setBounds(527, 314, 191, 21);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new print().setVisible(true);
				System.out.println("true");
				
				SwingUtilities.windowForComponent(btnNewButton).dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Admin login");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Admin().setVisible(true);
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1_1.setBounds(527, 368, 191, 21);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\niksh\\Downloads\\New Doc 04-07-2024 14.46_9 - .jpg"));
		lblNewLabel_2.setBounds(0, 0, 752, 461);
		contentPane.add(lblNewLabel_2);
	}
}
