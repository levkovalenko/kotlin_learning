<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>

<%! 
String getFormattedDate(int date) 
{ 
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss"); 
    return sdf.format(new Date(121, 3, date)); 
} 
%>
<%! 
String getDeveloperName() 
{ 
    return "Lev Kovalenko"; 
} 
%>
<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Добро пожаловать, JSP!</title>
  </head>
  <body>
       <h1>Добро пожаловать!</h1>
       <i>Разработчик <%= getDeveloperName() %></i><br>
       <i>Дата получения задания <%= getFormattedDate(10) %></i><br>
       <i>Дата выполнения задания <%= getFormattedDate(17) %></i><br>
  </body>
</html>