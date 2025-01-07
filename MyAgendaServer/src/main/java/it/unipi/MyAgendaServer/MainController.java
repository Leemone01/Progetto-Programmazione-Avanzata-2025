/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.MyAgendaServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Leemo
 */

@Controller  
@RequestMapping(path="/home")  
public class MainController { 
    
    //Inietto "database" con il riferimento all'oggetto di tipo Database creato a runtime.
    @Autowired
    private Database database;

    @GetMapping(path="/caricadati") 
    public @ResponseBody Esito caricaDati() {
        Esito e = new Esito();
        
        try{
            //Inizializzo il DB.
            database.inizializzaDatabase();
            //In caso di successo, notifico l'utente.
            e.setStatus(true);
        }
        catch(DataAccessException ex){
            e.setMessaggio(ex.getMessage());
        }
        
        return e;
    }  
} 
