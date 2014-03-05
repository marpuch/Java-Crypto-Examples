package nsc.ssl.crypto.helper;

import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Lists all known providers and all known services as console output.
 *
 * @author Marek Puchalski
 */
public class ListAllProviderCapabilities {

	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		
		for (Provider provider : Security.getProviders()) {
			System.out.println("************************************ " + provider);
			listAllServices(provider);
		}
	}

	private static void listAllServices(Provider provider) {
		for (Service service : provider.getServices()) {
			System.out.println(service);
		}
	}
}
