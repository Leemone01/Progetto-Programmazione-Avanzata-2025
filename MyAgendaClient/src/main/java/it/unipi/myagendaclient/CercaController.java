/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author Leemo
 */
public class CercaController extends TabellaController{
    @FXML
    private Button bottoneTorna;
    
    @FXML
    private Button bottoneDisconnettiti;
    
    @FXML
    private TextField input;
    
    @FXML
    private ChoiceBox<String> categoria;
    
    @FXML
    private RadioButton titolo;
    
    @FXML
    private RadioButton contenuto;
    
    @FXML
    private Button bottoneCerca;
    
    @FXML
    protected void initialize(){
        super.initialize();
        
        Task task = new Task<Void>(){
            public Void call(){
                try{
                    //Recupero tutte le categorie
                    Categoria[] catArray = Utilities.recuperaCategorie();
                    
                    //Aggiungo le categorie recuperate alla ChoiceBox
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
    private void cerca(){
        bottoneTorna.setDisable(true);
        bottoneDisconnettiti.setDisable(true);
        input.setDisable(true);
        categoria.setDisable(true);
        titolo.setDisable(true);
        contenuto.setDisable(true);
        bottoneCerca.setDisable(true);
        attivitaTable.setDisable(true);
        
        int utente = Sessione.getInstance().getID();
        Task task = new Task<Void>(){
            public Void call(){
                try{
                    //Controllo se l'utente ha inserito il testo che vuole cercare.
                    if(input.getText().isBlank()){
                        throw new AttivitaException("Inserisci il testo che vuoi cercare.");
                    }

                    //Costruisco l'URL in base a ciò che ha inserito l'utente. Mandando tutto come query string, effetto l'encoding di ogni parametro.
                    String urlString = "http://localhost:8080/agenda/caricaattivita?utente=" + URLEncoder.encode(Integer.toString(utente), StandardCharsets.UTF_8);

                    if(categoria.getValue() != null){
                        urlString += "&categoria=" + URLEncoder.encode(categoria.getValue(), StandardCharsets.UTF_8);
                    }

                    if(titolo.isSelected()){
                        urlString += "&titolo=" + URLEncoder.encode(input.getText(), StandardCharsets.UTF_8);
                    }
                    else{
                        urlString += "&contenuto=" + URLEncoder.encode(input.getText(), StandardCharsets.UTF_8);
                    }

                    //Cerco le attività dell'utente che soddisfano ciò che l'utente vuole cercare attraverso una richiesta GET
                    Attivita[] attArray = HTTPConnector.getRequest(urlString, Attivita[].class);

                    Platform.runLater(new Runnable(){
                        public void run(){
                            //Svuoto la tabella nel caso in cui fosse stata fatta un'altra ricerca in precedenza.
                            ol.clear();

                            //Riempio la tabella con le attività cercate.
                            ol.addAll(Arrays.asList(attArray));

                            //Svuoto "messaggio", nel caso in cui ci fosse scritto qualcosa.
                            messaggio.setText("");
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
                            input.setDisable(false);
                            categoria.setDisable(false);
                            titolo.setDisable(false);
                            contenuto.setDisable(false);
                            bottoneCerca.setDisable(false);
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
        input.setDisable(true);
        categoria.setDisable(true);
        titolo.setDisable(true);
        contenuto.setDisable(true);
        bottoneCerca.setDisable(true);
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
                            input.setDisable(false);
                            categoria.setDisable(false);
                            titolo.setDisable(false);
                            contenuto.setDisable(false);
                            bottoneCerca.setDisable(false);
                            attivitaTable.setDisable(false);
                        }
                    });
                }
                return null;
            }
        };
        new Thread(task).start();
    }
}
