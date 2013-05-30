	<%@page import="com.da.TurDAO"%>
	<%@page import="com.entity.Tur"%>
	<%@page import="com.entity.Cihaz"%>
	<%@page import="java.util.ArrayList"%>
	<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<%
    		ArrayList<Tur> turler = new TurDAO().butunTurleriGetir();
            request.setAttribute("turler", turler);
    %>
    
				<c:if test="${not empty session_ad}">
					<span>Merhaba ${session_ad}!</span>
				</c:if>
		<h4>Türler</h4>
		
		<!--  21.05.2013 İLKER BAŞ -->
		
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
			
			<!--  21.05.2013 İLKER SON -->
			
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
						<select id="cbadet<%=cihazid %>" name="cbadet<%=cihazid %>">
						<% 	
							int counter = 1;
							while(counter<10){
								%>
								<option value="<%= counter%>"><%= counter%></option>
								<%		
							counter++;
							}
						%>
						</select>
		
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
		<tr><td colspan="2"><input type="button" value="Siparis Ver" id="btnSiparisVer"></input></td></tr>
		</c:if>
		</table>
		
		
		<script type="text/javascript">
		var $j = jQuery.noConflict();
			
		$j("#btnSiparisVer").click(
				function(){

					var adetListesiJS = Array();

					<% 
						ArrayList<Cihaz> bcihaz = null; 
						bcihaz = (ArrayList<Cihaz>)request.getSession().getAttribute("sepet");
						int sayac = 0;
						if (bcihaz!=null)	
						for (Cihaz tempcihaz : bcihaz) {
						
						String cihazid = String.valueOf(tempcihaz.getId());
						String comboismi = "cbadet" + cihazid;
						String jqcombo = "'#cbadet" + cihazid + "'";
							%>
								adetListesiJS[<%= sayac %>]= $j(<%=jqcombo %>).val();
							<%
						sayac++;
							}
						%>	

						var formData = 
								{
									istek: true,
									adetlistem: JSON.stringify(adetListesiJS)
								};
					
					$j.ajax({
						url: "./siparisekle",
						type: "POST",
						datatype: "JSON",
						data: formData,
						success: function(data){
							alert("Siparis Verildi!");
							$j("#sidemenu").load("Essentials/a_yanmenu.jsp");
						},
						error: function(data){
						alert("Siparis Verilemedi!");
						}
						
						});
					
				});



		</script>
	

<c:if test="${ not empty mesaj}">
<p style="color: red;"> ${mesaj}   </p>

</c:if>