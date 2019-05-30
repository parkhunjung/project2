package com.project2.teacher;

import java.util.Scanner;

import com.project2.main.MainView;

/**
 * 강사 - 사용자 인터페이스 클래스
 * 
 * @author sist56
 *
 */
public class TeacherView {

	public final static int LESSON = 1;
	public final static int GRADE = 2;
	public final static int ATTENDED = 3;

	private MainView mview;

	public TeacherView() {

		mview = new MainView();

	}

	public void login() {
		System.out.println("\t\t\t\t\t\t\t\t\t[강사 로그인 화면]");
	}

	public void loginSucess() {
		System.out.println("\t\t\t\t\t\t\t\t\t로그인 성공이야 !!!");
	}

	public void menu() {

		System.out.println(
				"=======================================================================================================================================================================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(

				"\t\t\t\t\t\t\t\t████████╗███████╗ █████╗  ██████╗██╗  ██╗███████╗██████╗\r\n"
						+ "\t\t\t\t\t\t\t\t╚══██╔══╝██╔════╝██╔══██╗██╔════╝██║  ██║██╔════╝██╔══██╗\r\n"
						+ "\t\t\t\t\t\t\t\t   ██║   █████╗  ███████║██║     ███████║█████╗  ██████╔╝\r\n"
						+ "\t\t\t\t\t\t\t\t   ██║   ██╔══╝  ██╔══██║██║     ██╔══██║██╔══╝  ██╔══██╗\r\n"
						+ "\t\t\t\t\t\t\t\t   ██║   ███████╗██║  ██║╚██████╗██║  ██║███████╗██║  ██║\r\n"
						+ " \t\t\t\t\t\t\t\t  ╚═╝   ╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\r\n"
						+ "													"

		);
		System.out.println("\t\t\t\t\t\t\t\t\t\t[강사 메뉴]");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 강의스케줄 조회");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 성적 조회");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 교육생 출결조회");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 종료");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(
				"=======================================================================================================================================================================================");
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t선택: : ");

	}

	public void scoremenu() {
		System.out.println("\t\t\t\t\t\t\t\t\t============================");
		System.out.println("\t\t\t\t\t\t\t\t\t[성적 및 배점 조회]");
		System.out.println("\t\t\t\t\t\t\t\t\t1. 배점 조회");
		System.out.println("\t\t\t\t\t\t\t\t\t2. 가답안 조회");
		System.out.println("\t\t\t\t\t\t\t\t\t3. 맞힌 문제 개수 조회");
		System.out.println("\t\t\t\t\t\t\t\t\t4. 최종 변환 점수 조회");
		System.out.println("\t\t\t\t\t\t\t\t\t0. 뒤로가기");
		System.out.println("\t\t\t\t\t\t\t\t\t============================");
		System.out.print("\t\t\t\t\t\t\t\t\t선택:  ");

	}

	public void pilgiORsilgmenu() {
		System.out.println("\t\t\t\t\t\t\t\t\t1. 필기 조회");
		System.out.println("\t\t\t\t\t\t\t\t\t2. 실기 조회");
		System.out.println("\t\t\t\t\t\t\t\t\t0. 뒤로가기");
		System.out.println("\t\t\t\t\t\t\t\t\t============================");
		System.out.print("\t\t\t\t\t\t\t\t\t선택:  ");

	}

	public void title(int n) {

		if (n == TeacherView.LESSON) {
			System.out.println("\t\t\t\t\t\t\t\t[강의스케줄 조회]");
		} else if (n == TeacherView.GRADE) {
			System.out.println("\t\t\t\t\t\t\t\t[성적 조회]");
		} else if (n == TeacherView.ATTENDED) {
			System.out.println("\t\t\t\t\t\t\t\t[교육생 출결조회]");
		}

	}

	public void courseTitle() {
		System.out.println("\t\t\t\t\t\t\t\t\t[과정별 조회]");
		System.out.println("\t\t\t\t\t\t\t\t\t[과정번호]\t[과정기간]\t\t\t[과정명]\t\t\t\t"
				+ "[상태]");

	}

	public void subjectTitle() {
		System.out.println("\t\t\t\t\t\t\t\t\t[과목별 조회]");
		System.out.println("\t\t\t\t\t\t\t\t\t[과목번호]\t[과목명]\t\t\t[과목기간]\t\t\t\t[과목설명]");

	}

	public void gradeTitle() {
		System.out.println("\t\t\t\t\t\t\t\t\t[성적 조회]");
		System.out.println("\t\t\t\t\t\t\t\t\t[번호]\t[교육생]\t\t[과목명]\t\t\t[출석점수]\t[필기점수]\t[실기점수]\t[총점수]");

	}

	public void numberanswerTitle() {

		System.out.println("\t\t\t\t\t\t\t\t\t[맞힌 개수 조회]");
		System.out.println("\t\t\t\t\t\t\t\t\t[번호]\t[교육생]\t\t[과목명]\t[필기]\t[실기]\t[출석]");
	}

	public void pause() {
		mview.pause();
	}

	public void pauseBack() {
		mview.pauseBack();
	}

	public void perScore() {
		System.out.println("\t\t\t\t\t\t\t\t\t[배점 조회]");
		System.out.println("[과목번호]\t[과목명]\t\t[결석]\t[지각]\t[외출]\t[조퇴]\t[병가]\t[기타]\t[출근]\t[출석배점]" + "  "
				+ "[필기배점]" + "  " + "[실기배점]\n");

	}

	public void answerJohae() {

		System.out.println("\t\t\t\t\t\t\t\t\t[가답안 조회]");
		System.out.println("\t\t\t\t\t\t\t[문제번호]\t[문제]\t\t\t\t\t\t\t\t\t\t\t\t[문제답안]");
	}

	public void scheduleTitle() {

		System.out.println("\t\t\t\t\t\t\t\t\t[스케줄 조회]");
		System.out.println(
				"[과목번호]\t[과정명]\t\t\t\t\t[과정기간]\t\t\t[강의실]\t[과목명]\t\t[과목기간]\t\t\t[교재명]\t\t\t[학생수]");

	}

	public void wholeTitle() {
		System.out.println("\t\t\t\t\t\t\t\t[담당교육생 전체 출결조회]");
		System.out.println("\t\t\t\t\t\t\t\t[출근날짜]\t\t\t[출근시간]\t\t\t[퇴근시간]\t\t\t[이름]\t\t\t[상태]");

	}

	public void subjectAttendedTitle() {
		System.out.println("\t\t\t\t\t\t\t\t\t[과목별 출결률 조회]");
		System.out.println("\t\t\t\t\t\t\t\t\t[과목명]\t[출결률]");

	}

	public void dayTitle() {
		System.out.println("\t\t\t\t\t\t\t\t\t[일별 조회]");
		System.out.println("[출근날짜]\t\t[출근시간]\t[퇴근시간]\t[이름]\t\t\t[상태]\t\t[과정명]");

	}

	public void monthTitle() {
		System.out.println("\t\t\t\t\t\t\t\t\t[월별 조회]");
		System.out.println("[출근날짜]\t\t[출근시간]\t[퇴근시간]\t[이름]\t\t\t[상태]\t\t[과정명]");

	}

	public void yearTitle() {
		System.out.println("\t\t\t\t\t\t\t\t\t[년별 조회]");
		System.out.println("[출근날짜]\t\t[출근시간]\t[퇴근시간]\t[이름]\t\t\t[상태]\t\t[과정명]");
	}

}
