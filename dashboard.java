package hotel;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class booking extends JFrame {

    private static final long serialVersionUID = 1L;
    protected static final booking Signup = null;
    private JPanel contentPane;
    private JLabel imgslider;
    private ImageIcon[] images;
    private int currentIndex = 0;
    private Timer timer;
    private int xPosition;
   // private  int image = 1;;
  //  private var imageFiles = [];

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    booking frame = new booking();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public booking() {
    	setUndecorated(true);
        setTitle("Movie Vault");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 815, 518);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(192, 192, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel HOME = new JLabel("HOTEL MANAGEMENT");
        HOME.setBackground(new Color(255, 255, 255));
        HOME.setHorizontalAlignment(SwingConstants.CENTER);
        HOME.setForeground(new Color(255, 255, 255));
        HOME.setFont(new Font("Tahoma", Font.BOLD, 31));
        HOME.setBounds(0, 0, 815, 33);
        contentPane.add(HOME);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 72, 815, 2);
        contentPane.add(separator);

        imgslider = new JLabel("");
        imgslider.setBackground(new Color(0, 0, 0));
        imgslider.setBorder(null);
        imgslider.setHorizontalAlignment(SwingConstants.CENTER);
        imgslider.setBounds(0, 80, 805, 367);
        contentPane.add(imgslider);
        File directory = new File("C:\\Users\\niksh\\OneDrive\\Pictures\\imageFiles");
        File[] imageFiles = directory.listFiles();
        images = new ImageIcon[imageFiles.length];

        for (int i = 0; i < imageFiles.length; i++) {
            images[i] = new ImageIcon(imageFiles[i].getAbsolutePath());
        }

        imgslider.setIcon(new ImageIcon("C:\\Users\\niksh\\Downloads\\17-172961_3840x2160-four-seasons-hotel-2016-shanghai-china-4k (1).jpg"));

        xPosition = getWidth();
        
        JButton btnNe = new JButton("X");
        btnNe.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//new loginpage().setVisible(true);
        		//System.exit(0);
        		dispose();
        	}
        });
        
        JLabel lblanimation = new JLabel("Welcome to HOTELMANAGEMENT, your ultimate destination for ROOM BOOKING . We provides a seamless experience for ROOM BOOKING enthusiasts to explore ROOMS FECILITATE .");
        lblanimation.setForeground(new Color(128, 0, 64));
        lblanimation.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblanimation.setBounds(10, 438, 1169, 56);
        contentPane.add(lblanimation);
        btnNe.setForeground(Color.WHITE);
        btnNe.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNe.setFocusable(false);
        btnNe.setFocusTraversalKeysEnabled(false);
        btnNe.setFocusPainted(false);
        btnNe.setBorder(null);
        btnNe.setBackground(new Color(0, 0, 0));
        btnNe.setBounds(770, 0, 45, 33);
        contentPane.add(btnNe);
        
        JLabel lblReport = new JLabel("Report");
        lblReport.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		report frame = new report();
        		frame.setVisible(true);
        	}
        });
        lblReport.setForeground(new Color(0, 255, 255));
        lblReport.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblReport.setBounds(701, 33, 114, 41);
        contentPane.add(lblReport);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 128));
        panel.setBounds(0, 0, 815, 74);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblFoodservice = new JLabel("Foodservice");
        lblFoodservice.setForeground(new Color(0, 255, 255));
        lblFoodservice.setBounds(534, 33, 157, 41);
        panel.add(lblFoodservice);
        lblFoodservice.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		food1 frame = new food1();
        		frame.setVisible(true);
        	}
        });
        lblFoodservice.setFont(new Font("Tahoma", Font.BOLD, 25));
        
        JLabel lblLogin = new JLabel("SingleRoom");
        lblLogin.setForeground(new Color(0, 255, 255));
        lblLogin.setBounds(355, 33, 189, 41);
        panel.add(lblLogin);
        lblLogin.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		rooming frame = new rooming();
        		frame.setVisible(true);
        	}
        });
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
        
        JLabel lblNewLabel = new JLabel("About");
        lblNewLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		other frame = new other();
        		frame.setVisible(true);
        	}
        });
        lblNewLabel.setForeground(new Color(0, 255, 255));
        lblNewLabel.setBounds(97, 33, 114, 41);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        
        JLabel lblHome = new JLabel("Home");
        lblHome.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new loginpage().setVisible(true);
        	}
        });
        lblHome.setForeground(Color.CYAN);
        lblHome.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblHome.setBounds(10, 33, 114, 41);
        panel.add(lblHome);
        
        JLabel lblSingleroom = new JLabel("DoubleRoom");
        lblSingleroom.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new Dashboard2().setVisible(true);
        	}
        });
        lblSingleroom.setForeground(Color.CYAN);
        lblSingleroom.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblSingleroom.setBounds(186, 33, 160, 41);
        panel.add(lblSingleroom);

        Timer textTimer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xPosition--;
                if (xPosition + lblanimation.getWidth() < 0) {
                    xPosition = getWidth();
                }
                lblanimation.setLocation(xPosition, lblanimation.getY());
            }
        });
        textTimer.start();

        startSlideshow();
    }

    private void startSlideshow() {
        timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % images.length;
                imgslider.setIcon(images[currentIndex]);
            }
        });
        timer.start();
}
}