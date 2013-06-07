<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="com.da.CihazDAO"%>
<%@page import="com.entity.Cihaz"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.spring.util.SpringFactoryProvider"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext"%>

 <%   		int cihazid = Integer.valueOf(request.getParameter("cihazid"));
 			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
 			Cihaz cihaz = ((CihazDAO)context.getBean("CihazDAO",CihazDAO.class)).CihazDetayiniGetir(cihazid);
 %>


<h3><%= cihaz.getAd() %></h3>
<table style="border:1px solid;border-color:gray">

<tr><td>IP : </td><td><%= cihaz.getIp() %></td></tr>
<tr><td>Üretici : </td><td><%= cihaz.getUretici().getAd() %></td></tr>
<tr><td>Türü : </td><td><%= cihaz.getTur().getAd() %></td></tr>
<tr><td>Port Sayısı : </td><td><%= cihaz.getPortlar().size() %></td></tr>
<tr><td>PoE : </td><td>Var</td></tr>

 </table>

<br>
<br>


<% Object o_session_yetki = request.getSession().getAttribute("session_yetki");
	if(o_session_yetki!=null) {	
		int session_yetki = Integer.valueOf(o_session_yetki.toString());
		if(session_yetki==0){
			%>
	
	<script type="text/javascript">
			$j("#basket, #baskettext").click(
			
			function(){

				$j("#sepetmesaji").html("").slideUp(1);
				
				$j.ajax({
					url: "./sepetekle?cihazid="+<%= cihaz.getId()%>,
					type: "GET",
					datatype: "JSON",
					success: function(data){
						
						$j("#contentplaceholder").load("cihazlar.jsp?turid="+<%=cihaz.getTur().getId()%>);
						$j("#sidemenu").load("./Essentials/a_yanmenu.jsp", function(){


							$j("#sepetmesaji").html("Cihaz Listeye Eklendi!").slideDown(1500);

							});
						
					},
					error: function(data){
					alert("Cihaz Listeye Eklenemedi!");
					}
					
					});
				
				});

	</script>
	
	<a href="#"><img id="basket" src="${pageContext.request.contextPath}/Images/addtolist.png"></img></a><br>
	<a href="#" id="baskettext">Takip Listesine Ekle</a>
			
			<% 		} 
		
				} %>
		