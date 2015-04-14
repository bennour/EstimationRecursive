package fr.unice.polytech.cpo.leroux;

public class Observeur extends Point {
	private double theta, rayon;

	public Observeur(double x0, double y0, double theta, double rayon) {
		super(x0, y0);
		this.theta = theta;
		this.rayon = rayon;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double teta) {
		this.theta = teta;
	}

	public double getRayon() {
		return rayon;
	}

	public void setRayon(double rayon) {
		this.rayon = rayon;
	}
	
			
}
