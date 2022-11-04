package board_ex.model;



import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import guest.model.Message;
import guest.model.MessageException;

public class BoardDao
{
   
   // Single Pattern 
   private static BoardDao instance;
   
   // DB 연결시  관한 변수  
   private static final String    dbDriver   =   "oracle.jdbc.driver.OracleDriver";
   private static final String      dbUrl      =   "jdbc:oracle:thin:@192.168.0.41:1521:xe";
   private static final String      dbUser      =   "scott";
   private static final String      dbPass      =   "tiger";
   
   
   private Connection          con;   
   
   //--------------------------------------------
   //#####    객체 생성하는 메소드 
   public static BoardDao   getInstance() throws BoardException
   {
      if( instance == null )
      {
         instance = new BoardDao();
      }
      return instance;
   }
   
   private BoardDao() throws BoardException
   {
   
      try{
         
         /********************************************
         1. 오라클 드라이버를 로딩
            ( DBCP 연결하면 삭제할 부분 )
      */
         Class.forName( dbDriver );   
      }catch( Exception ex ){
         throw new BoardException("DB 연결시 오류  : " + ex.toString() );   
      }
      
   }
   
   /************************************************
    * 함수명 : insert
    * 역할 :   게시판에 글을 입력시 DB에 저장하는 메소드 
    * 인자 :   BoardVO
    * 리턴값 : 입력한 행수를 받아서 리턴
   */
   public int insert( BoardVO rec ) throws BoardException
   {

      ResultSet rs = null;
      Statement stmt   = null;
      PreparedStatement ps = null;
      //아래두변수는 새로고침하면 계속 값이 들어가는 막기위한 변수선언이다
      PreparedStatement ps2 = null;
      int result = 0;

      try{

         con   = DriverManager.getConnection( dbUrl, dbUser, dbPass );
         
         //* sql 문장 만들기
         String putQuery      = "INSERT INTO board_ex(seq,title,writer,content,regdate,cnt,pass) VALUES(seq_board.nextval,?,?,?,sysdate,0,?)";  

         ps      = con.prepareStatement( putQuery );
         //* sql 문장의 ? 지정하기
            //데이터는 sysdate로 설정가능하고 초기값은 그냥 0두면됨
            //?갯수만큼 순번정해 잘들어가기
         ps.setString(1, rec.getTitle());
         ps.setString(2, rec.getWriter());
         ps.setString(3, rec.getContent());
         ps.setString(4, rec.getPass());
         
   
            ps.executeUpdate();      
         
         //2
            String sql = "SELECT seq_board.CURRVAL as seq From dual ";
            ps2 = con.prepareStatement(sql);
            rs = ps2.executeQuery();
            
            if(rs.next()) {
               result = rs.getInt("seq");
            }
            
         return result;

      }catch( Exception ex ){
         throw new BoardException("게시판 ) DB에 입력시 오류  : " + ex.toString() );   
      } finally{
         if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
         if( stmt != null ) { try{ stmt.close(); } catch(SQLException ex){} }
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }
      
   }


   /************************************************
    * 함수명 : selectList
    * 역할 :   전체 레코드를 검색하는 함수
    * 인자 :   없음
    * 리턴값 : 테이블의 한 레코드를 BoardVO 지정하고 그것을 ArrayList에 추가한 값
   */

   public List<BoardVO> selectList() throws BoardException
   {
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<BoardVO> mList = new ArrayList<BoardVO>();
      boolean isEmpty = true;
      
      try{
         String url = "jdbc:oracle:thin:@192.168.0.41:1521:xe"; //이방법 고정
          String user = "scott";
          String pass = "tiger";

         con   = DriverManager.getConnection( dbUrl, dbUser, dbPass );
         
         // * sql 문장만들기
            //*전체대려오면 용량만 많음 필요한거만 가져오면됨 어차피 저장은 다됬으니
         String sql ="SELECT seq, title, writer, regdate, cnt FROM board_ex";
         // * 전송객체 얻어오기
          ps  = con.prepareStatement(sql);
         // * 전송하기
          rs = ps.executeQuery();
          
          while(rs.next()) {
             BoardVO v = new BoardVO(); //객체생성 필수
             
             v.setSeq(rs.getInt("seq"));
             v.setTitle(rs.getString("title"));
             v.setWriter(rs.getString("writer"));
             v.setRegdate(rs.getString("regdate"));
             v.setCnt(rs.getInt("cnt"));
            
         // * 결과 받아 List<BoardVO> 변수 mList에 지정하기
             mList.add(v);
             isEmpty = false;
          }
          
          
         if( isEmpty ) return Collections.emptyList();
         
         return mList;
      }catch( Exception ex ){
         throw new BoardException("게시판 ) DB에 목록 검색시 오류  : " + ex.toString() );   
      } finally{
         if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }      
   }
   
   //인자 있는 페이징 구문
   public List<BoardVO> selectList(int firstRow, int endRow) throws BoardException{
      Connection          con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<BoardVO> mList = new ArrayList<BoardVO>();
      boolean isEmpty = true;
      
      //오버로딩 위는 인자가없음 여기는 있음
      try{
         String url = "jdbc:oracle:thin:@192.168.0.41:1521:xe"; //이방법 고정
          String user = "scott";
          String pass = "tiger";
         
          con = DriverManager.getConnection(url, user, pass);
         
          //어려운 sql ㅠ
          String sql = "SELECT seq, title, writer, regdate, cnt FROM  board_ex  WHERE  seq in (SELECT seq FROM ( SELECT rownum AS rnum ,seq FROM (select rownum, seq from board_ex order by seq desc))  WHERE  rnum>=? AND rnum<=?)   ORDER BY seq DESC";
       ps  = con.prepareStatement(sql);
       
       ps.setInt(1, firstRow);
       ps.setInt(2, endRow);
      // System.out.println(firstRow + ":" + endRow );
       rs = ps.executeQuery();

         while(rs.next()) {
            BoardVO vo = new BoardVO();
            vo.setSeq(rs.getInt("seq"));
            vo.setTitle(rs.getString("title"));
            vo.setWriter(rs.getString("writer"));
            vo.setCnt(rs.getInt("cnt"));
            vo.setRegdate(rs.getString("regdate")); //추가하면 뜸 
         
            mList.add(vo);
            isEmpty = false; //while문에 한번이라도 들어오면 false
            
         }
         
         
         if( isEmpty ) return Collections.emptyList();
         
         return mList;
      }catch( Exception ex ){
         throw new BoardException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );   
      } finally{
         if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }      
   }////end of selectList - 인자있는 페이지수 넘기기용
   

   
   private BoardVO BoardVO() {
      // TODO Auto-generated method stub
      return null;
   }
   //전체 레코드 수 검색
   public int getTotalCount() throws BoardException{
      Connection          con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      int count = 0; 

      try{
         String url = "jdbc:oracle:thin:@192.168.0.41:1521:xe"; //이방법 고정
          String user = "scott";
          String pass = "tiger";
         
          con = DriverManager.getConnection(url, user, pass);
         
         
         String sql = "SELECT count(*) as CNT FROM board_ex";
         
          ps  = con.prepareStatement(sql);
          rs = ps.executeQuery();
          
          //별칭으로 만든 CNT녀석을 카운터에 담아서 리턴한다
          // 아까 리설트와같이 이런녀석들 리턴할때 멤버변수로 초기화 해놓고 집어넣고 리턴하는 
          //폼을 계속 보인다 기억하자
         if(rs.next()) {
            count =  rs.getInt("CNT"); 
         }
         

         return  count;
         
      }catch( Exception ex ){
         throw new BoardException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );   
      } finally{
         if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }         
   }
   

   //--------------------------------------------
   //#####    게시글번호에 의한 레코드(데이타) 검색하는 함수
   public BoardVO selectById(int seq) throws BoardException
   {
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      BoardVO rec = new BoardVO();
      
      try{

         con   = DriverManager.getConnection( dbUrl, dbUser, dbPass );
         // * sql 문장만들기
         String sql ="SELECT * FROM board_ex WHERE seq = ?";
         // * 전송객체 얻어오기
          ps  = con.prepareStatement(sql);
         // * 전송하기 / 받아오는건 프라이머리 ? 1개 지정한걸로 받으면되고
          ps.setInt(1, seq);

          rs = ps.executeQuery();
         // * 결과 받아 BoardVO변수 rec에 지정하기 변수선언 위에있음
             //값은 나와야하니 모두적자
          while(rs.next()) {
             rec.setSeq(rs.getInt("seq"));
                  rec.setTitle(rs.getString("title"));
                  rec.setWriter(rs.getString("writer"));
                  rec.setContent(rs.getString("content"));
                  rec.setRegdate(rs.getString("regdate"));
                  rec.setPass(rs.getString("pass"));
                  rec.setCnt(rs.getInt("cnt"));
               }
          
         return rec;
      }catch( Exception ex ){
         throw new BoardException("게시판 ) DB에 글번호에 의한 레코드 검색시 오류  : " + ex.toString() );   
      } finally{
         if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }      
   }

   //--------------------------------------------
   //#####    게시글 보여줄 때 조회수 1 증가
   public void increaseReadCount( int seq ) throws BoardException
   {
      PreparedStatement ps = null;
      try{
         String url = "jdbc:oracle:thin:@192.168.0.41:1521:xe"; //이방법 고정
          String user = "scott";
          String pass = "tiger";

         con   = DriverManager.getConnection( dbUrl, dbUser, dbPass );
         // * sql 문장만들기
             //seq 하나만 지정하는 이유는 그글의 조회수만 증가시키려고
         String sql = "UPDATE board_ex SET cnt=cnt+1 WHERE seq = ? ";
         // * 전송객체 얻어오기
          ps  = con.prepareStatement(sql);
         // * 전송하기
         ps.setInt(1, seq);
         
         ps.executeUpdate(); 
      
         ps.close();
         
      }catch( Exception ex ){
         throw new BoardException("게시판 ) 게시글 볼 때 조회수 증가시 오류  : " + ex.toString() );   
      } finally{
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }
      
   }
   //--------------------------------------------
   //#####    게시글 수정할 때
   public int update( BoardVO rec ) throws BoardException
   {

      PreparedStatement ps = null;
      try{

         con   = DriverManager.getConnection( dbUrl, dbUser, dbPass );
         // * sql 문장만들기
         String sql ="UPDATE board_ex SET title=?, content=? , regdate=sysdate WHERE seq=? AND pass =?";
         // * 전송객체 얻어오기
         ps = con.prepareStatement(sql);
         
         ps.setString(1,rec.getTitle());
         ps.setString(2,rec.getContent());
         ps.setInt(3, rec.getSeq());
         ps.setString(4,rec.getPass());
         
         return ps.executeUpdate();
      
      }catch( Exception ex ){
         throw new BoardException("게시판 ) 게시글 수정시 오류  : " + ex.toString() );   
      } finally{
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }
      
   }
   
   
   //--------------------------------------------
   //#####    게시글 삭제할 때
   public int delete( int seq, String pass ) throws BoardException
   {
      
      int result = 0;
      Connection          con = null;

      PreparedStatement ps = null;
      try{
//         String url = "jdbc:oracle:thin:@192.168.0.48:1521:xe"; //이방법 고정
//          String user = "scott";
//          String pass1 = "tiger";
          
         con   = DriverManager.getConnection( dbUrl, dbUser, dbPass );
      
         // * sql 문장만들기
         String sql = "DELETE FROM board_ex WHERE seq=? AND pass=?";
         // * 전송객체 얻어오기
         ps = con.prepareStatement(sql);
         ps.setInt(1, seq);
         ps.setString(2,pass);

         result = ps.executeUpdate(); 
         
         
         return result;
         
      }catch( Exception ex ){
         throw new BoardException("게시판 ) 게시글 수정시 오류  : " + ex.toString() );   
      } finally{
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }
      
   }
   
   
}