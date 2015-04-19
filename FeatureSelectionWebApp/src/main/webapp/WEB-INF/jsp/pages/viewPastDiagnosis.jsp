<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-header">
	<h2>My Diagnosis</h2>
</div>

<div class="messages">
	<c:if test="${not empty error}">
		<c:forEach items="${error}" var="err">
			<div class="alert alert-warning alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>Warning! </strong>
				<c:out value="${err}"></c:out>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${not empty success}">
		<div class="alert alert-success alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>Success! </strong>
			<c:out value="${success}"></c:out>
		</div>
	</c:if>
</div>

<c:if test="${not empty items}">
	<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th>Illness Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${items}" var="item">
				<tr>
					<td><c:out value="${item.illnessName}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>