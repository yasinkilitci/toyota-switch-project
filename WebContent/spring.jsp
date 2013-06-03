<%@ page language="java" contentType="text/html; charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>

<%@page import="org.spring.util.SpringFactoryProvider"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext"%>

<!-- Spring'in ApplicationContext'ini burada 1 kere baþta oluþturuyoruz. Bellekte
yer aldýðý için artýk tekrar talep ettiðimizde o örnek bize ulaþýyor -->
<% AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext(); %>