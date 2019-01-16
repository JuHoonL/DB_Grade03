package com.biz.grade.service;

import java.util.Scanner;

import com.biz.grade.dao.ScoreDAO;
import com.biz.grade.dao.ScoreDAOImp;
import com.biz.grade.dao.StudentDAO;
import com.biz.grade.dao.StudentDAOImp;
import com.biz.grade.vo.ScoreVO;
import com.biz.grade.vo.StudentVO;

public class ScoreService {

	Scanner scan;
	
	//학생정보를 조회하기 위한 StudentDAO를 선언한다.
	StudentDAO stdDao;
	
	//점수정보를 조회하기 위한 ScoreDAO를 선언한다.
	ScoreDAO scDao;
	
	public ScoreService() {
		scan = new Scanner(System.in);
		stdDao = new StudentDAOImp();
		scDao = new ScoreDAOImp();
	}
	
	public void scoreMenu() {
		
		while(true) {
			System.out.println("===================================================");
			System.out.println("성적정보 입력");
			System.out.println("---------------------------------------------------");
			System.out.println("1. 입력     2. 수정     3. 삭제     0. 종료");
			System.out.print(">> ");
			
			String strM = scan.nextLine();
			int intM = Integer.valueOf(strM);
			
			if(intM == 0) return;
			if(intM == 1) this.insertScore();
			if(intM == 2) this.updateScore();
			if(intM == 3) this.deleteScore();
		}
	}
	

	/*
	 * 입력을 선택하면
	 * 1. 학번을 입력받도록 하고
	 * 2. 학번에 대한 학생정보를 보여주고
	 * 3. 이미 점수가 입력되어 있으면 점수를 보여주고
	 * 4. 점수를 입력하지 않고 Enter를 누르면 기존 점수를 유지하고
	 * 	  >> 수정 상태로 변경
	 * 5. 점수가 없는 경우에는 
	 * 6. 새로운 점수를 입력받고
	 * 7. 점수를 insert한다.
	 */
	private void insertScore() {
		// TODO 학생점수 입력
		
		System.out.println("===========================================================");
		System.out.print("학번>> ");
		String strNum = scan.nextLine();
		if(strNum.equals("")) {
			System.out.println("점수 입력을 취소합니다.");
			return;
		}
		//학번을 입력했으면 학생정보로 부터 학번에 대한 학생데이터를 조회해서 보여주기
		StudentVO stdVO = stdDao.findByNum(strNum);
		if(stdVO == null) {
			System.out.println("학번이 존재하지 않습니다.");
			return;
		}
		System.out.println("===========================================================");
		System.out.println("학번 : " + stdVO.getSt_num());
		System.out.println("이름 : " + stdVO.getSt_name());
		System.out.println("전화번호 : " + stdVO.getSt_tel());
		System.out.println("주소 : " + stdVO.getSt_addr());
		System.out.println("===========================================================");
		
		//학번을 입력했을 때 만약 학생 점수가 있으면 점수를 보여주기
		ScoreVO scVO = scDao.findByNum(strNum);
		//점수가 있기 때문에 수정으로 변경
		if(scVO != null) {
			System.out.println("===========================================================");
			System.out.println("학번\t국어\t영어\t수학 ");
			System.out.println("-----------------------------------------------------------");
			System.out.print(scVO.getSc_num() + "\t");
			System.out.print(scVO.getSc_kor() + "\t");
			System.out.print(scVO.getSc_eng() + "\t");
			System.out.print(scVO.getSc_math() + "\n");
			System.out.println("===========================================================");
		
			this.updateScore(strNum);
			return;
		} 
		
		// 값을 새로 추가
		ScoreVO vo = inputScore(strNum);
		
		int insertCount = scDao.insert(vo);
		if(insertCount > 0) {
			System.out.println("데이터 추가 성공!!");
		} else {
			System.out.println("데이터 추가 실패!!");
		}
	}
	
	private ScoreVO inputScore(String strNum) {
		System.out.print("국어점수 >>");
		String strKor = scan.nextLine();
		
		System.out.print("영어점수 >>");
		String strEng = scan.nextLine();
		
		System.out.print("수학점수 >>");
		String strMath = scan.nextLine();
		
		int intKor = 0;
		int intEng = 0;
		int intMath = 0;
		
		try {
			intKor = Integer.valueOf(strKor);
			intEng = Integer.valueOf(strEng);
			intMath = Integer.valueOf(strMath);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("점수입력 오류");
			return null;
		}
		
		ScoreVO vo = new ScoreVO();
		
		vo.setSc_num(strNum);
		vo.setSc_kor(intKor);
		vo.setSc_eng(intEng);
		vo.setSc_math(intMath);
		
		return vo;
	}

	
	//menu에서 실행될 updateScore() 메서드
	private void updateScore() {
		// TODO 키보드를 통해서 학번을 입력받고 updateScore()에 학번을 전달하여 실행
		System.out.println("===========================================================");
		System.out.print("학번 >> ");
		String strNum = scan.nextLine();
		updateScore(strNum);
	}
	
	//insertScore()에서 실행될 updateScore()메서드
	private void updateScore(String strNum) {
		// TODO 키보드를 통해서 점수를 입력 받고 Dao.Update를 실행
		ScoreVO vo = inputScore(strNum);
		if( scDao.update(vo) > 0) {
			System.out.println("데이터 업데이트 성공!!!");
		} else {
			System.out.println("데이터 업데이트 실패!!!");
		}
	}
	
	private void deleteScore() {
		// TODO Auto-generated method stub
		
	}
	
}
