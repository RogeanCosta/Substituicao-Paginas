package Programa;

import java.util.LinkedList;

public class AlgoritmosDeSubstituicao {

	// MOD: conteudo -> páginas, tamanho -> qntdPaginas
	private String[] conteudo;
	private String[] tipoAcesso;
	private int Q1, Q2, R, tamanho;

	// Atributos(LinkedList) referentes a fila, referência, modificação e classe
	LinkedList<Integer> fila = new LinkedList<Integer>();
	LinkedList<Integer> ref = new LinkedList<Integer>();
	LinkedList<Integer> mod = new LinkedList<Integer>();
	LinkedList<Integer> classe = new LinkedList<Integer>();

	public AlgoritmosDeSubstituicao(String conteudo, int Q1, int Q2, int R) {

		// Definindo quantidade de páginas na Stack do processo
		this.tamanho = conteudo.split("-").length;

		// Criando vetor com páginas referenciadas e vetor com tipo de acessos refer.
		this.conteudo = new String[tamanho];
		this.conteudo = conteudo.replaceAll("[WR || [ ]]", "").split("-");

		this.tipoAcesso = new String[tamanho];
		this.tipoAcesso = conteudo.replaceAll("[0-9 || [ ]]", "").split("-");

		// Armazenando nos atributos valores referentes aos frames e a zerezíma do bit R
		this.Q1 = Q1;
		this.Q2 = Q2;
		this.R = R;
	}

	public int[] simulaFIFO() {

		int[] acertos = new int[(Q2 - Q1) + 1];

		for (int i = Q1; i <= Q2; i++) {
			int acerto = 0;

			// for para referenciar páginas
			for (int j = 0; j < tamanho; j++) {

				// Pegando valor em específico e convertendo-o para inteiro
				int valor = Integer.parseInt(conteudo[j]);

				// Verifica se a página já está contida na memória: se estiver, ACERTO!
				if (fila.contains(valor)) {
					acerto++;

				} else {
					// Se os frames estiverem todos ocupados, removeremos o primeiro e adicionaremos
					// o atual na última posição
					if (fila.size() == i) {
						fila.poll();
						fila.offer(valor);

					} else {
						fila.offer(valor); // se não está cheio, apenas adiciona no fim

					}
				}
			}

			acertos[i - Q1] = acerto;

			// Zera os frames, afim de calcularmos para uma nova quantidade de frames
			fila.clear();
		}

		return acertos;
	}

	public int[] simulaSC() {

		int[] acertos = new int[(Q2 - Q1) + 1];
		
		for(int i = Q1; i <= Q2; i++) {
			
			int acerto = 0;
			
			// for para referenciar pï¿½ginas
			for(int j = 0; j < tamanho; j++) {
				
				// Pegando valor em especï¿½fico e convertendo-o para inteiro
				int valor = Integer.parseInt(conteudo[j]);
				
				// Verifica se a pï¿½gina jï¿½ estï¿½ contida na memï¿½ria: se estiver, ACERTO!
				if(fila.contains(valor)) {
					//System.out.println("Pagina "+ valor + " jï¿½ estava carregada");
					int index = fila.indexOf(valor);

					ref.set(index, 1);
					
					acerto++;
					
				} else {
					// Se os frames estiverem todos ocupados, removeremos o primeiro com ref 0 
					// e adicionaremos o atual na ï¿½ltima posiï¿½ï¿½o
					if(fila.size() == i) {
						
						while(ref.get(0) == 1) {
							
							ref.poll();
							ref.offer(0);
							
							fila.offer(fila.get(0));
							fila.poll();		
						
						}
						
						fila.poll();
						fila.offer(valor);
						
						ref.poll();
						ref.offer(1);
						
					} else {
						fila.offer(valor); // se nï¿½o estï¿½ cheio, apenas adiciona no fim
						ref.offer(1);

					}
				}
				
				// Verifica se jï¿½ atingiu a quantidade de refs
				if((j + 1) % R == 0) {
					
					while(ref.contains(1)) {
						int index = ref.indexOf(1);
						
						ref.set(index, 0);
					}
				}
				
			}
			
			acertos[i - Q1] = acerto;
			
			// Zera os frames, para calcular a quantidade de acertos da próxima quantia de frames
			fila.clear();
			ref.clear();
		}
		
		return acertos;
	}

	public int[] simulaMRU() {
		
		int[] acertos = new int[(Q2 - Q1) + 1];
		
		for(int i = Q1; i <= Q2; i++) {
			int acerto = 0;
			
			// for para referenciar páginas
			for(int j = 0; j < tamanho; j++) {
				
				// Pegando valor em específico e convertendo-o para inteiro
				int valor = Integer.parseInt(conteudo[j]);
				
				// Verifica se a página já está contida na memória: se estiver, ACERTO!
				if(fila.contains(valor)) {
					
					int index = fila.indexOf(valor);
					
					fila.remove(index);
					fila.offer(valor);
					
					acerto++;
					
				} else {
					// Se os frames estiverem todos ocupados, removeremos o primeiro e adicionaremos o atual na última posição
					if(fila.size() == i) {
						fila.poll();
						fila.offer(valor);
						
					} else {
						fila.offer(valor); // se não está cheio, apenas adiciona no fim
						
					}
				}
			}
			
			acertos[i - Q1] = acerto;
			
			// Zera os frames para se calcular corretamente a quantidade de acertos da prox qntd de frames
			fila.clear();
		}
		
		return acertos;
	}

	public int[] simulaNUR() {
		
		int[] acertos = new int[(Q2 - Q1) + 1];
		
		for(int i = Q1; i <= Q2; i++) {
			
			int acerto = 0;
			
			// for para referenciar paginas
			for(int j = 0; j < tamanho; j++) {
				
				// Pegando valor em especifico e convertendo-o para inteiro
				int valor = Integer.parseInt(conteudo[j]);
				
				// Verifica se a pagina esta contida na memoria: se estiver, ACERTO!
				if(fila.contains(valor)) {
					int index = fila.indexOf(valor);

					ref.set(index, 1);
					
					if(tipoAcesso[j].equals("W")) {
						mod.set(index, 1);
						classe.set(index, 3);
					} else {
						classe.set(index, 2);
					}
					
					acerto++;
					
				} else {
					// Se os frames estiverem todos ocupados, removeremos o primeiro com 
					// a menor classe 
					if(fila.size() == i) {
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
						fila.remove(index);
						
						fila.offer(valor);
						ref.offer(1);
						
						this.setaClasse(j);
						
					} else { // se nao estiver cheio, apenas adiciona no fim
			
						fila.offer(valor); 
						ref.offer(1);
						
						this.setaClasse(j);
					}
				}
				
				// Verifica se jï¿½ atingiu a quantidade de refs
				if((j + 1) % R == 0) {
					
					while(ref.contains(1)) {
						int index = ref.indexOf(1);
						
						ref.set(index, 0);
						
						if(mod.get(index) == 0) {
							classe.set(index, 0);

						} else {
							classe.set(index, 1);
						}

					}
				}
				
			}
			
			acertos[i - Q1] = acerto;
			
			// Zera tudo para calcular corretamente os acertos da próxima quantia de frames
			fila.clear();
			ref.clear();
			mod.clear();
			classe.clear();
		}
		
		return acertos;
	}

	// Este método auxiliar do método simulaNUR, engloba código comum 
	private void setaClasse (int j) {
	
		if(tipoAcesso[j].equals("W")) {
			mod.offer(1);
			classe.offer(3);
		
		} else {
			mod.offer(0);
			classe.offer(2);
		}
	}
}
