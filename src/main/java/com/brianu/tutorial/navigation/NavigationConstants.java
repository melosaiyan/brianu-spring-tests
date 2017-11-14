package com.brianu.tutorial.navigation;

/**
 * Constant Values
 * 
 * @author Ergels Gaxhaj
 *
 */
public class NavigationConstants {
	
	/* Constant variables used in retrieving fragments */
	/* public static final String DOC_BASE    = "/var/IRS/resource/dynamic/fragments"; */
	public static final String DOC_BASE    = "C:\\fragments"; 
	public static final String HEAD_NAME   = "head_rd.html";
	public static final String HEADER_NAME = "header_rd.html";
	public static final String FOOTER_NAME = "footer_rd.html";
	
    /* Constant variables used in navigation custom taglib */
	public static final String TAG_HEAD   = "head_rd";
	public static final String TAG_HEADER = "header_rd";
	public static final String TAG_FOOTER = "footer_rd";
	
	public static final String NAVIGATION_BEAN_NAME = "navigationBean";
	
    /* Constant variables used in the Quartz-Scheduler. */
	public static final String QUARTZ_JOB_NAME           = "eitc2016-job";
	public static final String QUARTZ_JOB_GROUP          = "eitc2016-job-group";
	public static final String QUARTZ_CRON_TRIGGER_NAME  = "eitc2016-trigger";
	public static final String QUARTZ_CRON_TRIGGER_GROUP = "eitc2016-trigger-group";
	public static final String QUARTZ_CRON_EXPRESSION    = "0 0 12pm * * ?";
}
