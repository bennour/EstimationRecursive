package fr.unice.polytech.cpo.leroux;

import org.jblas.DoubleMatrix;
import org.jfree.ui.RefineryUtilities;

public class Launch {

	public static void main(String[] args) {
		System.out.println("******************************************************");
		System.out.println("****                SANS BRUIT                    ****");
		System.out.println("******************************************************");
		launch(false);
		
		System.out.println("\n\n");
		
		System.out.println("******************************************************");
		System.out.println("****                AVEC BRUIT                    ****");
		System.out.println("******************************************************");
		launch(true);
	}
	
	public static void launch(boolean bruit) {
		Observateur observeur = new Observateur(20, 20, 0.2, 50, bruit);
		Mobile mobile = new Mobile(10, 10, 2, 2.5);
		Simulateur simulateur = new Simulateur(20, observeur, mobile);
		
		System.out.println("Angles entre l'observeur et le mobile aux temps t :");
		for(int i = 0; i < simulateur.getAngles().length; i++) {
			System.out.println("theta(" + i + ") : " + simulateur.getAngles()[i] + " rad = " + enDegrees(simulateur.getAngles()[i]) + "°");
		}

		String titre = "Simulation " + (bruit ? "avec" : "sans") + " bruit";
		Vue demo = new Vue(titre, observeur.positions, mobile.positions);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
		

		System.out.println("\n*************** Resolution *****************\n");
		simulateur.resolutionParametres(Resolution.Methode.GRADIANT_CONJUGUE);
		System.out.println("\nPar la methode du gradiant conjugue : ");
		System.out.println(simulateur.toString());
		
		simulateur.resolutionParametres(Resolution.Methode.MOINDRES_CARRES);
		System.out.println("\nPar la methode des moindres carees : ");
		System.out.println(simulateur.toString());
		
		simulateur.resolutionParametres(Resolution.Methode.INVERSE);
		System.out.println("\nPar la methode inverse : ");
		System.out.println(simulateur.toString());
		
		System.out.println("\n*************** Matrices *****************\n");
		System.out.println("A\n" + printMatrix(simulateur.getA()));
		System.out.println("B\n" + printMatrix(simulateur.getB()));
	}
	
	public static String printMatrix(DoubleMatrix m) {
		String str = "";
		
		for(int i = 0; i < m.rows; i++) {
			str += "| ";
			for(int j = 0; j < m.columns; j++)
				str += m.get(i, j) + " ";
			str += " |\n";
		}
		
		return str;
	}
	
	public static int enDegrees(double radian) {
		return (int) (radian * 180 / Math.PI);
	}
}
