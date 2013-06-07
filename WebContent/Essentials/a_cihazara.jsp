<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    	<%@page import="com.da.TurDAO"%>
    	<%@page import="com.da.CihazDAO"%>
	<%@page import="com.entity.Tur"%>
	<%@page import="com.entity.Cihaz"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="org.spring.util.SpringFactoryProvider"%>
	<%@page import="org.springframework.context.support.AbstractApplicationContext"%>
	
	<% Object o_yetki = request.getSession().getAttribute("session_yetki"); %>
	
	
	<table>
	<% if(o_yetki!=null){int yetki = Integer.valueOf(o_yetki.toString());
			if(yetki==1){ 
				AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
	%>
	<tr>
		<td colspan="2">
			<input type="button" id="btnHepsiniTara" value="Tüm Cihazları Tara ve Bildirim Yap"/>
			<script type="text/javascript">

			$j("#btnHepsiniTara").click(

					function(){

								var onay = confirm("Veritabanına Kayıtlı Tüm Cihazlardaki Portlar\nTaranacak ve Sorumlu Kişiler 30 Gün Kullanılmayan Portlardan Haberdar Edilecektir.\n Bu işlem bir miktar zaman alabilir. Devam Etmek İstiyor Musunuz?");
								if(onay){

									$j("#btnHepsiniTara").fadeOut(1000);

									$j.ajax({
										url: "./portislem?hepsinitara=true",
										type: "GET",
										datatype: "JSON",
										success: function(data){
											alert("Tüm Cihazlar Taranıyor...");
											
										},
										error: function(request, textStatus, errorThrown){
											alert("İşlem Başarısız!");
										}
										
										});


								}
						});
			</script>
		</td>
	</tr>
	<% }} %>
	<tr>
		<td colspan="2">
			CİHAZ ARAMA
		</td>
	</tr>
		<tr>
				<td>
				Cihaz Adı: 
				</td>
				<td>
				<input type="text" value="Aranacak Cihazın Adı" id="txtCihazAra"/>
				</td>
		 </tr>
		<tr>
				<td>
				Cihaz IP: 
				</td>
				<td>
				<input type="text" value="Aranacak Cihazın IP'si" id="txtIPAra"/>
				</td>
		 </tr>
	</table>
	<div id="cph_CihazAra"></div>
	
	<script type="text/javascript">

	var $j = jQuery.noConflict();
	
		$j("#txtCihazAra").keyup(function(){

			var keyword = $j("#txtCihazAra").val();
			var keyword_ip = $j("#txtIPAra").val();
			$j("#cph_CihazAra").load("./Essentials/a_arasonuc.jsp?keyword_ip="+keyword_ip+"&keyword="+keyword);
					});

		$j("#txtIPAra").keyup(function(){
			
			var keyword = $j("#txtCihazAra").val();
			var keyword_ip = $j("#txtIPAra").val();
			$j("#cph_CihazAra").load("./Essentials/a_arasonuc.jsp?keyword_ip="+keyword_ip+"&keyword="+keyword);
					});

		$j("#txtCihazAra").focus(function(){

			$j("#txtCihazAra").val("");
			
					});

		$j("#txtIPAra").focus(function(){

			$j("#txtIPAra").val("");
			
					});
		
	</script>
