<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h2>Search</h2>
</div>

<f:form class="form-horizontal" commandName="searchForm"
	action="/NHSDrugSuitabilityPortal/search/results"
	method="POST">	
	<div class="form-group">
		<label for="searchTerm" class="col-sm-2 control-label">Search Term: </label>
		<div class="col-sm-10">
			<f:input type="text" class="form-control" id="searchTerm" name="searchTerm" path="searchString" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="entity" class="col-sm-2 control-label">Type: </label>
		<div class="col-sm-10">
			<f:select id="entity" class="form-control" path="entity">
				<f:option value="User"></f:option>
				<f:option value="Drug"></f:option>
				<f:option value="Illness"></f:option>
				<f:option value="Effect"></f:option>
			</f:select>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button id="submit" name="submit" type="submit"
				class="btn btn-primary">Search</button>
		</div>
	</div>
</f:form>
