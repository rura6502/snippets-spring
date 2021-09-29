CREATE TABLE students(
  id INT PRIMARY KEY AUTO_INCREMENT
  , name VARCHAR(255)
  , gender VARCHAR(255)
  , age INT
);

INSERT INTO students VALUES(null, '김길동', 'MALE', 13);
INSERT INTO students VALUES(null, '홍상후', 'MALE', 20);
INSERT INTO students VALUES(null, '우리들', 'MALE', 47);
INSERT INTO students VALUES(null, '손성현', 'MALE', 15);

INSERT INTO students VALUES(null, '김은지', 'FEMALE', 35);
INSERT INTO students VALUES(null, '류희영', 'FEMALE', 23);



CREATE TABLE classes(
  id INT PRIMARY KEY AUTO_INCREMENT
  , name VARCHAR(255)
  , type VARCHAR(255)
);

INSERT INTO classes VALUES(null, 'CLASS-A-A', 'CLASS-FRESHMAN');
INSERT INTO classes VALUES(null, 'CLASS-A-B', 'CLASS-FRESHMAN');
INSERT INTO classes VALUES(null, 'CLASS-B-A', 'CLASS-JUNIOR');
INSERT INTO classes VALUES(null, 'CLASS-B-B', 'CLASS-JUNIOR');
INSERT INTO classes VALUES(null, 'CLASS-B-C', 'CLASS-JUNIOR');
INSERT INTO classes VALUES(null, 'CLASS-C-A', 'CLASS-SENIOR');