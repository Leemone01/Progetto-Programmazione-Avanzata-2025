/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package it.unipi.myagendaclient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Leemo
 */
public class SessioneTest {
    
    public SessioneTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testGetInstance(){
        Sessione s = Sessione.getInstance();
        
        //Se il metodo non ha creato in automatico un nuovo oggetto Sessione
        if(s == null){
            fail("Errore nel creare l'oggetto Sessione");
        }
    }
    
    @Test
    public void testSetInstance() {
        //Scelgo un ID a caso tra 1 e 10 e un username a caso
        int ID = (int)((Math.random()*10 ) + 1);
        String username = "test" + Math.random();
        
        //Setto l'oggetto Sessione con i valori generati
        Sessione.setInstance(ID, username);
        
        //Controllo che l'oggetto Sessione abbia i valori scelti
        Sessione s = Sessione.getInstance();
        assertEquals(ID, s.getID());
        assertEquals(username, s.getUsername());
    }
    
}
