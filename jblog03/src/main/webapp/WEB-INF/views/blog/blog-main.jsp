<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% pageContext.setAttribute("newLine", "\n"); %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:choose>
						<c:when test="${empty viewPost}">
							<h4>${postList[0].title}</h4>
							<p>${fn:replace(postList[0].contents, newLine, "<br>")}</p>						
						</c:when>
						<c:otherwise>
							<h4>${viewPost.title}</h4>
							<p>${fn:replace(viewPost.contents, newLine, "<br>")}</p>				
						</c:otherwise>
					</c:choose>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList}" var="vo" varStatus="status">
						<li><a href="${pageContext.request.contextPath}/${blogVo.blogId}/${vo.categoryId}/${vo.id}">${vo.title}</a> <span>${vo.regDate}</span></li>					
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.profile}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList}" var="vo" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/${vo.blogId}/${vo.id}">${vo.name}</a></li>				
				</c:forEach>
			</ul>
		</div>
		
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>