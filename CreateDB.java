import java.sql.*;

public class CreateDB {
	public static Connection makeConnection() {
		String url = "jdbc:mysql://127.0.0.1/company?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, "root", "dltjgus07_");
			System.out.println("데이터베이스 연결 성공");
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch(SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
		return con;
	}
	public static void load() {
		Connection con = CreateDB.makeConnection();
		Statement stmt = null;
		stmt = con.createStatement();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = makeConnection();
	}

}
