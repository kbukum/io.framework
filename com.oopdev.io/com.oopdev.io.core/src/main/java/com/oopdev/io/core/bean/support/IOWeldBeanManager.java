package com.oopdev.io.core.bean.support;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.oopdev.io.core.exceptions.IOCoreException;
/**
 * 
 * @author kamilbukum
 *
 */
public class IOWeldBeanManager extends IOBeanManager{

	private final BeanManager beanManager;
	/**
	 * 
	 * @throws IOCoreException
	 */
	public IOWeldBeanManager() throws IOCoreException {
		super(10);
		BeanManager manager=null;
	      try{
	            InitialContext initialContext = new InitialContext();
	            manager=((BeanManager) initialContext.lookup("java:comp/BeanManager"));
	        }
	        catch (NamingException e) {
	           manager=null;
	        }
	      beanManager=manager;
	      isEnabled=beanManager!=null;
	}

	@Override
	public Object getComponent(String projectId,String name, Class<?> clazz) throws IOCoreException {
		try{
			if(beanManager==null){
				 throw new IOCoreException("com.oopdev.io.core.bean.support.IOCDIBeanManager.beanmanager.is.null");
			}
	        Bean<?> bean =  beanManager.getBeans(name).iterator().next();
	        CreationalContext<?> ctx = beanManager.createCreationalContext(bean); // could be inlined below
	        Object o = beanManager.getReference(bean, clazz, ctx); // could be inlined with return
	        return o;
			}catch (Exception e) {
				 throw new IOCoreException("com.oopdev.io.core.bean.support.IOCDIBeanManager.getComponent.exception",new Object[]{e.getMessage()},e);
			}
	}	
}
