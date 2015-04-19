<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h2>Update User Details</h2>
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
	<c:if test="${not empty success}">
		<div class="alert alert-success alert-dismissible" role="alert">
 				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  				<strong>Success! </strong> <c:out value="${success}"></c:out>
		</div>
	</c:if>
</div>

<f:form class="form-horizontal" commandName="userDetails"
	action="/NHSDrugSuitabilityPortal/user/update/persist"
	method="POST">

	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Forename</label>
		<div class="col-sm-10">
			<c:choose>
				<c:when test="${not empty formContents}">
					<f:input type="text" class="form-control" id="forename"
						value="${formContents.forename}" path="forename" />
				</c:when>
				<c:otherwise>
					<f:input type="text" class="form-control" id="forename"
						placeholder="Email" path="forename" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Surname</label>
		<div class="col-sm-10">
			<c:choose>
				<c:when test="${not empty formContents}">
					<f:input type="text" class="form-control" id="surname"
						value="${formContents.surname}" path="surname" />
				</c:when>
				<c:otherwise>
					<f:input type="text" class="form-control" id="surname"
						placeholder="Surname" path="surname" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Email</label>
		<div class="col-sm-10">
			<c:choose>
				<c:when test="${not empty formContents}">
					<f:input type="email" class="form-control" id="emailAddress"
						value="${formContents.emailAddress}" path="emailAddress" />
				</c:when>
				<c:otherwise>
					<f:input type="email" class="form-control" id="emailAddress"
						placeholder="Email" path="emailAddress" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Date of Birth</label>
		<div class="col-sm-10">
			<c:choose>
				<c:when test="${not empty formContents}">
					<f:input type="date" class="form-control" id="dob"
						value="${formContents.dob}" path="dob" />
				</c:when>
				<c:otherwise>
					<f:input type="date" class="form-control" id="dob" path="dob" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button id="submit" name="submit" type="submit"
				class="btn btn-primary">Submit</button>
		</div>
	</div>

</f:form>