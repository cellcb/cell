package org.anne.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.anne.user.BlogUser;
import org.apache.commons.lang.StringUtils;

public class VisitFilter implements Filter {
	private static final String FILTERED_REQUEST = "@@session_context_filtered_request";

	private static final String[] INHERENT_ESCAPE_URIS = { "/index.jsp",
			"/index.html", "/login.jsp", "/login/doLogin.html",
			"/register.jsp", "/register.html" };

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {
			chain.doFilter(request, response);
		} else {

			request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
			HttpServletRequest httpRequest = (HttpServletRequest) request;

			BlogUser userContext = getSessionUser(httpRequest);
			if (userContext == null
					&& !isURILogin(httpRequest.getRequestURI(), httpRequest)) {
				String toUrl = httpRequest.getRequestURL().toString();
				if (!StringUtils.isEmpty(httpRequest.getQueryString())) {
					toUrl += "?" + httpRequest.getQueryString();
				}

				httpRequest.getSession().setAttribute(
						CommonConstant.LOGIN_TO_URL, toUrl);

				request.getRequestDispatcher("/login.html").forward(request,
						response);
				return;
			}
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * 当前URI资源是否需要登录才能访问
	 * 
	 * @param requestURI
	 * @param request
	 * @return
	 */
	private boolean isURILogin(String requestURI, HttpServletRequest request) {
		if (request.getContextPath().equalsIgnoreCase(requestURI)
				|| (request.getContextPath() + "/")
						.equalsIgnoreCase(requestURI))
			return true;
		for (String uri : INHERENT_ESCAPE_URIS) {
			if (requestURI != null && requestURI.indexOf(uri) >= 0) {
				return true;
			}
		}
		return false;
	}

	protected BlogUser getSessionUser(HttpServletRequest request) {
		return (BlogUser) request.getSession().getAttribute(
				CommonConstant.USER_CONTEXT);
	}

	@Override
	public void destroy() {

	}
}
