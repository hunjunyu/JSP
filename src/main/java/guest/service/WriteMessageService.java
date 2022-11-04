package guest.service;

import guest.model.Message;
import guest.model.MessageDao;
import guest.model.MessageException;

public class WriteMessageService {

	private static WriteMessageService instance;//instance 에 WriteMessageService를 담는다
	
	public static WriteMessageService	getInstance() throws MessageException
	{//getInstance함수에 WriteMessageService를 생성하는 함수를 넣고 예외처리한다
		if( instance == null )
		{
			instance = new WriteMessageService();
		}
		return instance;
	}
	
	private WriteMessageService(){}//WriteMessageService의 기본 생성자 method생성을 위해 꼭 생성해야한다
	
	public void write( Message rec ) throws MessageException
	{//message.java를 상속받는다
		MessageDao mDao = MessageDao.getInstance();//MessageDao의 함수생성을 mDao에 넣는다
		mDao.insert(rec);//Message의 입력한 값들을 mDao를 거쳐 db에 넣는구문
	
	}
}
