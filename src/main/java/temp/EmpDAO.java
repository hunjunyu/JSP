package temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpDAO {
	
	private EmpDAO() throws Exception{
		//1. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("EmpDAO 객체 생성 - 드라이버로딩 성공");
	}
	
	static EmpDAO empDAO = null;
	public static EmpDAO getInstance() throws Exception{
		if(empDAO == null) empDAO = new EmpDAO();
		return empDAO; 
	}
	
	
	
	public void insert(EmpVO vo)throws Exception{
		//변수선언
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
		// 2. 연결객체 얻어오기
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String USER ="scott";
		String PASS = "tiger";
		
		con = DriverManager.getConnection(URL, USER , PASS);	
		
		// 3. sql 문장 만들기(emp테이블에 insert)
		String sql = "INSERT INTO EMP(empno,ename,deptno,job,sal) VALUES(?,?,?,?,?)";
		// 4. 전송 객체 얻어오기(+?에 값지정)
		ps = con.prepareStatement(sql);
		ps.setInt(1, vo.getEmpno());
		ps.setString(2,vo.getEname());
		ps.setInt(3, vo.getDeptno());
		ps.setString(4, vo.getJob());
		ps.setInt(5, vo.getSal());
		
		// 5. 전송
		ps.executeUpdate();
		
		}finally{
		//6 .닫기
		con.close();
		ps.close();
		}
	}
	
}
