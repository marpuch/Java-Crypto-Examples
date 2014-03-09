package nsc.ssl.crypto.ssl_06.hashing_functions;

import junit.framework.TestCase;

public class PasswordHashingScryptTest extends TestCase {

	private static final String password = "ala ma kota";
	
	public void testHash() {
		String hash = PasswordHashingScrypt.hash(password);
		System.out.println("======================");
		System.out.println("Hash: " + hash);
	}

	public void testCheckPassword() {
		String hash = PasswordHashingScrypt.hash(password);
		assertTrue(PasswordHashingScrypt.checkPassword(hash, password));
		assertFalse(PasswordHashingScrypt.checkPassword(hash, password+"a"));
	}

}
