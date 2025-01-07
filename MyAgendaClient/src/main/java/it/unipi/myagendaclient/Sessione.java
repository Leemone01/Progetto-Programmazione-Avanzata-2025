/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

/**
 *
 * @author Leemo
 */
//Classe realizzata seguendo il Singleton pattern per mantenere dettagli sulla sessione dell'utente corrente.
//I metodi non sono synchronized, in quanto l'unico thread ad accedere a questa classe è il JavaFX thread,
//e non i Task.
public class Sessione {
    private static Sessione sessioneCorrente;
    
    //ID dell'utente. Servirà quando l'utente corrente vorrà inserire o cercare attività.
    private int ID;
    //Username dell'utente. Verrà mostrato in ogni schermata dell'applicazione dopo che l'utente avrà fatto l'accesso.
    private String username;
    
    //Ridefinisco il costruttore senza argomenti per renderlo privato
    private Sessione(){
    }
    
    public static Sessione getInstance(){
        if(sessioneCorrente == null){
            sessioneCorrente = new Sessione();
        }
        return sessioneCorrente;
    }
    
    public static void setInstance(int ID, String username){
        if(sessioneCorrente == null){
            sessioneCorrente = new Sessione();
        }
        sessioneCorrente.ID = ID;
        sessioneCorrente.username = username;
    }
    
    public int getID(){
        return ID;
    }
    
    public String getUsername(){
        return username;
    }
}
