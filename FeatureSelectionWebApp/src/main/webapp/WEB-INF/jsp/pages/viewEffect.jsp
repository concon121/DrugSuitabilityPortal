<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-header">
	<h2>View Effect</h2>
</div>

<div class="messages">
	<c:if test="${not empty error}">
		<c:forEach items="${error}" var="err">
			<div class="alert alert-warning alert-dismissible" role="alert">
 				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  				<strong>Warning! </strong> <c:out value="${err}"></c:out>
			</div>
		</c:forEach>
	</c:if>
</div>

<table class="table table-hover table-bordered">
	<tbody>
		<c:if test="${not empty effect}">
			<tr>
				<td>Name</td>
				<td><c:out value="${effect.name}"/></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><c:out value="${effect.description}"/></td>
			</tr>
		</c:if>
	</tbody>
</table>