package banking6;

import java.io.Serializable;
import java.util.Scanner;

//계좌정보를 표현한 클래스로 NormalAccount, HighCreditAccount의 부모클래스가 된다. 

abstract class Account implements Serializable{

	@Override
	abstract public String toString() ;

	private String accountNumber; //계좌번호
	private String name; //이름
	private int balance; //잔액

	//생성자
	public Account(String accountNumber, String name, int balance) {
		super();
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
	}
	//getter , setter	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public int setBalance(int balance) {
		this.balance = balance;
		return balance;
	}
	
	//해쉬코드로 계좌번호를 비교
	@Override
	public int hashCode() {
		int retCode1 = this.accountNumber.hashCode();
		return retCode1;
	}
	
	//equals로 계좌번호가 같은지 구분
	@Override
	public boolean equals(Object obj) {
		Account ac = (Account)obj;
		//계좌번호가 같다면 true를 반환
		if(ac.accountNumber.equals(this.accountNumber)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	//입금처리
	public void deposit(int money) {
		balance += money;
	}
	//출금처리
	public void withdraw(int money) {
		//잔고가 출금금액보다 클때 출금한다.
		if(balance>= money) {
			balance -= money;
		}
		else {
			//System.out.println("잔고부족");
		}
	}
	//전액출금처리
	public void allWithdraw(int money) {
		
		Scanner scan = new Scanner(System.in);
		//잔고가 출금금액보다 적을때 전액출금.
		if(balance< money) {
			
			allWithdrawMenu();

			//문자로 실행
			String aWd = scan.nextLine();

			if(aWd.toUpperCase().equals("YES")) {
				balance = 0;
				System.out.println("전액 출금되었습니다.");
			}
			else if (aWd.toUpperCase().equals("NO")) {
				System.out.println("출금요청이 취소되었습니다.");
			}
			else {
				System.out.println("잘못입력했습니다.");
			}
		}
	}

	// 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("계좌번호> "+accountNumber); 
		System.out.println("이름> "+name);
		System.out.println("잔액> "+balance);

	};
	
	//잔액부족 전체출금 메뉴
	public void allWithdrawMenu() {
		System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
		System.out.println("1.YES : 금액전체 출금처리");
		System.out.println("2.NO : 출금요청취소");
	}
	
	
	
	
}
