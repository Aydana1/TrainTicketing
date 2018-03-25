

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

public class ForgotPassword extends JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(DatabaseClient dbc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword(dbc);
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
	public ForgotPassword(DatabaseClient dbc) {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 394);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblJsd = new JLabel("Username");
		lblJsd.setFont(new Font("Arial", Font.PLAIN, 12));
		lblJsd.setBounds(38, 52, 110, 22);
		contentPane.add(lblJsd);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setBounds(28, 74, 219, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblSecurityQuestion = new JLabel("Security question:");
		lblSecurityQuestion.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSecurityQuestion.setBounds(38, 126, 466, 22);
		contentPane.add(lblSecurityQuestion);
		lblSecurityQuestion.setVisible(false);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(28, 153, 219, 40);
		contentPane.add(textField_2);
		textField_2.setVisible(false);
		
		JLabel lblPleaseCreateA = new JLabel("To create a new password answer the security question below, please.");
		lblPleaseCreateA.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPleaseCreateA.setBounds(28, 21, 508, 22);
		contentPane.add(lblPleaseCreateA);
		
		JLabel label = new JLabel("New Password");
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setBounds(38, 205, 110, 22);
		contentPane.add(label);
		label.setVisible(false);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		lblConfirmPassword.setBounds(300, 205, 158, 22);
		contentPane.add(lblConfirmPassword);
		lblConfirmPassword.setVisible(false);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBack.setBounds(81, 301, 117, 29);
		contentPane.add(btnBack);
		
		JButton btnSave = new JButton("Submit");
		btnSave.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSave.setBounds(345, 301, 117, 29);
		contentPane.add(btnSave);
		btnSave.setVisible(false);
		
		JButton btnSubmit = new JButton("Next");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSubmit.setBounds(275, 81, 117, 29);
		contentPane.add(btnSubmit);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(28, 231, 219, 39);
		contentPane.add(passwordField);
		passwordField.setVisible(false);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(290, 230, 214, 40);
		contentPane.add(passwordField_1);
		passwordField_1.setVisible(false);

btnSubmit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 new SwingWorker<Void, Void>() 
	                {
	                    @Override
	                    protected Void doInBackground() throws Exception 
	                    {
  	
		                    if(textField.getText().equals("")) {
	                    		JOptionPane.showMessageDialog(null, "Write your login first and then click Next");
		                    } else {
		                    	boolean usernameExists = false;
		                    	usernameExists = dbc.runQueryUsername(textField.getText());
		           
		                    	if(usernameExists) {
	
		                    		String securityQ = dbc.runQuerySecurtiyQuestion(textField.getText());
		                    		
		                    		lblSecurityQuestion.setText(securityQ);
		                    		
		                    		lblSecurityQuestion.setVisible(true);
		                    		textField_2.setVisible(true);
		                    		label.setVisible(true);
		                    		lblConfirmPassword.setVisible(true);
		                    		btnSave.setVisible(true);
		                    		passwordField.setVisible(true);
		                    		passwordField_1.setVisible(true);
	
		                    	} else {
		                      		JOptionPane.showMessageDialog(null, "Username is wrong. Please, try again");	
		                      	}
		                    }
		                    		return null;
		      
	                    }
	                }.execute();
			};
	});
		
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 new SwingWorker<Void, Void>() 
	                {
	                    @SuppressWarnings("deprecation")
						@Override
	                    protected Void doInBackground() throws Exception 
	                    {
	     
	                       if (textField_2.getText().equals("") || passwordField.getText().equals("") || textField.getText().equals("") || passwordField.getText().equals("")) {
	                      			JOptionPane.showMessageDialog (null, "There are empty fields. Please, fill all of the forms");
	                      	} else {
	                    		boolean answerExists = false;
	                    		answerExists = dbc.runQuerySecurityAnswer(textField_2.getText());
	                    		
		                      	if(answerExists) {
		 	                       // If password fields match update the password
		 	                       if(passwordField.getText().equals(passwordField_1.getText())) {
		 	                    	   
		 	                    	   		dbc.runQueryChangePassword(textField.getText() , passwordField.getText());
		 		    						JOptionPane.showMessageDialog(null, "Password is succesfully changed");
		 		                      		// go back to the login form
				 	                       	Window w = SwingUtilities.getWindowAncestor(contentPane);
				   							w.setVisible(false);

		 	                       } else {
		 	                    	   		JOptionPane.showMessageDialog(null, "Passwords do not match");
		 	                       }
		 	             		
		                      	} else {
		                      		JOptionPane.showMessageDialog(null, "Security answer is wrong. Please, try again");	   
		                      	} 
	                      	}
	                        return null;
	                    }
	                }.execute();
			};
	});

btnBack.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 new SwingWorker<Void, Void>() 
	                {
	                    @Override
	                    protected Void doInBackground() throws Exception 
	                    {
	                    		btnBack.setEnabled(true);
   	
							Window w = SwingUtilities.getWindowAncestor(contentPane);
	    						w.setVisible(false);
	    						
	    						// then go back to the login form
	    	  
	                    		return null;
	                    }
	                }.execute();
			};
	});
}	
}
