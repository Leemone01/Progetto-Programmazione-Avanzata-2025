/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 *
 * @author Leemo
 */
//Superclasse diretta o indiretta di tutte le schermate
public class BaseController {
    //Messaggio per avvisare l'utente del successo o del fallimento di un'operazione.
    @FXML
    protected Text messaggio;
    
    //Aggiorna "messaggio" con le informazioni "info" sul fallimento di un'operazione.
    protected void mostraErrore(String info){
        messaggio.setText(info);
        messaggio.setFill(Paint.valueOf("red"));
    }
    
    //Aggiorna "messaggio" con le informazioni "info" sul successo di un'operazione.
    protected void mostraSuccesso(String s){
        messaggio.setText(s);
        messaggio.setFill(Paint.valueOf("green"));
    }
    
    //Cambia la schermata corrente con quella avente il nome "schermata". 
    //Gestisce un'eventuale IOException sollevata da App.setRoot().
    protected void cambiaSchermata(String schermata){
        try{
            App.setRoot(schermata);
        }
        catch(IOException io){
            mostraErrore(io.getMessage());
        }
    }
}
