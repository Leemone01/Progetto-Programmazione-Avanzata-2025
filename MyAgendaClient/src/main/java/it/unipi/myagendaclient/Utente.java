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

//Classe usata per serializzare username e password forniti dall'utente in fase di registrazione o di accesso
public class Utente implements Serializable{
    public String username;
    public String password;
    
    public Utente(String username, String password){
        this.username = username;
        this.password = password;
    }
}
