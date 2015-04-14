package fr.unice.polytech.cpo.leroux;

public class Launch {
	public static void main(String[] args) {
		Observeur observeur = new Observeur(0, 0, 0, 10);
		Mobile mobile = new Mobile(0, 0, 0, 0);
		Simulateur simulateur = new Simulateur(observeur, mobile);
		
		for (int i = 0; i < 10; i++) {
			mobile.avancer(i);
			simulateur.resolutionParametres();
			System.out.println(simulateur.toString());
		}
	}
}
