CREATE TABLE students(
  id INT PRIMARY KEY
  , name VARCHAR(255)
  , gender VARCHAR(255)
  , age INT
);

INSERT INTO students VALUES(1, '김길동', 'MALE', 13);
INSERT INTO students VALUES(2, '홍상후', 'MALE', 20);
INSERT INTO students VALUES(3, '우리들', 'MALE', 47);
INSERT INTO students VALUES(4, '손성현', 'MALE', 15);

INSERT INTO students VALUES(5, '김은지', 'FEMALE', 35);
INSERT INTO students VALUES(6, '류희영', 'FEMALE', 23);



CREATE TABLE classes(
  id INT PRIMARY KEY
  , name VARCHAR(255)
  , type VARCHAR(255)
);

INSERT INTO classes VALUES(1, 'CLASS-A-A', 'CLASS-FRESHMAN');
INSERT INTO classes VALUES(2, 'CLASS-A-B', 'CLASS-FRESHMAN');
INSERT INTO classes VALUES(3, 'CLASS-B-A', 'CLASS-JUNIOR');
INSERT INTO classes VALUES(4, 'CLASS-B-B', 'CLASS-JUNIOR');
INSERT INTO classes VALUES(5, 'CLASS-B-C', 'CLASS-JUNIOR');
INSERT INTO classes VALUES(6, 'CLASS-C-A', 'CLASS-SENIOR');