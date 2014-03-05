package nsc.ssl.crypto.ssl_06.hashing_functions;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingFunction {

	public static final byte[] hash(byte[] input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(input);
	}
	
	public static final byte[] hash(InputStream in) throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] buffer = new byte[1024];
		while (in.read(buffer) > 0) {
			md.update(buffer);
		}
		return md.digest();
	}
}
