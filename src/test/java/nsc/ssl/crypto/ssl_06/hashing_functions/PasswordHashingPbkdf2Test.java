package nsc.ssl.crypto.ssl_06.hashing_functions;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import junit.framework.TestCase;
import sun.misc.BASE64Encoder;

public class PasswordHashingPbkdf2Test extends TestCase {

	private static final String PASSWORD = "ala ma kota";

	public void testHash() throws NoSuchAlgorithmException, InvalidKeySpecException {
		Pbkdf2Bean result = PasswordHashingPbkdf2.hash(PASSWORD.toCharArray());
		
		BASE64Encoder encoder = new BASE64Encoder();
		System.out.println("======================");
		System.out.println("Salt: " + encoder.encode(result.getSalt()));
		System.out.println("Hash: " + encoder.encode(result.getHash()));
		System.out.println("Iter: " + result.getIterations());
	}

	public void testCheckPassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
		Pbkdf2Bean result = PasswordHashingPbkdf2.hash(PASSWORD.toCharArray());
		assertTrue(PasswordHashingPbkdf2.checkPassword(result, PASSWORD.toCharArray()));
		assertFalse(PasswordHashingPbkdf2.checkPassword(result, (PASSWORD+"a").toCharArray()));
	}
}
