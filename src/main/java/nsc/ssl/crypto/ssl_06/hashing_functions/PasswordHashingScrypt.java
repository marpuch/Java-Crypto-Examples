package nsc.ssl.crypto.ssl_06.hashing_functions;

import com.lambdaworks.crypto.SCryptUtil;

public class PasswordHashingScrypt {

	public static final String hash(String password) {
		// http://stackoverflow.com/questions/11126315/what-are-optimal-scrypt-work-factors
		return SCryptUtil.scrypt(password, 65536, 8, 1);
	}

	public static final boolean checkPassword(String hash, String password) {
		return SCryptUtil.check(password, hash);
	}
}
