CREATE TABLE USER(
	id	INT NOT NULL AUTO_INCREMENT,
	name	VARCHAR(20) NOT NULL,
	grp	VARCHAR(5) NOT NULL,
	pwd	VARCHAR(20) NOT NULL,
	createdate	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(id)
);
INSERT INTO USER(name,grp,pwd)VALUES('wongpeeng','admin','wp19890818');


