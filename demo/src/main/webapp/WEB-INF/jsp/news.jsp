<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Новости</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

</head>
<body>
<div>
    <h2>Новости <br> Только для залогинившихся пользователей.</h2>
    <div class="row">

    <c:forEach items="${articles}" var="article">
    <div class="col-sm-6  align-item-sm-center">
      <div class="card" style="width: 90%;margin: 5%;">
        <div class="card-body">
          <h4 class="card-title">${article.title}</h4>
          <h5 class="card-subtitle">${article.user.username}</h5>
          <c:set var = "preview_text" value = "${fn:substring(article.text, 0, 300)}" />
            <p class="card-text">${preview_text}...</p>
            
        </div>
      </div>
      </div>
    </c:forEach>
</div>
    <a href="/">Главная</a>
</div>
</body>
</html>