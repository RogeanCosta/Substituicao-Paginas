package Janelas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import org.jfree.ui.ApplicationFrame;

import Programa.Aplicacao;

@SuppressWarnings("serial")
public class Tabela extends ApplicationFrame{

	public JFrame frame;
	public JTable table;
	private JTextField titulo;
	

	public Tabela(String title) {
		super(title);
		initialize();
	}

	
	private void initialize() {
		
		// Criação e Configuração do frame que conterá os dados da tabela
		frame = new JFrame();
		frame.setTitle("Algoritmos de Substituição");
		frame.setBounds(100, 100, 980, 619);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		// Array de String com os títulos das colunas
		String[] nomeColunas = {"Quantidade de Frames", "FIFO", "SEGUNDA CHANCE", "NUR", "MRU"};
		
		// Scrollpane sendo inserido na tabela para visualização total dos dados
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 42, 801, 447);
		frame.getContentPane().add(scrollPane);
		
		// Criação da tabela de fato
		table = new JTable(Aplicacao.dados, nomeColunas);
		scrollPane.setViewportView(table);
		
		// Não permite edição de qualquer tipo nas colunas da tabela
		table.setEnabled(false);
		table.getTableHeader().setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		
		//Centralizando todas as celulas da tabela
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++)
	    {
			table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
	    }
		
		// Campo com texto "Quantidade de acertos" acima das colunas da tabela
		titulo = new JTextField();
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 12));
		titulo.setBackground(Color.WHITE);
		titulo.setBounds(244, 23, 641, 20);
		frame.getContentPane().add(titulo);
		titulo.setColumns(10);
		titulo.setHorizontalAlignment(JTextField.CENTER);
		titulo.setEditable(false);
		titulo.setText("QUANTIDADE DE ACERTOS");
		
		// Botão para mostrar o gráfico em um novo frame, este permanece aberto
		JButton mostraGraph = new JButton("Mostrar Gráfico");
		mostraGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Grafico window = new Grafico();
				window.frame.setVisible(true);
			}
		});
		mostraGraph.setBackground(new Color(0, 204, 153));
		mostraGraph.setFont(new Font("Arial Black", Font.PLAIN, 13));
		mostraGraph.setForeground(Color.DARK_GRAY);
		mostraGraph.setBounds(407, 512, 152, 29);
		
		frame.getContentPane().add(mostraGraph);
		frame.setResizable(false);
		
	}
}
