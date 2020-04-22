package sar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NullRidesTest {

	@Test
	public void testIsNull() {
		assertTrue(new NullRides().isNull());
	}

	@Test
	public void testNullRides() {
		ratings r = new ratings();
		assertTrue(r.matchesId(r.getID()));
	}

}
