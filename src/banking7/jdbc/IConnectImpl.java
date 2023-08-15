package banking7.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class IConnectImpl implements IConnect {
	
	//멤버변수
	public Connection con; //DB연결
	public PreparedStatement psmt; //동적쿼리문실행
	public ResultSet rs;//select 실행결과 반환
	
	public Statement stmt; //정적쿼리 실행
	public CallableStatement csmt; //프로시저 실행
	
	//매개변수 없음
	public IConnectImpl() {
		try {
			Class.forName(ORACLE_DRIVER);
			connect("education","1234");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	@Override
	public void connect(String user, String pass) {
		
		try {
			con = DriverManager.getConnection(ORACLE_URL,user,pass);
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 오류");
			e.printStackTrace();
		}
		
	}
	
	//오버라이딩 목적 각클래스에서 재정의하기
	@Override
	public void execute() {
		//실행부없음!
	}
	
	//자원반납
	@Override
	public void close() {
		try {
			if(con!=null) con.close();
			if(psmt!=null) psmt.close();
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(csmt!=null) csmt.close();
//			System.out.println("자원 반납 완료");
			
		} catch (Exception e) {
			System.out.println("자원 반납시 오류발생");
			e.printStackTrace();
		}
	}
	//사용자로부터 입력값을 받기위한 메서드
	@Override
	public String scanValue(String title) {
		Scanner scan = new Scanner(System.in);
		System.out.println(title);
		String inputStr = scan.nextLine();
		
		if("EXIT".equalsIgnoreCase(inputStr)) {
			System.out.println("프로그램을 종료합니다.");
			close();
			System.exit(0);
		}
		return inputStr;
	}

}
