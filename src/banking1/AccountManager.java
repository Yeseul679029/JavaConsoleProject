package banking1;
//컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다. 

import java.util.Scanner;

public class AccountManager {
	//배열 생성
	private Account[] myAccounts;
	/*배열 저장데이터 카운트 변수*/
	private int numOfAccounts;
	
	//생성자
	public AccountManager(int num) {
		//num 만큼의 배열 생성
		myAccounts = new Account[num];
		numOfAccounts = 0;
	}
	
	
	// 계좌개설을 위한 함수
	public void makeAccount() {
//		System.out.println("계좌개설 호출");
		//사용자 입력
		Scanner scan = new Scanner(System.in);
		String iAccountNum, iName; 
		int iBalance;
		System.out.print("계좌번호: ");
		iAccountNum = scan.nextLine();
		System.out.print("이름: ");
		iName = scan.nextLine();
		System.out.print("잔고: ");
		iBalance = scan.nextInt();
		System.out.println("계좌계설이 완료되었습니다.");
		
		Account account = 
				new Account(iAccountNum, iName, iBalance);
		
		//인스턴스 배열에 추가 0번인덱스에 추가후 1증가 
		myAccounts[numOfAccounts++] = account;
		
	}; 
	// 입    금
	public void depositMoney() {
//		System.out.println("입    금 호출");
				
		boolean isFind = false;
		
		System.out.println("***입   금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호: ");
		
		//찾을계좌번호 입력
		Scanner scan = new Scanner(System.in);
		String searchANum = scan.nextLine();
		
		System.out.print("입금액 :");
		int money = scan.nextInt();
		System.out.println("입금이 완료되었습니다.");
		
		
		//배열에 저장된 데이터 갯수만큼 반복
		for(int i=0; i<numOfAccounts; i++) {
			
			if(searchANum.compareTo(
					myAccounts[i].getAccountNumber())==0) {
				
				myAccounts[i].deposit(money);
//				System.out.println("입금이 완료되었습니다.");
				isFind = true;
			}
			
		}
		//찾는계좌가없다면
//		if(isFind==false) {
//			System.out.println("해당계좌없음");
//		}
		
		
	};   
	// 출    금
	public void withdrawMoney() {
//		System.out.println("출    금 호출");
		
		boolean isFind = false;
		
		System.out.println("***출   금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호: ");
		
		//찾을계좌번호 입력
		Scanner scan = new Scanner(System.in);
		String searchANum = scan.nextLine();
		
		System.out.print("출금액 :");
		int money = scan.nextInt();
		System.out.println("출금이 완료되었습니다.");
		
		
		//배열에 저장된 데이터 갯수만큼 반복
		for(int i=0; i<numOfAccounts; i++) {
			
			if(searchANum.compareTo(
					myAccounts[i].getAccountNumber())==0) {

				myAccounts[i].withdraw(money);
				isFind = true;
			}
			
		}
		//찾는계좌가없다면
//		if(isFind==false) {
//			System.out.println("해당계좌없음");
//		}
		
		
	}; 
	
	
	// 전체계좌정보출력
	public void showAccInfo() {
//		System.out.println("전체계좌정보출력 호출");
		System.out.println("***계좌정보출력***");
		for(int i=0; i<numOfAccounts; i++) {
			
			myAccounts[i].showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.\n");
		
	};  

}
