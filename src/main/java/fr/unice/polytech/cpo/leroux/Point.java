package fr.unice.polytech.cpo.leroux;

import org.jblas.DoubleMatrix;

public class Point {
	
	protected DoubleMatrix positions;
	
	public Point(double x0, double y0){
		positions = new DoubleMatrix(2, 1);
		
		positions.put(0, 0, x0);
		positions.put(1,  0, y0);
	}
	
	public double x(int t) {
		return positions.get(0, t);
	}
	
	public double y(int t) {
		return positions.get(1, t);
	}

	public DoubleMatrix getPositions() {
		return positions;
	}

	public void setPositions(DoubleMatrix positions) {
		this.positions = positions;
	}
}
