package hotel;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;

public class park extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JButton btnNewButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    park frame = new park();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public park() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 716, 436);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 682, 221);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null},
        		{null},
        		{null},
        		{null},
        		{null},
        		{null},
        		{null},
        		{null},
        		{null},
        		{null},
        	},
        	new String[] {
        		"BOOKING DETAILS"
        	}
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(147);
        scrollPane.setViewportView(table);

        btnNewButton = new JButton("SHOW");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        btnNewButton.addActionListener(e -> fetchData());
        btnNewButton.setBounds(237, 328, 176, 48);
        contentPane.add(btnNewButton);
    }

    private void fetchData() {
    	 try {
    	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/room", "root", "");

    	        String query = "SELECT r3.i, r2.j, r1.report1 " +
    	                "FROM room3 r3 " +
    	                "JOIN room2 r2 ON r3.id = r2.id " +
    	                "JOIN room1 r1 ON r2.id = r1.id";
    	        PreparedStatement statement = conn.prepareStatement(query);
    	        ResultSet rs = statement.executeQuery();

    	        DefaultTableModel dt = (DefaultTableModel) table.getModel();
    	        dt.setRowCount(0);
    	        while (rs.next()) {
    	            String i = rs.getString(1);
    	            String j = rs.getString(2);
    	            String report1 = rs.getString(3);
    	            
    	            // Add only non-null elements to the table
    	            if (i != null) {
    	                dt.addRow(new Object[] { i });
    	            }
    	            if (j != null) {
    	                dt.addRow(new Object[] { j });
    	            }
    	            if (report1 != null) {
    	                dt.addRow(new Object[] { report1 });
    	            }
    	        }

    	        conn.close();
    	    } catch (SQLException e1) {
    	        JOptionPane.showMessageDialog(this, "Failed to fetch data: " + e1.getMessage(), "Error",
    	                JOptionPane.ERROR_MESSAGE);
    	        e1.printStackTrace();
    	    }
    }
}
