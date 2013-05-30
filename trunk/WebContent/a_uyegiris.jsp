<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript" chartset="utf-8" src="${pageContext.request.contextPath}/Scripts/girisyap.js"></script>    
    
<form method="post" action="giris">
	<table>
		<tr><td>Kullanıcı Adı</td><td><input type="text" id="kullaniciadi" name="kullaniciadi"></td>   </tr>
		<tr><td>Şifre</td><td><input type="password" id="sifre" name="sifre"></td>   </tr>
		<tr><td colspan="2"><input type="submit" id="btnGiris" value="Giriş Yap"></td>   </tr>
	</table>

</form>

<span id="hatayeriLogin" style="{color:red}"></span>

<c:if test="${ not empty mesaj}">
<p style="color: red;"> ${mesaj}  </p>

</c:if>