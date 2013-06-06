<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.da.TurDAO"%>
    <%@page import="com.da.CihazDAO"%>
	<%@page import="com.entity.Tur"%>
	<%@page import="com.entity.Cihaz"%>
	<%@page import="com.entity.Port"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="org.spring.util.SpringFactoryProvider"%>
	<%@page import="org.springframework.context.support.AbstractApplicationContext"%>
	
	<table>
		<tr>
			<td colspan="3">PORT DETAYLARI</td>
		
		</tr>
		<tr>
			<td>Port Adı</td>
			<td>Son Erişim Zamanı</td>
		</tr>
	
	
	
	<% Object o_cihaz = request.getParameter("cihazid");
	
		if(o_cihaz!=null){
					AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
					int cihaz_id = Integer.valueOf(o_cihaz.toString());
					ArrayList<Port> portlar = context.getBean("CihazDAO",CihazDAO.class).portlariGetir(cihaz_id);
					for(Port port: portlar)
					{
						%>
							<tr>
								<td>
								<% port.getName(); %>
								</td>
								<td>
								<% port.getSonerisim().toString(); %>
								</td>
							</tr>
						<%
					}
		}
	
	%>
	
	</table>