package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class other extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					other frame = new other();
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
	public other() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 996, 674);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 64, 0));
		panel.setBounds(10, 10, 972, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HOTEL MANAGEMENT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(10, 5, 952, 45);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new booking().setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(921, 0, 51, 21);
		panel.add(btnNewButton);
		
		JEditorPane dtrpnFhjbfh = new JEditorPane();
		dtrpnFhjbfh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dtrpnFhjbfh.setText("Introduction:\r\nHotel management is a multifaceted discipline that encompasses various aspects of hospitality, operations, and customer service. It plays a pivotal role in ensuring the smooth functioning of hotels and resorts, catering to the diverse needs of guests while maintaining profitability and efficiency. \r\n\r\nThe Role of Hotel Managers:\r\nHotel managers serve as the linchpin of hospitality establishments, overseeing all operational aspects to deliver exceptional guest experiences. Their responsibilities range from strategic planning and financial management to staff supervision and guest relations. Effective hotel managers possess a diverse skill set, including leadership, communication, problem-solving, and adaptability, enabling them to navigate the complexities of the industry with finesse.\r\n\r\nOperational Management:\r\nOperational management lies at the heart of hotel management, encompassing day-to-day activities such as reservations, housekeeping, food and beverage services, and maintenance. Streamlining operations requires meticulous planning, resource allocation, and adherence to industry standards and regulations. Leveraging technology, such as property management systems (PMS) and customer relationship management (CRM) software, can enhance efficiency and guest satisfaction while optimizing resource utilization.\r\n\r\nGuest Experience and Service Excellence:\r\nThe guest experience is paramount in hotel management, as satisfied guests are more likely to become repeat customers and brand advocates. Hoteliers strive to create memorable experiences through personalized service, attention to detail, and anticipating guest needs. From the moment of reservation to check-out, every interaction shapes the guest's perception of the hotel, emphasizing the importance of consistent service excellence across all touchpoints.\r\n\r\nRevenue Management and Marketing:\r\nEffective revenue management is crucial for maximizing profitability and optimizing room occupancy and rates. Hotel managers employ pricing strategies, demand forecasting, and distribution channels to achieve revenue goals while maintaining competitiveness in the market. Marketing initiatives play a pivotal role in attracting guests and building brand awareness, encompassing digital marketing, social media engagement, loyalty programs, and partnerships with travel agencies and online platforms.\r\n\r\nHotel management is a dynamic and ever-evolving field that requires agility, creativity, and a customer-centric approach. ");
		dtrpnFhjbfh.setBounds(10, 80, 972, 547);
		contentPane.add(dtrpnFhjbfh);
	}
}
