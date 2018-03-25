
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import javax.swing.SwingUtilities;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ticket extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String tn, String from, String to, String tt, String dt, String at, String p, String as, String st, DatabaseClient dbc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ticket frame = new Ticket(args[0], args[1], args[2], args[3], args[4], tn, from, to, tt, dt, at, p, as, st, dbc);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int fillTicketNum(DatabaseClient dbc)
	{
		int TicketNum = 0;
		boolean exists = false;
		try {
			while (true) {
				TicketNum +=1;
				exists = dbc.runQueryTicketNum(TicketNum);
              if (!exists) {
            	  break;
              }
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return TicketNum;
	}

	/**
	 * Create the frame.
	 */
	public Ticket(String login, String docID, String Fname, String Lname, String seatNo, String tn, String from, String to, String tt, String dt, String at, String p, String as, String st, DatabaseClient dbc) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 770);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTicket = new JLabel("Ticket #");
		lblTicket.setBounds(183, 50, 83, 24);
		contentPane.add(lblTicket);
		lblTicket.setFont(new Font("Arial", Font.PLAIN, 20));

		int ticketId = fillTicketNum(dbc);
		JLabel tickNo = new JLabel(Integer.toString(ticketId));
		tickNo.setBounds(276, 50, 83, 24);
		tickNo.setFont(new Font("Arial", Font.PLAIN, 20));
		contentPane.add(tickNo);
		
		JLabel lblPassengerDetails = new JLabel("Passenger Details:");
		lblPassengerDetails.setBounds(28, 95, 176, 24);
		lblPassengerDetails.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(lblPassengerDetails);
		
		JLabel lblDocumentId = new JLabel("Document ID");
		lblDocumentId.setBounds(82, 130, 106, 24);
		lblDocumentId.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(lblDocumentId);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(82, 165, 106, 24);
		lblFirstName.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(82, 200, 106, 24);
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(lblLastName);
		
		JLabel lblTrainDetails = new JLabel("Train Details:");
		lblTrainDetails.setBounds(28, 243, 176, 24);
		lblTrainDetails.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(lblTrainDetails);
		
		JLabel lblTrainNo = new JLabel("Train No.");
		lblTrainNo.setBounds(82, 278, 106, 24);
		lblTrainNo.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(lblTrainNo);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(82, 313, 106, 24);
		lblType.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(lblType);
		
		JLabel lblDepartureFrom = new JLabel("From");
		lblDepartureFrom.setBounds(82, 348, 106, 24);
		lblDepartureFrom.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(lblDepartureFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(82, 383, 106, 24);
		lblTo.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(lblTo);
		
		JLabel lblSeatNo = new JLabel("Seat No.");
		lblSeatNo.setBounds(82, 418, 106, 24);
		lblSeatNo.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(lblSeatNo);
		
		JLabel lblOrderDetails = new JLabel("Order Details:");
		lblOrderDetails.setBounds(28, 492, 176, 24);
		lblOrderDetails.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(lblOrderDetails);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(82, 527, 106, 24);
		lblPrice.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(lblPrice);
		
		JLabel lblPurchaseDate = new JLabel("Purchase date");
		lblPurchaseDate.setBounds(82, 562, 122, 24);
		lblPurchaseDate.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(lblPurchaseDate);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(82, 597, 106, 24);
		lblUser.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(lblUser);
		
		JLabel lblAt = new JLabel("at");
		lblAt.setBounds(308, 348, 31, 24);
		lblAt.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(lblAt);
		
		JLabel lblAt_1 = new JLabel("at");
		lblAt_1.setBounds(308, 383, 31, 24);
		lblAt_1.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(lblAt_1);
		
		JLabel doc = new JLabel(docID);
		doc.setBounds(214, 130, 289, 24);
		doc.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(doc);
		
		JLabel label = new JLabel(Fname);
		label.setBounds(214, 165, 289, 24);
		label.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(label);
		
		JLabel label_1 = new JLabel(Lname);
		label_1.setBounds(214, 200, 289, 24);
		label_1.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel(login);
		label_2.setBounds(214, 597, 289, 24);
		label_2.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(label_2);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		JLabel label_3 = new JLabel(dtf.format(now));
		label_3.setBounds(214, 562, 232, 24);
		label_3.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel(seatNo);
		label_4.setBounds(188, 418, 122, 24);
		label_4.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(label_4);
		
		JButton btnClose = new JButton("Cancell booking");
		
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dbc.runQueryDeleteBooked(docID);
					dbc.runQueryUpdateSeat(seatNo, tn, "Available");
					dbc.runQuerUpdateTrain(tn, Integer.parseInt(as)+1);
				}catch (Exception e1) {
					e1.printStackTrace();
				}
				Window w = SwingUtilities.getWindowAncestor(contentPane);
				w.setVisible(false);
				JOptionPane.showMessageDialog(null, "Your booking was cancelled");
			}
		});
		btnClose.setBounds(210, 693, 149, 23);
		btnClose.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPane.add(btnClose);
		
		JLabel label_5 = new JLabel(tn);
		label_5.setBounds(183, 278, 156, 24);
		label_5.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel(tt);
		label_6.setFont(new Font("Arial", Font.PLAIN, 16));
		label_6.setBounds(184, 314, 191, 24);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel(from);
		label_7.setFont(new Font("Arial", Font.PLAIN, 16));
		label_7.setBounds(183, 347, 106, 24);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel(to);
		label_8.setFont(new Font("Arial", Font.PLAIN, 16));
		label_8.setBounds(183, 383, 106, 24);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel(dt);
		label_9.setFont(new Font("Arial", Font.PLAIN, 16));
		label_9.setBounds(345, 348, 216, 24);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel(at);
		label_10.setFont(new Font("Arial", Font.PLAIN, 16));
		label_10.setBounds(349, 383, 212, 24);
		contentPane.add(label_10);
		
		JLabel label_11 = new JLabel(p);
		label_11.setFont(new Font("Arial", Font.PLAIN, 16));
		label_11.setBounds(214, 527, 183, 24);
		contentPane.add(label_11);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBounds(237, 649, 95, 33);
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean booked = false;
				try {
					booked = dbc.runQueryBook(ticketId, label_3.getText(), login, docID);
				}catch (Exception e1) {
					e1.printStackTrace();
				}
				
				if(booked) {
					JOptionPane.showMessageDialog(null, "Ticket is succesfully bought");
					Window w = SwingUtilities.getWindowAncestor(contentPane);
					w.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "The system experiences techinical issues. Please, try again later");
				
				}
			}
		});
		btnBuy.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(btnBuy);
		
		JLabel lblSeatType = new JLabel("Seat Type");
		lblSeatType.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSeatType.setBounds(82, 457, 106, 24);
		contentPane.add(lblSeatType);
		
		JLabel label_12 = new JLabel(st);
		label_12.setFont(new Font("Arial", Font.PLAIN, 16));
		label_12.setBounds(183, 457, 106, 24);
		contentPane.add(label_12);
	}
}
