package banking7.jdbc;

public class InsertSQL extends IConnectImpl {

	String accNum,name;
	int balance;
	
	public InsertSQL() {
		super(ORACLE_DRIVER, "education", "1234");
	}
	
	
	public InsertSQL(String accNum, String name, int balance) {
		this.accNum = accNum;
		this.name = name;
		this.balance = balance;
	}
	
	@Override
	public void execute() {
		
		try {
			String query = "INSERT INTO banking_tb VALUES ( ?, ?, ?, ? )";
			
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, accNum);
			psmt.setString(2, name);
			psmt.setLong(3, balance);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
