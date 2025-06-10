package windows;

import java.awt.EventQueue;

public class MainApp {

	// μία μεταβλητή για κάθε παράθυρο
	public static Students studentW; //αρχικό
	public static Search searchW; // αναζήτησης
	public static Insert insertW; // εισαγωγής
	public static Update updateW; //ενημέρωσης / διαγραφής
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					// 3 βήματα για κάθε παράθυρο
					studentW = new Students(); // δημιουργία αντικειμένου
					studentW.setLocation(100, 100); // επιλογή θέσης χειροκίνητα
					studentW.setVisible(true); // ορατό το πρώτο παράθυρο
					
					searchW = new Search(); // δημιουργία αντικειμένου
					searchW.setLocation(150, 150); // επιλογή θέσης χειροκίνητα
					searchW.setVisible(false); // μη ορατό παράθυρο
					
					insertW = new Insert(); // δημιουργία αντικειμένου
					insertW.setLocation(200, 200); // επιλογή θέσης χειροκίνητα
					insertW.setVisible(false); // μη ορατό παράθυρο
					
					updateW = new Update(); // δημιουργία αντικειμένου
					updateW.setLocation(200, 200); // επιλογή θέσης χειροκίνητα
					updateW.setVisible(false); // μη ορατό παράθυρο
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
