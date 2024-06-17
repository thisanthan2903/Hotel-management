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

public class rooming extends JFrame {

    private JButton[][] buttons;
    private ActionListener lis;

    public rooming() {
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
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                JButton button = new JButton((i * 5) + j + 1 + "");
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
            	new booking().setVisible(true);
            	dispose();
                // Handle back button click event
                // Open booking frame or perform other actions
            }
        });
        contentPane.add(backButton);
    }

    private void connectToDatabase() {
        // Connect to the database and initialize button colors
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/room", "root", "Thisanthan@123");

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 5; j++) {
                    String query = "SELECT * FROM room2 WHERE j = ?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setInt(1, (i * 5) + j + 1);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        buttons[i][j].setBackground(Color.RED);
                        JButton button = new JButton( "not available");
                        button.setFont(new Font("Tahoma", Font.BOLD, 16));
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
            PreparedStatement statement = con.prepareStatement("INSERT INTO room2 (j) VALUES (?)");
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
                    rooming frame = new rooming();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
