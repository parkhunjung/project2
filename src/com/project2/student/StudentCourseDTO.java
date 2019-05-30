package com.project2.student;

/**
 * 교육생의 수강과정정보 객체 클래스 + 자신의 수강번호
 * @author 유병현
 *
 */
public class StudentCourseDTO {
	private String seq;
	private String title;
	private String status;
	private String begindate;
	private String enddate;
	private String room;
	private String totalPeople;
	private String learnseq;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBegindate() {
		return begindate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}

	public String getTotalPeople() {
		return totalPeople;
	}
	public void setTotalPeople(String totalPeople) {
		this.totalPeople = totalPeople;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getLearnseq() {
		return learnseq;
	}
	public void setLearnseq(String learnseq) {
		this.learnseq = learnseq;
	}
	
	
	
	
}
