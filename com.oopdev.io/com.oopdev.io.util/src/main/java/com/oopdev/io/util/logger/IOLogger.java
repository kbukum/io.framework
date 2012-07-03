package com.oopdev.io.util.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.oopdev.io.util.bundles.IOUtilBundle;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOLogger {

	private final IOUtilBundle bundle;
	private final String className;
    private final Logger logger;
    /**
     * 
     * @param baseName
     * @param clazz
     * @return
     */
	public static IOLogger getIOLogger(String baseName,Class<?> clazz){
		return new IOLogger(baseName, clazz!=null?clazz.getName():"");
	}
	/**
	 * 
	 * @param baseName
	 * @param className
	 * @return
	 */
	public static IOLogger getIOLogger(String baseName,String className){
		return new IOLogger(baseName, className);
	}
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static IOLogger getIOLogger(Class<?> clazz){
		return new IOLogger(null, clazz!=null?clazz.getName():"");
	}
	/**
	 * 
	 * @param className
	 * @return
	 */
	public static IOLogger getIOLogger(String className){
		return new IOLogger(null, className);
	}
	/**
	 * 
	 * @param baseName
	 * @param className
	 */
	private IOLogger(String baseName,String className) {
		this.bundle=baseName==null?null:IOUtilBundle.getInstance(baseName);
		this.className=className;
		logger=Logger.getLogger(this.className);
	}
	/**
	 * 
	 * @param msg
	 * @param params
	 */
	public void debug(String msg,Object ...params){
		logger.log(Level.CONFIG, bundle!=null?bundle.getMessage(msg,params):msg);
	}
	/**
	 * 
	 * @param msg
	 * @param params
	 */
	public void info(String msg,Object ...params){
		logger.log(Level.INFO, bundle!=null?bundle.getMessage(msg,params):msg);
	}
	/**
	 * 
	 * @param msg
	 * @param params
	 */
	public void warn(String msg,Object ...params){
		logger.log(Level.WARNING, bundle!=null?bundle.getMessage(msg,params):msg);
	}
	/**
	 * 
	 * @param msg
	 * @param params
	 */
	public void error(String msg,Object ...params){
		logger.log(Level.WARNING, bundle!=null?bundle.getMessage(msg,params):msg);
	}
	/**
	 * 
	 * @param msg
	 * @param params
	 */
	public void fatal(String msg,Object ...params){
		logger.log(Level.WARNING, bundle!=null?bundle.getMessage(msg,params):msg);
	}

}
