<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Bootstrap -->
<link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
<!-- Custom CSS -->
<link href="<c:url value='/resources/css/main.css'/>" rel="stylesheet">
<!-- JQuery UI -->
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/themes/smoothness/jquery-ui.css" />

<title><tiles:getAsString name="title" /></title>
</head>
<body>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js'/>" ></script>
	
	<!-- JQuery UI -->
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script>

	<div id="container">
		<header id="header" class="bordered">
			<div id="title">
				<tiles:insertAttribute name="header" />
			</div>
			<div id="menu">
				<tiles:insertAttribute name="menu" />
			</div>
		</header>
		<main id="content" class="bordered">
			<div class="page-header">
				<h2><tiles:getAsString name="errorTitle" />: <tiles:getAsString name="errorCode" /> 
				</h2>
			</div>
			
			<div class="alert alert-danger" role="alert">
				<strong>Error!</strong> <tiles:getAsString name="errorMessage" /> 
			</div>
		</main>
		<footer class="bordered">
			<tiles:insertAttribute name="footer" />
		</footer>
	</div>

</body>
</html>



