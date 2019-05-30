package com.project2.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.project2.admin.AdminEmptyRoomDTO;
import com.project2.main.MainView;
import com.project2.main.TeacherDTO;

/**
 * 강사 - 강사 기능 클래스
 * 
 * @author sist56
 *
 */
public class TeacherService implements ITeacherService {

	private TeacherView tview;
	private TeacherSubView tsview;
	private UserScanner scan;
	private TeacherDAO dao;
	private TeacherDTO dto;

	public TeacherService() {

		tview = new TeacherView();
		tsview = new TeacherSubView();
		scan = new UserScanner();

		dto = new TeacherDTO();
	}

	/**
	 * 강사- 로그인 기능
	 */
	public TeacherDTO login() {
		dao = new TeacherDAO();
		ArrayList<TeacherDTO> teacherList = dao.teacherList();
		boolean loop = true;
		TeacherDTO teacher = null;

		while (loop) {
			System.out.println("\t\t\t\t\t\t\t\t\t======================================");
			System.out.println("\t\t\t\t\t\t\t\t\t강사 로그인 화면!");

			System.out.print("\t\t\t\t\t\t\t\t\t아이디(이름) : ");
			String id = scan.nextLine();

			System.out.print("\t\t\t\t\t\t\t\t\t비밀번호(주민번호뒷자리) : ");
			String pw = scan.nextLine();

			for (TeacherDTO tl : teacherList) {
				if (tl.getName().equals(id) && tl.gettPassword().equals(pw)) {

					teacher = tl;

					loop = false;

					tview.loginSucess();
					tview.pause();

					break;

				}

			}

			if (teacher == null) {
				System.out.println("\t\t\t\t\t\t\t\t올바른 입력이 아닙니다!!!");
				System.out.println("\t\t\t\t\t\t\t\t엔터키. 다시입력");
				System.out.println("\t\t\t\t\t\t\t\t0. 뒤로가기");
				System.out.print("\t\t\t\t\t\t\t\t선택 : ");
				String sel = scan.nextLine();

				if (sel.equals("0")) {
					loop = false;

				}

			}

		}

		return teacher;

	}

	/**
	 * 강사 - 담당 과목 정보 출력 기능
	 */
	public void course(TeacherDTO teacher) {
		dao = new TeacherDAO();
		boolean loop = true;

		while (loop) {
			tview.courseTitle();

			ArrayList<CourseDTO> clist = dao.teacher_courseList(teacher);

			for (CourseDTO cdto : clist) {
				System.out.printf("\t\t\t\t\t\t\t\t\t%s\t\t%s\t\t%s\t%s\n", cdto.getCourseID(), cdto.getCoursedate(),
						cdto.getCoursename(), cdto.getStatus());

			}
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t(뒤로가고 싶으면 0을 눌러주세요.)");
			System.out.println("\t\t\t\t\t\t\t\t\t과정번호를 선택해주세요.");
			System.out.print("\t\t\t\t\t\t\t\t\t선택: ");
			String sel = scan.nextLine();

			for (CourseDTO choose : clist) {

				if (sel.equals(choose.getCourseID())) {

					coursesubject(sel, teacher);
					break;
				} else if (sel.equals("0")) {

					loop = false;

				} else {

					tview.pauseBack();

				}

			}

		}

	}

	/**
	 * 담당 과목 정보 출력 기능
	 * 
	 * @param 과정번호
	 * @param 강사번호
	 */
	public void coursesubject(String sel, TeacherDTO teacher) {
		dao = new TeacherDAO();

		boolean loop = true;
		while (loop) {

			tview.subjectTitle();
			ArrayList<SubjectListDTO> slist = dao.SubjectChoose(sel, teacher);
			for (SubjectListDTO sdto : slist) {
				System.out.printf("\t\t\t\t\t\t\t\t\t%s\t\t%-13s\t\t\t%s\t\t\t%s\n", sdto.getSubjectseq(),
						sdto.getSubjectname(), sdto.getSubjectperiod(), sdto.getDescription());
			}
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t뒤로가고 싶으면 0을 눌러주세요.");
			System.out.println("\t\t\t\t\t\t\t\t\t과목번호를 선택해주세요.");
			System.out.print("\t\t\t\t\t\t\t\t\t선택: ");
			String choice1 = scan.nextLine();

			for (SubjectListDTO choose : slist) {

				if (choice1.equals(choose.getSubjectseq())) {

					scoreOrpoints(sel, choice1);

				}

			}
			if (choice1.equals("0")) {

				loop = false;

			} 

		}

	}

	/**
	 * 성적 조회 메뉴 생성 기능
	 * 
	 * @param 과정번호
	 * @param 과목번호
	 */
	public void scoreOrpoints(String sel, String choice1) {

		boolean loop = true;
		while (loop) {

			tview.scoremenu();

			String choice2 = scan.nextLine();

			if (choice2.equals("1")) {
				perscore(sel, choice1);
				System.out.println();
			} else if (choice2.equals("2")) {

				choosepilgi(sel, choice1);

				System.out.println();

			} else if (choice2.equals("3")) {

				numberOfanswer(sel, choice1);
				System.out.println();

			} else if (choice2.equals("4")) {

				subject(sel, choice1);
				System.out.println();
			} else if (choice2.equals("0")) {
				loop = false;
				tview.pause();
			} else {
				tview.pauseBack();
				loop = false;

			}

		}

	}

	/**
	 * 가답안 조회시 필기/실기 메뉴 생성 기능
	 */
	public void choosepilgi(String sel, String choice1) {

		boolean loop = true;
		while (loop) {
			System.out.println("\t\t\t\t\t\t\t\t\t============================");
			System.out.println("\t\t\t\t\t\t\t\t\t[가답안 조회]");
			tview.pilgiORsilgmenu();

			String choice3 = scan.nextLine();

			if (choice3.equals("1")) {
				answerJohae(sel, choice1);
				System.out.println();
			} else if (choice3.equals("2")) {
				answerSilgiJohae(sel, choice1);
				System.out.println();
			} else if (choice3.equals("0")) {
				loop = false;

			} else {

				tview.pauseBack();
			}

		}

	}

	/**
	 * 교육생별 과목별 맞힌 개수 출력 기능
	 */
	public void numberOfanswer(String sel, String choice1) {
		dao = new TeacherDAO();

		boolean loop = true;

		while (loop) {
			System.out.println("\t\t\t\t\t\t\t\t\t[교육생별 문제 개수 조회]");
			tview.numberanswerTitle();
			ArrayList<NumberOfAnswerDTO> noalist = dao.numberOfanswer(sel, choice1);
			;
			for (NumberOfAnswerDTO noadto : noalist) {
				System.out.printf("\t\t\t\t\t\t\t\t\t%s\t%-15s\t%s\t\t%s\t%s\t%s\n", noadto.getSeq(), noadto.getName(),
						noadto.getSubjectname(), noadto.getPilgi(), noadto.getSilgi(), noadto.getAttendance());
			}
			System.out.println("\t\t\t\t\t\t\t\t\t뒤로가고 싶으면 엔터나 0을 눌러주세요.");
			System.out.print("\t\t\t\t\t\t\t\t\t선택: ");
			String choice2 = scan.nextLine();

			loop = false;

		}

	}

	/**
	 * 과목별 필기 답안 출력 기능
	 */
	public void answerJohae(String sel, String choice1) {
		dao = new TeacherDAO();
		boolean loop = true;
		while (loop) {
			tview.answerJohae();

			ArrayList<AnswerPilgiJohaeDTO> apjlist = dao.answerJohae(sel, choice1);
			;
			for (AnswerPilgiJohaeDTO apjdto : apjlist) {
				System.out.printf("\t\t\t\t\t\t\t%s\t\t%s\t\t\t\t\t%s\n", apjdto.getSeq(), apjdto.getQuestion(),
						apjdto.getAnswer());
			}
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t뒤로가고 싶으면 엔터나 0을 눌러주세요.");
			System.out.print("\t\t\t\t\t\t\t\t\t선택: ");
			String choice2 = scan.nextLine();

			loop = false;
		}

	}

	/**
	 * 과목별 실기 답안 출력 기능
	 */
	public void answerSilgiJohae(String sel, String choice1) {
		boolean loop = true;
		while (loop) {
			tview.answerJohae();

			ArrayList<AnswerSilgiJohaeDTO> asjlist = dao.answerSilgiJohae(sel, choice1);
			;
			for (AnswerSilgiJohaeDTO asjdto : asjlist) {
				System.out.printf("\t\t\t\t\t\t\t%s\t\t%s\t\t\t\t\t%s\n", asjdto.getSeq(), asjdto.getQuestion(),
						asjdto.getAnswer());
			}
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t뒤로가고 싶으면 엔터나 0을 눌러주세요.");
			System.out.print("\t\t\t\t\t\t\t\t\t선택: ");
			String choice2 = scan.nextLine();

			loop = false;
		}

	}

	/**
	 * 교육생별 최종성적 출력 기능
	 * 
	 * @param 과정번호
	 * @param 과목번호
	 */
	public void subject(String sel, String subject) {
		dao = new TeacherDAO();
		boolean loop = true;

		while (loop) {

			tview.gradeTitle();

			ArrayList<FinalScoreDTO> fslist = dao.finalchoose(sel, subject);
			for (FinalScoreDTO fsdto : fslist) {
				System.out.printf("\t\t\t\t\t\t\t\t\t%s\t%-15s\t%s\t\t\t%s\t\t\t%s\t\t%s\t\t%s\n", fsdto.getSeq(),
						fsdto.getName(), fsdto.getSubjectname(), fsdto.getAttendancescore(), fsdto.getPilgi(),
						fsdto.getSilgi(), fsdto.getTotal());

			}
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t뒤로 가시려면 0이나 엔터를 눌러주세요.");
			System.out.print("\t\t\t\t\t\t\t\t\t선택: ");
			
			scan.nextLine();
			loop = false;

		}

	}

	/**
	 * 과목별 배점 정보 출력 기능
	 * 
	 * @param 과정번호
	 * @param 과목번호
	 */
	public void perscore(String sel, String subject) {
		dao = new TeacherDAO();
		String seq = "";
		String subjectname = "";
		String noattendance = "";
		String halfattendance = "";
		String attendance = "";
		String attscore = "";
		String pilgiscore = "";
		String silgiscore = "";

		boolean loop = true;
		while (loop) {
			tview.perScore();
			ArrayList<PerScoreListDTO> pslist = dao.perscorego(sel, subject);
			for (PerScoreListDTO psdto : pslist) {
				seq = psdto.getSeq();
				subjectname = psdto.getSubjectname();
				noattendance = psdto.getNotattendance();
				halfattendance = psdto.getHalfattendance();
				attendance = psdto.getAttendance();
				attscore = psdto.getAttscore();
				pilgiscore = psdto.getPilgiscore();
				silgiscore = psdto.getSilgiscore();

			}

			System.out.printf("%s\t\t%-8s\t\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t    %s\t\t%s\n", seq,
					subjectname, noattendance, halfattendance, halfattendance, halfattendance, halfattendance,
					halfattendance, attendance, attscore, pilgiscore, silgiscore);

			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t뒤로가고 싶으면 엔터나 0을 눌러주세요.");
			System.out.print("\t\t\t\t\t\t\t\t\t선택: ");
			String choice1 = scan.nextLine();

			loop = false;
		}

	}

	/**
	 * 강사의 전체 스케줄 출력 기능
	 * 
	 * @param 강사번호
	 */

	public void schedule(TeacherDTO teacher) {

		tview.scheduleTitle();

		dao = new TeacherDAO();

		ArrayList<ScheduleDTO> schedule = dao.schedule(teacher);

		for (ScheduleDTO dto : schedule) {
			System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%-12s\t\t%s\t\t%-15s\t\t%s\n", dto.getSeq(),
					dto.getCoursetitle(), dto.getCoursedate(), dto.getRoomname(), dto.getSubjectname(),
					dto.getSubjectdate(), dto.getSubjectbook(), dto.getStudentsu());
		}

		tview.pause();

	}

	/**
	 * 강사의 과정별 스케줄 출력 기능
	 * 
	 * @param 강사번호
	 */
	public void course2(TeacherDTO teacher) {

		tview.courseTitle();

		TeacherDAO dao = new TeacherDAO();

		ArrayList<CourseDTO> course2 = dao.course2(teacher);

		for (CourseDTO dto : course2) {
			System.out.printf("\t\t\t\t\t\t\t\t\t%s\t\t%s\t\t%s\t%s\n", dto.getNum(), dto.getCoursedate(),
					dto.getCoursename(), dto.getStatus()

			);
		}

		tview.pause();

	}

	/**
	 * 강사 담당 전체 교육생의 출결 정보 출력 기능
	 * 
	 * @param 강사번호
	 */
	public void whole(TeacherDTO teacher) {
		dao = new TeacherDAO();
		tview.wholeTitle();
		TeacherDAO dao = new TeacherDAO();

		ArrayList<WholeDTO> whole = dao.whole(teacher);

		for (WholeDTO dto : whole) {
			System.out.printf("%s\t\t\t%s\t\t\t%s\t\t\t%-15s\t\t\t%s\n", dto.getBeginday(),
					dto.getBegintime(), dto.getEndday(), dto.getName(), dto.getState()

			);
		}

		tview.pause();

	}

	/**
	 * 강사 담당 과목별 출결률 출력 기능
	 */
	public void subjectAttended() {
		dao = new TeacherDAO();
		tview.subjectAttendedTitle();

		ArrayList<SSubjectDTO> subject = dao.subject(dto);

		for (SSubjectDTO dto : subject) {
			System.out.printf("\t\t\t\t\t\t\t\t\t%s\t\t%s\n", dto.getSubject(), dto.getInoutday()

			);

		}

		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t더 이상 진행중인 과목이 없으니 출결률 조회를 종료합니다....");

		tview.pause();
	}

	/**
	 * 일별 교육생 출결조회 출력 기능
	 * 
	 * @param 강사번호
	 */
	public void day(TeacherDTO teacher) {

		dao = new TeacherDAO();

		System.out.print("\t\t\t\t\t\t\t\t\t년 : ");
		int year = scan.nextInt();
		System.out.print("\t\t\t\t\t\t\t\t\t월 : ");
		int month = scan.nextInt();
		System.out.print("\t\t\t\t\t\t\t\t\t일 : ");
		int day = scan.nextInt();
		tview.dayTitle();

		ArrayList<DayDTO> dayy = dao.dayy(year, month, day, teacher);

		for (DayDTO dto : dayy) {
			System.out.printf("%s\t\t%s\t\t%s\t\t%-15s\t%s\t\t%s\n", dto.getBeginday(), dto.getIntime(),
					dto.getOuttime(), dto.getName(), dto.getStats(), dto.getCourse()

			);
		}

		tview.pause();
	}

	/**
	 * 월별 교육생 출결조회 출력 기능
	 * 
	 * @param 강사번호
	 */
	public void month(TeacherDTO teacher) {

		dao = new TeacherDAO();

		System.out.print("\t\t\t\t\t\t\t\t\t년 : ");
		int year = scan.nextInt();
		System.out.print("\t\t\t\t\t\t\t\t\t월 : ");
		int month = scan.nextInt();
		tview.monthTitle();
		ArrayList<DayDTO> monthh = dao.monthh(year, month, teacher);

		for (DayDTO dto : monthh) {
			System.out.printf("%s\t\t%s\t\t%s\t\t%-15s\t%s\t\t%s\n", dto.getBeginday(), dto.getIntime(),
					dto.getOuttime(), dto.getName(), dto.getStats(), dto.getCourse()

			);
		}

		tview.pause();

	}

	/**
	 * 년도별 교육생 출결조회 출력 기능
	 * 
	 * @param 강사번호
	 */
	public void year(TeacherDTO teacher) {

		dao = new TeacherDAO();

		System.out.print("\t\t\t\t\t\t\t\t\t년 : ");
		int year = scan.nextInt();

		tview.yearTitle();

		ArrayList<DayDTO> yearr = dao.yearr(year, teacher);

		for (DayDTO dto : yearr) {
			System.out.printf("%s\t\t%s\t\t%s\t\t%-15s\t%s\t\t%s\n", dto.getBeginday(), dto.getIntime(),
					dto.getOuttime(), dto.getName(), dto.getStats(), dto.getCourse()

			);
		}

		tview.pause();
	}

	/**
	 * 출석 조회 메뉴 생성 기능
	 */
	public void attended(TeacherDTO teacher) {

		boolean loop = true;

		while (loop) {

			tsview.attended();

			String sel = scan.nextLine();

			if (sel.equals("1"))
				whole(teacher);
			else if (sel.equals("2"))
				period(teacher);
			else if (sel.equals("3"))
				subjectAttended();
			else if (sel.equals("0"))
				loop = false;
			else
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}

	}
	/**
	 * 강사 스케줄 조회 메뉴 생성 기능
	 */
	public void lessonSchedule(TeacherDTO teacher) {
		boolean loop = true;

		while (loop) {

			tsview.lessonSchedule();

			String sel = scan.nextLine();

			if (sel.equals("1"))
				schedule(teacher);

			else if (sel.equals("2"))
				course2(teacher);
			else if (sel.equals("0"))
				loop = false;
			else
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
	}
	/**
	 * 기간별 출결 조회 메뉴 생성 기능 
	 * @param 강사번호
	 */
	public void period(TeacherDTO teacher) {

		boolean loop = true;

		while (loop) {

			tsview.period();

			String sel = scan.nextLine();

			if (sel.equals("1"))
				day(teacher);
			else if (sel.equals("2"))
				month(teacher);
			else if (sel.equals("3"))
				year(teacher);
			else if (sel.equals("0"))
				loop = false;
			else
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
	}

}
