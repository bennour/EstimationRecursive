package fr.unice.polytech.cpo.leroux;

import org.jblas.DoubleMatrix;

public class Observeur extends Point {
	private double theta, rayon;
	private DoubleMatrix bruit;

	public Observeur(double x0, double y0, double theta, double rayon) {
		super(x0, y0);
		this.theta = theta;
		this.rayon = rayon;
		bruit = new DoubleMatrix(2, 0);
	}
	
	public void updatePosition(int t) {
		DoubleMatrix TEMP = new DoubleMatrix(2, 1);
		DoubleMatrix oldpos;
		for (int i = 0; i < t; i++) {
			double new_x_theoric = (double) (x0 + rayon * Math.cos(theta * i));
			double new_y_theoric = (double) (y0 + rayon * Math.sin(theta * i));
			double new_x_noise = new_x_theoric + bruit();
			double new_y_noise = new_y_theoric + bruit();

			TEMP.put(0, 0, new_x_noise);
			TEMP.put(1, 0, new_y_noise);

			oldpos = bruit;
			bruit.resize(bruit.rows, bruit.columns + 1);
			//bruit.put();

			TEMP.put(0, 0, new_x_theoric);
			TEMP.put(1, 0, new_y_theoric);
		
			oldpos = positions;
			positions.resize(positions.rows, positions.columns + 1);
			//positions << oldpos, tmp;
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
