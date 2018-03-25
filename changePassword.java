import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class changePassword extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,DatabaseClient dbc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					changePassword frame = new changePassword(args[0], dbc);
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
	public changePassword(String login, DatabaseClient dbc) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 320);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("e-mail:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(227, 80, 50, 14);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel(login);
		label.setFont(new Font("Arial", Font.PLAIN, 16));
		label.setBounds(322, 80, 327, 14);
		contentPane.add(label);
		
		JLabel lblOldPassword = new JLabel("old password:");
		lblOldPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblOldPassword.setBounds(180, 105, 97, 26);
		contentPane.add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("new password:");
		lblNewPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewPassword.setBounds(172, 142, 105, 26);
		contentPane.add(lblNewPassword);
		
		JLabel lblConfirmNewPassword = new JLabel("confirm new password:");
		lblConfirmNewPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblConfirmNewPassword.setBounds(118, 179, 159, 26);
		contentPane.add(lblConfirmNewPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(322, 105, 201, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(322, 147, 201, 20);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(322, 184, 201, 20);
		contentPane.add(passwordField_2);
		
		JLabel lblChangeYourPassword = new JLabel("Change your password");
		lblChangeYourPassword.setFont(new Font("Arial", Font.PLAIN, 20));
		lblChangeYourPassword.setBounds(228, 21, 234, 26);
		contentPane.add(lblChangeYourPassword);
		
		JButton btnNewButton = new JButton("Submit");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 new SwingWorker<Void, Void>() 
	                {
	                    @SuppressWarnings("deprecation")
						@Override
	                    protected Void doInBackground() throws Exception 
	                    {
	                    	boolean exists = false;
	                    	exists = dbc.runQueryLogIn(login, passwordField.getText());
	                    	
	    					if (exists) {
	    						
	    						if (passwordField_1.getText().equals(passwordField_2.getText()) && !passwordField_1.getText().equals("")) {
	    							//update statement
		    						dbc.runQueryChangePassword(login, passwordField_1.getText());
		    						JOptionPane.showMessageDialog(null, "Password is succesfully changed");
		    						Window w = SwingUtilities.getWindowAncestor(contentPane);
		    						w.setVisible(false);
	    						}else {
	    							JOptionPane.showMessageDialog(null, "Password doesn't match confirmation or fields are empty");
	    						}
	    						
	    						
	    					}else {
	    						JOptionPane.showMessageDialog(null, "Old password is incorrect. Try again");
	    					}
	                        return null;
	                    }
	                }.execute();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNewButton.setBounds(286, 233, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Close");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window w = SwingUtilities.getWindowAncestor(contentPane);
				w.setVisible(false);
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 13));
		btnBack.setBounds(385, 234, 89, 23);
		contentPane.add(btnBack);
	}
}
