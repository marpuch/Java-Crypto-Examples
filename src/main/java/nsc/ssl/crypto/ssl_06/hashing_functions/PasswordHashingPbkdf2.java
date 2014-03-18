package nsc.ssl.crypto.ssl_06.hashing_functions;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * <p>This class shows an example of how to use a key derivation function like PBKDF2
 * for storing and verifying user passwords. This is the usual case people have
 * while implementing user authentication (over passwords) in the systems. There are
 * multiple pro and cons why you would (or wouldn't) like to use PBKDF2 for this reason.
 * 
 * <p>PRO:
 * <ul>
 * <li>PBKDF2 is a NIST standard,
 * <li>you can find an implementation of this function in the core jre libraries (no other
 * libraries required),
 * <li>it is a KDF, so it provides (unlike hashing functions) a clean way to include salt
 * in the hashing process.
 * </ul>
 * <p>CON:
 * <ul>
 * <li>you can use GPUs to accelerate the hashing process by a great deal. This is a serious
 * flaw. Password hashing functions are designed to be SLOW and GPUs are one of the cheapest
 * ways to speed up the hashing process.
 * </ul>
 * 
 * NOTE: You might want to consider going one step further and include not only salt, but also pepper. 
 * See here for more: http://security.stackexchange.com/questions/21263/how-to-apply-a-pepper-correctly-to-bcrypt
 * 
 */
public class PasswordHashingPbkdf2 {

	public static final Pbkdf2Bean hash(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return hash(password, generateSalt(), 1024, 128);
	}
	
	private static final Pbkdf2Bean hash(char[] password, byte[] salt, int iterations, int length) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterations, length);
		Key key = keyFactory.generateSecret(keySpec);
		return new Pbkdf2Bean(salt, key.getEncoded(), keySpec.getIterationCount());
	}

	public static final boolean checkPassword(Pbkdf2Bean hash, char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Pbkdf2Bean hashedPassword = hash(password, hash.getSalt(), hash.getIterations(), hash.getLengthBytes());
		return Arrays.equals(hashedPassword.getHash(), hash.getHash());
	}

	private static byte[] generateSalt() {
		byte[] salt = new byte[16];
		// NOTE: Never ever use javas Random for crypto.
		// Use SecureRandom instead.
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
		return salt;
	}
}
