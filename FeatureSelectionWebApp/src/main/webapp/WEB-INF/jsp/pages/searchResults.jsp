<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h2>Results</h2>
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

<div id="users">

	<c:forEach items="${user}" var="u">
		<div class="result">
			<f:form method="POST" action="/NHSDrugSuitabilityPortal/user/view"
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
						View Details
						</f:button>
						<f:hidden value="${u.id}" path="userId" />
					</div>
				</div>
			</f:form>
		</div>
	</c:forEach>

</div>

<div id="drugs">

	<c:forEach items="${drug}" var="d">
		<div class="result">
			<f:form method="POST" action="/NHSDrugSuitabilityPortal/drug/view"
				commandName="viewDrug">
				<div class="col-xs-12 col-sm-6">
					<div class="pull-left">
						<b><c:out value="${d.displayName}" /> </b> <small
							class="sub-heading-entity">Drug </small>
					</div>
				</div>
				<div class="col-xs-12 col-sm-6">
					<div class="pull-right">
						<f:button class="btn btn-primary" type="submit">
						View Details
						</f:button>
						<f:hidden value="${d.id}" path="drugId" />
					</div>
				</div>
			</f:form>
		</div>
	</c:forEach>

</div>

<div id="illnesses">

	<c:forEach items="${illness}" var="i">
		<div class="result">
			<f:form method="POST" action="/NHSDrugSuitabilityPortal/illness/view"
				commandName="viewIllness">
				<div class="col-xs-12 col-sm-6">
					<div class="pull-left">
						<b><c:out value="${i.displayName}" /> </b> <small
							class="sub-heading-entity">Illness</small>
					</div>
				</div>
				<div class="col-xs-12 col-sm-6">
					<div class="pull-right">
						<f:button class="btn btn-primary" type="submit">
						View Details
						</f:button>
						<f:hidden value="${i.id}" path="illnessId" />
					</div>
				</div>
			</f:form>
		</div>
	</c:forEach>

</div>

<div id="effects">

	<c:forEach items="${effect}" var="i">
		<div class="result">
			<f:form method="POST" action="/NHSDrugSuitabilityPortal/effect/view"
				commandName="viewEffect">
				<div class="col-xs-12 col-sm-6">
					<div class="pull-left">
						<b><c:out value="${i.displayName}" /> </b> <small
							class="sub-heading-entity">Effect</small>
					</div>
				</div>
				<div class="col-xs-12 col-sm-6">
					<div class="pull-right">
						<f:button class="btn btn-primary" type="submit">
						View Details
						</f:button>
						<f:hidden value="${i.id}" path="effectId" />
					</div>
				</div>
			</f:form>
		</div>
	</c:forEach>

</div>

