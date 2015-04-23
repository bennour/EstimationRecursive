package fr.unice.polytech.cpo.leroux;

import static org.junit.Assert.assertTrue;

import org.jblas.DoubleMatrix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResolutionTest {
	private DoubleMatrix A, B;

	@Before
	public void setUp() throws Exception {
		double[][] tabA = new double[4][4], tabB = new double[4][1];
		tabA[0] = new double[]{3.044, 0.705, -0.896, 1.324};
		tabA[1] = new double[]{0.705, 1.303, -1.055, 0.934};
		tabA[2] = new double[]{-0.896, -1.055, 1.202, -1.096};
		tabA[3] = new double[]{1.324, 0.934, -1.096, 1.269};
		
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
		DoubleMatrix res = Resolution.gradiantConjugue(A, B);
		DoubleMatrix resxA = A.mmul(res);
		
		assertTrue(resxA.equals(B));
	}
	
	@Test
	public void testMoindresCarres() {
		DoubleMatrix res = Resolution.moindresCarres(4, A, B);
		DoubleMatrix resxA = A.mmul(res);
		
		for(int i = 0; i < resxA.rows; i++) {
			for(int j = 0; j < resxA.columns; j++)
				assertTrue(Math.abs(resxA.get(i, j) - B.get(i, j)) < 0.01);
		}
	}
	
	@Test
	public void testInverse() {
		DoubleMatrix res = Resolution.methodeInverse(A, B);
		DoubleMatrix resxA = A.mmul(res);
		
		assertTrue(resxA.equals(B));
	}

}
