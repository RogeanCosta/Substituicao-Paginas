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

	
	private void initialize() {
		
		// Criação e Configuração da janela do gráfico
		frame = new JFrame();
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//frame.setLocationRelativeTo(null);
		
		// Cálculo para melhor uso de tela
		int largura = Toolkit.getDefaultToolkit().getScreenSize().width - 50;
		int altura = Toolkit.getDefaultToolkit().getScreenSize().height - 50;
		
		// Definição do tamanho do frame
		frame.setSize(largura, altura);

		// Criação e configuração do painel que irá conter o gráfico 
		JPanel panel = createDemoPanel();
		panel.setBounds(25, 0, largura - 50, altura - 50);
		frame.getContentPane().add(panel);

	}

	
	public static JPanel createDemoPanel() {
		
		// Definição dos eixos e suas configurações
		NumberAxis eixoX = new NumberAxis("Frames");
		eixoX.setAutoRangeIncludesZero(false);
		eixoX.setTickUnit(new NumberTickUnit(1));
		
		NumberAxis eixoY = new NumberAxis("Acertos");
		eixoY.setAutoRangeIncludesZero(false);

		// Definição da plotagem das linhas
		XYLineAndShapeRenderer renderizador = new XYLineAndShapeRenderer();
		
		// Configuração das cores das linhas
		renderizador.setSeriesPaint(0, new Color(168, 21, 193)); // Roxo
		renderizador.setSeriesPaint(1, Color.blue);
		renderizador.setSeriesPaint(2, new Color(255, 0, 127)); // Rosa Choque
		renderizador.setSeriesPaint(3, new Color(0, 100, 100)); // Verde Escuro
		
		// Plotagem do gráfico
		XYPlot plot = new XYPlot(createSampleData(), eixoX, eixoY, renderizador);
		
		// Autorange para o plot
		((NumberAxis)plot.getRangeAxis()).setAutoRangeIncludesZero(false);

		// Coloração da plotagem
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.black);
		
		// Criar e retornar painel chart
		JFreeChart chart = new JFreeChart("Algoritmos de substituição de páginas", 
				JFreeChart.DEFAULT_TITLE_FONT, plot, true);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		
		return chartPanel;
	}
	

	// Método que retornará as informações a serem impressas no gráfico
	private static XYDataset createSampleData() {
		
		XYSeries series = new XYSeries("FIFO");
		XYSeries series1 = new XYSeries("SC");
		XYSeries series2 = new XYSeries("NUR");
		XYSeries series3 = new XYSeries("MRU");
		
		// Informações necessárias para pegar apenas 5 pontos a serem plotados no gráfico
		int dif = (Aplicacao.Q2 - Aplicacao.Q1) + 1;
		int incremental = (dif > 4)? (dif/5) : 1;
		
		for(int i = 0; i < dif; i = i + incremental) {
			series.add(Aplicacao.dados[i][0], Aplicacao.dados[i][1]);
			series1.add(Aplicacao.dados[i][0], Aplicacao.dados[i][2]);
			series2.add(Aplicacao.dados[i][0], Aplicacao.dados[i][3]);
			series3.add(Aplicacao.dados[i][0], Aplicacao.dados[i][4]);
		}
		
		// União das informações em uma XYSeriesCollection
		XYSeriesCollection resultado = new XYSeriesCollection(series);
		resultado.addSeries(series1);
		resultado.addSeries(series2);
		resultado.addSeries(series3);
		
		return resultado;
	}
}
