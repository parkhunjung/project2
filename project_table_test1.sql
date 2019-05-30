
--1. 교육생  // 1=>
CREATE TABLE tblStudent
(
    seq NUMBER CONSTRAINT tblStudent_seq_PK PRIMARY KEY,
    name VARCHAR2(30) UNIQUE NOT NULL,
    ssn VARCHAR2(15) UNIQUE NOT NULL,
    tel VARCHAR2(15) UNIQUE NOT NULL,
    address VARCHAR2(150) NULL,
    regdate DATE DEFAULT SYSDATE NOT NULL,
    delete_status VARCHAR2(15) CONSTRAINT tblStudent_delete_status_CK CHECK(delete_status IN ('회원', '탈퇴')) NOT NULL
);

CREATE SEQUENCE student_seq --                                  시퀀스 수정 시작위치
    INCREMENT BY 1 -- 증감치 조절 (양수/음수 모두가능)
    START WITH 181
    MAXVALUE 30000;

--drop SEQUENCE student_seq;


--2. 강사
CREATE TABLE tblTeacher
(
    seq NUMBER CONSTRAINT tblTeacher_seq_PK PRIMARY KEY,
    name VARCHAR2(30) UNIQUE NOT NULL,
    ssn VARCHAR2(15) UNIQUE NOT NULL,
    tel VARCHAR2(15) UNIQUE NOT NULL,
    address VARCHAR2(150) NULL,
    regdate DATE DEFAULT SYSDATE NOT NULL,
    delete_status VARCHAR2(15) CONSTRAINT tblTeacher_delete_status_CK CHECK(delete_status IN ('회원', '탈퇴')) NOT NULL
);

SELECT * from tblteacher;

CREATE SEQUENCE teacher_seq
    INCREMENT BY 1 
    START WITH 11
    MAXVALUE 30000;

--drop SEQUENCE teacher_seq;


--3. 과목
CREATE TABLE tblSubject
(
    seq NUMBER CONSTRAINT tblSubject_seq_PK PRIMARY KEY,
    subject_name VARCHAR2(50) UNIQUE NOT NULL,
    delete_status VARCHAR2(15) CONSTRAINT tblSubject_delete_status_CK CHECK(delete_status IN ('가능', '불가능')) NOT NULL
);


CREATE SEQUENCE subject_seq;


--4. 강사가 담당하는 과목
CREATE TABLE tblTeacherSubject
(
    seq NUMBER CONSTRAINT tblTeacherSubject_seq_pk PRIMARY KEY,
    teacherseq NUMBER NOT NULL REFERENCES tblTeacher(seq),
    subjectseq NUMBER NOT NULL REFERENCES tblSubject(seq)
);

CREATE SEQUENCE teacherSubject_seq;



--5. 교재
CREATE TABLE tblBook
(
    seq NUMBER CONSTRAINT tblBook_seq_PK PRIMARY KEY,
    title VARCHAR2(150) UNIQUE NOT NULL,
    subjectseq NUMBER NOT NULL REFERENCES tblSubject(seq),
    publisher VARCHAR(30) NOT NULL 
);


CREATE SEQUENCE book_seq;

--6. 과정명
CREATE TABLE tblCourseTitle
(
    seq NUMBER CONSTRAINT tblCourseTitle_seq_PK PRIMARY KEY,
    title VARCHAR2(150) UNIQUE NOT NULL
);
CREATE SEQUENCE courseTitle_seq
    INCREMENT BY 1 
    START WITH 7;

--drop SEQUENCE courseTitle_seq;

--7. 강의실
CREATE TABLE tblRoom
(
    seq NUMBER CONSTRAINT tblRoom_seq_PK PRIMARY KEY,
    name VARCHAR2(20) UNIQUE NOT NULL,
    limit NUMBER(5) NOT NULL
);

CREATE SEQUENCE room_seq;


--8. 과정
CREATE TABLE tblCourse
(
    seq NUMBER CONSTRAINT tblCourse_seq_PK PRIMARY KEY,
    coursetitleseq NUMBER NOT NULL REFERENCES tblCourseTitle(seq),
    roomseq NUMBER NOT NULL REFERENCES tblRoom(seq),
    status VARCHAR2(20) CHECK (status IN ('진행중', '폐강', '정상종강')) NOT NULL,
    begindate DATE NOT NULL,
    enddate DATE NOT NULL,
    etc VARCHAR2(1000) NULL
);

CREATE SEQUENCE course_seq
    INCREMENT BY 1
    START WITH 7;


--9. 과정과목
CREATE TABLE tblCourseSubject
(
    seq NUMBER CONSTRAINT tblCourseSubject_seq_PK PRIMARY KEY,
    courseseq NUMBER NOT NULL REFERENCES tblCourse(seq),
    subjectseq NUMBER NOT NULL REFERENCES tblSubject(seq),
    bookseq NUMBER NOT NULL REFERENCES tblBook(seq),
    teacherseq NUMBER NOT NULL REFERENCES tblTeacher(seq),
    begindate VARCHAR2(20) NOT NULL,
    enddate VARCHAR2(20) NOT NULL,
    etc VARCHAR2(1000) NULL

);

CREATE SEQUENCE courseSubject_seq
    INCREMENT BY 1
    START WITH 26;




--10. 수강중인정보
CREATE TABLE tblLearning
(
    seq NUMBER CONSTRAINT tblLearning_seq_PK PRIMARY KEY,
    courseseq NUMBER NOT NULL REFERENCES tblCourse(seq),
    studentseq NUMBER NOT NULL REFERENCES tblStudent(seq),
    ing VARCHAR2(20) CONSTRAINT tblLearning_ing_CK CHECK (ing IN ('진행중', '중도탈락', '수료')) NOT NULL,
    ingdate VARCHAR2(30) DEFAULT '해당사항없음' NOT NULL
);

CREATE SEQUENCE learning_seq;

--11. 교육생출결하기
CREATE TABLE tblAttended
(
    seq NUMBER CONSTRAINT tblAttended_seq_PK PRIMARY KEY,
    intime DATE DEFAULT TO_CHAR(SYSDATE, 'yyyy-mm-dd') NULL,
    outtime DATE DEFAULT TO_CHAR(SYSDATE, 'yyyy-mm-dd') NULL,
    status VARCHAR2(30) DEFAULT '결석' CONSTRAINT tblAttended_status_CK CHECK (status IN ('출근', '지각', '조퇴', '결석', '병가', '기타')) NOT NULL, 
    learningseq NUMBER NOT NULL REFERENCES tblLearning(seq)
);

CREATE SEQUENCE attended_seq;


--12. 필기시험
CREATE TABLE tblPilgiExam
(
    seq NUMBER CONSTRAINT tblPilgiExam_seq_PK PRIMARY KEY,
    question VARCHAR2(2000) NOT NULL,
    answer NUMBER(2) NOT NULL,
    coursesubjectseq NUMBER NOT NULL REFERENCES tblCourseSubject(seq)
);

CREATE SEQUENCE pilgiExam_seq;

--13. 학생과목필기답
CREATE TABLE tblPilgiAnswer
(
    seq NUMBER CONSTRAINT tblPilgiAnswer_seq_PK PRIMARY KEY,
    answer NUMBER(2) NOT NULL,
    pilgiexamseq NUMBER NOT NULL REFERENCES tblPilgiExam(seq),
    learningseq NUMBER NOT NULL REFERENCES tblLearning(seq),
    courseSubjectSeq NUMBER NOT NULL REFERENCES tblCourseSubject(seq)
);

CREATE SEQUENCE pilgiAnswer_seq;


--14. 실기시험
CREATE TABLE tblSilgiExam
(
    seq NUMBER CONSTRAINT tblSilgiExam_seq_PK PRIMARY KEY,
    question VARCHAR2(2000) NOT NULL,
    answer VARCHAR2(1000) NOT NULL,
    coursesubjectseq NUMBER NOT NULL REFERENCES tblCourseSubject(seq)
);

CREATE SEQUENCE silgiExam_seq;


--15. 학생과목실기답
CREATE TABLE tblSilgiAnswer
(
    seq NUMBER CONSTRAINT tblSilgiAnswer_seq_PK PRIMARY KEY,
    answer VARCHAR2(1000) NOT NULL,
    silgiexamseq NUMBER NOT NULL REFERENCES tblSilgiExam(seq),
    learningseq NUMBER NOT NULL REFERENCES tblLearning(seq),
    courseSubjectSeq NUMBER NOT NULL REFERENCES tblCourseSubject(seq)
);

CREATE SEQUENCE silgiAnswer_seq;


--16. 출결수당지급내역



--17. 스터디
CREATE TABLE tblStudy
(
    seq NUMBER CONSTRAINT tblStudy_seq_PK PRIMARY KEY,
    name VARCHAR2(150) NULL,
    limit NUMBER(5) NOT NULL,
    leader NUMBER NOT NULL REFERENCES tblLearning(seq)
    
);
    

CREATE SEQUENCE study_seq;

--18. 스터디학생
CREATE TABLE tblStudyStudent
(
    seq NUMBER CONSTRAINT tblStudyStudent_seq_PK PRIMARY KEY,
    studyseq NUMBER NOT NULL REFERENCES tblStudy(seq),
    learningseq NUMBER NOT NULL REFERENCES tblLearning(seq)
);

CREATE SEQUENCE studyStudent_seq;


--19. 강의실상태 -- 시연안하면 필요 없는 테이블
CREATE TABLE tblRoomStatus
(
    seq NUMBER CONSTRAINT tblRoomStatus_seq_PK PRIMARY KEY,
    reservedate VARCHAR2(20) DEFAULT NULL,
    roomseq NUMBER NOT NULL REFERENCES tblRoom(seq),
    studyseq NUMBER NOT NULL REFERENCES tblStudy(seq)
);

CREATE SEQUENCE roomStatus_seq;



--===================================================================================================================

--20. 공휴일 테이블
CREATE TABLE tblHoliday
(
    seq NUMBER CONSTRAINT tblHoliday_seq_PK PRIMARY KEY,
    regdate DATE NOT NULL,
    name VARCHAR2(50) NOT NULL
);

CREATE SEQUENCE holiday_seq;

--21. 관리자 1명 테이블
CREATE TABLE tblAdmin 
(
    seq NUMBER CONSTRAINT tblAdmin_seq_PK PRIMARY KEY,
    name VARCHAR2(30) NOT NULL,
    ssn VARCHAR2(15) UNIQUE NOT NULL,
    tel VARCHAR2(15) UNIQUE NOT NULL,
    address VARCHAR2(150) NULL,
    regdate DATE NOT NULL
);

INSERT INTO tblAdmin VALUES (1, '유재석', '790302-1111222', '010-6521-7374', '경기도 파주시 조리읍 장곡리', '2017-01-01');
INSERT INTO tblAdmin VALUES (2, '하하하', '890302-1111222', '010-6661-7374', '경기도 파주시 조리읍 장곡리', '2018-01-01');
commit;
--2019
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-01-01', '신정');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-02-04', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-02-05', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-02-06', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-03-01', '삼일절');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-05-06', '어린이날(대체공휴일)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-06-06', '현충일');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-08-15', '광복절');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-09-12', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-09-13', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-09-14', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-10-03', '개천절');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-10-09', '한글날');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2019-12-25', '크리스마스');
--2018
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-01-01', '신정');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-02-15', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-02-16', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-02-17', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-03-01', '삼일절');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-05-07', '어린이날(대체공휴일)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-06-06', '현충일');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-06-13', '지방선거');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-08-15', '광복절');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-09-24', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-09-25', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-09-26', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-10-03', '개천절');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-10-09', '한글날');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-12-25', '크리스마스');
--2017
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-01-01', '신정');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-01-27', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-01-28', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-01-29', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-01-30', '설날(대체공휴일)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-03-01', '삼일절');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-05-03', '석가탄신일');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-05-05', '어린이날');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-05-09', '대통령선거(임시공휴일)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-06-06', '현충일');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-08-15', '광복절');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-10-03', '개천절');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-10-04', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-10-05', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-10-06', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-10-09', '한글날');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2017-12-25', '크리스마스');
--2016
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-01-01', '신정');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-02-08', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-02-09', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-02-10', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-03-01', '삼일절');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-04-13', '20대 국회의원 선거');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-05-05', '어린이날');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-05-06', '임시공휴일');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-03-01', '석가탄신일');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-06-06', '현충일');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-08-15', '광복절');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-09-14', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-09-15', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-09-16', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-10-03', '개천절');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-10-09', '한글날');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2016-12-25', '크리스마스');
--2015
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-01-01', '신정');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-02-18', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-02-19', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-02-20', '설날(연휴)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2018-03-02', '삼일절(대체공휴일)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-05-05', '어린이날');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-05-25', '석가탄신일');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-06-06', '현충일');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-08-14', '광복절(대체공휴일)');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-09-27', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-09-28', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-09-29', '추석');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-10-03', '개천절');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-10-09', '한글날');
INSERT INTO tblHoliday VALUES (holiday_seq.nextval, '2015-12-25', '크리스마스');


-- 과정 평일 계산 뷰테이블
CREATE OR REPLACE view vwStartMonth
AS
SELECT TO_DATE('20190128', 'yyyymmdd') + level -1 AS regdate FROM dual
    CONNECT BY level <= (LAST_DAY('20190101') - TO_DATE('20190128', 'yyyymmdd') + 1);

CREATE OR REPLACE view vwEndMonth
AS
SELECT TO_DATE('20190801', 'yyyymmdd') + level -1 AS regdate FROM dual
    CONNECT BY level <= (TO_DATE('20190821', 'yyyymmdd') - TO_DATE('20190801', 'yyyymmdd') + 1);

CREATE OR REPLACE view vwMonth2
AS
SELECT TO_DATE('20190201', 'yyyymmdd') + level -1 AS regdate FROM dual
    CONNECT BY level <= (LAST_DAY('20190201') - TO_DATE('20190201', 'yyyymmdd') + 1);


CREATE OR REPLACE view vwMonth3
AS
SELECT TO_DATE('20190301', 'yyyymmdd') + level -1 AS regdate FROM dual
    CONNECT BY level <= (LAST_DAY('20190301') - TO_DATE('20190301', 'yyyymmdd') + 1);


CREATE OR REPLACE view vwMonth4
AS
SELECT TO_DATE('20190401', 'yyyymmdd') + level -1 AS regdate FROM dual
    CONNECT BY level <= (LAST_DAY('20190401') - TO_DATE('20190401', 'yyyymmdd') + 1);


CREATE OR REPLACE view vwMonth5
AS
SELECT TO_DATE('20190501', 'yyyymmdd') + level -1 AS regdate FROM dual
    CONNECT BY level <= (LAST_DAY('20190501') - TO_DATE('20190501', 'yyyymmdd') + 1);


CREATE OR REPLACE view vwMonth6
AS
SELECT TO_DATE('20190601', 'yyyymmdd') + level -1 AS regdate FROM dual
    CONNECT BY level <= (LAST_DAY('20190601') - TO_DATE('20190601', 'yyyymmdd') + 1);


CREATE OR REPLACE view vwMonth7
AS
SELECT TO_DATE('20190701', 'yyyymmdd') + level -1 AS regdate FROM dual
    CONNECT BY level <= (LAST_DAY('20190701') - TO_DATE('20190701', 'yyyymmdd') + 1);

--- 뷰테이블 모음 ------------------------------------------------------------------------------

CREATE OR REPLACE VIEW vwEmptyRoom
AS
SELECT 
    seq as "강의실번호",
    name AS "강의실명",
    limit AS "강의실정원"
FROM tblroom WHERE not seq in
    (SELECT roomseq FROM tblCourse WHERE status = '진행중');
    
    
    
    
CREATE OR REPLACE VIEW vwCourseInfo
AS
SELECT      
    c.seq AS courseSeq,
    ct.title AS courseTitle,
    c.begindate AS begindate,
    c.enddate AS enddate,
    r.name AS roomName,
    --(SELECT COUNT(*) FROM tbllearning WHERE ing = '진행중' GROUP BY courseseq) AS "등록인원수",
    c.status AS status,
    c.etc AS etc
FROM tblcourse c
    INNER JOIN tblroom r
        ON c.roomseq = r.seq
            INNER JOIN tblcoursetitle ct
                ON c.coursetitleseq = ct.seq
                    ORDER BY c.seq;


CREATE OR REPLACE VIEW vwSubjectList
AS
SELECT  
    t.name AS teacherName,
    ct.title AS openCourseName,
    c.begindate || ' ~ ' || c.enddate AS openCoursePeriod,
    r.name AS roomName,
    s.subject_name AS subjectName,
    b.title AS bookName,
    st.name AS studentName,
    substr(st.ssn, 8) AS backSsn,
    (SELECT COUNT(*) FROM tblpilgianswer p
        INNER JOIN tblpilgiexam e
            ON e.seq = p.pilgiexamseq
                WHERE p.learningseq = l.seq AND e.coursesubjectseq = cs.seq
                    AND p.answer = e.answer) * 2 AS pilgi,
    (SELECT COUNT(*) FROM tblsilgianswer p
        INNER JOIN tblsilgiexam e
            ON e.seq = p.silgiexamseq
                WHERE p.learningseq = l.seq AND e.coursesubjectseq = cs.seq
                    AND p.answer = e.answer) * 4 AS silgi
FROM tblsubject s
    INNER JOIN tblcoursesubject cs
        ON s.seq = cs.subjectseq
            INNER JOIN tblteacher t
                ON t.seq = cs.teacherseq
                    INNER JOIN tblbook b
                        ON b.subjectseq = s.seq
                            INNER JOIN tblcourse c
                                ON c.seq = cs.courseseq
                                    INNER JOIN tblcoursetitle ct
                                        ON ct.seq = c.coursetitleseq
                                            INNER JOIN tblroom r
                                                ON r.seq = c.roomseq
                                                    INNER JOIN tbllearning l
                                                        ON l.courseseq = c.seq
                                                            INNER JOIN tblstudent st
                                                                ON st.seq = l.studentseq;


CREATE OR REPLACE VIEW vwStudentList
AS
SELECT 
     st.name AS studentName,
     SUBSTR(st.ssn, 8) AS backSsn,
     ct.title AS openCourseName,
     c.begindate || ' ~ ' || c.enddate AS openCoursePeriod,
     r.name AS roomName,
     t.name AS teacherName,
     s.subject_name openSubjectName,
     cs.begindate || ' ~ ' || cs.enddate AS openSubjectPeriod,
     (SELECT COUNT(*) FROM tblpilgianswer p
        INNER JOIN tblpilgiexam e
            ON e.seq = p.pilgiexamseq
                WHERE p.learningseq = l.seq AND e.coursesubjectseq = cs.seq
                    AND p.answer = e.answer) * 2 AS pilgi,
    (SELECT COUNT(*) FROM tblsilgianswer p
        INNER JOIN tblsilgiexam e
            ON e.seq = p.silgiexamseq
                WHERE p.learningseq = l.seq AND e.coursesubjectseq = cs.seq
                    AND p.answer = e.answer) * 4 AS silgi
FROM tblstudent st
    INNER JOIN tbllearning l
        ON l.studentseq = st.seq
            INNER JOIN tblcourse c
                ON c.seq = l.courseseq
                    INNER JOIN tblcoursetitle ct
                        ON c.coursetitleseq = ct.seq
                            INNER JOIN tblcoursesubject cs
                                ON cs.courseseq = c.seq
                                    INNER JOIN tblroom r
                                        ON r.seq = c.roomseq
                                            INNER JOIN tblsubject s
                                                ON s.seq = cs.subjectseq
                                                    INNER JOIN tblteacher t
                                                        ON t.seq = cs.teacherseq;

CREATE OR REPLACE VIEW vwAttendWhole
AS
select 
    s.name as studentName, 
    ct.title as courseName, 
    to_char(a.intime, 'yyyy-mm-dd/hh24:mi') as intime ,
    to_char(a.outtime, 'yyyy-mm-dd/hh24:mi') as outtime,
    a.status as attendStatus
from tblStudent s
    inner join tbllearning l
        on l.studentseq = s.seq
            inner join tblAttended a
                on l.seq = a.learningseq
                    inner join tblCourse c
                        on l.courseseq = c.seq
                            inner join tblCoursetitle ct
                                on  c.coursetitleseq = ct.seq;



    
--===========================================================================================
-- 평일수를 계산하기 위한 셀렉트

SELECT * FROM vwMonth7;

SELECT 
    COUNT
        (CASE
            WHEN TO_CHAR(v.regdate, 'd') IN ('7', '1') THEN NULL
            WHEN h.name is not null THEN NULL
            ELSE 1
        END) AS "이번달의 평일수"
FROM vwMonth6 v
    LEFT OUTER JOIN tblholiday h
        ON v.regdate = h.regdate;

--===========================================================================================
--드랍시퀀스 모음
DROP SEQUENCE student_seq;
DROP SEQUENCE teacher_seq;
DROP SEQUENCE subject_seq;
DROP SEQUENCE teachersubject_seq;
DROP SEQUENCE book_seq;
DROP SEQUENCE courseTitle_seq;
DROP SEQUENCE room_seq;
DROP SEQUENCE course_seq;
DROP SEQUENCE courseSubject_seq;
DROP SEQUENCE learning_seq;
DROP SEQUENCE attended_seq;
DROP SEQUENCE pilgiExam_seq;
DROP SEQUENCE pilgiAnswer_seq;
DROP SEQUENCE silgiExam_seq;
DROP sequence silgiAnswer_seq;
DROP sequence attendedSudang_seq;
DROP sequence study_seq;
DROP sequence studyStudent_seq;
DROP sequence roomStatus_seq;
DROP sequence holiday_seq;
--===========================================================================================
