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

//Classe che rappresenta l'esito di un'operazione richiesta al Server
public class Esito implements Serializable{
    public boolean status;
    public String messaggio;
    
    public Esito(boolean status, String messaggio) {
        this.status = status;
        this.messaggio = messaggio;
    }
    
}
