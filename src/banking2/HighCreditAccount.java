package banking2;
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
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자> "+ interestRate);
		System.out.println("신용등급> "+ creditRating);
	}
	
}
