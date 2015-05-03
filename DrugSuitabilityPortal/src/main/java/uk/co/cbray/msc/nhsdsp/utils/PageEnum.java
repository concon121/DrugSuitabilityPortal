package uk.co.cbray.msc.nhsdsp.utils;

public enum PageEnum {

	ACCESS_DENIED("accessDenied"),
	ACCOUNT_SETTINGS("accountSettings"),
	ASSESSMENT("assessment"),
	DIAGNOSIS("diagnosis"),
	HOME("home"),
	INDEX_DATA("indexData"),
	LOGIN("login"),
	NEW_DRUG("newDrug"),
	NEW_EFFECT("newEffect"),
	NEW_INCIDENT("newIncident"),
	NEW_USER_FORM("newUserForm"),
	PAGE_NOT_FOUND("pageNotFound"),
	PROFILE("profile"),
	SEARCH("search"),
	SEARCH_RESULTS("searchResults"),
	UNKNOWN_ERROR("unknownError"),
	UPDATE_PASSWORD("updatePassword"),
	UPDATE_PATIENT_DETAILS("updatePatientDetails"),
	UPDATE_USER_DETAILS("updateUserDetails"),
	VIEW_DRUG("viewDrug"),
	VIEW_EFFECT("viewEffect"),
	VIEW_ILLNESS("viewIllness"),
	VIEW_INCIDENTS("viewIncidents"),
	VIEW_ASSESSMENTS("viewPastAssessments"),
	VIEW_DIAGNOSIS("viewPastDiagnosis"),
	VIEW_USER("viewUser"),
	VIEW_USER_DETAILS("viewUserDetails");
	
	private String name;
	
	private PageEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
