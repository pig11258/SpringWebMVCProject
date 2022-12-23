<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <c:set var="contextRoot" value="${pageContext.request.contextPath}" />
    <link href="${contextRoot}/bootstrap.css/bootstrap.min.css"
	rel="stylesheet">
    <style>
      #pageBox {
        position: relative;
      }
    </style>
    <title>MessageBoard</title>
  </head>
  <body>
    <div class="d-flex">
      <div class="container" id="pageBox">
        <ul class="list-group">
          <li class="list-group-item"><h3>瀏覽公佈事項</h3></li>
        </ul>

        <table class="table">
          <thead class="thead-dark">
            <tr>
              <th scope="col">編號</th>
              <th scope="col">公布者</th>
              <th scope="col">標題</th>
              <th scope="col">公布日期</th>
              <th scope="col">截止日期</th>
              <th scope="col">修改</th>
              <th scope="col">刪除</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${list}" var="message">
              <tr>
                <th scope="row">${message.id}</th>
                <td>${message.member.name}</td>
                <td>${message.title}</td>
                <td><fmt:formatDate value="${message.post_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${message.deadline}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                    <a href="${contextRoot}/message/update/${message.id}"class="btn btn-info" >修改</a>
                </td>
                <td>
                    <a href="${contextRoot}/message/delete/${message.id}" class="btn btn-danger" onclick="return confirm('確定刪除嗎');">刪除</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <div class="container text-center">
        <c:forEach var="pageNumber" begin="1" end="${pageTotal}">
        <c:choose>
        <c:when test="${nowPage==pageNumber}">${pageNumber}</c:when>
        <c:otherwise>
        <a href="${contextRoot}/messagePage/${pageNumber}" >${pageNumber}</a>
        </c:otherwise>
        </c:choose>
        <c:if test="${pageNumber !=  pageTotal}">|</c:if>
        </c:forEach>
        </div>
        <br>
        <a href="${contextRoot}/message/new" class="btn btn-success">新增</a>
        <a href="${contextRoot}/logout" class="btn btn-warning">登出</a>
      </div>
    </div>
  </body>
  <script type="text/javascript"
		src="${contextRoot}/js/jquery-3.6.1.min.js"></script>
	<script type="text/javascript"
		src="${contextRoot}/js/bootstrap.bundle.js"></script>
	<script type="text/javascript" src="${contextRoot}/js/popper.min.js"></script>
</html>
