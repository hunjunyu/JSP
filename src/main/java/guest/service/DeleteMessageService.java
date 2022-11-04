package guest.service;

import guest.model.Message;
import guest.model.MessageDao;
import guest.model.MessageException;

public class DeleteMessageService {

	private static DeleteMessageService instance;
	//instance에 DeleteMessageService값 넣기
	public static DeleteMessageService	getInstance() throws MessageException
	{//DeleteMessageService 객체 생성 함수
		if( instance == null )
		{
			instance = new DeleteMessageService();
		}
		return instance;
	}
	
	private DeleteMessageService(){}
	
	public int delete( String messageId, String password ) throws MessageException
	{//아이디와 패스워드 값을 받아서 작동하는 함수
		int mId = 0;
		if( messageId != null) mId = Integer.parseInt(messageId);//아이디를 int형으로 형변환한 후 mId 변수에 넣는다
		MessageDao mDao = MessageDao.getInstance();//MessageDao의 객체를 생성한후 mDao 변수에 넣는다
		return mDao.delete(mId, password);//MessageDao의 delete함수에 아이디와 패스워드값을 인풋한다.
		
	}
}
