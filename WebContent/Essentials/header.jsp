<!-- üst kısımda ve menü kısmında ne olması gerektiğini burada düzenledik ve
sayfalara include ile ekledik. 
taglib ile başlayan tag jstl komutlarını kullanmak için gereklidir.
c: ile jstl komutlarını kullanabiliriz.
bunlar if foreach gibi komutlar olabilmektedir.
jstl komutlarını kullanabilmek için tag ile birlikte dosyalarını da kütüphaneye eklememiz
gerekir.
-->
<%@page import="com.da.TurDAO"%>
<%@page import="com.entity.Tur"%>
<%@page import="java.util.ArrayList"%>


<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
    	ArrayList<Tur> turler = new TurDAO().butunTurleriGetir();
            request.setAttribute("turler", turler);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Network Shopping</title>

<!-- JS VE CSS KODLARI BURADA EKLENİYOR -->
<script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/jskodlari.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Styles/Style01.css"></link>
<!-- JS VE CSS SON -->
</head>