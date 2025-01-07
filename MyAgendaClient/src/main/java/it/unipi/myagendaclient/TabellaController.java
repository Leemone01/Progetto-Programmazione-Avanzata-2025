/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Leemo
 */

//Superclasse di tutte le classi di controllo contenenti una tabella con attività
public class TabellaController extends AgendaController{
    @FXML
    protected TableView<Attivita> attivitaTable = new TableView<>();
    
    protected ObservableList<Attivita> ol; 
    
    @FXML
    protected void initialize(){ 
        super.initialize();
        
        //Creo la tabella con le attività dell'utente.
        TableColumn titoloCol = new TableColumn("Titolo"); 
        titoloCol.setCellValueFactory(new PropertyValueFactory<>("titolo")); 
        
        TableColumn categoriaCol = new TableColumn("Categoria"); 
        categoriaCol.setCellValueFactory(new PropertyValueFactory<>("categoria")); 
       
        TableColumn contenutoCol = new TableColumn("Contenuto"); 
        contenutoCol.setCellValueFactory(new PropertyValueFactory<>("contenuto"));
        
        //Imposto che la colonna "contenuto" occupi sempre almeno metà della larghezza della tabella
        attivitaTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        contenutoCol.minWidthProperty().bind(attivitaTable.widthProperty().multiply(0.5));
        

        attivitaTable.getColumns().addAll(titoloCol, categoriaCol, contenutoCol);                 
        
        ol = FXCollections.observableArrayList();     
        attivitaTable.setItems(ol);
    }
}
