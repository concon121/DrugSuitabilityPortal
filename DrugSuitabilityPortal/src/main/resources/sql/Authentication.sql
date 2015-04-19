DROP TABLE USER_ROLE CASCADE CONSTRAINTS;
DROP TABLE ROLE CASCADE CONSTRAINTS;
DROP TABLE USER_LOGIN CASCADE CONSTRAINTS;
DROP TABLE USERS CASCADE CONSTRAINTS;


CREATE TABLE USERS (
	USERNAME VARCHAR (50) NOT NULL,
	TITLE VARCHAR (10) NOT NULL,
	FORENAME VARCHAR (50) NOT NULL,
	SURNAME VARCHAR (50) NOT NULL,
	PRIMARY KEY (USERNAME)
);

CREATE TABLE USER_LOGIN (
  USERNAME VARCHAR (50) NOT NULL,
  PASSWORD VARCHAR (50) NOT NULL,
  PRIMARY KEY (USERNAME),
  CONSTRAINT USER_LOGIN_USERNAME FOREIGN KEY (USERNAME) REFERENCES USERS (USERNAME)
);

CREATE TABLE ROLE (
	ROLENAME VARCHAR (50) NOT NULL,
	PRIMARY KEY (ROLENAME)
);

CREATE TABLE USER_ROLE (
	USERNAME VARCHAR (50) NOT NULL,
	ROLENAME VARCHAR (50) NOT NULL,
	PRIMARY KEY (USERNAME, ROLENAME),
	CONSTRAINT USER_ROLE_USERNAME FOREIGN KEY (USERNAME) REFERENCES USERS (USERNAME),
	CONSTRAINT USER_ROLE_ROLENAME FOREIGN KEY (ROLENAME) REFERENCES ROLE (ROLENAME)
);

INSERT INTO USERS (USERNAME, TITLE, FORENAME, SURNAME) VALUES ('doctor','DR','Connor','Bray');
INSERT INTO USERS (USERNAME, TITLE, FORENAME, SURNAME) VALUES ('admin','Mrs','June','Jenson');
INSERT INTO USERS (USERNAME, TITLE, FORENAME, SURNAME) VALUES ('patient','Mr','David','Taylor');

INSERT INTO USER_LOGIN (USERNAME, PASSWORD) VALUES ('doctor', 'doctor');
INSERT INTO USER_LOGIN (USERNAME, PASSWORD) VALUES ('admin', 'admin');
INSERT INTO USER_LOGIN (USERNAME, PASSWORD) VALUES ('patient', 'patient');

INSERT INTO ROLE (ROLENAME) VALUES ('ROLE_DOCTOR');
INSERT INTO ROLE (ROLENAME) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (ROLENAME) VALUES ('ROLE_PATIENT');

INSERT INTO USER_ROLE (USERNAME,ROLENAME) VALUES ('doctor','ROLE_DOCTOR');
INSERT INTO USER_ROLE (USERNAME,ROLENAME) VALUES ('admin','ROLE_ADMIN');
INSERT INTO USER_ROLE (USERNAME,ROLENAME) VALUES ('patient','ROLE_PATIENT');

COMMIT;


