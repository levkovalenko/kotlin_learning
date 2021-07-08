<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Log in with your account</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</head>

<body>

<div class="row">

    <c:forEach items="${allUsers}" var="user">
    <div class="col-sm-3  align-item-sm-center">
      <div class="card" style="width: 18rem;margin-left:20%; margin-top:10%;">
        <div class="card-body">
          <h5 class="card-title">${user.username}</h5>
            <form action="${pageContext.request.contextPath}/admin" method="post">
              <input type="hidden" name="userId" value="${user.id}"/>
              <input type="hidden" name="action" value="update_role"/>
               <input type="hidden" name="roles" value="1"/>
              <select name="roles" multiple>
                  <c:forEach var="role" items="${roles}">
                      <option value="${role.id}" ${user.hasRole(role) ? 'selected="selected"' : ''}>${role.name}</option>
                  </c:forEach>
              </select>
              <button type="submit"  ${user.checkUserName(current_user) ? 'disabled' : ''}>Update</button>
            </form>
            <form action="${pageContext.request.contextPath}/admin" method="post">
              <input type="hidden" name="userId" value="${user.id}"/>
              <input type="hidden" name="action" value="delete"/>
              <button type="submit"  ${user.checkUserName(current_user) ? 'disabled' : ''}>Delete</button>
          </form>
        </div>
      </div>
      </div>
    </c:forEach>
  <a href="/">Главная</a>
</div>
</body>
</html>