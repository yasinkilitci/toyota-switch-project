<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.da.TurDAO"%>
    <%@page import="com.da.CihazDAO"%>
	<%@page import="com.entity.Tur"%>
	<%@page import="com.entity.Cihaz"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="org.spring.util.SpringFactoryProvider"%>
	<%@page import="org.springframework.context.support.AbstractApplicationContext"%>
	
	
	<% 	Object o_keyword = request.getParameter("keyword");
		Object o_keyword_ip = request.getParameter("keyword_ip");
		if((o_keyword!=null)&&(o_keyword_ip!=null)){
			String keyword = o_keyword.toString();
			String keyword_ip = o_keyword_ip.toString();
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
	  		ArrayList<Cihaz> cihazlar = context.getBean("CihazDAO",CihazDAO.class).benzeyenCihazlariListele(keyword,keyword_ip);
			
	  		%>
	  		<table class="tabloGenel">
 					<tr>
 						<td colspan="2">Arama Sonuçları</td></tr>
 					<tr>
 					<tr>
 						<td class="tabloBaslik">Cihaz Adı</td>
 						<td class="tabloBaslik">Cihaz IP</td>
 					</tr>
	  		<%
	  		
	  		for(Cihaz cihaz : cihazlar)
				{
						int cihaz_id = cihaz.getId();
						String cihazad = cihaz.getAd();
						String cihazip = cihaz.getIp();
						
						String jcihazid = "'#alink" + Integer.valueOf(cihaz_id) + "'";
						
												%>
						<tr>
 						<td class="tabloHucre"><a href="#" id="alink<%=cihaz_id %>"><%=cihazad %></a>
 						
 						
						
						<script type="text/javascript">
						var $j = jQuery.noConflict();
					$j(<%=jcihazid %>).click(

						function(){
							$j("#contentplaceholder").load("cihazDetay.jsp?cihazid="+<%=cihaz_id%>);
								
							});

						</script>
			 
		 
 						</td>
 						<td class="tabloHucre"><%= cihazip %></td>
						</tr>
				<% }
	  		
	  		
		}
	
	%>
	
	</table>