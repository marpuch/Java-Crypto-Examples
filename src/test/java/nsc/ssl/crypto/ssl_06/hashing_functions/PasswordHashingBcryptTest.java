package nsc.ssl.crypto.ssl_06.hashing_functions;

import junit.framework.TestCase;

public class PasswordHashingBcryptTest extends TestCase {

	private static final String password = "ala ma kota";
	
	public void testHash() {
		String hash = PasswordHashingBcrypt.hash(password);
		System.out.println("======================");
		System.out.println("Hash: " + hash);
	}

	public void testCheckPassword() {
		String hash = PasswordHashingBcrypt.hash(password);
		assertTrue(PasswordHashingBcrypt.checkPassword(hash, password));
		assertFalse(PasswordHashingBcrypt.checkPassword(hash, password+"a"));
	}

}
