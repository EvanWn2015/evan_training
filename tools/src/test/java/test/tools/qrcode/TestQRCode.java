package test.tools.qrcode;

import java.io.IOException;

import org.junit.Test;

import com.google.zxing.NotFoundException;

import idv.evan.tools.qrcode.QRCodeHelper;

public class TestQRCode {

	
	@Test
	public void testQ() {
	
		try {
			QRCodeHelper.build().createQRCode("dsadada", "/Users/evan/Desktop/defadnasd.png");
			String result = QRCodeHelper.build().readQRCode("/Users/evan/Desktop/defadnasd.png");
			System.out.println(result);
		} catch (IOException | NotFoundException e) {
			System.err.printf("ERROR: %s",e.getMessage());
		} 
	}
}
