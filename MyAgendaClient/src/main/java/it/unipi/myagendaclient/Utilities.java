/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Leemo
 */

//Classe contenente metodi di utilità
public class Utilities {
    //Autentica l'utente avente l'username e password passati come parametri, mandandoli all'URL passato come parametro
    //(diverso a seconda che si tratti di un accesso o una registrazione).
    //Restituisce l'ID univoco dell'utente ricevuto dal server.
    public static int autenticaUtente(String username, String password, String urlString) throws IOException, UtenteException{
        //Controllo che l'utente abbia inserito tutti i campi (uso isBlank() per verificare anche che l'utente non abbia messo solo spazi)
        if(username.isBlank() || password.isBlank()){
            throw new UtenteException("Inserisci tutti i campi");
        }

        //Creo un oggetto Utente contenente username e password inseriti dall'utente,
        //e lo invio al server in una richiesta POST all'URL "urlString"
        Utente u = new Utente(username, password);
        Esito e = HTTPConnector.postRequest(urlString, u);

        //Controllo l'esito dell'operazione
        if(!e.status){
            throw new UtenteException(e.messaggio);
        }
        
        //In caso di successo, restituisco l'ID univoco ricevuto dal server.
        return Integer.parseInt(e.messaggio);
        
    }
    
    //Rimuove l'attività "a" dal database
    public static void rimuoviAttivita(Attivita a) throws IOException, AttivitaException{
        //Controllo se l'utente ha selezionato un'attività da rimuovere.
        if(a == null){
            throw new AttivitaException("Nessuna attività selezionata");
        }
            
        //Invio al server una richiesta DELETE per eliminare l'attività selezionata.
        Integer idAttivita = a.getId();
        String urlString = "http://localhost:8080/agenda/rimuoviattivita?id=" + URLEncoder.encode(idAttivita.toString(), StandardCharsets.UTF_8);
        Esito e = HTTPConnector.deleteRequest(urlString, Esito.class);
            
        //Controllo l'esito dell'eliminazione
        if(!e.status){
            throw new AttivitaException(e.messaggio);
        }
    }
    
    //Aggiunge, per l'utente avente l'ID "utente", l'attività avente il titolo, la categoria e il contenuto passati come parametro
    public static void aggiungiAttivita(int utente, String titolo, String categoria, String contenuto) throws IOException, AttivitaException{
        //Controllo che l'utente abbia inserito il titolo e selezionato la categoria.
        if(titolo.isBlank() || categoria == null){
            throw new AttivitaException("Inserisci il titolo e seleziona la categoria.");
        }

        //Creo un oggetto Attivita per l'utente avente l'ID "utente" con titolo, categoria e contenuto passati come parametro
        //e lo invio al server in una richiesta POST all'URL "urlString".
        String urlString = "http://localhost:8080/agenda/inserimento/inserisciattivita";
        Attivita a = new Attivita(utente, titolo, categoria, contenuto);
        Esito e = HTTPConnector.postRequest(urlString, a);
        
        //Controllo l'esito dell'inserimento.
        if(!e.status){
            throw new AttivitaException(e.messaggio);
        }
    }
    
    public static Categoria[] recuperaCategorie() throws IOException{
        //Effettuo una richiesta GET per recuperare le categorie
        String urlString = "http://localhost:8080/agenda/inserimento/caricacategorie";
        return HTTPConnector.getRequest(urlString, Categoria[].class);
    }
}
