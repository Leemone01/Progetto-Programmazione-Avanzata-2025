/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.MyAgendaServer;

import java.io.Serializable;

/**
 *
 * @author Leemo
 */

//Classe che rappresenta l'esito dell'operazione richiesta dal client 
public class Esito implements Serializable{
    private boolean status;
    private String messaggio;

    public boolean getStatus() {
        return status;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public Esito(boolean status, String messaggio) {
        this.status = status;
        this.messaggio = messaggio;
    }
    
    //All'inizio, l'esito è negativo.
    public Esito() {                   
        this.status = false;
    }

}
