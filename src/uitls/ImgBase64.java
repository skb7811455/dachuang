package uitls;

import java.io.FileOutputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;

public class ImgBase64 {
	public static boolean generateImage(String imgStr, String path) {
		if (imgStr == null) return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
		// ����
		byte[] b = decoder.decodeBuffer(imgStr);
		System.out.println(imgStr);
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
		System.out.print("�ϴ�ͼƬ�ɹ�");
		return true;
		} catch (Exception e) {
			return false;
		}
	}
}
