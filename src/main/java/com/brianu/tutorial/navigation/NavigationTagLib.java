package com.brianu.tutorial.navigation;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class NavigationTagLib extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4721097742224002744L;
	//private static final Logger log = Logger.getLogger(NavigationTagLib.class);
	private Logger log = LogManager.getLogger(NavigationTagLib.class.getName());

	private String mName;
	
	public void setName(String name) {
		mName = name;
	}
	
	public int doStartTag() throws JspException {
		try {
			FileBean fileBean = 
					(FileBean) pageContext.getServletContext().getAttribute(
							NavigationConstants.NAVIGATION_BEAN_NAME);
			JspWriter out = pageContext.getOut();

			if (mName == NavigationConstants.TAG_HEAD)   out.print(fileBean.getHead());
			if (mName == NavigationConstants.TAG_HEADER) out.print(fileBean.getHeader());
			if (mName == NavigationConstants.TAG_FOOTER) out.print(fileBean.getFooter());

			
		} catch(Exception ioe) {
			log.error("Unable to retreive content.");
		}
		
		return SKIP_BODY;
	}
	
	public int doEndtag() throws JspException {
		return SKIP_PAGE;
	}
}

