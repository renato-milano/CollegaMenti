package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import collegamenti.model.Database;
import collegamenti.model.User;

class AuthenticationTest {

	@Test
	void test() {
		//LOGIN TEST
		User UsertoTest= Database.getIstance().CheckAccess("TestCoach@libero.it", "ChangedPassword1");			
		assertEquals("Renato", UsertoTest.getNome());
		assertEquals("Milano", UsertoTest.getCognome());
		assertEquals("Passtest1", UsertoTest.getPassword());
		assertEquals(null, UsertoTest.getCompetenza());
		assertEquals("TestCoach@libero.it", UsertoTest.getEmail());
		assertEquals(null, UsertoTest.getTitolodiStudio());
		assertEquals(1, UsertoTest.getTipo());
		assertEquals(false, UsertoTest.isAbbonato());
		assertEquals(false, UsertoTest.isMessaggiDaLeggere());
		
	
			
	}

}
