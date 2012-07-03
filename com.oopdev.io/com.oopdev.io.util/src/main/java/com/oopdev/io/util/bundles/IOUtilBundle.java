package com.oopdev.io.util.bundles;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * 
 * @author kamil bukum
 *
 */
public class IOUtilBundle {
	private static final List<String> supportLangList=new LinkedList<String>();
	static {
		supportLangList.add("en");
		supportLangList.add("tr");
	}
	private static final Map<String, Map<String, ResourceBundle>> bundleLocaleMap=new LinkedHashMap<String, Map<String,ResourceBundle>>();
	static Logger logger=Logger.getAnonymousLogger();
	private ResourceBundle bundle;
	private String baseName;

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static IOUtilBundle getInstance(String file){
		if(file==null){
			return null;
		}
		return new IOUtilBundle(file);
	}
	/**
	 * 
	 * @param baseName
	 */
	private IOUtilBundle(String baseName){
		if(!bundleLocaleMap.containsKey(baseName)){
			bundleLocaleMap.put(baseName, new LinkedHashMap<String, ResourceBundle>());
		}
		
		String locale=Locale.getDefault().getLanguage();
		if(!supportLangList.contains(locale)){
			if(supportLangList.size()>0){
				locale=supportLangList.get(0);
			}else{
				locale="en";
			}
		}
		this.baseName=baseName;
		if(bundleLocaleMap.get(baseName).containsKey(locale)){
			this.bundle=bundleLocaleMap.get(baseName).get(locale);
		}else{
			try{		
				this.bundle=ResourceBundle.getBundle(baseName,new Locale(locale));
				}catch (Exception e) {
					this.bundle=ResourceBundle.getBundle(baseName+"_"+locale+".properties");
			}
		}
		if(this.bundle!=null&&!bundleLocaleMap.get(baseName).containsKey(locale)){
			bundleLocaleMap.get(baseName).put(locale, this.bundle);
		}
	}
	/**
	 * 
	 * @param propertyKey
	 * @return
	 */
	public String getMessage(String propertyKey){
		return getMesaj(propertyKey,null);
	}
	/**
	 * 
	 * @param propertyKey
	 * @param params
	 * @return
	 */
	public String getMessage(String propertyKey,Object [] params){
		return getMesaj(propertyKey,params);
	}

	private String getMesaj(String propertyKey,Object [] params){
		try{
				String paramName=bundle.getString(propertyKey);
				return MessageFormat.format(paramName, params);
		}catch (Exception e) {
			// TODO: handle exception
			logger.warning(propertyKey+" is not define in "+baseName);
			return propertyKey;
		}
	}
}
