USE ohwoodb;
show tables;

DROP TABLE user;
DROP TABLE user_auth;
DROP TABLE board;
DROP TABLE files;
DROP TABLE likes;
DROP TABLE images;
DROP TABLE visitor;
        
ALTER TABLE board AUTO_INCREMENT = 1;

SELECT sysdate();

use ohwoodb;
COMMIT;
SELECT * FROM user;
SELECT * FROM user_auth;
SELECT * FROM board;
ALTER TABLE board AUTO_INCREMENT = 1;
SELECT COUNT(*)
		FROM visitor;
SELECT * FROM visitor;

CREATE TABLE visitor(
	id bigint auto_increment primary KEY,
    count bigint,
    regdate DATE
);

SELECT SUM(count) as count
		FROM visitor;

SELECT SUM(count) as count
FROM visitor
WHERE regdate = '2023-07-16';

SELECT * FROM visitor;

CREATE TABLE user(
   id VARCHAR(50) PRIMARY KEY NOT NULL,
   password VARCHAR(100),
   name VARCHAR(10),
   nickname VARCHAR(10) UNIQUE,
   phone CHAR(11),
   address VARCHAR(50),
   level VARCHAR(5),
   regdate DATE
);

CREATE TABLE user_auth(
	id VARCHAR(50),
    role VARCHAR(50),
    CONSTRAINT authfk_id_user_id FOREIGN KEY(id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE board(
   no BIGINT AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(50),
   author VARCHAR(50),
   content VARCHAR(500),
   regdate DATE,
   updateday DATE,
   CONSTRAINT brdfk_author_user_id FOREIGN KEY(author) REFERENCES user(nickname) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE images(
	no BIGINT AUTO_INCREMENT PRIMARY KEY,
    id VARCHAR(50),
    src VARCHAR(50),
    CONSTRAINT imgfk_id_user_id FOREIGN KEY(id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE files(
	no BIGINT AUTO_INCREMENT PRIMARY KEY,
    id VARCHAR(50),
    src VARCHAR(50),
    CONSTRAINT filefk_id_user_id FOREIGN KEY(id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE likes(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    no BIGINT,
    uid VARCHAR(50),
    CONSTRAINT likefk_no_board_no FOREIGN KEY(no) REFERENCES board(no) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT likefk_uid_user_id FOREIGN KEY(uid) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE
);

SELECT * FROM user;
SELECT * FROM user_auth;

COMMIT;