package com.biz.grade.exec;

import com.biz.grade.service.StudentService;

public class StudentExec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String strF = "src/com/biz/grade/학생리스트.txt";
		
		StudentService ss = new StudentService(strF);
		
		ss.stdMenu();
	}

}
