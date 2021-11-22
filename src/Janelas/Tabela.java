package Janelas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import Programa.Aplicacao;

import java.awt.SystemColor;

public class Tabela {

	public JFrame frame;
	public JTable table;
	private JTextField titulo;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Tabela window = new Tabela();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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
		frame.setTitle("Algoritmos de Substitui\u00E7\u00E3o");
		frame.setBounds(100, 100, 980, 725);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		String[] nomeColunas = {"Quantidade de Frames", "FIFO", "SEGUNDA CHANCE", "NUR", "MRU"};
		Object [][] dados = {
				{50, 50, 480, 488, 454},
				{70, 234, 485, 123, 234},
				{90, 323, 323, 343, 232}
			};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(105, 399, 757, 264);
		frame.getContentPane().add(scrollPane);
		
		
		table = new JTable(Aplicacao.dados, nomeColunas);
		scrollPane.setViewportView(table);
		table.getTableHeader().setReorderingAllowed(false);
		
		//Centralizando todas as celulas da tabela
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++)
	    {
			table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
	    }
		
		titulo = new JTextField();
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 12));
		titulo.setBackground(Color.WHITE);
		titulo.setBounds(255, 380, 607, 20);
		frame.getContentPane().add(titulo);
		titulo.setColumns(10);
		titulo.setHorizontalAlignment(JTextField.CENTER);
		titulo.setEditable(false);
		titulo.setText("QUANTIDADE DE ACERTOS");
		
		frame.setResizable(false);

	}
}
