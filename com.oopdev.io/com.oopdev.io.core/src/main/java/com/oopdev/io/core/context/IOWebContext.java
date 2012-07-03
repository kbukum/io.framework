package com.oopdev.io.core.context;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oopdev.io.core.app.IOCoreConstants;

public class IOWebContext extends IOCoreConstants{
	
    private static ThreadLocal<IOWebContext> instance = new ThreadLocal<IOWebContext>() {
        protected IOWebContext initialValue() { return (null); }
    };
    public static void setCurrentInstance(IOWebContext context) {
        if (context == null) {
            instance.remove();
        } else {
            instance.set(context);
        }

    }
    
     
    public static IOWebContext getCurrentInstance() {
        return (instance.get());
    }
	public static ServletContext getApplication() {
		return application;
	}
	public HttpSession getSession() {
		return session;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	
	public static void setApplication(ServletContext application) {
		IOWebContext.application = application;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	protected static ServletContext application;
	protected HttpSession session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
}
