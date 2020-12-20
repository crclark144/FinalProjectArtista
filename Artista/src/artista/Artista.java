package artista;

import java.awt.EventQueue;

public class Artista {

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
