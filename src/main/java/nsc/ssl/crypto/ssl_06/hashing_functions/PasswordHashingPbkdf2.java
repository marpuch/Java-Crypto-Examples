package nsc.ssl.crypto.ssl_06.hashing_functions;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

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
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
		return salt;
	}
}
