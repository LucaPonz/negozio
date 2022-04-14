package com.mycompany.negozio;

import com.mycompany.negozio.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    public boolean verifica_Prodotto(String nome) {
        boolean verifica = false;
        // Luca 
        //String query = "SELECT id FROM prodotto WHERE nome = ?";
        //Michael P.
        String query = "SELECT id FROM prodotti WHERE nome = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            while (rs.next()) {
                verifica = true;
            }

        } catch (SQLException e) {
        }
        return verifica;
    }

    public void aggiungiProdotto(ArrayList<Prodotto> scontrino) throws SQLException {
        for (Prodotto s : scontrino) {
            if (this.verifica_Prodotto(s.nome)) {
                //this.aggiornaProdotto();
            } else {
                try {
                    //Michael P.

                    String query_add = "INSERT INTO prodotti SET nome = ?, quantita = ?, prezzo = ?, barcode = ?;";
                    PreparedStatement stmt = con.prepareStatement(query_add);
                    stmt.setString(1, s.nome);
                    stmt.setInt(2, s.quantita);
                    stmt.setDouble(3, s.prezzo);
                    stmt.setString(4, s.barcode);
                    if (stmt.executeUpdate() > 0) {
                        System.out.println("Inserito correttamente");
                    } else {
                        System.out.println("Errore");
                    }

                } catch (SQLException e) {
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

    public int estrai_qtprodotto(String nome) {
        String query = "SELECT quantita FROM prodotti WHERE nome = ?";
        int qnt = 0;
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            while (rs.next()) {
                qnt = Integer.parseInt(rs.getString("quantita"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qnt;
    }
    
    public void nuovo_fornitore(String nome, String piva, String indirizzo, String citta, String nazione) {
        String query = "INSERT INTO fornitori SET nome = ?, p_iva = ?, indirizzo = ?, citta = ?, nazione = ?;";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, nome);
            stmt.setString(2, piva);
            stmt.setString(3, indirizzo);
            stmt.setString(4, citta);
            stmt.setString(5, nazione);
            if (stmt.executeUpdate() > 0) {
                System.out.println("Inserito correttamente");
            } else {
                System.out.println("Errore");
            }
        } catch (SQLException e) {
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

    public ResultSet fornitori() {
        String query = "SELECT * FROM fornitori;";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return rs;
        }
    }

    public ResultSet magazzino() {
        String query = "SELECT * FROM prodotti;";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return rs;
        }
    }

    public ResultSet cerca_prodotto(String nome) {
        //Michael P.
        String query = "SELECT * FROM prodotti WHERE nome = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
        }
        return rs;
    }

    public boolean verifica_qt(int qnt, String nome) throws SQLException {
        rs = this.cerca_prodotto(nome);
        boolean flag = true;
        int num = 0;
        while (rs.next()) {
            num = Integer.parseInt(rs.getString("quantita"));
            if (qnt > num) {
                flag = false;
            }
        }
        return flag;
    }

    //VENDITE
    public void carica_Vendite(Date data, double totale_vendita) throws SQLException {
        data = new Date();
        String query_tabVendite = "INSERT INTO vendita SET data = ?, totale = ?;";
        try {
            PreparedStatement stmt = con.prepareStatement(query_tabVendite);
            stmt.setString(1, formatter.format(data));
            stmt.setDouble(2, totale_vendita);
            if (stmt.executeUpdate() > 0) {
                System.out.println("Inserito correttamente");
            } else {
                System.out.println("Errore");
            }
        } catch (SQLException e) {
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

    public void carica_ProdottiVendite(String nome, int quantita, double totale_prodotto) throws SQLException {
        int id = 0, id_prodotto = 0;
        double prezzo_prodotto = 0;
        try {
            String last_id = "SELECT id FROM vendita ORDER BY id DESC;";
            stm = con.createStatement();
            rs = stm.executeQuery(last_id);
            if (rs.next()) {
                id = rs.getInt("id");
                System.out.println(id);
            }
        } catch (SQLException e) {
        }
        ResultSet rs_prodotto = cerca_prodotto(nome);
        while (rs_prodotto.next()) {
            id_prodotto = rs_prodotto.getInt("id");
            prezzo_prodotto = rs_prodotto.getDouble("prezzo");
        }
            //Query pivot 
            String query_tabProdVendite = "INSERT INTO prodotti_vendite SET id_prodotto = ?, id_vendita  = ?, quantita_prod = ?, totale_vendita = ?;";
            PreparedStatement stmt2;
        try {
            stmt2 = con.prepareStatement(query_tabProdVendite);
            stmt2.setInt(1, id_prodotto);
            stmt2.setInt(2, id);
            stmt2.setInt(3, quantita);
            stmt2.setDouble(4, totale_prodotto);
            if (stmt2.executeUpdate() > 0) {
                System.out.println("Inserito correttamente");
            } else {
                System.out.println("Errore");
            }
            //Aggiorna magazzino
            this.aggiorna_qtProdotto(nome, quantita);
        }catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void aggiorna_qtProdotto(String nome, int quantita){
        int qnt = estrai_qtprodotto(nome), tmp = 0;
        tmp = qnt - quantita;
        String query = "UPDATE prodotti SET quantita = ? WHERE nome = ?;";
        try {
            stm = con.createStatement();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, tmp);
            stmt.setString(2, nome);
            if (stmt.executeUpdate() > 0) {
                System.out.println("Modificato correttamente");
            } else {
                System.out.println("Errore");
            }
        }catch (SQLException e) {
                e.printStackTrace();
        }
    }
}
