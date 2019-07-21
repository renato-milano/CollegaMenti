/** 
 * MessaggioThread
 * 
 * Classe che descrive un messaggio inserito in un Thread.
 * 
 * @author Ciro Valerio Cerchia
 * @version 0.1
 * @since 22/03/19
 * 
 * 2019 Copyright by Collegamenti
 * 
 */


package collegamenti.model;

public class ThreadMessage {
	
	private String Autore;
	private String Messaggio;
	private int Votes;
	
	
	public ThreadMessage(String autore, String messaggio,int Votes) {
	  Autore = autore;
	  this.Messaggio = messaggio;
	  this.Votes=Votes;
		
		
	}
	
	
	public int getVotes() {
		return Votes;
	}


	public void setVotes(int votes) {
		Votes = votes;
	}


	public String getAutore() {
	  return Autore;
	}
	
	public void setAutore(String autore) {
	  Autore = autore;
	}
	
	public String getMessaggio() {
	  return Messaggio;
	}
	
	public void setMessaggio(String messaggio) {
	  Messaggio = messaggio;
	}
	
	

}
