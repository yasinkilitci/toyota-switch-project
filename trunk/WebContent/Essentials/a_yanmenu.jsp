	<%@page import="com.dao.DeviceTypeDAO"%>
	<%@page import="com.entity.DeviceType"%>
	<%@page import="com.entity.Device"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="org.spring.util.SpringFactoryProvider"%>
	<%@page import="org.springframework.context.support.AbstractApplicationContext"%>
	<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<%
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
				    		ArrayList<DeviceType> turler = context.getBean("TurDAO",DeviceTypeDAO.class).butunTurleriGetir();
				            request.setAttribute("turler", turler);
		%>
    
				<c:if test="${not empty session_ad}">
					<span>Merhaba ${session_ad}!</span>
				</c:if>
		<h4>Türler</h4>
		
			<ul id="yanmenu-css">
			<%
				String turid, jturid, turad;
					for (DeviceType temptur : turler) {
						
						turad = temptur.getAd();
						turid = Integer.toString(temptur.getId());
						jturid  = "'#tur" + turid + "'";
			%>
		 	<li><a href="#" id="tur<%=turid%>"><%=turad%></a></li>
			<script type="text/javascript">

			
					$j(<%=jturid%>).click(

						function(){
							
							$j("#contentplaceholder").load("cihazlar.jsp?turid="+<%=turid%>);
							
							});

			</script>
			 
			<%
			 				}
			 			%>
			</ul>
			
			<hr/>
			<c:if test="${not empty sepet}">
			<p><span id="sepetmesaji"></span></p>
			<h4>Takip Listesi</h4>
			<table>
				
				
				<%
													ArrayList<Device> acihaz = new ArrayList<Device>();
														
															acihaz = (ArrayList<Device>)request.getSession().getAttribute("sepet"); 
															
															for (Device tempcihaz : acihaz) {
															
																String cihazid = String.valueOf(tempcihaz.getId());
																String cihazad = tempcihaz.getAd();
																String jcihazid = "'#scihaz" + cihazid + "'";
												%>
				
					<tr>
						<td>
						
						<a href="#" id="scihaz<%=cihazid%>"><%=cihazad %></a>
						
						<script type="text/javascript">

						
						
						$j(<%=jcihazid %>).click(

						function(){
							
							$j("#contentplaceholder").load("cihazDetay.jsp?cihazid="+<%=cihazid%>);
								
							});

						</script>
						
						</td>
					</tr>
				<%} %>
			</table>
			</c:if>
			
		<table>
		<c:if test="${not empty sepet}">
		<tr><td colspan="2">
		<input type="button" value="Takip Et" id="btnTakipEt"></input>
		<input type="button" value="Sıfırla" id="btnSifirla"></input>
		</td></tr>
		
		</c:if>
		</table>
		
		
		<script type="text/javascript">
		var $j = jQuery.noConflict();
			
		$j("#btnTakipEt").click(
				function(){

					$j.ajax({
						url: "./sorumluluk",
						type: "POST",
						datatype: "JSON",
						data: {istek: true},
						success: function(data){
							alert("Takip Edilmeye Başlandı!");
							$j("#sidemenu").load("Essentials/a_yanmenu.jsp");
						},
						error: function(data){
						alert("Takip Etme Başarısız!");
						},
						timeout: 3000
						
						});
					
				});

		$j("#btnSifirla").click(
				function(){

					$j.ajax({
						url: "./sepetekle",
						type: "POST",
						datatype: "JSON",
						data: {sifirla: true},
						success: function(data){
							alert("Liste Sıfırlandı!");
							$j("#sidemenu").load("Essentials/a_yanmenu.jsp");
						},
						error: function(data){
						alert("Liste Sıfırlama İşlemi Başarısız!");
						},
						timeout: 3000
						
						});
					
				});



		</script>
	

<c:if test="${ not empty mesaj}">
<p style="color: red;"> ${mesaj}   </p>

</c:if>