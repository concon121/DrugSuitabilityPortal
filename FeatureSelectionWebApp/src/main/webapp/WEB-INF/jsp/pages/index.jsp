<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-header">
	<h2>Drug Suitability Portal</h2>
</div>

<div id="successMessages">
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

<div class="row">

	<c:choose>
		<c:when test="${userRole == 'PATIENT'}">
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>New Incident</h4>
					</div>
					<p>If you have experienced a problem with the medication your
						Doctor has prescribed to you, please log your experience.</p>
					<a href="/NHSDrugSuitabilityPortal/incident/new"
						class="btn btn-primary btn-lg btn-block">Log Incident</a>
				</div>
			</div>
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>View My Incidents</h4>
					</div>
					<p>If you wish to view our record of your past experiences with
						the medication you have been prescribed, please click the button
						below.</p>
					<a href="/NHSDrugSuitabilityPortal/incident/view"
						class="btn btn-primary btn-lg btn-block">View My Incidents</a>
				</div>
			</div>
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>View My Diagnoses</h4>
					</div>
					<p>If you wish to view our record of your past diagnoses made
						by your Doctor, please click the button below.</p>
					<a href="/NHSDrugSuitabilityPortal/diagnosis/view"
						class="btn btn-primary btn-lg btn-block">View My Diagnoses</a>
				</div>
			</div>
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>View My Assessments</h4>
					</div>
					<p>If you wish to view our record of your past assessments made
						by our system to help accurately determine which medication to
						prescribe for you, please click the button below.</p>
					<a href="/NHSDrugSuitabilityPortal/assessment/view"
						class="btn btn-primary btn-lg btn-block">View My Assessments</a>
				</div>
			</div>
		</c:when>
		<c:when test="${userRole == 'DOCTOR'}">
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>New Diagnosis</h4>
					</div>
					<p>After you have diagnosed a patient with an illness, please
						record it in our database.</p>
					<a href="/NHSDrugSuitabilityPortal/diagnosis"
						class="btn btn-primary btn-lg btn-block">New Diagnosis</a>
				</div>
			</div>
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>New Assessment</h4>
					</div>
					<p>Begin a new assessment to determine which course of
						treatment is best suited for your patient.</p>
					<a href="/NHSDrugSuitabilityPortal/assessment"
						class="btn btn-primary btn-lg btn-block">New Assessment</a>
				</div>
			</div>
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>New User</h4>
					</div>
					<p>Register a new User by following the link below. Doctors are
						only permitted to register new Patients.</p>
					<a href="/NHSDrugSuitabilityPortal/user/new"
						class="btn btn-primary btn-lg btn-block">New User</a>

				</div>
			</div>
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>Search</h4>
					</div>
					<p>Search our database for Users, Drugs, Side Effects and
						Illnesses to easily find what you are looking for.</p>
					<a href="/NHSDrugSuitabilityPortal/search"
						class="btn btn-primary btn-lg btn-block">Search</a>
				</div>
			</div>
		</c:when>
		<c:when test="${userRole == 'ADMIN'}">
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>New User</h4>
					</div>
					<p>Register a new User by following the link below. Admin are
						permitted to register new Patients, Doctors and other Admin.</p>
					<a href="/NHSDrugSuitabilityPortal/user/new"
						class="btn btn-primary btn-lg btn-block">New User</a>
				</div>
			</div>
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>New Drug</h4>
					</div>
					<p>Register a new Drug in our database by following the link
						below. Only Admin are permitted to add new Drugs onto the system.</p>
					<a href="/NHSDrugSuitabilityPortal/drug/new"
						class="btn btn-primary btn-lg btn-block">New Drug</a>
				</div>
			</div>
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>New Side Effect</h4>
					</div>
					<p>The side effects which some medications cause need to be
						stored in our database. If a new effect is required, please add it
						to the system.</p>
					<a href="/NHSDrugSuitabilityPortal/effect"
						class="btn btn-primary btn-lg btn-block">New Effect</a>
				</div>
			</div>
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>Search</h4>
					</div>
					<p>Search our database for Users, Drugs, Side Effects and
						Illnesses to easily find what you are looking for.</p>
					<a href="/NHSDrugSuitabilityPortal/search"
						class="btn btn-primary btn-lg btn-block">Search</a>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>New Patients</h4>
					</div>
					<p>We are currently accepting new patients who wish to be part
						of the programme. To register, simply follow the link below and
						fill in the form to create your profile. Once your profile has
						been created you should complete your profile by adding your
						medical information for you Doctors reference.</p>
					<a href="/NHSDrugSuitabilityPortal/user/new"
						class="btn btn-primary btn-lg btn-block">Register</a>
				</div>
			</div>
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>Existing Patients</h4>
					</div>
					<p>If you are an existing user of our system, then please log
						into your account via the link below. Once logged in you will be
						able to access the full functionality of our system for registered
						patients.</p>
					<a href="/NHSDrugSuitabilityPortal/login"
						class="btn btn-primary btn-lg btn-block">Log In</a>
				</div>
			</div>
		</c:otherwise>
	</c:choose>

</div>

