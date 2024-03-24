package controller;
import java.util.concurrent.Semaphore;

public class Metodos3 extends Thread {
	private Semaphore atirando;
	private int id;
	private int progresso, pontos, pontuacao, aux;
	static int chegada, ct;
	static int atletas[] = new int[25];
	static String colocacao[] = new String[25];
	static int premio = 250;
	
	public Metodos3(Semaphore atirando, int i) {
		id = i;
		this.atirando = atirando;		
	}
	
	public void run() {		
			while (progresso < 3000) {
				progresso += (int) ((Math.random() * 6) + 20);
			}
			if (progresso>3000) {
				int resto = progresso - 3000;
				progresso -= resto;
			}			
			try {
				atirando.acquire();
				atirando(id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				atirando.release();
			}
			while (progresso < 8000) {
				progresso += (int) ((Math.random() * 11) + 30);
			}
			if (progresso>8000) {
				int resto = progresso - 8000;
				progresso -= resto;
			}
			System.err.println("Atleta #" + id + " foi o " + ++chegada + "o. a chegar, com "
					+ (pontuacao+premio) + " pontos totais.");
			atletas[id] = (pontuacao + premio);
			premio -= 10;
			++ct;
			
			/*if (ct == 25) {
			for (int i = 0; i < 24; i++) {
				for (int j = (i + 1); j < 24; j++) {
					if (atletas[i]<atletas[j]) {
						aux = atletas[i];
						atletas[i] = atletas[j];
						atletas[j] = aux;
						colocacao[i] = i;
					} else {
						colocacao[i] = j;						
					}
				}
			}
			for (int i = 0; i < 25; i ++) {
				System.out.println("Atleta #" + colocacao[i] +" " + atletas[i]);
			}
			}*/
			if (ct == 25) {
				for (int i = 0; i < 24; i++) {
					for (int j = (i+1); j < 25; j++) {
						if (atletas[i] < atletas[j] ) {
							colocacao[i] = "Atleta #" + j + " " + atletas[j] + " pontos ";
							//colocacao[j] = "Atleta #" + j + " " + atletas[i] + " pontos ";
						} else {
							colocacao[i] =  "Atleta #" + i + " " + atletas[i] + " pontos ";
							//colocacao[j] = "Atleta #" + j + " " + atletas[j] + " pontos ";
						}
					}
				}
				for (int i = 0; i < 25; i++) {
					System.out.println(colocacao[i]);
				}
			}
			
	}

	private void atirando(int id) {

		for (int i = 0; i < 3; i ++) {
			int sleep = (int) ((Math.random() * 2501) + 500);
			int pontos = (int) ((Math.random() * 11));
			pontuacao += pontos;
			System.out.println("Atleta #" + id + " fez " + pontos + " pontos no " + (i+1) + " tiro");
			try {
				sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Atleta #" + id + " fez " + pontuacao + " pontos totais no "  + " tiro ao alvo");
	}
}
