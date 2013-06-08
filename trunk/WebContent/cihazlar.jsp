<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@page import="com.dao.DeviceDAO"%>
		<%@page import="com.entity.Device"%>
		<%@page import="java.util.ArrayList"%>
		<%@page import="org.spring.util.SpringFactoryProvider"%>
	<%@page import="org.springframework.context.support.AbstractApplicationContext"%>
       
       <%
              	int turid = Integer.valueOf(request.getParameter("turid"));
                                   		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
                                   		ArrayList<Device> cihazlar = ((DeviceDAO)context.getBean("CihazDAO",DeviceDAO.class)).tureAitcihazlariGetir(turid);
              %>
<span>Cihazlar Aşağıda</span>
		<ul id="cihazliste-css">
		<%
			for (Device tempcihaz : cihazlar) {
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
