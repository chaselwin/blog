package com.chasel.blog.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.chasel.blog.constant.LanguageConstants;
import com.chasel.blog.service.impl.UserService;

/**
 * 国际化中英文
 * 
 * @author chasel
 *
 */
public class ResourceUtil {
	
	private static Logger log = LoggerFactory.getLogger(ResourceUtil.class);

	private static final String MASSAGE_BASE = "config.messages";

	private static final String LANGUAGE_DEFAULT = "zh-CN";

	private static final String LANGUAGE_TOKEN = "languageToken";

	private static final Map<String, Locale> localeMap = new HashMap<>();

	private static final ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();

	static {
		bundle.setBasename(MASSAGE_BASE);
		localeMap.put(LanguageConstants.LANGUAGE_ZH, Locale.SIMPLIFIED_CHINESE);
		localeMap.put(LanguageConstants.LANGUAGE_EN, Locale.US);
	}

	public static String getMessage(String code, Object... args) {
		if (args == null)
			args = new Object[] {};
		return bundle.getMessage(code, args, code, getLocale());
	}

	private static Locale getLocale() {
		String language = getLanguageCode();
		Locale locale = localeMap.get(language);
		if (locale == null)
			locale = Locale.getDefault();
		return locale;
	}

	public static String getLanguageCode() {
		String language = getHeader(LANGUAGE_TOKEN);
		if (StringUtils.isEmpty(language)) {
			language = LANGUAGE_DEFAULT;
		}
		return language;
	}

	private static String getHeader(String headerName) {
		String headerValue = "";
		RequestAttributes attr = RequestContextHolder.getRequestAttributes();
		if (attr != null) {
			HttpServletRequest request = ((ServletRequestAttributes) attr).getRequest();
			if (request != null) {
				headerValue = request.getHeader(headerName);
			}
			if (StringUtils.isEmpty(headerValue)) {
				headerValue = request.getParameter(headerName);
			}
		}
		if (headerValue == null) {
			log.warn("Fail to get headerValue: "+headerValue);
			headerValue = "";
		}
		return headerValue;
	}

	public static String getUrl() {
		String url = "";
		RequestAttributes attr = RequestContextHolder.getRequestAttributes();
		if (attr != null) {
			HttpServletRequest request = ((ServletRequestAttributes) attr).getRequest();
			if (request != null) {
				url = request.getLocalName() + ":" + request.getLocalPort();
			}
		}
		return url;
	}
	
	public static boolean getSession() {
		boolean bool=false;
		Long userName = null;
		RequestAttributes attr = RequestContextHolder.getRequestAttributes();
		if (attr != null) {
			HttpServletRequest request = ((ServletRequestAttributes) attr).getRequest();
			if (request != null) {
				HttpSession session = request.getSession();
				if (session!=null) {
					userName = (Long) session.getAttribute(UserService.USER_NAME);
				}
			}
		}
		if (null != userName) {
			bool=true;
		}
		return bool;
	}
	
	public static Long getSessionName() {
		Long userId = null;
		RequestAttributes attr = RequestContextHolder.getRequestAttributes();
		if (attr != null) {
			HttpServletRequest request = ((ServletRequestAttributes) attr).getRequest();
			if (request != null) {
				HttpSession session = request.getSession();
				if (session!=null) {
					userId = (Long) session.getAttribute(UserService.USER_NAME);
				}
			}
		}
		return userId;
	}

	protected static String getCookie() {
		String cookieValue = "";
		RequestAttributes attr = RequestContextHolder.getRequestAttributes();
		if (attr != null) {
			HttpServletRequest request = ((ServletRequestAttributes) attr).getRequest();
			if (request != null) {
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.equals(LANGUAGE_TOKEN)) {
							cookieValue = cookie.getValue();
						}
					}
				}
			}
		}
		return cookieValue;
	}

}
