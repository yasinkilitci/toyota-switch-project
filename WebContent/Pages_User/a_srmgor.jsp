<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

 		
 		<%@page import="com.dao.DeviceDAO"%>
 		<%@page import="com.dao.ResponsibilityDAO"%>
		<%@page import="com.entity.Device"%>
 		<%@page import="java.util.ArrayList"%>
 		<%@page import="java.sql.Date"%>
 		<%@page import="java.text.SimpleDateFormat"%>
 		
<%@page import="org.spring.util.SpringFactoryProvider"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext"%>
 		
 		<%
 		 			int kulid = Integer.valueOf(request.getSession().getAttribute("session_id").toString()) - 1453;
 		 		 		 		 				AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
 		 		 		 		 				ArrayList<Device> cihazlar = ((ResponsibilityDAO)context.getBean("SorumlulukDAO",ResponsibilityDAO.class)).sorumluCihazlariGetir(kulid);
 		 		%>
 		
 			<table class="tabloGenel">
 					<tr>
 						<td colspan="5">SORUMLULUK GÖRÜNTÜLEME</td></tr>
 					<tr>
 					<tr>
 						<td class="tabloBaslik">Cihaz No</td>
 						<td class="tabloBaslik">Cihaz Adı</td>
 						<td class="tabloBaslik">Cihaz IP</td>
 						<td class="tabloBaslik">Takip</td>
 					</tr>
 					
 		
 		<%
 					 		 			for(Device cihaz : cihazlar)
 					 		 		 				{
 					 		 		 						int cihaz_id = cihaz.getId();
 					 		 		 						String cihazad = cihaz.getAd();
 					 		 		 						String cihazip = cihaz.getIp();
 					 		 		 						String uretici = cihaz.getUretici().getAd();
 					 		 		 						String tur = cihaz.getTur().getAd();
 					 		 		 						
 					 		 		 						String jsiponay = "'#obutton" + Integer.valueOf(cihaz_id) + "'";
 					 		 		%>
 						<tr>
	 						<td class="tabloHucre"><%= cihaz_id %> </td>
	 						<td class="tabloHucre"><%= cihazad %></td>
	 						<td class="tabloHucre"><%= cihazip %></td>
	 						<td class="tabloHucre">
	 						
	 						<input type="button" id="obutton<%=cihaz_id %>" value="İptal Et">
							<script type="text/javascript">
							$j(<%=jsiponay %>).click(

							function(){
									var cihaz_id = <%= cihaz_id %>;
									var kulid = <%= kulid %>;
								
								$j.ajax({
									url: "./sorumluluk?iptalno="+cihaz_id+"&kulid="+kulid,
									type: "GET",
									datatype: "JSON",
									success: function(data){

										alert(cihaz_id + "  No'lu Cihaz için Sorumluluk Başarıyla Kaldırıldı!");
										$j("#contentplaceholder").load("./Pages_User/a_srmgor.jsp");
									},
									error: function(data){
										alert(cihaz_id + "  No'lu Cihaz için Sorumluluk Kaldırılamadı!");
									}
									
									});
								
							});

							</script>
			 
	 						</td>
 						</tr>
 				<% } %>	
		
				</table>