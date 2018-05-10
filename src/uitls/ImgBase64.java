package uitls;

import java.io.FileOutputStream;
import java.io.OutputStream;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
public class ImgBase64 {
	public static boolean generateImage(String imgStr, String path) {
		if (imgStr == null) return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
		// ����
		byte[] b = decoder.decodeBuffer(imgStr);
		// ��������
		for (int i = 0; i < b.length; ++i) {
		if (b[i] < 0) {
			b[i] += 256;
		}
		}
		OutputStream out = new FileOutputStream(path);
		out.write(b);
		out.flush();
		out.close();
		return true;
		} catch (Exception e) {
			return false;
		}
	}
}
