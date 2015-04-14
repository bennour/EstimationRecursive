package fr.unice.polytech.cpo.leroux;

import org.jblas.DoubleMatrix;

public class Simulateur {
	private double x0, y0, vx, vy;
	private Observeur observeur;
	private Mobile mobile;

	public Simulateur(Observeur observeur, Mobile mobile) {
		this.observeur = observeur;
		this.mobile = mobile;
		x0 = y0 = vx = vy = 0;
	}

	public void resolutionParametres() {
		DoubleMatrix A = new DoubleMatrix(4, 4), B = new DoubleMatrix(4, 1);
		
		for(int i = 0; i < A.rows; i++) {
			for(int j = 0; j < A.columns; j++) {
				A.put(i, j, getConstanteA(j, j));
			}
		}
		
		for(int i = 0; i < B.columns; i++) {
			B.put(0, i, getConstanteB(i));
		}
		
		DoubleMatrix solutions = gradiantConjugue(A, B);
		x0 = solutions.get(0, 0);
		vx = solutions.get(1, 0);
		y0 = solutions.get(2, 0);
		vy = solutions.get(3, 0);
	}

	private DoubleMatrix gradiantConjugue(DoubleMatrix A, DoubleMatrix B) {
		DoubleMatrix res = new DoubleMatrix(4, 1);

		DoubleMatrix symA = A.transpose();
		symA = symA.mul(A);

		DoubleMatrix symB = B.transpose();
		symB = symB.mul(B);

		DoubleMatrix g = symA.mul(res).sub(symB);
		DoubleMatrix h = g;

		double temp = 0;
		for (int i = 0; i < res.rows; i++) {
			temp = h.transpose().mul(g).get(0, 0)
					/ h.transpose().mul(symA).mul(h).get(0, 0);
			res = res.sub(g.mul(temp));
			g = g.sub(symA.mul(h).mul(temp));
			h = g.sub(h.mul((h.transpose().mul(symA).mul(g).get(0, 0) / (h
					.transpose().mul(symA).mul(h).get(0, 0)))));
		}

		return res;
	}

	private double getConstanteA(int i, int t) {
		switch (i) {
		case 0:
			return Math.sin(getTheta(t));
		case 1:
			return Math.sin(getTheta(t)) * t;
		case 2:
			return Math.cos(getTheta(t));
		case 3:
			return Math.cos(getTheta(t)) * t;
		default:
			return -1.0;
		}
	}

	private double getConstanteB(int t) {
		double theta = getTheta(t);
		return Math.cos(theta) * observeur.y(t) - Math.sin(theta) * observeur.x(t);
	}
	
	private double getTheta(int t) {
		return Math.atan2(mobile.y(t) - observeur.y(t),
				mobile.x(t) - observeur.x(t));
	}
	
	public String toString() {
		return "x0 = " + x0 + " | y0 = " + y0 + " | vx = " + vx + " | vy = " + vy;
	}
	
	/**
	 * GETTERS ET SETTERS
	 */
	public double getX0() {
		return x0;
	}

	public void setX0(double x0) {
		this.x0 = x0;
	}

	public double getY0() {
		return y0;
	}

	public void setY0(double y0) {
		this.y0 = y0;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public Observeur getObserveur() {
		return observeur;
	}

	public void setObserveur(Observeur observeur) {
		this.observeur = observeur;
	}

	public Mobile getMobile() {
		return mobile;
	}

	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}
}
