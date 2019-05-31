package com.project2.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project2.main.DBUtil;
import com.project2.main.RoomDTO;
import com.project2.main.StudentDTO;
import com.project2.main.StudyDTO;


public class StudentDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;

	// 초기화(선행 작업)
	// - DB 접속
	/***
	 * 교육생 DB연동 클래스
	 */
	public StudentDAO() {

		try {
			DBUtil util = new DBUtil();
			conn = util.connect(DBUtil.SERVERIP, DBUtil.SERVERID, DBUtil.SERVERPW);
			stat = conn.createStatement();
			// pstat는 미리 못만듬!!
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("StudentDAO클래스의 생성자 : " + e.toString());
		}
	}
	/**
	 * 모든 교육생의 정보를 반납하는 메소드
	 * @return ArrayList<StudentDTO> : 모든 교육생정보의 배열
	 */
	public ArrayList<StudentDTO> studentList() {// 모든학생의 정보를가져옵니다! ==>(로그인 확인용)
		try {
			String sql = "SELECT * FROM tblStudent WHERE delete_status = '회원'";

			ResultSet rs = stat.executeQuery(sql);

			ArrayList<StudentDTO> studentList = new ArrayList<StudentDTO>();

			// rs -> (복사) -> list
			while (rs.next()) {
				// 레코드 1개 -> DTO 1개
				StudentDTO dto = new StudentDTO();

				dto.setSeq(rs.getString("seq"));
				// 컬럼값 --> DTO 멤버변수
				dto.setName(rs.getString("name"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				dto.setAddress(rs.getString("address"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setDelete_status(rs.getString("delete_status"));

				studentList.add(dto);
			}

			rs.close();
			return studentList;

		} catch (Exception e) {
			System.out.println("StudentDAO의 studentList() : " + e.toString());
		}
		return null;

	}

	/**
	 * 특정 교육생의 수강했던 혹은 수강중인 과정을 반납하는 메소드
	 * @param String seq : 특정 교육생의 고유번호
	 * @return  ArrayList<StudentCourseDTO> : 특정 교육생에 해당하는 과정정보배열
	 */
	public ArrayList<StudentCourseDTO> courseList(String seq) {// 특정학생에 해당하는 과정만 갖고옵니다

		try {
			/*
			 * private String courseseq; private String title; private String status;
			 * private String begindate; private String enddate; private String room;
			 * private String ; private String totalPeople;
			 */
			String sql = "SELECT  " + "	 seq,"
					+ "    (SELECT title FROM tblcoursetitle WHERE seq = c.coursetitleseq) AS courseTitle, "
					+ "    status, " + "    TO_CHAR(begindate,'YYYY-MM-DD') AS begindate, "
					+ "    TO_CHAR(enddate, 'YYYY-MM-DD') AS enddate, "
					+ "    (SELECT name FROM tblroom WHERE seq = c.roomseq) AS roomTitle, "
					+ "    (SELECT COUNT(*) FROM tbllearning WHERE courseseq = c.seq "
					+ "        AND ing IN ('진행중','수료')) AS totalPeople, "
					+ "	 (SELECT seq FROM tbllearning WHERE studentseq = ? AND c.seq = courseseq) AS learnseq "
					+ " FROM tblcourse c " + " WHERE seq IN (SELECT courseseq FROM tbllearning WHERE studentseq = ?)";
			// ORDER BY 상태(진행중 - 수료 - 중도포기 순으로 가능하면 추가하는데 귀찮스~)
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.setString(2, seq);

			ResultSet rs = pstat.executeQuery();

			ArrayList<StudentCourseDTO> courseList = new ArrayList<StudentCourseDTO>();

			while (rs.next()) {
				StudentCourseDTO dto = new StudentCourseDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setTitle(rs.getString("courseTitle"));
				dto.setStatus(rs.getString("status"));
				dto.setBegindate(rs.getString("begindate"));
				dto.setEnddate(rs.getString("enddate"));
				dto.setRoom(rs.getString("roomTitle"));
				dto.setTotalPeople(rs.getString("totalPeople"));
				dto.setLearnseq(rs.getString("learnseq"));
				courseList.add(dto);
			}

			rs.close();
			pstat.close();
			return courseList;

		} catch (Exception e) {
			System.out.println("StudentDAO의 courseList() : " + e.toString());
		}

		return null;// 실패 시 null반환

	}

//--------------------------------
	/***
	 * 특정 교육생의 특정과정에 해당하는 과목목록들을 반납하는 메소드
	 * @param StudentCourseDTO course : 특정교육생의 특정과정 한개
	 * @return ArrayList<StudentSubjectDTO> : 특정과정에맞는 과목목록
	 */
	public ArrayList<StudentSubjectDTO> subjectList(StudentCourseDTO course) {

		try {
			String sql = "SELECT  " + "    (SELECT subject_name FROM tblSubject WHERE seq = cs.subjectseq) AS title,  "
					+ "    CASE " + "        WHEN TO_DATE(enddate,'YYYY-MM-DD') <= SYSDATE "
					+ "        THEN TO_CHAR((SELECT " + "                COUNT(CASE "
					+ "                    WHEN pe.answer = pa.answer THEN 1 " + "                END)*2 AS pil "
					+ "            FROM tblpilgiexam pe " + "                JOIN tblpilgianswer pa "
					+ "                    ON pe.seq = pa.pilgiexamseq "
					+ "                        WHERE pa.learningseq = ? AND pe.coursesubjectseq = cs.seq)) "
					+ "        ELSE '미실시' " + "    END AS pilPoint, " + "    CASE "
					+ "        WHEN TO_DATE(enddate,'YYYY-MM-DD') <= SYSDATE " + "        THEN TO_CHAR((SELECT "
					+ "                COUNT(CASE " + "                    WHEN se.answer = sa.answer THEN 1 "
					+ "                END)*4 AS sil " + "            FROM tblsilgiexam se "
					+ "                JOIN tblsilgianswer sa " + "                    ON se.seq = sa.silgiexamseq "
					+ "                        WHERE sa.learningseq = ? AND se.coursesubjectseq = cs.seq)) "
					+ "        ELSE '미실시' " + "    END AS silPoint, " + "    (SELECT " + "        CASE "
					+ "            WHEN TO_DATE(enddate,'YYYY-MM-DD')+1 <= SYSDATE " + "            THEN "
					+ "            TO_CHAR(ROUND((COUNT( " + "                CASE "
					+ "                    WHEN status = '출근' THEN 1  " + "                END " + "            )*1 "
					+ "            + " + "            COUNT( " + "                CASE  "
					+ "                    WHEN status NOT IN ('출근','결석') THEN 1 " + "                END "
					+ "            )*0.5) " + "            /COUNT(status)*100/5,2)) " + "            ELSE '미실시' "
					+ "        END AS attend " + "    FROM tblattended " + "    WHERE learningseq = ?  "
					+ "        AND intime >= TO_DATE(begindate,'YYYY-MM-DD') "
					+ "        AND outtime < TO_DATE(enddate,'YYYY-MM-DD')+1) AS attendPoint, " + "    begindate,  "
					+ "    enddate " + " FROM tblcoursesubject cs WHERE courseseq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, course.getLearnseq());
			pstat.setString(2, course.getLearnseq());
			pstat.setString(3, course.getLearnseq());
			pstat.setString(4, course.getSeq());

			ResultSet rs = pstat.executeQuery();

			ArrayList<StudentSubjectDTO> subjectList = new ArrayList<StudentSubjectDTO>();

			// rs -> (복사) -> list
			while (rs.next()) {
				// 레코드 1개 -> DTO 1개
				StudentSubjectDTO dto = new StudentSubjectDTO();

				dto.setTitle(rs.getString("title"));
				dto.setBeigndate(rs.getString("begindate"));
				dto.setEnddate(rs.getString("enddate"));
				dto.setPilPoint(rs.getString("pilPoint"));
				dto.setSilPoint(rs.getString("silPoint"));
				dto.setAttendPoint(rs.getString("attendPoint"));

				// ㄴAlias의 이름에 맞게 해야된다.
				// TODO 점수까지 넣어야합니다!!! 쿼리에

				subjectList.add(dto);
			}

			rs.close();
			pstat.close();
			return subjectList;

		} catch (Exception e) {
			System.out.println("StudentDAO의 subjectList() : " + e.toString());
		}

		return null;// 실패 시 null반환

	}
//--------------------------
	/**
	 * 특정교육생의 출결리스트를 반납하는 메소드 
	 * @param String seq : 특정교육생의 고유번호
	 * @return ArrayList<StudentAttendDTO> : 특정교육생에 해당하는 출석 기록들을 반납
	 */
	public ArrayList<StudentAttendDTO> attendedList(String seq) {

		try {
			String sql = "SELECT " + "    intime, " + "    outtime, " + "    status, "
					+ "    (SELECT title FROM tblcoursetitle WHERE seq = (SELECT coursetitleseq FROM tblcourse WHERE seq = (SELECT courseseq FROM tbllearning WHERE a.learningseq = seq))) AS courseTitle "
					+ " FROM tblattended a WHERE learningseq IN (SELECT seq FROM tbllearning WHERE studentseq =?) ORDER BY intime DESC";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			ResultSet rs = pstat.executeQuery();

			ArrayList<StudentAttendDTO> attendList = new ArrayList<StudentAttendDTO>();

			while (rs.next()) {

				StudentAttendDTO dto = new StudentAttendDTO();

				dto.setIntime(rs.getString("intime"));
				dto.setOutime(rs.getString("outtime"));
				dto.setStatus(rs.getString("status"));
				dto.setCourseTitle(rs.getString("courseTitle"));

				attendList.add(dto);
			}
			rs.close();
			pstat.close();
			return attendList;

		} catch (Exception e) {
			System.out.println("StudentDAO의 attendedList() : " + e.toString());
		}

		return null;// 실패 시 null반환
	}
	
	/**
	 * 특정 교육생의 당일날 출석데이터를 삽입하는 메소드
	 * @param String seq : 특정 교육생의 고유번호
	 * @return int inputAttend : 출석성공의 결과값 반환
	 */
	public int inputAttend(String seq) {

		String sql = "UPDATE tblattended SET " + " outtime =  " + "    CASE  "
				+ "        WHEN TO_CHAR(SYSDATE,'YYYY-MM-DD')||'/00:00' <> TO_CHAR(intime,'YYYY-MM-DD/HH24:MI') "
				+ "        THEN SYSDATE " + "        ELSE outtime " + "    END, " + " intime =  " + "    CASE "
				+ "        WHEN TO_CHAR(SYSDATE,'YYYY-MM-DD')||'/00:00' = TO_CHAR(intime,'YYYY-MM-DD/HH24:MI') "
				+ "        THEN SYSDATE " + "        ELSE intime " + "    END, " + " status = " + "    CASE "
				+ "        WHEN intime <= TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD')||'/09:00','YYYY-MM-DD/HH24:MI') "
				+ "            AND outtime >= TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD')||'/18:00','YYYY-MM-DD/HH24:MI') "
				+ "        THEN '출결' "
				+ "        WHEN intime > TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD')||'/09:00','YYYY-MM-DD/HH24:MI') "
				+ "            AND intime < TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD')||'/18:00','YYYY-MM-DD/HH24:MI')  "
				+ "            AND ((TO_CHAR(outtime,'HH24'))*60 + TO_CHAR(outtime,'MI') - (TO_CHAR(intime,'HH24'))*60 + TO_CHAR(intime,'MI') ) >= 240 "
				+ "        THEN '지각' " + "        WHEN intime <> TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD')  "
				+ "            AND outtime < TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD')||'/18:00','YYYY-MM-DD/HH24:MI') "
				+ "            AND ((TO_CHAR(outtime,'HH24'))*60 + TO_CHAR(outtime,'MI') - (TO_CHAR(intime,'HH24'))*60 + TO_CHAR(intime,'MI') ) >= 240 "
				+ "        THEN '조퇴' " + "        ELSE '결석' " + "    END "
				+ " WHERE learningseq = (SELECT seq FROM tbllearning WHERE studentseq =  ? "
				+ "		 AND ing = '진행중') "
				+ "        AND TO_CHAR(intime,'YYYY-MM-DD') = TO_CHAR(SYSDATE,'YYYY-MM-DD')";

		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq); // 1번째 ?에 셋팅

			int num = pstat.executeUpdate();
			pstat.close();

			return num; // 완료시 1반납

		} catch (SQLException e) {
			System.out.println("StudentDAO클래스의 inputAttend() : " + e.toString());
			e.printStackTrace();
		}

		return 0; // 실패시 0을반납하도록함!

	}
	
	/**
	 * 교육생의 당일출석이 입실인지 퇴실인지 반납해주는 메소드
	 * @param seq : 특정교육생의 고유번호
	 * @return 출석이 입실인지 퇴실인지 반납
	 */
	public String checkAttend(String seq) {
		String sql = "SELECT " + "    CASE " + "        WHEN TO_CHAR(intime,'HH24:MI') = '00:00' THEN '입실 '||TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI') "
				+ "        ELSE '퇴실 '||TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI') " + "    END AS io, " + " 	intime " + " FROM tblattended "
				+ " WHERE learningseq = (SELECT seq FROM tbllearning WHERE studentseq = ? AND ing = '진행중') "
				+ "    AND TO_CHAR(intime,'YYYY-MM-DD') = TO_CHAR(SYSDATE,'YYYY-MM-DD') ";

		try {

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			ResultSet rs = pstat.executeQuery();

			String io = null;
			if (rs.next()) {
				io = rs.getString("io");

			}

			rs.close();
			pstat.close();

			return io;

		} catch (Exception e) {
			System.out.println("StudentDAO의 checkAttend() : " + e.toString());
		}
		return null;
	}
	/**
	 * 특정 교육생(스터디 조장)에 맞는 스터디 정보를 반납하는 메소드
	 * @param seq : 특정 교육생의 번호
	 * @return 스터디정보를 반납합니다
	 */
	public StudyDTO leaderLogin(String seq) {

		String sql = "SELECT * FROM tblstudy WHERE leader =  " + "    (SELECT seq FROM tbllearning "
				+ "        WHERE studentseq = ? AND ing = '진행중')";
		try {

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			ResultSet rs = pstat.executeQuery();

			StudyDTO leader = new StudyDTO();
			if (rs.next()) {
				leader.setSeq(rs.getString("seq"));
				leader.setName(rs.getString("Name"));
				leader.setLimit(rs.getString("limit"));
				leader.setLeader(rs.getString("leader"));

				rs.close();
				pstat.close();

				return leader;
			}

		} catch (Exception e) {
			System.out.println("StudentDAO의 leaderLogin() : " + e.toString());
		}

		return null;
	}
	/**
	 * 당일 모든 스터디의 강의실 예약내역을 반납하는 메소드
	 * @return 당일의 모든 스터디의 강의실 예약내역
	 */
	public ArrayList<StudentRoomStatusDTO> leader() {

		try {
			String sql = "SELECT  " + "    seq, " + "    reservedate, " + "    roomseq, " + "    studyseq, "
					+ "    (SELECT name FROM tblroom WHERE seq = rs.roomseq) AS roomtitle, "
					+ "    (SELECT name FROM tblstudy WHERE seq = rs.studyseq) AS studytitle "
					+ " FROM tblroomstatus rs " + " WHERE reservedate = TO_CHAR(SYSDATE,'YYYY-MM-DD') ORDER BY seq";

			ResultSet rs = stat.executeQuery(sql);

			ArrayList<StudentRoomStatusDTO> rentList = new ArrayList<StudentRoomStatusDTO>();

			while (rs.next()) {
				StudentRoomStatusDTO dto = new StudentRoomStatusDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setReservedate(rs.getString("reservedate"));
				dto.setRoomseq(rs.getString("roomseq"));
				dto.setStudyseq(rs.getString("studyseq"));
				dto.setRoomtitle(rs.getString("roomtitle"));
				dto.setStudytitle(rs.getString("studytitle"));

				rentList.add(dto);
			}

			rs.close();
			return rentList;

		} catch (Exception e) {
			System.out.println("StudentDAO의 leader() : " + e.toString());
		}
		return null;
	}
	
	/**
	 * 모든 강의실정보를 반납하는 메소드
	 * @return 모든 강의실정보 반납
	 */
	public ArrayList<RoomDTO> leaderRoomList() {
		try {
			String sql = "SELECT * FROM tblroom "
					+ " WHERE seq NOT IN (SELECT roomseq FROM tblroomstatus WHERE reservedate = TO_CHAR(SYSDATE,'YYYY-MM-DD')) ORDER BY seq";

			ResultSet rs = stat.executeQuery(sql);

			ArrayList<RoomDTO> roomList = new ArrayList<RoomDTO>();

			while (rs.next()) {
				RoomDTO dto = new RoomDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setLimit(rs.getString("limit"));

				roomList.add(dto);
			}

			rs.close();
			return roomList;

		} catch (Exception e) {
			System.out.println("StudentDAO의 leaderRoomList() : " + e.toString());
		}
		return null;
	}
	
	/***
	 * 특정스터디가 강의실을 빌리고 그 기록데이터를 삽입하는 메소드
	 * @param room : 해당 강의실 번호
	 * @param study : 해당 스터디 번호
	 * @return 빌리기 성공유무 반납 실패시 0
	 */
	public int studyRoomRent(String room, String study) {
		String sql = "INSERT INTO tblroomstatus(seq, reservedate, roomseq, studyseq) "
				+ "    VALUES((SELECT MAX(seq) FROM tblroomstatus)+1, TO_CHAR(SYSDATE,'YYYY-MM-DD'), ?, ? )";

		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, room); // 1번째 ?에 셋팅
			pstat.setString(2, study); // 1번째 ?에 셋팅

			return pstat.executeUpdate();

		} catch (SQLException e) {
			System.out.println("StudentDAO클래스의 studyRoomRent() : " + e.toString());
			e.printStackTrace();
		}

		return 0;
	}
	
	/***
	 * StudentDAO의 클래스가 연결되었는지 확인하는 메소드
	 * @return 연결 성공유무 반납
	 */
	public boolean isConnect() {

		try {
			return !this.conn.isClosed();
			// ㄴ반대값 연결되있으면 true 반납하게
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("StudentDAO클래스의 isConnect() : " + e.toString());

		}
		return false;

	}
	/***
	 * StudentDAO의 연결을 끊는 메소드
	 */
	public void close() {

		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("StudentDAO클래스의 close() : " + e.toString());
		}
	}

	/***
	 * 특정 교육생이 속해있는 스터디 정보를 반납하는 메소드
	 * @param seq : 특정 교육생의 번호
	 * @return 교육생이 속해있는 스터디 정보를 반납합니다
	 */
	public StudyDTO followers(String seq) {
		String sql = "SELECT * FROM tblstudy WHERE seq = (SELECT studyseq FROM tblstudystudent WHERE learningseq = (SELECT seq FROM tbllearning "
				+ " WHERE studentseq = ? AND ing = '진행중'))";
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			ResultSet rs = pstat.executeQuery();

			if(rs.next()) {
				// 레코드 1개 -> DTO 1개
				StudyDTO dto = new StudyDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setLimit(rs.getString("limit"));
				dto.setLeader(rs.getString("leader"));
				return dto;
			}


		} catch (Exception e) {
			System.out.println("StudentDAO의 followers() : " + e.toString());
		}

		return null;
	}
	/***
	 * 특정 스터디의 당일 예약내역을 불러오는 메소드
	 * @param study : 특정 스터디 정보
	 * @return 당일 해당스터디의 예약내역을 반납
	 */
	public StudentRoomStatusDTO followersStudyCheck(StudyDTO study) {

		try {
			String sql = "SELECT  " + "    seq, " + "    reservedate, " + "    roomseq, " + "    studyseq, "
					+ "    (SELECT name FROM tblroom WHERE seq = rs.roomseq) AS roomtitle, "
					+ "    (SELECT name FROM tblstudy WHERE seq = rs.studyseq) AS studytitle "
					+ " FROM tblroomstatus rs " + " WHERE reservedate = TO_CHAR(SYSDATE,'YYYY-MM-DD') AND studyseq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, study.getSeq());
			
			ResultSet rs = pstat.executeQuery();
			

			if(rs.next()) {
				StudentRoomStatusDTO dto = new StudentRoomStatusDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setReservedate(rs.getString("reservedate"));
				dto.setRoomseq(rs.getString("roomseq"));
				dto.setStudyseq(rs.getString("studyseq"));
				dto.setRoomtitle(rs.getString("roomtitle"));
				dto.setStudytitle(rs.getString("studytitle"));

				rs.close();
				return dto;
			}


		} catch (Exception e) {
			System.out.println("StudentDAO의 followersStudyCheck() : " + e.toString());
		}
		return null;
	}
	
}
