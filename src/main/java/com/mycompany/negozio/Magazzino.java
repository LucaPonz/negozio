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
public class Magazzino {
    ArrayList<Prodotto> listaProdotti;
    Magazzino(ArrayList<Prodotto> list){
        this.listaProdotti= list;
    }
    
    public boolean cercaProdotto(String nome){
        boolean verifica = false;
        for(Prodotto prod : listaProdotti){
            if(prod.nome.equals(nome)){
                verifica = true;
            }
        }
        return verifica;
    }
    
    public void aggiungiProdotto(Prodotto prod){
        if (this.cercaProdotto(prod.nome)){
           
        } else{
            listaProdotti.add(prod); 
        }
        
    }
}
