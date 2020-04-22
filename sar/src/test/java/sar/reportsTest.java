package sar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class reportsTest {

	@Test
	public void testReports() {
		
	}

	@Test
	public void testGet_pid() {
		
	}

	@Test
	public void testGet_name() {
		
	}

	@Test
	public void testGet_start_date() {
		reports r = new reports(907,"ff");
		r.set_start_date("sss");
		assertEquals(r.get_start_date(), "sss");
	}

	@Test
	public void testSet_start_date() {
		
	}

	@Test
	public void testGet_end_date() {
		reports r = new reports(907,"ff");
		r.set_end_date("sss");
		assertEquals(r.get_end_date(), "sss");
	}

	@Test
	public void testSet_end_date() {
		
	}

	@Test
	public void testGet_rides() {
		
	}

	@Test
	public void testSet_rides() {
		
	}

	@Test
	public void testGet_report_detail() {
		
	}

	@Test
	public void testAdd_in_list() {
		reports r =new reports(907, "f");
		report_detail rd = new report_detail();
		assertTrue(r.add_in_list(rd));
	}

	@Test
	public void testEmpty_list() {
		reports r =new reports(907, "f");
		report_detail rd = new report_detail();
		r.add_in_list(rd);
		r.empty_list();
		assertTrue(r.get_report_detail().isEmpty());
	}

	@Test
	public void testIsNull() {
		reports r = new reports(907,"f");
		assertFalse(r.isNull());
	}

}
