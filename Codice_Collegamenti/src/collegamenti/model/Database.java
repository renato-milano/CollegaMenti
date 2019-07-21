/** 
 * Database

 * 
 * Classe che permette il collefamento al DBMS MySql
 * 
 * @author Renato Milano
 * @version 0.1
 * @since 8/07/19
 * 
 * 2019 Copyright by Collegamenti
 * 
 */

package collegamenti.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Database {
	
	private static Database data= null;
	
	private ResultSet rs;
	
	private Statement st;
	
	private String sql;
	
	private Connection con;
	
	private PreparedStatement ps;
	
	
	public static  Database getIstance() {
		
		if (data==null) 
			data= new Database();
		
		return data;
		
	}

	private Database() {
		try {
		  Class.forName("com.mysql.jdbc.Driver");
	      String url = "jdbc:mysql://localhost:3306/Collegamenti_IS?autoReconnect=true&useSSL=false";
	      con = DriverManager.getConnection(url,"root","admin");
		  st = con.createStatement();
		  rs = st.executeQuery("SELECT * FROM Collegamenti_IS.utente;");
			 }
		
	    catch(Exception e) {
		  e.printStackTrace();
			 }
						}
	
	//CREAZIONE TUPLA NELLA TABELA UTENTE, RESTITUISCE TRUE IN CASO DI SUCCESSO
		public boolean newUser(String email,String password,String nome,String cognome,int Tipo,
				String TitolodiStudio, String Categoria)
		{
			try
			{
			 sql=("INSERT INTO Utente (Email, Nome,Cognome, TitoloDiStudio,Categoria, Password,Tipo,MessaggiDaLeggere,Abbonato) VALUES (?, ?, ?,?, ?, ?,?,0,0);");
			 ps = con.prepareStatement(sql);
			 ps.setString(1,email);
			 ps.setString(2,nome);
			 ps.setString(3,cognome);
			 ps.setString(4,TitolodiStudio);
			 ps.setString(5,Categoria);
			 ps.setString(6, password);
			 ps.setInt(7,Tipo);
			 ps.executeUpdate();
			 return true;
		    }
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		
		//Method that provides Login by checking email and password from database
		public User CheckAccess(String email, String pass) {
			try {
				sql=("Select * from Utente where Email= ?  AND Password= ? ;");
				ps = con.prepareStatement(sql);
				ps.setString(1,email);
				ps.setString(2,pass);
				rs = ps.executeQuery();
				if(rs.next()) {
					User user= new User(email,pass,rs.getString("Nome"),rs.getString("Cognome"),rs.getInt("Tipo"),rs.getString("TitoloDiStudio"), rs.getBoolean("MessaggiDaLeggere"),
							rs.getBoolean("Abbonato"));
					user.setCompetenza(rs.getString("Categoria"));
					if(user.getTipo()==2) {
						return null;
					}
					return user;
				}
				return null;
			}
			catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public ArrayList<Thread> getRandomThreads() {
			ArrayList<Thread> Threads= new ArrayList<Thread>();
			sql= ("Select * from Thread");
			try {
				ps = con.prepareStatement(sql);
				rs= ps.executeQuery();
				while(rs.next()) {
					Thread found= new Thread(rs.getInt("idThread"),rs.getString("EmailUtente"),rs.getString("Titolo"),rs.getString("Oggetto"));
					Threads.add(found);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			
			return Threads;
			
		}
		
		public ArrayList<Thread> getCategorizedThreads(String Selection){
			ArrayList<Thread> Threads= new ArrayList<Thread>();
			sql= ("Select * from Thread where Categoria=?");
			try {
				ps = con.prepareStatement(sql);
			    ps.setString(1, Selection);
				rs= ps.executeQuery();
				while(rs.next()) {
					Thread found= new Thread(rs.getInt("idThread"),rs.getString("EmailUtente"),rs.getString("Titolo"),rs.getString("Oggetto"));
					Threads.add(found);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			
		}
			return Threads;
		}
		
		public boolean addThread(String Title, String Description,String Categ , String User) {
			try
			{
			 sql=("INSERT INTO Thread (EmailUtente,Oggetto, Titolo, Categoria) VALUES (?,?,?,?);");
			 ps = con.prepareStatement(sql);
			 ps.setString(1,User);
			 ps.setString(2,Description);
			 ps.setString(3,Title);
			 ps.setString(4,Categ);
			 ps.executeUpdate();
			 return true;
		    }
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		
		public Thread getThreadByid(int id) {
		Thread found = new Thread();
			sql= ("Select * from Thread where idThread=?");
			try {
				ps = con.prepareStatement(sql);
			    ps.setInt(1, id);
				rs= ps.executeQuery();
				found=null;
				if(rs.next()) {
					found= new Thread(rs.getInt("idThread"),rs.getString("EmailUtente"),rs.getString("Titolo"),rs.getString("Oggetto"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			
		}
			return found;
		}
		
		
		
		public boolean addThreadAnswer(String Answer,String User, int IDThread) {
			int last=0;
			try
			{
				sql=("Select * from Partecipazione where idThread= ?  group by Ordine desc;");
				ps = con.prepareStatement(sql);
				ps.setInt(1,IDThread);
				rs = ps.executeQuery();
				if(rs.next()) {
				last= rs.getInt("Ordine");
				}
				last++;
			 sql=("INSERT INTO Partecipazione (idThread,EmailUtente, Messaggio,Ordine) VALUES (?,?,?,?);");
			 ps = con.prepareStatement(sql);
			 ps.setInt(1,IDThread);
			 ps.setString(2,User);
			 ps.setString(3,Answer);
			 ps.setInt(4, last);
			 ps.executeUpdate();
			 return true;
		    }
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		
		public ArrayList<ThreadMessage> getThreadAnswers(int id){
			try {
				ArrayList<ThreadMessage> array= new ArrayList<ThreadMessage>();
				sql=("Select * from Partecipazione where idThread= ?  order by Ordine ;");
				ps = con.prepareStatement(sql);
				ps.setInt(1,id);
				rs = ps.executeQuery();
				while(rs.next()) {
					ThreadMessage found= new ThreadMessage(rs.getString("EmailUtente"),rs.getString("Messaggio"),rs.getInt("Votes"));
					array.add(found);
				}
				return array;
			}
			catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
			
		public void addVote(int i,int idThread, int Ordine) {
			try {
			sql=("Update Partecipazione set Votes=? where idThread="+idThread+" AND Ordine="+Ordine);
			ps= con.prepareStatement(sql);
			ps.setInt(1, i);
			ps.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
				}
			}
		
		public void AddSub(String Email) {
			try {
			sql=("Update Utente set Abbonato=1 where Email=?;");
			 ps = con.prepareStatement(sql);
			 ps.setString(1,Email);
			 ps.executeUpdate();
		    }
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public boolean AddRequest(String Email,String Category ,String Description) {
			try {
			sql=("INSERT INTO RichiestaCoach (EmailUtente,Categoria,Argomento) VALUES (?,?,?);");
			 ps = con.prepareStatement(sql);
			 ps.setString(1,Email);
			 ps.setString(2,Category);
			 ps.setString(3,Description);
			 ps.executeUpdate();
			 return true;
		    }
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		
		public ArrayList<StudentRequest> getStudentRequest(String Category){
			ArrayList<StudentRequest> found= new ArrayList<StudentRequest>();
			
			try {
				sql=("select * from RichiestaCoach where Categoria=?;");
				ps= con.prepareStatement(sql);
				ps.setString(1, Category);
				rs= ps.executeQuery();
				while(rs.next()) {
				found.add(new StudentRequest(rs.getInt("idRichiesta"),rs.getString("EmailUtente"),
					rs.getString("Categoria"),rs.getString("Argomento")));	
				}
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			
			return found;
		}
		
		public boolean AddStudents(String Coach, String Student) {
			try {
				sql=("Insert into Messaggio(Ordine,Destinatario,Mittente,Oggetto) values (?,?,?,?);");
				ps= con.prepareStatement(sql);
				ps.setInt(1,1);
				ps.setString(2, Student);
				ps.setString(3, Coach);
				ps.setString(4, "Ciao, Come posso Aiutarti?");
				ps.executeUpdate();
				return true;
				
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public void RemoveStudentRequest(int id) {
			try {
				ps.execute("delete from RichiestaCoach where idRichiesta="+id+";");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		public User getUserbyEmail(String email) {
			sql=("select* from Utente where Email=?");
			try {
			ps= con.prepareStatement(sql);
			ps.setString(1, email);
			rs= ps.executeQuery();
			if(rs.next()) {
				User user= new User(rs.getString("Email"),rs.getString("Password"),rs.getString("Nome"),rs.getString("Cognome"),rs.getInt("Tipo"),rs.getString("TitoloDiStudio"), rs.getBoolean("MessaggiDaLeggere"),
						rs.getBoolean("Abbonato"));
				user.setCompetenza(rs.getString("Categoria"));
				return user;
			}
			return null;
			
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
		public void SendMessage(Message toSend) {
			int last1=0;
			int last2=0;
			sql=("select Ordine from Messaggio where Mittente=? AND Destinatario=? order by Ordine desc;");
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, toSend.getSender());
				ps.setString(2, toSend.getReciever());
				rs= ps.executeQuery();
				if(rs.next()) {
					last1= rs.getInt("Ordine");
				}
				sql=("select Ordine from Messaggio where Mittente=? AND Destinatario=? order by Ordine desc;");
					ps = con.prepareStatement(sql);
					ps.setString(1, toSend.getReciever());
					ps.setString(2, toSend.getSender());
					rs= ps.executeQuery();
					if(rs.next()) {
						last2= rs.getInt("Ordine");
					}
				int max= Math.max(last1, last2);
				max++;
				sql=("Insert into Messaggio(Ordine,Destinatario,Mittente,Oggetto) Values(?,?,?,?);");
				ps= con.prepareStatement(sql);
				ps.setInt(1, max);
				ps.setString(2, toSend.getReciever());
				ps.setString(3, toSend.getSender());
				ps.setString(4, toSend.getObject());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		public ArrayList<User> getAssociated(String User1){
			ArrayList<String> emails= new ArrayList<String>();
			ArrayList<User> users= new ArrayList<User>();

			sql=("select * from Messaggio where Mittente=? group by Destinatario;");
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, User1);
				rs= ps.executeQuery();
				while(rs.next()) {
					emails.add(rs.getString("Destinatario"));
				}
				sql=("select * from Messaggio where Destinatario=? group by Mittente;");
					ps = con.prepareStatement(sql);
					ps.setString(1, User1);
					rs= ps.executeQuery();
					while(rs.next()) {
						emails.add(rs.getString("Mittente"));
					}
					
					Set<String> set = new HashSet<>(emails);
					emails.clear();
					emails.addAll(set);
					for(String s: emails) {
					users.add(getUserbyEmail(s));
					}
					return users;
					
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		}
		
		public ArrayList<Message> GetMessages(String User1, String User2){
			ArrayList<Message> Messages= new ArrayList<Message>();
			sql=("select * from Messaggio where Mittente=? AND Destinatario=?;");
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, User1);
				ps.setString(2, User2);
				rs= ps.executeQuery();
				while(rs.next()) {
					Messages.add(new Message(rs.getInt("Ordine"),rs.getString("Oggetto"),
							rs.getString("Destinatario"),rs.getString("Mittente")));
				}
				sql=("select * from Messaggio where Mittente=? AND Destinatario=?;");
					ps = con.prepareStatement(sql);
					ps.setString(1, User2);
					ps.setString(2, User1);
					rs= ps.executeQuery();
					while(rs.next()) {
						Messages.add(new Message(rs.getInt("Ordine"),rs.getString("Oggetto"),
								rs.getString("Destinatario"),rs.getString("Mittente")));
					}
					//ArrayList<Message> MessagesOrdered= new ArrayList<Message>();
					//for(int i=0;i<Messages.size();i++) {
						//MessagesOrdered.add();
					//}

					Collections.sort(Messages);
					return Messages;
					
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		}
		
		public boolean RemoveUser(String Email) {
			sql=("delete from Utente where Email=?");
			try {
				ps= con.prepareStatement(sql);
				ps.setString(1, Email);
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		
		public User ChangePassword(String Email,String Pass) {
			try {
				sql=("Update Utente set Password=? where Email=?;");
				 ps = con.prepareStatement(sql);
				 ps.setString(1,Pass);
				 ps.setString(2, Email);
				 ps.executeUpdate();
				  return this.CheckAccess(Email, Pass);
			}
			
			
				catch(Exception e){
					e.printStackTrace();
					return null;
				}
		}
		
		public ArrayList<User> getRegisterRequest(){
			ArrayList<User> array= new ArrayList<User>();
			sql=("select* from Utente where Tipo=2");
			try {
			ps= con.prepareStatement(sql);
			rs= ps.executeQuery();
			while(rs.next()) {
				User user= new User(rs.getString("Email"),rs.getString("Password"),rs.getString("Nome"),rs.getString("Cognome"),rs.getInt("Tipo"),rs.getString("TitoloDiStudio"), rs.getBoolean("MessaggiDaLeggere"),
						rs.getBoolean("Abbonato"));
				user.setCompetenza(rs.getString("Categoria"));
				array.add(user);
			}
			return array;
			
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
		
		public void ValidateCoach(String Email) {
			try {
				sql=("Update Utente set Tipo=1 where Email=?");
				ps=con.prepareStatement(sql);
				ps.setString(1, Email);
				ps.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		public ArrayList<User> getCoachs(String Email) {
			ArrayList<User> array= new ArrayList<User>();
			ArrayList<String> emails= new ArrayList<String>();
			sql=("select Mittente from Messaggio where Destinatario=? and Ordine=1");
			try {
				ps= con.prepareStatement(sql);
				ps.setString(1, Email);
				rs= ps.executeQuery();
				while(rs.next()) {
					emails.add(rs.getString("Mittente"));
				}
				for(String s: emails) {
					array.add(getUserbyEmail(s));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return array;
		}
		public ArrayList<User> getStudents(String Email) {
			ArrayList<User> array= new ArrayList<User>();
			ArrayList<String> emails= new ArrayList<String>();
			sql=("select Destinatario from Messaggio where Mittente=? and Ordine=1");
			try {
				ps= con.prepareStatement(sql);
				ps.setString(1, Email);
				rs= ps.executeQuery();
				while(rs.next()) {
					emails.add(rs.getString("Destinatario"));
				}
				for(String s: emails) {
					array.add(getUserbyEmail(s));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return array;
		}
		public void DeleteThread(int ID) {
			sql=("delete from Thread where idThread=?");
			try {
				ps= con.prepareStatement(sql);
				ps.setInt(1, ID);
				ps.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		}
		

