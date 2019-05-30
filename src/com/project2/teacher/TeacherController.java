package com.project2.teacher;

import com.project2.main.TeacherDTO;
import java.util.Scanner;

/**
 * 강사 - 강사메인클래스
 * 
 * @author sist56
 *
 */
public class TeacherController {

	private TeacherView view;
	private UserScanner scan;
	private ITeacherService service;

	public TeacherController() {

		view = new TeacherView();
		scan = new UserScanner();
		service = new TeacherService();
	}

	public void subMain() {
		TeacherDTO teacher = service.login();

		boolean loop = true;

		if (teacher != null) {
			while (loop) {

				view.menu();

				String sel = scan.nextLine();

				if (sel.equals("1"))
					service.lessonSchedule(teacher);
				else if (sel.equals("2"))
					service.course(teacher);
				else if (sel.equals("3"))
					service.attended(teacher);
				else if (sel.equals("0"))
					loop = false;
				else
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");

			}
		}

	}

}
