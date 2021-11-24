package Programa;

import java.util.concurrent.Semaphore;

import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import Janelas.Frames;
import Janelas.Tabela;

public class Aplicacao {

	public static Semaphore parou = new Semaphore(0);
	public static String conteudo;
	public static int Q1;
	public static int Q2;
	public static int R;
	public static Object dados[][];
	
	
	public static void main(String[] args) {
	
		
		Frames window = new Frames();
		window.frame.setVisible(true);

		try {
			parou.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.printf("");
		window.frame.setVisible(false);
		window.frame.dispose();
		
		// ============ TESTANDO NOVA CLASSE ==================
		var alg = new AlgoritmosDeSubstituicao(conteudo, Q1, Q2, R);
			
		int [] acertosFIFO = alg.simulaFIFO();
		int [] acertosSC = alg.simulaSC();
		int [] acertosMRU = alg.simulaMRU();
		int [] acertosNUR = alg.simulaNUR();

		
		int dif = (Q2 - Q1) + 1;
		System.out.println("dif: " + dif);
		int incremento = dif/15;
		System.out.println("Numero de linhas:" + incremento);
		dados = new Object[dif][5];
		
//		for (int j = 0; j < incremento; j++) {
//			System.out.println("j:" + j);
//			dados[j][0] = Q1 + j;
//			dados[j][1] = acertosFIFO[j+Q1];
//			dados[j][2] = acertosSC[j+Q1];
//			dados[j][3] = acertosNUR[j+Q1];
//			dados[j][4] = acertosMRU[j+Q1];
//		}
		
		for (int j = 0; j < dif; j++) {
			System.out.println("j:" + j);
			dados[j][0] = (Q1 + j);
			dados[j][1] = acertosFIFO[j];
			dados[j][2] = acertosSC[j];
			dados[j][3] = acertosNUR[j];
			dados[j][4] = acertosMRU[j];
		}
		
		
		
		// Apenas vendo os valores, apagar esse trecho dps
		//********************************************************
		dif = (Q2 - Q1) + 1;
		for(int i = 0; i < dif; i = i + 20) {
			int frame = Q1 + i;

			System.out.println("FIFO frame: " + frame + " acertos: " + acertosFIFO[i]);
			System.out.println("SC frame: " + frame + " acertos: " + acertosSC[i]);
			System.out.println("MRU frame: " + frame + " acertos: " + acertosMRU[i]);
			System.out.println("NUR frame: " + frame + " acertos: " + acertosNUR[i]);
			
			
		}
		// ******************************************************
		
		Tabela tabela = new Tabela("AcertosxFrames");
		
		// DONT WORK
		// tabela.pack();
        // RefineryUtilities.centerFrameOnScreen(tabela);

        tabela.frame.setVisible(true);
	}
}
