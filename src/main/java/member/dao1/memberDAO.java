package member.dao1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import oracle.net.aso.g;
import temp.EmpDAO;
import temp.EmpVO;

public class memberDAO {
	
	private memberDAO() throws Exception{
		//1. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("memberDAO 객체 생성 - 드라이버로딩 성공");
	}
	
	static memberDAO memberdao = null;
	public static memberDAO getInstance() throws Exception{
		if(memberdao == null) memberdao = new memberDAO();
		return memberdao; 
	}
	
	
	
	public void insert(memberVO vo)throws Exception{
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
		String sql = "INSERT INTO member(name,nickname,email,age) VALUES(?,?,?,?)";
		// 4. 전송 객체 얻어오기(+?에 값지정)
		ps = con.prepareStatement(sql);
		ps.setString(1, vo.getName());
		ps.setString(2, vo.getNick());
		ps.setString(3, vo.getEmail());
		ps.setInt(4, vo.getAge());
		
		// 5. 전송
		ps.executeUpdate();
		
		}finally{
		//6 .닫기
		con.close();
		ps.close();
		}
	}
	
}
