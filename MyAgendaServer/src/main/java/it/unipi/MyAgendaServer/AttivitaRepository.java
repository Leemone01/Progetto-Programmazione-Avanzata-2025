/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.MyAgendaServer;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Leemo
 */
public interface AttivitaRepository extends CrudRepository<Attivita, Integer>{
    Iterable<Attivita> findByUtente(int utente);
    Iterable<Attivita> findByUtenteAndTitoloContainingAndContenutoContaining(int utente, String titolo, String contenuto);
    Iterable<Attivita> findByUtenteAndCategoriaAndTitoloContainingAndContenutoContaining(int utente, String categoria, String titolo, String contenuto);
    Long deleteById(int id);
}
