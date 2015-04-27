package fr.unice.polytech.cpo.leroux;

import org.jblas.DoubleMatrix;

public class Simulateur {
	private double x0, y0, vx, vy;
	private int temps;
	private Observateur observeur;
	private Mobile mobile;
	private double[] angles;
	private DoubleMatrix A, B;

	public Simulateur(int temps, Observateur observeur, Mobile mobile) {
		this.observeur = observeur;
		this.mobile = mobile;
		this.temps = temps;

		A = new DoubleMatrix(temps, 4);
		B = new DoubleMatrix(temps, 1);

		observeur.genererPosition(temps);
		mobile.genererPosition(temps);
		angles = calculerAngles(temps);
		x0 = y0 = vx = vy = 0;
	}

	public void resolutionParametres(Resolution.Methode methode) {
		initA();
		initB();

		DoubleMatrix solutions = null;
		switch (methode) {
		case GRADIANT_CONJUGUE:
			solutions = Resolution.gradiantConjugue(A, B);
			break;
		case MOINDRES_CARRES:
			solutions = Resolution.moindresCarres(temps, A, B);
			break;
		case INVERSE:
			solutions = Resolution.methodeInverse(A, B);
			break;
		default:
			break;
		}

		x0 = solutions.get(0, 0);
		vx = solutions.get(1, 0);
		y0 = solutions.get(2, 0);
		vy = solutions.get(3, 0);
	}

	public void initA() {
		double theta;
		for (int i = 0; i < A.rows; i++) {
			theta = angles[i];
			A.put(i, 0, Math.sin(theta));
			A.put(i, 1, -Math.cos(theta));
			A.put(i, 2, i * Math.sin(theta));
			A.put(i, 3, -i * Math.cos(theta));
		}
	}

	public void initB() {
		double theta;
		for (int i = 0; i < B.rows; i++) {
			theta = angles[i];
			B.put(i,0, observeur.getPositions().get(0,i) * Math.sin(theta) - observeur.getPositions().get(1,i) * Math.cos(theta));
		}	
	}

	private double[] calculerAngles(int t) {
		angles = new double[t];
		for (int i = 0; i < t; i++)
			angles[i] = getTheta(i);
		return angles;
	}

	private double getTheta(int i) {
		return Math.atan2(mobile.y(i) - observeur.y(i),
				mobile.x(i) - observeur.x(i));
	}
	
	public String afficherErreur() {
		return "x0 : " + Math.abs(mobile.getX0() - x0) + " y0 : " + Math.abs(mobile.getY0() - y0)
				+ " vx : " + Math.abs(mobile.getVx() - vx) + " vy : " + Math.abs(mobile.getVy() - vy);
	}

	public String toString() {
		return "x0 = " + x0 + " | y0 = " + y0 + " | vx = " + vx + " | vy = "
				+ vy + "\nErreur \n" + afficherErreur();
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

	public Observateur getObserveur() { return observeur; }
	public void setObserveur(Observateur observeur) { this.observeur = observeur; }

	public Mobile getMobile() { return mobile; }
	public void setMobile(Mobile mobile) { this.mobile = mobile; }

	public double[] getAngles() { return angles; }
	public void setAngles(double[] angles) { this.angles = angles; }

	public int getTemps() { return temps; }
	public void setTemps(int temps) { this.temps = temps; }

	public DoubleMatrix getA() { return A; }
	public void setA(DoubleMatrix a) { A = a; }

	public DoubleMatrix getB() { return B; }
	public void setB(DoubleMatrix b) { B = b; }
}
