package banking6;
//컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다. 

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class AccountManager {
	//Hashset 생성	
	HashSet<Account> account = new HashSet<Account>();
	
	
	//불러오기 inputStream
	public void inputAccount() {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(
				new FileInputStream("src/banking6/AccountInfo.obj"));
			
			//진짜 알수가없다 왜지 왜 지금은 되는지 모르겠다.
			account =(HashSet<Account>)in.readObject();
	
			in.close();
			
		}
		catch (FileNotFoundException e) {
			System.out.println("[예외발생] 지정된 파일을 찾을 수 없습니다.");
			
		}
		catch (ClassNotFoundException e) {
			System.out.println("클래스없음");
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		
	}
	//내보내기 outputStream
	public void outAccount() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("src/banking6/AccountInfo.obj"));
			
			out.writeObject(account);
			System.out.println("저장되었습니다.");
			
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
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
					String msg = "지정된 숫자만 입력하세요";
					MenuSelectException ex = new MenuSelectException(msg);
					
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
			
			//Account 타입 생성 변수 
			Account acc = null ;
			
			if(makeChoice==1) {
				//객체 생성
				acc = new NormalAccount(iAccountNum, iName, iBalance, iInterestRate);
			}
			else if(makeChoice==2) {
				System.out.print("신용등급(A,B,C등급):");
				icreditRating = scan.nextLine();
				icreditRating = icreditRating.toUpperCase();
				//신용등급 외의 예외처리
				try {
					switch (icreditRating) {
					case "A": case "B": case "C":
						break;
					default:
						String msg = "지정된 신용등급만 적으세요";
						MenuSelectException ex = new MenuSelectException(msg);
						
						throw ex;
						
					}
				} 
				catch (MenuSelectException e) {
					System.out.println("[예외발생]"+e.getMessage());
					return;
				}
				
				//객체 생성
				acc = new HighCreditAccount(iAccountNum, iName, 
								iBalance, iInterestRate, icreditRating);
			}
			//객체 중복확인 변수, acc 추가 
			boolean flag = account.add(acc);
			
			if(flag==true) {
				System.out.println("계좌계설이 완료되었습니다.");
			}
			else {
				System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
				String yn= scan.nextLine();
//				yn = yn.toUpperCase();
				
				switch (yn) {
				case "y": case "Y": 
					account.remove(acc);
					account.add(acc);
					System.out.println("새로운 정보로 덮어씌웠습니다.");
					break;
					
				case "n": case "N":
					System.out.println("기존 정보를 유지합니다.");
					break;
					
				default:
					System.out.println("다른문자를 입력하셨습니다.");
					break;
				}
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
					//저장된 데이터 갯수만큼 반복
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

		//확장for문으로 작성
//		for(Account acc : account) {
//			System.out.println("-------");
//			acc.showAccInfo();
//			System.out.println("-------");
//		}
		//iterator로 작성
		Iterator<Account> itr = account.iterator();
		while(itr.hasNext()) {
			Account ac = itr.next();
			System.out.println("-------");
			ac.showAccInfo();
			System.out.println("-------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.\n");
		
	};  

	//계좌정보삭제
	public void deleteInfo() {
//		System.out.println("계좌정보 삭제 출력");
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 계좌번호를 입력하세요: ");
		String deleteNum = scan.nextLine();
		
		Iterator<Account> itr = account.iterator();
		while(itr.hasNext()) {
			
			Account ac = itr.next();
			
			if(ac.getAccountNumber().equals(deleteNum)) {
				account.remove(ac);
				isFind = true;
				break;
			}
		}
		if(isFind==true) {
			System.out.println("계좌가 삭제되었습니다.");
		}
		else {
			System.out.println("찾는 정보가 없습니다.");
		}
	}
	
	public void autosaver() {
//		System.out.println("자동저장옵션 호출");
		
		System.out.println("-----계좌선택------");
		System.out.println("1.자동저장On ");
		System.out.println("2.자동저장Off ");
		System.out.println("-----------------");
		
		Scanner scan = new Scanner(System.in);
		int autoMenu = scan.nextInt();
		
		switch (autoMenu) {
		case 1: 
			//자동저장 on
			break;
		case 2: 
			//자동저장 off
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + autoMenu);
		}
		
		
	}
	
}
