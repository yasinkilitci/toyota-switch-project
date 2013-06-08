<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.dao.DeviceTypeDAO"%>
    <%@page import="com.dao.DeviceDAO"%>
	<%@page import="com.entity.DeviceType"%>
	<%@page import="com.entity.Device"%>
	<%@page import="com.entity.Port"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="org.spring.util.SpringFactoryProvider"%>
	<%@page import="org.springframework.context.support.AbstractApplicationContext"%>
	
	<%
			Object o_cihaz = request.getParameter("cihazid");
			
				if(o_cihaz!=null){
					
					AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
					int cihaz_id = Integer.valueOf(o_cihaz.toString());
					ArrayList<Port> portlar = context.getBean("CihazDAO",DeviceDAO.class).portlariGetir(cihaz_id);
					String cihazadi = portlar.get(0).getCihaz().getAd();
		%>
					
					<table>
						<tr>
							<td colspan="3"><%=cihazadi %> - PORT DETAYLARI</td>
						
						</tr>
						<tr>
							<td>Port Adı</td>
							<td>Son Erişim Zamanı</td>
							<td>İşlem</td>
						</tr>
					
					<%
					
					for(Port port: portlar)
					{
						int portid = port.getId();
						String portadi = port.getName();
						String jportgun = "'#btnEris" + Integer.valueOf(portid) + "'";
						%>
							<tr>
								<td>
								<%= portadi %>
								</td>
								<td>
								<%= port.getSonerisim().toString() %>
								</td>
								<td>
								<input type="button" id="btnEris<%= portid %>" value="Porta Eriş"/>
									<script type="text/javascript">
									$j(<%=jportgun %>).click(
										function(){
											var port_id = <%=portid %>;
									$j.ajax({
										url: "./portislem?portid="+port_id,
										type: "GET",
										datatype: "JSON",
										success: function(data){
											alert("Porta Başarıyla Erişildi!");
											$j("#contentplaceholder").load("./Pages_Admin/a_portlar.jsp?cihazid="+<%=cihaz_id%>);
										},
										error: function(request, textStatus, errorThrown){
											alert(port_id + " No'lu Porta Erişilemedi!");
										}
										
										});

										});
	
									</script>
								</td>
							</tr>
						<%
					}
		}
	
	%>
	
	</table>