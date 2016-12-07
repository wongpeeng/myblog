CREATE TABLE NOTICE(
	id			INT	AUTO_INCREMENT,
	level		INT DEFAULT 0,
	author		VARCHAR(20),
	title		VARCHAR(200),
	content 	VARCHAR(2000),
	strDate		DATE,
	endDate		DATE,
	PRIMARY KEY(id)
);

