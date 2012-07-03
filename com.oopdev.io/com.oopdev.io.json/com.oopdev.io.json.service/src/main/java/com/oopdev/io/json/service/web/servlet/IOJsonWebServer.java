package com.oopdev.io.json.service.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oopdev.io.core.app.IOCoreConstants;
import com.oopdev.io.core.bean.support.IOBeanSupportMap;
import com.oopdev.io.core.component.loader.IOComponentMap;
import com.oopdev.io.core.component.reflects.pojo.IOClass;
import com.oopdev.io.core.component.reflects.pojo.IOMethod;
import com.oopdev.io.core.security.IOSecurity;
import com.oopdev.io.core.security.IOSecurityManager;
import com.oopdev.io.json.service.annotations.IOJsonService;
import com.oopdev.io.json.service.app.IOJsonServiceConstants;
import com.oopdev.io.json.service.app.JsonResult;
import com.oopdev.io.json.service.component.manager.IOJsonServiceComponentManager;
import com.oopdev.io.json.service.exceptions.IOJsonServiceException;
import com.oopdev.io.json.util.common.IOJsonUtility;
import com.oopdev.io.util.exceptions.IOUtilException;
import com.oopdev.io.util.reflects.IOUtilMethodReflections;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOJsonWebServer {

	private static Logger logger = Logger.getLogger(IOJsonWebServer.class.getName());
	private static String fileExtension = null;

	public IOJsonWebServer(String fileExtension) throws IOJsonServiceException {
		IOJsonWebServer.fileExtension = fileExtension == null ? IOJsonServiceConstants.DEFAULT_FILE_EXTENSION: fileExtension;
		if (!IOJsonWebServer.fileExtension.matches("("+ IOJsonServiceConstants.FILE_EXTENSION_PATTERN + ")?")) {
			throw new IOJsonServiceException("com.oopdev.io.json.service.web.servlet.IOJsonWebServer.file.extension.wrongly",new Object[] {IOJsonServiceConstants.FILE_EXTENSION_CONTEXT_PARAM_NAME,IOJsonWebServer.fileExtension });
		}
	}

	private String bodyJsonInput;

	public void doInOut(HttpServletRequest req, HttpServletResponse resp,DoType doType) throws IOUtilException, IOException {
		req.setCharacterEncoding(IOCoreConstants.IO_CHARACTER_ENCODING_VALUE);
		resp.setCharacterEncoding(IOCoreConstants.IO_CHARACTER_ENCODING_VALUE);


		
		String resultData = "";
		String reqEncoding=null;
		String fileType=null;
		try {
			// Request Path Controller BEGIN...
			/** Request ile gelen servise path ve method kontrolu yap **/
			IOJsonInputPOJO inputPOJO= requestPathMatcher(req);
			// Request Path Controller END...
			/**
			 * Header Controller if fault type error throw exception else return
			 * header map
			 * 
			 */
			/** Header ve body değerlerini alıp okuyoruz **/
			IOJsonWebReader ioJsonReader = new IOJsonWebReader(req, doType);
			String jsonData = ioJsonReader.getJsonDataFromRequest();
			/** Header controlu yapiliyor. **/
//			JsonObject jsonObject = IOJsonServiceUtility.serviceHeaderController(ioJsonReader.getHeader());
			/** Header içerisinde method tipi tanimlimi ? **/
/*			if (headerMap.get(IOJsonServiceConstants.IO_JSON_HEADER_METHOD_TYPE_KEY) == null) {
				throw new IOJsonServiceException("com.oopdev.io.json.service.web.servlet.IOJsonWebServer.method.do.type.not.define",new Object[] { IOJsonServiceConstants.IO_JSON_HEADER_METHOD_TYPE_KEY });
			}
			if(headerMap.get(IOJsonServiceConstants.IO_JSON_HEADER_ENCODING_TYPE_KEY)!=null){
				reqEncoding=headerMap.get(IOJsonServiceConstants.IO_JSON_HEADER_ENCODING_TYPE_KEY);
				req.setCharacterEncoding(reqEncoding);
				resp.setCharacterEncoding(reqEncoding);
			}
			if(headerMap.get(IOJsonServiceConstants.IO_JSON_HEADER_FILE_TYPE_KEY)!=null){
				fileType=headerMap.get(IOJsonServiceConstants.IO_JSON_HEADER_FILE_TYPE_KEY);
			}
			/** Tanimli ise o method tipine gore olmasi gereken methodu bul **/
/*			DoType methodDoType = DoType.getDoType(headerMap.get(IOJsonServiceConstants.IO_JSON_HEADER_METHOD_TYPE_KEY));
			if (methodDoType == null) {
				throw new IOJsonServiceException("com.oopdev.io.json.service.web.servlet.IOJsonWebServer.method.do.type.is.wrongly");
			}
			
			if (!doType.equals(methodDoType)) {
				return;
			}

*/
			/** body al **/
			bodyJsonInput = jsonData; //ioJsonReader.getBody();
			IOClass ioClass = IOComponentMap.getComponentclassmap().get(IOJsonServiceComponentManager.class.getName()).get(inputPOJO.getServiceName());
			if (ioClass == null) {
				throw new IOJsonServiceException("com.oopdev.io.json.service.web.servlet.IOJsonWebServer.service.not.found",new Object[] { inputPOJO.getServiceName() });
			}

			IOMethod ioMethod = ioClass.getIoMethodMap().get(inputPOJO.getMethodName());
			if (ioMethod == null) {
				throw new IOJsonServiceException("com.oopdev.io.json.service.web.servlet.IOJsonWebServer.method.not.found",new Object[] { inputPOJO.getServiceName(), inputPOJO.getMethodName()});
			}

			/** Güvenlik kontrolü yapılıyor **/
			IOJsonService ioJsonService = ioClass.getClazz().getAnnotation(IOJsonService.class);
			
			if(ioJsonService.crossDomain()){
				resp.setHeader("Access-Control-Allow-Origin", "*");
				resp.setHeader("Access-Control-Allow-Headers","X-Requested-With");
			}
			if (ioJsonService.secure()) {// Servisin çağrımı güvenli ise
				boolean loginOk = false;

				if (IOComponentMap.getComponentclassmap().get(IOSecurityManager.class.getName()) == null|| IOComponentMap.getComponentclassmap().get(IOSecurityManager.class.getName()).size() == 0) {
					throw new IOJsonServiceException("com.oopdev.io.json.service.web.servlet.IOJsonWebServer.security.class.not.implemented");
				}
				Map<String, IOClass> map = IOComponentMap
						.getComponentclassmap().get(
								IOSecurityManager.class.getName());
				if (map.size() == 1) {
					IOClass secureClass = map.get(map.keySet().toArray()[0]);
					IOSecurity security = (IOSecurity) IOBeanSupportMap.getComponent(IOSecurityManager.class.getName(),secureClass);
					
					loginOk = security.isSessionExist();
					if (!loginOk) {
						if (ioJsonService.autoLogin()) {
							loginOk = security.login(null);//headerMap);
							if (!loginOk) {
								throw new IOJsonServiceException("com.oopdev.io.json.service.web.servlet.IOJsonWebServer.security.info.is.wrongly");
							}
						}
					}
				}
				if (!loginOk) {
					throw new IOJsonServiceException(
							"com.oopdev.io.json.service.web.servlet.IOJsonWebServer.security.fail ! ");
				}
			}

			/**
			 * Elimizde servis classi ve method mevcut. Buradan istedigimiz
			 * sekilde servisi olusturup method donebiliriz. Ancak biz yine de
			 * CDI ve baska frameworklerin destegi varsa onlarin olsuturdugu
			 * sekilde olusturabilirsek bu sekilde Onların desteklediği herşeyi
			 * desteklemiş olacağız.
			 * 
			 * 
			 * 
			 */
			List<Object> inputList = IOJsonUtility.jsonToPojos(bodyJsonInput,ioMethod);
			// TODO O zaman durma gore yeni class ureten bir method olusturup
			// buradan baslatalim
			// Oncelikle support durumu ve priority'e gore external bean
			// managers(cdi,spring...) cagriliyor.
			Object responseObject = IOBeanSupportMap.getComponent(
					IOJsonServiceComponentManager.class.getName(), ioClass);
			Object result = null;
			if (responseObject != null) {
				result = IOUtilMethodReflections.invokeMethod(
						ioMethod.getMethod(), responseObject,
						inputList.toArray());
			}
			resultData = new JsonResult(result, "SUCCESS", "").toString();
		} catch (Exception e) {
			resultData = new JsonResult("", "FAIL", e.getMessage()).toString();
		}
		resp.setContentType(fileType!=null?fileType:IOJsonServiceConstants.IO_JSON_HEADER_FILE_TYPE_VALUE);
		resp.setCharacterEncoding(reqEncoding!=null?reqEncoding:IOCoreConstants.IO_CHARACTER_ENCODING_VALUE);
		
		resp.getWriter().print(resultData);
	}

	private IOJsonInputPOJO requestPathMatcher(HttpServletRequest req)throws IOJsonServiceException {
		/**
		 * Request Path Controlling
		 */
		logger.info("Servis Path Name :" + req.getPathInfo());
		logger.info("Servis Path Controlling ...");
		String newRegEx = IOJsonServiceConstants.IO_JSON_SERVICE_PATH_REGEX+ ("".equals(fileExtension) ? "" : "[.]" + fileExtension);
		if (!Pattern.matches(newRegEx, req.getPathInfo())) {
			throw new IOJsonServiceException("com.oopdev.io.json.service.web.servlet.IOJsonWebServer.wrong.service.path",new Object[] { req.getLocalName(),req.getLocalPort()+"",req.getContextPath(),req.getServletPath(),"".equals(fileExtension) ? "" : "." + fileExtension});
		}
		logger.info("Servis Path Controlling successed .");
		Matcher matcher=Pattern.compile(newRegEx).matcher(req.getPathInfo());
		IOJsonInputPOJO outputPOJO=new IOJsonInputPOJO();
		while (matcher.find()) {
			outputPOJO.setServiceName(matcher.group(1));
			outputPOJO.setMethodName(matcher.group(2));
		}
		return outputPOJO;
	}

}
