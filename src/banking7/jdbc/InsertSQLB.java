package banking7.jdbc;

public class InsertSQLB extends IConnectImpl {

	String accNum,name;
	int balance;
	
	
	public InsertSQLB(String accNum, String name, int balance) {
		this.accNum = accNum;
		this.name = name;
		this.balance = balance;
	}
	
	@Override
	public void execute() {
		
		try {
			String query = "INSERT INTO banking_tb VALUES ( ?, ?, ? )";
			
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, accNum);
			psmt.setString(2, name);
			psmt.setLong(3, balance);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
