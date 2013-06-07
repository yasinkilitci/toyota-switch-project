<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul id="menu-css">
			
			
				<li><a href="#" id="menu_anasayfa">Anasayfa</a></li>
				<li><a href="#" id="menu_hak">Hakkımızda</a></li>
				
				<!--  Session varsa yetkiye göre farklı butonlar göster
					  YETKİLER: ADMIN = 1  KULLANICI = 0
				 -->
				<% 
					Object session_id = request.getSession().getAttribute("session_id");
					Object yetki = request.getSession().getAttribute("session_yetki");
						
					if(session_id!=null)
					{ 
						/* yetki bilgisini integer'a dönüştürdük yoksa string olarak
						karşılaştırmada sorun yaşıyoruz */
					int i_yetki = Integer.parseInt(yetki.toString());		
					%>
					
								<!-- Admin için gösterilecekler -->
								<% if( i_yetki == 1 ){ %>
						
								<li><a href="#" id="menu_cihazekle">Cihaz Ekle</a></li>
								<li><a href="#" id="menu_siparisonay">Sorumluları Görüntüle</a></li>
								<li><a href="#" id="menu_kullar">Kullanıcıları Görüntüle</a></li>
								<script type="text/javascript">
									$j("body").css("background-color","#444444");

								</script>
								
								<!-- Düz Kullanıcı için gösterilecekler -->
								<% }else{ %>
								
								<li><a href="#" id="menu_bilgun">Bilgi Güncelle</a></li>
								<li><a href="#" id="menu_siparisgor">Sorumluluk Görüntüle</a></li>
								<script type="text/javascript">
									$j("body").css("background-color","#2222FF");

								</script>
								<% } 
								%>
					<!-- Giriş Yapan Herkeste Gösterilecek Butonlar -->	
					<li><a href="#" id="menu_cihazara">Cihaz Ara</a></li>	
					<li><a href="uyecikis" id="menu_cikis">Çıkış</a></li>
					
								
					<!-- Session yoksa Giriş ve kayıt ol butonları çıksın -->
					<% } else { %>
					<li><a href="#" id="menu_giris">Giriş</a></li>
					<li><a href="#" id="menu_kulekle">Kayıt Ol</a></li>
					<% } %>
			</ul>