package Programa;

import java.util.LinkedList;

public class Nur {

	public int[] calcularAcertos(String[] conteudo, String[] tipoAcesso, int Q1, int Q2, int R) {
		
		int tamanho = conteudo.length;
		
		// int[] ref = new int[tamanho];
		int[] acertos = new int[(Q2 - Q1) + 1];
		
		for(int i = Q1; i <= Q2; i++) {
			LinkedList<Integer> nur = new LinkedList<Integer>();
			LinkedList<Integer> ref = new LinkedList<Integer>();
			LinkedList<Integer> mod = new LinkedList<Integer>();
			LinkedList<Integer> classe = new LinkedList<Integer>();
			
			int acerto = 0;
			
			// for para referenciar páginas
			for(int j = 0; j < tamanho; j++) {
				
				// Pegando valor em espec�fico e convertendo-o para inteiro
				int valor = Integer.parseInt(conteudo[j]);
				
				// Verifica se a p�gina j� est� contida na mem�ria: se estiver, ACERTO!
				if(nur.contains(valor)) {
					int index = nur.indexOf(valor);

					ref.set(index, 1);
					
//					System.out.println(tipoAcesso[j]);
//					System.out.println(tipoAcesso[index]);
//					System.out.println(tipoAcesso[index].equals("W"));
//					System.out.println(mod.get(index));
					
					if(tipoAcesso[j].equals("W")) {
						mod.set(index, 1);
						classe.set(index, 3);
					} 
					
					acerto++;
					
				} else {
					// Se os frames estiverem todos ocupados, removeremos o primeiro com 
					// a menor classe 
					if(nur.size() == i) {
						int index;
						
						if(classe.contains(0)) {
							index = classe.indexOf(0);
							
						} else if(classe.contains(1)) {
							index = classe.indexOf(1);
							
						} else if(classe.contains(2)) {
							index = classe.indexOf(2);
							
						} else {
							index = classe.indexOf(3);
						}
						
						classe.remove(index);
						ref.remove(index);
						mod.remove(index);
						nur.remove(index);
						
						nur.offer(valor);
						ref.offer(1);
						
						if(tipoAcesso[j].equals("W")) {
							mod.offer(1);
							classe.offer(3);
						} else {
							mod.offer(0);
							classe.offer(2);
						}
						
					} else {
			
						nur.offer(valor); // se n�o est� cheio, apenas adiciona no fim
						ref.offer(1);
						
						if(tipoAcesso[j].equals("W")) {
							mod.offer(1);
							classe.offer(3);
						} else {
							mod.offer(0);
							classe.offer(2);
						}
					}
				}
				
				// Verifica se j� atingiu a quantidade de refs
				if((j + 1) % R == 0) {
					
					while(ref.contains(1)) {
						int index = ref.indexOf(1);
						
						ref.set(index, 0);
						
//						System.out.println(mod.indexOf(index));
						
						if(mod.get(index) == 0) {
							classe.set(index, 0);
						} else {
							classe.set(index, 1);
						}
					}
				}
				
			}
			
			acertos[i - Q1] = acerto;
		}
		
		return acertos;
	}
	
}
