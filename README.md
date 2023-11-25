# Java-Hackathon-25112023
```
CREATE TABLE USERS(
UID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
FNAME VARCHAR(20),
LNAME VARCHAR(20),
DOB DATE,
ADDRESS VARCHAR(40),
GENDER VARCHAR(10),
MOBILE BIGINT,
EMAIL VARCHAR(40),
PASSWORD VARCHAR(40)
);



CREATE TABLE APPLICATIONS(
APPID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
UID INT CONSTRAINT FK_1 REFERENCES USERS(UID),
COURSECODE VARCHAR(20) CONSTRAINT FK_2 REFERENCES COURSE(COURSECODE),
TENTHMARKS INT,
TWELMARKS INT,
UGMARKS INT,
UGUNIVERSITY VARCHAR(40)
);


CREATE TABLE COURSE(
	COURSECODE VARCHAR(20) PRIMARY KEY,
	COURSENAME VARCHAR(40)
);

```