//package SW;

import java.sql.*;
import java.text.*;

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
	
	//전체 직원 목록 불러오기
	public static void loadAllEmployee() {
		//Attribute 설정
		Connection con;
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;
		
		//employee table 안에 있는 전체 값 읽기 수행
		try {
			con = CreateDB.makeConnection();	//Database 연결
			stmt = con.createStatement();		//Query문(sql)을 생성하기 위한 Statement 변수 생성
		
			sql = "select * from employee";		//Query문을 sql변수에 저장
		
			rs = stmt.executeQuery(sql);		//sql 쿼리를 실행시키고 나온 결과를 rs에 저장
			
			//rs의 값 읽기
			while (rs.next()) {
				String name = rs.getString("name");
				String employeeid = rs.getString("employeeid");
			    Date birthdate = rs.getDate("birthdate");
			    String department = rs.getString("department");
			    String phonenum = rs.getString("Phonenum");
			    String address = rs.getString("address");
			    String mail = rs.getString("mail");
			    double salary = rs.getDouble("salary");
			    String position = rs.getString("position");
			    double score = rs.getDouble("score");
			    
			    System.out.format("%s %s %s %s %s %s %s %f %s %f", name, employeeid, birthdate, department, phonenum, 
			    		address, mail, salary, position, score);
			}
		}
        catch(SQLException e){
            e.printStackTrace();
        }
		
	}
	
	//신규 직원 데이터베이스에 추가하기
	public static void InsertEmployee(Object[] temporaryObject) {
		
		//연결 Attribute 설정
		Connection con;
		Statement stmt = null;
		String sql = null;
		
		//employee 변수 Attribute 설정
		String name;
		String employeeid;
		Date birthdate;
	    String department;
	    String phonenum;
	    String address;
	    String mail;
	    double salary;
	    String position;
	    double score;
		
		//새로운 employee객체를 employee table 안에 삽입 수행
		try {
			con = CreateDB.makeConnection();		//Database 연결
			stmt = con.createStatement();			//Query문(sql)을 생성하기 위한 Statement 변수 생성
		
			//sql query문을 만들기 위한 사전 employee 데이터 작업 실행
			name = temporaryObject[1].toString();
			employeeid = temporaryObject[0].toString();
			department = temporaryObject[2].toString();
			position = temporaryObject[3].toString();
			birthdate = CreateDB.transformDate(temporaryObject[4].toString());
			phonenum = temporaryObject[5].toString();
			address = temporaryObject[6].toString();
			mail = temporaryObject[7].toString();
			salary = (double)temporaryObject[8];
			score = (double)temporaryObject[9];

			//sql변수에 query 저장
			sql = "insert into employee values(\"" + name + "\", \"" + employeeid + "\", \"" + birthdate + "\", \"" + department +
					"\", \"" + phonenum + "\", \"" + address + "\", \"" + mail + "\", " + salary + ", \"" + position + "\", " + score +");";
			
			System.out.println(sql);
			stmt.execute(sql);						//쿼리 수행
		}
        catch(SQLException e){
            e.printStackTrace();
        }
	}
	
	//퇴직직원 데이터베이스에서 삭제하기
	public static void DeleteEmployee(Object[] temporaryObject) {
		
		//연결 Attribute 설정
		Connection con;
		Statement stmt = null;
		String sql = null;
		
		//employee 변수 Attribute 설정
		String employeeid;
		
		//퇴직한 employee의 내용을 삭제하기 위한 절차 수행
		try {
			con = CreateDB.makeConnection();		//Database 연결
			stmt = con.createStatement();			//Query문(sql)을 생성하기 위한 Statement 변수 생성
		
			//삭제하고자 하는 직원의 id값을 찾기 위한 employeeid 변수 설정			
			employeeid = temporaryObject[0].toString();
			
			//sql변수에 query 저장
			sql = "delete from employee where employeeid like \""+ employeeid + "\";";
			
			System.out.println(sql);
			stmt.execute(sql);						//쿼리 수행
		}
        catch(SQLException e){
            e.printStackTrace();
        }
	}
	
	//기존 직원의 수정된 데이터 데이터베이스에서 수정하기
	public static void ModifyEmployee(Object[] temporaryObject) {
		
		//연결 Attribute 설정
		Connection con;
		Statement stmt = null;
		String sql = null;
		
		//employee 변수 Attribute 설정
		String name;
		String employeeid;
		Date birthdate;
	    String department;
	    String phonenum;
	    String address;
	    String mail;
	    double salary;
	    String position;
	    double score;
		
		//새로운 employee객체를 employee table 안에 삽입 수행
		try {
			con = CreateDB.makeConnection();		//Database 연결
			stmt = con.createStatement();			//Query문(sql)을 생성하기 위한 Statement 변수 생성
		
			//sql query문을 만들기 위한 사전 employee 데이터 작업 실행
			name = temporaryObject[1].toString();
			employeeid = temporaryObject[0].toString();
			department = temporaryObject[2].toString();
			position = temporaryObject[3].toString();
			birthdate = CreateDB.transformDate(temporaryObject[4].toString());
			phonenum = temporaryObject[5].toString();
			address = temporaryObject[6].toString();
			mail = temporaryObject[7].toString();
			salary = (double)temporaryObject[8];
			score = (double)temporaryObject[9];

			//sql변수에 query 저장
			sql = "update employee" + 
					" set name = \"" + name + "\", employeeid = \"" + employeeid + "\", birthdate = \"" + birthdate + 
					"\", department = \"" + department + "\", phonenum = \"" + phonenum + 
					"\", address = \"" + address + "\", mail = \"" + mail + "\", salary = " + salary + 
					", position = \"" + position + "\", score = " + score +
					"where employeeid = \"" + employeeid + "\";";
			
			System.out.println(sql);
			stmt.execute(sql);						//쿼리 수행
		}
        catch(SQLException e){
            e.printStackTrace();
        }
	}
	
	//string을 java.sql.date형으로 변환함
	public static Date transformDate(String date) {
        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
        
        java.util.Date tempDate = null;
        
        try {
            tempDate = beforeFormat.parse(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        
        String transDate = afterFormat.format(tempDate);
        
        // 반환된 String 값을 Date로 변경한다.
        Date d = Date.valueOf(transDate);
        
        return d;
    }

}
