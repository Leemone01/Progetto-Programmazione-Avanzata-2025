/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

import java.io.IOException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;

/**
 *
 * @author Leemo
 */
public class RegistratiController extends AutenticazioneController{
    @FXML
    private void registraUtente(){
        username.setDisable(true);
        password.setDisable(true);
        bottoneInvio.setDisable(true);
        bottoneHome.setDisable(true);
        
        String urlString = "http://localhost:8080/utente/registra";
        
        Task task = new Task<Void>(){
            public Void call(){
                try{
                    //Autentico l'utente in base ai dati inseriti
                    int ID = Utilities.autenticaUtente(username.getText(), password.getText(), urlString);
                    
                    //In caso di successo, aggiorno la sessione con quella dell'utente corrente, 
                    //impostando l'ID univoco ricevuto dal server e l'username dell'utente,
                    //e porto l'utente alla schermata che mostra tutte le sue attività
                    Platform.runLater(new Runnable(){
                        public void run(){
                            Sessione.setInstance(ID, username.getText());
                            cambiaSchermata("tutti");
                        }
                    });
                    
                }
                catch(IOException | UtenteException ex){
                    Platform.runLater(new Runnable(){
                        public void run(){
                            mostraErrore(ex.getMessage());
                        }
                    });
                }
                finally{
                    Platform.runLater(new Runnable(){
                        public void run(){
                            username.setDisable(false);
                            password.setDisable(false);
                            bottoneInvio.setDisable(false);
                            bottoneHome.setDisable(false); 
                        }
                    });
                }
                
                return null;
            }
        };
        new Thread(task).start();
    }  
}
