package com.brianu.tutorial.navigation;

import java.util.Calendar;

import javax.servlet.ServletContext;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;


/**
 * Quartz Job class that checks if any updates are necessary.
 * If so it will perform the necessary updates.
 * 
 * @author Ergels Gaxhaj
 *
 */
public class FileBeanJob implements Job {

	//private static final Logger log = Logger.getLogger(FileBeanJob.class);
	private Logger log = LogManager.getLogger(FileBeanJob.class.getName());
	
	@Override
	public void execute(JobExecutionContext jctx) throws JobExecutionException {
		log.info("========================================================================");
		log.info("Execution at: " + Calendar.getInstance().getTime()
				+ " triggered by: " + jctx.getTrigger().getKey().getName());
		log.info("Starting updates...");
		
		String logMsg = "No updates required...";

		try {
			ServletContext servletContext = 
					(ServletContext) jctx.getScheduler().getContext().get("servletContext");
			FileBean fileBean = 
					(FileBean) servletContext.getAttribute(NavigationConstants.NAVIGATION_BEAN_NAME);
			FileBeanHelper helper = new FileBeanHelper();

			/* Check head for updates. */
			helper.setFileBase(
					NavigationConstants.DOC_BASE, 
					NavigationConstants.HEAD_NAME);
			
			if (helper.lastModified() > fileBean.headLastModified()) {
				log.info("Updating head...");
				fileBean.setHead(helper.getFileStream(), helper.lastModified());
				logMsg = "Update process complete...";
			}
			
			/* Check header for updates. */
			helper.setFileBase(
					NavigationConstants.DOC_BASE, 
					NavigationConstants.HEADER_NAME);
			
			if (helper.lastModified() > fileBean.headerLastModified()) {
				log.info("Updating header...");
				fileBean.setHeader(helper.getFileStream(), helper.lastModified());
				logMsg = "Update process complete...";
			}
			
			/* Check footer for updates. */
			helper.setFileBase(
					NavigationConstants.DOC_BASE, 
					NavigationConstants.FOOTER_NAME);
			
			if (helper.lastModified() > fileBean.footerLastModified()) {
				log.info("Updating footer...");
				fileBean.setFooter(helper.getFileStream(), helper.lastModified());
				logMsg = "Update process complete...";
			}
			
			
			log.info(logMsg);
			log.info("Execution complete at: " + Calendar.getInstance().getTime());
		} catch (SchedulerException e) {
			log.error("SchedulerException: Unable to retrieve servletContext...");
		}
	}
}
