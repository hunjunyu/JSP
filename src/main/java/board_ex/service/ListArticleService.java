package board_ex.service;

import java.util.List;

import board_ex.model.*;

public class ListArticleService {
   private int totalRecCount;      // 전체 레코드 수   
   private int pageTotalCount;      // 전체 페이지 수
   private int countPerPage = 3;   // 한페이지당 레코드 수

   
   private static ListArticleService instance;
   public static ListArticleService getInstance()  throws BoardException{
      if( instance == null )
      {
         instance = new ListArticleService();
      }
      return instance;
   }
   
   public List <BoardVO> getArticleList() throws BoardException
   {
       List <BoardVO> mList = BoardDao.getInstance().selectList();         
      return mList;
   }
   
   public List <BoardVO> getArticleList(String pNum) throws BoardException

   {
	   int pageNum = 1; //겂이 없을때 1 이 되야하니까 초기화 역활

      //맨처음 킅릭이없으니 널이지 널이아니라면은 클릭이있었다는 것
      if(pNum !=null) { 
         pageNum = Integer.parseInt(pNum);
      }
      /*
       * 페이지번호    시작레코드    끝레코드
       *    1            1         3
       *    2            4         6
       * 	3            7         9
       * 
       * */

      // 3*3 9 -2 = 7 이니까 예시
      int startRow = countPerPage*pageNum-2;
      int endRow = pageNum * countPerPage;


      //지금 계산이된 변수들을 selectList에 인자로 넣어주고  selectList 함수중 이제 인자를 받는 함수란으로 넘어가 sql을 만들자
      List <BoardVO> mList = BoardDao.getInstance().selectList(startRow, endRow);         
      return mList;
   }
   

   public int getTotalPage() throws BoardException {

      //서비스가 필요한 이유가 이런 계산식 함수들 불러와서 쓰는데 그계산식들 만드는 란으로 쓰려고

      //전체 레코드 데이타의 수
      totalRecCount = BoardDao.getInstance().getTotalCount();
      //System.out.println("totalRecCount:"+totalRecCount);
      //만약 레코드수 20개면
      //위에 한페이지당 3이라 정해놨음 
      // 그럼 7페이지 일까?
      //전체레코드수 10 이면 페이지수 4페이지
      // 전체레코드수가 9 면 페이지수 3
      // 11 이면 4페이지
      // 12면 4페이지
      // 13이면 5페이지겠지 이렇게 되도록 계산을 해야겠지?


      //pageTotalCount = (int)Math.ceil(totalRecCount/(double)countPerPage); //더블해줘야 나머지생략안뜨고 잘나옴
      //System.out.println("pageTotalCount:"+pageTotalCount);

      pageTotalCount = totalRecCount/countPerPage;
      if( totalRecCount%countPerPage >0) pageTotalCount++;

      //페이지 수 리턴
      return pageTotalCount;
   }



   
      
}
