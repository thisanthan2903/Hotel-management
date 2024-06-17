package hotel;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.awt.Color;

public class food1 extends JFrame implements ActionListener {
    private JComboBox<String> foodItemsComboBox;
    private JComboBox<Integer> qtyComboBox;
    private JLabel totalPriceLabel;

    private HashMap<String, Double> itemPrices;

    public food1() {
        setTitle("Food Order App");
        setSize(625, 389);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the HashMap for item prices
        itemPrices = new HashMap<>();
        itemPrices.put("Biryani", 100.99);
        itemPrices.put("Butter Chicken", 208.99);
        itemPrices.put("chicken noodles", 120.99);
        itemPrices.put("full grill", 408.99);
        itemPrices.put("naan", 30.99);
        itemPrices.put("Butter naan", 48.99);
        //itemPrices.put("plane", 120.99);
        itemPrices.put("chicken rice", 128.99);
        // Add more items and prices here

        // Create components
        JPanel panel = new JPanel();
        foodItemsComboBox = new JComboBox<>(itemPrices.keySet().toArray(new String[0]));
        foodItemsComboBox.setBounds(246, 76, 101, 21);
        qtyComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        qtyComboBox.setBounds(246, 157, 31, 19);
        JButton calculateButton = new JButton("Calculate Total");
        calculateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        calculateButton.setBounds(176, 272, 185, 47);
        calculateButton.addActionListener(this);
        totalPriceLabel = new JLabel("Total Price: $0.00");
        totalPriceLabel.setForeground(new Color(255, 255, 255));
        totalPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        totalPriceLabel.setBounds(414, 293, 151, 28);
        panel.setLayout(null);

        // Add components to panel
        JLabel label = new JLabel("Select Food Item: ");
        label.setFont(new Font("Tahoma", Font.PLAIN, 19));
        label.setBounds(35, 59, 175, 47);
        panel.add(label);
        panel.add(foodItemsComboBox);
        JLabel label_1 = new JLabel("Select Quantity: ");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
        label_1.setBounds(35, 139, 151, 47);
        panel.add(label_1);
        panel.add(qtyComboBox);
        panel.add(calculateButton);
        panel.add(totalPriceLabel);

        // Add panel to frame
        getContentPane().add(panel);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\niksh\\OneDrive\\Desktop\\WhatsApp Image 2024-06-10 at 22.53.46_dd8bc860.jpg"));
        lblNewLabel.setBounds(0, 0, 611, 352);
        panel.add(lblNewLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Calculate Total")) {
            String selectedItem = (String) foodItemsComboBox.getSelectedItem();
            int selectedItemQty = (int) qtyComboBox.getSelectedItem();
            double price = 0.0; // Initialize price variable

            // Retrieve price for the selected item from the map
            if (itemPrices.containsKey(selectedItem)) {
                price = itemPrices.get(selectedItem);
            } else {
                // Handle case when selected item is not found in the map
                // You could display an error message or set a default price
                System.out.println("Price not found for selected item: " + selectedItem);
            }

            double totalPrice = price * selectedItemQty;
            totalPriceLabel.setText("Total Price: $" + String.format("%.2f", totalPrice));
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/about","root","Thisanthan@123");
                String sql = "INSERT INTO food (items, qty, price) VALUES (?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, selectedItem);
                statement.setInt(2, selectedItemQty);
                statement.setDouble(3, totalPrice);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                	
                            // Generate the bill
                            StringBuilder bill = new StringBuilder();
                            bill.append("=============================================\n");
                            bill.append("                     HOTEL MANAGEMENT               \n");
                            bill.append("=============================================\n\n");
                            bill.append(String.format("%-15s %-10s %-10s  \n", "food ordered", "   quantity", "     totalPrice"));

                            
                            for (int i = 0; i < 1; i++) {
                            /*    
                                String seatType = (String) table1.getValueAt(i, 0);
                                int price = (int) table1.getValueAt(i, 1);
                                int quantity = (int) table1.getValueAt(i, 2);
                                int total = (int) table1.getValueAt(i, 3);
                                String name = (String) table1.getValueAt(i, 4);*/

                                bill.append(String.format("%-15s              %-10s                    %-10s \n", selectedItem,  selectedItemQty, totalPrice));
                            }

                            
                            bill.append("\nfood ordered: ").append(selectedItem).append("\n");
                            bill.append("quantity: ").append(selectedItemQty).append("\n");
                            bill.append("Total price: ").append(totalPrice).append("\n");

                            
                            JTextArea textArea = new JTextArea(bill.toString());
                            textArea.setFont(new Font("Arial", Font.PLAIN, 14));
                            textArea.setEditable(false);
                            JScrollPane scrollPane = new JScrollPane(textArea);
                            scrollPane.setPreferredSize(new Dimension(400, 300));
                            JOptionPane.showMessageDialog(null, scrollPane, "food Bill", JOptionPane.PLAIN_MESSAGE);
                            //dispose();
                            try {
            					textArea.print();
            				} catch (PrinterException e1) {
            					// TODO Auto-generated catch block
            					e1.printStackTrace();
            				}
                     
                    System.out.println("Bill generated and stored successfully!");
                    new booking().setVisible(true);
                } else {
                    System.out.println("Failed to generate and store bill.");
                }
                conn.close();
            } catch (SQLException e3) {
                e3.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new food1();
    }
}
