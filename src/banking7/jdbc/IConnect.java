package banking7.jdbc;

public interface IConnect {

	String ORACLE_DRIVER="oracle.jdbc.OracleDriver";
	String ORACLE_URL="jdbc:oracle:thin:@localhost:1521:xe";
	
	void connect(String user, String pass);//DB연결
	void execute();//쿼리실행
	void close();//자원반납
	//사용자로부터 입력값을 받기위해 정의한 메서드
	String scanValue(String title);
}
