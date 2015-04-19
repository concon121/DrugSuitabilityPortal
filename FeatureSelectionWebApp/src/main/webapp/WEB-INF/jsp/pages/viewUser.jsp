<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-header">
	<h2>View User</h2>
</div>

<table class="table table-hover table-bordered">
	<tbody>
		<c:if test="${not empty user}">
			<tr>
				<td>Forename</td>
				<td><c:out value="${user.forename}"/></td>
			</tr>
			<tr>
				<td>Surname</td>
				<td><c:out value="${user.surname}"/></td>
			</tr>
			<tr>
				<td>Date of Birth</td>
				<td><c:out value="${user.dob}"/></td>
			</tr>
			<tr>
				<td>Email Address</td>
				<td><c:out value="${user.emailAddress}"/></td>
			</tr>
		</c:if>
		<c:if test="${not empty patient}">
			<tr>
				<td>Gender</td>
				<td><c:out value="${patient.gender}"/></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><c:out value="${patient.gender}"/></td>
			</tr>
			<tr>
				<td>Height</td>
				<td><c:out value="${patient.height}"/></td>
			</tr>
			<tr>
				<td>Weight</td>
				<td><c:out value="${patient.weight}"/></td>
			</tr>
			<tr>
				<td>Ethnicity</td>
				<td><c:out value="${patient.ethnicity}"/></td>
			</tr>
			<tr>
				<td>Smoker</td>
				<td><c:out value="${patient.smoker}"/></td>
			</tr>
			<tr>
				<td>Diabetes</td>
				<td><c:out value="${patient.diabetes}"/></td>
			</tr>
			<tr>
				<td>Allergies</td>
				<td><c:out value="${patient.allergies}"/></td>
			</tr>
		</c:if>
	</tbody>
</table>