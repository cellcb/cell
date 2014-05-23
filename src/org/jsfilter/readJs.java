package org.jsfilter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class readJs implements Filter {
	
	private String dirBase;
	
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;
		String requestURI = req.getRequestURI();
		String fileContent = "";
		if(requestURI != null && requestURI.length() >= 1) {
			requestURI  = requestURI.substring(1);
			requestURI = requestURI.replaceFirst("\\.js$", "");
			//   STK/gaea
			Util util =  new Util(dirBase);
			fileContent = util.readFile(requestURI);
		}
		res.setContentType("application/x-javascript;charset=UTF-8");
		PrintWriter writer = res.getWriter();
		writer.print(fileContent);
		writer.flush();
		writer.close();
	}

	public void init(FilterConfig arg0) throws ServletException {
		dirBase = arg0.getInitParameter("dir");
	}

}
