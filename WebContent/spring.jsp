<%@ page language="java" contentType="text/html; charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>

<%@page import="org.spring.util.SpringFactoryProvider"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext"%>

<!-- Spring'in ApplicationContext'ini burada 1 kere ba�ta olu�turuyoruz. Bellekte
yer ald��� i�in art�k tekrar talep etti�imizde o �rnek bize ula��yor -->
<% AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext(); %>