package com.project2.main;

/**
 * 메인 - 강사 객체 클래스
 * @author sist57
 *
 */
public class TeacherDTO {

   private String seq;
   private String name;
   private String ssn;
   private String tel;
   private String address;
   private String regdate;
   private String delete_status;
   private String coursename;
   private String courseperiod;


   public String getSeq() {
      return seq;
   }

   public void setSeq(String seq) {
      this.seq = seq;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSsn() {
      return ssn;
   }



   public void setSsn(String ssn) {
      this.ssn = ssn;
   }

   public String getTel() {
      return tel;
   }

   public void setTel(String tel) {
      this.tel = tel;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getRegdate() {
      return regdate;
   }

   public void setRegdate(String regdate) {
      this.regdate = regdate;
   }

   public String getDelete_status() {
      return delete_status;
   }

   public void setDelete_status(String delete_status) {
      this.delete_status = delete_status;
   }


   public String gettPassword() {
      return this.ssn.substring(7);
   }

   public String getCourseperiod() {
      return courseperiod;
   }

   public void setCourseperiod(String courseperiod) {
      this.courseperiod = courseperiod;
   }

   public String getCoursename() {
      return coursename;
   }

   public void setCoursename(String coursename) {
      this.coursename = coursename;
   }

   
}