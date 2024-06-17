package hotel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dashboard2 extends JFrame {

    private JButton[][] buttons;
    private ActionListener lis;

    public Dashboard2() {
        initialize();
        connectToDatabase();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("Room Vacancy");

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 15));

        buttons = new JButton[10][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                JButton button = new JButton((i * 5) + j + 51 + "");
                button.setFont(new Font("Tahoma", Font.BOLD, 16));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();
                        String buttonText = clickedButton.getText();
                        int roomNumber = Integer.parseInt(buttonText);

                        // Check if the button is green
                        if (clickedButton.getBackground() == Color.GREEN) {
                            // Insert the room number into the database
                            insertIntoDatabase(roomNumber);
                            // Change button color to red
                            clickedButton.setBackground(Color.RED);
                            new Dashboard().setVisible(true);
                            dispose();
                        }
                    }
                });
                buttons[i][j] = button;
                panel.add(button);
            }
        }
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(0, 0, 786, 493);
        contentPane.add(scrollPane);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Tahoma", Font.BOLD, 28));
        backButton.setBounds(0, 497, 786, 66);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle back button click event
                // Open booking frame or perform other actions
            	new booking().setVisible(true);
            }
        });
        contentPane.add(backButton);
    }

    private void connectToDatabase() {
        // Connect to the database and initialize button colors
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/room", "root", "Thisanthan@123");

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    String query = "SELECT * FROM room3 WHERE i = ?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setInt(1, (i * 5) + j + 51);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        buttons[i][j].setBackground(Color.RED);
                    } else {
                        buttons[i][j].setBackground(Color.GREEN);
                    }
                }
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle database connection error
        }
    }
    private void insertIntoDatabase(int roomNumber) {
        try {
            // Connect to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/room", "root", "Thisanthan@123");

            // Prepare SQL statement
            PreparedStatement statement = con.prepareStatement("INSERT INTO room3 (i) VALUES (?)");
            statement.setInt(1, roomNumber);

            // Execute the statement
            statement.executeUpdate();

            // Close the connection
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection error
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dashboard2 frame = new Dashboard2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
