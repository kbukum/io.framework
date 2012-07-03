package com.oopdev.io.loader.finder.classes;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author unknown
 *
 */
public class IOClassPathFinder {
	private static final Pattern fileNamePattern = Pattern.compile("file:.*!.*",Pattern.CASE_INSENSITIVE);
	
	
	/**
	 * 
	 * @param packageName
	 * @return all jar Files by packageName
	 */
	public static List<File> getLibDirectories(String packageName){
		if(packageName==null)
			packageName="";
		ClassLoader classLoader = getClassLoader();
		List<File> libDirList=new ArrayList<File>();
		String path=packageName.replace('.', '/');
		try {
			
			Enumeration<URL> resources1 = classLoader.getResources(path);
			
			int pathLength=path.length();
			
			while (resources1.hasMoreElements()) {
				File  file=getNextFile((URL) resources1.nextElement());
				int fileLength=file.getPath().length();
				String directoryPath=file.getPath();
				if(!file.getPath().trim().endsWith(".jar")){
						directoryPath=file.getPath().substring(0,fileLength-pathLength);
				}
				if(directoryPath.endsWith("\\"))
					directoryPath=directoryPath.substring(0,directoryPath.length()-1);
					libDirList.add(new File(directoryPath));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return libDirList;
	}
	
	private static File getNextFile(URL resource) throws UnsupportedEncodingException {
		String fileNameDecoded = URLDecoder.decode(resource.getFile(), "UTF-8");
		Matcher m = fileNamePattern.matcher(fileNameDecoded);
		if (m.matches()) {
			fileNameDecoded = fileNameDecoded.substring(
					fileNameDecoded.indexOf(":") + 1,
					fileNameDecoded.indexOf("!"));
		}
		return new File(fileNameDecoded);
	}
	private static ClassLoader getClassLoader() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		return classLoader;
	}
}
