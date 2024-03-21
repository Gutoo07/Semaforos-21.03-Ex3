package view;
import java.util.concurrent.Semaphore;
import controller.Metodos3;

public class Main3 {

	public static void main(String[] args) {
		Semaphore atirando = new Semaphore (5);
		for (int i = 0; i < 25; i++) {
			Thread atleta = new Metodos3(atirando, i);
			atleta.start();
		}
	}

}
