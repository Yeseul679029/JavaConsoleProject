package banking3;
/*
Account의 자식클래스로 보통예금계좌를 의미한다.
생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다.

 */
public class NormalAccount extends Account{
	
	//이자비율 초기화
	private double interestRate;
	//생성자.
	public NormalAccount(String accountNumber, String name, int balance, double interestRate) {
		super(accountNumber, name, balance);
		this.interestRate = interestRate;
	}
	//이자비율 getter,setter
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	//입금처리 
	@Override
	public void deposit(int money) {
		int myMoney=super.getBalance();
		myMoney+= (myMoney*(interestRate/100))+ money ;
		super.setBalance(myMoney);
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자> "+ (int)interestRate+" %");
	}

}
