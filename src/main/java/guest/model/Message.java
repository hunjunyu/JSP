package guest.model;

public class Message {
	private int messageId;
	private String guestName;
	private String password;
	private String message;
	
	public Message() {}

	public Message(int messageId, String geustName, String password, String message) {
		super();
		this.messageId = messageId;
		this.guestName = geustName;
		this.password = password;
		this.message = message;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String geustName) {
		this.guestName = geustName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
