<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-header">
	<h2>View Drug</h2>
</div>

<table class="table table-hover table-bordered">
	<tbody>
		<c:if test="${not empty drug}">
			<tr>
				<td>Name</td>
				<td><c:out value="${drug.name}"/></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><c:out value="${drug.description}"/></td>
			</tr>
			<tr>
				<td>Associated Allergies</td>
				<td><c:out value="${drug.allergies}"/></td>
			</tr>
		</c:if>
	</tbody>
</table>