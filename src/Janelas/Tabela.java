package Janelas;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

import Programa.Aplicacao;

import javax.swing.JPanel;

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
		
		// Criação do panel para inserção do gráfico
		JPanel panel = createDemoPanel();
		panel.setBounds(105, 42, 757, 291);
		
		frame.getContentPane().add(panel);
		frame.setResizable(false);
		
	}
	
	
	public static JPanel createDemoPanel() {
		
		// Definição dos eixos
		NumberAxis eixoX = new NumberAxis("Frames");
		// eixoX.setAutoRangeIncludesZero(false);
		eixoX.setAutoRange(false);
		
		NumberAxis eixoY = new NumberAxis("Acertos");
		// eixoY.setAutoRangeIncludesZero(false);
		eixoY.setAutoRange(false);
		
		// Definição da plotagem das linhas
		XYLineAndShapeRenderer renderizador = new XYLineAndShapeRenderer();
		
		// Configuração das cores das linhas
		renderizador.setSeriesPaint(0, new Color(168, 21, 193)); // Roxo
		renderizador.setSeriesPaint(1, Color.blue);
		renderizador.setSeriesPaint(2, new Color(255, 0, 127)); // Rosa Choque
		renderizador.setSeriesPaint(3, new Color(0, 100, 100)); // Verde Escuro
		
		// Plotagem do gráfico
		XYPlot plot = new XYPlot(createSampleData(), eixoX, eixoY, renderizador);
		
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.black);
		plot.setAxisOffset(new RectangleInsets(40, 40, 40, 40));
		
		// Criar e retornar painel chart
		JFreeChart chart = new JFreeChart("Algoritmos de sub. de páginas", 
				JFreeChart.DEFAULT_TITLE_FONT, plot, true);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		
		return chartPanel;
	}
	
	private static XYDataset createSampleData() {
		
		XYSeries series = new XYSeries("FIFO");
		XYSeries series1 = new XYSeries("SC");
		XYSeries series2 = new XYSeries("NUR");
		XYSeries series3 = new XYSeries("MRU");
		
		int dif = (Aplicacao.Q2 - Aplicacao.Q1) + 1;
		for(int i = 0; i < dif; i++) {
			series.add(Double.valueOf(Aplicacao.dados[i][0].toString()), Double.valueOf(Aplicacao.dados[i][1].toString()));
			series1.add(Double.valueOf(Aplicacao.dados[i][0].toString()), Double.valueOf(Aplicacao.dados[i][2].toString()));
			series2.add(Double.valueOf(Aplicacao.dados[i][0].toString()), Double.valueOf(Aplicacao.dados[i][3].toString()));
			series3.add(Double.valueOf(Aplicacao.dados[i][0].toString()), Double.valueOf(Aplicacao.dados[i][4].toString()));
		}
		
		System.out.println("QUEBROU? " + Double.valueOf(Aplicacao.dados[0][0].toString()));
		
		XYSeriesCollection resultado = new XYSeriesCollection(series);
		resultado.addSeries(series1);
		resultado.addSeries(series2);
		resultado.addSeries(series3);
		
		return resultado;
	}
}
