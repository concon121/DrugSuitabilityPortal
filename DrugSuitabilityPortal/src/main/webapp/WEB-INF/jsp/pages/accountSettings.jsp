<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-header">
	<h2>Account Settings</h2>
</div>

<div class="row">
		<div class="col-xs-12 col-md-6">
			<div class="bordered">
				<div class="page-header">
					<h4>Update User Details</h4>
				</div>
				<p>Your user details tell us what you are called, how old you are and how to contact you.  If you need to update this information, for example if you change your name, simply fill out the form by following the link below.</p>
				<a href="/NHSDrugSuitabilityPortal/user/update" class="btn btn-primary btn-lg btn-block">Update User Details<br />
				</a>
			</div>
		</div>
		<c:if test="${userRole == 'PATIENT'}">
			<div class="col-xs-12 col-md-6">
				<div class="bordered">
					<div class="page-header">
						<h4>Update Patient Details</h4>
					</div>
					<p>We store extra medical information about our registered patients which is used to help improve our system.  Please keep this information up to date and accurate by quickly filling out the form on the next page.</p>
					<a href="/NHSDrugSuitabilityPortal/patient/update" class="btn btn-primary btn-lg btn-block">Update Patient Details<br />
					</a>
				</div>
			</div>
		</c:if>
		<div class="col-xs-12 col-md-6">
			<div class="bordered">
				<div class="page-header">
					<h4>Update Password</h4>
				</div>
				<p>If you think your account may have been compromised, you can change your password by clicking the button below and completing the on screen form.</p>
				<a href="/NHSDrugSuitabilityPortal/access/update"  class="btn btn-primary btn-lg btn-block">Update Password
				</a>
			</div>
		</div>
</div>