<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
		
 		<%@page import="com.da.SiparisDAO"%>
 		<%@page import="com.entity.Siparis"%>
 		<%@page import="java.util.ArrayList"%>
 		<%@page import="java.sql.Date"%>
 		<%@page import="java.text.SimpleDateFormat"%>
 		
 		<% 		 
 				ArrayList<Siparis> siparisler = new SiparisDAO().tumSiparisleriGetir();
 			
 		%>
 		
 		<table class="tabloGenel">
 					<tr>
 						<td colspan="6">SİPARİŞ ONAYLAMA</td></tr>
 					<tr>
 						<td class="tabloBaslik">Kullanıcı Adı</td>
 						<td class="tabloBaslik">Sipariş No</td>
 						<td class="tabloBaslik">Toplam Tutar</td>
 						<td class="tabloBaslik">Tarih</td>
 						<td class="tabloBaslik">Durum</td>
 						<td class="tabloBaslik">Onay</td>
 					</tr>
 					
 		
 		<%
 				for(Siparis tempsip : siparisler)
 				{
 						
 						String kuladi = tempsip.getKuladi();
 						int sipno = tempsip.getSipno();
 						int onay = tempsip.getOnay();
 						int toplam = tempsip.getToplam();
 						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 						String tarih = sdf.format(tempsip.getTarih());
 						
 						String jsiponay = "'#obutton" + Integer.valueOf(sipno) + "'";
 						
 						
 						
 												%>
 						<tr>
	 						<td class="tabloHucre"><%= kuladi %> </td>
	 						<td class="tabloHucre"><%= sipno %> </td>
	 						<td class="tabloHucre"><%= toplam %> TL</td>
	 						<td class="tabloHucre"><%= tarih %> </td>
	 						<td class="tabloHucre"><% if(onay==0){%>Bekleniyor<%}else {%>İşleme Alındı<%}%></td>
	 						<td class="tabloHucre"><% if(onay==0){%>
	 						
	 						<input type="button" id="obutton<%=sipno %>" value="Onayla">
							<script type="text/javascript">
							$j(<%=jsiponay %>).click(

							function(){
									var sipno = <%= sipno %>;
								
								$j.ajax({
									url: "./siparisonay?onayno="+sipno,
									type: "GET",
									datatype: "JSON",
									success: function(data){

										alert(sipno + " Nolu Sipariş Başarıyla Onaylandı!");
										$j("#contentplaceholder").load("./Pages_Admin/a_siponay.jsp");
									},
									error: function(data){
										alert(sipno + " Nolu Sipariş Onaylanamadı!");
									}
									
									});
								
							});

							</script>
			 
	 						<%}else {%>
	 						
	 						<input type="button" id="obutton<%=sipno %>" value="Onay Kaldır">
							<script type="text/javascript">
							$j(<%=jsiponay %>).click(

							function(){
									var sipno = <%= sipno %>;
								
								$j.ajax({
									url: "./siparisonay?onayiptalno="+sipno,
									type: "GET",
									datatype: "JSON",
									success: function(data){

										alert(sipno + " Nolu Siparişin Onayı Başarıyla Kaldırıldı!");
										$j("#contentplaceholder").load("./Pages_Admin/a_siponay.jsp");
									},
									error: function(data){
										alert(sipno + " Nolu Siparişin Onayı Kaldırılamadı!");
									}
									
									});
								
							});

							</script>
	 						
	 						<%}%></td>
 						</tr>
 				<% } %>	
		
				</table>
 					
 					