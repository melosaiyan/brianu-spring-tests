package com.brianu.tutorial.navigation;

/**
 * A bean that will make data available to the application.
 * 
 * @author Ergels Gaxhaj
 *
 */
public class FileBean {

	private long mHeadLastModified   = -1L;
	private long mHeaderLastModified = -1L;
	private long mFooterLastModified = -1L;
	
	private String mHead   = null;
	private String mHeader = null;
	private String mFooter = null;

	
	private FileBeanHelper mHelper;
	
	/**
	 * Empty constructor
	 */
	public FileBean() {
	}
	
	/**
	 * Constructor
	 * 
	 * @param docBase - A documents base directory.
	 */
	public FileBean(String docBase) {
		/* Set head. */
		mHelper = new FileBeanHelper();
		mHelper.setFileBase(docBase, NavigationConstants.HEAD_NAME);
		setHead(mHelper.getFileStream(), mHelper.lastModified());
		
		/* Set header. */
		mHelper = new FileBeanHelper();
		mHelper.setFileBase(docBase, NavigationConstants.HEADER_NAME);
		setHeader(mHelper.getFileStream(), mHelper.lastModified());
		
		/* Set footer. */
		mHelper = new FileBeanHelper();
		mHelper.setFileBase(docBase, NavigationConstants.FOOTER_NAME);
		setFooter(mHelper.getFileStream(), mHelper.lastModified());
		
	}
	
	public void setHead(String head, long lastModified) {
		mHead = head;
		mHeadLastModified = lastModified;
	}
	
	public void setHeader(String header, long lastModified) {
		mHeader = header;
		mHeaderLastModified = lastModified;
	}
	
	public void setFooter(String footer, long lastModified) {
		mFooter = footer;
		mFooterLastModified = lastModified;
	}
	
	public String getHead() {
		return mHead;
	}
	
	public String getHeader() {
		return mHeader;
	}
	
	public String getFooter() {
		return mFooter;
	}
	
	public long headLastModified() {
		return mHeadLastModified;
	}
	
	public long headerLastModified() {
		return mHeaderLastModified;
	}
	
	public long footerLastModified() {
		return mFooterLastModified;
	}
}
