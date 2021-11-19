package Programa;

import java.util.LinkedList;

public class SegundaChance {

	public int[] calcularAcertos(String[] conteudo, int Q1, int Q2, int R) {
		
		int tamanho = conteudo.length;
		
		// int[] ref = new int[tamanho];
		int[] acertos = new int[(Q2 - Q1) + 1];
		
		for(int i = Q1; i <= Q2; i++) {
			LinkedList<Integer> sc = new LinkedList<Integer>();
			LinkedList<Integer> ref = new LinkedList<Integer>();
			
			int acerto = 0;
			
			// for para referenciar p�ginas
			for(int j = 0; j < tamanho; j++) {
				
				// Pegando valor em espec�fico e convertendo-o para inteiro
				int valor = Integer.parseInt(conteudo[j]);
				
				// Verifica se a p�gina j� est� contida na mem�ria: se estiver, ACERTO!
				if(sc.contains(valor)) {
					//System.out.println("Pagina "+ valor + " j� estava carregada");
					int index = sc.indexOf(valor);

					ref.set(index, 1);
					
					acerto++;
					
				} else {
					// Se os frames estiverem todos ocupados, removeremos o primeiro com ref 0 
					// e adicionaremos o atual na �ltima posi��o
					if(sc.size() == i) {
						
						while(ref.get(0) == 1) {
							
							ref.poll();
							ref.offer(0);
							
							sc.offer(sc.get(0));
							sc.poll();		
						
						}
						
						sc.poll();
						sc.offer(valor);
						
						ref.poll();
						ref.offer(1);
						
					} else {
						sc.offer(valor); // se n�o est� cheio, apenas adiciona no fim
						ref.offer(1);

					}
				}
				
				// Verifica se j� atingiu a quantidade de refs
				if((j + 1) % R == 0) {
					
					while(ref.contains(1)) {
						int index = ref.indexOf(1);
						
						ref.set(index, 0);
					}
				}
				
			}
			
			acertos[i - Q1] = acerto;
		}
		
		return acertos;
	}
}
