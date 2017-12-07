package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.mail.internet.NewsAddress;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField pwd;
	
	private UserDao userDao = new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setTitle("Welcome to Staff Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" Username:");
		lblNewLabel.setBounds(143, 98, 75, 16);
		contentPane.add(lblNewLabel);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String un=username.getText();
				String pass=new String(pwd.getPassword());
				if (un.equals("")||pass.equals("")) {
					JOptionPane.showMessageDialog(LoginFrame.this, "Username and password can not be empty！");
					return;
				}
				
				if (userDao.checkLogin(un, pass)) {
					MainFrame mainFrame=new MainFrame();
					mainFrame.setVisible(true);
					dispose();
				}else{
					JOptionPane.showMessageDialog(LoginFrame.this, "Username or password is wrong!");
				}
			}
		});
		loginBtn.setBounds(355, 269, 117, 29);
		contentPane.add(loginBtn);
		
		username = new JTextField();
		username.setBounds(238, 93, 130, 26);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel label = new JLabel("Password:");
		label.setBounds(143, 150, 75, 16);
		contentPane.add(label);
		
		pwd = new JPasswordField();
		pwd.setBounds(238, 145, 130, 26);
		contentPane.add(pwd);
	}
}
