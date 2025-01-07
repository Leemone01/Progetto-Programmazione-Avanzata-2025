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
//Classe che rappresenta la tabella "utente" del database.
@Entity
@Table(name="utente", schema="615600")
public class Utente implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) //lascio sia il DB a gestire l'indice autoincrementante
    private Integer id;  
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Utente(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Utente() {
    }
}
