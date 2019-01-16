package com.biz.grade.exec;

import com.biz.grade.service.GradeService;

public class GradeExec {

	public static void main(String[] args) {
		String strF = "src/com/biz/grade/학생리스트.txt";
		GradeService gs = new GradeService(strF);
		
		gs.gradeMenu();
	}
}
