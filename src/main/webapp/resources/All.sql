CREATE DATABASE OHWOODB;

USE ohwoodb;

DROP TABLE user;
DROP TABLE user_auth;
DROP TABLE admin;
DROP TABLE board;
DROP TABLE file;
DROP TABLE likes;

CREATE TABLE user(
   id VARCHAR(50) PRIMARY KEY,
   password VARCHAR(100),
   name VARCHAR(10),
   nickname VARCHAR(10),
   phone VARCHAR(13),
   address VARCHAR(50),
   level VARCHAR(5),
   regdate DATE
);

CREATE TABLE admin(
   id VARCHAR(50) PRIMARY KEY,
   password VARCHAR(100),
   name VARCHAR(10),
   phone VARCHAR(13),
   address VARCHAR(50),
   regdate DATE
);


CREATE TABLE user_auth(
	id VARCHAR(50),
    auth VARCHAR(50),
    CONSTRAINT authfk_id_user_id FOREIGN KEY(id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE board(
   no BIGINT AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(50),
   author VARCHAR(10),
   content VARCHAR(500),
   regdate DATE,
   updateday DATE,
   CONSTRAINT brdfk_author_user_id FOREIGN KEY(author) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE file(
	no BIGINT AUTO_INCREMENT PRIMARY KEY,
    id VARCHAR(20),
    src VARCHAR(50),
    CONSTRAINT filefk_id_user_id FOREIGN KEY(id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE likes(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    no BIGINT,
    uid VARCHAR(20),
    CONSTRAINT likefk_no_board_no FOREIGN KEY(no) REFERENCES board(no) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT likefk_uid_user_id FOREIGN KEY(uid) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE
);


SELECT * FROM user;
INSERT INTO user_auth(id, auth)
VALUES ('1111', 'ROLE_USER');

종속 삭제 방법
SET foreign_key_checks = 0;
drop table board;
SET foreign_key_checks = 1;


COMMIT;