<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

 		
 		<%@page import="com.da.SiparisDAO"%>
 		<%@page import="com.entity.Siparis"%>
 		<%@page import="java.util.ArrayList"%>
 		<%@page import="java.sql.Date"%>
 		<%@page import="java.text.SimpleDateFormat"%>
 		
 		<% 		
 				int kulid = Integer.valueOf(request.getSession().getAttribute("session_id").toString()) - 1453; 
 				ArrayList<Siparis> siparisler = new SiparisDAO().kullaniciyaAitSiparisleriGetir(kulid);
 		%>
 		
 			<table class="tabloGenel">
 					<tr>
 						<td colspan="5">SİPARİŞ GÖRÜNTÜLEME</td></tr>
 					<tr>
 					<tr>
 						<td class="tabloBaslik">Sipariş No</td>
 						<td class="tabloBaslik">Toplam Tutar</td>
 						<td class="tabloBaslik">Tarih</td>
 						<td class="tabloBaslik">Durum</td>
 						<td class="tabloBaslik">İşlem</td>
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
	 						<td class="tabloHucre"><%= sipno %> </td>
	 						<td class="tabloHucre"><%= toplam %> TL</td>
	 						<td class="tabloHucre"><%= tarih %> </td>
	 						<td class="tabloHucre"><% if(onay==0){%>Bekleniyor<%}else {%>İşleme Alındı<%}%></td>
	 						<td class="tabloHucre"><% if(onay==0){%>
	 						
	 						<input type="button" id="obutton<%=sipno %>" value="İptal Et">
							<script type="text/javascript">
							$j(<%=jsiponay %>).click(

							function(){
									var sipno = <%= sipno %>;
								
								$j.ajax({
									url: "./siparisonay?iptalno="+sipno,
									type: "GET",
									datatype: "JSON",
									success: function(data){

										alert(sipno + " Nolu Sipariş Başarıyla İptal Edildi!");
										$j("#contentplaceholder").load("./Pages_User/a_sipgor.jsp");
									},
									error: function(data){
										alert(sipno + " Nolu Sipariş İptal Edilemedi!");
									}
									
									});
								
							});

							</script>
			 
	 						<%}else {%>Onaylandı<%}%></td>
 						</tr>
 				<% } %>	
		
				</table>