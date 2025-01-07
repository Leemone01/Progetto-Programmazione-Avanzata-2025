/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.MyAgendaServer;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Leemo
 */

@Controller  
@RequestMapping(path="/utente")  
public class UtenteController { 

    @Autowired
    private UtenteRepository utenteRepository;

    @PostMapping(path="/registra")
    public @ResponseBody Esito registraUtente(@RequestBody Utente u){
        Esito e = new Esito();
        
        try{
            //Controllo se l'username è già in uso
            Utente uu = utenteRepository.findByUsername(u.getUsername());
            if(uu != null){
                throw new UtenteException("Username già in uso");
            }
        
            //Arrivati qui, l'utente ha inserito i dati corretti.
            //Salvo allora l'utente nel database hashando la password.
            //Per l'hashing della password è stata usata la libreria jBCrypt.
            u.setPassword(BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
            Utente utenteAggiunto = utenteRepository.save(u);
        
            //Preparo l'oggetto Esito per notificare l'utente del successo dell'operazione, 
            //inserendo anche l'ID univoco che gli è stato assegnato dal DBMS.
            e.setMessaggio(utenteAggiunto.getId().toString()); 
            e.setStatus(true);
        }
        //In caso di errore, notifico il client.
        catch(UtenteException ex){
            e.setMessaggio(ex.getMessage());
        }
        
        //Mando l'esito al client, positivo o negativo.
        return e;
    }
    
    @PostMapping(path="/accedi")
    public @ResponseBody Esito accessoUtente(@RequestBody Utente u){
        Esito e = new Esito();
        
        try{
            //Controllo se l'username esiste
            Utente uu = utenteRepository.findByUsername(u.getUsername());
            if(uu == null){
                throw new UtenteException("Username non esistente.");
            }
        
            //Controllo se la password inserita dall'utente è corretta.
            if(!BCrypt.checkpw(u.getPassword(), uu.getPassword())){
                throw new UtenteException("Password errata.");
            }
        
            //Arrivati qui, l'utente ha inserito i dati corretti.
            //Preparo allora l'oggetto Esito per notificare l'utente del successo dell'operazione, 
            //inserendo anche l'ID univoco che gli è stato assegnato dal DBMS.
            e.setStatus(true);
            e.setMessaggio(uu.getId().toString());
        }
        
        //In caso di errore, inserisco nell'oggetto Esito informazioni sull'errore che si è verificato
        catch(UtenteException ex){
            e.setMessaggio(ex.getMessage());
        }
        
        //Mando l'esito al client, positivo o negativo.
        return e;
        
    }
    
} 
