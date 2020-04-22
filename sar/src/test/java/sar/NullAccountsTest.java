package sar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



public class NullAccountsTest {

	@Test
	public void testIsNull() {
		assertTrue(new NullAccounts().isNull());
	}

}
