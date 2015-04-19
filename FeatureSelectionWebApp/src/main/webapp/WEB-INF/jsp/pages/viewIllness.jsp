<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-header">
	<h2>View Illness</h2>
</div>

<table class="table table-hover table-bordered">
	<tbody>
		<c:if test="${not empty illness}">
			<tr>
				<td>Name</td>
				<td><c:out value="${illness.name}"/></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><c:out value="${illness.description}"/></td>
			</tr>
		</c:if>
	</tbody>
</table>