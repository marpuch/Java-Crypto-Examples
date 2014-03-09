package nsc.ssl.crypto.ssl_06.hashing_functions;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordHashingBcrypt {

	public static final String hash(String password) {
		String salt = BCrypt.gensalt(12);
		return BCrypt.hashpw(password, salt);
	}

	public static final boolean checkPassword(String hash, String password) {
		return BCrypt.checkpw(password, hash);
	}
}
