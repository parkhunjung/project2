package com.project2.teacher;

import com.project2.main.TeacherDTO;

public interface ITeacherService {

	void numberOfanswer(String sel, String choice1);

	void attended(TeacherDTO teacher);

	void lessonSchedule(TeacherDTO teacher);

	TeacherDTO login();

	void answerJohae(String sel, String check1);

	void perscore(String sel, String subject);

	void course(TeacherDTO teacher);

	void course2(TeacherDTO teacher);

	void choosepilgi(String sel, String choice1);

	void answerSilgiJohae(String sel, String choice1);

}
