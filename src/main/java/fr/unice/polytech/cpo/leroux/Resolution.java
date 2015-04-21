package fr.unice.polytech.cpo.leroux;

import org.jblas.DoubleMatrix;

public class Resolution {
	
	public enum Methode {
		GRADIANT_CONJUGUE,
		MOINDRES_CARRES,
		INVERSE
	}

	public static DoubleMatrix gradiantConjugue(DoubleMatrix A, DoubleMatrix B) {
		DoubleMatrix res = new DoubleMatrix(4, 1);

		DoubleMatrix transposeA = A.transpose();
		
		DoubleMatrix gamma = transposeA.mmul(A);
		B = transposeA.mmul(B);

		DoubleMatrix g = gamma.mmul(res).sub(B);

		DoubleMatrix h = g;

		double temp; 
		for (int i = 0; i < res.rows; i++) {
			
			temp = h.transpose().mmul(g).get(0, 0)
					/ (h.transpose().mmul(gamma).mmul(h).get(0, 0));
			
			res = res.sub(g.mmul(temp));
			
			g = g.sub(gamma.mmul(h).mmul(temp));
			
			h = g.sub(h.mmul(
					(h.transpose().mmul(gamma).mmul(g)).get(0, 0)
					/ (h.transpose().mmul(gamma).mmul(h)).get(0, 0)));
			
		}

		return res;
	}
	
	public static DoubleMatrix moindresCarres(int n, DoubleMatrix A, DoubleMatrix B) {
		DoubleMatrix res = new DoubleMatrix(4, 1), 
				A2 = new DoubleMatrix(4, 1),
				P = new DoubleMatrix(4, 4);
		
		for (int i = 0; i < 4; i++)
			P.put(i, i, 100000);

		double predictionErreur, temp;
		DoubleMatrix K;
		for (int i = 0; i < n; i++) {
			A2.put(0, 0, A.get(i,0));
			A2.put(1, 0, A.get(i,1));
			A2.put(2, 0, A.get(i,2));
			A2.put(3, 0, A.get(i,3));

			predictionErreur = B.get(i,0) - A2.transpose().mmul(res).get(0,0);

			temp = A2.transpose().mmul(P).mmul(A2).get(0,0);

			K = P.mmul(A2).mmul(1 / (temp + 1));

			res = res.add(K.mmul(predictionErreur));

			P = P.sub(K.mmul(A2.transpose()).mmul(P));
		}
		
		return res;
	}
	
	public static DoubleMatrix methodeInverse(DoubleMatrix A, DoubleMatrix B) {
		DoubleMatrix res = new DoubleMatrix(4, 1);

		DoubleMatrix transposeA = A.transpose();
		DoubleMatrix gamma = transposeA.mmul(A);
		B = transposeA.mmul(B);
		
		res = Solve.pinv(gamma).mmul(B);
		
		return res;
	}
	
	public static void predictionErreur(DoubleMatrix pratique, DoubleMatrix theorique) {
		for (int i = 0; i < pratique.rows; i++) {
			for (int j = 0; j < pratique.columns; j++)
				System.out.println(Math.abs(pratique.get(i,j) - theorique.get(i,j)));
		}
	}
}
