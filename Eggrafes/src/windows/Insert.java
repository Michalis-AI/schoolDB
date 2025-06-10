package windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class Insert extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textEpitheto;
	private JTextField textOnoma;


	public Insert() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainApp.searchW.setEnabled(true); // επανενεργοποίηση παραθύρου αναζήτησης με το κλείσιμο του τρέχοντος
			}
		});
		setTitle("Φόρμα Εισαγωγής");
		setBounds(100, 100, 382, 236);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFill = new JLabel("Συμπληρώστε τα πεδία:");
		lblFill.setHorizontalAlignment(SwingConstants.LEFT);
		lblFill.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblFill, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBorder(new EmptyBorder(30, 30, 30, 30));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2, 0, 20));
		
		JLabel lblName = new JLabel("Όνομα:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblName);
		
		// πεδίο όπου ο χρήστης θα γράφει όνομα
		textOnoma = new JTextField();
		textOnoma.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(textOnoma);
		textOnoma.setColumns(10);
		
		JLabel lblLastName = new JLabel("Επίθετο:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblLastName);
		
		// πεδίο όπου ο χρήστης θα γράφει επίθετο
		textEpitheto = new JTextField();
		textEpitheto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(textEpitheto);
		textEpitheto.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		// κουμπί εισαγωγής νέας καταχώρησης
		JButton btnInsert = new JButton("Εισαγωγή");
		btnInsert.setBackground(SystemColor.info);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//// έλεγχος για κενό όνομα ή επίθετο, εμφάνιση σχετικού μηνύματος και έξοδος από τη μέθοδο ώστε να επιστρέψουμε στην αρχική κατάσταση
				if (textOnoma.getText().isBlank() || textEpitheto.getText().isBlank()) {
					JOptionPane.showMessageDialog(contentPane, "Κενό όνομα ή επίθετο", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String sql = "INSERT INTO students(FIRST_NAME, LAST_NAME) VALUES(?, ?);"; /// query εισαγωγής καταχώρησης στη βάση δεδομένων
				// SQLException handling
				try {
					PreparedStatement ps = Students.conn.prepareStatement(sql);
					ps.setString(1, textOnoma.getText()); // λήψη ονόματος από το σχετικό πεδίο του χρήστη
					ps.setString(2, textEpitheto.getText()); // λήψη επιθέτου από το σχετικό πεδίο του χρήστη
					ps.executeUpdate();
					JOptionPane.showMessageDialog(contentPane, "Επιτυχής καταχώρηση!", "Πληροφορίες", JOptionPane.INFORMATION_MESSAGE); // ενημέρωση επιτυχίας
					ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "Ανεπιτυχής καταχώρηση!", "Σφάλμα", JOptionPane.ERROR_MESSAGE); // ενημέρωση σφάλματος
				}
				
			}
		});
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(btnInsert);
	}

}
