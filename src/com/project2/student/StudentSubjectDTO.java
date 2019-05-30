package com.project2.student;
/**
 * 특정교육생의 특정과정 과목정보 + 특정교육생의 성적 객체 클래스
 * @author 유병현
 *
 */
public class StudentSubjectDTO {
	private String title;
	private String beigndate;
	private String enddate;
	private String attendPoint;
	private String pilPoint;
	private String silPoint;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBeigndate() {
		return beigndate;
	}
	public void setBeigndate(String beigndate) {
		this.beigndate = beigndate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getAttendPoint() {
		return attendPoint;
	}
	public void setAttendPoint(String attendPoint) {
		this.attendPoint = attendPoint;
	}
	public String getPilPoint() {
		return pilPoint;
	}
	public void setPilPoint(String pilPoint) {
		this.pilPoint = pilPoint;
	}
	public String getSilPoint() {
		return silPoint;
	}
	public void setSilPoint(String silPoint) {
		this.silPoint = silPoint;
	}
}
