/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.MyAgendaServer;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 *
 * @author Leemo
 */


//Creo a runtime un oggetto di tipo Database.
@Configuration
public class Database {
    
    //Inietto "dataSource" con il riferimento all'oggetto di tipo DataSource che rappresenta il database.
    @Autowired
    private DataSource dataSource;
    
    //Esegue lo script "615600.sql", che crea e popola le tabelle del database.
    public void inizializzaDatabase(){
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("615600.sql"));
        
        populator.execute(dataSource);
    }
    
    public DataSource getDataSource(){
        return dataSource;
    }
}
