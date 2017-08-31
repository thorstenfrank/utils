package de.tfsw.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for {@link IntegerByteConverter}.
 *
 */
public class IntegerByteConverterTest {

	/**
	 * 
	 */
	@Test
	public void testToAndFrom() {
		assertEquals(17,IntegerByteConverter.bytesToInt(IntegerByteConverter.toBytes(17,1)));
		assertEquals(17,IntegerByteConverter.bytesToInt(IntegerByteConverter.toBytes(17,3)));
		assertEquals(17,IntegerByteConverter.bytesToInt(IntegerByteConverter.toBytes(17,5)));
		
		// overflow
		assertEquals(0,IntegerByteConverter.bytesToInt(IntegerByteConverter.toBytes(256,1)));
		
		assertEquals(256,IntegerByteConverter.bytesToInt(IntegerByteConverter.toBytes(256,2)));
		
		assertEquals(256,IntegerByteConverter.bytesToInt(IntegerByteConverter.toBytes(256,10)));
	}
}
