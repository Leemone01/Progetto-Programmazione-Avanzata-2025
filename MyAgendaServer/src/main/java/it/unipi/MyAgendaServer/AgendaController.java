/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.MyAgendaServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Leemo
 */

@Controller  
@RequestMapping(path="/agenda")  
public class AgendaController {
    @Autowired
    private AttivitaRepository attivitaRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @GetMapping(path="/caricaattivita") 
    public @ResponseBody Iterable<Attivita> caricaAttivita(@RequestParam int utente, 
            @RequestParam(required = false, defaultValue = "") String titolo, 
            @RequestParam(required = false) String categoria, 
            @RequestParam(required = false, defaultValue = "") String contenuto) {
        //Almeno un parametro tra titolo e contenuto sarà una stringa vuota. Tuttavia, effettuo comunque la ricerca per titolo e contenuto,
        //dato che che la colonna corrispondente al parametro vuoto verrebbe analizzata con una clausola "LIKE '%param%'" ==> "LIKE '%%'",
        //ed essendo ogni cella di questa colonna sicuramente NOT NULL (anche nel caso di contenuto vuoto, dato che JavaFX memorizza il testo di un campo vuoto 
        //come una stringa vuota, e per coerenza anche nel DB lo memorizzo come una stringa vuota), questa clausola non avrà effetto.
        //Se invece il parametro categoria fosse una stringa vuota, non effettuo la ricerca per categoria, 
        //altrimenti la colonna corrispondente a questo parametro verrebbe analizzata con una clausola "= categoria" ==> "= ''" 
        //e quindi la query non restituirebbe alcuna attività. 
        
        //Restituisco le attività che soddisfano i parametri.
        if(categoria == null){
            return attivitaRepository.findByUtenteAndTitoloContainingAndContenutoContaining(utente, titolo, contenuto);
        }
        return attivitaRepository.findByUtenteAndCategoriaAndTitoloContainingAndContenutoContaining(utente, categoria, titolo, contenuto);
    }
    
    @DeleteMapping(path="/rimuoviattivita")
    public @ResponseBody Esito rimuoviAttivita(@RequestParam int id) {
        Esito e = new Esito();
        
        //Rimuovo dal DB l'attività avente l'id passato come parametro, e notifico l'utente.
        attivitaRepository.deleteById(id);
        e.setStatus(true);
        return e;
    }
    
    @GetMapping(path="/inserimento/caricacategorie")
    public @ResponseBody Iterable<Categoria> caricaCategorie() {
        //Restituisco tutte le categorie.
        return categoriaRepository.findAll();
    }
    
    @PostMapping(path="/inserimento/inserisciattivita")
    public @ResponseBody Esito inserisciAttivita(@RequestBody Attivita a){
        Esito e = new Esito();
        
        //Inserisco nel DB l'attività passata come parametro, e notifico l'utente.
        attivitaRepository.save(a);
        e.setStatus(true);
        return e;
    }
}
