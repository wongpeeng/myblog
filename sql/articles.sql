CREATE TABLE ARTICLES(
	id		INT		NOT NULL AUTO_INCREMENT,
	title		VARCHAR(200)	NOT NULL,
	author		VARCHAR(20)	NOT NULL,
	pubdate		TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	revdate		TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	views	INT		NOT NULL DEFAULT 0,
	cmtnum	INT		NOT NULL DEFAULT 0,
	content		VARCHAR(2000)	NOT NULL,
	PRIMARY KEY(id)	
) character set=utf8;

