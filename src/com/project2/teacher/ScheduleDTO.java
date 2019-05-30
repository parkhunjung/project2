package com.project2.teacher;

/**
 * 강사 - 담당 스케줄 객체 클래스
 * 
 * @author sist56
 *
 */
public class ScheduleDTO {
	private String seq;
	private String coursetitle;
	private String coursedate;
	private String roomname;
	private String subjectname;
	private String subjectdate;
	private String subjectbook;
	private String studentsu;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getCoursetitle() {
		return coursetitle;
	}

	public void setCoursetitle(String coursetitle) {
		this.coursetitle = coursetitle;
	}

	public String getCoursedate() {
		return coursedate;
	}

	public void setCoursedate(String coursedate) {
		this.coursedate = coursedate;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getSubjectdate() {
		return subjectdate;
	}

	public void setSubjectdate(String subjectdate) {
		this.subjectdate = subjectdate;
	}

	public String getSubjectbook() {
		return subjectbook;
	}

	public void setSubjectbook(String subjectbook) {
		this.subjectbook = subjectbook;
	}

	public String getStudentsu() {
		return studentsu;
	}

	public void setStudentsu(String studentsu) {
		this.studentsu = studentsu;
	}

}
