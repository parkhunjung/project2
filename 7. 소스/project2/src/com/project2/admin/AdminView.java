package com.project2.admin;

import java.util.Scanner;

import com.project2.main.MainView;

/**
 * 관리자 - 인터페이스 클래스
 * @author sist47
 *
 */
public class AdminView {

   public final static int ADD = 1;
   public final static int EDIT = 2;
   public final static int LIST = 3;
   public final static int SCOREJOHAE = 2;
   public final static int PILGI = 1;
   public final static int SILGI = 2;

   private MainView mview;

   public AdminView() {

      mview = new MainView();

   }

   public void login() {
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[관리자 로그인 화면]");
   }

   public void loginSucess() {
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t로그인 성공이야 !!!");
   }

   public void menu() {
      
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println(
            "\t\t\t\t\t\t\t █████╗  ██████╗ █████╗ ██████╗ ███████╗███╗   ███╗██╗   ██╗\r\n" + 
            "\t\t\t\t\t\t\t██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔════╝████╗ ████║╚██╗ ██╔╝\r\n" + 
            "\t\t\t\t\t\t\t███████║██║     ███████║██║  ██║█████╗  ██╔████╔██║ ╚████╔╝ \r\n" + 
            "\t\t\t\t\t\t\t██╔══██║██║     ██╔══██║██║  ██║██╔══╝  ██║╚██╔╝██║  ╚██╔╝  \r\n" + 
            "\t\t\t\t\t\t\t██║  ██║╚██████╗██║  ██║██████╔╝███████╗██║ ╚═╝ ██║   ██║   \r\n" + 
            "\t\t\t\t\t\t\t╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═════╝ ╚══════╝╚═╝     ╚═╝   ╚═╝   \r\n" + 
            "                                                            "
            );
      System.out.println("\t\t\t\t\t\t\t\t\t\t[관리자 메뉴]");
      System.out.println();
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t1. 강사 계정 관리");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t2. 개설 과정 & 과목 관리");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t3. 교육생 관리");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t4. 출결 관리 및 조회");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t5. 시험 관리 및 성적 조회");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로가기");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.print("\t\t\t\t\t\t\t\t\t선택 : ");
   }

   public void end() {
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[관리자 계정 종료합니다.]");

   }


   public void teachermenu() {
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println(
            "\t\t\t\t\t\t\t █████╗  ██████╗ █████╗ ██████╗ ███████╗███╗   ███╗██╗   ██╗\r\n" + 
            "\t\t\t\t\t\t\t██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔════╝████╗ ████║╚██╗ ██╔╝\r\n" + 
            "\t\t\t\t\t\t\t███████║██║     ███████║██║  ██║█████╗  ██╔████╔██║ ╚████╔╝ \r\n" + 
            "\t\t\t\t\t\t\t██╔══██║██║     ██╔══██║██║  ██║██╔══╝  ██║╚██╔╝██║  ╚██╔╝  \r\n" + 
            "\t\t\t\t\t\t\t██║  ██║╚██████╗██║  ██║██████╔╝███████╗██║ ╚═╝ ██║   ██║   \r\n" + 
            "\t\t\t\t\t\t\t╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═════╝ ╚══════╝╚═╝     ╚═╝   ╚═╝   \r\n" + 
            "                                                            "
            );
      System.out.println("\t\t\t\t\t\t\t\t\t\t[강사 계정 관리]");
      System.out.println();
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t1. 강사 정보 입력");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t2. 강사 정보 수정");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t3. 강사 정보 출력(삭제)");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로가기");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.print("\t\t\t\t\t\t\t\t\t선택 : ");

   }


   public void teachertitle(int n) {

      if (n == AdminView.ADD) {
         System.out.println();
         System.out.println("\t\t\t\t\t\t\t\t\t[강사 정보 입력]");

      } else if (n == AdminView.EDIT) {
         System.out.println();
         System.out.println("\t\t\t\t\t\t\t\t\t[강사 정보 수정]");
         System.out.println("[번호]\t[이름]\t\t\t[전화번호]\t[주소]");//0417헌정수정
      } else if (n == AdminView.LIST) {
         System.out.println();
         System.out.println("\t\t\t\t\t\t\t\t\t[강사 정보 삭제]");
         System.out.println("[번호]\t[이름]\t\t\t[주민등록번호]\t[전화번호]\t[등록일]\t[상태]\t[주소]");
      }
   }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////   

   public void opencourseMenu() {
      
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println(
            "\t\t\t\t\t\t\t █████╗  ██████╗ █████╗ ██████╗ ███████╗███╗   ███╗██╗   ██╗\r\n" + 
            "\t\t\t\t\t\t\t██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔════╝████╗ ████║╚██╗ ██╔╝\r\n" + 
            "\t\t\t\t\t\t\t███████║██║     ███████║██║  ██║█████╗  ██╔████╔██║ ╚████╔╝ \r\n" + 
            "\t\t\t\t\t\t\t██╔══██║██║     ██╔══██║██║  ██║██╔══╝  ██║╚██╔╝██║  ╚██╔╝  \r\n" + 
            "\t\t\t\t\t\t\t██║  ██║╚██████╗██║  ██║██████╔╝███████╗██║ ╚═╝ ██║   ██║   \r\n" + 
            "\t\t\t\t\t\t\t╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═════╝ ╚══════╝╚═╝     ╚═╝   ╚═╝   \r\n" + 
            "                                                            "
            );
      System.out.println("\t\t\t\t\t\t\t\t\t\t[개설 과정 & 과목 관리]");
      System.out.println();
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t1. 개설 과정 입력");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t2. 개설 과정 수정");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t3. 개설 과정 조회");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t4. 개설 과목 입력");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t5. 개설 과목 수정");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t6. 개설 과목 조회");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로가기");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.print("\t\t\t\t\t\t\t\t\t선택 : ");
      

   }

   
   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

   public void studentmng() {
      
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println(
            "\t\t\t\t\t\t\t █████╗  ██████╗ █████╗ ██████╗ ███████╗███╗   ███╗██╗   ██╗\r\n" + 
            "\t\t\t\t\t\t\t██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔════╝████╗ ████║╚██╗ ██╔╝\r\n" + 
            "\t\t\t\t\t\t\t███████║██║     ███████║██║  ██║█████╗  ██╔████╔██║ ╚████╔╝ \r\n" + 
            "\t\t\t\t\t\t\t██╔══██║██║     ██╔══██║██║  ██║██╔══╝  ██║╚██╔╝██║  ╚██╔╝  \r\n" + 
            "\t\t\t\t\t\t\t██║  ██║╚██████╗██║  ██║██████╔╝███████╗██║ ╚═╝ ██║   ██║   \r\n" + 
            "\t\t\t\t\t\t\t╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═════╝ ╚══════╝╚═╝     ╚═╝   ╚═╝   \r\n" + 
            "                                                            "
            );
      System.out.println("\t\t\t\t\t\t\t\t\t\t[교육생 관리 메뉴]");
      System.out.println();
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t1. 교육생 정보 입력");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t2. 교육생 정보 수정");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t3. 교육생 정보 조회");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로가기");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.print("\t\t\t\t\t\t\t\t\t선택 : ");
      

   }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

   public void attendancemng() {
      
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println(
            "\t\t\t\t\t\t\t █████╗  ██████╗ █████╗ ██████╗ ███████╗███╗   ███╗██╗   ██╗\r\n" + 
            "\t\t\t\t\t\t\t██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔════╝████╗ ████║╚██╗ ██╔╝\r\n" + 
            "\t\t\t\t\t\t\t███████║██║     ███████║██║  ██║█████╗  ██╔████╔██║ ╚████╔╝ \r\n" + 
            "\t\t\t\t\t\t\t██╔══██║██║     ██╔══██║██║  ██║██╔══╝  ██║╚██╔╝██║  ╚██╔╝  \r\n" + 
            "\t\t\t\t\t\t\t██║  ██║╚██████╗██║  ██║██████╔╝███████╗██║ ╚═╝ ██║   ██║   \r\n" + 
            "\t\t\t\t\t\t\t╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═════╝ ╚══════╝╚═╝     ╚═╝   ╚═╝   \r\n" + 
            "                                                            "
            );
      System.out.println("\t\t\t\t\t\t\t\t\t\t[출결관리 및 조회 메뉴]");
      System.out.println();
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t1. 교육생 출결 관리");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t2. 교육생 출결 조회");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로가기");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.print("\t\t\t\t\t\t\t\t\t선택 : ");
      
   }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////


   public void attend_inquiry() {
      
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println(
            "\t\t\t\t\t\t\t █████╗  ██████╗ █████╗ ██████╗ ███████╗███╗   ███╗██╗   ██╗\r\n" + 
            "\t\t\t\t\t\t\t██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔════╝████╗ ████║╚██╗ ██╔╝\r\n" + 
            "\t\t\t\t\t\t\t███████║██║     ███████║██║  ██║█████╗  ██╔████╔██║ ╚████╔╝ \r\n" + 
            "\t\t\t\t\t\t\t██╔══██║██║     ██╔══██║██║  ██║██╔══╝  ██║╚██╔╝██║  ╚██╔╝  \r\n" + 
            "\t\t\t\t\t\t\t██║  ██║╚██████╗██║  ██║██████╔╝███████╗██║ ╚═╝ ██║   ██║   \r\n" + 
            "\t\t\t\t\t\t\t╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═════╝ ╚══════╝╚═╝     ╚═╝   ╚═╝   \r\n" + 
            "                                                            "
            );
      System.out.println("\t\t\t\t\t\t\t\t\t\t[교육생 출결 조회]");
      System.out.println();
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t1. 교육생 전체 출결 조회");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t2. 교육생 출결 일별 조회");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t3. 교육생 출결 월별 조회");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t4. 교육생 출결 연도별 조회");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t5. 과정별 출결 조회");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로가기");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.print("\t\t\t\t\t\t\t\t\t선택 : ");
      
   }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////      


   public void testmng_scoreCheckMenu() {
      
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println(
            "\t\t\t\t\t\t\t █████╗  ██████╗ █████╗ ██████╗ ███████╗███╗   ███╗██╗   ██╗\r\n" + 
            "\t\t\t\t\t\t\t██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔════╝████╗ ████║╚██╗ ██╔╝\r\n" + 
            "\t\t\t\t\t\t\t███████║██║     ███████║██║  ██║█████╗  ██╔████╔██║ ╚████╔╝ \r\n" + 
            "\t\t\t\t\t\t\t██╔══██║██║     ██╔══██║██║  ██║██╔══╝  ██║╚██╔╝██║  ╚██╔╝  \r\n" + 
            "\t\t\t\t\t\t\t██║  ██║╚██████╗██║  ██║██████╔╝███████╗██║ ╚═╝ ██║   ██║   \r\n" + 
            "\t\t\t\t\t\t\t╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═════╝ ╚══════╝╚═╝     ╚═╝   ╚═╝   \r\n" + 
            "                                                            "
            );
      System.out.println("\t\t\t\t\t\t\t\t\t\t[관리자의 시험 관리 및 성적 조회]");
      System.out.println();
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t1. 시험 및 성적 등록 여부");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t2. 성적조회하기");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로가기");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.print("\t\t\t\t\t\t\t\t\t선택 : ");
      

   }


   public void scoreLISTMenu() {
      
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println(
            "\t\t\t\t\t\t\t █████╗  ██████╗ █████╗ ██████╗ ███████╗███╗   ███╗██╗   ██╗\r\n" + 
            "\t\t\t\t\t\t\t██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔════╝████╗ ████║╚██╗ ██╔╝\r\n" + 
            "\t\t\t\t\t\t\t███████║██║     ███████║██║  ██║█████╗  ██╔████╔██║ ╚████╔╝ \r\n" + 
            "\t\t\t\t\t\t\t██╔══██║██║     ██╔══██║██║  ██║██╔══╝  ██║╚██╔╝██║  ╚██╔╝  \r\n" + 
            "\t\t\t\t\t\t\t██║  ██║╚██████╗██║  ██║██████╔╝███████╗██║ ╚═╝ ██║   ██║   \r\n" + 
            "\t\t\t\t\t\t\t╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═════╝ ╚══════╝╚═╝     ╚═╝   ╚═╝   \r\n" + 
            "                                                            "
            );
      System.out.println("\t\t\t\t\t\t\t\t\t\t[관리자의 성적 조회]");
      System.out.println();
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t1. 과목별 성적 조회하기");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t2. 교육생별 성적 조회하기");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로가기");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.print("\t\t\t\t\t\t\t\t\t선택 : ");

   }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   // 헌정 추가 => 회원상태
   public void student_editcolumn() {
	  System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      System.out.println("[번호]\t[이름]\t\t\t[주민번호]\t[전화번호]\t[주소지]\t\t\t\t\t\t[상태]"); //(헌정0418수정)
      System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
   }
   public void student_eline() {
	  System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
   }
   
   public void student_columnname() {
      System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      System.out.println("[번호]\t[이름]\t\t\t[주민번호]\t[전화번호]\t[등록일]\t[상태]\t[주소지]"); //(헌정0417수정)
      System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
   }
   
   public void student_cline() {
      System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
   }
   

   public void attend_day() {
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[일별 출결 조회]");
      System.out.println("[출근날짜]\t[출근시간]\t[퇴근시간]\t[이름]\t\t\t\t[상태]\t\t[과정명]"); //(0417 헌정수정)
   }

   public void attend_month() {
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[월별 출결 조회]");
      System.out.println("[출근날짜]\t[출근시간]\t[퇴근시간]\t[이름]\t\t\t\t[상태]\t\t[과정명]"); //(0417 헌정수정)
   }

   public void attend_year() {
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[년도별 출결 조회]");
      System.out.println("[출근날짜]\t[출근시간]\t[퇴근시간]\t[이름]\t\t\t\t[상태]\t\t[과정명]"); //(0417 헌정수정)
   }
   
   public void attend_whole() {
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[교육생 전체 출결 조회]");
      System.out.println("[교육생명]\t[과정명]\t[출근시간]\t[퇴근시간]\t\t[상태]");
   }
   
   
   
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
   public void scoreLISTtitle(int n) {

      if (n == AdminView.ADD) {
         System.out.println();
         System.out.println("\t\t\t\t\t\t\t\t\t[교육생별 성적 조회]");
         System.out.println("[교육생명]\t\t\t[주민번호뒷자리]\t[개설 과정명]\t\t\t\t[개설과정기간]\t\t[강의실명]\t[강사명]\t[개설 과목명]\t\t[개설 과목기간]\t\t\t\t[필기]\t[실기]");
      } else if (n == AdminView.SCOREJOHAE) {
         System.out.println();
         System.out.println("\t\t\t\t\t\t\t\t\t[과목별 성적 조회]");
         System.out.println("[강사명]\t[과정명]\t\t\t\t\t\t\t[과정기간]\t\t[강의실명]\t[과목명]\t[교재명]\t\t\t[교육생이름]\t\t\t[주민번호뒷자리]\t[필기]\t[실기]");
      }

   }

   public void scoreAddmenu() {
      
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println(
            "\t\t\t\t\t\t\t █████╗  ██████╗ █████╗ ██████╗ ███████╗███╗   ███╗██╗   ██╗\r\n" + 
            "\t\t\t\t\t\t\t██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔════╝████╗ ████║╚██╗ ██╔╝\r\n" + 
            "\t\t\t\t\t\t\t███████║██║     ███████║██║  ██║█████╗  ██╔████╔██║ ╚████╔╝ \r\n" + 
            "\t\t\t\t\t\t\t██╔══██║██║     ██╔══██║██║  ██║██╔══╝  ██║╚██╔╝██║  ╚██╔╝  \r\n" + 
            "\t\t\t\t\t\t\t██║  ██║╚██████╗██║  ██║██████╔╝███████╗██║ ╚═╝ ██║   ██║   \r\n" + 
            "\t\t\t\t\t\t\t╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═════╝ ╚══════╝╚═╝     ╚═╝   ╚═╝   \r\n" + 
            "                                                            "
            );
      System.out.println("\t\t\t\t\t\t\t\t\t\t[관리자의 시험 및 성적 등록 여부]");
      System.out.println();
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t1. 필기 등록 여부");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t2. 실기 등록 여부");
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로가기");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("=======================================================================================================================================================================================");
      System.out.println();
      System.out.print("\t\t\t\t\t\t\t\t\t선택 : ");

   }

   public void scoreAddtitle(int n) {

      if (n == AdminView.PILGI) {
         System.out.println();
         System.out.println("\t\t\t\t\t\t\t\t\t[필기 등록 여부]");
         System.out.println("\t\t\t\t\t\t\t\t\t[과목명]\t\t[시험문제 등록 여부]\t[성적 등록 여부]");//0417헌정수정
      } else if (n == AdminView.SILGI) {
         System.out.println();
         System.out.println("\t\t\t\t\t\t\t\t\t[실기 등록 여부]");
         System.out.println("\t\t\t\t\t\t\t\t\t[과목명]\t\t[시험문제 등록 여부]\t[성적 등록 여부]");//0417헌정수정
      }

   }

   public void pause() {
      mview.pause();
   }

   public void pauseBack() {
      mview.pauseBack();
   }

   public void attend_management() {
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[관리자 출결관리]");
      
   }

   public void attend_subject() {
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[과목별 출결조회]");
   }

   public void attend_subjectsub() {
      System.out.println();
      System.out.println("[교육생명]\t\t\t[상태]\t[출석시간]\t\t\t[과정번호]\t[과정명]\t\t\t\t\t[과정기간]");//헌정0417수정
   }
   
   ///예찬's 뷰
   public void emptyroomtitle() {/* 예찬 */
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[비어있는 강의실 정보입니다.]");
      System.out.println("\t\t\t\t\t\t\t\t[번호]\t[비어있는 강의실명]\t[강의실 정원]");
   }

   public void subjectList() {/* 예찬 */
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[등록 가능한 과목 정보입니다.]");
      System.out.println("\t\t\t\t\t\t\t\t\t\t[번호]\t[과목명]");
   }

   public void teacherList() {/* 예찬 */
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[해당 과목 가능한 강사 정보입니다.]");
      System.out.println("\t\t\t\t\t\t\t\t[번호]\t[강사명]\t[나이]\t[등록일]");

   }

   public void bookList() {/* 예찬 */
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[해당 과목 교재 정보입니다.]");
      System.out.println("\t\t\t\t\t\t\t\t[번호]\t[교재명]\t[출판사]");

   }

   public void registCourseSuccess(int courseResult) {/* 예찬 */

      if (courseResult == 1) {
         System.out.println("\t\t\t\t\t\t\t\t\t[과정 정보 등록 완료]");
      } else {
         System.out.println("\t\t\t\t\t\t\t\t\t[과정 정보 등록 실패]");
      }

   }

   public void registCourseSubjectSuccess(int courseSubjectResult, int i) {/* 예찬 */

      if (courseSubjectResult == 1) {
         System.out.printf("\t\t\t\t\t\t\t\t\t[%d과목 정보 등록 완료]\n", i + 1);
      } else {
         System.out.printf("\t\t\t\t\t\t\t\t\t[%d과목 정보 등록 실패]\n", i + 1);
      }

   }

   public void courseList() {/* 예찬 */
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[전체 과정 정보입니다.]");
      System.out.println("[과정번호]\t[과정명]\t\t\t\t\t\t[시작일]\t\t[종료일]\t\t[강의실명]\t[상태]\t[비고]");

   }

   public void studentList() {/* 예찬 */
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[해당 과정의 교육생 정보]");
      System.out.println("[교육생번호]\t[교육생명]\t\t[주민등록번호]\t\t[전화번호]\t\t[주소]");
   }

   public void soloStudent() {
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t[특정 교육생의 정보]");
      System.out.println("[교육생이름]\t\t\t[주민등록번호]\t\t[전화번호]\t\t[주소]\t\t\t\t\t\t[수강중인과정]\t\t\t\t\t[과정기간]\t\t[강의실명]\t[수료및중도탈락여부]\t[수료및중도탈락날짜]\t[상태]");
   }

   public void attSearch() {
      System.out.println();
      System.out.println("\t\t\t\t\t\t\t\t\t특정 교육생의 출결정보");
      System.out.println("[교육생명]\t\t\t[출근시간]\t\t[퇴근시간]\t\t[상태]\t[과정명]");
      
   }//없어서 추가한 뷰(헌정0417수정)

	public void courseList2() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t[전체 과정 정보입니다.]");
		System.out.println("[과정번호]\t[과정명]\t\t\t\t\t\t[시작일]\t\t[종료일]\t\t[강의실명]\t[상태]");

	}
   
}












