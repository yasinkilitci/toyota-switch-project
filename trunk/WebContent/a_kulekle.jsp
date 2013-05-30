<%@ page language="java" contentType="text/html; charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/kaydol.js" charset="utf-8"></script>

		<% Object o_session_id = request.getSession().getAttribute("session_id"); 
				int kulid = 0;
				if(o_session_id!=null){
						kulid = Integer.valueOf(o_session_id.toString())-1453;
				}		
		%>

	<table>
			<% if(o_session_id==null){ %>
		<tr><td colspan="2">YENÝ KAYIT OLUÞTUR</td></tr>
		<tr><td>Kullanýcý Adý</td><td><input type="text" id="kuladi" name="kuladi"></td></tr>
		<% } else { %>
		<tr><td colspan="2">KULLANICI BÝLGÝ GÜNCELLEME</td></tr>
		<input type="hidden" id="kulid" name="kulid" value="<%= kulid%>">
		<% } %>
		<tr><td>Þifre</td><td><input type="password" id="sifre" name="sifre"></td>   </tr>
		<tr><td>Þifre(Tekrar)</td><td><input type="password" id="sifre2" name="sifre2"></td>   </tr>
		<tr><td>Adý Soyadý</td><td><input type="text" id="adsoyad" name="adsoyad"></td></tr>
		<tr><td>Adres</td><td><textarea rows="3" cols="16" id="adres" name="adres"></textarea></td>   </tr>
		<tr><td>Telefon</td><td><input type="text" id="tel" name="tel"></td>   </tr>
		
	<% if(o_session_id==null){ %>
	<tr><td colspan="2"><input type="button" value="Ekle" id="btnKulEkle"></td>   </tr>
	<% } else { %>
	<tr><td colspan="2"><input type="button" value="Güncelle" id="btnKulGuncelle"></td>   </tr>
	<% } %>
	</table>
	
	<span id="hatayeriSignup"></span>

<c:if test="${ not empty mesaj}">
<p style="color: red;"> ${mesaj}   </p>

</c:if>