<%@include file="../fragments/include.jsp" %>
<%--  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"--%>  
    %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC - Hello World Example | brianu</title>
</head>
<body>
<navigation:fragment name="<%= NavigationConstants.TAG_HEAD %>" />

<div id="myheader">
	<navigation:fragment name="<%= NavigationConstants.TAG_HEADER %>" />
  <!-- header will go in here... -->
</div>
	<h2>${message}</h2>
	<h4>Server date time is : ${date} </h4>
	
	<h2>Spring 4 MVC - @RequestMapping example</h2>
	
	<hr/>
	
	<fmt:message key="user.name.empty"/>
	
	<!-- Request One -->
	<form action="/welcome">
		<input type="submit" value="One">
	</form>
	
	<a href="?language=en">English </a> | <a href="?language=es">Spanish </a>
</body>
</html>