package Programa;

import java.util.concurrent.Semaphore;

import Janelas.Frames;
import Janelas.Tabela;

public class Aplicacao {

	public static Semaphore parou = new Semaphore(0);
	public static String conteudo;
	public static int Q1;
	public static int Q2;
	public static int R;
	public static Integer dados[][];
	
	
	public static void main(String[] args) {
	
		
		Frames window = new Frames();
		window.frame.setVisible(true);

		// Semáforo interrompe execução da Thread main até que valores sejam passados
		try {
			parou.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		window.frame.setVisible(false);
		window.frame.dispose();
	
		// Criação da classe para calcular acertos
		var alg = new AlgoritmosDeSubstituicao(conteudo, Q1, Q2, R);
		
		// Cálculo dos acertos
		int [] acertosFIFO = alg.simulaFIFO();
		int [] acertosSC = alg.simulaSC();
		int [] acertosMRU = alg.simulaMRU();
		int [] acertosNUR = alg.simulaNUR();

		
		// Criação e inserção de dados em array de Object para tabela
		int dif = (Q2 - Q1) + 1;
		dados = new Integer[dif][5];
		
		for (int j = 0; j < dif; j++) {
			dados[j][0] = (Q1 + j);
			dados[j][1] = acertosFIFO[j];
			dados[j][2] = acertosSC[j];
			dados[j][3] = acertosNUR[j];
			dados[j][4] = acertosMRU[j];
		}
		
		
		Tabela tabela = new Tabela("AcertosxFrames");		
        tabela.frame.setVisible(true);
	}
}
