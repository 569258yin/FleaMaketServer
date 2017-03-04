package com.aygxy.fmaket.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.aygxy.fmaket.Application;
import com.aygxy.fmaket.debug.DebugLog;

public class InitWebServerListener implements ServletContextListener{

	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
//		System.out.println("=======================contextDestroyed========================");
		DebugLog.logger.info("=======================contextDestroyed========================");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
//		System.out.println("======================contextInitialized=========================");
		DebugLog.logger.info("======================contextInitialized=========================");
		Application.Instance().initTokenMap();
		
	}

}
