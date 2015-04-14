package fr.unice.polytech.cpo.leroux;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimulateurTest {
	private Simulateur simulateur;
	private Observeur observeur;
	private Mobile mobile;

	@Before
	public void setUp() throws Exception {
		observeur = new Observeur(0, 0, 0, 10);
		mobile = new Mobile(0, 0, 0, 0);
		simulateur = new Simulateur(observeur, mobile);
	}

	@After
	public void tearDown() throws Exception {
		observeur = null;
		mobile = null;
		simulateur = null;
	}

	@Test
	public void testSimulateur() {
		assertNotNull(simulateur);
	}

	@Test
	public void testResolutionParametres() {
		simulateur.resolutionParametres();
		System.out.println(simulateur.getVx() + " " + simulateur.getVy());
	}

}
