package fr.unice.polytech.cpo.leroux;


public class Observeur extends Point {
	private double theta, rayon;

	public Observeur(double x0, double y0, double theta, double rayon) {
		super(x0, y0);
		this.theta = theta;
		this.rayon = rayon;
	}
	
	public void updatePosition(int t) {
		positions.resize(2, t);
		for(int i = 1; i < t; i++) {
			double xTheorique = (double) (x0 + rayon * Math.cos(theta * i));
			double yTheorique = (double) (y0 + rayon * Math.sin(theta * i));
			double xBruit = xTheorique + bruit();
			double yBruit = yTheorique + bruit();
			
			positions.put(0, i, xBruit);
			positions.put(1, i, yBruit);
		}
	}

	public double bruit() {
		return Math.random() - 0.5;
	}

	public double getTheta() { return theta; }
	public void setTheta(double teta) { this.theta = teta; }

	public double getRayon() { return rayon; }
	public void setRayon(double rayon) { this.rayon = rayon; }
			
}
