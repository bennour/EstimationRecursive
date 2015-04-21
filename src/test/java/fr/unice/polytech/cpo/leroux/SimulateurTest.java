package fr.unice.polytech.cpo.leroux;

import static org.junit.Assert.*;

import org.jblas.DoubleMatrix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimulateurTest {
	private Simulateur simulateur;
	private Observeur observeur;
	private Mobile mobile;

	@Before
	public void setUp() throws Exception {
		observeur = new Observeur(0, 0, 0, 10);
		mobile = new Mobile(0, 0, 0, 0);
		simulateur = new Simulateur(0, observeur, mobile);
	}

	@After
	public void tearDown() throws Exception {
		observeur = null;
		mobile = null;
		simulateur = null;
	}

	@Test
	public void testSimulateur() {
		assertNotNull(simulateur);
	}

	@Test
	public void testResolutionParametres() {
		// TODO
	}
	
	@Test
	public void testGradiantConjugue() {
		double[][] tabA = new double[4][4], tabB = new double[4][1];
		tabA[0] = new double[]{3.044, 0.705, -0.896, 1.324};
		tabA[1] = new double[]{0.705, 1.303, -1.055, 0.934};
		tabA[2] = new double[]{-0.896, -1.055, 1.202, -1.096};
		tabA[3] = new double[]{1.324, 0.934, -1.096, 1.269};
		
		tabB[0][0] = -0.098;
		tabB[1][0] = -0.886;
		tabB[2][0] = 0.567;
		tabB[3][0] = 0.04;
		
		DoubleMatrix A = new DoubleMatrix(tabA), B = new DoubleMatrix(tabB);
	
		DoubleMatrix res = Resolution.gradiantConjugue(A, B);
		
		System.out.println("Taille : ( "+A.rows +"," +A.columns +" )  x  ( " +B.rows +"," +B.columns+ " ) =  ( " +res.rows +"," +res.columns+ " )" );
		
		System.out.println("A : " + A + "\nB : " + B + "\nX : " + res);

		DoubleMatrix resxA = A.mmul(res);
		System.out.println("\nA * X : " + resxA);
		assertTrue(resxA.equals(B));
	}

}
