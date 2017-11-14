package com.brianu.tutorial;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author brian.urbina
 * This is in place of servlet.xml file
 */
public class MyWebAppInitializer extends 
   AbstractAnnotationConfigDispatcherServletInitializer{

   @Override
   protected Class<?>[] getRootConfigClasses() {
      return new Class[]{RootConfig.class};
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[]{WebConfig.class};
   }

   @Override
   protected String[] getServletMappings() {
      return new String[]{"/"};
   }
}
