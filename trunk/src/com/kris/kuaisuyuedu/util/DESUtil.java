package com.kris.kuaisuyuedu.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/***
 * 超级记忆吧安卓手机版 注册码算法
 * @author kris lau
 *
 */

public class DESUtil {
	public static byte[] iv = { 8, 7, 1, 5, 3, 2, 4, 6 };
	public static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
			.toCharArray();

	public static int decode(char paramChar) {
		if ((paramChar >= 'A') && (paramChar <= 'Z')) {
			return paramChar - 'A';
		}

		if ((paramChar >= 'a') && (paramChar <= 'z')) {
			return paramChar - 'a' + 26;
		} else if ((paramChar >= '0') && (paramChar <= '9')) {
			return paramChar - '0' + 26 + 26;
		}

		switch (paramChar) {
		case '+':
			return 62;
		case '/':
			return 63;
		case '=':
			return 0;
		default:
			throw new RuntimeException("unexpected code:" + paramChar);
		}
	}

	public static void decode(String paramString, OutputStream paramOutputStream)
    throws IOException
  {
    int i = 0;
    int j = paramString.length();
    
    for (i=0;i<j;i++) {
		if ((paramString.charAt(i) > ' ')) {
			int k = (decode(paramString.charAt(i)) << 18) + (decode(paramString.charAt(i + 1)) << 12) + (decode(paramString.charAt(i + 2)) << 6) + decode(paramString.charAt(i + 3));
            paramOutputStream.write(k >> 16 & 0xFF);
            if (paramString.charAt(i + 2) == '=') {
            	return;
			}
            paramOutputStream.write(k >> 8 & 0xFF);
            if (paramString.charAt(i + 3) == '=') {
				return;
			}
            paramOutputStream.write(k & 0xFF);
            i += 3;
		}
    }
    
  }

	public static byte[] decode(String paramString) {
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		byte[] arrayOfByte = null;
		try {
			decode(paramString, localByteArrayOutputStream);
			arrayOfByte = localByteArrayOutputStream.toByteArray();
			localByteArrayOutputStream.close();
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}
		return arrayOfByte;
	}

	public static String decrypt(String paramString1, String paramString2) {
		byte[] arrayOfByte = paramString1.getBytes();
		byte[] arrayOfByte2 = null;
		try {
			SecureRandom localSecureRandom = new SecureRandom();
			DESKeySpec localDESKeySpec = new DESKeySpec(paramString2.getBytes());
			SecretKey localSecretKey = SecretKeyFactory.getInstance("DES")
					.generateSecret(localDESKeySpec);
			Cipher localCipher = Cipher.getInstance("DES");
			localCipher.init(2, localSecretKey, localSecureRandom);
			arrayOfByte2 = localCipher.doFinal(arrayOfByte);
		} catch (InvalidKeyException localInvalidKeyException) {
			localInvalidKeyException.printStackTrace();
		} catch (NoSuchAlgorithmException noSuchAlgorithmException) {
			noSuchAlgorithmException.printStackTrace();
		} catch (InvalidKeySpecException invalidKeySpecException) {
			invalidKeySpecException.printStackTrace();
		} catch (NoSuchPaddingException noSuchPaddingException) {
			noSuchPaddingException.printStackTrace();
		} catch (IllegalBlockSizeException illegalBlockSizeException) {
			illegalBlockSizeException.printStackTrace();
		} catch (BadPaddingException badPaddingException) {
			badPaddingException.printStackTrace();
		}
		return new String(arrayOfByte2);
	}

	public static String decryptDES(String paramString1, String key)
			throws Exception {
		byte[] arrayOfByte = decode(paramString1);
		IvParameterSpec localIvParameterSpec = new IvParameterSpec(iv);
		SecretKeySpec localSecretKeySpec = new SecretKeySpec(
				key.getBytes(), "DES");
		Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5PADDING");
		localCipher.init(2, localSecretKeySpec, localIvParameterSpec);
		return new String(localCipher.doFinal(arrayOfByte));
	}

	public static String encode(byte[] paramArrayOfByte) {
		int i = paramArrayOfByte.length;
		StringBuffer localStringBuffer = new StringBuffer(
				3 * paramArrayOfByte.length / 2);
		int j = i - 3;
		int k = 0;
		int m = 0;
//		for (m = 0; m < i; m++) {
//			
//		}
		
		for (;;) {
		if (k > j) {
			if (k != -2 + (0 + i)) {
				if (k == -1 + (0 + i)) {
					int i2 = (0xFF & paramArrayOfByte[k]) << 16;
					localStringBuffer.append(legalChars[(0x3F & i2 >> 18)]);
					localStringBuffer.append(legalChars[(0x3F & i2 >> 12)]);
					localStringBuffer.append("==");
					break;
				}else{
					break;
				}
			}
			int i3 = (0xFF & paramArrayOfByte[k]) << 16
					| (0xFF & paramArrayOfByte[(k + 1)]) << 8;
			localStringBuffer.append(legalChars[(0x3F & i3 >> 18)]);
			localStringBuffer.append(legalChars[(0x3F & i3 >> 12)]);
			localStringBuffer.append(legalChars[(0x3F & i3 >> 6)]);
			localStringBuffer.append("=");
			break;
		}else{
			int n = (0xFF & paramArrayOfByte[k]) << 16
					| (0xFF & paramArrayOfByte[(k + 1)]) << 8 | 0xFF
					& paramArrayOfByte[(k + 2)];
			localStringBuffer.append(legalChars[(0x3F & n >> 18)]);
			localStringBuffer.append(legalChars[(0x3F & n >> 12)]);
			localStringBuffer.append(legalChars[(0x3F & n >> 6)]);
			localStringBuffer.append(legalChars[(n & 0x3F)]);
			k += 3;
			int i1 = m + 1;
			if (m >= 14) {
				i1 = 0;
				localStringBuffer.append(" ");
			}
			m = i1;
		}
		}
		return localStringBuffer.toString();
	}

	public static String encrypt(String paramString1, String paramString2) {
		byte[] arrayOfByte1 = paramString1.getBytes();
		byte[] arrayOfByte2 = null;
		try {
			SecureRandom localSecureRandom = new SecureRandom();
			DESKeySpec localDESKeySpec = new DESKeySpec(paramString2.getBytes());
			SecretKey localSecretKey = SecretKeyFactory.getInstance("DES")
					.generateSecret(localDESKeySpec);
			Cipher localCipher = Cipher.getInstance("DES");
			localCipher.init(1, localSecretKey, localSecureRandom);
			arrayOfByte2 = localCipher.doFinal(arrayOfByte1);
		} catch (InvalidKeyException localInvalidKeyException) {
			localInvalidKeyException.printStackTrace();
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
			localNoSuchAlgorithmException.printStackTrace();
		} catch (InvalidKeySpecException localInvalidKeySpecException) {
			localInvalidKeySpecException.printStackTrace();
		} catch (NoSuchPaddingException localNoSuchPaddingException) {
			localNoSuchPaddingException.printStackTrace();
		} catch (IllegalBlockSizeException localIllegalBlockSizeException) {
			localIllegalBlockSizeException.printStackTrace();
		} catch (BadPaddingException localBadPaddingException) {
			localBadPaddingException.printStackTrace();
		}
		return new String(arrayOfByte2);
	}

	public static String encryptDES(String paramString1, String key)
			throws Exception {
		IvParameterSpec localIvParameterSpec = new IvParameterSpec(iv);
		SecretKeySpec localSecretKeySpec = new SecretKeySpec(
				key.getBytes(), "DES");
		Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5PADDING");
		localCipher.init(1, localSecretKeySpec, localIvParameterSpec);
		return encode(localCipher.doFinal(paramString1.getBytes()));
	}
}
