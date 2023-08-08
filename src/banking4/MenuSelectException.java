package banking4;
//개발자가 직접 정의한 예외처리 클래스

public class MenuSelectException extends Exception {

	public MenuSelectException() {
		super("지정된 숫자만 입력하세요");
	}

}
