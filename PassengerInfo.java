
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class PassengerInfo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String login, String tn, String from, String to, String tt, String dt, String at, String as, DatabaseClient dbc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassengerInfo frame = new PassengerInfo(login, tn, from, to, tt, dt, at, as, dbc);
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
	public PassengerInfo(String login, String tn, String from, String to, String tt, String dt, String at, String as, DatabaseClient dbc) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 487);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseInsertPassenger = new JLabel("Please, insert Passenger Information");
		lblPleaseInsertPassenger.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPleaseInsertPassenger.setBounds(46, 30, 364, 24);
		contentPane.add(lblPleaseInsertPassenger);
		
		JLabel lblDocuementId = new JLabel("Docuement ID*");
		lblDocuementId.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDocuementId.setBounds(46, 115, 115, 14);
		contentPane.add(lblDocuementId);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		formattedTextField.setBounds(171, 114, 226, 20);
		contentPane.add(formattedTextField);
		
		JLabel lblFirstName = new JLabel("First Name*");
		lblFirstName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFirstName.setBounds(46, 146, 115, 14);
		contentPane.add(lblFirstName);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setFont(new Font("Arial", Font.PLAIN, 12));
		formattedTextField_1.setBounds(171, 145, 226, 20);
		contentPane.add(formattedTextField_1);
		
		JLabel lblLastName = new JLabel("Last Name*");
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLastName.setBounds(46, 177, 115, 14);
		contentPane.add(lblLastName);
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setFont(new Font("Arial", Font.PLAIN, 12));
		formattedTextField_2.setBounds(171, 176, 226, 20);
		contentPane.add(formattedTextField_2);
		
		JLabel lblAvailableSeats = new JLabel("Available seats:");
		lblAvailableSeats.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAvailableSeats.setBounds(46, 226, 115, 14);
		contentPane.add(lblAvailableSeats);
		
		JButton btnNext = new JButton("Book");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (formattedTextField.getText().equals("") ||  formattedTextField_1.getText().equals("") || formattedTextField_2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please, fill out all fields");
				} else {
					if (!formattedTextField.getText().matches("[0-9]+")) {
						JOptionPane.showMessageDialog(null, "Document ID should consists of digits only");
					} else {
						String ass= as;
						int index = table.getSelectedRow();
	
						if(index == -1) {
							JOptionPane.showMessageDialog(null, "Please, select seat");
						} else {
							TableModel model = table.getModel();
							String sn = model.getValueAt(index, 0).toString();
							String st = model.getValueAt(index, 1).toString();
							String p = model.getValueAt(index, 2).toString();
							boolean booked = false;
							try {
								booked = dbc.runQueryInsertPassenger(formattedTextField.getText(), formattedTextField_1.getText(), formattedTextField_2.getText(), sn, tn);
								dbc.runQueryUpdateSeat(sn, tn, "Not Available");
								dbc.runQuerUpdateTrain(tn, Integer.parseInt(as)-1);
								int temp = Integer.parseInt(as)-1;
								ass = Integer.toString(temp);
							}catch (Exception e) {
								e.printStackTrace();
							}
							
							if(booked) {
								JOptionPane.showMessageDialog(null, "Ticket is succesfully booked");
								Window w = SwingUtilities.getWindowAncestor(contentPane);
								w.setVisible(false);
								
								Ticket ticket = new Ticket(login, formattedTextField.getText(), formattedTextField_1.getText(), formattedTextField_2.getText(), sn, tn, from, to, tt, dt, at, p, ass, st, dbc);
								ticket.setVisible(true);
							} else {
								
								JOptionPane.showMessageDialog(null, "The system experiences techinical issues. Please, try again later");
								
							}
						}
					}
				}
			}
		});
		btnNext.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNext.setBounds(369, 336, 89, 23);
		contentPane.add(btnNext);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 272, 242, 120);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setVisible(false);
		try {
			dbc.runQueryLoadSeats(table, tn);
			
			JButton btnClose = new JButton("Close");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Window w = SwingUtilities.getWindowAncestor(contentPane);
					w.setVisible(false);
				}
			});
			btnClose.setFont(new Font("Arial", Font.PLAIN, 12));
			btnClose.setBounds(369, 369, 89, 23);
			contentPane.add(btnClose);
			
			JLabel lblrequiredFields = new JLabel("*required fields");
			lblrequiredFields.setForeground(Color.GRAY);
			lblrequiredFields.setFont(new Font("Arial", Font.PLAIN, 12));
			lblrequiredFields.setBounds(313, 207, 135, 14);
			contentPane.add(lblrequiredFields);
			
			JLabel lblPleaseSelectOne = new JLabel("Please, select one seat*");
			lblPleaseSelectOne.setFont(new Font("Arial", Font.PLAIN, 12));
			lblPleaseSelectOne.setBounds(46, 247, 160, 14);
			contentPane.add(lblPleaseSelectOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
		table.setVisible(true);
		
		
		
	}
}
