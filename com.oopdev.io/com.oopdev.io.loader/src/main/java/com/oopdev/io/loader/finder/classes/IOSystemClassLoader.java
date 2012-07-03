package com.oopdev.io.loader.finder.classes;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 
 * @author unknown
 *
 */
public class IOSystemClassLoader {

	private final List<File> directories;

	private final String packageName;
	private final List<Class<?>> classes;

	public IOSystemClassLoader(List<File> directories, String packageName) throws ClassNotFoundException {
		this.directories = directories;
		this.packageName = packageName;
		classes = findAllClasses();
	}

	public static final IOSystemClassLoader getInstance(List<File> directories, String packageName)
			throws ClassNotFoundException {

		return new IOSystemClassLoader(directories, packageName);
	}

	public static final IOSystemClassLoader getInstance(File directory, String packageName){
		if (packageName==null) {
			packageName="";
		}
		directory=new File(directory.getPath()+"\\"+packageName.replace(".", "\\"));
		List<File> list = new ArrayList<File>();
		list.add(new File(directory.getPath()+""));
		try {
			return getInstance(list, packageName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Class<?>> findAllClasses() throws ClassNotFoundException {

		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		for (File directory : directories) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes;
	}

	private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<Class<?>>();

		if (directory.exists()) {
			File[] files = directory.listFiles();
			for (File file : files) {
				String fileName = file.getName();
				if (file.isDirectory()) {
					classes.addAll(findClasses(file, getClassName(packageName, fileName)));
				} else if (isValidClassName(fileName)) {
					classes.add(createClass(packageName, fileName));
				}
			}
		}

		return classes;
	}

	private String getClassName(String packageName, String fileName) {

		String retVal;
		if (packageName.length() == 0) {
			retVal = fileName;
		} else {
			retVal = packageName + "." + fileName;
		}
		return retVal;
	}

	private boolean isValidClassName(String fileName) {
		return fileName.endsWith(".class") && !fileName.contains("$");
	}

	private Class<?> createClass(String packageName, String fileName) throws ClassNotFoundException {

		String className = getClassName(packageName, fileName.substring(0, fileName.length() - 6));
		return Class.forName(className);
	}

	public List<Class<?>> getClasses() {
		return Collections.unmodifiableList(classes);
	}

	public String getPackageName() {
		return packageName;
	}
}
