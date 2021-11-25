package Janelas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

// Aqui foi copiado
import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.ValueAxisPlot;
import org.jfree.data.Range;
import org.jfree.data.RangeType;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectUtilities;

import Programa.Aplicacao;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tabela extends ApplicationFrame{

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
	public Tabela(String title) {
		super(title);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Algoritmos de Substitui\u00E7\u00E3o");
		frame.setBounds(100, 100, 980, 619);
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
		scrollPane.setBounds(85, 42, 757, 427);
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
		titulo.setBounds(235, 23, 607, 20);
		frame.getContentPane().add(titulo);
		titulo.setColumns(10);
		titulo.setHorizontalAlignment(JTextField.CENTER);
		titulo.setEditable(false);
		titulo.setText("QUANTIDADE DE ACERTOS");
		
		JButton mostraGraph = new JButton("Mostrar Gráfico");
		mostraGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Grafico window = new Grafico();
				window.frame.setVisible(true);
			}
		});
		mostraGraph.setBackground(Color.GREEN);
		mostraGraph.setFont(new Font("Arial Black", Font.PLAIN, 13));
		mostraGraph.setForeground(Color.DARK_GRAY);
		mostraGraph.setBounds(392, 514, 169, 23);
		frame.getContentPane().add(mostraGraph);
		
		// Criação do panel para inserção do gráfico
//		JPanel panel = new JPanel();
//		JPanel panel = createDemoPanel();
//		panel.setBounds(29, 27, 910, 392);
//		panel.setAutoscrolls(true);
		//panel.setPreferredSize(new DimensionUIResource(Aplicacao.Q1, Aplicacao.Q2));
		
//		frame.getContentPane().add(panel);
		frame.setResizable(false);
		
	}
}
