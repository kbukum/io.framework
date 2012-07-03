package com.oopdev.io.loader.finder.classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
/**
 * 
 * @author unknown
 *
 */
public class IOJarFinder {

	private final List<File> directories;

	private final String packageName;

	private final List<Class<?>> classes;

	public IOJarFinder(List<File> directories, String packageName) throws ClassNotFoundException, IOException {
		this.directories = directories;
		this.packageName = packageName;
		classes = findAllClasses();
	}

	public static final IOJarFinder getInstance(List<File> directories, String packageName) throws ClassNotFoundException,
			IOException {

		return new IOJarFinder(directories, packageName);
	}

	public static final IOJarFinder getInstance(File directory, String packageName){

		List<File> list = new ArrayList<File>();
		list.add(directory);
		try {
			return getInstance(list, packageName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Class<?>> findAllClasses() throws IOException, ClassNotFoundException {

		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (File directory : directories) {
			classes.addAll(walkJar(directory));
		}

		return classes;
	}

	private List<Class<?>> walkJar(File directory) throws IOException, ClassNotFoundException {

		List<Class<?>> classes = new ArrayList<Class<?>>();
		JarFile jarFile = new JarFile(directory);

		Enumeration<JarEntry> jarEntries = jarFile.entries();

		while (jarEntries.hasMoreElements()) {
			JarEntry jarEntry = jarEntries.nextElement();
			addClassFromJar(jarEntry, classes);
		}

		return classes;
	}

	private void addClassFromJar(JarEntry jarEntry, List<Class<?>> classes) {
		if (isMatchingClass(jarEntry)) {
			String fileName = jarEntry.getName();
			if (isValidClassName(fileName)) {
				try{
					Class<?> clazz = createClass(fileName);
					if (isNotNull(clazz)) {
						classes.add(clazz);
					}
				}catch (Throwable e) {
				}
			}
		}
	}

	private boolean isMatchingClass(JarEntry jarEntry) {

		boolean retVal = false;
		if (!jarEntry.isDirectory()) {
			retVal = true;
		}
		return retVal;
	}

	private boolean isValidClassName(String fileName) {
		return fileName.endsWith(".class") && !fileName.contains("$");
	}

	private Class<?> createClass(String fileName) {

		try {
			String className = getClassName(fileName);
			return Class.forName(className);
		} catch (Throwable e) {
			return null;
		}
	}

	private String getClassName(final String fileName) {

		String retVal = fileName.substring(0, fileName.length() - 6);
		retVal = retVal.replaceAll("/", ".");

		return retVal;
	}

	private boolean isNotNull(Object obj) {
		return obj != null;
	}

	public List<Class<?>> getClasses() {
		return Collections.unmodifiableList(classes);
	}

	public String getPackageName() {
		return packageName;
	}
}
