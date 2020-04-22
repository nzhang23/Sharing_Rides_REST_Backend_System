package sar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class join_requestTest {

	@Test
	public void testJoin_request() {
	}

	@Test
	public void testIsNull() {
		join_request jr = new join_request();
		assertFalse(jr.isNull());
	}

}
