//burada filtre tnaï¿½mladï¿½k bu filtrede gï¿½venli klasï¿½rï¿½nï¿½n altï¿½na kullanï¿½cï¿½ yoksa yani
//login olmamï¿½ï¿½sa giremez.
package com.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class GuvenlikFiltre
 */
@WebFilter("/Pages_User/*")
public class Filtre_User implements Filter {

    /*Kurucu */
    public Filtre_User() {
        // TODO Auto-generated constructor stub
    }

	/* Yýkýcý */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/* Çalýþan Filtre */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		Object object_yetki = ((HttpServletRequest)request).getSession().getAttribute("session_yetki");
		
		/* Kullanýcý Deðilse Zaten null dönecektir. */ 
		 if (object_yetki == null)
		 
		{
			response.getWriter().write("Yetkisiz Eriþim!");
			((HttpServletResponse)response).sendRedirect(request.getServletContext().getContextPath()+"/index.jsp");
			chain.doFilter(request, response);
			return;
		}
		 
		 chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
