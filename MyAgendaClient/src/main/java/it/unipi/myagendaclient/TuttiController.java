/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

import java.io.IOException;
import java.util.Arrays;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Leemo
 */
public class TuttiController extends TabellaController{
    @FXML
    private Button bottoneCerca;
    
    @FXML
    private Button bottoneInserisci;
    
    @FXML
    private Button bottoneTorna;
    
    @FXML
    private Button bottoneDisconnettiti;
    
    
    @FXML
    protected void initialize(){ 
        super.initialize();
        
        int utente = Sessione.getInstance().getID();
        Task task = new Task<Void>(){
            public Void call(){
                try{
                    //Effettuo una richiesta GET per recuperare tutte le attività dell'utente corrente
                    String urlString = "http://localhost:8080/agenda/caricaattivita?utente=" + utente;
                    Attivita[] attArray = HTTPConnector.getRequest(urlString, Attivita[].class);

                    //Aggiungo le attività recuperate alla ordered list "ol", mostrandole così nella tabella
                    Platform.runLater(new Runnable(){
                        public void run(){
                            ol.addAll(Arrays.asList(attArray));
                        }
                    });
                }
                catch(IOException io){
                    Platform.runLater(new Runnable(){
                        public void run(){
                            mostraErrore(io.getMessage());
                        }
                    });
                }
                finally{
                    Platform.runLater(new Runnable(){
                        public void run(){
                            attivitaTable.setDisable(false);
                        }
                    });
                }
                
                return null;
            }
        };
        new Thread(task).start();
        
    }
    
    @FXML
    public void rimuoviAttivita(){
        bottoneTorna.setDisable(true);
        bottoneDisconnettiti.setDisable(true);
        bottoneCerca.setDisable(true);
        bottoneInserisci.setDisable(true);
        attivitaTable.setDisable(true);
        
        Attivita attivitaSelezionata = attivitaTable.getSelectionModel().getSelectedItem();
        
        Task task = new Task<Void>(){
            public Void call(){
                try{
                    //Rimuovo l'attività selezionata dal DB
                    Utilities.rimuoviAttivita(attivitaSelezionata);
                    
                    //In caso di successo, rimuovo l'attività anche dalla tabella, e notifico l'utente
                    Platform.runLater(new Runnable(){
                        public void run(){
                            ol.remove(attivitaSelezionata);
                            mostraSuccesso("Attività eliminata con successo!");
                        }
                    });
                }
                catch(IOException | AttivitaException ex){
                    Platform.runLater(new Runnable(){
                        public void run(){
                            mostraErrore(ex.getMessage());
                        }
                    });
                }
                finally{
                    Platform.runLater(new Runnable(){
                        public void run(){
                            bottoneTorna.setDisable(false);
                            bottoneDisconnettiti.setDisable(false);
                            bottoneCerca.setDisable(false);
                            bottoneInserisci.setDisable(false);
                            attivitaTable.setDisable(false);
                        }
                    });
                }
                return null;
            }
        };
        new Thread(task).start();
    }
    
    @FXML
    public void switchToInserimento(){
        cambiaSchermata("inserimento");
    }
    
    @FXML
    public void switchToCerca(){
        cambiaSchermata("cerca");
    }
}
