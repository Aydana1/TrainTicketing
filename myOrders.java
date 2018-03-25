import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class myOrders extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, DatabaseClient dbc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myOrders frame = new myOrders(args[0], dbc);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void fillTable(String login, DatabaseClient dbc) {
        new SwingWorker<Void, Void>() 
        {
            @Override
            protected Void doInBackground() throws Exception 
            {
                dbc.runQueryLoadOrders(table, login);
                return null;
            }
        }.execute();
	}
	/**
	 * Create the frame.
	 */
	public myOrders(String login, DatabaseClient dbc) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 414);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMyOrders = new JLabel("My orders");
		lblMyOrders.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMyOrders.setBounds(38, 29, 134, 37);
		contentPane.add(lblMyOrders);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 819, 230);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		fillTable(login, dbc);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window w = SwingUtilities.getWindowAncestor(contentPane);
				w.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Arial", Font.PLAIN, 11));
		btnClose.setBounds(592, 334, 89, 23);
		contentPane.add(btnClose);
		
		JButton btnReturnTicket = new JButton("Return Selected Ticket");
		
		btnReturnTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "Please, select ticket to return");
				} else {
					TableModel model = table.getModel();
					String tickID = model.getValueAt(index, 0).toString();
					dbc.runQueryDeleteTicket(tickID);
					String docID = model.getValueAt(index, 3).toString();
					dbc.runQueryDeleteBooked(docID);
					String sn = model.getValueAt(index, 7).toString();
					String tn = model.getValueAt(index, 6).toString();
					dbc.runQueryUpdateSeat(sn, tn, "Available");
					String as = dbc.runQueryFindSeats(tn);
					dbc.runQuerUpdateTrain(tn, Integer.parseInt(as)+1);
					JOptionPane.showMessageDialog(null, "Ticket is succesfully returned");
					Window w = SwingUtilities.getWindowAncestor(contentPane);
					w.setVisible(false);
				}
			}
		});
		btnReturnTicket.setFont(new Font("Arial", Font.PLAIN, 11));
		btnReturnTicket.setBounds(38, 334, 165, 23);
		contentPane.add(btnReturnTicket);
	}
}
