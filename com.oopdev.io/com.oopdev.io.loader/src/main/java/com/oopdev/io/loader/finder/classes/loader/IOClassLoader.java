package com.oopdev.io.loader.finder.classes.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.oopdev.io.loader.finder.classes.IOClassPathFinder;
import com.oopdev.io.loader.finder.classes.IOJarFinder;
import com.oopdev.io.loader.finder.classes.IOSystemClassLoader;
/**
 * 
 * @author Kamil BUKUM
 *
 */
public class IOClassLoader {

	private static Logger logger = Logger.getLogger(IOClassLoader.class.getName());

	/**
	 * 
	 * @param library jar files
	 * @param packageName
	 * @return allClasses in jar files by packageName
	 * <pre>
	 * <b>Desciription:The find classes by package name in libraries</b>
	 * </pre>
	 */
	public static List<Class<?>> getClasses(List<File> libFiles,
			String packageName) {

		List<Class<?>> classList = new ArrayList<Class<?>>();
		for (File file : libFiles) {
			classList.addAll(getClasses(file, packageName));
		}
		return (classList);
	}
	/**
	 * 
	 * @param library jar file
	 * @return allClasses in jar file
	 * <pre>
	 * <b>Desciription:The find classes  in libraries</b>
	 * </pre>
	 */
	public static List<Class<?>> getClasses(List<File> libFiles) {
		return getClasses(libFiles, "");
	}

	/**
	 * 
	 * @param packageName
	 * @return allClasses by packageName
	 * <pre>
	 * <b>Desciription:The find classes by packageName  in classpath and all libraries</b>
	 * </pre>
	 */
	public static List<Class<?>> getClasses(String packageName) {
		List<Class<?>> classList = new ArrayList<Class<?>>();
		if (packageName == null)
			packageName = "";
		List<File> files = IOClassPathFinder.getLibDirectories(packageName);
		for (File file : files) {
			classList.addAll(getClasses(file, packageName));
		}
		return classList;
	}
	/**
	 * 
	 * @return All Classes
	 * <pre>
	 * <b>Desciription:The find classes  in classpath and all libraries</b>
	 * </pre>
	 */
	public static List<Class<?>> getClasses() {
		return getClasses("");
	}

	/**
	 * 
	 * @param library jar file
	 * @param packageName
	 * @return allClasses in library by packageName
	 * <pre>
	 * <b>Desciription:The find classes by package name in a library</b>
	 * </pre>
	 */
	public static List<Class<?>> getClasses(File libFile, String packageName) {
		List<Class<?>> classList = new ArrayList<Class<?>>();
		if (packageName == null)
			packageName = "";
		String filePath = libFile.getPath();
		if (filePath.endsWith(".jar")) {
			IOJarFinder finder = IOJarFinder.getInstance(libFile, packageName);
			if (finder != null)
				classList.addAll(finder.getClasses());
		} else {
			IOSystemClassLoader finder = IOSystemClassLoader.getInstance(
					libFile, packageName);
			if (finder != null)
				classList.addAll(finder.getClasses());
		}
		return classList;
	}


	/**
	 * 
	 * @param libFile
	 * @return allClasses in library
	 * <pre>
	 * <b>Desciription:The find classes in a library</b>
	 * </pre>
	 */
	public static List<Class<?>> getClasses(File libFile) {
		return getClasses(libFile, "");
	}

	/**
	 * 
	 * @param packageNames
	 * @return AllClasses in packageNames
	 * <pre>
	 * <b>Desciription:The find classes by package name in classpath and libraries</b>
	 * </pre>
	 */
	public static List<Class<?>> getAllClasses(String packageNames){
		List<String> realPackageNames = new ArrayList<String>();
		if (packageNames == null) {
			logger.warning("Package Name is Not Define ! Because Of Server initialize can take long time ...");
			realPackageNames.add("");
		} else {
			List<String> packNames = Arrays.asList(packageNames.split(","));
			if (packNames.size() > 0) {
				for (int i = 0; i < packNames.size(); i++) {
					boolean isExist = false;
					for (int j = 1; j < packNames.size(); j++) {
						if (i != j) {
							if (packNames.get(i).contains(packNames.get(j))) {
								isExist = true;
								break;
							}
						}
					}
					if (!isExist) {
						realPackageNames.add(packNames.get(i));
					}
				}

			} else {
				realPackageNames.add("");
			}
		}
		List<Class<?>> ioJarClassList = new ArrayList<Class<?>>();
		for (String packageName : realPackageNames) {
			ioJarClassList.addAll(getClasses(packageName));
		}
		return ioJarClassList;
	}
}
