package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import collegamenti.model.Database;
import collegamenti.model.Message;
import collegamenti.model.User;

class MessageTest {
		private Database data;
		private User UsertoTest;
	
		@Test
	void test() {
		data= Database.getIstance();
		UsertoTest= Database.getIstance().CheckAccess("TestCoach@libero.it", "ChangedPassword1");
		
		testSendMessage();
	}

	void testSendMessage() {
		//TEST MESSAGE SEND
				String Sender= UsertoTest.getEmail();
				String Reciever= "renatomilano@libero.it";
				String Object= "Ciao";
				Message toSend= new Message(Object, Reciever, Sender);
				data.SendMessage(toSend);
				
				ArrayList<Message> expected = new ArrayList<Message>();
				expected.add(new Message(1,"Ciao","renatomilano@libero.it",UsertoTest.getEmail()));
				ArrayList<Message> actual= data.GetMessages(UsertoTest.getEmail(), "renatomilano@libero.it");
				
				assertEquals(expected.get(0).getObject(),actual.get(0).getObject());
	}
	
}
