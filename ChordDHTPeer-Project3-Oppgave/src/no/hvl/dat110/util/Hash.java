package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class Hash {

	private static BigInteger hashint;

	public static BigInteger hashOf(String entity) {

		// Task: Hash a given string using MD5 and return the result as a BigInteger.
		// we use MD5 with 128 bits digest
		// compute the hash of the input 'entity'
		// convert the hash into hex format
		// convert the hex into BigInteger
		// return the BigInteger

		byte[] entityBytes = entity.getBytes(StandardCharsets.UTF_8);
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
			System.exit(1);
		}

		byte[] hashBytes = md.digest(entityBytes);
		String hashHexString = toHex(hashBytes);
		hashint = new BigInteger(hashHexString, 16);

		return hashint;
	}

	public static BigInteger addressSize() {

		// Task: compute the address size of MD5
		// get the digest length
		// compute the number of bits = digest length * 8
		// compute the address size = 2 ^ number of bits
		// return the address size

		return BigInteger.valueOf(2).pow(bitSize());
	}

	public static int bitSize() {

		int digestlen = 0;

		// find the digest length

		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
			System.exit(1);
		}

		digestlen = md.getDigestLength();
		return digestlen*8;
	}

	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
