package fr.unice.polytech.cpo.leroux;

public class Mobile extends Point{
	
	private final double vx, vy;
	
	public Mobile(double x0, double y0, double vx, double vy) {
		super(x0, y0);
		this.vx = vx;
		this.vy = vy;
	}
	
	public void updatePosition(int t) {
		positions.resize(2, t);
		for(int i = 1; i < t; i++) {
			positions.put(0, i, x0 + vx * i);
			positions.put(1, i, x0 + vy * i);
		}
	}
	
	public double getVx() { return vx; }
	public double getVy() { return vy; }
}
