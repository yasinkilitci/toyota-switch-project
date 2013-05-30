<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<jsp:include page="./Essentials/header.jsp"/>
<body>
		<div class=page>
			<div class="top">
				<div id="banner"></div>
				<div id="mainmenu">
					<jsp:include page="./Essentials/a_anamenu.jsp"/>
				</div>
			</div>
			<div class="middle">
				<div id="sidemenu">
					<jsp:include page="./Essentials/a_yanmenu.jsp"/>
				</div>
				<div id="contentplaceholder"></div>
			</div>
			<div id="footer"><jsp:include page="./Essentials/a_footer.jsp"/></div>
		
		</div>
</body>

