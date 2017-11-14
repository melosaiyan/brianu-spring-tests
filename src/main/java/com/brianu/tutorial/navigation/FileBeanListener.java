package com.brianu.tutorial.navigation;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Initializes FileBean class to application scope when we first deploy our application.
 * We can then later access the bean through the jsp:useBean tag.
 * 
 * Here we also initialize the Quartz Scheduler and pass the ServletContext 
 * to the SchedulerContext. We can then access to the ServletContext inside a Job.
 * @see FileBeanJob
 * 
 * @author Ergels Gaxhaj
 *
 */
public class FileBeanListener implements ServletContextListener {

	//private static final Logger log = Logger.getLogger(FileBeanListener.class);
	private Logger log = LogManager.getLogger(FileBeanListener.class.getName());
	
	SchedulerFactory schedulerFactory;
	Scheduler scheduler;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		event.getServletContext().removeAttribute(NavigationConstants.NAVIGATION_BEAN_NAME);
		
		try {
			TriggerKey triggerKey = new TriggerKey(NavigationConstants.QUARTZ_CRON_TRIGGER_NAME,
							NavigationConstants.QUARTZ_CRON_TRIGGER_GROUP);
			
			scheduler.unscheduleJob(triggerKey);
			scheduler.shutdown();
		} catch (SchedulerException e) {
			log.error("SchedulerException: Scheduler shutdown failed...");
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		FileBean bean = new FileBean(NavigationConstants.DOC_BASE);
		event.getServletContext().setAttribute(NavigationConstants.NAVIGATION_BEAN_NAME, bean);
		
		log.debug("FileBean added to servletcontext.");
		
		try {
			schedulerFactory = new StdSchedulerFactory();
			scheduler = schedulerFactory.getScheduler();
			scheduler.start();
			scheduler.getContext().put("servletContext", event.getServletContext());

			JobDetail job = newJob(FileBeanJob.class)
				.withIdentity(NavigationConstants.QUARTZ_JOB_NAME, 
							  NavigationConstants.QUARTZ_JOB_GROUP)
				.build();
			
			Trigger trigger = (Trigger) newTrigger()
					.withIdentity(NavigationConstants.QUARTZ_CRON_TRIGGER_NAME,
								NavigationConstants.QUARTZ_CRON_TRIGGER_GROUP)
					.startNow()
					.withSchedule(cronSchedule(NavigationConstants.QUARTZ_CRON_EXPRESSION))
					.forJob(NavigationConstants.QUARTZ_JOB_NAME, NavigationConstants.QUARTZ_JOB_GROUP)
					.build();
			
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			log.error("SchedulerException: Unable to retrieve scheduler.");
		}
	}
}
