package Janelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class Principal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
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
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 983, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 47, 903, 318);
		frame.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Abrir Arquivo");
		btnNewButton.setBackground(new Color(51, 204, 153));
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnNewButton.setBounds(348, 471, 131, 34);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Abrir Tabela");
		btnNewButton_1.setBackground(new Color(51, 204, 153));
		btnNewButton_1.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnNewButton_1.setBounds(503, 471, 123, 34);
		frame.getContentPane().add(btnNewButton_1);
	}
}
