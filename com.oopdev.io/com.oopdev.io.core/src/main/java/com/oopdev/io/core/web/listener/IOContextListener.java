package com.oopdev.io.core.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.oopdev.io.core.app.IOCoreConstants;
import com.oopdev.io.core.app.IOProjectType;
import com.oopdev.io.core.component.loader.IOComponentLoader;
import com.oopdev.io.core.context.IOWebContext;
import com.oopdev.io.util.exceptions.IOUtilException;
import com.oopdev.io.util.logger.IOLogger;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOContextListener extends IOCoreConstants implements ServletContextListener,HttpSessionListener,ServletRequestListener{
	
	static IOLogger logger=IOLogger.getIOLogger(IOCoreConstants.IO_CORE_LOG_PATH,ServletContextListener.class);
	@Override
	/**
	 * IO framework initalize method
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// Project type is web or console
		logger.info("com.oopdev.io.core.web.listener.IOContextListener.contextInitialized.init");
		IOCoreConstants.IO_PROJECT_TYPE=IOProjectType.WEB.getProjectType();
		IOWebContext.setApplication(sce.getServletContext());
		IOCoreConstants.IO_APPLICATION_PACKAGES_VALUE=IOWebContext.getApplication().getInitParameter(IOCoreConstants.IO_APPLICATION_PACKAGES_KEY);
		String characterEncoding=IOWebContext.getApplication().getInitParameter(IOCoreConstants.IO_CHARACTER_ENCODING_KEY);
		if(characterEncoding!=null){
		IOCoreConstants.IO_CHARACTER_ENCODING_VALUE=characterEncoding;
		}
		try {
			IOComponentLoader.loadComponents(IOCoreConstants.IO_APPLICATION_PACKAGES_VALUE);
		} catch (IOUtilException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("com.oopdev.io.core.web.listener.IOContextListener.contextDestroyed");
	}

	public static ServletContext getServletContext() {
		return IOWebContext.getApplication();
	}


	@Override
	public void sessionCreated(HttpSessionEvent event) {
		if(IOWebContext.getCurrentInstance()==null){
		IOWebContext context=new IOWebContext();
	    context.setSession(event.getSession());
	    IOWebContext.setCurrentInstance(context);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		

	}
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
	}

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		if(event.getServletRequest() instanceof HttpServletRequest){
			if(IOWebContext.getCurrentInstance()==null){
				IOWebContext context=new IOWebContext();
			    IOWebContext.setApplication(event.getServletContext());
			    IOWebContext.setCurrentInstance(context);
			}
			HttpServletRequest request=(HttpServletRequest)event.getServletRequest();
			IOWebContext.getCurrentInstance().setRequest(request);
			IOWebContext.getCurrentInstance().setSession(request.getSession());
		}
	}

}
