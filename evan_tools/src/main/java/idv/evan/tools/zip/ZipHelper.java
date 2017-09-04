package idv.evan.tools.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.google.common.base.Strings;

public class ZipHelper{
	
	private String[] inputFilePaths = new String[] {};
	private String outputZipFilePath;
	private String[] newEntryNames = new String[] {};
	private String zipFileName;
	private ZipOutputStream zos ;
	private static final byte[] BUFFER = new byte[1024];
	private boolean isBuild = false;
	
	private ZipHelper () { }
	
	public static ZipHelper newZipHelper () {
		return new ZipHelper();
	}

	public ZipHelper withInputFilePath(String inputFilePath) {
		this.inputFilePaths = concat(this.inputFilePaths, new String[] { inputFilePath });
		return this;
	}

	public ZipHelper withInputFilePathArray(String[] inputFilePaths) {
		this.inputFilePaths = concat(this.inputFilePaths, inputFilePaths);
		return this;
	}

	public ZipHelper withOutputZipFilePath(String outputZipFilePath) {
		this.outputZipFilePath = outputZipFilePath;
		return this;
	}

	public ZipHelper withZipFileName(String zipFileName) {
		this.zipFileName = zipFileName;
		return this;
	}
	
	public ZipHelper withNewEntryNameArray(String[] newEntryNames) {
		this.newEntryNames = concat(this.newEntryNames, newEntryNames);
		return this;
	}
	
	public ZipHelper withNewEntryName(String newEntryName) {
		this.newEntryNames = concat(this.newEntryNames, new String[] {newEntryName});
		return this;
	}
	
	public ZipHelper withInputFilePathToNewEntryName (String inputFilePath, String newEntryName) {
		withInputFilePath(inputFilePath);
		if (Strings.isNullOrEmpty(newEntryName)) {
			withNewEntryName(newEntryName);	
		}
		return this;
	}
	
	public ZipHelper build() throws IOException {
		this.checkFile();
		FileOutputStream fouts = new FileOutputStream(this.outputZipFilePath + "/" +this.zipFileName);
		zos  = new ZipOutputStream(fouts);
		this.isBuild = true;
		return this;
	}
	
	public void clear() {
		this.inputFilePaths = new String[] {};
		this.outputZipFilePath = null;
		this.zipFileName = null;
		this.newEntryNames = new String[] {};
		this.isBuild = false;
	}

	private void checkFile() throws IOException {
		for (String paths : this.inputFilePaths) {
			new FileInputStream(paths);
		}
		if (this.newEntryNames.length > 0 && this.newEntryNames.length != this.inputFilePaths.length) {
			throw new IOException("newEntryNames.length != inputFilePaths.length");
		}
	}
	
	private FileOutputStream getFileOutputStream() throws IOException{
		String last = this.outputZipFilePath.substring(this.outputZipFilePath.length()-1);
		String zipFilePath ;
		if ("/".equals(last) || "\\".equals(last)) {
			zipFilePath = this.outputZipFilePath + this.zipFileName;
		}else {
			zipFilePath = this.outputZipFilePath + "/" + this.zipFileName;
		}
		return new FileOutputStream(zipFilePath);
	}
	
	public void writeZip() throws IOException {
		if (!this.isBuild) {
			throw new IOException(new RuntimeException("this Instance has not been established yet !"));
		}
		FileOutputStream fouts = getFileOutputStream();
		zos = new ZipOutputStream(fouts);
		int index = 0;
		for (String inputPath : this.inputFilePaths) {
			FileInputStream fins = new FileInputStream(inputPath);
			if (this.newEntryNames.length > 0) {
				zos.putNextEntry(new ZipEntry(this.newEntryNames[index]));
			}else {
				zos.putNextEntry(new ZipEntry(new File(inputPath).getName()));	
			}
			int length;
			while ((length = fins.read(BUFFER)) > 0) {
				zos.write(BUFFER, 0, length);
			}
			fins.close();
			index ++;
		}
		zos.closeEntry();
		zos.close();
		fouts.close();
	}

	private static final <T> T[] concat(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}	
}
