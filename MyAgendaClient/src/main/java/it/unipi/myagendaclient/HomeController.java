package it.unipi.myagendaclient;

import java.io.IOException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController extends BaseController{
    @FXML
    private Button bottoneRegistrati;
    @FXML
    private Button bottoneAccedi;
    @FXML
    private Button bottoneCarica;
    
    
    @FXML
    private void switchToAccedi(){
        cambiaSchermata("accedi");
    }
    
    @FXML
    private void switchToRegistrati(){
        cambiaSchermata("registrati");
    }
    
    @FXML
    private void caricaDatabase(){
        //Disabilito i bottoni
        bottoneRegistrati.setDisable(true);
        bottoneAccedi.setDisable(true);
        bottoneCarica.setDisable(true);
        
        Task task = new Task<Void>(){
            public Void call(){
                try{
                    //Mando una richiesta GET al server per caricare il database
                    Esito e = HTTPConnector.getRequest("http://localhost:8080/home/caricadati", Esito.class);

                    //Controllo l'esito del caricamento del database
                    if(!e.status){
                        throw new CaricamentoException(e.messaggio);
                    }

                    //In caso di successo, notifico l'utente
                    Platform.runLater(new Runnable(){
                        public void run(){
                           mostraSuccesso("Database caricato con successo!"); 
                        }
                    });
                }
                catch(IOException | CaricamentoException ex){
                    Platform.runLater(new Runnable(){
                        public void run(){
                            mostraErrore(ex.getMessage());
                        }
                    });
                }
                //Qualunque sia stato l'esito, riabilito i bottoni
                finally{
                    Platform.runLater(new Runnable(){
                        public void run(){
                            bottoneRegistrati.setDisable(false);
                            bottoneAccedi.setDisable(false);
                            bottoneCarica.setDisable(false);
                        }
                    });
                }
                return null;
            }
        };
        new Thread(task).start();
    }
}
