<%@ page language="java" contentType="text/html; charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 		
 		<%@page import="com.da.UreticiDAO"%>
 		<%@page import="com.da.TurDAO"%>
 		<%@page import="com.entity.uretici"%>
 		<%@page import="com.entity.Tur"%>
		<%@page import="java.util.ArrayList"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/cihazekle.js"></script>

<% ArrayList<uretici> listeUretici = new UreticiDAO().butunureticileriGetir();
   ArrayList<Tur> listeTur = new TurDAO().butunTurleriGetir();
%>
	<table>
		<tr><td>Cihaz ADI</td><td><input type="text" id="cihazad"  name="cihazad"></td>   </tr>
		<tr><td>Fiyat</td><td><input type="text" id="cihazfiyat"  name="cihazfiyat"></td>   </tr>
			<tr><td>Türü</td>
		
				<td>
		
					<select id="cbTur" name="cbTur">	
						<% for (Tur temptur : listeTur) 
						
					{
					String turid = String.valueOf(temptur.getId());
					String turad = temptur.getAd();
					%>
						<option value="<%= turid%>"><%= turad%></option>
					<%}%>
					</select>
				</td>
			</tr>
			
			
		<tr><td>Üretici</td>
		
				<td>
		
					<select id="cbUretici" name="cbUretici">	
						<% for (uretici tempuretici : listeUretici) 
						
					{
					String ureticiid = String.valueOf(tempuretici.getId());
					String ureticiad = tempuretici.getAd();
					%>
						<option value="<%= ureticiid%>"><%= ureticiad%></option>
					<%}%>
					</select>
				</td>
			</tr>
	
	<tr><td colspan="2"><input type="button" value="Ekle" id="btnCihazEkle"></td>   </tr>
	
	</table>
<span id="hatayeriCihazEkle"></span>
<c:if test="${ not empty mesaj}">
<p style="color: red;"> ${mesaj}   </p>

</c:if>
