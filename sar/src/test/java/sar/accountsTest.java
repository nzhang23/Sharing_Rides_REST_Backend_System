package sar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class accountsTest {
   
	@Test
	public void testAccounts() {
	}

	@Test
	public void testAddRatings() {
			accounts l = new accounts();
            ratings r = new ratings();
            assertTrue(l.get_detail().add(r));
	}

	@Test
	public void testIsNull() {
		accounts l = new accounts();
		assertFalse(l.isNull());
	}

	@Test
	public void testGet_aid() {
		
	}

	@Test
	public void testGet_first_name() {
		
	}

	@Test
	public void testSet_first_name() {
		
	}

	@Test
	public void testGet_last_name() {
		
	}

	@Test
	public void testSet_last_name() {
		
	}

	@Test
	public void testGet_phone() {
		
	}

	@Test
	public void testSet_phone() {
		
	}

	@Test
	public void testGet_picture() {
		
	}

	@Test
	public void testSet_picture() {
		
	}

	@Test
	public void testGet_status() {
		
	}

	@Test
	public void testSet_status_active() {
		
	}

	@Test
	public void testSet_status_inactive() {
	}

	@Test
	public void testGet_date_created() {
		
	}

	@Test
	public void testGet_rides() {
		
	}

	@Test
	public void testSet_rides() {
		
	}

	@Test
	public void testGet_ratings() {
		
	}

	@Test
	public void testSet_ratings() {
		
	}

	@Test
	public void testGet_detail() {
		
	}

	@Test
	public void testAdd_detail() {
		accounts l = new accounts();
		ratings r = new ratings();
		ratings b = new ratings();
		double f = (4+2)/2;
		r.set_rating(4);
		b.set_rating(2);
		assertTrue(l.add_detail(r)); 
        assertTrue(l.add_detail(b)); 
        assertEquals(l.get_ratings(), 2);
	}

	@Test
	public void testGet_average_rating() {
		
	}

	@Test
	public void testSet_average_rating() {
		accounts l = new accounts();
		ratings r = new ratings();
		ratings b = new ratings();
		double DELTA = 1e-15;
		double f = (4+4)/2;
		r.set_rating(4);
		b.set_rating(4);
		l.AddRatings(r);
		l.AddRatings(b);
		l.set_average_rating();
		assertEquals(l.get_average_rating(),f, DELTA);
       
	}

	@Test
	public void testMatchesId() {
		accounts l = new accounts();
		assertTrue(l.matchesId(l.get_aid()));
		
	}

}
