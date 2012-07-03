package com.oopdev.io.json.service.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oopdev.io.json.service.app.IOJsonServiceConstants;
import com.oopdev.io.json.service.exceptions.IOJsonServiceException;
import com.oopdev.io.json.util.exceptions.IOJsonUtilException;
import com.oopdev.io.util.exceptions.IOUtilException;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOJsonWebServlet extends HttpServlet {

	IOJsonWebServer server = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			server = new IOJsonWebServer(config.getServletContext().getInitParameter(IOJsonServiceConstants.FILE_EXTENSION_CONTEXT_PARAM_NAME));
		} catch (IOJsonServiceException e) {
			throw new ServletException(e.getMessage());
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		try {
			server.doInOut(req, resp, DoType.GET);
		} catch (IOJsonServiceException e) {
			throw new ServletException(e.getMessage());
		} catch (IOJsonUtilException e) {
			throw new ServletException(e.getMessage());
		}catch (IOUtilException e) {
			throw new ServletException(e.getMessage());
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		try {
			server.doInOut(req, resp, DoType.POST);
		} catch (IOJsonServiceException e) {
			throw new ServletException(e.getMessage());
		} catch (IOJsonUtilException e) {
			throw new ServletException(e.getMessage());
		}catch (IOUtilException e) {
			throw new ServletException(e.getMessage());
		}
	}
}
