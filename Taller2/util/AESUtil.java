package co.edu.unbosque.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.context.support.StaticApplicationContext;

import static org.apache.commons.codec.binary.Base64.encodeBase64;
import static org.apache.commons.codec.binary.Base64.decodeBase64;

public class AESUtil {
	
	private static final String ALGORITHM= "AES";
	
	private static final String CIPHER_TYPE = "AES/CBC/PKCS5Padding";
	public static String encrypt(String text) {
		String iv = "aghtreljsopdtew3";
		String key = "llavede16caracte";
		return encrypt(key,iv,text);
	}
	
	public static String decrypt(String text) {
		String iv = "aghtreljsopdtew3";
		String key = "llavede16caracte";
		return decrypt(key,iv,text);
	}
	
	public static String encrypt(String key, String iv, String text) {
		Cipher chipher = null;
		try {
			chipher = Cipher.getInstance(CIPHER_TYPE);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
		
		try {
			chipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,ivParameterSpec);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		
		byte [] encripted = null;
		try {
			encripted = chipher.doFinal(text.getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return new String(encodeBase64(encripted));
		
	}
	
	public static String decrypt(String key, String iv, String text) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(CIPHER_TYPE);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
		
		try {
			cipher.init(cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		
		byte [] encrypted = decodeBase64(text);
		byte [] decrypted = null;
		
		try {
			decrypted = cipher.doFinal(encrypted);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		
		return new String(decrypted);
	}
	
	public static void main(String[] args) {
		String text = "hola como estas";
		String encrypted = encrypt(text);
		System.out.println(encrypted);
		String decrypted = decrypt(encrypted);
		System.out.println(decrypted);
	}
	
	

}
