package Programa;

import java.util.LinkedList;

public class SegundaChance {

	public int[] calcularAcertos(String[] conteudo, int Q1, int Q2, int R) {
		
		int tamanho = conteudo.length;
		
		// int[] ref = new int[tamanho];
		int[] acertos = new int[(Q2 - Q1) + 1];
		
		for(int i = Q1; i <= Q2; i++) {
			LinkedList<Integer> fifo = new LinkedList<Integer>();
			LinkedList<Integer> ref = new LinkedList<Integer>();
			
			int acerto = 0;
			
			// for para referenciar páginas
			for(int j = 0; j < tamanho; j++) {
				
				// Pegando valor em específico e convertendo-o para inteiro
				int valor = Integer.parseInt(conteudo[j]);
				
				// Verifica se já atingiu a quantidade de refs
				if((j + 1) % R == 0) {
					
					while(ref.contains(1)) {
						int index = ref.indexOf(1);
						
						ref.set(index, 0);
					}
				}
				
				// Verifica se a página já está contida na memória: se estiver, ACERTO!
				if(fifo.contains(valor)) {
					int index = fifo.indexOf(valor);

					ref.set(index, 1);
					
					acerto++;
					
				} else {
					// Se os frames estiverem todos ocupados, removeremos o primeiro com ref 0 e adicionaremos o atual na última posição
					if(fifo.size() == i) {
						
						fifo.poll();
						fifo.offer(valor);
						
					} else {
						fifo.offer(valor); // se não está cheio, apenas adiciona no fim
						ref.offer(1);
						
					}
				}
			}
			
			acertos[i - Q1] = acerto;
		}
		
		return acertos;
	}
}
