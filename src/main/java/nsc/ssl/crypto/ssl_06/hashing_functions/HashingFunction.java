package nsc.ssl.crypto.ssl_06.hashing_functions;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * This class presents some examples about how to use hashing functions in Java
 * over the JCA API.
 */
public class HashingFunction {

	/**
	 * <p>Creates a digest of the given byte input.
	 * <p>NOTE: The usual case of using any cryptographic primitives in Java
	 * involves passing input parameters as byte/char arrays (not Strings or any
	 * other immutable objects). Get used to it. The problem with immutable objects
	 * is that their content can not be overwritten before the object is passed
	 * the garbage collector. This means that potentially sensitive data remains
	 * in the applications stack (and even worse - can be swapped to disk).
	 * That's why the most crypto libraries accept only arrays as input.
	 * 
	 * @see http://en.wikipedia.org/wiki/Zeroisation
	 *
	 * @param input Byte input.
	 * @return Digest.
	 * @throws NoSuchAlgorithmException
	 */
	public static final byte[] hash(byte[] input) throws NoSuchAlgorithmException {
		// Not much things you can do wrong here. Just be sure not to use
		// MD5 or SHA-1 any more. You can use the SHA-2 family with SHA-256, SHA-384 or
		// SHA-512 (the number is the length of the digest in bytes).
		// See also: http://valerieaurora.org/hash.html
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(input);
	}
	
	/**
	 * Creates a diges for the given input stream.
	 * 
	 * @param in Input stream.
	 * @return Digest.
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static final byte[] hash(InputStream in) throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] buffer = new byte[1024];
		for (int length = in.read(buffer); length > 0; length = in.read(buffer)) {
			md.update(buffer, 0, length);
		}
		// Since we do not know what kind of data the function will be handling, we should treat
		// the data as sensitive and overwrite all buffer variables we have created before
		// releasing the object for garbage collection.
		Arrays.fill(buffer, (byte)0);
		return md.digest();
	}
}
