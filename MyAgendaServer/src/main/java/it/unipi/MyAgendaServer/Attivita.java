/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.MyAgendaServer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Leemo
 */

//Classe che rappresenta la tabella "attivita" del database.
@Entity
@Table(name="attivita", schema="615600")
public class Attivita implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) //lascio sia il DB a gestire l'indice autoincrementante
    private Integer id;  
    
    @Column(name = "utente")
    private Integer utente;
    
    @Column(name = "titolo")
    private String titolo;
    
    @Column(name = "categoria")
    private String categoria;
    
    @Column(name = "contenuto")
    private String contenuto;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUtente(Integer utente) {
        this.utente = utente;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public Attivita(Integer id, Integer utente, String titolo, String categoria, String contenuto) {
        this.id = id;
        this.utente = utente;
        this.titolo = titolo;
        this.categoria = categoria;
        this.contenuto = contenuto;
    }

    public Attivita() {
    }
    
}
