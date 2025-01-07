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
public interface UtenteRepository extends CrudRepository<Utente, Integer>{
    Utente findByUsername(String n);
}
