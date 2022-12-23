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
    <script src="https://cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
    <style>
      #pageBox {
        position: relative;
      }
    </style>
    <title>Update Message</title>
  </head>
  <body>
    <div class="d-flex">
      <div class="container" id="pageBox">
        <ul class="list-group">
          <li class="list-group-item"><h3>修改留言</h3></li>
        </ul>
        <form action="${contextRoot}/message/update2" method="GET">
          <div class="mb-3">
            <label for="id" class="form-label">編號</label>
            <input
              type="text"
              class="form-control"
              id="idInput"
              name="id"
              value="${message.id}"
              readonly
            />
          </div>
          <div class="mb-3">
            <label for="title" class="form-label">標題</label>
            <input
              type="text"
              class="form-control"
              id="titleInput"
              name="title"
              value="${message.title}"
            />
          </div>

          <div class="mb-3">
            <label for="postDate" class="form-label">發布日期</label>
            <input
              type="datetime-local"
              class="form-control"
              id="postDateInput"
              name="postDate"
              value='<fmt:formatDate value="${message.post_date}" pattern="yyyy-MM-dd'T'HH:mm"/>'
            />
          </div>
          <div class="mb-3">
            <label for="deadLine" class="form-label">截止日期</label>
            <input
              type="datetime-local"
              class="form-control"
              id="deadLineInput"
              name="deadLine"
              value='<fmt:formatDate value="${message.deadline}" pattern="yyyy-MM-dd'T'HH:mm"/>'
            />
          </div>
          <div class="mb-3">
            <label for="postName" class="form-label">發布者</label>
            <input
              type="text"
              class="form-control"
              id="accountInput"
              name="postName"
              readonly
              value="${member.name}"
            />
          </div>
          <div class="input-group">
            <div class="input-group-prepend">
              <span class="input-group-text">公布的內容</span>
            </div>
            <textarea
              class="form-control"
              name="content"
              id="contentTextArea"
            >${message.content}</textarea>
            <script>
              CKEDITOR.replace("content");
            </script>
          </div>
          <button type="submit" class="btn btn-info">送出</button>
        </form>
      </div>
    </div>
  </body>
  <script type="text/javascript"
		src="${contextRoot}/js/jquery-3.6.1.min.js"></script>
	<script type="text/javascript"
		src="${contextRoot}/js/bootstrap.bundle.js"></script>
	<script type="text/javascript" src="${contextRoot}/js/popper.min.js"></script>
</html>
