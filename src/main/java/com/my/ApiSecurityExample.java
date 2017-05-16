package com.my;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class ApiSecurityExample {
	public static void main(String[] args) {
		try {
			String secret = "e94480edaeedee3ea4e02dfd3597e1a0666e8ce5";
			String message = "PUT&https%3A%2F%2Fapi.pinterest.com%2Fv3%2Foauth%2Fcode_exchange%2F&client_id=1447555&code=44cdf7bc534c8852b2df236533862cde28cee997&consumer_id=1447555&grant_type=authorization_code&redirect_uri=https%3A%2F%2Fprod-env.ktspk3mupv.us-east-1.elasticbeanstalk.com%2Fcallback&timestamp=1492865217";

			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
			sha256_HMAC.init(secret_key);

			String hash = bytesToHex(sha256_HMAC.doFinal(message.getBytes()));
			System.out.println(hash.toLowerCase());
		}
		catch (Exception e){
			System.out.println("Error");
		}
	}

	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for ( int j = 0; j < bytes.length; j++ ) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
}
