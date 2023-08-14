package banking7.jdbc;

import java.sql.SQLException;

public class selectSQL extends IConnectImpl {

	@Override
	public void execute() {
		try {
			stmt = con.createStatement();
			
			String query = "SELECT seq_num, account_id, customer_name, balance "
					+ " FROM banking_tb";
			
			rs = stmt.executeQuery(query);
			System.out.printf("%s %s %s %s\n",
					"번호","계좌번호","이름","잔액");
			while(rs.next()) {
				String seq_num = rs.getString(1);
				String account_id = rs.getString(2);
				String customer_name = rs.getString(3);
				String balance = rs.getString(4);
				
				
				System.out.printf("%s %s %s %s\n",
						seq_num,account_id,customer_name,balance);
			}
		} 
		catch (SQLException e) {
			System.out.println("쿼리오류발생");
			e.printStackTrace();
		}
		finally {
			close();//DB자원반납
		}
	}
	
}
