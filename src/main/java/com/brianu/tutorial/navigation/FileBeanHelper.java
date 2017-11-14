package com.brianu.tutorial.navigation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Helper class that checks if file is available for reading.
 * 
 * @author ergels.gaxhaj
 *
 */
public class FileBeanHelper {

	//private static final Logger log = Logger.getLogger(FileBeanHelper.class);
	private Logger log = LogManager.getLogger(FileBeanHelper.class.getName());

	private String mDocBase;
	private String mFileName;
	
	private File mFile;
	
	/**
	 * 
	 * @return long - Last modified date of the file.
	 */
	public long lastModified() {
		return mFile.lastModified();
	}
	
	/**
	 * Retrieves the contents of the file convert to UTF-8 encoding.
	 * 
	 * @return string - File content.
	 */
	public String getFileStream() {
		mFile = new File(mDocBase, mFileName);
		
		StringBuffer buffer = new StringBuffer();
		try {
	        FileInputStream fis = new FileInputStream(mFile);
	        InputStreamReader isr = new InputStreamReader(fis, "UTF8");
	        Reader in = new BufferedReader(isr);
	        try {
		        int ch;
		        while ((ch = in.read()) > -1) {
		            buffer.append((char)ch);
		        }
	        } finally {
	        	if (in != null) in.close();
	        }
	    } catch (IOException ioe) {
			log.error("IOException: Unable to read file.");
	    }
		return buffer.toString();
	}
	
	/**
	 * Checks if the file exists and is available for reading.
	 * 
	 * @param docBase - document base directory.
	 * @param fileName - resources file name.
	 */
	public void setFileBase(String docBase, String fileName) {
		if (docBase == null) {
			throw new NullPointerException("Document base directory must not null");
		}
		
		if (fileName == null) {
			throw new NullPointerException("File name must not be null");
		}
		
		mFile = new File(docBase, fileName);
		try {
			mFile = mFile.getCanonicalFile();
		} catch (IOException e)	{
			log.error("IOException: Unable to get cononical file");
		}
		
		if (!mFile.exists() || !mFile.isFile() || !mFile.canRead()) {
			throw new IllegalArgumentException("Unable to validate file.");
		}
		
		mDocBase = docBase;
		mFileName = fileName;
	}
}
