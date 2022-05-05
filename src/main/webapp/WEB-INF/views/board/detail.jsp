<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<button class="btn btn-success" onclick="history.back()">돌아가기</button>
	<c:if test="${board.user.id == principal.user.id }">
		<a href="/board/${board.id }/updateForm" id="updatebtn" class="btn btn-warning">수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<br />
	<br />
	<table class="table">
		<thead class="thead">
			<tr>
				<th><h2>제목: ${board.title }</h2></th>

			</tr>
		</thead>
		<tbody>
			<tr>
				<td>작성자: ${board.user.username } </td>
				<td>글 번호: ${board.id }</td>
				<td>조회수: ${board.count }</td>
			</tr>
		</tbody>
	</table>
	<hr />
	<div>
		<div>${board.content }</div>
	</div>
	<hr />

	<div class="card">
		<div class="card-body">
			<textarea id="reply-content" class="form-control" rows="1" cols="2"></textarea>
		</div>
		<div class="card-footer">
			<button id="btn-reply-save" class="btn btn-primary">등록</button>
		</div>
	</div>
	<br />

	<div class="card">
		<input type="hidden" id="boardId" value="${board.id }" />
		<div class="card-header">댓글 리스트</div>
		<ul id="reply-box" class="list-group">
			<c:forEach var="reply" items="${board.replys }">
				<li id="reply-${reply.id }" class="list-group-item d-flex justify-content-between">
					<div>${reply.content }</div>
					<div class="d-flex">
						<div class="font-italic">작성자: ${reply.user.username} &nbsp;</div>
						<c:if test="${reply.user.id eq principal.user.id}">
							<button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>

	</div>

</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>