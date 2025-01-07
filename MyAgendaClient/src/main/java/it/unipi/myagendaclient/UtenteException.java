/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

/**
 *
 * @author Leemo
 */
//Eccezione che viene lanciata in caso di errore nella registrazione/accesso
public class UtenteException extends Exception{
    public UtenteException(){
        super();
    }
    
    public UtenteException(String s){
        super(s);
    }
}
