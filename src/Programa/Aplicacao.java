package Programa;

import java.util.concurrent.Semaphore;

import Janelas.Frames;

public class Aplicacao {

	public static Semaphore parou = new Semaphore(0);
	public static String conteudo;
	public static int Q1;
	public static int Q2;
	public static int R;
	
	
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
				
//		int[] acertos = alg.simulaFIFO();
//		int[] acertos = alg.simulaSC();
//		int[] acertos = alg.simulaMUR();
		int[] acertos = alg.simulaNUR();
		

		// Apenas vendo os valores, apagar esse trecho dps
		//********************************************************
		int dif = (Q2 - Q1) + 1;
		for(int i = 0; i < dif; i = i + 20) {
			int frame = Q1 + i;
			System.out.println("frame: " + frame + " acertos: " + acertos[i]);
		}
		// ******************************************************
	}
}
