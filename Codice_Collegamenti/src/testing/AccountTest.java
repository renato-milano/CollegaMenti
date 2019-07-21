package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import collegamenti.model.Database;
import collegamenti.model.StudentRequest;
import collegamenti.model.User;

class AccountTest {

	@Test
	void test() {
		Database data= Database.getIstance();
		User UsertoTest= Database.getIstance().CheckAccess("TestCoach@libero.it", "Passtest1");
		
		//TEST SUBSCRIPTION
		data.AddSub(UsertoTest.getEmail());
		UsertoTest= Database.getIstance().CheckAccess("TestCoach@libero.it", "Passtest1");
		assertEquals(true,UsertoTest.isAbbonato());
		
		//TEST CHANGE PASSWORD
		data.ChangePassword("TestCoach@libero.it", "ChangedPassword1");
		UsertoTest= Database.getIstance().CheckAccess("TestCoach@libero.it", "ChangedPassword1");
		assertNotNull(UsertoTest);
		
		//TEST STUDENT REQUEST A COACH
		data.AddRequest(UsertoTest.getEmail(), "Italiano", "Aggettivi e Sostantivi");
		ArrayList<StudentRequest> expected= new ArrayList<StudentRequest>();
		expected.add(new StudentRequest(1,UsertoTest.getEmail(),"Italiano","Aggettivi e Sostantivi"));
		ArrayList<StudentRequest> localRequest= 
				data.getStudentRequest("Italiano");
		assertEquals(expected.get(0).getEmail(), localRequest.get(0).getEmail());
		
		//TEST COACH ACCEPT A STUDENT
		data.AddStudents(UsertoTest.getEmail(), "TestStudente@libero.it");
		
		//TEST DELETE ACCOUNT 
		//data.RemoveUser(UsertoTest.getEmail());
		//UsertoTest= Database.getIstance().CheckAccess("TestCoach@libero.it", "ChangedPassword1");
		//assertEquals(null, UsertoTest);
		}

}
