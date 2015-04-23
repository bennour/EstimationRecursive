package fr.unice.polytech.cpo.leroux;

import org.jfree.ui.RefineryUtilities;

public class Launch {

	public static void main(String[] args) {
		Observeur observeur = new Observeur(20, 20, 0.2, 50);
		Mobile mobile = new Mobile(5, 5, 2, 2.5);
		Simulateur simulateur = new Simulateur(20, observeur, mobile);
		
		System.out.println("Angles entre l'observeur et le mobile aux temps t :");
		for(int i = 0; i < simulateur.getAngles().length; i++) {
			System.out.println("theta(" + i + ") : " + simulateur.getAngles()[i]);
		}

		Vue demo = new Vue("Simulation", observeur.positions, mobile.positions);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

		System.out.println("\n\n*************** Resolution *****************\n");
		simulateur.resolutionParametres(Resolution.Methode.GRADIANT_CONJUGUE);
		System.out.println("\nPar la m�thode du gradiant conjugu� : ");
		System.out.println(simulateur.toString());
		
		simulateur = new Simulateur(20, observeur, mobile);
		simulateur.resolutionParametres(Resolution.Methode.MOINDRES_CARRES);
		System.out.println("\nPar la m�thode des moindres car�es : ");
		System.out.println(simulateur.toString());
		
		simulateur = new Simulateur(20, observeur, mobile);
		simulateur.resolutionParametres(Resolution.Methode.INVERSE);
		System.out.println("\nPar la m�thode inverse : ");
		System.out.println(simulateur.toString());
	}
}
