package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;

import collegamenti.model.Database;
import collegamenti.model.User;

class RegisterTest {

	@Test
	void test() {
		// CREATE NUOVO ACOUNT COACH TEST
		String email= "TestCoach@libero.it";
		String name= "Renato";
		String surname= "Milano";
		String qualification= null;
		String password = "Passtest1";
		int type= 2;
		String category= null;
		boolean check= Database.getIstance().newUser(email, password, name, surname, type, qualification, category);
		assertEquals(true, check);
		User UsertoTest= Database.getIstance().CheckAccess("TestCoach@libero.it", "Passtest1");
		assertEquals(null,UsertoTest);
		// CONVALIDA ACCOUNT COACH TEST
		Database.getIstance().ValidateCoach(email);
		
		assertEquals(true,check);
		
		//LOGIN TEST
		UsertoTest= Database.getIstance().CheckAccess("TestCoach@libero.it", "Passtest1");
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
