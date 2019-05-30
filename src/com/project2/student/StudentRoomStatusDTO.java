package com.project2.student;
/**
 * 스터디의 강의실예약내역 객체 클래스
 * @author 유병현
 *
 */
public class StudentRoomStatusDTO {

	private String seq;
	private String roomseq;
	private String studyseq;
	private String roomtitle;
	private String studytitle;
	private String reservedate;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getRoomseq() {
		return roomseq;
	}
	public void setRoomseq(String roomseq) {
		this.roomseq = roomseq;
	}
	public String getStudyseq() {
		return studyseq;
	}
	public void setStudyseq(String studyseq) {
		this.studyseq = studyseq;
	}
	public String getRoomtitle() {
		return roomtitle;
	}
	public void setRoomtitle(String roomtitle) {
		this.roomtitle = roomtitle;
	}
	public String getStudytitle() {
		return studytitle;
	}
	public void setStudytitle(String studytitle) {
		this.studytitle = studytitle;
	}
	public String getReservedate() {
		return reservedate;
	}
	public void setReservedate(String reservedate) {
		this.reservedate = reservedate;
	}
}
