DROP SEQUENCE USERS_ID_SEQ;
DROP SEQUENCE DRUG_ID_SEQ;
DROP SEQUENCE DRUG_USER_SUITABILITY_ID_SEQ;
DROP SEQUENCE EFFECT_ID_SEQ;
DROP SEQUENCE USER_LOGIN_ID_SEQ;
DROP SEQUENCE PATIENT_DETAILS_ID_SEQ;
DROP SEQUENCE INCIDENT_ID_SEQ;
DROP SEQUENCE PATIENT_ALLERGIES_ID_SEQ;
DROP SEQUENCE DRUG_ALLERGY_ID_SEQ;
DROP SEQUENCE DIAGNOSIS_ID_SEQ;
DROP SEQUENCE DRUG_ILLNESS_ID_SEQ;
DROP SEQUENCE DRUG_EFFECT_ID_SEQ;
DROP SEQUENCE ILLNESS_ID_SEQ;
DROP TABLE DRUG_ILLNESS CASCADE CONSTRAINTS;
DROP TABLE DRUG_EFFECT CASCADE CONSTRAINTS;
DROP TABLE DIAGNOSIS CASCADE CONSTRAINTS;
DROP TABLE ILLNESS CASCADE CONSTRAINTS;
DROP TABLE DRUG_USER_SUITABILITY CASCADE CONSTRAINTS;
DROP TABLE INCIDENT CASCADE CONSTRAINTS;
DROP TABLE EFFECT CASCADE CONSTRAINTS;
DROP TABLE DRUG CASCADE CONSTRAINTS;
DROP TABLE PATIENT_ALLERGIES CASCADE CONSTRAINTS;
DROP TABLE DRUG_ALLERGY CASCADE CONSTRAINTS;
DROP TABLE PATIENT_DETAILS CASCADE CONSTRAINTS;
DROP TABLE USER_LOGIN CASCADE CONSTRAINTS;
DROP TABLE USERS CASCADE CONSTRAINTS;
DROP TABLE ROLES CASCADE CONSTRAINTS;


CREATE TABLE ROLES (
  ROLE_NAME VARCHAR2(50) PRIMARY KEY
);

CREATE TABLE USERS (
 ID NUMBER(5) NOT NULL,
 FORENAME VARCHAR(30) NOT NULL,
 SURNAME VARCHAR(30) NOT NULL,
 DOB DATE NOT NULL,
 EMAIL_ADDRESS VARCHAR(50) NOT NULL,
 ROLE_NAME VARCHAR2(50) NOT NULL,
 PRIMARY KEY (ID),
 CONSTRAINT FK_USER_ROLE_NAME FOREIGN KEY (ROLE_NAME) REFERENCES ROLES (ROLE_NAME)
);

CREATE TABLE USER_LOGIN (
  ID NUMBER(5) NOT NULL,
  USER_ID NUMBER(5) NOT NULL,
  USERNAME VARCHAR(20) NOT NULL,
  PASSWORD VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT FK_USER_LOGIN_USER_ID FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
);

CREATE TABLE PATIENT_DETAILS (
	ID NUMBER(5) NOT NULL,
	USER_ID NUMBER(5) NOT NULL,
	HEIGHT NUMBER(5) NOT NULL,
	WEIGHT NUMBER(5) NOT NULL,
	ETHNICITY VARCHAR(100) NOT NULL,
	SMOKER NUMBER(1) NOT NULL,
	DIABETES NUMBER(1) NOT NULL,
	GENDER VARCHAR(10) NOT NULL,
	PRIMARY KEY (ID),
	CONSTRAINT FK_PATIENT_RECORD_USERID FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
);

CREATE TABLE PATIENT_ALLERGIES (
	ID NUMBER(5) NOT NULL,
	USER_ID NUMBER(5) NOT NULL,
	ALLERGY VARCHAR(100) NOT NULL,
	CONSTRAINT FK_PATIENT_ALL_USER_ID FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
	PRIMARY KEY (ID)
);

CREATE TABLE DRUG (
	ID NUMBER(5) NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	DESCRIPTION VARCHAR(300) NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE DRUG_ALLERGY (
	ID NUMBER(5) NOT NULL,
	DRUG_ID NUMBER(5) NOT NULL,
  ALLERGY VARCHAR(100) NOT NULL,
	PRIMARY KEY(ID),
  CONSTRAINT DRUG_ALLERGY_DRUG_ID FOREIGN KEY (DRUG_ID) REFERENCES DRUG(ID)
);

CREATE TABLE EFFECT (
	ID NUMBER(5) NOT NULL,
	NAME VARCHAR(30) NOT NULL,
	DESCRIPTION VARCHAR(300) NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE INCIDENT (
	ID NUMBER (5) NOT NULL,
	USER_ID NUMBER (5) NOT NULL,
	DRUG_ID NUMBER (5) NOT NULL,
	EFFECT_ID NUMBER (5) NOT NULL,
	CONSTRAINT FK_INCIDENT_USER_ID FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
	CONSTRAINT FK_INCIDENT_DRUG_ID FOREIGN KEY (DRUG_ID) REFERENCES DRUG(ID),
	CONSTRAINT FK_INCIDENT_EFFECT_ID FOREIGN KEY (EFFECT_ID) REFERENCES EFFECT(ID),
	PRIMARY KEY (ID)
);

CREATE TABLE DRUG_USER_SUITABILITY (
	ID NUMBER(5) NOT NULL,
	USER_ID NUMBER(5) NOT NULL,
	DRUG_ID NUMBER(5) NOT NULL,
	EFFECT_ID NUMBER(5) NOT NULL,
	INCOMPATIBILITY NUMBER(2) NOT NULL,
	CONSTRAINT FK_DRUG_USER_SUIT_USER_ID FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
	CONSTRAINT FK_DRUG_USER_SUIT_DRUG_ID FOREIGN KEY (DRUG_ID) REFERENCES DRUG(ID), 
	CONSTRAINT FK_DRUG_USER_SUIT_EFFECT_ID FOREIGN KEY (EFFECT_ID) REFERENCES EFFECT(ID), 
	PRIMARY KEY(ID)
);

CREATE TABLE ILLNESS (
	ID NUMBER(5) NOT NULL,
	NAME VARCHAR(100) NOT NULL,
	DESCRIPTION VARCHAR(500) NOT NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE DRUG_ILLNESS (
	ID NUMBER(5) NOT NULL,
	DRUG_ID NUMBER(5) NOT NULL,
	ILLNESS_ID NUMBER(5) NOT NULL,
	PRIMARY KEY(ID),
	CONSTRAINT DRUG_ILLNESS_DRUG_ID FOREIGN KEY (DRUG_ID) REFERENCES DRUG(ID),
	CONSTRAINT DRUG_ILLNESS_ILLNESS_ID FOREIGN KEY (ILLNESS_ID) REFERENCES ILLNESS(ID)
);

CREATE TABLE DIAGNOSIS (
	ID NUMBER(5) NOT NULL,
	USER_ID NUMBER(5) NOT NULL,
	ILLNESS_ID NUMBER(5) NOT NULL,
	PRIMARY KEY (ID),
	CONSTRAINT FK_DIAGNOSIS_USER_ID FOREIGN KEY(USER_ID) REFERENCES USERS(ID),
	CONSTRAINT FK_DIAGNOSIS_ILLNESS_ID FOREIGN KEY (ILLNESS_ID) REFERENCES ILLNESS(ID)
);

CREATE TABLE DRUG_EFFECT (
	ID NUMBER(5) NOT NULL,
	DRUG_ID NUMBER(5) NOT NULL,
	EFFECT_ID NUMBER(5) NOT NULL,
	PRIMARY KEY(ID),
	CONSTRAINT FK_DRUG_EFFECT_DRUG_ID FOREIGN KEY (DRUG_ID) REFERENCES DRUG(ID),
	CONSTRAINT FK_DRUG_EFFECT_EFFECT_ID FOREIGN KEY (EFFECT_ID) REFERENCES EFFECT(ID)
);

CREATE SEQUENCE DRUG_EFFECT_ID_SEQ 
  START WITH 1
  MAXVALUE 99999
  MINVALUE 1
  INCREMENT BY 1;

CREATE SEQUENCE ILLNESS_ID_SEQ 
  START WITH 1
  MAXVALUE 99999
  MINVALUE 1
  INCREMENT BY 1;
  
CREATE SEQUENCE DRUG_ILLNESS_ID_SEQ 
  START WITH 1
  MAXVALUE 99999
  MINVALUE 1
  INCREMENT BY 1;

CREATE SEQUENCE DIAGNOSIS_ID_SEQ 
  START WITH 1
  MAXVALUE 99999
  MINVALUE 1
  INCREMENT BY 1;


CREATE SEQUENCE USERS_ID_SEQ 
  START WITH 1
  MAXVALUE 99999
  MINVALUE 1
  INCREMENT BY 1;
  
CREATE SEQUENCE PATIENT_DETAILS_ID_SEQ 
  START WITH 1
  MAXVALUE 99999
  MINVALUE 1
  INCREMENT BY 1;

CREATE SEQUENCE USER_LOGIN_ID_SEQ 
  START WITH 1
  MAXVALUE 99999
  MINVALUE 1
  INCREMENT BY 1;

CREATE SEQUENCE PATIENT_ALLERGIES_ID_SEQ 
  START WITH 1
  MAXVALUE 99999
  MINVALUE 1
  INCREMENT BY 1;
  
CREATE SEQUENCE INCIDENT_ID_SEQ 
  START WITH 1
  MAXVALUE 99999
  MINVALUE 1
  INCREMENT BY 1;  
  
CREATE SEQUENCE EFFECT_ID_SEQ 
  START WITH 1
  MAXVALUE 99999
  MINVALUE 1
  INCREMENT BY 1;  
 
CREATE SEQUENCE DRUG_USER_SUITABILITY_ID_SEQ 
  START WITH 1
  MAXVALUE 99999
  MINVALUE 1
  INCREMENT BY 1;
  
CREATE SEQUENCE DRUG_ID_SEQ 
  START WITH 1
  MAXVALUE 99999
  MINVALUE 1
  INCREMENT BY 1;
  
CREATE SEQUENCE DRUG_ALLERGY_ID_SEQ 
  START WITH 1
  MAXVALUE 99999
  MINVALUE 1
  INCREMENT BY 1;
  
INSERT INTO ROLES (ROLE_NAME) VALUES ('ADMIN');
INSERT INTO ROLES (ROLE_NAME) VALUES ('DOCTOR');
INSERT INTO ROLES (ROLE_NAME) VALUES ('PATIENT');

COMMIT;


