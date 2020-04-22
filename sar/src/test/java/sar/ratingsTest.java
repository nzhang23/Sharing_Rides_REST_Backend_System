package sar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ratingsTest {

	@Test
	public void testRatings() {
		
	}

	@Test
	public void testGet_driver_ratings() {
		
	}

	@Test
	public void testSet_driver_ratings() {
	
	}

	@Test
	public void testSet_rider_ratings() {
		
	}

	@Test
	public void testIsNull() {
		ratings r = new ratings();
		assertFalse(r.isNull());
	}

	@Test
	public void testGetID() {
		
	}

	@Test
	public void testGet_rid() {
		
	}

	@Test
	public void testSet_rid() {
		
	}

	@Test
	public void testGet_sent_by_id() {
		
	}

	@Test
	public void testSet_sent_by_rid() {
		
	}

	@Test
	public void testGet_rating() {
		
	}

	@Test
	public void testSet_rating() {
		
	}

	@Test
	public void testGet_comment() {
		
	}

	@Test
	public void testSet_comment() {
		ratings r = new ratings();
		r.set_comment("ff");
		assertEquals(r.get_comment(),"ff");
		
	}

	@Test
	public void testGet_date() {
		
	}

	@Test
	public void testGet_first_name() {
		
	}

	@Test
	public void testSet_first_name() {
		
	}

	@Test
	public void testMatchesId() {
		ratings r =new ratings();
		assertTrue(r.matchesId(r.getID()));
		assertFalse(r.matchesId(1000));
	}

}
