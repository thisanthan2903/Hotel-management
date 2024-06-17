package hotel;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class BillGeneratorGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtRoomId;
    private JComboBox<String> comboBoxMembers;
    private JComboBox<String> comboBoxTransport;
    private JDateChooser dateChooserCheckin;
    private JDateChooser dateChooserCheckout;
//	private String checkin;
	//private String checkout;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BillGeneratorGUI frame = new BillGeneratorGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BillGeneratorGUI() {
        setTitle("Bill Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 472);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 128));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblMembers = new JLabel("Number of Members:");
        lblMembers.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblMembers.setForeground(new Color(0, 0, 0));
        lblMembers.setBounds(10, 22, 172, 39);
        contentPane.add(lblMembers);

        comboBoxMembers = new JComboBox<String>();
        comboBoxMembers.setBounds(225, 26, 137, 39);
        comboBoxMembers.addItem("1");
        comboBoxMembers.addItem("2");
        comboBoxMembers.addItem("3");
        comboBoxMembers.addItem("4");
        contentPane.add(comboBoxMembers);

        JLabel lblTransport = new JLabel("Mode of Transport:");
        lblTransport.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTransport.setForeground(new Color(0, 0, 0));
        lblTransport.setBounds(12, 85, 203, 32);
        contentPane.add(lblTransport);

        comboBoxTransport = new JComboBox<String>();
        comboBoxTransport.setBounds(225, 86, 165, 39);
        comboBoxTransport.addItem("Car");
        comboBoxTransport.addItem("Travels");
        comboBoxTransport.addItem("Flight");
        comboBoxTransport.addItem("Two Wheeler");
        contentPane.add(comboBoxTransport);

        JLabel lblRoomId = new JLabel("Room No:");
        lblRoomId.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblRoomId.setForeground(new Color(0, 0, 0));
        lblRoomId.setBounds(10, 141, 203, 42);
        contentPane.add(lblRoomId);

        txtRoomId = new JTextField();
        txtRoomId.setBounds(225, 147, 137, 39);
        contentPane.add(txtRoomId);
        txtRoomId.setColumns(10);

        JLabel lblCheckinDate = new JLabel("Check-in Date:");
        lblCheckinDate.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblCheckinDate.setForeground(new Color(0, 0, 0));
        lblCheckinDate.setBounds(10, 211, 203, 42);
        contentPane.add(lblCheckinDate);

        dateChooserCheckin = new JDateChooser();
        dateChooserCheckin.setBounds(225, 217, 137, 39);
        contentPane.add(dateChooserCheckin);

        JLabel lblCheckoutDate = new JLabel("Checkout Date:");
        lblCheckoutDate.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblCheckoutDate.setForeground(new Color(0, 0, 0));
        lblCheckoutDate.setBounds(20, 282, 193, 39);
        contentPane.add(lblCheckoutDate);

        dateChooserCheckout = new JDateChooser();
        dateChooserCheckout.setBounds(225, 286, 137, 40);
        contentPane.add(dateChooserCheckout);

        JButton btnGenerateBill = new JButton("Generate Bill");
        btnGenerateBill.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnGenerateBill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });
        btnGenerateBill.setBounds(196, 345, 182, 50);
        contentPane.add(btnGenerateBill);
        
        JButton btnExit = new JButton("exit");
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new booking().setVisible(true);
        	}
        });
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnExit.setBounds(465, 345, 113, 50);
        contentPane.add(btnExit);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\niksh\\Downloads\\untitled-design-6.png"));
        lblNewLabel.setBounds(0, 0, 636, 435);
        contentPane.add(lblNewLabel);
    }

    private void generateBill() {
        String members = (String) comboBoxMembers.getSelectedItem();
        String transport = (String) comboBoxTransport.getSelectedItem();
        int roomId = Integer.parseInt(txtRoomId.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String checkinDate = sdf.format(dateChooserCheckin.getDate());
        String checkoutDate = sdf.format(dateChooserCheckout.getDate());
        double billAmount = calculateBill(roomId, checkinDate, checkoutDate);
        storeBillInDatabase(members, transport, billAmount);
    }

    private double calculateBill(int roomId, String checkinDate, String checkoutDate) {
        double roomRate = 1000.0; // Example room rate per night
        int numberOfNights = calculateNumberOfNights(checkinDate, checkoutDate);
        return roomRate * numberOfNights;
    }

    private int calculateNumberOfNights(String checkinDate, String checkoutDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date checkin = sdf.parse(checkinDate);
            Date checkout = sdf.parse(checkoutDate);
            long differenceInMillis = checkout.getTime() - checkin.getTime();
            long differenceInDays = differenceInMillis / (1000 * 60 * 60 * 24);
            return (int) differenceInDays;
          
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void storeBillInDatabase(String members, String transport, double billAmount) {
        try {
        	 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
             String checkinDate = sdf.format(dateChooserCheckin.getDate());
             String checkoutDate = sdf.format(dateChooserCheckout.getDate());
        	
             
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booking", "root", "Thisanthan@123");
            String sql = "INSERT INTO booking (qw, qe, amount) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, members);
            statement.setString(2, transport);
            if(billAmount == 0.00) {
            	 statement.setDouble(3, 1000.00);
            }else {
            statement.setDouble(3, billAmount);
            }
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                StringBuilder bill = new StringBuilder();
                bill.append("=============================================\n");
                bill.append("                     HOTEL MANAGEMENT               \n");
                bill.append("=============================================\n\n");
                bill.append(String.format("%-20s %-20s %-20s  \n", "Members", "Transport", "Bill Amount"));
                bill.append(String.format("%-20s %-20s %-20s  \n", members, transport, billAmount));
                bill.append("\nCheck-in Date: ").append(checkinDate).append("\n");
                bill.append("Checkout Date: ").append(checkoutDate).append("\n");
                if(billAmount == 0.00) {
                	 bill.append("Total Price: ").append(1000.00).append("\n");
               }else {
            	   bill.append("Total Price: ").append(billAmount).append("\n");
               }
               

                JTextArea textArea = new JTextArea(bill.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 300));
                JOptionPane.showMessageDialog(null, scrollPane, "Room Bill", JOptionPane.PLAIN_MESSAGE);
                textArea.print();
                System.out.println("Bill generated and stored successfully!");
            } else {
                System.out.println("Failed to generate and store bill.");
            }
            conn.close();
        } catch (SQLException | PrinterException e) {
            e.printStackTrace();
        }
    }
}

