package windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.SystemColor;

public class Students extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static Connection conn; // μεταβλητή για τη σύνδεση με τη βάση δεδομένων


	public Students() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String url = "jdbc:mysql://localhost:3306/schooldb"; // διαδρομή για τη βάση δεδομένων
				// SQLException handling
				try {
					conn = DriverManager.getConnection(url, "user1", "1234"); // επιχείρηση σύνδεσης
					JOptionPane.showMessageDialog(contentPane, "Επιτυχής σύνδεση!", "Πληροφορίες", JOptionPane.PLAIN_MESSAGE); // μήνυμα επιτυχίας
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "Αποτυχία σύνδεσης!", "Σφάλμα", JOptionPane.ERROR_MESSAGE); // μήνυμα αποτυχίας
				}
			}
		});
		setTitle("Αρχικό Παράθυρο");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 253);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblStart = new JLabel("Διαχείριση μαθητών:");
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblStart, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new EmptyBorder(30, 30, 30, 30));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 30));
		
		// κουμπί για τη διαχείριση της εφαρμογής
		JButton btnStudents = new JButton("Μαθητές");
		btnStudents.setBackground(SystemColor.info);
		btnStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainApp.searchW.setVisible(true); // ορατότητα παραθύρου αναζήτησης
				MainApp.studentW.setEnabled(false); // απενεργοποίηση τρέχοντος παραθύρου
			}
		});
		btnStudents.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnStudents);
		
		// κουμπί έκδοσης
		JButton btnVersion = new JButton("Έκδοση");
		btnVersion.setBackground(SystemColor.info);
		btnVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contentPane, "0.1.0", "Έκδοση", JOptionPane.INFORMATION_MESSAGE); // πληροφορία έκδοσης
			}
		});
		btnVersion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnVersion);
	}

}
