<%@ page language="java" contentType="text/html; charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 		
 		<%@page import="com.dao.VendorDAO"%>
 		<%@page import="com.dao.DeviceTypeDAO"%>
 		<%@page import="com.entity.Vendor"%>
 		<%@page import="com.entity.DeviceType"%>
		<%@page import="java.util.ArrayList"%>
		
		<%@page import="org.spring.util.SpringFactoryProvider"%>
		<%@page import="org.springframework.context.support.AbstractApplicationContext"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/cihazekle.js"></script>

<%
	/***** SPRING ******/
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		ArrayList<DeviceType> listeTur = ((DeviceTypeDAO)context.getBean("TurDAO")).butunTurleriGetir();
		ArrayList<Vendor> listeUretici = ((VendorDAO)context.getBean("UreticiDAO")).butunureticileriGetir();
		/***** SPRING ******/
%>
	<table>
		<tr><td>Cihaz Adý</td><td><input type="text" id="cihazad"  name="cihazad"></td>   </tr>
		<tr><td>IP</td><td><input type="text" id="cihazip"  name="cihazip"></td>   </tr>
			<tr><td>Türü</td>
		
				<td>
		
					<select id="cbTur" name="cbTur">	
						<%
								for (DeviceType temptur : listeTur) 
																															
																														{
																														String turid = String.valueOf(temptur.getId());
																														String turad = temptur.getAd();
							%>
						<option value="<%=turid%>"><%=turad%></option>
					<%
						}
					%>
					</select>
				</td>
			</tr>
			
			
		<tr><td>Üretici</td>
		
				<td>
		
					<select id="cbUretici" name="cbUretici">	
						<%
								for (Vendor tempuretici : listeUretici) 
																										
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
