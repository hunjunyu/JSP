package member.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {

	
	// DB 연결시  관한 변수 

	private static final String 	dbDriver	=	"oracle.jdbc.driver.OracleDriver";
	private static final String		dbUrl		=	"jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String		dbUser		=	"scott";
	private static final String		dbPass		=	"tiger";

		
	private static MemberDao memberDao;
	
	public static MemberDao getInstance() throws MemberException
	{
		if( memberDao == null )
		{
			memberDao =  new MemberDao();
		}
		return memberDao;
	}
	

	private MemberDao() throws MemberException
	{
			
		try{
			
			/********************************************
				1. 드라이버를 로딩
							
			*/
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("memberDAO 객체 생성 - 드라이버로딩 성공");
			
		
		}catch( Exception ex ){
			throw new MemberException("DB 연결시 오류  : " + ex.toString() );	
		}
	}
	
	/*******************************************
	 * * 회원관리테이블 MEMBERTEST 에  회원정보를 입력하는 함수
	 * @param rec
	 * @throws MemberException
	 */
	public void insertMember( Member rec ) throws MemberException
	{ 		Connection con = null;
			PreparedStatement pr = null;
		try {
			
			 // 0. 연결 객체 얻어오기
			String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String USER ="scott";
			String PASS = "tiger";
			con = DriverManager.getConnection(URL, USER , PASS);
			 // 1. sql 문장 만들기 ( insert문 )
			String sql = "INSERT INTO memberTest(id,password,name,tel,addr) VALUES(?,?,?,?,?)";
			 // 2. sql 전송 객체 만들기
			pr = con.prepareStatement(sql);
			pr.setString(1, rec.getId());
			pr.setString(2, rec.getPassword());
			pr.setString(3, rec.getName());
			pr.setString(4, rec.getTel());
			pr.setString(5, rec.getAddr());
			
			 // 3. sql 전송
			pr.executeUpdate();
			 // 4. 객체 닫기
			con.close();
			pr.close();
		} catch ( Exception ex ){
			throw new MemberException("멤버 입력시 오류  : " + ex.toString() );		
			
		}			
	}
	
	/**********************************************************
	 * * 회원관리테이블 MEMBERTEST에서 기존의 id값과 중복되는지 확인하는 함수
	 */
	public boolean isDuplicatedId( String id ) throws MemberException
	{
		Connection con = null;
		PreparedStatement pr = null;
		boolean flag = false;
		
		try{
			String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String USER ="scott";
			String PASS = "tiger";
			
			con = DriverManager.getConnection(URL, USER , PASS);
				
			String sql = "SELECT*FROM membertest WHERE id=?";
			
			pr= con.prepareStatement(sql);
			pr.setString(1, id);
			
			ResultSet rs = pr.executeQuery();
			
			if(rs.next()) {
				flag = true;
			}
			
			rs.close();
			con.close();
			pr.close();
		}catch( Exception ex ){
			throw new MemberException("중복아이디 검사시 오류  : " + ex.toString() );			
		}
			
		return flag;
	}
	
	
	public boolean checkLogin(String id, String pass) 
	throws Exception{
		boolean result = false;
		Connection con = null;
		PreparedStatement pr = null;
		
		try{
			String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";//오라클 위치
			String USER ="scott";//오라클 아이디
			String PASS = "tiger";//오라클 scott계정의 비밀번호
			
			con = DriverManager.getConnection(URL, USER , PASS);//오라클db에 연결
			
			//sql문장
			String sql = "SELECT*FROM membertest WHERE id=? AND password=?";
			
			pr= con.prepareStatement(sql);//sql삽입
			pr.setString(1, id);
			pr.setString(2, pass);
			
			ResultSet rs = pr.executeQuery();//sql업데이트
			
			if(rs.next()) {//rs를 하나씩 읽어준다
				result = true;//result를 진짜로 설정
			}
			
			rs.close();  //업데이트 문장 닫기
			con.close(); //연결객체 닫기
			pr.close();  //문장닫기
		}catch( Exception ex ){
			throw new MemberException("중복계정 검사시 오류  : " + ex.toString() );			
		}
				
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
