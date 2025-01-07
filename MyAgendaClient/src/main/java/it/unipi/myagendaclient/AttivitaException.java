/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

/**
 *
 * @author Leemo
 */
//Eccezione che viene lanciata in caso di errore nell'inserimento/ricerca/eliminazione di attività.
public class AttivitaException extends Exception{
    public AttivitaException(){
        super();
    }
    
    public AttivitaException(String s){
        super(s);
    }
}