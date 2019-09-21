package com.realspeed.util;

import java.io.File;
import java.io.IOException;

public final class DataExporterUtil {

	public static File getTemporaryDirectoryLocation() throws IOException {
		File tempFile = File.createTempFile("temp", Long.toString(System.nanoTime()));

		if (!(tempFile.delete())) {
			throw new IOException("Could not delete temp file: " + tempFile.getAbsolutePath());
		}

		if (!(tempFile.getCanonicalFile().mkdir())) {
			throw new IOException("Could not create temp directory: " + tempFile.getAbsolutePath());
		}
		return tempFile;
	}
	public static void main(String[] args) throws IOException {
		System.out.println(DataExporterUtil.getTemporaryDirectoryLocation());;
	}

}
