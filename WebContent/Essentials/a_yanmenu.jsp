	<%@page import="com.da.TurDAO"%>
	<%@page import="com.entity.Tur"%>
	<%@page import="com.entity.Cihaz"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="org.spring.util.SpringFactoryProvider"%>
	<%@page import="org.springframework.context.support.AbstractApplicationContext"%>
	<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<%
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
    		ArrayList<Tur> turler = context.getBean("TurDAO",TurDAO.class).butunTurleriGetir();
            request.setAttribute("turler", turler);
    %>
    
				<c:if test="${not empty session_ad}">
					<span>Merhaba ${session_ad}!</span>
				</c:if>
		<h4>Türler</h4>
		
			<ul id="yanmenu-css">
			<% 
			String turid, jturid, turad;
			for (Tur temptur : turler) {
				
				turad = temptur.getAd();
				turid = Integer.toString(temptur.getId());
				jturid  = "'#tur" + turid + "'";
			
			%>
		 	<li><a href="#" id="tur<%=turid%>"><%=turad%></a></li>
			<script type="text/javascript">

			
					$j(<%=jturid %>).click(

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
			<h4>Sepetim</h4>
			<table>
				
				
				<% ArrayList<Cihaz> acihaz = new ArrayList<Cihaz>();
				
					acihaz = (ArrayList<Cihaz>)request.getSession().getAttribute("sepet"); 
					
					for (Cihaz tempcihaz : acihaz) {
					
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
		<tr><td colspan="2"><input type="button" value="Takip Et" id="btnTakipEt"></input></td></tr>
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



		</script>
	

<c:if test="${ not empty mesaj}">
<p style="color: red;"> ${mesaj}   </p>

</c:if>