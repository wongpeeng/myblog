CREATE TABLE Comment(
	id	INT	NOT NULL	AUTO_INCREMENT,
	logId	INT	NOT NULL,
	toPerson	VARCHAR(20) NOT NULL ,
	critic	VARCHAR(20)	NOT NULL,
	pubDate TIMESTAMP 	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	revDate	TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	content	VARCHAR(500)	NOT NULL,
	pros	INT NOT NULL DEFAULT 0,
	cons	INT NOT NULL DEFAULT 0,
	PRIMARY KEY(id)
);
