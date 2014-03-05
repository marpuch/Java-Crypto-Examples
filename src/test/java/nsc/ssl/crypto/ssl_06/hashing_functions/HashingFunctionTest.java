package nsc.ssl.crypto.ssl_06.hashing_functions;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import junit.framework.TestCase;
import sun.misc.BASE64Encoder;

public class HashingFunctionTest extends TestCase {

	private static final String text = 
			"Litwo! Ojczyzno moja! ty jesteś jak zdrowie.\n" +
			"Ile cię trzeba cenić, ten tylko się dowie,\n" +
			"Kto cię stracił. Dziś piękność twą w całej ozdobie\n" +
			"Widzę i opisuję, bo tęsknię po tobie.";
	
	public void testHashByteArray() throws NoSuchAlgorithmException {
		System.out.println("======================");
		byte[] hash = HashingFunction.hash(text.getBytes());
		System.out.println("Text: " + text);
		System.out.println("Hash: " + new BASE64Encoder().encode(hash));
	}

	public void testHashInputStream() throws IOException, NoSuchAlgorithmException {
		System.out.println("======================");
		String inFilename = "./input/pan_tadeusz.txt";
		InputStream in = new BufferedInputStream(new FileInputStream(inFilename));
		byte[] hash = HashingFunction.hash(in);
		
		System.out.println("Input: " + inFilename);
		System.out.println("Hash:  " + new BASE64Encoder().encode(hash));
		
		in.close();
	}

}
