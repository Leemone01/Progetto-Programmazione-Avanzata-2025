/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

import java.io.IOException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Leemo
 */
public class InserimentoController extends AgendaController{
    @FXML
    private Button bottoneTorna;
    
    @FXML
    private Button bottoneDisconnettiti;
    
    @FXML
    private TextField titolo;
    
    @FXML
    private ChoiceBox<String> categoria;
    
    @FXML
    private TextArea contenuto;
    
    @FXML
    private Button bottoneInserisci;
    
    @FXML
    protected void initialize(){
        super.initialize();
        
        Task task = new Task<Void>(){
            public Void call(){
                try{
                    //Recupero le categorie
                    Categoria[] catArray = Utilities.recuperaCategorie();
                    
                    //In caso di successo, aggiungo le categorie recuperate alla ChoiceBox
                    Platform.runLater(new Runnable(){
                        public void run(){
                            for(int i = 0; i < catArray.length; i++){
                                categoria.getItems().add(catArray[i].nome);
                            }
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
                            categoria.setDisable(false);
                        }
                    });
                }
                
                return null;
            }
        };
        new Thread(task).start();
    }
    
    @FXML
    private void inserisciAttivita(){
        bottoneTorna.setDisable(true);
        bottoneDisconnettiti.setDisable(true);
        titolo.setDisable(true);
        categoria.setDisable(true);
        contenuto.setDisable(true);
        bottoneInserisci.setDisable(true);
        
        int utente = Sessione.getInstance().getID();
        Task task = new Task<Void>(){
            public Void call(){
                try{
                    //Aggiungo l'attività con i campi inseriti dall'utente
                    Utilities.aggiungiAttivita(utente, titolo.getText(), categoria.getValue(), contenuto.getText());
                    
                    //In caso di successo, notifico l'utente e svuoto tutti i campi.
                    Platform.runLater(new Runnable(){
                        public void run(){
                            mostraSuccesso("Attività inserita con successo!");
                            titolo.clear();
                            categoria.setValue(null);
                            contenuto.clear();
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
                    bottoneTorna.setDisable(false);
                    bottoneDisconnettiti.setDisable(false);
                    titolo.setDisable(false);
                    categoria.setDisable(false);
                    contenuto.setDisable(false);
                    bottoneInserisci.setDisable(false);
                }
                return null;
            }
        };
        new Thread(task).start();
    }
}
