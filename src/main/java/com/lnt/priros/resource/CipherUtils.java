package com.lnt.priros.resource;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.StringUtils;

public class CipherUtils {

    public final static String SHA256 = Constant.SHA256;
	public final static String ALGORITHM_AES = Constant.ALGORITHM_AES;
	public final static String TRANSFORMATION_PKCS5 = Constant.TRANSFORMATION_PKCS5;
	
    public static String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }    

    public static String getHexEncodeString(byte[] byteArray) {
        StringBuilder hexStringBuffer = new StringBuilder();
		for (byte b : byteArray) {
			hexStringBuffer.append(byteToHex(b));
		}
        return hexStringBuffer.toString();
    }    

	public static byte[] hash(String algorithm, byte[] input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(algorithm);
		md.update(input);
		return md.digest();
	}

	public static String hash(byte[] input) throws Exception {
		MessageDigest md = MessageDigest.getInstance(SHA256);
		md.update(input);
		return Arrays.toString(md.digest());
	}

	public static String hashBySHA256(byte[] input) throws NoSuchAlgorithmException {
		return getHexEncodeString(hash(SHA256, input));
	}

	public static String hashBySHA256(String str) {
		if (StringUtils.isEmpty(str))
			return "";
		try {
			return hashBySHA256(str.getBytes());
		} catch (NoSuchAlgorithmException e) {
			return str;
		}
	}

	public static String bytesToBase64Encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);		
	}

	public static byte[] base64DecodedToBytes(String src) {
		return Base64.getDecoder().decode(src);
	}

	public static byte[] decryptAES(byte[] key, byte[] iv, byte[] encripted) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(TRANSFORMATION_PKCS5);		
		SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM_AES);
		IvParameterSpec ivParamSpec = new IvParameterSpec(iv, 0, 16);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
		return cipher.doFinal(encripted);
	}	

	public static byte[] encryptAES(byte[] key, byte[] iv, byte[] source) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(TRANSFORMATION_PKCS5);
		SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM_AES);
		IvParameterSpec ivParamSpec = new IvParameterSpec(iv, 0, 16);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec); 
		return cipher.doFinal(source);
	}

	public static byte[] encryptAES(byte[] key, byte[] iv, String planeText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return encryptAES(key, iv, planeText.getBytes());
	}
    
	public static byte[] generateSecurityKeyBytes() throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom();
		byte[] bytes = new byte[32];
		secureRandom.nextBytes(bytes);
		return bytes;
	}

}
