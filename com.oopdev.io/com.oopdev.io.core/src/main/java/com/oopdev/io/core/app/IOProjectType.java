package com.oopdev.io.core.app;

/**
 * 
 * @author kamilbukum
 *
 */
public enum IOProjectType {
	CONSOLE(0),WEB(1);
	
	private final int projectType;

	private IOProjectType(int projectType){
		this.projectType=projectType;
	}
	public int getProjectType() {
		return projectType;
	}
	/**
	 * 3
	 * @param projectType
	 * @return
	 * 
	 */
	public static IOProjectType getProjectType(int projectType){
		switch (projectType) {
		case 0:
			return IOProjectType.CONSOLE;
		case 1:
			return IOProjectType.WEB;
		}
		return IOProjectType.CONSOLE;
	}
}
