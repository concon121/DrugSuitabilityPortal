<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h2>New Diagnosis</h2>
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

<f:form class="form-horizontal" commandName="searchForm"
	action="/NHSDrugSuitabilityPortal/diagnosis/search" method="POST">
	<div class="form-group">
		<label for="searchTerm" class="col-sm-2 control-label">Search for Patient: </label>
		<div class="col-sm-10">
			<f:input type="text" class="form-control" id="searchTerm"
				name="searchTerm" path="searchString" />
		</div>
	</div>

	<f:hidden class="form-control" path="entity" value="User" />

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button id="submit" name="submit" type="submit"
				class="btn btn-primary">Search</button>
		</div>
	</div>
</f:form>

<div id="users">

	<c:if test="${not empty user}">
		<div class="page-header">
			<h4>Results</h4>
		</div>
	</c:if>

	<c:forEach items="${user}" var="u">
		<div class="result">
			<f:form method="POST"
				action="/NHSDrugSuitabilityPortal/diagnosis/user"
				commandName="viewUser">
				<div class="col-xs-12 col-sm-6">
					<div class="pull-left">
						<b><c:out value="${u.displayName}" /> </b> <small
							class="sub-heading-entity">User</small>
					</div>
				</div>
				<div class="col-xs-12 col-sm-6">
					<div class="pull-right">
						<f:button class="btn btn-primary" type="submit">
						Choose
						</f:button>
						<f:hidden value="${u.id}" path="userId" />
					</div>
				</div>
			</f:form>
		</div>
	</c:forEach>

</div>

<c:if test="${not empty chosenUser}">
	<div class="row patientInfo">
		<div class="col-xs-12">
			<div class="bordered">
				<div class="page-header">
					<h4>Chosen Patient</h4>
				</div>
				<div class="row">
					<div id="forenameLabel" class="col-xs-12 col-sm-6 col-md-3">
						<b>Forename:</b>
					</div>
					<div id="forenameContent" class="col-xs-12 col-sm-6 col-md-3">
						<c:out value="${chosenUser.forename}" />
					</div>
					<div id="surnameLabel" class="col-xs-12 col-sm-6 col-md-3">
						<b>Surname:</b>
					</div>
					<div id="surnameContent" class="col-xs-12 col-sm-6 col-md-3">
						<c:out value="${chosenUser.surname}" />
					</div>
					<div id="dobLabel" class="col-xs-12 col-sm-6 col-md-3">
						<b>Date of Birth:</b>
					</div>
					<div id="dobContent" class="col-xs-12 col-sm-6 col-md-3">
						<c:out value="${chosenUser.dob}" />
					</div>
					<div id="emailLabel" class="col-xs-12 col-sm-6 col-md-3">
						<b>Email Address: </b>
					</div>
					<div id="emailContent" class="col-xs-12 col-sm-6 col-md-3">
						<c:out value="${chosenUser.emailAddress}" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<f:form class="form-horizontal" commandName="diagnosisForm"
		action="/NHSDrugSuitabilityPortal/diagnosis/user/illness"
		method="POST">
		<div class="form-group">
			<label for="illness" class="col-sm-2 control-label">Illness:</label>
			<div class="col-sm-10">
				<f:select id="illness" items="${illnesses}" class="form-control"
					path="illnessName">
				</f:select>
			</div>
		</div>
		
		<f:hidden path="userId" value="${chosenUser.id}"/>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button id="submit" name="submit" type="submit"
					class="btn btn-primary">Diagnose</button>
			</div>
		</div>
	</f:form>
</c:if>

