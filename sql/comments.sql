CREATE TABLE COMMENTS(
	id	INT	NOT NULL	AUTO_INCREMENT,
	logid	INT	NOT NULL,
	writer	VARCHAR(20)	NOT NULL,
	pubtime TIMESTAMP 	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	revtime	TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	content	VARCHAR(200)	NOT NULL,
	PRIMARY KEY(id)
	
);
