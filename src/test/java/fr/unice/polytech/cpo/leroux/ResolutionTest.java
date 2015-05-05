package fr.unice.polytech.cpo.leroux;

import static org.junit.Assert.assertTrue;

import org.jblas.DoubleMatrix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResolutionTest {
	private DoubleMatrix A, B;

	@Before // Initialisation des matrices A et B avant chaque test
	public void setUp() throws Exception {
		double[][] tabA = new double[4][4], tabB = new double[4][1];
		// Matrice A
		tabA[0] = new double[]{3.044, 0.705, -0.896, 1.324};
		tabA[1] = new double[]{0.705, 1.303, -1.055, 0.934};
		tabA[2] = new double[]{-0.896, -1.055, 1.202, -1.096};
		tabA[3] = new double[]{1.324, 0.934, -1.096, 1.269};
		
		// Matrice B
		tabB[0][0] = -0.098;
		tabB[1][0] = -0.886;
		tabB[2][0] = 0.567;
		tabB[3][0] = 0.04;
		
		A = new DoubleMatrix(tabA);
		B = new DoubleMatrix(tabB);
	}

	@After
	public void tearDown() throws Exception {
		A = null;
		B = null;
	}
	
	@Test
	public void testGradiantConjugue() {
		DoubleMatrix res = Resolution.gradiantConjugue(A, B); // Resolution de X
		DoubleMatrix resxA = A.mmul(res); // AX := A * X
		
		assertTrue(resxA.equals(B)); // Test si AX = B
	}
	
	@Test
	public void testMoindresCarres() {
		DoubleMatrix res = Resolution.moindresCarres(4, A, B);// Resolution de X
		DoubleMatrix resxA = A.mmul(res); // AX := A * X
		
		for(int i = 0; i < resxA.rows; i++) {
			for(int j = 0; j < resxA.columns; j++)
				// test pour chaque valeur si AX(i,j) = B(i,j) a 0,01 pres 
				assertTrue(Math.abs(resxA.get(i, j) - B.get(i, j)) < 0.01);
		}
	}
	
	@Test
	public void testInverse() {
		DoubleMatrix res = Resolution.methodeInverse(A, B); // Resolution de X
		DoubleMatrix resxA = A.mmul(res); // AX := A * X
		
		assertTrue(resxA.equals(B)); // Test si AX = B
	}

}
