/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negozio;

/**
 *
 * @author Michael
 */
public class Acquisto {
    String prodotto;
    int quantita;
    double prezzo_unita, totale_prodotto;
    
    Acquisto(String prodotto, int quantita, double prezzo_unita, double totale_prodotto){
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.prezzo_unita = prezzo_unita;
        this.totale_prodotto = totale_prodotto;
    }
}
