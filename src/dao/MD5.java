package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		    'a', 'b', 'c', 'd', 'e', 'f' };
	public String MD(String str) throws NoSuchAlgorithmException {
		byte[] strBytes = str.getBytes();
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(strBytes);
		byte[] newBytes = md.digest();
		char newStr[] = new char[32];
		   // 循环进行处理
		   for (int i = 0; i < 16; i++) {
		    byte tmp = newBytes[i];
		    newStr[2*i] = hexDigits[tmp >>> 4 & 0xf];
		    newStr[2*i+1] = hexDigits[tmp & 0xf];
		   }
		   
        return new String(newStr);
	}
}
 