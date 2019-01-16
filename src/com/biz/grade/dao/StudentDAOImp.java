package com.biz.grade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.util.DBConnection;
import com.biz.grade.util.DBContract;
import com.biz.grade.vo.StudentVO;

public class StudentDAOImp implements StudentDAO {

	Connection dbConn;
	
	//util패키지에 있는 DBConnection의 connection을 가져다가 초기화 시켜줘서 한번에 사용
	public StudentDAOImp() {
		dbConn = DBConnection.getInstance();
	}

	@Override
	public int insert(StudentVO vo) {
		// TODO 학생정보 추가
		String sql = String.format(" INSERT INTO %s ", DBContract.TABLE_STUDENT);
		sql += " VALUES (?,?,?,?)";
		
		PreparedStatement ps;
		
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, vo.getSt_num());
			ps.setString(2, vo.getSt_name());
			ps.setString(3, vo.getSt_tel());
			ps.setString(4, vo.getSt_addr());
			
			int ret = ps.executeUpdate();
			
			return ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<StudentVO> selectAll() {
		// TODO 전체 학생의 데이터 조회하여 List로 리턴
		String sql = String.format(" SELECT * FROM %s", DBContract.TABLE_STUDENT);
		
		PreparedStatement ps;
		List<StudentVO> stdList = new ArrayList();
		
		try {
			ps = dbConn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				StudentVO vo = new StudentVO(
						rs.getString(DBContract.ST_COLUMN.ST_NUM),
						rs.getString(DBContract.ST_COLUMN.ST_NAME),
						rs.getString(DBContract.ST_COLUMN.ST_TEL),
						rs.getString(DBContract.ST_COLUMN.ST_ADDR)
						);
				stdList.add(vo);
			}
			rs.close();
			ps.close();
			
			return stdList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StudentVO findByNum(String st_num) {
		// TODO 학번으로 조회하여 VO를 리턴
		String sql = String.format(" SELECT * FROM %s ", DBContract.TABLE_STUDENT);
		sql += String.format(" WHERE %s = ? ", DBContract.ST_COLUMN.ST_NUM);
		
		PreparedStatement ps;
		
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(st_num));
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				StudentVO vo = new StudentVO(
						rs.getString(DBContract.ST_COLUMN.ST_NUM),
						rs.getString(DBContract.ST_COLUMN.ST_NAME),
						rs.getString(DBContract.ST_COLUMN.ST_TEL),
						rs.getString(DBContract.ST_COLUMN.ST_ADDR)
						);
				
				return vo;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public StudentVO findByNum01(String st_num) {
		// TODO 학번으로 조회하여 VO를 리턴
		String sql = String.format(" SELECT * FROM %s ", DBContract.TABLE_STUDENT);
		sql += String.format(" WHERE %s = ? ", DBContract.ST_COLUMN.ST_NUM);
		
		PreparedStatement ps;
		
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(st_num));
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				StudentVO vo = new StudentVO(
						rs.getString("st_num"),
						rs.getString("st_name"),
						rs.getString("st_tel"),
						rs.getString("st_addr")
						);
				
				return vo;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<StudentVO> findByName(String st_name) {
		// TODO 이름으로 조회하여 List로 리턴
		String sql = String.format(" SELECT * FROM %s ", DBContract.TABLE_STUDENT);
		sql += String.format(" WHERE %s LIKE ? ", DBContract.ST_COLUMN.ST_NAME);
		
		/*
		 * SQL의 LIKE 키워드를 사용해서 SQL을 작성하려면
		 * sql += sql += " WHERE st_name LIKE '%" + st_name + "%' ";
		 */
		
		PreparedStatement ps;
		
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, "%" + st_name + "%");
			ResultSet rs = ps.executeQuery();
			
			List<StudentVO> stdList = new ArrayList();
			while(rs.next()) {
				StudentVO vo = new StudentVO(
						rs.getString(DBContract.ST_COLUMN.ST_NUM),
						rs.getString(DBContract.ST_COLUMN.ST_NAME),
						rs.getString(DBContract.ST_COLUMN.ST_TEL),
						rs.getString(DBContract.ST_COLUMN.ST_ADDR)
						);
				stdList.add(vo);
			}
			return stdList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int Update(StudentVO vo) {
		// TODO Auto-generated method stub
		String sql = String.format(" UPDATE %s ", DBContract.TABLE_STUDENT);
		sql += " SET ";
		sql += String.format(" %s = ?, ", DBContract.ST_COLUMN.ST_NAME);
		sql += String.format(" %s = ?, ", DBContract.ST_COLUMN.ST_TEL);
		sql += String.format(" %s = ? ", DBContract.ST_COLUMN.ST_ADDR);
		sql += String.format(" WHERE %s = ?, ", DBContract.ST_COLUMN.ST_NUM);
		
		PreparedStatement ps ;
		
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, vo.getSt_name());
			ps.setString(2, vo.getSt_name());
			ps.setString(3, vo.getSt_name());
			ps.setInt(4, Integer.valueOf(vo.getSt_num()));
			
			int ret = ps.executeUpdate();
			
			return ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(String st_num) {
		// TODO 학생정보 삭제
		String sql = String.format(" DELETE FROM %s ", DBContract.TABLE_STUDENT);
		sql += String.format(" WHERE %s = ? ", DBContract.ST_COLUMN.ST_NUM);
		
		PreparedStatement ps;
		
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(st_num));
			int ret = ps.executeUpdate();
			
			return ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public void insertBulk(List<StudentVO> stdList) {
		// TODO Sevice에서 생선한 stdList를 매개변수로 받아 DB에  insertBulk하는 메서드
		
		String sql = " INSERT INTO tbl_student ";
		sql += " VALUES(?,?,?,?) ";
		
		PreparedStatement ps;
		
		try {
			ps = dbConn.prepareStatement(sql);
/*			for(StudentVO v : stdList) {
				ps.setString(1, v.getSt_num());
				ps.setString(2, v.getSt_name());
				ps.setString(3, v.getSt_tel());
				ps.setString(4, v.getSt_addr());
				
				ps.executeUpdate(); // 파일이 많아질수록 느려지고 오류날 가능성이 높아짐
			}
*/
			/*
			 * 벌크데이터를 insert할때는 1개의 데이터를 set 하고 exec...()을 실행하는 것은 매우 비 효율적이다.
			 * JAVA PrepareStatement에는 이처럼 bulk DATA를 insert할 때는 Batch처리를 한다.
			 * 
			 * batch처리란 ? 일괄처리방식
			 */
			
			for(StudentVO vo : stdList) {
				ps.setString(1, vo.getSt_num());
				ps.setString(2, vo.getSt_name());
				ps.setString(3, vo.getSt_tel());
				ps.setString(4, vo.getSt_addr());
				
				ps.addBatch();
			}
			ps.executeBatch(); //한꺼번에 모아서 보냄(update는 매번 하나씩 보냄)
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
