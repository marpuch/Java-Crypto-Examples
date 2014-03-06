package nsc.ssl.crypto.ssl_06.hashing_functions;

public class Pbkdf2Bean {

	private byte[] salt;
	private byte[] hash;
	private int iterations;

	public Pbkdf2Bean(byte[] salt, byte[] hash, int iterations) {
		super();
		this.salt = salt;
		this.hash = hash;
		this.iterations = iterations;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public byte[] getHash() {
		return hash;
	}

	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public int getLengthBytes() {
		return hash == null? 0 : hash.length*8;
	}

}
