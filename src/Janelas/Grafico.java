package Janelas;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Programa.Aplicacao;

public class Grafico {

	public JFrame frame;


	public Grafico() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
//		frame.setBounds(100, 100, 723, 756);//
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		int largura = Toolkit.getDefaultToolkit().getScreenSize().width - 50;
		int altura = Toolkit.getDefaultToolkit().getScreenSize().height - 50;
		
		//frame.setBounds(100, 100, largura, altura);
		// frame.setLocationRelativeTo(null);

//		JPanel panel = new JPanel();
		JPanel panel = createDemoPanel();
		panel.setBounds(25, 0, largura, altura);
		// panel.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().add(panel);

	}

	
public static JPanel createDemoPanel() {
		
		// Definição dos eixos
		NumberAxis eixoX = new NumberAxis("Frames");
		eixoX.setAutoRangeIncludesZero(false);
		// -> eixoX.setRange(new Range(Aplicacao.Q1 - 1, Aplicacao.Q2 + 1));
		eixoX.setTickUnit(new NumberTickUnit(1));
//		eixoX.setAutoRangeMinimumSize(Aplicacao.Q1);
		
		NumberAxis eixoY = new NumberAxis("Acertos");
		eixoY.setAutoRangeIncludesZero(false);
		eixoY.setTickUnit(new NumberTickUnit(50));
		// -> eixoY.setRange(new Range(400, 900));
//		eixoY.setAutoRange(true);
		
		
		// Definição da plotagem das linhas
		XYLineAndShapeRenderer renderizador = new XYLineAndShapeRenderer();
		
		// Configuração das cores das linhas
		renderizador.setSeriesPaint(0, new Color(168, 21, 193)); // Roxo
		renderizador.setSeriesPaint(1, Color.blue);
		renderizador.setSeriesPaint(2, new Color(255, 0, 127)); // Rosa Choque
		renderizador.setSeriesPaint(3, new Color(0, 100, 100)); // Verde Escuro
		
		// Plotagem do gráfico
		XYPlot plot = new XYPlot(createSampleData(), eixoX, eixoY, renderizador);
		
		// plot.setDomainAxis(50, eixoX);
		// plot.setDomainAxis(100, eixoY);
		((NumberAxis)plot.getRangeAxis()).setAutoRangeIncludesZero(false); // add		

		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.black);
		//plot.setAxisOffset(new RectangleInsets(40, 40, 40, 40));
		
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
		for(int i = 0; i < dif; i = i + 10) {
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
