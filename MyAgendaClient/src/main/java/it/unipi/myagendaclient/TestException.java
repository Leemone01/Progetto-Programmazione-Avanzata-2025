/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

/**
 *
 * @author Leemo
 */
//Eccezione che viene lanciata in caso di errore nell'esecuzione di un test.
public class TestException extends Exception{
    public TestException(){
        super();
    }
    
    public TestException(String s){
        super(s);
    }
}
