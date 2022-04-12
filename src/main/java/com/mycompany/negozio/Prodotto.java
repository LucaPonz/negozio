/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negozio;

import java.util.ArrayList;

/**
 *
 * @author luca
 */
public class Prodotto {
    public String nome,barcode;
    public int quantita;
    public double prezzo, totale;
    Prodotto(String nome,  int quantita, double prezzo, double totale, String barcode){
        this.nome = nome;
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.barcode = barcode;
        this.totale = totale;
    }
    
    public String stampa_scontrino(ArrayList<Prodotto> scontrino){
        String voce = "";
        for(Prodotto s : scontrino){
            voce = s.nome + " " + s.quantita + " " + s.prezzo + " " + s.totale;
        }
        return voce;
    }
}
