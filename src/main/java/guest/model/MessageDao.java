package guest.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageDao {

	// Single Pattern 
	private static MessageDao instance;
	
	// DB 연결시  관한 변수 
	private static final String 	dbDriver	=	"oracle.jdbc.driver.OracleDriver";
	private static final String		dbUrl		=	"jdbc:oracle:thin:@192.168.0.41:1521:xe";
	private static final String		dbUser		=	"scott";
	private static final String		dbPass		=	"tiger";
	
	
	

	//	 객체 생성하는 메소드 
	public static MessageDao	getInstance() throws MessageException
	{
		if( instance == null )
		{
			instance = new MessageDao();
		}
		return instance;
	}
	
	// 오라클 db 연결하는 메소드
	private MessageDao() throws MessageException
	{ 	
	
		try{//예외처리
			//오라클 db 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");

		}catch( Exception ex ){//예외처리
			throw new MessageException("방명록 ) DB 연결시 오류  : " + ex.toString() );	
		}
		
	}
	
	
	
	 //메세지를 입력하는 함수
	public void insert(Message rec) throws MessageException
	{
		Connection	 		con = null;
		PreparedStatement ps = null;
		try{

			// 1. 연결객체(Connection) 얻어오기
			String URL = "jdbc:oracle:thin:@192.168.0.41:1521:xe";
			String USER ="scott";
			String PASS = "tiger";
			con = DriverManager.getConnection(URL, USER , PASS);
			// 2. sql 문장 만들기
			String sql = "INSERT INTO guesttb (message_id, guest_name, password, message)"
					     +"  VALUES (seq_guestTb_messageId.nextval,?,?,?)";
			// 3. 전송객체 얻어오기
			ps = con.prepareStatement(sql);
			ps.setString(1, rec.getGuestName());
			ps.setString(2, rec.getPassword());
			ps.setString(3, rec.getMessage());
			
			
			// 4. 전송하기
			ps.executeUpdate();
	
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 입력시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
	
	}
	
	// 메세지 목록 전체를 얻어올 때
	public List<Message> selectList() throws MessageException
	{
		Connection	 		con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Message> mList = new ArrayList<Message>();//Message파일의 값들을 mlist에 넣는다
		boolean isEmpty = true;
		
		try{//예외처리
			// 1. 연결객체(Connection) 얻어오기
			String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String USER ="scott";
			String PASS = "tiger";
			
			con = DriverManager.getConnection(URL, USER , PASS);
			//sql문장	
			String sql = "SELECT*FROM guesttb";
			//전송객체 얻어오기
			ps = con.prepareStatement(sql);
			//전송
			rs = ps.executeQuery();
			//전송된 값 가져오기
			while(rs.next()) {
				
				Message m = new Message();
				m.setMessageId(rs.getInt("MESSAGE_ID"));
				m.setGuestName(rs.getString("GUEST_NAME"));
				m.setPassword(rs.getString("PASSWORD"));
				m.setMessage(rs.getString("MESSAGE"));
				mList.add(m);
				isEmpty = false;
				
			}
			//값이 비어있을시. emptyList()로 리턴
			if( isEmpty ) return Collections.emptyList();
			
			return mList;//리턴값
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	

	 // 현재 페이지에 보여울 메세지 목록  얻어올 때
	public List<Message> selectList(int firstRow, int endRow) throws MessageException
	{
		Connection	 		con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Message> mList = new ArrayList<Message>();
		boolean isEmpty = true;
		
		try{//예외처리
			//db 연결 객체 얻어오기 
			String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String USER ="scott";
			String PASS = "tiger";
			
			con = DriverManager.getConnection(URL, USER , PASS);
			//SQL문장	
			String sql = "select *from guesttb\r\n"
					+ "where message_id in(select message_id\r\n"
					+ "from(select rownum as rnum,message_id\r\n"
					+ "from (select message_id  From guesttb \r\n"
					+ "order by message_id desc))\r\n"
					+ "where rnum>=? and rnum<=?)\r\n"
					+ "order by message_id desc";
			//전송객체 얻어오기
			ps = con.prepareStatement(sql);
			ps.setInt(1, firstRow);
			ps.setInt(2, endRow);
			//전송
			rs = ps.executeQuery();
			//전송된 값 얻어오기
			while(rs.next()) {
				//얻어온 값을 Message.java파일의 변수에 넣어주기
				Message m = new Message();
				m.setMessageId(rs.getInt("MESSAGE_ID"));
				m.setGuestName(rs.getString("GUEST_NAME"));
				m.setPassword(rs.getString("PASSWORD"));
				m.setMessage(rs.getString("MESSAGE"));
				mList.add(m);
				isEmpty = false;
				
			}
			
			
			
			if( isEmpty ) return Collections.emptyList();
			//값이 비어있거나 못가져오면 에러발생.
			return mList;
			//
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	
	

	 // 메세지 전체 레코드 수를 검색
	public int getTotalCount() throws MessageException{
		Connection	 		con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;

		try{
			//db 연결 객체 얻어오기
			con = DriverManager.getConnection(dbUrl,dbUser,dbPass);
			//sql 문장
			String sql = "SELECT count(*) cnt FROM guesttb";
			//전송객체 얻어오기
			ps = con.prepareStatement(sql);
			//전송
			rs = ps.executeQuery();
			//전송된 값 얻어오기
			if(rs.next()) {//값 대입하기
			count = rs.getInt("cnt");//count에 cnt값을 넣는다.
			}
			return  count;//리턴값으로 cnt값을 리턴
			
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}			
	}
	
	
	  // 메세지 번호와 비밀번호에 의해 메세지 삭제
	public int delete( int messageId, String password ) throws MessageException
	{ 	//지역변수의 초기화
		int result = 0;
		Connection	 		con = null;
		PreparedStatement ps = null;
		try{//예외처리
			// 드라이버 연결
			con = DriverManager.getConnection(dbUrl,dbUser,dbPass);
			//sql 문장
			String sql = "DELETE FROM guesttb WHERE message_id = ? AND password = ?";
			//연결객체 생성
			ps = con.prepareStatement(sql);
			ps.setInt(1, messageId);
			ps.setString(2, password);
			//전송
			result = ps.executeUpdate();//result에 전송값을 넣어준다

			return result;//리턴값
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 삭제시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
}
