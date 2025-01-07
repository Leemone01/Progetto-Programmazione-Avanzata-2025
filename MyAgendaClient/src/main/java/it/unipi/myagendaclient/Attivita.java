/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

import java.io.Serializable;

/**
 *
 * @author Leemo
 */
//Classe che rappresenta un'attività dell'agenda, da mostrare nella tabella. 
public class Attivita implements Serializable{
    public Integer id; 
    public Integer utente; 
    public String titolo; 
    public String categoria; 
    public String contenuto; 

    public Integer getId() {
        return id;
    }

    public Integer getUtente() {
        return utente;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getContenuto() {
        return contenuto;
    }

    public Attivita(Integer id, Integer utente, String titolo, String categoria, String contenuto) {
        this.id = id;
        this.utente = utente;
        this.titolo = titolo;
        this.categoria = categoria;
        this.contenuto = contenuto;
    }
    
    //Costruttore senza l'ID, invocato quando si vorrà inserire un'attività (essendo l'ID scelto dal DB, e quindi non ancora noto)
    public Attivita(Integer utente, String titolo, String categoria, String contenuto) {
        this.utente = utente;
        this.titolo = titolo;
        this.categoria = categoria;
        this.contenuto = contenuto;
    }
    
    
}
