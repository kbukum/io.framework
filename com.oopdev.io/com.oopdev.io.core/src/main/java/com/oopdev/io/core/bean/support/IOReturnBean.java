package com.oopdev.io.core.bean.support;

public class IOReturnBean {

	private final Object object;
	private final String beanManagerUniqueName;
	
	public IOReturnBean(Object object,String beanManagerUniqueName) {
		this.object=object;
		this.beanManagerUniqueName=beanManagerUniqueName;
	}

	public Object getObject() {
		return object;
	}

	public String getBeanManagerUniqueName() {
		return beanManagerUniqueName;
	}
}
