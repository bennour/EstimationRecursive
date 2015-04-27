package fr.unice.polytech.cpo.leroux;

import org.jblas.DoubleMatrix;

public class Mobile extends Point{
	
	private double vx, vy;
	
	public Mobile(double x0, double y0, double vx, double vy) {
		super(x0, y0);
		this.vx = vx;
		this.vy = vy;
	}
	
	public void genererPosition(int t) {
		positions.resize(2, t);
		init();
		
		for(int i = 1; i < t; i++) {
			positions.put(0, i, x0 + vx * i);
			positions.put(1, i, x0 + vy * i);
		}
		
	}
	
	public DoubleMatrix resulatsTheorique() {
		DoubleMatrix res = new DoubleMatrix(4, 1);
		
		res.put(0, 0, x0);
		res.put(1, 0, y0);
		res.put(2, 0, vx);
		res.put(3, 0, vy);
		
		return res;
	}
	
	public double getVx() { return vx; }
	public double getVy() { return vy; }
}
