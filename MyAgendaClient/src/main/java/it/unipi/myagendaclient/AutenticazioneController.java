/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Leemo
 */
//Superclasse delle classi di controllo della schermata di accesso e registrazione
public class AutenticazioneController extends BaseController{ 
    @FXML
    protected TextField username;
    
    @FXML
    protected PasswordField password;
    
    //Bottone di accesso/registrazione
    @FXML
    protected Button bottoneInvio;
    
    @FXML
    protected Button bottoneHome;
    
    @FXML
    protected void switchToHome(){
        //Riporto l'utente alla schermata iniziale.
        cambiaSchermata("home");
    }
}
