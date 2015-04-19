<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h2>Update Patient Details</h2>
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
  
<f:form class="form-horizontal" commandName="patientDetails" action="/NHSDrugSuitabilityPortal/patient/update/persist" method="POST">
		
	  	<div class="form-group">
	  		<label for="hight" class="col-sm-2 control-label">Height</label>
	    	<div class="col-sm-10">
	    		<div class="input-group">
	    			<c:choose>
	    				<c:when test="${not empty formContents}">
	    					<f:input type="text" class="form-control" id="height" value="${formContents.height}" path="height" aria-describedby="basic-addon1"/>
	      					<span class="input-group-addon" id="basic-addon1">cm</span>
	    				</c:when>
	    				<c:otherwise>
	    					<f:input type="text" class="form-control" id="height" placeholder="Height" path="height" aria-describedby="basic-addon1"/>
	      					<span class="input-group-addon" id="basic-addon1">cm</span>
	    				</c:otherwise>
	    			</c:choose>
	      		</div>
	    	</div>
	  	</div>
	  	<div class="form-group">
	  		<label for="weight" class="col-sm-2 control-label">Weight</label>
	    	<div class="col-sm-10">
	    		<div class="input-group">
					<c:choose>
						<c:when test="${not empty formContents}">
							<f:input type="text" class="form-control" id="weight" value="${formContents.weight}" path="weight" aria-describedby="basic-addon2"/>
	      					<span class="input-group-addon" id="basic-addon2">kg</span>
						</c:when>
						<c:otherwise>
							<f:input type="text" class="form-control" id="weight" placeholder="Weight" path="weight" aria-describedby="basic-addon2"/>
	      					<span class="input-group-addon" id="basic-addon2">kg</span>
						</c:otherwise>
					</c:choose>	    		
	      		</div>
	    	</div>
	  	</div>
	  	<div class="form-group">
	  		<label for="ethnicity" class="col-sm-2 control-label">Ethnicity</label>
	  		<c:set var="first" value="true"/>
		  	<c:forEach items="${availableEthnicities}" var="ethnicity">
		  		<c:choose>
			  		<c:when test="${first == true}">
			  			<div class="checkbox col-sm-10">
			  				<label>
								<f:radiobutton path="ethnicity" value="${ethnicity}"/>
								<c:out value="${ethnicity}"/>
							</label>
						</div>
			  			<c:set var="first" value="false"/>
			  		</c:when>
			  		<c:otherwise>
			  			<div class="col-sm-2"></div>
			  			<div class="checkbox col-sm-10">
				  			<label>
								<f:radiobutton path="ethnicity" value="${ethnicity}"/>
								<c:out value="${ethnicity}"/>
							</label>
						</div>
			  		</c:otherwise>
		  		</c:choose>
			</c:forEach>
		</div>
		<div class="form-group">
	  		<label for="gender" class="col-sm-2 control-label">Gender</label>
	  		<c:set var="first" value="true"/>
		  	<c:forEach items="${availableGenders}" var="gender">
		  		<c:choose>
			  		<c:when test="${first == true}">
			  			<div class="checkbox col-sm-10">
			  				<label>
								<f:radiobutton path="gender" value="${gender}"/>
								<c:out value="${gender}"/>
							</label>
						</div>
			  			<c:set var="first" value="false"/>
			  		</c:when>
			  		<c:otherwise>
			  			<div class="col-sm-2"></div>
			  			<div class="checkbox col-sm-10">
				  			<label>
								<f:radiobutton path="gender" value="${gender}"/>
								<c:out value="${gender}"/>
							</label>
						</div>
			  		</c:otherwise>
		  		</c:choose>
			</c:forEach>
		</div>
		<div class="form-group">
			<label for="smoker" class="col-sm-2 control-label">Smoker</label>
			<div class="checkbox col-sm-10">
				<label>
					<f:radiobutton path="smoker" value="YES"/>
					<c:out value="YES"/>
				</label>
			</div>
			<div class="col-sm-2"></div>
			<div class="checkbox col-sm-10">
				<label>
					<f:radiobutton path="smoker" value="NO"/>
					<c:out value="NO"/>
				</label>
			</div>
		</div>
		<div class="form-group">
			<label for="diabetes" class="col-sm-2 control-label">Diabetes</label>
			<div class="checkbox col-sm-10">
				<label>
					<f:radiobutton path="diabetes" value="YES"/>
					<c:out value="YES"/>
				</label>
			</div>
			<div class="col-sm-2"></div>
			<div class="checkbox col-sm-10">
				<label>
					<f:radiobutton path="diabetes" value="NO"/>
					<c:out value="NO"/>
				</label>
			</div>
		</div>
		<div class="form-group" id="allergies">
			<label for="allergy" class="col-sm-2 control-label">Allergies</label>
	    	<div class="col-sm-10">
	    		<c:choose>
					<c:when test="${not empty formContents}">
						<f:input type="text" class="form-control" id="height" value="${formContents.allergies}" path="allergies" />
					</c:when>
					<c:otherwise>
						<f:input type="text" class="form-control" id="height" placeholder="Comma separated list of Allergies" path="allergies" />
					</c:otherwise>
				</c:choose>
	       	</div>
		</div>
		<div class="form-group">
		  	<div class="col-sm-offset-2 col-sm-10">
				<button id="submit" name="submit" type="submit" class="btn btn-primary">Submit</button>
			</div>
  		</div>
	  	
</f:form>