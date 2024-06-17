package hotel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class create extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField newuser;
    private JPasswordField newpass;
    private JLabel lbltxt;
    private JButton btnGeneratePassword;
    private JLabel lblCaptcha;
    private JTextField txtCaptcha;
    private String captchaCode;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    create frame = new create();
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
    public create() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 654, 433);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(159, 116, 77));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        newuser = new JTextField();
        newuser.setBounds(200, 50, 245, 30);
        contentPane.add(newuser);
        newuser.setColumns(10);

        JLabel user = new JLabel("Enter new username:");
        user.setFont(new Font("Tahoma", Font.BOLD, 16));
        user.setBounds(50, 50, 150, 30);
        contentPane.add(user);

        JLabel pass = new JLabel("Enter the password:");
        pass.setFont(new Font("Tahoma", Font.BOLD, 16));
        pass.setBounds(50, 100, 150, 30);
        contentPane.add(pass);

        newpass = new JPasswordField();
        newpass.setBounds(200, 100, 245, 30);
        contentPane.add(newpass);

        lbltxt = new JLabel("");
        lbltxt.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbltxt.setBounds(50, 219, 473, 21);
        contentPane.add(lbltxt);

        btnGeneratePassword = new JButton("Generate Password");
        btnGeneratePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateStrongPassword();
            }
        });
        btnGeneratePassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGeneratePassword.setBounds(213, 180, 200, 30);
        contentPane.add(btnGeneratePassword);

        JLabel lblCaptcha = new JLabel("Enter the captcha:");
        lblCaptcha.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCaptcha.setBounds(50, 250, 150, 30);
        contentPane.add(lblCaptcha);

        txtCaptcha = new JTextField();
        txtCaptcha.setBounds(200, 250, 150, 30);
        contentPane.add(txtCaptcha);
        txtCaptcha.setColumns(10);

        JLabel lblCaptchaCode = new JLabel("Captcha:");
        lblCaptchaCode.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCaptchaCode.setBounds(360, 250, 80, 30);
        contentPane.add(lblCaptchaCode);
        
       

        JLabel lblCaptchaValue = new JLabel(generateCaptcha());
        lblCaptchaValue.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCaptchaValue.setBounds(450, 250, 100, 30);
        contentPane.add(lblCaptchaValue);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (verifyCaptcha()) {
                    saveUserData();
                  //  registerUser();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Invalid captcha! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtCaptcha.setText("");
                    lblCaptchaValue.setText(generateCaptcha());
                }
            }

		/*	private void registerUser() {
				// TODO Auto-generated method stub
				   String username1 = newuser.getText();
		            String password1 = new String(newpass.getPassword());
		            //String confirmPassword = new String(confirmPasswordField.getPassword());

		          
		                if (isStrongPassword(password1)) {
		                    try {
		                        Class.forName("com.mysql.cj.jdbc.Driver");
		                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "Thisanthan@123");

		                        String query = "insert into access value(username1,password1)";
		                        PreparedStatement pstmt = con.prepareStatement(query);
		                  
		                        pstmt.executeUpdate();

		                        con.close();

		                        JOptionPane.showMessageDialog(contentPane, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		                        dispose(); // Close the registration form after successful registration
		                    } catch (Exception ex) {
		                        ex.printStackTrace();
		                        JOptionPane.showMessageDialog(contentPane, "Error registering user!", "Error", JOptionPane.ERROR_MESSAGE);
		                    }
		                } else {
		                
							errorLabel.setText("Password must be at least 8 characters and at least one uppercase, one lowercase, and one digit.");
		                }
		            } */
			
				
			
        });

        btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnSave.setBounds(200, 300, 150, 30);
        contentPane.add(btnSave);
        
        

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	loginpage frame = new loginpage();
            	frame.setVisible(true);
                
            }
        });
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnExit.setBounds(370, 300, 100, 30);
        contentPane.add(btnExit);
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton("show");
        rdbtnNewRadioButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(rdbtnNewRadioButton.isSelected()) {
					newpass.setEchoChar((char)0);
				}
				else {
					newpass.setEchoChar('*');
				}
        	}
        });
        rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        rdbtnNewRadioButton.setBounds(458, 103, 103, 21);
        contentPane.add(rdbtnNewRadioButton);
        
        JLabel errorLabel = new JLabel("");
        errorLabel.setBounds(10, 200, 566, 40);
        contentPane.add(errorLabel);
        
        JLabel lblPasswordMustHave = new JLabel("strong password  atleast 8 character,one uppercase,symbol and num");
        lblPasswordMustHave.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblPasswordMustHave.setBounds(10, 140, 576, 30);
        contentPane.add(lblPasswordMustHave);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\niksh\\Downloads\\sign-up-young-man-press-digital-button-interface-front-him-43410531.jpg"));
        lblNewLabel.setBounds(0, 0, 640, 396);
        contentPane.add(lblNewLabel);
        
       
    }

    private void generateStrongPassword() {
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{}|;:,.<>?";
        int passwordLength = 10;
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            int index = (int) (Math.random() * symbols.length());
            password.append(symbols.charAt(index));
        }

        newpass.setText(password.toString());
    }

    private String generateCaptcha() {
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int captchaLength = 5;
        StringBuilder captcha = new StringBuilder();

        for (int i = 0; i < captchaLength; i++) {
            int index = (int) (Math.random() * symbols.length());
            captcha.append(symbols.charAt(index));
        }

        captchaCode = captcha.toString();
        return captchaCode;
    }

    private boolean verifyCaptcha() {
        String enteredCaptcha = txtCaptcha.getText();
        return enteredCaptcha.equals(captchaCode);
    }

    private void saveUserData() {
        String username = newuser.getText();
        String password = newpass.getText();
        if (isStrongPassword(password)) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "Thisanthan@123");

            String query = "INSERT INTO access (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

            conn.close();

            JOptionPane.showMessageDialog(contentPane, "User created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new loginpage().setVisible(true);
            //dispose(); // Close the frame after successful creation
        }
         catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(contentPane, "Error creating user!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
        else {
        	JOptionPane.showMessageDialog(contentPane, "Password must be at least 8 characters and at least one uppercase, one lowercase, and one digit.", "Error", JOptionPane.ERROR_MESSAGE);
        
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
