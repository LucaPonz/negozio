/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negozio;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Michael
 */
public class VendiP {
    static DB db = new DB();
    public String nome;
    public int quantita;
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    Date data = new Date(System.currentTimeMillis());
    public double totale;
    VendiP(String nome, int quantita, double totale){
        this.nome = nome;
        this.quantita = quantita;
        this.totale = totale;
    }
    
    public static void salva_vendite(ArrayList<VendiP> vendita) throws SQLException{
        String nome;
        int qnt = 0;
        Date data;
        double totale = 0;
        for(VendiP v : vendita){
            nome = v.nome;
            qnt = v.quantita;
            data = v.data;
            totale = v.totale;
            db.carica_Vendite(nome, qnt, data, totale);
        }
    }
}
