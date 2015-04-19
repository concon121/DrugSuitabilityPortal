<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h2>Register New Drug</h2>
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
	action="/NHSDrugSuitabilityPortal/drug/effect/search" method="POST">
	<div class="form-group">
		<label for="searchTerm" class="col-sm-2 control-label">Search for Effects: </label>
		<div class="col-sm-10">
			<f:input type="text" class="form-control" id="searchTerm"
				name="searchTerm" path="searchString" />
		</div>
	</div>

	<f:hidden class="form-control" path="entity" value="Effect" />

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button id="submit" name="submit" type="submit"
				class="btn btn-primary">Search</button>
		</div>
	</div>
</f:form>

<div id="effect">

	<c:if test="${not empty effect}">
		<div class="page-header">
			<h4>Results</h4>
		</div>
	</c:if>

	<c:forEach items="${effect}" var="u">
		<div class="result">
			<f:form method="POST"
				action="/NHSDrugSuitabilityPortal/effect/view"
				commandName="viewEffect">
				<div class="col-xs-12 col-sm-6">
					<div class="pull-left">
						<b><c:out value="${u.displayName}" /> </b> <small
							class="sub-heading-entity">Effect</small>
					</div>
				</div>
				<div class="col-xs-12 col-sm-6">
					<div class="pull-right">
						<f:button class="btn btn-primary" type="submit">
						View
						</f:button>
						<f:hidden value="${u.id}" path="effectId" />
					</div>
				</div>
			</f:form>
		</div>
	</c:forEach>

</div>

<f:form class="form-horizontal" commandName="form"
	action="/NHSDrugSuitabilityPortal/drug/new/persist"
	method="POST">

	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Name</label>
		<div class="col-sm-10">
			<c:choose>
				<c:when test="${not empty formContents}">
					<f:input type="text" class="form-control" id="name"
						value="${formContents.name}" path="name" />
				</c:when>
				<c:otherwise>
					<f:input type="text" class="form-control" id="name"
						placeholder="Name" path="name" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Description</label>
		<div class="col-sm-10">
			<c:choose>
				<c:when test="${not empty formContents}">
					<f:input type="text" class="form-control" id="description"
						value="${formContents.description}" path="description" />
				</c:when>
				<c:otherwise>
					<f:input type="text" class="form-control" id="description"
						placeholder="Description" path="description" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Associated Allergies</label>
		<div class="col-sm-10">
			<c:choose>
				<c:when test="${not empty formContents}">
					<f:input type="text" class="form-control" id="allergies"
						value="${formContents.allergies}" path="allergies" />
				</c:when>
				<c:otherwise>
					<f:input type="text" class="form-control" id="allergies"
						placeholder="Comma separated list of allergies" path="allergies" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Side Effects</label>
		<div class="col-sm-10">
			<c:choose>
				<c:when test="${not empty formContents}">
					<f:input type="text" class="form-control" id="effects"
						value="${formContents.effects}" path="effects" />
				</c:when>
				<c:otherwise>
					<f:input type="text" class="form-control" id="effects"
						placeholder="Comma separated list of effects" path="effects" />
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