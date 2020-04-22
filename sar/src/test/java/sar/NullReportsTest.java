package sar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NullReportsTest {

	@Test
	public void testIsNull() {
		String s="ss";
		int pid=907;
		assertTrue(new NullReports(pid,s).isNull());
	}

	@Test
	public void testNullReports() {
		
	}

}
