package fr.unice.polytech.cpo.leroux;

public class Mobile extends Point{
	
	private final double vx, vy;
	
	public Mobile(double x0, double y0, double vx, double vy) {
		super(x0, y0);
		this.vx = vx;
		this.vy = vy;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public void avancer(int t) {
		double xt = positions.get(0, 0) + vx * t;
		double yt = positions.get(1, 0) + vy * t;
		
		positions.resize(positions.rows, positions.columns++);
		
		positions.put(0, t, xt);
		positions.put(1, t, yt);
	}
}
