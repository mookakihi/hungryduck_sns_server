package asdf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.BBSList_DTO;
import dto.BBS_DTO;

public class asdf {
		public static void main(String[] args) {
		Connection conn=null;
		BBSList_DTO bbsList=new BBSList_DTO();
	
		try {
			//Context initCtx = new InitialContext();
			//Context envCtx  = (Context)initCtx.lookup("java:comp/env");
			//DataSource ds   = (DataSource)envCtx.lookup("jdbc/java");
			String url = "jdbc:mysql://localhost:3306/SNSDB";        
			String id = "root";                                                
			String pw = "1234";         
			Class.forName("com.mysql.jdbc.Driver"); 
			conn=DriverManager.getConnection(url,id,pw);
			System.out.println("커넥션 풀로부터 커넥션 객체 획득");			
			// 쿼리수행			
			String sql 		= "select * from sns_bbs;";
			PreparedStatement ps =  conn.prepareStatement(sql);			
			ResultSet rs    = ps.executeQuery();
			// Model
			ArrayList<BBS_DTO> boards = new ArrayList<BBS_DTO>();
			BBS_DTO boardBean = null;
			while( rs.next() ){
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

		
}
}
