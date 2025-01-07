/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

/**
 *
 * @author Leemo
 */
//Eccezione che viene lanciata in caso di errore nel caricamento del database.
public class CaricamentoException extends Exception{
    public CaricamentoException(){
        super();
    }
    
    public CaricamentoException(String s){
        super(s);
    }
}
