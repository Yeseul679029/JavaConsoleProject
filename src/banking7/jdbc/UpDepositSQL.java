package banking7.jdbc;

import java.sql.SQLException;

public class UpDepositSQL extends IConnectImpl{

	@Override
	public void execute() {

		//update 쿼리문 생성
		String sql = "UPDATE banking_tb "
				+ " SET  balance = balance + ? "
				+ " WHERE account_id =  ? ";
		try {
			//prepared객체 생성
			psmt = con.prepareStatement(sql);
			
				psmt.setString(2, scanValue("계좌번호를 입력해주세요."));
//				psmt.setString(1, scanValue("이름"));
				psmt.setString(1, scanValue("입금액"));
				
				//쿼리문 실행 및 결과확인
				int affected = psmt.executeUpdate();
				System.out.println(affected +"행이 업데이트 되었습니다.");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
}
