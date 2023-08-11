package banking7.jdbc;

import java.util.Scanner;

//main 메서드를 포함한 클래스. 프로그램은 여기서 실행한다. 
public class BankingSystemMain {

	//배열 생성 static을 붙여줘야한다.
	private static Account[] myAccounts;
	/*배열 저장데이터 카운트 변수*/
	private static int numOfAccounts;
	
		
	//메뉴출력 메서드
	public static void showMenu() {	
		System.out.println("---------- Menu -----------");
		System.out.print(" 1.계좌개설 ");
		System.out.print(" 2.입금 ");
		System.out.print(" 3.출금 ");
		System.out.print("\n 4.계좌정보출력");
		System.out.print(" 5.프로그램종료\n");
		System.out.println("---------------------------");
	};
	
	public static void main(String[] args) {

		//사용자 입력
		Scanner scan = new Scanner(System.in);
		
		//50개를 담을 수 있는 배열로 지정
		myAccounts = new Account[50];
		numOfAccounts = 0;
		
		//메뉴를 무한루프한다
		while(true) {
			//메뉴 출력
			showMenu();
			
			//2.사용자 기능 메뉴 선택
			int choice = scan.nextInt();
			
			switch (choice) {
			case ICustomDefine.MAKE: //개설
				makeAccount();
				break;
			case ICustomDefine.DEPOSIT: //입금
				depositMoney();
				break;
			case ICustomDefine.WITHDRAW: //출금
				withdrawMoney();
				break;
			case ICustomDefine.INQUIRE: //전체계좌정보출력
				showAccInfo();
				break;
			case ICustomDefine.EXIT: //프로그램종료
				System.out.println("프로그램종료");
				return;//메인함수 리턴되면 프로그램 종료
			}
		}
		
	}
	
	// 계좌개설을 위한 함수
	public static void makeAccount() {
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
		
		new InsertSQL(iAccountNum,iName,iBalance).execute();
		
		System.out.println("계좌계설이 완료되었습니다.");
		
		Account account = 
				new Account(iAccountNum, iName, iBalance);
		
		//인스턴스 배열에 추가 0번인덱스에 추가후 1증가 
		myAccounts[numOfAccounts++] = account;
		
	};
	
	// 입    금
	public static void depositMoney() {
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
		if(isFind==false) {
			System.out.println("해당계좌없음");
		}
		
		
	};   
	// 출    금
	public static void withdrawMoney() {
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
		if(isFind==false) {
			System.out.println("해당계좌없음");
		}
		
		
	}; 
	
	
	// 전체계좌정보출력
	public static void showAccInfo() {
//		System.out.println("전체계좌정보출력 호출");
		System.out.println("***계좌정보출력***");
		for(int i=0; i<numOfAccounts; i++) {
			
			myAccounts[i].showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.\n");
		
	};
	
	
	

}
