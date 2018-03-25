import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;


import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.awt.Color;


import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

public class MainPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxStation;
	private JComboBox<String> comboBoxStation2;
	private final JTable table;
	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String args, DatabaseClient argc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage(args, argc);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void fillComboBox(DatabaseClient dbc)
	{
		try {
			new SwingWorker<Void, Void>() 
            {
                @Override
                protected Void doInBackground() throws Exception 
                {
                	dbc.runQueryListStations(comboBoxStation);
                	dbc.runQueryListStations(comboBoxStation2);
                    return null;
                }
            }.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Create the frame.
	 */
	public MainPage(String login, DatabaseClient dbc) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 697);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Raleway", Font.PLAIN, 14));
		lblUsername.setBounds(15, 11, 86, 14);
		contentPane.add(lblUsername);
		
		JLabel label = new JLabel(login);
		label.setForeground(new Color(255, 0, 102));
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setBounds(96, 11, 240, 14);
		contentPane.add(label);
		
		JButton btnChangePassword = new JButton("Change Password");
		
		
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassword cp = new changePassword(login, dbc);
				cp.setVisible(true);
				
			}
		});
		
		
		
		btnChangePassword.setFont(new Font("Arial", Font.PLAIN, 11));
		btnChangePassword.setBounds(589, 8, 133, 23);
		contentPane.add(btnChangePassword);
		
		JButton btnAbout = new JButton("About");
		
		
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "This Java project was done by Aidana N. and Bakhtiayr O., 2017");
			}
		});
		
		
	
		btnAbout.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAbout.setBounds(490, 8, 89, 23);
		contentPane.add(btnAbout);
		
		JButton btnLogout = new JButton("Log-Out");
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Window w = SwingUtilities.getWindowAncestor(contentPane);
							w.setVisible(false);
							LoginPage window = new LoginPage();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				Window w = SwingUtilities.getWindowAncestor(contentPane);
				w.setVisible(false);
			}
		});
		
		
		
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 11));
		btnLogout.setBounds(732, 8, 89, 23);
		contentPane.add(btnLogout);
		
		JButton btnMyTickets = new JButton("My Orders");
		
		
		
		btnMyTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myOrders mo = new myOrders(login, dbc);
				mo.setVisible(true);
			}
		});
		
		
		
		btnMyTickets.setFont(new Font("Arial", Font.PLAIN, 12));
		btnMyTickets.setBounds(380, 8, 100, 23);
		contentPane.add(btnMyTickets);
	
		comboBoxStation = new JComboBox<String>();
		comboBoxStation.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxStation.setBounds(106, 148, 166, 20);
		contentPane.add(comboBoxStation);
		
		comboBoxStation2 = new JComboBox<String>();
		comboBoxStation2.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxStation2.setBounds(332, 148, 166, 20);
		contentPane.add(comboBoxStation2);
		
		fillComboBox(dbc);
		
		comboBoxStation.setSelectedItem(null);
		comboBoxStation2.setSelectedItem(null);
		
		final JLabel lblNewLabel_1 = new JLabel();
		final JLabel label_1 = new JLabel();
		final JLabel label_2 = new JLabel();
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		dateChooser.setBounds(561, 148, 95, 20);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		contentPane.add(dateChooser);
		
		
		JButton btnFind = new JButton("Find");
		btnFind.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String departureStation = (String) comboBoxStation.getSelectedItem();
				String arrivalStation = (String) comboBoxStation2.getSelectedItem();
				String date  = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				
				lblNewLabel_1.setText(departureStation);
				label_1.setText(arrivalStation);
				label_2.setText(date);
				
                new SwingWorker<Void, Void>() 
                {
                    @Override
                    protected Void doInBackground() throws Exception 
                    {
                    	btnFind.setEnabled(false);
                        dbc.runQueryLoadTrains(table, departureStation, arrivalStation, date);
                        btnFind.setEnabled(true);
       
                        return null;
                    }
                }.execute();
			}
		});
		
		
		
		btnFind.setBounds(711, 147, 89, 23);
		contentPane.add(btnFind);
		
		JLabel lblNewLabel = new JLabel("Departure");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(106, 124, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblArrival = new JLabel("Arrival");
		lblArrival.setFont(new Font("Arial", Font.PLAIN, 12));
		lblArrival.setBounds(332, 126, 86, 14);
		contentPane.add(lblArrival);
		
		JLabel lblRequestInfo = new JLabel("Request info:");
		lblRequestInfo.setFont(new Font("Arial", Font.BOLD, 12));
		lblRequestInfo.setBounds(106, 219, 109, 23);
		contentPane.add(lblRequestInfo);
		
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFrom.setBounds(106, 253, 43, 14);
		contentPane.add(lblFrom);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTo.setBounds(106, 278, 26, 14);
		contentPane.add(lblTo);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDate.setBounds(106, 302, 34, 14);
		contentPane.add(lblDate);
		
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(159, 253, 75, 14);
		contentPane.add(lblNewLabel_1);
		
		label_1.setFont(new Font("Arial", Font.PLAIN, 12));
		label_1.setBounds(159, 279, 75, 14);
		contentPane.add(label_1);
		
		label_2.setFont(new Font("Arial", Font.PLAIN, 12));
		label_2.setBounds(159, 303, 75, 14);
		contentPane.add(label_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(78, 346, 817, 219);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		JButton btnBuyTicket = new JButton("Book Ticket");
		
		btnBuyTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "Please, select train");
				} else {
					TableModel model = table.getModel();
					String tn = model.getValueAt(index, 0).toString();
					String from = model.getValueAt(index, 1).toString();
					String to = model.getValueAt(index, 2).toString();
					String tt = model.getValueAt(index, 3).toString();
					String dt = model.getValueAt(index, 4).toString();
					String at = model.getValueAt(index, 5).toString();
					String as = model.getValueAt(index, 6).toString();
					
					PassengerInfo pp = new PassengerInfo(login, tn, from, to, tt, dt, at, as, dbc);
					pp.setVisible(true);
				}
			}
		});
		
		
		btnBuyTicket.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBuyTicket.setBounds(106, 593, 109, 23);
		contentPane.add(btnBuyTicket);
		
		JButton btnExit = new JButton("Exit");
		
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window w = SwingUtilities.getWindowAncestor(contentPane);
				w.setVisible(false);
			}
		});
		
		
		btnExit.setFont(new Font("Arial", Font.PLAIN, 11));
		btnExit.setBounds(831, 8, 89, 23);
		contentPane.add(btnExit);
	
		
		
	}
}
