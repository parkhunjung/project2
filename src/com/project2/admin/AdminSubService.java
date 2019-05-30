package com.project2.admin;

import java.util.ArrayList;


import com.project2.main.AdminDTO;
import com.project2.main.BookDTO;
import com.project2.main.CourseDTO;
import com.project2.main.CourseSubjectDTO;
import com.project2.main.CourseTitleDTO;
import com.project2.main.LearningDTO;
import com.project2.main.StudentDTO;
import com.project2.main.SubjectDTO;
import com.project2.main.TeacherDTO;
import com.project2.main.UserScanner;

/**
 * 관리자 - 기능 클래스
 * @author sist47
 *
 */
public class AdminSubService {

	private AdminView view;
	private UserScanner scan;
	private AdminDTO aDto;
	private AdminDAO dao;
	private TeacherDTO tdto;
	
	public AdminSubService() {
		this.view = new AdminView();
		this.scan = new UserScanner();
		this.aDto = new AdminDTO();
		dao = new AdminDAO();
		tdto = new TeacherDTO();
		
	}

	
	/**
	 * 관리자 - 강사 정보 등록 메소드
	 */
	public void add() {
		dao = new AdminDAO();
		boolean loop = true;

		while (loop) {
			System.out.println("\t\t\t\t\t\t\t\t\t[강사 정보 입력 메뉴 입니다.]");

			System.out.print("\t\t\t\t\t\t\t\t\t강사이름 : ");
			String name = scan.nextLine();
			name = name.replace("'", "''");

			System.out.print("\t\t\t\t\t\t\t\t\t주민번호(-를 포함해서 작성해주세요.) : ");
			String ssn = scan.nextLine();
			ssn = ssn.replace("'", "''");

			System.out.print("\t\t\t\t\t\t\t\t\t전화번호(-를 포함해서 작성해주세요.) : ");
			String tel = scan.nextLine();
			tel = tel.replace("'", "''");

			System.out.print("\t\t\t\t\t\t\t\t\t주소지 : ");
			String address = scan.nextLine();
			address = address.replace("'", "''");

			tdto.setName(name);
			tdto.setSsn(ssn);
			tdto.setTel(tel);
			tdto.setAddress(address);

			int result = dao.add(tdto);

			dao.close();

			if (result == 1) {
				System.out.println("\t\t\t\t\t\t\t\t\t[교사 정보 추가 완료]");
			} else {
				System.out.println("\t\t\t\t\t\t\t\t\t[교사 정보 추가 실패]");

			}
			view.pause();
			loop = false;

		} // loop

	}

	/**
	 * 관리자 - 강사 정보 수정 메소드
	 */
	public void tEdit() {
		dao = new AdminDAO();

		view.teachertitle(AdminView.EDIT);
		ArrayList<TeacherDTO> list = dao.tlist();

		for (TeacherDTO dto : list) {
			System.out.printf("%s\t%-15s\t%s\t%s\n", dto.getSeq(), dto.getName(), dto.getTel(), dto.getAddress());
		}
		System.out.print("\t\t\t\t\t\t\t\t\t수정할 번호 : ");
		String seq = scan.nextLine();

		TeacherDTO dto = dao.get(seq);

		String name = "";
		String tel = "";
		String address = "";

		if (dto != null) {
			name = dto.getName();
			tel = dto.getTel();
			address = dto.getAddress();
		}
		System.out.println("\t\t\t\t\t\t\t\t\t[수정하지 않으려면 엔터키를 입력하세요.]");

		System.out.println("\t\t\t\t\t\t\t\t\t이름 : " + name);
		System.out.print("\t\t\t\t\t\t\t\t\t새로운 이름 : ");
		String temp = scan.nextLine();
		if (!temp.equals("")) {
			name = temp;
			name = name.replace("'", "''");
		} else {
		}
		System.out.println("\t\t\t\t\t\t\t\t\t기존 전화번호 : " + tel);
		System.out.print("\t\t\t\t\t\t\t\t\t새로운 전화번호 : ");
		temp = scan.nextLine();
		if (!temp.equals("")) {
			tel = temp;
			tel = tel.replace("'", "''");
		}
		System.out.println("\t\t\t\t\t\t\t\t\t기존 주소 : " + address);
		System.out.print("\t\t\t\t\t\t\t\t\t새로운 주소 : ");
		temp = scan.nextLine();
		if (!temp.equals("")) {
			address = temp;
			address = address.replace("'", "''");
		}
		dto = new TeacherDTO();
		dto.setSeq(seq);
		dto.setName(name);
		dto.setTel(tel);
		dto.setAddress(address);

		int result = dao.edit(dto);

		if (result == 1) {
			System.out.println("\t\t\t\t\t\t\t\t\t[교사 정보 수정 완료]");
		} else {
			System.out.println("\t\t\t\t\t\t\t\t\t[교사 정보 수정 실패]");
		}

		view.pause();

		dao.close();
	}
	

	/**
	 * 관리자 - 강사 정보 삭제(수정) 메소드
	 */
	public void tDel() {
		dao = new AdminDAO();

		System.out.println("\t\t\t\t\t\t\t\t\t[교사 정보 입니다.]");
		view.teachertitle(AdminView.LIST);
		ArrayList<TeacherDTO> list = dao.tlist();

		for (TeacherDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", dto.getSeq(), dto.getName(), dto.getSsn(), dto.getTel(),
					dto.getAddress(), dto.getDelete_status());
		}

		boolean loop = true;
		while (loop) {

			System.out.print("\t\t\t\t\t\t\t\t\t교사 정보를 삭제하시겠습니까? (y / n) : ");
			String del = scan.nextLine();

			if (del.equals("y")) {
				System.out.print("\t\t\t\t\t\t\t\t\t삭제할 번호: ");
				String seq = scan.nextLine();

				int result = dao.del(seq);

				if (result == 1) {
					System.out.println("\t\t\t\t\t\t\t\t\t[탈퇴가 성공하였습니다.]");
					loop = false;

				} else {
					System.out.println("\t\t\t\t\t\t\t\t\t[교사 정보 삭제 실패]");

				}

			} else if (del.equals("n")) {
				loop = false;
				System.out.println("\t\t\t\t\t\t\t\t\t[정보 조회를 종료합니다.]");
			} else {

				view.pauseBack();

			}
		} // loop

		view.pause();
		dao.close();
	}

	
	/**
	 * 관리자 - 시험 및 성적 등록 여부 메소드
	 */
	public void scoreADD() {
		dao = new AdminDAO();

		boolean loop = true;

		while (loop) {

			view.scoreAddmenu();

			String sel = scan.nextLine();

			if (sel.equals("1")) {
				pilgiADD();
			} else if (sel.equals("2")) {
				silgiAdd();
			} else if (sel.equals("0")) {
				loop = false;
			} else {
				view.pauseBack();
			}
		}

		dao.close();
	}
	


	/**
	 * 관리자 - 실기 등록 여부 메소드
	 */
	private void silgiAdd() {
		dao = new AdminDAO();
		AdminDAO aDao = new AdminDAO();

		int cnt = aDao.courseCnt();

		boolean temp = true;

		while (temp) {
			System.out.printf("\t\t\t\t\t\t\t\t\t과정 번호 입력(1 ~ %d까지 입력) : ", cnt);
			int cseq = scan.nextInt();

			if (cseq <= 0 || cseq > cnt) {
				view.pauseBack();
			} else {

				view.scoreAddtitle(AdminView.SILGI);

				ArrayList<SilgiRegiDTO> list = aDao.sRegi(cseq);

				temp = false;

				for (SilgiRegiDTO sDto : list) {

					System.out.printf("\t\t\t\t\t\t\t\t\t%-15s\t\t%s\t\t\t%s\r\n", sDto.getSubjectName(),
							sDto.getExamRegistry(), sDto.getGradeRegistry());

				}
			}
		}
		aDao.close();

		view.pause();

	}
	
	/**
	 * 관리자 - 필기 등록 여부 메소드
	 */
	private void pilgiADD() {
		dao = new AdminDAO();
		AdminDAO aDao = new AdminDAO();
		boolean temp = true;

		int cnt = aDao.courseCnt();

		while (temp) {
			System.out.printf("\t\t\t\t\t\t\t\t\t과정 번호 입력(1 ~ %d까지 입력) : ", cnt);
			int cseq = scan.nextInt();

			if (cseq <= 0 || cseq > cnt) {
				view.pauseBack();
			} else {

				view.scoreAddtitle(AdminView.PILGI);

				ArrayList<PilgiRegiDTO> list = aDao.pRegi(cseq);

				temp = false;

				for (PilgiRegiDTO pDto : list) {

					System.out.printf("\t\t\t\t\t\t\t\t\t%-15s\t\t%s\t\t\t%s\r\n" // 0417헌정수정
							, pDto.getSubjectName(), pDto.getExamRegistry(), pDto.getGradeRegistry());

				}

			}
		}
		aDao.close();

		view.pause();

	}

	
	/**
	 * 관리자 - 교육생 성적 조회 메소드
	 */
	public void scoreLIST() {

		dao = new AdminDAO();
		boolean loop = true;

		while (loop) {

			view.scoreLISTMenu();

			String sel = scan.nextLine();

			if (sel.equals("1"))
				to_subjectSEARCH();
			else if (sel.equals("2"))
				to_studentSEARCH();

			else if (sel.equals("0")) {
				loop = false;
			} else {
				view.pauseBack();
			}

		}

		dao.close();

	}
	
	


	/**
	 * 관리자 - 교육생별 성적 조회 메소드
	 */
	private void to_studentSEARCH() {
		dao = new AdminDAO();
		System.out.print("\t\t\t\t\t\t\t\t\t검색할 학생 번호 : ");
		String seq = scan.nextLine();

		view.scoreLISTtitle(AdminView.ADD);

		AdminDAO aDao = new AdminDAO();

		ArrayList<GradeStudentDTO> list = aDao.gradeStudent(seq);

		for (GradeStudentDTO gsDto : list) {

			System.out.printf("%-15s\t\t%s\t\t\t%s\t%s\t%s\t\t%s\t\t%-13s\t\t%s\t\t\t%s\t%s\r\n",
					gsDto.getStudentName(), gsDto.getBackSsn(), gsDto.getOpenCourseName(), gsDto.getOpenCoursePeriod(),
					gsDto.getRoomName(), gsDto.getTeacherName(), gsDto.getOpenSubjectName(),
					gsDto.getOpenSubjectPeriod(), gsDto.getPilgi(), gsDto.getSilgi());
		}

		aDao.close();

		view.pause();

	}
	
	
	/**
	 * 관리자 - 과목별 교육생 성적 조회
	 */
	private void to_subjectSEARCH() {
		dao = new AdminDAO();
		System.out.print("\t\t\t\t\t\t\t\t\t검색할 과목 번호 : ");
		String seq = scan.nextLine();

		view.scoreLISTtitle(AdminView.SCOREJOHAE);

		AdminDAO aDao = new AdminDAO();

		ArrayList<GradeSubjectDTO> list = aDao.gradeSubject(seq);

		for (GradeSubjectDTO gDto : list) {
			System.out.printf("%s\t\t%s\t%s\t%s\t\t%s\t\t%s\t%-15s\t\t%s\t\t\t%s\t%s\r\n", gDto.getTeacherName(),
					gDto.getOpenCourseName(), gDto.getOpenCoursePeriod(), gDto.getRoomName(), gDto.getSubjectName(),
					gDto.getBookName(), gDto.getStudentName(), gDto.getBackSsn(), gDto.getPilgi(), gDto.getSilgi());
		}

		aDao.close();

		view.pause();

	}

	
	/**
	 * 관리자 - 교육생 출결 수정 메소드
	 */
	public void attend_management() {
		dao = new AdminDAO();
		view.attend_management();

		System.out.print("\t\t\t\t\t\t\t\t\t수정하실 이름 입력 : ");
		String name = scan.nextLine();

		System.out.print("\t\t\t\t\t\t\t\t\t수정하실 날짜 입력 ex)2019-01-28: ");
		String day = scan.nextLine();

		System.out.print("\t\t\t\t\t\t\t\t\t번호 입력(1.출근 2.지각 3.조퇴 4.결석 5.병가 6.기타) : ");
		String status = scan.nextLine();

		dao.management(name, day, status);

		view.pause();

		dao.close();
	}

	/**
	 * 관리자 - 출결 조회 항목선택 메소드 
	 */
	public void attend_inquiry() {

		dao = new AdminDAO();

		AdminSubService subservice = new AdminSubService();
		boolean loop = true;

		while (loop) {

			view.attend_inquiry();

			String sel = scan.nextLine();
			if (sel.equals("1"))
				subservice.inquiry_whole();
			else if (sel.equals("2"))
				subservice.inquiry_day();
			else if (sel.equals("3"))
				subservice.inquiry_month();
			else if (sel.equals("4"))
				subservice.inquiry_year();
			else if (sel.equals("5"))
				subservice.inquiry_course();
			else if (sel.equals("0")) {
				loop = false;
			} else {
				view.pauseBack();
			}

		}

		dao.close();
	}
	
	/**
	 * 관리자 - 과정별 출결 조회 메소드
	 */
	private void inquiry_course() {
		dao = new AdminDAO();
		view.attend_subject();

		System.out.print("\t\t\t\t\t\t\t\t\t과정 번호 입력 : ");
		String seq = scan.nextLine();

		view.attend_subjectsub();

		ArrayList<AdminAttendSubjectDTO> list = dao.attendCourse(seq);

		for (AdminAttendSubjectDTO dto : list) {
			System.out.printf("%-15s\t\t%s\t%s\t\t%s\t\t%s\t%s\r\n", dto.getName(), dto.getAttendStatus(),
					dto.getAttendTime(), dto.getCourseSeq(), dto.getCourseName(), dto.getCoursePeriod());
		}

		view.pause();
		dao.close();

	}

	/**
	 * 관리자 - 교육생 전체 줄결 조회
	 */
	private void inquiry_whole() {
		dao = new AdminDAO();
		view.attend_whole();

		ArrayList<AdminStudentAttendedDTO> list = dao.attendWhole();

		for (AdminStudentAttendedDTO dto : list) {
			System.out.printf("%-15s\t%s\t%s\t%s\t%s\r\n"// (0417 헌정수정)
					, dto.getName(), dto.getIntime(), dto.getOuttime(), dto.getStatus(), dto.getTitle());
		}

		System.out.print("\t\t\t\t\t\t\t\t\t특정 교육생의 출결 정보를 확인하시겠습니까? (y / n) : ");
		String res = scan.nextLine();

		if (res.equals("y")) {
			System.out.print("\t\t\t\t\t\t\t\t\t교육생의 이름 입력 : ");
			String name = scan.nextLine();

			view.attSearch();

			ArrayList<AdminStudentAttendedDTO> list2 = dao.attendSearch(name);

			for (AdminStudentAttendedDTO dto : list2) {
				System.out.printf("%-15s\t\t%s\t%s\t%s\t%s\r\n" // (0417 헌정수정)
						, dto.getName(), dto.getIntime(), dto.getOuttime(), dto.getStatus(), dto.getTitle());

			}
		} else if (res.equals("n")) {
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t[정보 조회를 종료합니다.]");
		}

		view.pause();
		dao.close();

	}

	


	/**
	 * 관리자 - 일별 교육생 출결 조회 메소드
	 */
	public void inquiry_day() {
		dao = new AdminDAO();

		System.out.println("\t\t\t\t\t\t\t\t\t원하는 날짜를 입력하세요. ex) 2019-01-28 ");
		System.out.print("\t\t\t\t\t\t\t\t\t > ");
		String day = scan.nextLine();

		view.attend_day();

		ArrayList<AdminStudentAttendedDTO> list = dao.day(day);

		for (AdminStudentAttendedDTO dto : list) {
			System.out.printf("%s\t%s\t\t%s\t\t%-15s\t\t%s\t\t%s\t\n" // (헌정0417수정)
					, dto.getDay(), dto.getIntime(), dto.getOuttime(), dto.getName(), dto.getStatus(), dto.getTitle());
		}

		view.pause();
		dao.close();
	}

	/**
	 * 관리자 - 월별 교육생 출결 조회 메소드
	 */
	public void inquiry_month() {
		dao = new AdminDAO();

		System.out.println("\t\t\t\t\t\t\t\t\t원하는 달을 입력하세요. ex) 2019-01 ");
		System.out.print("\t\t\t\t\t\t\t\t\t > ");
		String month = scan.nextLine();

		view.attend_month();

		dao = new AdminDAO();
		ArrayList<AdminStudentAttendedDTO> list = dao.month(month);

		for (AdminStudentAttendedDTO dto : list) {
			System.out.printf("%s\t%s\t\t%s\t\t%-15s\t\t%s\t\t%s\t\n"
					, dto.getDay(), dto.getIntime(), dto.getOuttime(), dto.getName(), dto.getStatus(), dto.getTitle());
		}

		view.pause();
		dao.close();
	}

	
	/**
	 * 관리자 - 년도별 교육생 출결 조회 메소드
	 */
	public void inquiry_year() {
		dao = new AdminDAO();

		System.out.println("\t\t\t\t\t\t\t\t\t원하는 년도를 입력하세요. ex) 2019 ");
		System.out.print("\t\t\t\t\t\t\t\t\t > ");
		String year = scan.nextLine();

		view.attend_year();

		dao = new AdminDAO();
		ArrayList<AdminStudentAttendedDTO> list = dao.year(year);

		for (AdminStudentAttendedDTO dto : list) {
			System.out.printf("%s\t%s\t\t%s\t\t%-15s\t\t%s\t\t%s\t\n"
					, dto.getDay(), dto.getIntime(), dto.getOuttime(), dto.getName(), dto.getStatus(), dto.getTitle());
		}

		view.pause();
		dao.close();
	}
	
	
	
	/**
	 * 관리자 - 교육생 정보 등록 메소드
	 */
	public void student_Input() {

		AdminDAO aDao = new AdminDAO();

		System.out.print("\t\t\t\t\t\t\t\t\t이름 : ");
		String name = scan.nextLine();
		name = name.replace("'", "''");

		System.out.print("\t\t\t\t\t\t\t\t\t주민번호(-를 포함해서 작성해주세요.) : ");
		String ssn = scan.nextLine();

		System.out.print("\t\t\t\t\t\t\t\t\t전화번호(-를 포함해서 작성해주세요.) : ");
		String tel = scan.nextLine();

		System.out.print("\t\t\t\t\t\t\t\t\t주소지 : ");
		String address = scan.nextLine();
		address = address.replace("'", "''");

		StudentDTO sDto = new StudentDTO();
		sDto.setName(name);
		sDto.setSsn(ssn);
		sDto.setTel(tel);
		sDto.setAddress(address);

		int result = aDao.sInput(sDto);

		view.courseList2();
		ArrayList<AdminCourseInfoDTO> clist = aDao.courseInfoList2();

		for (AdminCourseInfoDTO dto : clist) {
			System.out.printf("%-3s\t\t%30s\t%s\t\t%s\t\t%s\t\t%s\r\n", dto.getCourseSeq(), dto.getCourseTitle(),
					dto.getBegindate(), dto.getEnddate(), dto.getRoomName(), dto.getStatus());
		}

		System.out.print("\t\t\t\t\t\t\t\t\t과정 선택 : ");
		String cSel = scan.nextLine();

		int result2 = aDao.addLearning(cSel);

		if (result == 1 && result2 == 1) {
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t[교육생 추가를 완료했습니다.]");
		} else {
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t[교육생 추가에 실패했습니다.]");
		}

		aDao.close();
		view.pause();

	}
	
	/**
	 * 관리자 - 교육생 정보 수정 하는 메소드
	 */
	public void student_Modified() {
		dao = new AdminDAO();
		view.student_editcolumn();

		AdminDAO aDao = new AdminDAO();

		ArrayList<StudentDTO> list = aDao.sElist();

		for (StudentDTO sDto : list) {
			System.out.printf("%s\t%-15s\t%s\t%s\t%s\t%s\r\n", sDto.getSeq(), sDto.getName(), sDto.getSsn(), sDto.getTel(),
					sDto.getAddress(), sDto.getDelete_status());
		}

		view.student_eline();

		boolean loop = true;
		while (loop) {
			System.out.print("\t\t\t\t\t\t\t\t\t수정할 번호 : ");
			String seq = scan.nextLine();

			if (!seq.equals("")) {
				StudentDTO sDto = aDao.sGet(seq);

				String tel = "";
				String address = "";
				String delete_status = "";

				if (sDto != null) {
					tel = sDto.getTel();
					address = sDto.getAddress();
					delete_status = sDto.getDelete_status();
				}

				System.out.println("\t\t\t\t\t\t\t\t\t[수정하지 않으려면 엔터를 입력해주세요.]");
				System.out.println("\t\t\t\t\t\t\t\t\t기존 전화번호 : " + tel);
				System.out.print("\t\t\t\t\t\t\t\t\t새로운 전화번호 : ");
				String temp = scan.nextLine();

				if (!temp.equals("")) {
					tel = temp;
					tel = tel.replace("'", "''");
				}

				System.out.println("\t\t\t\t\t\t\t\t\t기존 주소지 : " + address);
				System.out.print("\t\t\t\t\t\t\t\t\t새로운 주소지 : ");
				temp = scan.nextLine();

				if (!temp.equals("")) {
					address = temp;
					address = address.replace("'", "''");
				}

				System.out.println("\t\t\t\t\t\t\t\t\t기존 상태 : " + delete_status);
				System.out.print("\t\t\t\t\t\t\t\t\t새로운 상태('회원' or '탈퇴') : ");
				temp = scan.nextLine();

				if (!temp.equals("")) {
					delete_status = temp;
					delete_status = delete_status.replace("'", "''");
				}

				sDto = new StudentDTO();
				sDto.setSeq(seq);
				sDto.setTel(tel);
				sDto.setAddress(address);
				sDto.setDelete_status(delete_status);

				int result = aDao.sEdit(sDto);

				if (temp.equals("탈퇴")) {

					LearningDTO lDto = new LearningDTO();
					lDto.setSeq(seq);

					int result2 = aDao.lEdit(lDto);

					if (result2 == 1) {
						System.out.println(); 
						System.out.println("\t\t\t\t\t\t\t\t\t[수강정보 수정 완료]"); 
					} else {
						System.out.println(); 
						System.out.println("\t\t\t\t\t\t\t\t\t[수강정보 수정 실패]"); 
					}

				}

				if (result == 1) {
					System.out.println(); 
					System.out.println("\t\t\t\t\t\t\t\t\t[회원 수정 완료]"); 
				} else {
					System.out.println(); 
					System.out.println("\t\t\t\t\t\t\t\t\t[회원 수정 실패]"); 
				}

				loop = false;
			} else {
				view.pauseBack();
			}
		}

		aDao.close();

		view.pause();

	}
	
	/**
	 *  관리자 - 교육생 정보 출력 메소드
	 */
	public void student_inquiry() {
		dao = new AdminDAO();
		view.student_columnname();
		
		AdminDAO aDao = new AdminDAO();
		
		ArrayList<StudentDTO> list = aDao.slist();
		
		for(StudentDTO sDto : list) {
			
			System.out.printf("%-4s\t%-15s\t%s\t%s\t%s\t%s\t%s\r\n" 
								, sDto.getSeq()
								, sDto.getName()
								, sDto.getSsn()
								, sDto.getTel()
								, sDto.getRegdate()
								, sDto.getDelete_status()
								, sDto.getAddress());
			
		}
		
		view.student_cline();
		aDao.close();
		
		
		boolean loop = true;
		
		while (loop) {

			System.out.print("\t\t\t\t\t\t\t\t\t특정 교육생의 정보를 확인하시겠습니까? (y / n) : ");
			String res = scan.nextLine();
			
			if (res.equals("y")) {
				System.out.print("\t\t\t\t\t\t\t\t\t교육생의 이름 입력 : ");
				String name = scan.nextLine();
				
				view.soloStudent();
				
				ArrayList<AdminStudentSearchDTO> list2 = dao.StudentSearch(name);
				
				for (AdminStudentSearchDTO dto : list2) {
					System.out.printf("%-15s\t\t%s\t\t%s\t\t%s\t\t\t%s\t%s\t%s\t\t%s\t\t\t%s\t\t%s\r\n"
										, dto.getName()
										, dto.getSsn()
										, dto.getTel()
										, dto.getAddress()
										, dto.getLearningCourse()
										, dto.getCoursePeriod()
										, dto.getRoomName()
										, dto.getIng()
										, dto.getIngdate()
										, dto.getStatus());
				}
				
				loop = false;
				
			} else if (res.equals("n")) {
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t[정보 조회를 종료합니다.]");
				loop = false;
			} else {
				view.pauseBack();
			}
		}
		dao.close();
		view.pause();
		
	}
	
	
	/**
	 *  관리자 - 개설 과정 등록
	 */
	public void courseADD() {
		dao = new AdminDAO();

		boolean loop = true;

		while (loop) {

			view.emptyroomtitle();

			// 남은 강의실 조회 + 선택
			ArrayList<AdminEmptyRoomDTO> erlist = dao.getEmptyRoom();

			for (AdminEmptyRoomDTO erdto : erlist) {
				System.out.printf("\t\t\t\t\t\t\t\t%s\t%s\t\t\t%s\n"
									, erdto.getSeq()
									, erdto.getName()
									, erdto.getLimit());
			}

			
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t등록 할 강의실 번호 선택");
			System.out.print("\t\t\t\t\t\t\t\t\t > ");
			String roomseq = scan.nextLine();

			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t과정명 입력");
			System.out.print("\t\t\t\t\t\t\t\t\t > ");
			String title = scan.nextLine();

			CourseTitleDTO ctdto = new CourseTitleDTO();
			ctdto.setTitle(title);
			dao.addCourseTitle(ctdto);

			// 과정명에 따른 과정번호 가져오기
			String coursetitleseq = dao.getCourseSeq(title);

			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t시작일 입력 ex) 2019-01-28");
			System.out.print("\t\t\t\t\t\t\t\t\t > ");
			String coursebegindate = scan.nextLine();

			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t종료일 입력 ex) 2019-08-21");
			System.out.print("\t\t\t\t\t\t\t\t\t > ");
			String courseenddate = scan.nextLine();

			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t과정 설명 입력");
			System.out.print("\t\t\t\t\t\t\t\t\t > ");
			String courseetc = scan.nextLine();

			// 최종 과정 정보 DTO 생성
			CourseDTO cdto = new CourseDTO();

			cdto.setRoomseq(roomseq);
			cdto.setCoursetitleseq(coursetitleseq);
			cdto.setBegindate(coursebegindate);
			cdto.setEnddate(courseenddate);
			cdto.setEtc(courseetc);

			// 최종 과정 정보 등록
			int courseResult = dao.registCourse(cdto);

			ArrayList<SubjectDTO> slist = dao.getSubject();

			ArrayList<String> sublist = new ArrayList<String>();
			// 과목 선택 for문
			for (int i = 0;; i++) {
				// 과목조회
				view.subjectList();
				for (SubjectDTO sdto : slist) {
					System.out.printf("\t\t\t\t\t\t\t\t\t\t%s\t%s\n"
										, sdto.getSeq()
										, sdto.getSubject_name());
				}

				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t[선택을 그만 하려면 엔터를 입력해주세요.]");
				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t%d과목 번호 선택\n", i + 1);
				System.out.print("\t\t\t\t\t\t\t\t\t > ");
				String subjectseq = scan.nextLine();
				sublist.add(subjectseq);

				if (sublist.get(i).equals("")) {
					break;
				}

				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t%d과목 시작일 입력 ex) 2019-01-28\n", i + 1);
				System.out.print("\t\t\t\t\t\t\t\t\t > ");
				String subjectbegindate = scan.nextLine();

				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t%d과목 종료일 입력 ex) 2019-08-21\n", i + 1);
				System.out.print("\t\t\t\t\t\t\t\t\t > ");
				String subjectenddate = scan.nextLine();

				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t%d과목 설명 입력\n", i + 1);
				System.out.print("\t\t\t\t\t\t\t\t\t > ");
				String subjectetc = scan.nextLine();

				// 강사 조회
				view.teacherList();
				ArrayList<AdminTeacherInfoDTO> atlist = dao.getTeacherList(sublist.get(i));

				for (AdminTeacherInfoDTO atdto : atlist) {
					System.out.printf("\t\t\t\t\t\t\t\t%s\t%s\t\t%s\t%s\n"
										, atdto.getSeq()
										, atdto.getName()
										, atdto.getAge()
										, atdto.getRegdate());
				}

				// 강사 선택
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t강사 번호 선택");
				System.out.print("\t\t\t\t\t\t\t\t\t > ");
				String teacherseq = scan.nextLine();

				// 교재 조회
				view.bookList();

				ArrayList<BookDTO> blist = dao.getBook(subjectseq);

				for (BookDTO bdto : blist) {
					System.out.printf("\t\t\t\t\t\t\t\t%s\t%s\t%s\n"
										, bdto.getSeq()
										, bdto.getTitle()
										, bdto.getPublisher());
				}
				// 교재 선택
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t교재 번호 선택");
				System.out.print("\t\t\t\t\t\t\t\t\t > ");
				String bookseq = scan.nextLine();

				String newcourseseq = dao.getNewCourseSeq();

				// 최종 과목 정보 DTO 생성
				CourseSubjectDTO csdto = new CourseSubjectDTO();

				csdto.setCourseseq(newcourseseq);
				csdto.setSubjectseq(subjectseq);
				csdto.setBookseq(bookseq);
				csdto.setTeacherseq(teacherseq);
				csdto.setBegindate(subjectbegindate);
				csdto.setEnddate(subjectenddate);
				csdto.setEtc(subjectetc);

				// 최종 과목 정보 등록
				int courseSubjectResult = dao.registCourseSubject(csdto);

				// 등록 성공 메시지
				view.registCourseSuccess(courseResult);
				view.registCourseSubjectSuccess(courseSubjectResult, i);

			} // for_i

			loop = false;
		} // loop
		view.pause();
		dao.close();
	}
	
	/**
	 * 관리자 - 개설 과정 정보 수정
	 */
	public void courseSET() {
		dao = new AdminDAO();

		view.courseList();
		ArrayList<AdminCourseInfoDTO> aclist = dao.courseInfoList();

		for (AdminCourseInfoDTO dto : aclist) {
			System.out.printf("%s\t\t%s\t%s\t\t%s\t\t%s\t\t%s\t%s\n\n"
								, dto.getCourseSeq()
								, dto.getCourseTitle()
								, dto.getBegindate()
								, dto.getEnddate()
								, dto.getRoomName()
								, dto.getStatus()
								, dto.getEtc());
		}

		// 과정 선택
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t수정할 과정 번호 선택");
		System.out.print("\t\t\t\t\t\t\t\t\t > ");
		String courseseq = scan.nextLine();

		AdminCourseInfoDTO acdto = dao.courseInfo(courseseq);
		String courseSeq = "";
		String courseTitle = "";
		String begindate = "";
		String enddate = "";
		String roomName = "";
		String status = "";
		String etc = "";

		if (acdto != null) {
			courseSeq = acdto.getCourseSeq();
			courseTitle = acdto.getCourseTitle();
			begindate = acdto.getBegindate();
			enddate = acdto.getEnddate();
			roomName = acdto.getRoomName();
			status = acdto.getStatus();
			etc = acdto.getEtc();
		}

		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t[수정하지 않으려면 엔터키를 입력하세요.]");
		System.out.println("\t\t\t\t\t\t\t\t\t기존 상태 : " + status);
		System.out.println("\t\t\t\t\t\t\t\t\t수정할 상태 ex) 진행중, 폐강, 정상종강");
		System.out.print("\t\t\t\t\t\t\t\t\t > ");
		String alterStatus = scan.nextLine();

		if (!alterStatus.equals("")) {
			status = alterStatus;
		}

		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t[수정하지 않으려면 엔터키를 입력하세요.]");
		System.out.println("\t\t\t\t\t\t\t\t\t기존 비고 : " + etc);
		System.out.println("\t\t\t\t\t\t\t\t\t수정할 비고");
		System.out.print("\t\t\t\t\t\t\t\t\t > ");
		String alterEtc = scan.nextLine();

		if (!alterEtc.equals("")) {
			etc = alterEtc;
		}

		acdto = new AdminCourseInfoDTO();
		acdto.setCourseSeq(courseSeq);
		acdto.setCourseTitle(courseTitle);
		acdto.setBegindate(begindate);
		acdto.setEnddate(enddate);
		acdto.setRoomName(roomName);
		acdto.setStatus(status);
		acdto.setEtc(etc);

		int result = dao.editCourseInfo(acdto);

		if (result == 1) {
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t[과정 정보 수정 완료]");
		} else {
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t[과정 정보 수정 실패]");
		}

		view.pause();
		dao.close();
	}
	
	/**
	 *  관리자 - 개설 과정 검색
	 */
	public void courseSEARCH() {
		dao = new AdminDAO();

		view.courseList();
		ArrayList<AdminCourseInfoDTO> aclist = dao.courseInfoList();

		for (AdminCourseInfoDTO dto : aclist) {
			System.out.printf("%-3s\t\t%30s\t%s\t\t%s\t\t%s\t\t%s\t%s\r\n"
								, dto.getCourseSeq()
								, dto.getCourseTitle()
								, dto.getBegindate()
								, dto.getEnddate()
								, dto.getRoomName()
								, dto.getStatus()
								, dto.getEtc());
		}

		// 과정 선택
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t과정 번호 선택");
		System.out.print("\t\t\t\t\t\t\t\t\t > ");
		String courseseq = scan.nextLine();

		ArrayList<StudentDTO> list = dao.studentInfoList(courseseq);

		view.studentList();
		for (StudentDTO sdto : list) {
			System.out.printf("%-4s\t\t%-15s\t%s\t\t%s\t\t%s\n", sdto.getSeq(), sdto.getName(), sdto.getSsn(),
					sdto.getTel(), sdto.getAddress());
		}

		view.pause();
		dao.close();

	}
	
	
	
	
}









