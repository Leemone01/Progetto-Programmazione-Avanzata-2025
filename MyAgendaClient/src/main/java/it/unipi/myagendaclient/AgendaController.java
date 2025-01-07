/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 *
 * @author Leemo
 */

//Superclasse diretta o indiretta delle classi di controllo di tutte le schermate successive a quella di accesso/registrazione.
public class AgendaController extends BaseController{
    @FXML
    protected Text welcomeMsg;
    
    @FXML
    protected void initialize(){
        //Recupero la sessione dell'utente corrente, mostrando l'username.
        Sessione s = Sessione.getInstance();
        welcomeMsg.setText("Ciao, " + s.getUsername());
    }
    
    @FXML
    protected void disconnettiUtente(){
        //Annullo la sessione dell'utente corrente e riporto l'utente alla home.
        Sessione.setInstance(0, null);
        cambiaSchermata("home");
    }
    
    @FXML
    protected void switchToTutti(){
        //Riporto l'utente alla schermata con tutte le attività.
        cambiaSchermata("tutti");
    }
}
