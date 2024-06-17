package hotel;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import com.toedter.calendar.JDateChooser;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import hotel.roomvacant;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField address;
	private JTextField id;
	private JTextField phoneno;
	private JTextField i;
	private JDateChooser checkin;
	private JDateChooser checkout;
	private JTable table;
	private String qw;
	private int number;
	
	/**
	 * Launch the application.
	 */
    
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1052, 718);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 124, 150, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setForeground(new Color(255, 255, 255));
		lblAddress.setBounds(10, 171, 150, 37);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblAddress);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(new Color(255, 255, 255));
		lblId.setBounds(10, 218, 150, 37);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblId);
		
		JLabel lblPhoneNo = new JLabel("PHONE NO");
		lblPhoneNo.setForeground(new Color(255, 255, 255));
		
		lblPhoneNo.setBounds(10, 265, 150, 37);
		lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblPhoneNo);
		
		checkin = new JDateChooser();
		checkin.setBounds(140, 323, 211, 30);
		
		getContentPane().add(checkin);
		
		checkout = new JDateChooser();
		checkout.setBounds(140, 370, 211, 30);
		
		getContentPane().add(checkout);
		
		name = new JTextField();
		name.setBounds(140, 132, 211, 28);
		contentPane.add(name);
		name.setColumns(10);
		
		address = new JTextField();
		address.setBounds(140, 180, 211, 28);
		address.setColumns(10);
		contentPane.add(address);
		
		id = new JTextField();
		id.setBounds(140, 227, 211, 28);
		id.setColumns(10);
		contentPane.add(id);
		
		phoneno = new JTextField();
		
		phoneno.setBounds(140, 274, 211, 28);
		phoneno.setColumns(10);
		contentPane.add(phoneno);
		
		JButton btnNewButton = new JButton("Book");
		btnNewButton.setBounds(10, 473, 121, 47);
		btnNewButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				//isStrongPassword();
				
				try {
			         //    	int  number = Integer.parseInt(phoneno.getText());
					qw = (phoneno.getText());
					 if (isStrongPassword(qw)) {
						 
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","Thisanthan@123");
					System.out.println("1");
					PreparedStatement st = con.prepareStatement("insert into room(i,name,address,id,phoneno,CHECKIN,CHECKOUT)values('"+i.getText()+"','"+name.getText()+"','"+address.getText()+"','"+id.getText()+"','"+phoneno.getText()+"','"+(checkin.getDate()).toString()+"','"+(checkout.getDate()).toString()+"')");
					PreparedStatement st1 = con.prepareStatement("UPDATE room2 SET id = ? WHERE id IS NULL ORDER BY j LIMIT 1");
					st1.setString(1, id.getText());	
					PreparedStatement st2 = con.prepareStatement("UPDATE room3 SET id = ? WHERE id IS NULL ORDER BY i LIMIT 1");
					st2.setString(1, id.getText());
					PreparedStatement st3 = con.prepareStatement("insert into room1(id)values('"+id.getText()+"')");
					int rowaffected=st.executeUpdate();
					int rowaffected1=st1.executeUpdate();
					int rowaffected2=st2.executeUpdate();
					int rowaffected3=st3.executeUpdate();
				//	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
					
					if(rowaffected>0) {
						BillGeneratorGUI frame = new BillGeneratorGUI();
						frame.setVisible(true);
						JOptionPane.showMessageDialog(null,"Room booked");
					}else {
						JOptionPane.showMessageDialog(null,"code error");
					}
					}
					 else {
						 JOptionPane.showMessageDialog(null,"enter 10 digit number");
					 }
				}
				 
				catch(Exception e1){
					JOptionPane.showMessageDialog(null,"give the phoneno not in Integer");
			e1.printStackTrace();	
				}
				
				 }

			private boolean isStrongPassword(String qw) {
				 int minLength = 10;
			        
			        if (qw.length()==10) {
			        	return true;
			        }
				// TODO Auto-generated method stub
				return false;
			}
			

		});
		
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnNewButton);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBounds(194, 473, 121, 47);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ie=id.getText();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","Thisanthan@123");
					System.out.println("1");
					Statement st = con.createStatement();
					boolean b = st.execute("update room set name='"+name.getText()+"',address='"+address.getText()+"',id='"+id.getText()+"',phoneno='"+phoneno.getText()+"' where id="+ie);
					if(!b) {
						JOptionPane.showMessageDialog(null,"updated");
					}else {
						JOptionPane.showMessageDialog(null,"invalid");
					}
					}
				catch(Exception e1){
				e1.printStackTrace();	
				}
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("CANCEL");
		btnDelete.setBounds(10, 563, 121, 47);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int code=Integer.parseInt(id.getText());
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","Thisanthan@123");
					String sql="delete from room  WHERE id= ? ";
					PreparedStatement pts=con.prepareStatement(sql);
					pts.setInt(1, code );
					pts.execute();
					JOptionPane.showMessageDialog(null,"deleted");
					con.close();
				}
				catch(Exception ec) {
					JOptionPane.showMessageDialog(null,"it has not been registered");
					
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnDelete);
		
		JButton btnExit = new JButton("SHOW");
		btnExit.setBounds(194, 563, 121, 47);
		btnExit.addActionListener(new ActionListener() {
		

			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","Thisanthan@123");
					Statement st=con.createStatement();
					String sql="select * from room";
					
					PreparedStatement pts=con.prepareStatement(sql);
					ResultSet rs= pts.executeQuery();
					    DefaultTableModel dt=(DefaultTableModel)table.getModel();
					dt.setRowCount(0);
					while(rs.next()) {
					
					Object o[]= {rs.getString("i"),rs.getString("name"),rs.getString("address"),rs.getString("id"),rs.getString("phoneno"),rs.getString("checkin"),rs.getString("checkout")};
					dt.addRow(o);
					}
			}
				catch(Exception ec) {
					JOptionPane.showMessageDialog(null,"it has not been registered");
					ec.printStackTrace();
				}
				/*try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/ok1","root","Thisanthan@123");
					Statement st=con.createStatement();
					String sql="select * from room";
					
					PreparedStatement pts=con.prepareStatement(sql);
					ResultSet rs= pts.executeQuery();
					    DefaultTableModel dt=(DefaultTableModel)table.getModel();
					dt.setRowCount(0);
					while(rs.next()) {
					
					Object o[]= {rs.getString("i")};
					dt.addRow(o);
					}
			}
				catch(Exception ec) {
					JOptionPane.showMessageDialog(null,"it has not been registered");
					ec.printStackTrace();
				}*/
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnExit);
		
		JLabel lblRoomNo = new JLabel("ROOM NO");
		lblRoomNo.setForeground(new Color(255, 255, 255));
		lblRoomNo.setBounds(10, 70, 150, 37);
		lblRoomNo.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblRoomNo);
		
		i = new JTextField();
		i.setBounds(140, 79, 52, 28);
		i.setColumns(10);
		contentPane.add(i);
		
		JButton btnExit_2 = new JButton("EXIT");
		btnExit_2.setBounds(917, 577, 52, 47);
		btnExit_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				booking  frame = new booking();
				frame.setVisible(true);
			}
		});
		btnExit_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnExit_2);
		
		JLabel lblDate = new JLabel("CHECKIN");
		lblDate.setForeground(new Color(255, 255, 255));
		lblDate.setBounds(10, 323, 150, 37);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblDate);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(398, 265, 630, 300);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\niksh\\Downloads\\New Doc 04-07-2024 14.46_2.jpg"));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblCheckout = new JLabel("CHECKOUT");
		lblCheckout.setForeground(new Color(255, 255, 255));
		lblCheckout.setBounds(10, 366, 150, 37);
		lblCheckout.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblCheckout);
		
		

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(402, 54, 626, 185);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"roomno", "name", "address", "id", "phoneno", "checkin", "checkout"
			}
		));
		scrollPane.setViewportView(table);
		
		 
		     

		       
		  
		
	}
}
