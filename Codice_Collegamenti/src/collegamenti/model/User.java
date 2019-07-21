/** 
 * User
 * 
 * Classe che descrive l'utente registrato al sistema
 * 
 * @author Ciro Valerio Cerchia
 * @version 0.1
 * @since 10/06/19
 * 
 * 2019 Copyright by Collegamenti
 * 
 */


package collegamenti.model;

import java.util.ArrayList;

public class User {

	private String email;
	private String password;
	private String nome;
	private String cognome;
	private String TitolodiStudio;
	private int Tipo;
	private boolean MessaggiDaLeggere;
	private boolean Abbonato;
	private ArrayList<User> Associati;
	private String Competenza;
	
	
	
	
	
	
	public ArrayList<User> getAssociati() {
		return Associati;
	}


	public void setAssociati(ArrayList<User> associati) {
		Associati = associati;
	}


	public String getCompetenza() {
		return Competenza;
	}


	public void setCompetenza(String competenza) {
		Competenza = competenza;
	}


	public User(String email, String password, String nome, String cognome,int tipo, String titolodiStudio, boolean MessaggiDaLeggere , boolean Abbonato ) {
	  this.email = email;
	  this.password = password;
	  this.nome = nome;
	  this.cognome = cognome;
	  this.TitolodiStudio = titolodiStudio;
	  this.Tipo = tipo; //Tipo = 0 if coach / Tipo = 1 if Student
	  this.Abbonato=Abbonato;
	  this.MessaggiDaLeggere = MessaggiDaLeggere;
	}
	
	
	public boolean isMessaggiDaLeggere() {
		return MessaggiDaLeggere;
	}


	public void setMessaggiDaLeggere(boolean messaggiDaLeggere) {
		MessaggiDaLeggere = messaggiDaLeggere;
	}


	public boolean isAbbonato() {
		return Abbonato;
	}


	public void setAbbonato(boolean abbonato) {
		Abbonato = abbonato;
	}


	public String getEmail() {
	  return email;
	}
	
	public void setEmail(String email) {
	  this.email = email;
	}
	
	public String getPassword() {
	  return password;
	}
	
	public void setPassword(String password) {
	  this.password = password;
	}
	
	public String getNome() {
	  return nome;
	}
	
	public void setNome(String nome) {
	  this.nome = nome;
	}
	
	public String getCognome() {
	  return cognome;
	}
	
	public void setCognome(String cognome) {
	  this.cognome = cognome;
	}
	
	public String getTitolodiStudio() {
	  return TitolodiStudio;
	}
	
	public void setTitolodiStudio(String titolodiStudio) {
	  TitolodiStudio = titolodiStudio;
	}
	
	public int getTipo() {
      return Tipo;
	}
	
	public void setTipo(int tipo) {
	  Tipo = tipo;
	}
	
	
}
