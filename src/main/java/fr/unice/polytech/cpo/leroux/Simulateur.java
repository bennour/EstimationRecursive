package fr.unice.polytech.cpo.leroux;

import org.jblas.DoubleMatrix;

public class Simulateur {
	private double x0, y0, vx, vy;
	private Observeur observeur;
	private Mobile mobile;
	private double[] angles;
	
	public Simulateur(int temps, Observeur observeur, Mobile mobile) {
		this.observeur = observeur;
		this.mobile = mobile;
		observeur.updatePosition(temps);
		mobile.updatePosition(temps);
		angles = calculerAngles(temps);
		x0 = y0 = vx = vy = 0;
	}

	public void resolutionParametres(Resolution.Methode methode) {
		DoubleMatrix A = new DoubleMatrix(4, 4), B = new DoubleMatrix(4, 1);

		for (int i = 0; i < A.rows; i++) {
			for (int j = 0; j < A.columns; j++) {
				A.put(i, j, getConstanteA(j, j));
			}
		}

		for (int i = 0; i < B.columns; i++) {
			B.put(0, i, getConstanteB(i));
		}

		DoubleMatrix solutions = null;
		switch(methode) {
		case GRADIANT_CONJUGUE : 
			solutions = Resolution.gradiantConjugue(A, B);
			break;
		case MOINDRES_CARRES :
			solutions = Resolution.moindresCarres(0, A, B); // TODO
			break;
		case INVERSE :
			solutions = Resolution.methodeInverse(A, B);
			break;
		default :
			break;
		}
		
		x0 = solutions.get(0, 0);
		vx = solutions.get(1, 0);
		y0 = solutions.get(2, 0);
		vy = solutions.get(3, 0);
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
		return Math.cos(theta) * observeur.y(t) - Math.sin(theta)
				* observeur.x(t);
	}
	
	private double[] calculerAngles(int t) {
		angles = new double[t];
		for(int i = 0; i < t; i++)
			angles[i] = getTheta(i);
		return angles;
	}

	private double getTheta(int i) {
		return Math.atan2(mobile.y(i) - observeur.y(i),
				mobile.x(i) - observeur.x(i));
	}

	public String toString() {
		return "x0 = " + x0 + " | y0 = " + y0 + " | vx = " + vx + " | vy = "
				+ vy;
	}

	/**
	 * GETTERS ET SETTERS
	 */
	public double getX0() { return x0; }
	public void setX0(double x0) { this.x0 = x0; }

	public double getY0() { return y0; }
	public void setY0(double y0) { this.y0 = y0; }

	public double getVx() { return vx; }
	public void setVx(double vx) { this.vx = vx; }

	public double getVy() { return vy; }
	public void setVy(double vy) { this.vy = vy; }

	public Observeur getObserveur() { return observeur; }
	public void setObserveur(Observeur observeur) { this.observeur = observeur; }

	public Mobile getMobile() { return mobile; }
	public void setMobile(Mobile mobile) { this.mobile = mobile; }
	
	public double[] getAngles() { return angles; }
	public void setAngles(double[] angles) { this.angles = angles; }
}
