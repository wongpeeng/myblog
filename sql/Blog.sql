CREATE TABLE Blog(
	id			INT				NOT NULL AUTO_INCREMENT,
	title		VARCHAR(200)	NOT NULL,
	author		VARCHAR(20)		NOT NULL,
	pubDate		TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
	revDate		TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
	views		INT				NOT NULL DEFAULT 0,
	cmt			INT				NOT NULL DEFAULT 0,
	content		VARCHAR(2000)	NOT NULL,
	PRIMARY KEY(id)	
) character set=utf8;

