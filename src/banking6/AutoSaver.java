package banking6;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;


public class AutoSaver extends Thread {
	
	private AccountManager AManager;
	public AutoSaver(AccountManager AManager) {
		this.AManager = AManager;
	}
	
	@Override
	public void run() {
		System.out.println("자동 저장이 시작되었습니다.");
		
		while(true) {
			try {
				//5초 쉬게하며 자동 저장
				sleep(5000);
				
				
				AManager.saveToFile();
//				System.out.println("자동저장 되었습니다.");
				
		
			} catch (InterruptedException e) {
				System.out.println("자동저장이 종료되었습니다.");
				return;
			}
			catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}	
			
		}
		
		
	}

}
