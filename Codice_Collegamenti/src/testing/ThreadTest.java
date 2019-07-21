package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import collegamenti.model.Database;
import collegamenti.model.Thread;
import collegamenti.model.ThreadMessage;

class ThreadTest {

	@Test
	void test() {
		// TEST ADDING A NEW THREAD
		String Creator="TestCoach@libero.it";
		String Title= "Titolo";
		String Description= "Descrizione";
		String Category= "CategSelection";
		boolean check= Database.getIstance().addThread(Title,Description,Category,Creator);
		assertEquals(true,check);
		
		//TEST SELCTING NEW THREAD
		Thread thread= new Thread(4,Creator,Title,Description);
		Thread actual= Database.getIstance().getThreadByid(4);
		assertEquals(thread.getOggetto(),actual.getOggetto());
	
		//TEST ADDING A THREAD ANSWER
		Database.getIstance().addThreadAnswer("TestRisposta", Creator, 4);
		ArrayList<ThreadMessage> Answers= Database.getIstance().getThreadAnswers(4);
		ThreadMessage toTest= new ThreadMessage(Creator,"TestRisposta",0);
		assertEquals(Answers.get(0).getMessaggio(),toTest.getMessaggio());
		
		//TEST VOTE AN ANSWER
		int i = Answers.get(0).getVotes();
		Database.getIstance().addVote(1, 4, 1);
		Answers= Database.getIstance().getThreadAnswers(4);
		assertEquals(i+1,Answers.get(0).getVotes());
		
		//TEST CLOSING A THREAD
		Database.getIstance().DeleteThread(4);
		actual= Database.getIstance().getThreadByid(4);
		assertEquals(null,actual);
		
		
		
		
	}

}
