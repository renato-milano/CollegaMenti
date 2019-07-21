/** 
 * Thread
 * 
 * Classe che descrive un Thread nel forum del sistema.
 * 
 * @author Ciro Cerchia Valerio
 * @version 0.1
 * @since 22/03/19
 * 
 * 2019 Copyright by Collegamenti
 * 
 */

package collegamenti.model;

import java.util.ArrayList;

public class Thread {

	private String Creatore;
	private String Titolo;
	private String Oggetto;
	
	private int ID;
	
	private ArrayList<ThreadMessage> Messaggi;
	
	public Thread(int ID,String Creatore, String Titolo, String Oggetto) {
		
		this.ID = ID;
		this.Creatore = Creatore;
		this.Titolo = Titolo;
		this. Oggetto = Oggetto;
		
	}

	public Thread() {
		// TODO Auto-generated constructor stub
	}

	public String getCreatore() {
		return Creatore;
	}

	public void setCreatore(String creatore) {
		Creatore = creatore;
	}

	public String getTitolo() {
		return Titolo;
	}

	public void setTitolo(String titolo) {
		Titolo = titolo;
	}

	public String getOggetto() {
		return Oggetto;
	}

	public void setOggetto(String oggetto) {
		Oggetto = oggetto;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public ArrayList<ThreadMessage> getMessaggi() {
		return Messaggi;
	}

	public void setMessaggi(ArrayList<ThreadMessage> messaggi) {
		Messaggi = messaggi;
	}
	
}
