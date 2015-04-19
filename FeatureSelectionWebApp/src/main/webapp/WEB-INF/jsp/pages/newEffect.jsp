<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h2>Register New Side Effect</h2>
</div>

<f:form class="form-horizontal" commandName="form"
	action="/NHSDrugSuitabilityPortal/effect/new" method="POST">

	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Name</label>
		<div class="col-sm-10">
			<f:input type="text" class="form-control" id="name"
				placeholder="Name" path="name" />
		</div>
	</div>

	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Description</label>
		<div class="col-sm-10">
			<f:input type="text" class="form-control" id="description"
				placeholder="Description" path="description" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button id="submit" name="submit" type="submit"
				class="btn btn-primary">Submit</button>
		</div>
	</div>

</f:form>