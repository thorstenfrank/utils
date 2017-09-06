/*
 *  Copyright 2017 Thorsten Frank (accounting@tfsw.de).
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package de.tfsw.utils;

/**
 * Converts between arbitrary-length byte arrays and integers.
 * 
 * <p>There is no checking for max values!</p>
 * 
 * @author Thorsten Frank
 */
public final class IntegerByteConverter {
	
	private static final int OCTET = 8;
	
	private static final int BYTE_MASK = 0xFF;
	
	/**
	 * 
	 */
	private IntegerByteConverter() {
		
	}
	
	/**
	 * Converts an array of bytes into an integer representation.
	 * 
	 * <p>The intention is to provide a way to convert a byte array that is shorter than 4 elements.</p>
	 * 
	 * <p>This method makes no assumption as to the length of the byte array. Beware that providing an array longer 
	 * than 4 does not mean you can convert a value larger than {@link Integer#MAX_VALUE}!</p>
	 * 
	 * @param bytes the byte array to convert
	 * 
	 * @return the integer value of the bytes
	 */
	public static int bytesToInt(final byte[] bytes) {
		int val = 0;
		
		for (byte b : bytes) {
			val <<= OCTET;
			val = val | (b & BYTE_MASK);
		}
		return val;
	}
	
	/**
	 * Converts an integer value into an arbitrary-length byte array.
	 * 
	 * <p>The intention is to provide a way to convert a byte array that is shorter than 4 elements.</p> 
	 * 
	 * <p>This method makes no assumption as to the length of the requested byte array. Asking to convert a value into
	 * an array that is too short is not validated! Instead, you will receive an "overflow" - e.g. trying to convert 
	 * 256 into a single byte will result in 0.</p>
	 * 
	 * @param value the integer value to convert
	 * @param size the length of the resulting byte array
	 * @return a byte array representing the integer value
	 */
	public static byte[] toBytes(final int value, final int size) {
		byte[] result = new byte[size];
		int temp = value;
		for (int i=size-1; i >= 0; i--) {
			result[i] = (byte) (temp & BYTE_MASK);
			temp >>= OCTET;
		}
		
		return result;
	}
	
}
