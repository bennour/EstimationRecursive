package fr.unice.polytech.cpo.leroux;



public class Observateur extends Point {
	private double theta, rayon;
	private boolean bruit;

	public Observateur(double x0, double y0, double theta, double rayon, boolean bruit) {
		super(x0, y0);
		this.theta = theta;
		this.rayon = rayon;
		this.bruit = bruit;
	}
	
	public void genererPosition(int t) {
		positions.resize(2, t);
		init();
		
		for(int i = 1; i < t; i++) {
			double xTheorique = (double) (x0 + rayon * Math.cos(theta * i));
			double yTheorique = (double) (y0 + rayon * Math.sin(theta * i));
			double xBruit = xTheorique + bruit();
			double yBruit = yTheorique + bruit();
			
			if (bruit) {
				positions.put(0, i, xBruit);
				positions.put(1, i, yBruit);
			} else {
				positions.put(0, i, xTheorique);
				positions.put(1, i, yTheorique);
			}
		}
	}

	public double bruit() {
		return Math.random() - rayon * 0.03;
	}

	public double getTheta() { return theta; }
	public void setTheta(double teta) { this.theta = teta; }

	public double getRayon() { return rayon; }
	public void setRayon(double rayon) { this.rayon = rayon; }
			
}
