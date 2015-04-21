package fr.unice.polytech.cpo.leroux;

import java.net.Authenticator.RequestorType;

public class Launch {
	public static void main(String[] args) {
		Observeur observeur = new Observeur(20, 20, 0.2, 50);
		Mobile mobile = new Mobile(5, 5, 2, 2.5);
		Simulateur simulateur = new Simulateur(observeur, mobile);

		simulateur.resolutionParametres(Resolution.Methode.GRADIANT_CONJUGUE);
		System.out.println(simulateur.toString());
	}
}
