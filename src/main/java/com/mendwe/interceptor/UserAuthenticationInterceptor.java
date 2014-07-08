package com.mendwe.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class UserAuthenticationInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger
			.getLogger(UserAuthenticationInterceptor.class);

	/*
	 * private static final Logger logger = Logger
	 * .getLogger(UserAuthenticationInterceptor.class);
	 */

	@Override
	public boolean preHandle(HttpServletRequest request,

	HttpServletResponse response, Object handler) throws Exception {

		String uri = request.getRequestURI();

		logger.debug("*******Inside prehandle ******  from interceptor uri = " + uri);
		if (!uri.endsWith("login.html") && !uri.endsWith("loginValidate.html")
				&& !uri.endsWith("registerUser.html")) {
			logger.debug("request coming from other than login.html");
			HttpSession session = request.getSession();
			String sessionValue = (String) session.getAttribute("username");
			if (sessionValue == null) {
				logger.debug("session found null sending to login page");
				response.sendRedirect("login.html");
				return false;
			}
			logger.debug("session is available");
		}

		// response.sendRedirect("home.do");
		return true;
	}
}