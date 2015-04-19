<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-header">
	<h2>Log In</h2>
</div>

<div id="successMessages">
	<c:if test="${not empty success}">
		<div class="alert alert-success alert-dismissible" role="alert">
			<strong>Success! </strong><c:out value="${success}"></c:out>  
		</div>
	</c:if>
</div>

<form role="form" class="form-horizontal" name="loginForm" method="POST" action="/NHSDrugSuitabilityPortal/j_spring_security_check">
	<div class="form-group">
		<label for="username" class="col-sm-2 control-label">Username: </label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="username" name="username" path="username" />
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">Password: </label>
		<div class="col-sm-10">
			<input type="password" class="form-control" id="password" name="password" path="password" />
		</div>
	</div>
	<div class="form-group">
    	<div class="col-sm-offset-2 col-sm-10">
			<button id="submit" name="submit" type="submit" class="btn btn-primary">Log In</button>
		</div>
	</div>
</form>
