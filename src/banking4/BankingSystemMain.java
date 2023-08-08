package banking4;

import java.util.InputMismatchException;
import java.util.Scanner;

//main 메서드를 포함한 클래스. 프로그램은 여기서 실행한다. 
public class BankingSystemMain {

	//메뉴출력 메서드
	public static void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입	금");
		System.out.println("3.출	금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.프로그램종료");		
	};
	
	public static void main(String[] args){

		
		//사용자 입력
		Scanner scan = new Scanner(System.in);
		
		//기능담당 AccountManager의 인스턴스 생성
		AccountManager AManager = new AccountManager();
		
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
					//버퍼제거?
					scan.nextLine();
				}
				//개발자지정 예외처리
				try {
					//메뉴외의 숫자를 입력하면 예외처리
					if(choice>5 || choice<1) {
						MenuSelectException ex = new MenuSelectException();
						
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
				case ICustomDefine.EXIT: //프로그램종료
					System.out.println("프로그램종료");
					return;//메인함수 리턴되면 프로그램 종료
				}
		}
		
	}

}
