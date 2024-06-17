package hotel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class print extends JFrame {
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JLabel errorLabel;
    private JLabel lblNewLabel_3;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    print frame = new print();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public print() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 664, 448);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(39, 49, 144, 40);
        contentPane.add(lblNewLabel);

        usernameField = new JTextField();
        usernameField.setBounds(280, 49, 213, 39);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Password:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setBounds(39, 126, 120, 41);
        contentPane.add(lblNewLabel_1);

        passwordField = new JPasswordField();
        passwordField.setBounds(280, 127, 213, 38);
        contentPane.add(passwordField);

        JLabel lblNewLabel_2 = new JLabel("Confirm Password:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2.setBounds(39, 207, 213, 30);
        contentPane.add(lblNewLabel_2);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(280, 193, 213, 39);
        contentPane.add(confirmPasswordField);

        errorLabel = new JLabel("");
        errorLabel.setBounds(110, 365, 401, 20);
        errorLabel.setForeground(Color.RED);
        contentPane.add(errorLabel);

        JButton registerButton = new JButton("save");
        registerButton.setBounds(187, 307, 151, 40);
        registerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        contentPane.add(registerButton);
        
        lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\niksh\\Downloads\\th (1).jpg"));
        lblNewLabel_3.setBounds(0, 0, 650, 411);
        contentPane.add(lblNewLabel_3);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (password.equals(confirmPassword)) {
            if (isStrongPassword(password)) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "Thisanthan@123");

                    String query = "update access set password = '"+password+"' where username = '"+username+"'";
                    PreparedStatement pstmt = conn.prepareStatement(query);
              
                    pstmt.executeUpdate();

                    conn.close();

                    JOptionPane.showMessageDialog(contentPane, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close the registration form after successful registration
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(contentPane, "Error registering user!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                errorLabel.setText("Password must be at least 8 characters and at least one uppercase, one lowercase, and one digit.");
            }
        } else {
            errorLabel.setText("Passwords do not match.");
        }
    }

    private boolean isStrongPassword(String password) {
        int minLength = 8;
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;

        if (password.length() < minLength) {
            return false;
        }

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            }
        }

        return hasUppercase && hasLowercase && hasDigit;
    }
}
