package banking5;

import java.util.InputMismatchException;
import java.util.Scanner;

//main 메서드를 포함한 클래스. 프로그램은 여기서 실행한다. 
public class BankingSystemMain {

	//메뉴출력 메서드
	public static void showMenu() {
		System.out.println("-----Menu------");
		System.out.print("1.계좌개설 ");
		System.out.print("2.입금 ");
		System.out.print("3.출금 ");
		System.out.print("\n4.계좌정보출력 ");
		System.out.print("5.계좌정보삭제 ");
		System.out.print("\n6.프로그램종료\n");		
	};
	
	public static void main(String[] args){

		
		//사용자 입력
		Scanner scan = new Scanner(System.in);
		
		//기능담당 AccountManager의 인스턴스 생성
		AccountManager AManager = new AccountManager();
		
		//인풋스트림 호출
		AManager.inputAccount();
		
		
		//메뉴를 무한루프한다
		while(true) {
			//메뉴 출력
			showMenu();
			
				//2.사용자 기능 메뉴 선택
				int choice = 0;
				//문자입력 예외처리
				try {
					choice = scan.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("숫자로만 입력해주세요.");
					scan.nextLine(); // 입력버퍼를 비운다.
				}
				//개발자지정 예외처리
				try {
					//메뉴외의 숫자를 입력하면 예외처리
					if(choice>5 || choice<1) {
						String msg = "1~5까지의 숫자만 입력하세요";
						MenuSelectException ex = new MenuSelectException(msg);
						
						throw ex;
					}
				} catch (MenuSelectException e) {
					System.out.println("[예외발생] "+e.getMessage());
				}
				
				switch (choice) {
				case ICustomDefine.MAKE: //개설
					AManager.makeAccount();
					break;
				case ICustomDefine.DEPOSIT: //입금
					AManager.depositMoney();
					break;
				case ICustomDefine.WITHDRAW: //출금
					AManager.withdrawMoney();
					break;
				case ICustomDefine.INQUIRE: //전체계좌정보출력
					AManager.showAccInfo();
					break;
				case ICustomDefine.DELETE_INFO: //계좌정보삭제
					AManager.deleteInfo();
					break;
				case ICustomDefine.EXIT: //프로그램종료
					System.out.println("프로그램종료");
					//세이브 메서드 호출
					AManager.outAccount();
					return;//메인함수 리턴되면 프로그램 종료
				}
		}
		
	}

}
