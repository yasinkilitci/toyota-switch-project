<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

 		
 		<%@page import="com.da.CihazDAO"%>
 		<%@page import="com.da.KulDAO"%>
 		<%@page import="com.da.SorumlulukDAO"%>
		<%@page import="com.entity.Cihaz"%>
		<%@page import="com.entity.Kul"%>
 		<%@page import="java.util.ArrayList"%>
 		<%@page import="java.sql.Date"%>
 		<%@page import="java.text.SimpleDateFormat"%>
 		
<%@page import="org.spring.util.SpringFactoryProvider"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext"%>
 		
 		<% 		
 				int kulid = Integer.valueOf(request.getSession().getAttribute("session_id").toString()) - 1453;
 				AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
 		%>
 		
 			<table class="tabloGenel">
 					<tr>
 						<td colspan="7">SORUMLULUK GÖRÜNTÜLEME</td></tr>
 					<tr>
 					<tr>
 						<td class="tabloBaslik">Sorumlu</td>
 						<td class="tabloBaslik">Cihaz No</td>
 						<td class="tabloBaslik">Cihaz Adı</td>
 						<td class="tabloBaslik">Cihaz IP</td>
 						<td class="tabloBaslik">Takip</td>
 					</tr>
 					
 		
 		<%
 				ArrayList<Kul> kullar = context.getBean("KulDAO",KulDAO.class).tumKullanicilariGetir();
 				for(Kul kul : kullar){
 				int mevcutkul_id = kul.getId();
 				ArrayList<Cihaz> cihazlar = context.getBean("SorumlulukDAO",SorumlulukDAO.class).sorumluCihazlariGetir(mevcutkul_id);
 				for(Cihaz cihaz : cihazlar)
 				{
 						String sorumlu = kul.getKuladi();
 						int cihaz_id = cihaz.getId();
 						String cihazad = cihaz.getAd();
 						String cihazip = cihaz.getIp();
 						String uretici = cihaz.getUretici().getAd();
 						String tur = cihaz.getTur().getAd();
 						
 						String jsiponay = "'#obutton" + Integer.valueOf(cihaz_id) + "'";
 						
 						
 						
 												%>
 						<tr>
	 						<td class="tabloHucre"><%= sorumlu %> </td>
	 						<td class="tabloHucre"><%= cihaz_id %> </td>
	 						<td class="tabloHucre"><%= cihazad %></td>
	 						<td class="tabloHucre"><%= cihazip %></td>
	 						<td class="tabloHucre">
	 						
	 						<input type="button" id="obutton<%=cihaz_id %>" value="Kaldır">
							<script type="text/javascript">
							$j(<%=jsiponay %>).click(

							function(){
									var cihaz_id = <%= cihaz_id %>;
									var kulid = <%= mevcutkul_id %>;
								
								$j.ajax({
									url: "./sorumluluk?iptalno="+cihaz_id+"&kulid="+kulid,
									type: "GET",
									datatype: "JSON",
									success: function(data){

										alert(cihaz_id + "  No'lu Cihaz için Sorumluluk Başarıyla Kaldırıldı!");
										$j("#contentplaceholder").load("./Pages_Admin/a_srmgor.jsp");
									},
									error: function(data){
										alert(cihaz_id + "  No'lu Cihaz için Sorumluluk Kaldırılamadı!");
									}
									
									});
								
							});

							</script>
			 
	 						</td>
 						</tr>
 				<% }} %>	
		
				</table>