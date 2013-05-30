//burada filtre tna�mlad�k bu filtrede g�venli klas�r�n�n alt�na kullan�c� yoksa yani
//login olmam��sa giremez.
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

	/* Y�k�c� */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/* �al��an Filtre */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		Object object_yetki = ((HttpServletRequest)request).getSession().getAttribute("session_yetki");
		
		/* Kullan�c� De�ilse Zaten null d�necektir. */ 
		 if (object_yetki == null)
		 
		{
			response.getWriter().write("Yetkisiz Eri�im!");
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
