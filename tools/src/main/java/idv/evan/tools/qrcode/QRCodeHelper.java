package idv.evan.tools.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Map;

import javax.imageio.ImageIO;

import com.beust.jcommander.internal.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeHelper {

	private static Map<EncodeHintType, Object> EN_CODE_HINTS = Maps.newHashMap();
	private static Map<DecodeHintType, Object> DE_CODE_HINTS = Maps.newHashMap();
	private static int HEIGHT = 300, WIDTH = 300;
	private static String CHARACTER_SET = "UTF-8";
	private static int MARGIN = 4;
	private static BarcodeFormat BARCODE_FORMAT = BarcodeFormat.QR_CODE;
	private static ErrorCorrectionLevel ERROR_LEVEL = ErrorCorrectionLevel.H;
	private static String IMAGE_FORMAT = "png";
	private static String FILE_NAME = "defult";
	private static boolean IS_BUILD = false;
	
	private static QRCodeHelper HELPER;

	private QRCodeHelper() {
	}

	public static QRCodeHelper newQRCodeHelper() {
		HELPER = new QRCodeHelper();
		return HELPER;
	}

	public QRCodeHelper withEnCoHints(Map<EncodeHintType, Object> encodeHints) {
		EN_CODE_HINTS.clear();
		EN_CODE_HINTS.putAll(encodeHints);
		return this;
	}

	public QRCodeHelper withDeCoHints(Map<DecodeHintType, Object> decodeHints) {
		DE_CODE_HINTS.clear();
		DE_CODE_HINTS.putAll(decodeHints);
		return this;
	}

	public QRCodeHelper withEnCoHeightAndWidth(int height, int width) {
		HEIGHT = height;
		WIDTH = width;
		return this;
	}

	public QRCodeHelper withEnCoCharacterSet(String character) {
		CHARACTER_SET = character;
		return this;
	}

	public QRCodeHelper withEnCoBarcodeFormat(BarcodeFormat barcodeFormat) {
		BARCODE_FORMAT = barcodeFormat;
		return this;
	}

	public QRCodeHelper withEnCoErrorLevel(ErrorCorrectionLevel errorLevel) {
		ERROR_LEVEL = errorLevel;
		return this;
	}

	public QRCodeHelper withEnCoMargin(int margin) {
		MARGIN = margin;
		return this;
	}

	public static QRCodeHelper build() {
		if (HELPER == null) {
			HELPER = new QRCodeHelper();
		}
		
		putEnCoHints();
		putDeCoHints();
		IS_BUILD = true;
		return HELPER;
	}

	private static void putEnCoHints() {
		EN_CODE_HINTS.clear();
		EN_CODE_HINTS.put(EncodeHintType.CHARACTER_SET, CHARACTER_SET);
		EN_CODE_HINTS.put(EncodeHintType.MARGIN, MARGIN);
		EN_CODE_HINTS.put(EncodeHintType.ERROR_CORRECTION, ERROR_LEVEL);
	}

	private static void putDeCoHints() {
		DE_CODE_HINTS.clear();
		DE_CODE_HINTS.put(DecodeHintType.CHARACTER_SET, CHARACTER_SET);
	}

	public BufferedImage createBufferedImage(String contents) throws IOException {
		BitMatrix matrix = getBitMatrix(contents);
		return MatrixToImageWriter.toBufferedImage(matrix);
	}

	public void createQRCode(String contents, String path) throws IOException {
		this.createQRCode(contents, new File(path));
	}

	public void createQRCode(String contents, File file) throws IOException {
		this.createQRCode(contents, file.toPath());
	}

	public void createQRCode(String contents, Path path) throws IOException {
		BitMatrix matrix = getBitMatrix(contents);
		MatrixToImageWriter.writeToPath(matrix, IMAGE_FORMAT, path);
	}

	public void createQRCode(String contents, OutputStream stream) throws IOException {
		BitMatrix matrix = getBitMatrix(contents);
		MatrixToImageWriter.writeToStream(matrix, IMAGE_FORMAT, stream);
	}

	public String readQRCode(String filePath) throws IOException, NotFoundException {
		return this.readQRCode(new FileInputStream(filePath));
	}

	public String readQRCode(InputStream inputStream) throws IOException, NotFoundException {
		return this.readQRCode(ImageIO.read(inputStream));
	}
	
	public String readQRCode(BufferedImage bufferedImage) throws IOException, NotFoundException {
		BinaryBitmap binaryBitmap = getBinaryBitmap(bufferedImage);
		return new MultiFormatReader().decode(binaryBitmap, DE_CODE_HINTS).getText();
	}

	private static BitMatrix getBitMatrix(String contents) throws IOException {
		checkBuild();
		BitMatrix matrix = null;
		try {
			matrix = new MultiFormatWriter().encode(contents, BARCODE_FORMAT, WIDTH, HEIGHT, EN_CODE_HINTS);
		} catch (WriterException e) {
			System.err.printf("ERROR: %s", e.getMessage());
		}
		return matrix;
	}

	private static BinaryBitmap getBinaryBitmap(BufferedImage bufferedImage) throws IOException {
		checkBuild();
		return new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
	}

	private static void checkBuild() throws IOException {
		if (!IS_BUILD) {
			throw new IOException("is Not Build yet !");
		}
	}
}
