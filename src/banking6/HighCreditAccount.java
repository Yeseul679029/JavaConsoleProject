package banking6;
/*
Account의 자식클래스로 신용도가 높은 고객에게 개설이 허용되며 높은 이율의 계좌이다.
생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다.

 */
public class HighCreditAccount extends Account {

	//이자비율 초기화
	private double interestRate;
	private String creditRating;
	//생성자.
	public HighCreditAccount(String accountNumber, 
			String name, int balance, double interestRate,String creditRating) {
		super(accountNumber, name, balance);
		this.interestRate = interestRate;
		this.creditRating = creditRating;
	}
	//이자비율 getter,setter
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public String getcreditRating() {
		return creditRating;
	}
	public void setcreditRating(String creditRating) {
		this.creditRating = creditRating;
	}
	
	@Override
	public void deposit(int money) {
		
		int myMoney=super.getBalance();
		double cInterestRate = 0;
		
		switch (creditRating) {
		case "A": case "a":
			cInterestRate = ICustomDefine.CREDIT_RATING_A;
			break;
		case "B": case "b":
			cInterestRate = ICustomDefine.CREDIT_RATING_B;
			break;
		case "C": case "c":
			cInterestRate = ICustomDefine.CREDIT_RATING_C;
			break;
		}
		
		myMoney+= ((myMoney*(interestRate/100))+(myMoney*(cInterestRate/100))+ money) ;
		super.setBalance(myMoney);
	}
	//출력
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자> "+ (int)interestRate+" %");
		System.out.println("신용등급> "+ creditRating.toUpperCase());
	}
	
}
