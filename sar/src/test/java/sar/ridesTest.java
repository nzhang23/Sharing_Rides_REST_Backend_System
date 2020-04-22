package sar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ridesTest {

	@Test
	public void testRides() {
	}

	@Test
	public void testMatchesId() {
		rides r = new rides();
		assertTrue(r.matchesId(r.get_rid()));
	}

	@Test
	public void testGet_rid() {
		
	}

	@Test
	public void testGet_location() {
		
	}

	@Test
	public void testSet_location() {
		
	}

	@Test
	public void testGet_car() {
		
	}

	@Test
	public void testSet_car() {
		
	}

	@Test
	public void testGet_date() {
		
	}

	@Test
	public void testSet_date() {
		
	}

	@Test
	public void testGet_aid() {
		
	}

	@Test
	public void testSet_aid() {
		
	}

	@Test
	public void testGet_max_passengers() {
		
	}

	@Test
	public void testSet_max_passengers() {
		
	}

	@Test
	public void testGet_amount_per_passengers() {
		
	}

	@Test
	public void testSet_amount_per_passengers() {
		
	}

	@Test
	public void testGet_conditions() {
		
	}

	@Test
	public void testSet_conditions() {
		
	}

	@Test
	public void testAdd_list_join_request() {
		  rides r = new rides();
		  join_request jr = new join_request();
		  r.add_list_join_request(jr);
		  assertEquals(r.find_in_list_join_requests(jr.jid), jr);
		  
	}

	@Test
	public void testGet_list_join_request() {
	}

	@Test
	public void testFind_in_list_join_requests() {
		  rides r = new rides();
		  join_request jr = new join_request();
		  r.get_list_join_request().clear();
		  r.add_list_join_request(jr);
		  assertEquals(jr, r.find_in_list_join_requests(jr.jid));
		  assertTrue(r.find_in_list_join_requests(1000).isNull());
	}

	@Test
	public void testAdd_list_messages() {
		rides r = new rides();
	    messages m = new messages();
	    r.add_list_messages(m);
	    assertEquals( r.get_list_messages().get(0), m);
	}

	@Test
	public void testGet_list_messages() {
		
	}

	@Test
	public void testIsNull() {
		 rides r = new rides();
		 assertFalse(r.isNull());
	}

}
