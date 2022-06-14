<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>
<div class="container">
	<c:forEach var="board" items="${boards.content }">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title }</h4>
				<div>작성자: ${board.user.username }</div>
				<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>
</div>
<ul class="pagination justify-content-center">
	<li class="page-item previous"><a class="page-link" href="?page=${startPageNo-5}"><<</a></li>
	<c:forEach var="i" begin="${startPageNo}" end="${endPageNo}">
		<c:choose>
			<c:when test="${i eq boards.number+1}">
				<li class="page-item active"><a class="page-link" href="?page=${i}">${i}</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${i}">${i}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<li class="page-item next"><a class="page-link" href="?page=${endPageNo+5}">>></a></li>
</ul>
<!--  
<ul class="pagination justify-content-center" >
	<li class="page-item previous"><a class="page-link" href="?page=${boards.number-1 }">Previous</a></li>
	<c:forEach var="i" begin="1" end="${boards.totalPages}">
		<li class="page-item ${i}" }><a class="page-link" href="?page=${i-1}">${i}</a></li>
	</c:forEach>
	<li class="page-item next"><a class="page-link" href="?page=${boards.number+1 }">Next</a></li>
</ul>
-->
<%@ include file="layout/footer.jsp"%>

