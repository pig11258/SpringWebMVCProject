<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <title>New Message</title>
  </head>
  <body>
    <div class="d-flex">
      <div class="container" id="pageBox">
        <ul class="list-group">
          <li class="list-group-item"><h3>新增留言</h3></li>
        </ul>
        <form action="${contextRoot}/message/create" method="post">
          <div class="mb-3">
            <label for="title" class="form-label">標題</label>
            <input
              type="text"
              class="form-control"
              id="titleInput"
              name="title"
            />
          </div>
          <div class="mb-3">
            <label for="postDate" class="form-label">公布日期</label>
            <input
              type="datetime-local"
              class="form-control"
              id="postDateInput"
              name="postDate"
            />
          </div>
          <div class="mb-3">
            <label for="deadLine" class="form-label">截止日期</label>
            <input
              type="datetime-local"
              class="form-control"
              id="deadLineInput"
              name="deadLine"
            />
          </div>
          <div class="input-group">
            <div class="input-group-prepend">
              <span class="input-group-text">公布的內容</span>
            </div>
            <br/>
            <textarea
              class="form-control"
              name="content"
              id="contentTextArea"
            ></textarea>
            <script>
              CKEDITOR.replace("content"); 
            </script>
          </div>
          <button type="submit" class="btn btn-primary">送出</button>
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
