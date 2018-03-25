import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage 
{

	private DatabaseClient dbc;
	JFrame frame;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		dbc = new DatabaseClient();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBackground(Color.DARK_GRAY);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 449, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("E-mail");
		lblLogin.setBackground(Color.WHITE);
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 20));
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setBounds(48, 98, 101, 21);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPassword.setBounds(48, 139, 101, 21);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(179, 139, 186, 21);
		frame.getContentPane().add(passwordField);
		
		JFormattedTextField login = new JFormattedTextField();
		login.setFont(new Font("Raleway", Font.PLAIN, 12));
		login.setBounds(179, 101, 186, 20);
		frame.getContentPane().add(login);
		
		JButton btnSignUp = new JButton("Sign Up");
		
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUp su = new SignUp(dbc);
				su.setVisible(true);
			}
		});
		
		btnSignUp.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSignUp.setBounds(163, 321, 111, 33);
		frame.getContentPane().add(btnSignUp);
		
		JButton btnForgotPassword = new JButton("Forgot Password");
		
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ForgotPassword fp = new ForgotPassword(dbc);
				fp.setVisible(true);
			}
		});
		
		btnForgotPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		btnForgotPassword.setBounds(231, 171, 134, 21);
		frame.getContentPane().add(btnForgotPassword);
		
		JLabel lblOr = new JLabel("or");
		lblOr.setForeground(Color.BLACK);
		lblOr.setFont(new Font("Arial", Font.PLAIN, 20));
		lblOr.setBounds(208, 283, 19, 21);
		frame.getContentPane().add(lblOr);
		
		JButton button = new JButton("Log-in");
		button.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {
				 new SwingWorker<Void, Void>() 
	                {
	                    @SuppressWarnings("deprecation")
						@Override
	                    protected Void doInBackground() throws Exception 
	                    {
	                    	boolean exists = false;
	                    	exists = dbc.runQueryLogIn(login.getText(), passwordField.getText());
	                    	
	    					if (exists) {
	    						frame.dispose();
	    						MainPage mainP = new MainPage(login.getText(), dbc); //
	    						mainP.setVisible(true);
	    					}else {
	    						JOptionPane.showMessageDialog(null, "Login or password is incorrect. Try again");
	    					}
	                        return null;
	                    }
	                }.execute();
			}
		});
		
		
		
		button.setFont(new Font("Arial", Font.PLAIN, 12));
		button.setBounds(163, 239, 111, 33);
		frame.getContentPane().add(button);
	}
}
