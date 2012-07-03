package com.oopdev.io.core.bean.create;

import com.oopdev.io.core.component.loader.IOComponentMap;
import com.oopdev.io.core.component.loader.IOScopeType;
import com.oopdev.io.core.context.IOWebContext;
import com.oopdev.io.util.common.IOTypeUtil;
import com.oopdev.io.util.exceptions.IOUtilException;

public class IOBeanWebCreator {

	public static Object getComponent(String projectId, String name, Class<?> clazz) throws IOUtilException{
		IOScopeType scope = IOComponentMap.getComponentscopemap().get(projectId).get(name);
		Object o=null;
		if(scope==IOScopeType.NONE||scope==IOScopeType.REQUEST){
			o=IOTypeUtil.newInstance(clazz);
			IOWebContext.getCurrentInstance().getRequest().setAttribute("IO_REQUEST"+projectId+"_"+name, o);
		}else if(scope==IOScopeType.CONVERSATION){
			o=IOWebContext.getCurrentInstance().getSession().getAttribute("IO_CONVERSATION_"+projectId+"_"+name);
			if(o==null){
				o=IOTypeUtil.newInstance(clazz);
				IOWebContext.getCurrentInstance().getSession().setAttribute("IO_CONVERSATION_"+projectId+"_"+name, o);
			}
		}else if(scope==IOScopeType.SESSION){
			o=IOWebContext.getCurrentInstance().getSession().getAttribute("IO_SESSION_"+projectId+"_"+name);
			if(o==null){
				o=IOTypeUtil.newInstance(clazz);
				IOWebContext.getCurrentInstance().getSession().setAttribute("IO_SESSION_"+projectId+"_"+name, o);
			}
		}else if(scope==IOScopeType.APPLICATION){
			o=IOWebContext.getApplication().getAttribute("IO_APPLICATION_"+projectId+"_"+name);
			if(o==null){
				o=IOTypeUtil.newInstance(clazz);
				IOWebContext.getApplication().setAttribute("IO_APPLICATION_"+projectId+"_"+name, o);
			}
		}
		if(o==null){
			o=IOTypeUtil.newInstance(clazz);
		}
		return  o;
	}
}
