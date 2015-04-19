<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/NHSDrugSuitabilityPortal/home"> <img
				src="<c:url value='/resources/images/nhsLogo.png'/>" alt="NHS Logo"
				style="width: 50px; height: 20px">
			</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<!-- Display menu items in a drop down list -->
				<c:choose>
					<c:when test="${userRole == 'PATIENT'}">
						<li><a href="/NHSDrugSuitabilityPortal/incident/new">New
								Incident</a></li>
						<li><a href="/NHSDrugSuitabilityPortal/incident/view">View
								My Incidents</a></li>
						<li><a href="/NHSDrugSuitabilityPortal/diagnosis/view">View
								My Diagnoses</a></li>
						<li><a href="/NHSDrugSuitabilityPortal/assessment/view">View
								My Assessments</a></li>
					</c:when>
					<c:when test="${userRole == 'DOCTOR'}">
						<li><a href="/NHSDrugSuitabilityPortal/diagnosis">New
								Diagnosis</a></li>
						<li><a href="/NHSDrugSuitabilityPortal/assessment">New
								Assessment</a></li>
						<li><a href="/NHSDrugSuitabilityPortal/user/new">New User</a></li>
						<li><a href="/NHSDrugSuitabilityPortal/search">Search</a></li>
					</c:when>
					<c:when test="${userRole == 'ADMIN'}">
						<li><a href="/NHSDrugSuitabilityPortal/user/new">New User</a></li>
						<li><a href="/NHSDrugSuitabilityPortal/drug/new">New Drug</a></li>
						<li><a href="/NHSDrugSuitabilityPortal/search">Search</a></li>
					</c:when>
				</c:choose>
			</ul>
			<c:choose>
				<c:when test="${not empty username}">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false"> <span
								class="glyphicon glyphicon-user" aria-hidden="true"></span> <c:out
									value=" ${username}" /> <span class="label label-primary"><c:out
										value="${userRole}" /></span> <span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="/NHSDrugSuitabilityPortal/profile">My
										Profile</a></li>
								<li><a
									href="/NHSDrugSuitabilityPortal/profile/accountSettings">Account
										Settings</a></li>
								<li class="divider"></li>
								<li><a href="/NHSDrugSuitabilityPortal/logout">Log Out</a></li>
							</ul></li>
					</ul>
				</c:when>
				<c:otherwise>
					<form class="navbar-form navbar-right" name="loginForm"
						method="POST"
						action="<c:url value='/NHSDrugSuitabilityPortal/j_spring_security_check'/>">
						<div class="form-group">
							<input type="text" class="form-control" id="username"
								name="username" placeholder="Username"> <input
								type="password" class="form-control" id="password"
								name="password" placeholder="Password">
						</div>
						<button id="submit" name="submit" type="submit"
							class="btn btn-primary">Log In</button>
					</form>
				</c:otherwise>
			</c:choose>

		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>