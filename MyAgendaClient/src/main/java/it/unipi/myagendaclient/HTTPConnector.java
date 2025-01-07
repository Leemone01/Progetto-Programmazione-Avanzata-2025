/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.myagendaclient;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Leemo
 */
//Classe contenente metodi usati per effettuare richieste HTTP al server
public class HTTPConnector {
    
    //Invia la stringa "s" sulla connessione "con"
    private static void sendToServer(HttpURLConnection con, String s) throws IOException{
        //Uso il costrutto try-with-resources, così da chiudere automaticamente le risorse, anche nel caso venisse sollevata un'eccezione.
        try (PrintStream out = new PrintStream(con.getOutputStream())){
            out.println(s);
        }
    }
    
    //Restituisce la stringa ricevuta dalla connessione "con"
    private static String receiveFromServer(HttpURLConnection con) throws IOException{
        StringBuffer content;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        }
        return content.toString();
    }

    //Effettua una richiesta POST all'URL "urlString", inviando l'oggetto obj dopo averlo serializzato in formato JSON.
    //Restituisce un oggetto di tipo Esito contenente la risposta del server.
    public static <T> Esito postRequest(String urlString, T obj) throws IOException{
        //Serializzo l'oggetto "obj" in una stringa "objSerializzato", in formato JSON.
        Gson gson = new Gson();
        String objSerializzato = gson.toJson(obj);
        
        //Effettuo una richiesta POST all'URL "urlString"
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
            
        //Invio al server "objSerializzato".
        sendToServer(con, objSerializzato);
        
        //Ottengo la risposta dal server.
        String content = receiveFromServer(con);
        
        //Deserializzo la risposta del server in un oggetto di tipo Esito, che restituisco al chiamante.
        return gson.fromJson(content, Esito.class);
    }
    
    //Effettua una richiesta GET all'URL "urlString".
    //Restituisce un oggetto di tipo "classe" contenente la risposta del server.
    public static <T> T getRequest(String urlString, Class<T> classe) throws IOException{
        //Effettuo una richiesta GET all'URL "urlString"
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        
        //Ottengo la risposta dal server
        String content = receiveFromServer(con);
            
        //Deserializzo la risposta del server in un oggetto di tipo "classe", che restituisco al chiamante
        Gson gson = new Gson();
        return gson.fromJson(content, classe);
    }
    
    //Effettua una richiesta DELETE all'URL "urlString".
    //Restituisce un oggetto di tipo "classe" contenente la risposta del server.
    public static <T> T deleteRequest(String urlString, Class<T> classe) throws IOException{
        //Effettuo una richiesta DELETE all'URL "urlString"
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Accept", "application/json");
        
        //Ottengo la risposta dal server
        String content = receiveFromServer(con);
            
        //Deserializzo la risposta del server in un oggetto di tipo "classe", che restituisco al chiamante
        Gson gson = new Gson();
        return gson.fromJson(content, classe);
    }
}
