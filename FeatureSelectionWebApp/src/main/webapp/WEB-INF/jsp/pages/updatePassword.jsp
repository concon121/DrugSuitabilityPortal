<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h2>Update Password</h2>
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

<f:form class="form-horizontal" commandName="form"
	action="/NHSDrugSuitabilityPortal/access/update/persist"
	method="POST">
	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Password</label>
		<div class="col-sm-10">
			<f:input type="password" class="form-control" id="password"
				placeholder="Password" path="password" />
		</div>
	</div>
	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Confirm Password</label>
		<div class="col-sm-10">
			<f:input type="password" class="form-control" id="confirmPassword"
				placeholder="Confirm Password" path="passwordConfirmation" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button id="submit" name="submit" type="submit"
				class="btn btn-primary">Submit</button>
		</div>
	</div>
</f:form>