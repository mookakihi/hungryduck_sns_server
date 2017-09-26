package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.sql.DataSource;



import dto.BBSList_DTO;
import dto.BBS_DTO;

public class BBSListDAO {
	Connection conn=null;
	BBSList_DTO bbsList=new BBSList_DTO();
	public BBSList_DTO getBBSList() {

		try {
			Context initCtx = new InitialContext();
			Context envCtx  = (Context)initCtx.lookup("java:comp/env");
			DataSource ds   = (DataSource)envCtx.lookup("jdbc/java");
			conn=ds.getConnection();
			System.out.println("커넥션 풀로부터 커넥션 객체 획득");			
			// 쿼리수행			
			String sql 		= "select * from sns_bbs;";
			PreparedStatement ps =  conn.prepareStatement(sql);			
			ResultSet rs    = ps.executeQuery();
			// Model
			ArrayList<BBS_DTO> boards = new ArrayList<BBS_DTO>();
			BBS_DTO boardBean = null;
			while( rs.next() ){
				System.out.println(1);
				boardBean = new BBS_DTO();	
				boardBean.setBbs_no( 		rs.getInt("bbs_no") );
				boardBean.setUser_id(		rs.getString("user_id"));
				boardBean.setBbs_date(	rs.getTimestamp("bbs_date"));
				boardBean.setBbs_title(rs.getString("bbs_title"));
				boardBean.setBbs_content(	rs.getString("bbs_content"));
				boardBean.setBbs_read(rs.getInt("bbs_read"));
				boardBean.setBbs_location(rs.getString("bbs_location"));
				boards.add(boardBean);
			}
			
			bbsList.setCodenum(1);
			bbsList.setBbsList(boards);
			rs.close();
			ps.close();
		
			conn.close();
			for(BBS_DTO b : boards) {
				System.out.println(b.getBbs_content());
			}
		}catch(Exception e) {
			System.out.println("Error!"+e.getMessage());
		}
	return bbsList;
}
	
	
	
	public String BbsPost_DAO(BBS_DTO bbs_dto) {
		String result=null;
		// 상위 command에서 줄 것이다 Gson> BBS_DTO
		try {
			Context initCtx = new InitialContext();
			Context envCtx  = (Context)initCtx.lookup("java:comp/env");
			DataSource ds   = (DataSource)envCtx.lookup("jdbc/java");
			conn		    = ds.getConnection();
			System.out.println("커넥션 풀로부터 커넥션 객체 획득");			
			
			
			// 쿼리수행			
			String sql 		= "INSERT INTO sns_bbs (user_id,bbs_date,"
					+ "bbs_title,bbs_content,bbs_location) VALUES(?,?,?,?,?);";
			PreparedStatement ps =  conn.prepareStatement(sql);	
			ps.setString(1,bbs_dto.getUser_id());
			ps.setTimestamp(2, bbs_dto.getBbs_date());
			ps.setString(3,bbs_dto.getBbs_title());
			ps.setString(4, bbs_dto.getBbs_content());
			ps.setString(5, bbs_dto.getBbs_location());
			int isInsert = ps.executeUpdate();
			
			if(isInsert!=0) {
				result= "{result:"+"'success'}";
			}
			ps.close();
			// 반납
			conn.close();
		}catch(Exception e) {
			System.out.println("게시글 등록 오류! 내용을 확인하세요 .."+e.getMessage());
		}
		
		return result;
		
	}
}