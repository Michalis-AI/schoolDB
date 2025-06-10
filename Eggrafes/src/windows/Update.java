package windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Update extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private ResultSet results;

	
	public Update() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainApp.searchW.setEnabled(true); // επανενεργοποίηση παραθύρου αναζήτησης με το κλείσιμο του τρέχοντος
			}
		});
		setTitle("Φόρμα Ενημέρωσης/Διαγραφής");
		setBounds(100, 100, 373, 343);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFill = new JLabel("Αποτελέσματα:");
		lblFill.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFill.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblFill, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(Color.PINK);
		panel.add(panelInfo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelInfo.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblID = new JLabel("ID:");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblID);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(txtID);
		txtID.setColumns(10);
		txtID.setEnabled(false);
		
		JLabel lblName = new JLabel("Όνομα:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblName);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Επίθετο:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblLastName);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(txtLastName);
		txtLastName.setColumns(10);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(Color.PINK);
		panelButtons.setBorder(new EmptyBorder(40, 40, 40, 40));
		panel.add(panelButtons);
		panelButtons.setLayout(new GridLayout(0, 1, 10, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelButtons.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 4, 5, 0));
		
		// κουμπί πλοήγησης first
		JButton btnFirst = new JButton("|<");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// SQLException handling
				try {
					results.first();
					showCurrentResult(); // εμφάνιση σχετικών αποτελεσμάτων
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnFirst.setForeground(Color.ORANGE);
		btnFirst.setBackground(SystemColor.textHighlight);
		btnFirst.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(btnFirst);
		
		// κουμπί πλοήγησης previous
		JButton btnPrevious = new JButton("<<");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// SQLException handling
				try {
					// αν δεν υπάρχει προηγούμενο αποτέλεσμα, μεταβαίνει στο τελευταίο λειτουργώντας κυκλοτερώς
					if(!results.previous())
						results.last();
					showCurrentResult(); // εμφάνιση σχετικών αποτελεσμάτων
				} catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnPrevious.setForeground(Color.ORANGE);
		btnPrevious.setBackground(SystemColor.textHighlight);
		btnPrevious.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(btnPrevious);
		
		// κουμπί πλοήγησης next
		JButton btnNext = new JButton(">>");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// SQLException handling
				try {
					// αν δεν υπάρχει επόμενο αποτέλεσμα, μεταβαίνει στο πρώτο λειτουργώντας κυκλοτερώς
					if(!results.next())
						results.first();
					showCurrentResult(); // εμφάνιση σχετικών αποτελεσμάτων
				} catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNext.setForeground(Color.ORANGE);
		btnNext.setBackground(SystemColor.textHighlight);
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(btnNext);
		
		// κουμπί πλοήγησης last
		JButton btnLast = new JButton(">|");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// SQLException handling
				try {
					results.last();
					showCurrentResult(); // εμφάνιση σχετικών αποτελεσμάτων
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLast.setForeground(Color.ORANGE);
		btnLast.setBackground(SystemColor.textHighlight);
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(btnLast);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.inactiveCaption);
		panel_3.setBorder(new EmptyBorder(5, 40, 5, 40));
		contentPane.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(0, 2, 30, 0));
		
		// κουμπί ενημέρωσης της βάσης
		JButton btnUpdate = new JButton("Ενημέρωση");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "UPDATE students SET FIRST_NAME = ?, LAST_NAME = ? WHERE ID = ?;"; // query ενημέρωσης της βάσης
				// SQLException handling
				try {
					String newFirstName = txtFirstName.getText();
					String newLastName = txtLastName.getText();
					PreparedStatement ps = Students.conn.prepareStatement(sql);
					ps.setString(1, newFirstName);
					ps.setString(2, newLastName);
					ps.setInt(3, Integer.parseInt(txtID.getText()));
					ps.executeUpdate();
					
					// έλεγχος κενού ονόματος και έξοδος από τη μέθοδο
					if (txtFirstName.getText().isBlank() || txtLastName.getText().isBlank()) {
						JOptionPane.showMessageDialog(contentPane, "Κενό όνομα ή επίθετο", "Σφάλμα", JOptionPane.ERROR_MESSAGE); // ενημέρωση σφάλματος
						return;
					}
					
					JOptionPane.showMessageDialog(contentPane, "Επιτυχής ενημέρωση!", "Πληροφορίες", JOptionPane.INFORMATION_MESSAGE); // ενημέρωση επιτυχίας
					ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "Ανεπιτυχής ενημέρωση!", "Σφάλμα", JOptionPane.ERROR_MESSAGE); // ενημέρωση σφάλματος
				}
			}
		});
		btnUpdate.setBackground(SystemColor.info);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(btnUpdate);
		
		// κουμπί διαγραφής από τη βάση
		JButton btnDelete = new JButton("Διαγραφή");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM students WHERE ID=?;"; // query διαγραφής από τη βάση
				// SQLException handling
				try {
					int answer = JOptionPane.showConfirmDialog(contentPane,  "Είστε σίγουρος ότι θέλετε να διαγράψετε;", "Διαγραφή", JOptionPane.OK_CANCEL_OPTION);
					if (answer != JOptionPane.OK_OPTION)
						return;
					PreparedStatement ps = Students.conn.prepareStatement(sql);
					ps.setInt(1,  results.getInt("ID"));
					ps.executeUpdate();
					ps.close();
					JOptionPane.showMessageDialog(contentPane, "Επιτυχής διαγραφή!", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
					
					MainApp.searchW.setEnabled(true); // επανενεργοποίηση παραθύρου αναζήτησης
					MainApp.updateW.setVisible(false); // κλείσιμο ορατότητας τρέχοντος παραθύρου
				} catch(SQLException ex) {
					JOptionPane.showMessageDialog(contentPane,  "Ανεπιτυχής διαγραφή!.", "Σφάλμα", JOptionPane.ERROR_MESSAGE); // ενημέρωση σφάλματος
				}
			}
		});
		btnDelete.setBackground(SystemColor.info);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(btnDelete);
	}
	
	// setter
	public void setResults(ResultSet rs) {
		this.results = rs;
		this.showCurrentResult();
	}
	
	// μέθοδος προβολής αποτελέσματος
	private void showCurrentResult() {
		try {
			// εισαγωγή στα σχετικά πεδία του παραθύρου τα στοιχεία του τρέχοντος αποτελέσματος
			this.txtID.setText(Integer.toString(this.results.getInt("ID")));
			this.txtFirstName.setText(results.getString("FIRST_NAME"));
			this.txtLastName.setText(results.getString("LAST_NAME"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
