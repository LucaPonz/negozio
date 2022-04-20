package com.mycompany.negozio;

import com.mycompany.negozio.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
                this.aggiornaProdotto(scontrino);
            } else {
                try {
                    //Inserimento nuovo prodotto
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
                }
            }
        }
    }

    public void aggiorna_prodotti_fornitori(String fornitore, String nome, double prezzo) {
        if (this.verifica_Prodotto(nome)) {
            System.out.println("IF");
            try {
                //Inserimento prodotto già esistente
                int id = 0, id_fornitore = 0;

                ResultSet rs2 = this.cerca_prodotto(nome);
                if (rs2.next()) {
                    id = rs2.getInt("id");
                }
                id_fornitore = id_fornitore(fornitore);
                System.out.println("Prodotto: " + id);

                System.out.println("Fornitore: " + id_fornitore);
                String query = "INSERT INTO prodotti_fornitori SET id_prodotto=" + id + ", id_fornitore=" + id_fornitore + ", prezzo_prodotto=?;";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setDouble(1, prezzo);
                if (stmt.executeUpdate() > 0) {
                    System.out.println("Caricato");
                } else {
                    System.out.println("Errore!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ELSE");
            try {
                int last_id = 0, id_fornitore = 0;

                last_id = this.last_id_prodotto();
                id_fornitore = id_fornitore(fornitore);

                System.out.println("Prodotto: " + last_id);
                System.out.println("Fornitore: " + id_fornitore);
                String query = "INSERT INTO prodotti_fornitori SET id_prodotto=" + last_id + ", id_fornitore=" + id_fornitore + ", prezzo_prodotto=?;";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setDouble(1, prezzo);
                if (stmt.executeUpdate() > 0) {
                    System.out.println("Caricato");
                } else {
                    System.out.println("Errore!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int last_id_prodotto() {
        int last_id = 0;
        String query = "SELECT id FROM prodotti ORDER BY id DESC";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query);
            if (rs.next()) {
                last_id = rs.getInt("id");
            }
        } catch (SQLException e) {
        }
        return last_id;
    }

    public double estrai_prezzo_prod(String nome) {
        String query = "SELECT prezzo FROM prodotti WHERE nome = ?";
        double prezzo = 0;
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            while (rs.next()) {
                prezzo = Double.parseDouble(rs.getString("prezzo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prezzo;
    }

    public void aggiornaProdotto(ArrayList<Prodotto> scon) throws SQLException {
        int id = 0;
        double prezzo = 0;
        int qt = 0;
        for (Prodotto s : scon) {
            ResultSet rs = this.cerca_prodotto(s.nome);
            if (rs.next()) {
                id = rs.getInt("id");
            }
            prezzo = estrai_prezzo_prod(s.nome);
            qt = estrai_qtprodotto(s.nome) + s.quantita;
            try {
                //Inserimento nuovo prodotto

                String query_add = "UPDATE prodotti SET quantita = ?, prezzo = ? WHERE id =" + id + ";";
                PreparedStatement stmt = con.prepareStatement(query_add);
                stmt.setInt(1, qt);
                if (prezzo > s.prezzo) {
                    stmt.setDouble(2, prezzo);
                } else {
                    stmt.setDouble(2, s.prezzo);
                }
                if (stmt.executeUpdate() > 0) {
                    System.out.println("Inserito correttamente");
                } else {
                    System.out.println("Errore");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void inserisci_acquisto(String fornitore, double totale) {
        int id = this.id_fornitore(fornitore);
        Date data_ac = new Date();
        String query_ins = "INSERT INTO acquisti SET data_acquisto = '" + formatter.format(data_ac) + "', id_fornitore = " + id + ", totale = " + totale + ";";
        System.out.println(id);
        System.out.println(data_ac);
        System.out.println(query_ins);
        try {
            stm = con.createStatement();
            if (stm.executeUpdate(query_ins) > 0) {
                System.out.println("Acquisto inserito");
            } else {
                System.out.println("Acquisto non inserito");
            }
        } catch (SQLException e) {
        }
    }

    //Caricamento tab pivot acquisti_prodotti
    public void inserisci_ac_prodotti(String prodotto, int qnt_prodotto, double prezzou_prodotto, double totale_ac) throws SQLException {
        ResultSet prod = cerca_prodotto(prodotto);
        int id_prodotto = 0, lastid_ac = this.lastID_acquisti();
        while (prod.next()) {
            id_prodotto = prod.getInt("id");
        }
        String query_inserisci = "INSERT INTO acquisti_prodotti SET id_prodotto = " + id_prodotto + ", id_acquisto = " + lastid_ac + ", quantita_prodotto = ?, prezzo_prodotto = ?, totale_acquisto = ?;";
        try {
            PreparedStatement stmt = con.prepareStatement(query_inserisci);
            stmt.setInt(1, qnt_prodotto);
            stmt.setDouble(2, prezzou_prodotto);
            stmt.setDouble(3, totale_ac);
            if (stmt.executeUpdate() > 0) {
                System.out.println("Inserito correttamente");
            } else {
                System.out.println("Errore");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Funzione recupera ultimo ID acquisti
    private int lastID_acquisti() {
        int lastid_ac = 0;
        String query_lastIDac = "SELECT id FROM acquisti ORDER BY id DESC;";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query_lastIDac);
            if (rs.next()) {
                lastid_ac = rs.getInt("id");
            }
        } catch (SQLException e) {
        }
        return lastid_ac;
    }

    //Funzione recupero id fornitore
    public int id_fornitore(String fornitore) {
        String query_fornitore = "SELECT id FROM fornitori WHERE nome = '" + fornitore + "';";
        int id = 0;
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query_fornitore);
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
        }
        return id;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void aggiorna_qtProdotto(String nome, int quantita) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet storico_vendite() {
        String query = "SELECT * FROM vendita;";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
        }
        return rs;
    }

    public ResultSet storico_acquisti() {
        String query = "SELECT a.id, a.data_acquisto, a.totale, f.nome FROM acquisti AS a INNER JOIN fornitori AS f ON a.id_fornitore = f.id;";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
        }
        return rs;
    }

    public String dettaglio_vendita(int id) {
        String dettaglio = "";
        ArrayList<Integer> prodotto = new ArrayList<>();
        ArrayList<Integer> quantita = new ArrayList<>();
        ArrayList<Double> prezzo = new ArrayList<>();
        ArrayList<String> nome = new ArrayList<>();
        String query = "SELECT pv.id_prodotto, pv.quantita_prod, pv.totale_vendita, p.nome FROM prodotti_vendite AS pv INNER JOIN prodotti as p ON pv.id_prodotto = p.id WHERE pv.id_vendita = '" + id + "';";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query);
            while (rs.next()) {
                nome.add(rs.getString("nome"));
                prodotto.add(rs.getInt("id_prodotto"));
                quantita.add(rs.getInt("quantita_prod"));
                prezzo.add(rs.getDouble("totale_vendita"));
            }

        } catch (SQLException e) {
        }
        for (int i = 0; i < nome.size(); i++) {
            dettaglio += "Prodotto: " + nome.get(i) + " - Pezzi: " + quantita.get(i) + " - Totale: " + prezzo.get(i) + ";\n";
        }

        return dettaglio;
    }

    public String dettaglio_acquisto(int id) {
        String dettaglio = "";
        ArrayList<Integer> prodotto = new ArrayList<>();
        ArrayList<Integer> quantita = new ArrayList<>();
        ArrayList<Double> prezzo = new ArrayList<>();
        ArrayList<String> nome = new ArrayList<>();
        String query = "SELECT p.nome, ac.quantita_prodotto, ac.totale_acquisto FROM acquisti_prodotti AS ac INNER JOIN prodotti as p ON ac.id_prodotto = p.id WHERE ac.id_acquisto = '" + id + "';";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query);
            while (rs.next()) {
                nome.add(rs.getString("nome"));
                quantita.add(rs.getInt("quantita_prodotto"));
                prezzo.add(rs.getDouble("totale_acquisto"));
            }

        } catch (SQLException e) {
        }
        for (int i = 0; i < nome.size(); i++) {
            dettaglio += "Prodotto: " + nome.get(i) + " - Pezzi: " + quantita.get(i) + " - Totale: " + prezzo.get(i) + ";\n";
        }

        return dettaglio;
    }

    public ResultSet cerca_prodotto_lettera(String c) {
        ArrayList<Integer> id = new ArrayList<>();
        ArrayList<String> nome = new ArrayList<>();
        ArrayList<Integer> quantita = new ArrayList<>();
        ArrayList<Double> prezzo = new ArrayList<>();
        ArrayList<String> barcode = new ArrayList<>();
        String query = "SELECT id, nome, quantita, prezzo, barcode FROM prodotti WHERE nome LIKE '" + c + "%';";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query);

        } catch (SQLException e) {
        }

        return rs;
    }

    public ResultSet cerca_prodotto_barcode(String c) {
        ArrayList<Integer> id = new ArrayList<>();
        ArrayList<String> nome = new ArrayList<>();
        ArrayList<Integer> quantita = new ArrayList<>();
        ArrayList<Double> prezzo = new ArrayList<>();
        ArrayList<String> barcode = new ArrayList<>();
        String query = "SELECT id, nome, quantita, prezzo, barcode FROM prodotti WHERE barcode LIKE '" + c + "%';";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
        }
        return rs;
    }

    public ResultSet cerca_fornitore(String c) {
        ArrayList<Integer> id = new ArrayList<>();
        ArrayList<String> nome = new ArrayList<>();
        ArrayList<String> p_iva = new ArrayList<>();
        ArrayList<String> indirizzo = new ArrayList<>();
        ArrayList<String> citta = new ArrayList<>();
        ArrayList<String> nazione = new ArrayList<>();
        String query = "SELECT id, nome, p_iva, indirizzo, citta, nazione FROM fornitori WHERE nome LIKE '" + c + "%';";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
        }
        return rs;
    }

    public int id_prodotto(String nome) {
        int id = 0;
        String query = "SELECT id FROM prodotti WHERE nome = ?;";
        try {
            stm = con.createStatement();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
        }
        return id;
    }

    public ResultSet statistiche_fornitori(String prodotto) {
        int id_p = id_prodotto(prodotto);
        String query = "SELECT DISTINCT nome FROM fornitori INNER JOIN prodotti_fornitori AS pf ON id_prodotto = " + id_p + " WHERE id = pf.id_fornitore;";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return rs;
        }
    }

    //Funzioni per statitstiche
    public int vendite_mese(String prodotto) {
        LocalDate oggi = LocalDate.now();
        int mese = oggi.getMonthValue();
        int anno = oggi.getYear();
        int conta = 0;
        String query = "SELECT COUNT(pv.id_vendita),p.id FROM prodotti_vendite as pv\n"
                + "INNER JOIN prodotti as p on p.id = pv.id_prodotto\n"
                + "INNER JOIN vendita as v ON v.id = pv.id_vendita\n"
                + "where p.nome = '" + prodotto + "' and v.data > '2022/04/01';";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return conta;
        }
    }
}
//" + anno + "/" + mese + "