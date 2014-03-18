package nsc.ssl.crypto.ssl_06.hashing_functions;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * This password hashing example utilizes the bcrypt implementation that can be found
 * in the spring libraries. The API here has been greatly simplified. It became much
 * easier to use. The interesting things here are:
 * <ul>
 * <li>the salt and hash are concatenated and encoded to base64,
 * <li>the input and output are String objects (looks like the class has been designed
 * to be used only on the server side, because no data zeroisation is possible here),
 * <li>the cost parameter is encoded as part of the salt (see here for more info:
 * http://stackoverflow.com/questions/5881169/storing-a-hashed-password-bcrypt-in-a-database-type-length-of-column/5882472#5882472 ).
 * </ul>
 * NOTE: You might want to consider going one step further and include not only salt, but also pepper. 
 * See here for more: http://security.stackexchange.com/questions/21263/how-to-apply-a-pepper-correctly-to-bcrypt
 */
public class PasswordHashingBcrypt {

	public static final String hash(String password) {
		String salt = BCrypt.gensalt(12);
		return BCrypt.hashpw(password, salt);
	}

	public static final boolean checkPassword(String hash, String password) {
		return BCrypt.checkpw(password, hash);
	}
}
