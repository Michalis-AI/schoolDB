package windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Search extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textSearch;


	public Search() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainApp.studentW.setEnabled(true); // επανενεργοποίηση αρχικού παραθύρου αν κλείσει το παρόν
			}
		});
		setTitle("Φόρμα Αναζήτησης");
		setBounds(100, 100, 349, 282);
		contentPane = new JPanel(); 
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblType = new JLabel("Επώνυμο:");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblType, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(); 
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new EmptyBorder(30, 50, 30, 50));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 1, 0, 20));
		
		// δημιουργία πεδίου όπου ο χρήστης θα γράφει επίθετο
		textSearch = new JTextField();
		panel.add(textSearch);
		textSearch.setColumns(10);
		
		// κουμπί αναζήτησης
		JButton btnSearch = new JButton("Αναζήτηση");
		btnSearch.setBackground(SystemColor.info);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// SQLException handling
				try {
				    String sql = "SELECT * FROM students WHERE LAST_NAME LIKE ?;"; // query αναζήτησης/select από τη βάση δεδομένων
				    PreparedStatement ps = Students.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				    ps.setString(1, '%' + textSearch.getText() + '%');
				    ResultSet results = ps.executeQuery();
				    
				    // έλεγχος αν υπάρχει πρώτο αποτέλεσμα αναζήτησης. Αν όχι, σημαίνει ότι δεν υπάρχει αποτέλεσμα γενικά για τη συγκεκριμένη αναζήτηση
				    if (!results.first()) {
				        JOptionPane.showMessageDialog(contentPane,  "Δεν βρέθηκαν αποτελέσματα", "Ενημέρωση", JOptionPane.INFORMATION_MESSAGE);
				        return;
				    }
				    
				    MainApp.updateW.setResults(results);
				    MainApp.updateW.setVisible(true); // ορατότητα παραθύρου ενημέρωσης
					MainApp.searchW.setEnabled(false); // απενεργοποίηση τρέχοντος παραθύρου
				    
				} catch (SQLException e1) {
				    // Handle the exception
				    e1.printStackTrace(); 
				    JOptionPane.showMessageDialog(contentPane, "Πρόβλημα ολοκλήρωσης αναζήτησης", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
				}
				 
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(btnSearch);
		
		// κουμπί εισαγωγής
		JButton btnInsert = new JButton("Εισαγωγή"); 
		btnInsert.setBackground(SystemColor.info);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainApp.insertW.setVisible(true); // ορατότητα παραθύρου ειαγωγής
				MainApp.searchW.setEnabled(false); // απενεργοποίηση τρέχοντος παραθύρου
			}
		});
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(btnInsert);
	}

}
