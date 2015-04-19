<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h2>Record a new Incident</h2>
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

<f:form class="form-horizontal" commandName="form"
	action="/NHSDrugSuitabilityPortal/incident/new/persist"
	method="POST">

	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Drug</label>
		<div class="col-sm-10">
			<c:choose>
				<c:when test="${not empty formContents}">
					<f:select path="drug" class="form-control">
						<f:option value="${formContents.drug}"></f:option>
						<c:forEach items="${availableDrugs}" var="drug">
							<f:option value="${drug.name}"></f:option>
						</c:forEach>
					</f:select>
				</c:when>
				<c:otherwise>
					<f:select path="drug" class="form-control">
						<f:option value="NONE" label="Please Select"></f:option>
						<c:forEach items="${availableDrugs}" var="drug">
							<f:option value="${drug.name}"></f:option>
						</c:forEach>
					</f:select>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="form-group">
		<label for="hight" class="col-sm-2 control-label">Effect</label>
		<div class="col-sm-10">
			<c:choose>
				<c:when test="${not empty formContents}">
					<f:select path="effect" class="form-control">
						<f:option value="${formContents.effect}"></f:option>
						<c:forEach items="${availableEffects}" var="effect">
							<f:option value="${effect.name}"></f:option>
						</c:forEach>
					</f:select>
				</c:when>
				<c:otherwise>
					<f:select path="effect" class="form-control">
						<f:option value="NONE" label="Please Select"></f:option>
						<c:forEach items="${availableEffects}" var="effect">
							<f:option value="${effect.name}"></f:option>
						</c:forEach>
					</f:select>
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