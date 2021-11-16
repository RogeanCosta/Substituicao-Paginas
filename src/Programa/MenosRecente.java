package Programa;

import java.util.LinkedList;
import java.util.Queue;

public class MenosRecente {
	
	public int[] calcularAcertos(String[] conteudo, int Q1, int Q2) {
		
		int tamanho = conteudo.length;
		int[] acertos = new int[(Q2 - Q1) + 1];
		
		for(int i = Q1; i <= Q2; i++) {
			Queue<Integer> mru = new LinkedList<Integer>();
			int acerto = 0;
			
			// for para referenciar p�ginas
			for(int j = 0; j < tamanho; j++) {
				
				// Pegando valor em espec�fico e convertendo-o para inteiro
				int valor = Integer.parseInt(conteudo[j]);
				
				// Verifica se a p�gina j� est� contida na mem�ria: se estiver, ACERTO!
				if(mru.contains(valor)) {
					
					mru.remove(valor);
					mru.offer(valor);
					
					acerto++;
					
				} else {
					// Se os frames estiverem todos ocupados, removeremos o primeiro e adicionaremos o atual na �ltima posi��o
					if(mru.size() == i) {
						mru.poll();
						mru.offer(valor);
						
					} else {
						mru.offer(valor); // se n�o est� cheio, apenas adiciona no fim
						
					}
				}
			}
			
			acertos[i - Q1] = acerto;
		}
		
		return acertos;
	}
	
}
