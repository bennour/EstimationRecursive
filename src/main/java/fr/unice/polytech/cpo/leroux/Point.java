package fr.unice.polytech.cpo.leroux;

import org.jblas.DoubleMatrix;

public class Point {
	
	protected DoubleMatrix positions;
	protected double x0, y0;
	
	public Point(double x0, double y0){
		this.x0 = x0;
		this.y0 = y0;
		
		positions = new DoubleMatrix(2, 1);
	}
	
	public void init() {
		positions.put(0, 0, x0);
		positions.put(1, 0, y0);
	}
	
	public double x(int t) { return positions.get(0, t); }
	public double y(int t) { return positions.get(1, t); }

	public DoubleMatrix getPositions() { return positions; }
	public void setPositions(DoubleMatrix positions) { this.positions = positions; }

	public double getY0() { return y0; }
	public void setY0(double y0) { this.y0 = y0; }

	public double getX0() { return x0; }
	public void setX0(double x0) { this.x0 = x0; }
}
