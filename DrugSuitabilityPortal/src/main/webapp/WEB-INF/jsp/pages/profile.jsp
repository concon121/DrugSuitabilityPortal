<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-header">
	<h2>Profile</h2>
</div>


<div id="profileContent">

	<div id="successMessages">
		<c:if test="${not empty success}">
			<div class="alert alert-success alert-dismissible" role="alert">
 				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  				<strong>Success! </strong> <c:out value="${success}"></c:out>
			</div>
		</c:if>
	</div>

	<div class="row">
		<div class="col-xs-12 col-md-6">
			<div class="bordered">
				<div class="page-header">
					<h4>User Details</h4>
				</div>
				<form action="#">
					<div class="form-group">
						<label for="forename">Forename:</label>
						<input class="form-control" type="text" placeholder="<c:out value=' ${userProfile.forename}'/>" readonly>
					</div>
					<div class="form-group">
						<label for="surname">Surname:</label>
						<input class="form-control" type="text" placeholder="<c:out value=' ${userProfile.surname}'/>" readonly>
					</div>
					<div class="form-group">
						<label for="email">Email Address:</label>
						<input class="form-control" type="text" placeholder="<c:out value=' ${userProfile.emailAddress}'/>" readonly>
					</div>
					<div class="form-group">
						<label for="dob">Date of Birth:</label>
						<input class="form-control" type="text" placeholder="<c:out value=' ${userProfile.dob}'/>" readonly>
					</div>
					<div class="form-group">
						<a href="/NHSDrugSuitabilityPortal/user/update" class="btn btn-primary btn-lg btn-block">Update User Details
						</a>
					</div>
				</form>
			</div>
		</div>
		
		<c:if test="${userRole == 'PATIENT'}">
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>Patient Details</h4>
					</div>
					<form action="#">
					<div class="form-group">
						<label for="forename">Height:</label>
						<div class="input-group">
							<input class="form-control" type="text" placeholder="<c:out value=' ${patientDetails.height}'/>" readonly aria-describedby="basic-addon1">
							<span class="input-group-addon" id="basic-addon1">cm</span>
						</div>
					</div>
					<div class="form-group">
						<label for="surname">Weight:</label>
						<div class="input-group">
							<input class="form-control" type="text" placeholder="<c:out value=' ${patientDetails.weight}'/>" readonly aria-describedby="basic-addon2">
							<span class="input-group-addon" id="basic-addon2">kg</span>
						</div>
					</div>
					<div class="form-group">
						<label for="email">Ethnicity:</label>
						<input class="form-control" type="text" placeholder="<c:out value=' ${patientDetails.ethnicity}'/>" readonly>
					</div>
					<div class="form-group">
						<label for="email">Gender:</label>
						<input class="form-control" type="text" placeholder="<c:out value=' ${patientDetails.gender}'/>" readonly>
					</div>
					<div class="form-group">
						<label for="dob">Smoker:</label>
						<input class="form-control" type="text" placeholder="<c:out value=' ${patientDetails.smoker}'/>" readonly>
					</div>
					<div class="form-group">
						<label for="dob">Diabetes:</label>
						<input class="form-control" type="text" placeholder="<c:out value=' ${patientDetails.diabetes}'/>" readonly>
					</div>
					<div class="form-group">
						<label for="dob">Allergies:</label>
						<input class="form-control" type="text" placeholder="<c:out value=' ${patientDetails.allergies}'/>" readonly>
					</div>
					<div class="form-group">
						<a href="/NHSDrugSuitabilityPortal/patient/update">
							<button type="button" class="btn btn-primary btn-lg btn-block">Update Patient Details</button>
						</a>
					</div>
				</form>
				</div>
			</div>
		</c:if>
		
		

		<div class="col-xs-12">
			<div class="bordered">
				<div class="page-header">
					<h4>Account Settings</h4>
				</div>
				<a href="/NHSDrugSuitabilityPortal/user/update" class="btn btn-primary btn-lg btn-block">Update User Details<br />
				</a>
				<c:if test="${userRole == 'PATIENT'}">
					<a href="/NHSDrugSuitabilityPortal/patient/update" class="btn btn-primary btn-lg btn-block">Update Patient Details<br />
					</a>
				</c:if>
				<a href="/NHSDrugSuitabilityPortal/access/update" class="btn btn-primary btn-lg btn-block">Update Password
				</a>
			</div>
		</div>

	</div>

</div>