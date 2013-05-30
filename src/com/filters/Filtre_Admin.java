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
@WebFilter("/Pages_Admin/*")
public class Filtre_Admin implements Filter {

    /*Kurucu */
    public Filtre_Admin() {
        // TODO Auto-generated constructor stub
    }

	/* Y�k�c� */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/* �al��an Filtre */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		Object object_yetki = ((HttpServletRequest)request).getSession().getAttribute("session_yetki");
		
		/* Kullan�c�/Admin Giri� Yapmam��sa Zaten null d�necektir. */ 
		 if (object_yetki == null)
		 
		{
			response.getWriter().write("Yetkisiz Eri�im!");
			((HttpServletResponse)response).sendRedirect(request.getServletContext().getContextPath()+"/index.jsp");
			chain.doFilter(request, response);
			return;
		}
		
		/* yetki bilgisini integer'a d�n��t�rd�k yoksa string olarak
		kar��la�t�rmada sorun ya��yoruz */
		int i_yetki = Integer.parseInt(object_yetki.toString());	
		
		/* Admin De�ilse �zin Verme */
		if(i_yetki!=1)
		
		{
			response.getWriter().write("Yetkisiz Eri�im!");
			((HttpServletResponse)response).sendRedirect(request.getServletContext().getContextPath()+"/index.jsp");	
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
