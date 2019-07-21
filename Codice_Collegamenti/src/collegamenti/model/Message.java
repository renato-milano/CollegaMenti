package collegamenti.model;

/** 
 * Message
 * 
 * Classe che descrive un messaggio salvato dal sistema
 * 
 * @author Ciro Valerio Cerchia
 * @version 0.1
 * @since 10/06/19
 * 
 * 2019 Copyright by Collegamenti
 * 
 */
public class Message implements Comparable<Message> {

	private String Object;
	private String Reciever;
	private String Sender;
	private Integer Order;
	
	public Message(Integer Order,String Object,String Reciever, String Sender) {
		
		this.Object=Object;
		this.Reciever= Reciever;
		this.Sender=Sender;
		this.Order=Order;
		
	}
	
	public Message(String Object,String Reciever, String Sender) {
		this.Object=Object;
		this.Reciever= Reciever;
		this.Sender=Sender;
	}

	public String getObject() {
		return Object;
	}

	public void setObject(String object) {
		Object = object;
	}

	public String getReciever() {
		return Reciever;
	}

	public void setReciever(String reciever) {
		Reciever = reciever;
	}

	public String getSender() {
		return Sender;
	}

	public void setSender(String sender) {
		Sender = sender;
	}

	public Integer getOrder() {
		return Order;
	}

	public void setOrder(int order) {
		Order = order;
	}

	@Override
	public int compareTo(Message arg0) {
		return this.getOrder().compareTo(arg0.getOrder());
	}
}
