package com.pyramid.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class PasswordProtector {
	
	private static final char[] PASSWORD = "".toCharArray();
    private static final byte[] SALT = {};

  /*  public static void main(String[] args)
    {
        
        String token = OTPUtil.generateToken();
        String originalPassword = "admin.dummy@medrep.com";
        String newToken = originalPassword + "MEDREP" + token;
        
        System.out.println("Original password: " + newToken);
        String encryptedPassword = encrypt(newToken);
        System.out.println("Encrypted password: " + encryptedPassword);
        String decryptedPassword = decrypt(encryptedPassword);
        System.out.println(decryptedPassword);
        StringTokenizer tokenizer = new StringTokenizer(decryptedPassword,"MEDREP");
        String emailId = tokenizer.nextToken();
        String decToken = tokenizer.nextToken();
        System.out.println("Decrypted password: " + decToken);
        System.out.println("Decrypted Id: " + emailId);
    	
    	String token = "Test123";
    	String encodedToken = PasswordProtector.base64Encode(token.getBytes());
    	System.out.println(encodedToken);
    	System.out.println("Encoded Password " + encodedToken);
    	String decodedToken = new String(PasswordProtector.base64Decode(encodedToken));
    	System.out.println("Decoded Password " + decodedToken);
        
    }
*/
    public static void main(String[] args) {
		System.out.println(encrypt("Test123"));
		
	}
    public static String encrypt(String property)
    {
    	String encodedString = "";
    	try
    	{
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
	        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
	        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
	        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
	        encodedString = base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        return encodedString;
    }

    public static String base64Encode(byte[] bytes) {
        // NB: This class is internal, and you probably should use another impl
        return new String(Base64.encodeBase64(bytes));
    }

    public static String decrypt(String property) 
    {
    	String encodedString = "";
    	try
    	{
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
	        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
	        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
	        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
	        encodedString = new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        return encodedString;
    }

    public static byte[] base64Decode(String property) {
        return Base64.decodeBase64(property.getBytes());
    }

    
}
