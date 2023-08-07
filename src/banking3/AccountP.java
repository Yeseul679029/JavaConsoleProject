package banking3;

import java.util.Scanner;

//계좌정보를 표현한 클래스로 NormalAccount, HighCreditAccount의 부모클래스가 된다. 
//Account에서 전체출금 메뉴 생성, 호출

abstract class AccountP {

	private String accountNumber; //계좌번호
	private String name; //이름
	private int balance; //잔액

	//생성자
	public AccountP(String accountNumber, String name, int balance) {
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
			//잔고가 출금금액보다 적을때 전액출금.
			if(balance< money) {
				balance = 0;
				System.out.println("전액 출금되었습니다.");
			}
		}

	// 전체계좌정보출력
	public void showAccInfo() {
//		System.out.println("-------");
		System.out.println("계좌번호> "+accountNumber); 
		System.out.println("이름> "+name);
		System.out.println("잔액> "+balance);
//		System.out.println("-------");

	};
	
}
