package banking7.jdbc;

import java.util.Scanner;

public class InsertSQL extends IConnectImpl {

	String accNum,name;
	int balance;
	
	public InsertSQL() {
		super(ORACLE_DRIVER, "education", "1234");
	}
	
	
//	public InsertSQL(String accNum, String name, int balance) {
//		this.accNum = accNum;
//		this.name = name;
//		this.balance = balance;
//	}
	

	
	
	@Override
	public void execute() {
		
		try {
			
			String query = "INSERT INTO banking_tb VALUES ( seq_banking.NEXTVAL, ?, ?, ? )";
			
			psmt = con.prepareStatement(query);
			
			Scanner scan = new Scanner(System.in);
			
			System.out.print("계좌번호: ");
			this.accNum = scan.nextLine();
			System.out.print("이름: ");
			this.name = scan.nextLine();
			System.out.print("잔고: ");
			this.balance = scan.nextInt();
			
			
			psmt.setString(1, accNum);
			psmt.setString(2, name);
			psmt.setLong(3, balance);
			
			
			//5.쿼리실행 및 결과값 반환
			int affected = psmt.executeUpdate();
			System.out.println(affected + "행이 입력되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}


}
