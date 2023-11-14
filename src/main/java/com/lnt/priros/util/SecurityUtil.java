package com.lnt.priros.util;

import com.lnt.priros.resource.Constant;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

@Slf4j
public class SecurityUtil {

    public final static String SHA256 = Constant.SHA256;
    public final static String ALGORITHM_AES = Constant.ALGORITHM_AES;
    public final static String TRANSFORMATION_PKCS5 = Constant.TRANSFORMATION_PKCS5;
    public static final byte[] SECURITY_KEY = "z!@pf#&*f+_IDK$%f+d345pa54*^#yhR".getBytes();

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
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] base64DecodedToBytes(String src) {
        return java.util.Base64.getDecoder().decode(src);
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

    public static String encrypt(String input, byte[] securityKeyBytes) throws IOException {
        if (StringUtils.isEmpty(input) || securityKeyBytes.length == 0) return "";
        return Base64.encodeBase64String(encrypt(input.getBytes(), securityKeyBytes));
    }

    public static byte[] encrypt(byte[] input, byte[] securityKeyBytes) throws IOException {
        try {
            return getSymmetricCipher(Cipher.ENCRYPT_MODE, securityKeyBytes).doFinal(input);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IOException(e.getMessage());
        }
    }

    public static String encryptSecurityKeyString(byte[] securityKeyBytes) throws IOException {
        try {
            return Base64.encodeBase64String(getSymmetricCipher(Cipher.ENCRYPT_MODE, SECURITY_KEY).doFinal(securityKeyBytes));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IOException(e.getMessage());
        }
    }

    public static String decrypt(String input, byte[] securityKeyBytes) throws IOException {
        if (StringUtils.isEmpty(input) || securityKeyBytes.length == 0) return "";
        return new String(decrypt(Base64.decodeBase64(input), securityKeyBytes));
    }

    public static byte[] decrypt(byte[] input, byte[] securityKeyBytes) throws IOException {
        try {
            return getSymmetricCipher(Cipher.DECRYPT_MODE, securityKeyBytes).doFinal(input);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IOException(e.getMessage());
        }
    }

    public static byte[] decryptSecurityKeyBytes(String encryptSecurityKeyString) throws IOException {
        try {
            return getSymmetricCipher(Cipher.DECRYPT_MODE, SECURITY_KEY).doFinal(Base64.decodeBase64(encryptSecurityKeyString));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IOException(e.getMessage());
        }
    }

    public static Cipher getSymmetricCipher(int opMode, byte[] securityKeyBytes) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION_PKCS5);
        cipher.init(opMode, new SecretKeySpec(securityKeyBytes, ALGORITHM_AES), new IvParameterSpec(hash(SHA256, securityKeyBytes), 0, 16));
        return cipher;
    }

    public static byte[] getNewSecurityKeyBytes() {
        try {
            return SecurityUtil.generateSecurityKeyBytes();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String getNewSecurityKey(byte[] securityKeyBytes) throws IOException {
        return SecurityUtil.encryptSecurityKeyString(securityKeyBytes);
    }
}
