
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JPasswordField;

public class SignUp extends JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_6;
	private JTextField textField_7;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(DatabaseClient dbc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp(dbc);
					frame.setUndecorated(true);
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
	public SignUp(DatabaseClient dbc) {
		
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 446);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblPassword = new JLabel("Phone number");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblSecondName = new JLabel("Second Name");
		lblSecondName.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_3.setColumns(10);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_6.setColumns(10);
		
		JLabel lblSecurityQuestion = new JLabel("Security question");
		lblSecurityQuestion.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblSecurityAnswer = new JLabel("Security answer");
		lblSecurityAnswer.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_7.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField_1 = new JPasswordField();
		
		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 new SwingWorker<Void, Void>() 
	                {
	                    @SuppressWarnings("deprecation")
						@Override
	                    protected Void doInBackground() throws Exception 
	                    {
	                        	                        
	                        boolean exists = false;
	                        exists = dbc.runQueryEmail(textField.getText()); 
	                        
	                        //String email = dbc.runQueryEmailCheck(textField.getText());  // returned as String 
	                        	                      		
	                       if (textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals("") || 
	                    		   textField_3.getText().equals("") || passwordField.getText().equals("") || textField_6.getText().equals("") 
	                    		   || textField_7.getText().equals("")) 
	                       {
	                      	  JOptionPane.showMessageDialog(null, "There are empty fields. Please, fill all of the forms.");
	                      	} else if(exists) {
	                      		JOptionPane.showMessageDialog(null, "This username already exists.");
	                      	} else if(!passwordField.getText().equals(passwordField_1.getText())){
	                      		JOptionPane.showMessageDialog(null, "Password doesn't match confirmation");
	                      	} else if(!textField_3.getText().matches("[0-9]+")){
	                      		JOptionPane.showMessageDialog(null, "Phone number should consists of digits only");
	                      	}else
	                      	{
	                    		dbc.runQuerySignUp( textField.getText(), textField_1.getText(), textField_2.getText(), 
	                    				textField_3.getText(), passwordField.getText(), textField_6.getText(), textField_7.getText());
	                    		JOptionPane.showMessageDialog(null, "Account is succesfully created. Please, log-in.");
	    						Window w = SwingUtilities.getWindowAncestor(contentPane);
	    						w.setVisible(false);
	                      	}
	                        return null;
	                       }
	                }.execute();
			}
				
		});
		
		JLabel lblPleaseCompleteThe = new JLabel("Please, complete the form below in order to register. ");
		lblPleaseCompleteThe.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Window w = SwingUtilities.getWindowAncestor(contentPane);
				w.setVisible(false);
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 11));
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(154)
					.addComponent(lblPleaseCompleteThe)
					.addContainerGap(158, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(236)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
							.addComponent(btnBack))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSecondName, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(passwordField, 272, 272, 272)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblSecurityQuestion, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPassword_1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblConfirmPassword, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
										.addComponent(lblSecurityAnswer, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)))
								.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))))
					.addGap(53))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(lblPleaseCompleteThe)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPassword_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblConfirmPassword)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSecurityQuestion)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(lblSecurityAnswer)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblUsername)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFirstName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSecondName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(lblPassword)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBack))
					.addGap(31))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
