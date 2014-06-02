package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;

public class GUI {

	private JFrame frame;

    JPanel User=new Users();
    JPanel usersatt=new UsersAtt();
    JPanel HR=new HealthRecords();
    JPanel att=new Attributes();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
	
	
		tabbedPane.addTab("Userattributes", null, usersatt, null);
		tabbedPane.addTab("user", null, User, null);
		tabbedPane.addTab("Healthrec", null, HR, null);
		tabbedPane.addTab("attributes", null, att, null);

	}

	
}
