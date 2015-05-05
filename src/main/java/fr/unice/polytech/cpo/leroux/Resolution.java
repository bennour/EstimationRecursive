package fr.unice.polytech.cpo.leroux;

import org.jblas.DoubleMatrix;
import org.jblas.SimpleBlas;

public class Resolution {
	
	public enum Methode {
		GRADIANT_CONJUGUE,
		MOINDRES_CARRES,
		INVERSE
	}

	public static DoubleMatrix gradiantConjugue(DoubleMatrix A, DoubleMatrix B) {
		DoubleMatrix res = new DoubleMatrix(4, 1); // initialisation d'une matrice de taille 4 x 1

		DoubleMatrix transposeA = A.transpose(); // A := transpose de A 
		
		DoubleMatrix gamma = transposeA.mmul(A); // gamma := transpose de A * A
		B = transposeA.mmul(B); // B := transpose de A * B

		DoubleMatrix g = gamma.mmul(res).sub(B); // g := gamma * X - B

		DoubleMatrix h = g; // h := g

		double temp;
		DoubleMatrix transposeH;
		for (int i = 0; i < res.rows; i++) { // Iteration sur les n lignes de X 
			transposeH = h.transpose();
			// temp := (traspose de h * g) / (transpose de h * gamma * h)
			temp = transposeH.mmul(g).get(0, 0)
					/ (transposeH.mmul(gamma).mmul(h).get(0, 0));
			
			res = res.sub(h.mmul(temp)); // X := X - h * temp
			
			g = g.sub(gamma.mmul(h).mmul(temp)); // g = g - gamma * h * temp
			
			// h := g - h * (transpose de h * gamma * g) / (transpode de h * gamma * h)
			h = g.sub(h.mmul(
					(transposeH.mmul(gamma).mmul(g)).get(0, 0)
					/ (transposeH.mmul(gamma).mmul(h)).get(0, 0)));
		}

		return res;
	}
	
	public static DoubleMatrix moindresCarres(int n, DoubleMatrix A, DoubleMatrix B) {
		DoubleMatrix res = new DoubleMatrix(4, 1), 
				A2 = new DoubleMatrix(4, 1),
				P = new DoubleMatrix(4, 4); // Initialisation de matrices 4 x 1
		
		for (int i = 0; i < 4; i++)
			P.put(i, i, 999999); // Initialisation de la diagonale avec une grande valeur

		double predictionErreur, temp;
		DoubleMatrix K;
		for (int i = 0; i < n; i++) {
			A2.put(0, 0, A.get(i,0));
			A2.put(1, 0, A.get(i,1));
			A2.put(2, 0, A.get(i,2));
			A2.put(3, 0, A.get(i,3));// A2 := colonne i de A

			predictionErreur = B.get(i,0) - A2.transpose().mmul(res).get(0,0); // calcul de l'erreur de prediction e

			temp = A2.transpose().mmul(P).mmul(A2).get(0,0); // temp := transpose de A2 * P * A2

			K = P.mmul(A2).mmul(1 / (temp + 1)); // K := P * A2 * (1 / temp + 1)

			res = res.add(K.mmul(predictionErreur)); // X := X + K * e

			P = P.sub(K.mmul(A2.transpose()).mmul(P)); // P := P - K * transpose de A2 * P
		}
		
		return res;
	}
	
	public static DoubleMatrix methodeInverse(DoubleMatrix A, DoubleMatrix B) {
		DoubleMatrix res = new DoubleMatrix(4, 1); // initialisation d'une matrice de taille 4 x 1

		DoubleMatrix transposeA = A.transpose(); // transposeA := transpose de A;
		DoubleMatrix gamma = transposeA.mmul(A); // gamma := transpose de A * A
		B = transposeA.mmul(B); // B := transpose de A * B
		
		res = inverse(gamma).mmul(B); // X := inverse de gamma * B
		
		return res;
	}
	
	private static DoubleMatrix solveLeastSquares(DoubleMatrix A, DoubleMatrix B) {
		if (B.rows < A.columns) {
			DoubleMatrix X = DoubleMatrix.concatVertically(B, new DoubleMatrix(
					A.columns - B.rows, B.columns));
			SimpleBlas.gelsd(A.dup(), X);
			return X;
		} else {
			DoubleMatrix X = B.dup();
			SimpleBlas.gelsd(A.dup(), X);
			return X.getRange(0, A.columns, 0, B.columns);
		}
	}

	private static DoubleMatrix inverse(DoubleMatrix A) {
		return solveLeastSquares(A, DoubleMatrix.eye(A.rows));
	}
}
