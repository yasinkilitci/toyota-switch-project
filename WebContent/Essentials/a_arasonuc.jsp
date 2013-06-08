<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.dao.DeviceTypeDAO"%>
    <%@page import="com.dao.DeviceDAO"%>
	<%@page import="com.entity.DeviceType"%>
	<%@page import="com.entity.Device"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="org.spring.util.SpringFactoryProvider"%>
	<%@page import="org.springframework.context.support.AbstractApplicationContext"%>
	
	
	<%
				Object o_keyword = request.getParameter("keyword");
						Object o_keyword_ip = request.getParameter("keyword_ip");
						Object o_yetki = request.getSession().getAttribute("session_yetki");
						if((o_keyword!=null)&&(o_keyword_ip!=null)&&(o_yetki!=null)){
					int yetki = Integer.valueOf(o_yetki.toString());
					String keyword = o_keyword.toString();
					String keyword_ip = o_keyword_ip.toString();
					AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
					  		ArrayList<Device> cihazlar = context.getBean("CihazDAO",DeviceDAO.class).benzeyenCihazlariListele(keyword,keyword_ip);
			%>
	  		<table class="tabloGenel">
 					<tr>
 						<td colspan="2">Arama Sonuçları</td></tr>
 					<tr>
 					<tr>
 						<td class="tabloBaslik">Cihaz Adı</td>
 						<td class="tabloBaslik">Cihaz IP</td>
 						<%
 							if (yetki==1){
 						%>
 						<td class="tabloBaslik">Portlar</td>
 						<%
 							}
 						%>
 					</tr>
	  		<%
	  			for(Device cihaz : cihazlar)
	  				{
	  						int cihaz_id = cihaz.getId();
	  						String cihazad = cihaz.getAd();
	  						String cihazip = cihaz.getIp();
	  						
	  						String jcihazid = "'#alink" + Integer.valueOf(cihaz_id) + "'";
	  						String jport = "'#btnPort" + Integer.valueOf(cihaz_id) + "'";
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
 						<% if (yetki==1){ %>
 						<td class="tabloBaslik">
 						<input type="button" id="btnPort<%= cihaz_id %>" value="Portları Görüntüle"/>
 						<script type="text/javascript">
						var $j = jQuery.noConflict();
					$j(<%=jport %>).click(

						function(){
							
							$j("#contentplaceholder").load("./Pages_Admin/a_portlar.jsp?cihazid="+<%=cihaz_id%>);
								
							});

						</script>
 						
 						
 						
 						</td>
 						<% } %>
						</tr>
				<% }
	  		
	  		
		}
	
	%>
	
	</table>