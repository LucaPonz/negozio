package com.mycompany.negozio;


import com.mycompany.negozio.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luca
 */
public class DB {
    ResultSet rs = null;
    Statement stm = null;
    private Connect conObj = new Connect();
    private Connection con = conObj.createConnection();
    
    public boolean cercaProdotto(String nome){
        boolean verifica = false;
        // Luca 
        //String query = "SELECT id FROM prodotto WHERE nome = ?";
        //Michael P.
        String query = "SELECT id FROM prodotti WHERE nome = ?";
        try{
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            while(rs.next()){
                verifica = true;
            }
            
        }catch(SQLException e){
        }
        return verifica;
    }
    
    public void aggiungiProdotto(ArrayList<Prodotto> scontrino) throws SQLException{
        for(Prodotto s : scontrino){
            if(this.cercaProdotto(s.nome)){
            //this.aggiornaProdotto();
            }else{
                try{
                    //Michael P.
                    
                        String query_add = "INSERT INTO prodotti SET nome = ?, quantita = ?, prezzo = ?, barcode = ?;";
                        PreparedStatement stmt = con.prepareStatement(query_add);
                        stmt.setString(1, s.nome);
                        stmt.setInt(2, s.quantita);
                        stmt.setDouble(3, s.prezzo);
                        stmt.setString(4, s.barcode);
                        if(stmt.executeUpdate() > 0){
                            System.out.println("Inserito correttamente");
                        }else{
                            System.out.println("Errore");
                        }
            
                    
                }catch(SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    public void aggiornaProdotto(){
        String query_agg = "UPDATE prodotto (barcode,nome,quantita,listino";
    }
    public void nuovo_fornitore(String nome, String piva, String indirizzo, String citta, String nazione){
        String query = "INSERT INTO fornitori SET nome = ?, p_iva = ?, indirizzo = ?, citta = ?, nazione = ?;";
        try{
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, nome);
            stmt.setString(2, piva);
            stmt.setString(3, indirizzo);
            stmt.setString(4, citta);
            stmt.setString(5, nazione);
            if(stmt.executeUpdate() > 0){
                System.out.println("Inserito correttamente");
            }else{
                System.out.println("Errore");
            }
        }catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
     
    public ResultSet fornitori(){
        String query = "SELECT * FROM fornitori;";
        try{
            stm = con.createStatement();
            rs = stm.executeQuery(query);
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            return rs;
        }
     }
    
    public ResultSet magazzino(){
        String query = "SELECT * FROM prodotti;";
        try{
            stm = con.createStatement();
            rs = stm.executeQuery(query);
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            return rs;
        }
     }
}
