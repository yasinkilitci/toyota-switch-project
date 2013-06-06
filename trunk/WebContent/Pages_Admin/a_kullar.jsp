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
 				AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
 		%>
 		
 			<table class="tabloGenel">
 					<tr>
 						<td colspan="7">KULLANICI GÖRÜNTÜLEME</td></tr>
 					<tr>
 					<tr>
 						<td class="tabloBaslik">Kullanıcı Adı</td>
 						<td class="tabloBaslik">Adı Soyadı</td>
 						<td class="tabloBaslik">E-Posta</td>
 						<td class="tabloBaslik">Takip Ettiği Cihaz Sayısı</td>
 						<td class="tabloBaslik">İşlem</td>
 					</tr>
 					
 		
 		<%	
 				try{
 				ArrayList<Kul> kullar = context.getBean("KulDAO",KulDAO.class).tumKullanicilariGetir();
 				for(Kul kul : kullar){
 				int mevcutkul_id = kul.getId();
		
 				int kul_id = kul.getId();
				String kuladi = kul.getKuladi();
				String adsoyad = kul.getAdsoyad();
				String eposta = kul.getEposta();
				int sorsay = kul.getCihazlar().size();
				
				String jkulsil = "'#obutton" + Integer.valueOf(kul_id) + "'";
				%>
 						<tr>
	 						<td class="tabloHucre"><%= kuladi %> </td>
	 						<td class="tabloHucre"><%= adsoyad %> </td>
	 						<td class="tabloHucre"><%= eposta %></td>
	 						<td class="tabloHucre"><%= sorsay %></td>
	 						<td class="tabloHucre">
	 						
	 						<input type="button" id="obutton<%=kul_id %>" value="Sil">
							<script type="text/javascript">
							$j(<%=jkulsil %>).click(function(){

								
								var kulid = <%= kul_id %>;
								var cevap = confirm(kulid + " no'lu kullanıcıyı takip listesiyle\nbirlikte silmek istediğinizden emin misiniz?");
								
									if(cevap){
								$j.ajax({
									url: "./kulsil",
									type: "POST",
									data: {kulid: kulid},
									datatype: "JSON",
									success: function(data){
										alert(kulid + " no'lu kullanıcı başarıyla silindi!");
										$j("#contentplaceholder").load("./Pages_Admin/a_kullar.jsp");
									},
									error: function(data){
										alert(kulid + " no'lu kullanıcı silinemedi!");
									},
									timeout:3000
									
									});

									}
									else
										{
											alert("Herhangi bir işlem yapılmadı!");
										}
								
							});

							</script>
			 
	 						</td>
 						</tr>
 				<% } }
 				catch(Exception e)
 				{
 					e.printStackTrace();
 				}
 					%>
 		
				</table>