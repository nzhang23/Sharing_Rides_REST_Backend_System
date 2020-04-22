package sar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class searchTest {

	@Test
	public void testIsNull() {
		search s = new search();
		assertFalse(s.isNull());
	}

}
