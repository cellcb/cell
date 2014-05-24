package org.cell.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class FileProcess {

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
		System.out.println(UUID.randomUUID().toString().length());

		zipImage("f:\\abc.jpg", "f:\\abc123.jpg", 300, 200);
	}

	public static void zipImage(String srcImgFileName,
			String targetImgFileName, int imgWidth, int imgHeight) {

		File _file = new File(srcImgFileName);// �����ļ�
		try {
			Image src = javax.imageio.ImageIO.read(_file);// ����Image����
			BufferedImage tag = new BufferedImage(imgWidth, imgHeight,
					BufferedImage.TYPE_INT_RGB);
			// ������С���ͼ
			Graphics g = tag.createGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, imgWidth, imgHeight);

			g.drawImage(src, 0, 0, imgWidth, imgHeight, null);
			// g.drawImage(src, imgWidth, imgHeight, Color.WHITE, null);
			// ������ļ���

			FileOutputStream out = new FileOutputStream(targetImgFileName);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

			encoder.encode(tag);// ��JPEG����
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ImageFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeFile(InputStream in, String fileName)
			throws IOException {
		FileOutputStream fs = new FileOutputStream(Config.FILEPATH + fileName);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = in.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		in.close();
	}

	public static InputStream getFile(String fileid) throws IOException {
		InputStream fs = new FileInputStream(Config.FILEPATH + fileid);
		return fs;
	}
}
