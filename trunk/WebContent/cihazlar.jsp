<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@page import="com.da.CihazDAO"%>
		<%@page import="com.entity.Cihaz"%>
		<%@page import="java.util.ArrayList"%>
       
       <%   int turid = Integer.valueOf(request.getParameter("turid"));
    		ArrayList<Cihaz> cihazlar = new CihazDAO().TureAitcihazlariGetir(turid);
    	%>
<span>Cihazlar Aşağıda</span>
		<ul id="cihazliste-css">
		<% for (Cihaz tempcihaz : cihazlar) {
					String cihazid = String.valueOf(tempcihaz.getId());
					String cihazad = tempcihaz.getAd();
					String jcihazid = "'#cihaz" + cihazid + "'";
			%>

			<li><a href="#" id="cihaz<%=cihazid%>"><%=cihazad%></a></li>
			<script type="text/javascript">
					$j(<%=jcihazid %>).click(

						function(){
							$j("#contentplaceholder").load("cihazDetay.jsp?cihazid="+<%=cihazid%>);
								
							});

			</script>
			 
			<%
			}
			%>
		</ul>
