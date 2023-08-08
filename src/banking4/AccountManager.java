package banking4;
//컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다. 

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {
//	//배열 생성
//	private Account[] myAccounts;
//	/*배열 저장데이터 카운트 변수*/
//	private int numOfAccounts;
//	
//	//생성자
//	public AccountManager(int num) {
//		//num 만큼의 배열 생성
//		myAccounts = new Account[num];
//		numOfAccounts = 0;
//	}
	
	HashSet<Account> account = new HashSet<Account>();
	
	//계좌개설 메뉴
	public static void makeShowMenu() {
		System.out.println("-----계좌선택------");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
	}
	
	// 계좌개설을 위한 함수
	public void makeAccount() {
//		System.out.println("계좌개설 호출");
		//사용자 입력
		Scanner scan = new Scanner(System.in);
		String iAccountNum, iName, icreditRating; 
		int iBalance, iInterestRate;
		System.out.println("***신규계좌개설***");
	
		//계좌메뉴 출력
		makeShowMenu();
		//개설메뉴 사용자입력
		int makeChoice =0;
		//문자열 입력 예외처리
		try {
			makeChoice = scan.nextInt();
			scan.nextLine();
			
			//개발자지정 예외처리
			try {
				//메뉴외의 숫자를 입력하면 예외처리
				if(makeChoice>2 || makeChoice<1) {
					MenuSelectException ex = new MenuSelectException();
					
					throw ex;
				}
			} catch (MenuSelectException e) {
				System.out.println("[예외발생] "+e.getMessage());
				return;
			}
			
			System.out.print("계좌번호: ");
			iAccountNum = scan.nextLine();
			System.out.print("이름: ");
			iName = scan.nextLine();
			System.out.print("잔고: ");
			iBalance = scan.nextInt();
			System.out.print("기본이자%(정수형태로입력): ");
			iInterestRate = scan.nextInt();
			scan.nextLine();
			
			if(makeChoice==1) {
				
				Account acc = 
						new NormalAccount(iAccountNum, iName, iBalance, iInterestRate);
//				myAccounts[numOfAccounts++] = normal;
				account.add(acc);
				System.out.println("계좌계설이 완료되었습니다.");
			}
			else if(makeChoice==2) {
				System.out.print("신용등급(A,B,C등급):");
				icreditRating = scan.nextLine();
				
				Account acc =
						new HighCreditAccount(iAccountNum, iName, 
								iBalance, iInterestRate, icreditRating);
//				myAccounts[numOfAccounts++] = high;
				account.add(acc);
				System.out.println("계좌계설이 완료되었습니다.");
			}
		} catch (InputMismatchException e) {
			System.out.println("숫자로만 입력해주세요");
			scan.nextLine();
		}
			
		
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
		
		//문자입력 예외처리
		try {
			System.out.print("입금액 :");
			int money = scan.nextInt();
			
			//if문으로 예외처리
			if(money>0) {
				if(money%500==0) {
					//배열에 저장된 데이터 갯수만큼 반복
//					for(int i=0; i<account.size(); i++) {
//						
//						if(searchANum.compareTo(
//								myAccounts[i].getAccountNumber())==0) {
//							
//							myAccounts[i].deposit(money);
//							isFind = true;
//							System.out.println("입금이 완료되었습니다.");
//						}
//					}<Account> account
					for(Account acc : account) {
						if(searchANum.equals(acc.getAccountNumber())) {
							acc.deposit(money);
							isFind = true;
							System.out.println("입금이 완료");
						}
						
					}
					//찾는계좌가없다면
	//				if(isFind==false) {
	//				System.out.println("해당계좌없음");
	//				}
				}
				else {
					System.out.println("입금액은 500원 단위로 가능합니다.");
				}
			}
			else {
				System.out.println("음수를 입금 할 수 없습니다.");
			}
		} 
		catch (InputMismatchException e) {
			System.out.println("정수만 입력하세요");
			e.getMessage();
		}
		catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

		
	}; 
	
	
	// 출    금
	public void withdrawMoney() {
//		System.out.println("출    금 호출");
		
		System.out.println("***출   금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호: ");
		
		//찾을계좌번호 입력
		Scanner scan = new Scanner(System.in);
		String searchANum = scan.nextLine();
		
		//문자입력 예외처리
		try {
			System.out.print("출금액 :");
			int money = scan.nextInt();
			
			//if문으로 예외처리
			if(money>0) {
				if(money%1000==0) {
					//배열에 저장된 데이터 갯수만큼 반복
//					for(int i=0; i<numOfAccounts; i++) {
//						//계좌번호 검색
//						if(searchANum.compareTo(
//								myAccounts[i].getAccountNumber())==0) {
//
//							//잔액부족시 전체출금 메뉴
//							if(myAccounts[i].getBalance()<money) {
//								//전체출금메뉴 Account에서 호출
//								myAccounts[i].allWithdraw(money);
//							}
//							else {
//								myAccounts[i].withdraw(money);
//								System.out.println("출금이 완료되었습니다.");
//							}
//						}
//					}
					//배열에 저장된 데이터 갯수만큼 반복
					for(Account acc : account) {
						//계좌번호 검색
						if(acc.getAccountNumber().equals(searchANum)) {
							//잔액부족시 전체출금 메뉴
							if(acc.getBalance()<money) {
								//전체출금메뉴 Account에서 호출
								acc.allWithdraw(money);
							}
							else {
								acc.withdraw(money);
								System.out.println("출금이 완료되었습니다.");
							}
						}
					}
				}
				else {
					System.out.println("출금은 1000원 단위로 가능합니다.");
				}
			}
			else {
				System.out.println("음수를 출금 할 수 없습니다.");
			}
		} 
		catch (InputMismatchException e) {
			System.out.println("정수만 입력하세요");
			e.getMessage();
		}
		catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		
	}; 
	
	
	// 전체계좌정보출력
	public void showAccInfo() {
//		System.out.println("전체계좌정보출력 호출");
		System.out.println("***계좌정보출력***");
//		for(int i=0; i<numOfAccounts; i++) {
//			
//			System.out.println("-------");
//			myAccounts[i].showAccInfo();
//			System.out.println("-------");
//		}
		for(Account acc : account) {
			System.out.println("-------");
			acc.showAccInfo();
			System.out.println("-------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.\n");
		
	};  

}