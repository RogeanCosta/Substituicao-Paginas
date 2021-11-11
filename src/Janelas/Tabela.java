package Janelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;

public class Tabela {

	private JFrame frame;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabela window = new Tabela();
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
	public Tabela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 834, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String[] nomeColunas = {"Quantidade de Frames", "FIFO", "SEGUNDA CHANCE", "NUR", "MRU"};
		Object [][] dados = {
				{50, 50, 480, 488, 454},
				{70, 234, 485, 123, 234},
				{90, 323, 323, 343, 232}
			};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 46, 723, 167);
		frame.getContentPane().add(scrollPane);
		
		
		table = new JTable(dados, nomeColunas);
		scrollPane.setViewportView(table);
		frame.setResizable(false);
	}
}
