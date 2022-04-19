/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.negozio;

import java.awt.event.ItemEvent;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luca
 */
public class NegozioForm extends javax.swing.JFrame {

    boolean flag = false;
    boolean v_flag = false;
    boolean vendi = false;
    double tot = 0, tot_ac = 0;
    DefaultTableModel model_s, model_f, model_m, model_v;
    static DB db = new DB();
    ArrayList<Prodotto> scontrino = new ArrayList<>();

    /**
     * Creates new form NegozioForm
     */
    public NegozioForm() throws SQLException {
        initComponents();
        model_s = (DefaultTableModel) table_scontrino.getModel();
        model_v = (DefaultTableModel) table_vendi.getModel();
        //calcolo_totale();
        lista_fornitori();
        lista_magazzino();
        vendi_prodotto();
        //estrai_qtprodotto(); //Verifica errore iniziale
        lista_fornitori_combo();
        flag = true;
    }

    private void calcolo_totale() {
        String nome = acquista_prodotto.getText();
        double qnt = Double.parseDouble(acquista_quantita.getText());
        double prezzo = Double.parseDouble(acquista_prezzo.getText());
        double totale = qnt * prezzo;
        String tot = "" + totale;
        acquista_totale.setText(tot);
    }

    private void lista_fornitori() throws SQLException {
        model_f = (DefaultTableModel) table_fornitori.getModel();
        ResultSet rs = db.fornitori();
        String id= "", nome = "", piva = "", indirizzo = "", citta = "", nazione = "";
        while (rs.next()) {
            id = "" + rs.getInt("id");
            nome = rs.getString("nome");
            piva = rs.getString("p_iva");
            indirizzo = rs.getString("indirizzo");
            citta = rs.getString("citta");
            nazione = rs.getString("nazione");
            String riga[] = {id,nome, piva, indirizzo, citta, nazione};
            model_f.addRow(riga);
        }
    }
    
    private void lista_forn_cerca(String c) throws SQLException {
        model_f = (DefaultTableModel) table_fornitori.getModel();
        model_f.getDataVector().removeAllElements();
        model_f.fireTableDataChanged();
        ResultSet rs = db.cerca_fornitore(c);
        String id= "", nome = "", piva = "", indirizzo = "", citta = "", nazione = "";
        while (rs.next()) {
            id = "" + rs.getInt("id");
            nome = rs.getString("nome");
            piva = rs.getString("p_iva");
            indirizzo = rs.getString("indirizzo");
            citta = rs.getString("citta");
            nazione = rs.getString("nazione");
            String riga[] = {id,nome, piva, indirizzo, citta, nazione};
            model_f.addRow(riga);
        }
    }

    private void lista_magazzino() throws SQLException {
        model_m = (DefaultTableModel) table_magazzino.getModel();
        ResultSet rs = db.magazzino();
        String id = "", nome = "", qnt = "", prezzo = "", barcode = "";
        while (rs.next()) {
            id = "" + rs.getInt("id");
            nome = rs.getString("nome");
            qnt = "" + rs.getInt("quantita");
            prezzo = "" + rs.getDouble("prezzo");
            barcode = rs.getString("barcode");
            String riga[] = {id, nome, qnt, prezzo, barcode};
            model_m.addRow(riga);
        }
    }
    
    private void lista_mag_cerca(String c) throws SQLException {
        
        model_m = (DefaultTableModel) table_magazzino.getModel();
        model_m.getDataVector().removeAllElements();
        model_m.fireTableDataChanged();
        ResultSet rs = db.cerca_prodotto_lettera(c);
        String id = "", nome = "", qnt = "", prezzo = "", barcode = "";
        while (rs.next()) {
            id = "" + rs.getInt("id");
            nome = rs.getString("nome");
            qnt = "" + rs.getInt("quantita");
            prezzo = "" + rs.getDouble("prezzo");
            barcode = rs.getString("barcode");
            String riga[] = {id, nome, qnt, prezzo, barcode};
            model_m.addRow(riga);
            }
    }
    
    private void lista_mag_cerca_barcode(String c) throws SQLException {
        
        model_m = (DefaultTableModel) table_magazzino.getModel();
        model_m.getDataVector().removeAllElements();
        model_m.fireTableDataChanged();
        ResultSet rs = db.cerca_prodotto_barcode(c);
        String id = "", nome = "", qnt = "", prezzo = "", barcode = "";
        while (rs.next()) {
            id = "" + rs.getInt("id");
            nome = rs.getString("nome");
            qnt = "" + rs.getInt("quantita");
            prezzo = "" + rs.getDouble("prezzo");
            barcode = rs.getString("barcode");
            String riga[] = {id, nome, qnt, prezzo, barcode};
            model_m.addRow(riga);
        }
    }

    private void vendi_prodotto() {
        if (flag == true) {
            vendi_nome_prodotto.removeAllItems();
            vendi_nome_prodotto.addItem("Seleziona prodotto...");
        } else if (v_flag == true){
            vendi_nome_prodotto.removeAllItems();
            v_flag = false;
            vendi_prodotto();
        }
        ResultSet rs = db.magazzino();
        try {

            while (rs.next()) {
                vendi_nome_prodotto.addItem(rs.getString("nome") + " (" + rs.getInt("quantita") + ")");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        vendi = true;
    }

    private void lista_fornitori_combo() {
        ResultSet rs = db.fornitori();
        try {
            while (rs.next()) {
                combo_acquista_fornitore.addItem(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void estrai_qtprodotto() {
        String nome = vendi_nome_prodotto.getSelectedItem().toString();
        if (nome.equals("Seleziona prodotto...")) {
            box_qnt_vendi.addItem("" + 0);
        } else {
            nome = vendi_nome_prodotto.getSelectedItem().toString().substring(0, vendi_nome_prodotto.getSelectedItem().toString().indexOf("("));
            int x = db.estrai_qtprodotto(nome);
            box_qnt_vendi.removeAllItems();
            if (x > 0) {
                for (int i = 1; i < x + 1; i++) {
                    box_qnt_vendi.addItem("" + i);
                }
            } else {
                box_qnt_vendi.addItem("" + 0);
            }
        }
    }

    private static String calcolo_totale_vendi(int qnt) {
        String x = vendi_nome_prodotto.getSelectedItem().toString().substring(0, vendi_nome_prodotto.getSelectedItem().toString().indexOf("("));
        ResultSet rs = db.cerca_prodotto(x);
        String totale = "";
        double tmp = 0;
        try {
            while (rs.next()) {
                tmp = qnt * rs.getDouble("prezzo");
                totale = "" + tmp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totale;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainMenu = new javax.swing.JTabbedPane();
        Vendi = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        vendi_unitario = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_vendi = new javax.swing.JTable();
        vendi_button = new javax.swing.JButton();
        vendi_aggiungi = new javax.swing.JButton();
        vendi_storico = new javax.swing.JButton();
        vendi_nome_prodotto = new javax.swing.JComboBox<>();
        box_qnt_vendi = new javax.swing.JComboBox<>();
        tot_car = new javax.swing.JLabel();
        Acquista = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        acquista_prezzo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        acquista_totale = new javax.swing.JTextField();
        acquista_prodotto = new javax.swing.JTextField();
        acquista_quantita = new javax.swing.JTextField();
        acquista_aggiungi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_scontrino = new javax.swing.JTable();
        acquista_button = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        combo_acquista_fornitore = new javax.swing.JComboBox<>();
        acquista_storico = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        acquista_barcode = new javax.swing.JTextField();
        tot_car_ac = new javax.swing.JLabel();
        Magazzino = new javax.swing.JPanel();
        mag_tabella = new javax.swing.JScrollPane();
        table_magazzino = new javax.swing.JTable();
        mag_cerca = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        mag_cercabarcode = new javax.swing.JTextField();
        Fornitori = new javax.swing.JPanel();
        forn_tabella = new javax.swing.JScrollPane();
        table_fornitori = new javax.swing.JTable();
        forn_cerca = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btn_nuovofornitore = new javax.swing.JButton();
        btn_agg = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Prodotto");

        jLabel2.setText("Quantità");

        jLabel4.setText("Prezzo Unitario");

        vendi_unitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendi_unitarioActionPerformed(evt);
            }
        });

        table_vendi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "prodotto", "quantità", "prezzo", "totale"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_vendi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_vendiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                table_vendiMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(table_vendi);

        vendi_button.setText("Vendi");
        vendi_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendi_buttonActionPerformed(evt);
            }
        });

        vendi_aggiungi.setText("Aggiungi");
        vendi_aggiungi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendi_aggiungiActionPerformed(evt);
            }
        });

        vendi_storico.setText("Visualizza Storico Vendite");
        vendi_storico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendi_storicoActionPerformed(evt);
            }
        });

        vendi_nome_prodotto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vendi_nome_prodotto.setMaximumRowCount(10);
        vendi_nome_prodotto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleziona prodotto..." }));
        vendi_nome_prodotto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                vendi_nome_prodottoItemStateChanged(evt);
            }
        });
        vendi_nome_prodotto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendi_nome_prodottoMouseClicked(evt);
            }
        });
        vendi_nome_prodotto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendi_nome_prodottoActionPerformed(evt);
            }
        });

        box_qnt_vendi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0" }));
        box_qnt_vendi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box_qnt_vendiActionPerformed(evt);
            }
        });

        tot_car.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tot_car.setText("Totale:      0€");

        javax.swing.GroupLayout VendiLayout = new javax.swing.GroupLayout(Vendi);
        Vendi.setLayout(VendiLayout);
        VendiLayout.setHorizontalGroup(
            VendiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VendiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VendiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(VendiLayout.createSequentialGroup()
                        .addGroup(VendiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vendi_nome_prodotto, 0, 200, Short.MAX_VALUE)
                            .addComponent(vendi_unitario)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VendiLayout.createSequentialGroup()
                                .addComponent(vendi_aggiungi, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addComponent(box_qnt_vendi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 39, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(VendiLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(VendiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tot_car, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vendi_storico)
                            .addComponent(vendi_button, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16))
        );
        VendiLayout.setVerticalGroup(
            VendiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VendiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vendi_storico)
                .addGap(13, 13, 13)
                .addGroup(VendiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VendiLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tot_car, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(VendiLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vendi_nome_prodotto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(box_qnt_vendi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vendi_unitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(vendi_aggiungi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vendi_button)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        MainMenu.addTab("Vendi", Vendi);

        jLabel5.setText("Nome Prodotto");

        jLabel6.setText("Quantità");

        jLabel7.setText("Prezzo Unitario");

        acquista_prezzo.setText("0");
        acquista_prezzo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acquista_prezzoMouseClicked(evt);
            }
        });
        acquista_prezzo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acquista_prezzoActionPerformed(evt);
            }
        });
        acquista_prezzo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                acquista_prezzoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                acquista_prezzoKeyReleased(evt);
            }
        });

        jLabel8.setText("Totale");

        acquista_totale.setEditable(false);
        acquista_totale.setText("0");

        acquista_prodotto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acquista_prodottoActionPerformed(evt);
            }
        });

        acquista_quantita.setText("0");
        acquista_quantita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acquista_quantitaMouseClicked(evt);
            }
        });
        acquista_quantita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                acquista_quantitaKeyReleased(evt);
            }
        });

        acquista_aggiungi.setText("Aggiungi");
        acquista_aggiungi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acquista_aggiungiActionPerformed(evt);
            }
        });

        table_scontrino.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "prodotto", "quantità", "prezzo", "totale"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_scontrino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_scontrinoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_scontrino);

        acquista_button.setText("Acquista");
        acquista_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acquista_buttonActionPerformed(evt);
            }
        });

        jLabel9.setText("Fornitore");

        combo_acquista_fornitore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_acquista_fornitoreActionPerformed(evt);
            }
        });

        acquista_storico.setText("Visualizza Storico Acquisti");
        acquista_storico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acquista_storicoActionPerformed(evt);
            }
        });

        jLabel12.setText("Barcode");

        acquista_barcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acquista_barcodeActionPerformed(evt);
            }
        });

        tot_car_ac.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tot_car_ac.setText("Totale:      0€");

        javax.swing.GroupLayout AcquistaLayout = new javax.swing.GroupLayout(Acquista);
        Acquista.setLayout(AcquistaLayout);
        AcquistaLayout.setHorizontalGroup(
            AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AcquistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AcquistaLayout.createSequentialGroup()
                        .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(acquista_prodotto, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(combo_acquista_fornitore, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(acquista_totale, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(acquista_quantita)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(acquista_prezzo)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(acquista_barcode)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AcquistaLayout.createSequentialGroup()
                                .addGap(0, 272, Short.MAX_VALUE)
                                .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AcquistaLayout.createSequentialGroup()
                                        .addComponent(acquista_aggiungi, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(acquista_button, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(acquista_storico, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jScrollPane1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AcquistaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tot_car_ac, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        AcquistaLayout.setVerticalGroup(
            AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AcquistaLayout.createSequentialGroup()
                .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AcquistaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(acquista_storico))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AcquistaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AcquistaLayout.createSequentialGroup()
                        .addComponent(combo_acquista_fornitore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acquista_prodotto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acquista_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acquista_quantita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acquista_prezzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(acquista_totale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tot_car_ac, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acquista_aggiungi)
                    .addComponent(acquista_button))
                .addGap(18, 18, 18))
        );

        MainMenu.addTab("Acquista", Acquista);

        table_magazzino.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "prodotto", "quantità", "prezzo listino", "barcode"
            }
        ));
        mag_tabella.setViewportView(table_magazzino);

        mag_cerca.setText("Cerca nome");
        mag_cerca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mag_cercaMouseClicked(evt);
            }
        });
        mag_cerca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mag_cercaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mag_cercaKeyReleased(evt);
            }
        });

        jLabel10.setText("Cerca Prodotto");

        mag_cercabarcode.setText("Cerca barcode");
        mag_cercabarcode.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                mag_cercabarcodeInputMethodTextChanged(evt);
            }
        });
        mag_cercabarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mag_cercabarcodeActionPerformed(evt);
            }
        });
        mag_cercabarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mag_cercabarcodeKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout MagazzinoLayout = new javax.swing.GroupLayout(Magazzino);
        Magazzino.setLayout(MagazzinoLayout);
        MagazzinoLayout.setHorizontalGroup(
            MagazzinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MagazzinoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MagazzinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mag_tabella, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                    .addGroup(MagazzinoLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mag_cerca, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mag_cercabarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        MagazzinoLayout.setVerticalGroup(
            MagazzinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MagazzinoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mag_tabella, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(MagazzinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mag_cerca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(mag_cercabarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        MainMenu.addTab("Magazzino", Magazzino);

        table_fornitori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "nome", "p.iva", "indirizzo", "città", "nazione"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        forn_tabella.setViewportView(table_fornitori);

        forn_cerca.setText("Cerca nome");
        forn_cerca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                forn_cercaKeyReleased(evt);
            }
        });

        jLabel11.setText("Cerca Fornitore");

        btn_nuovofornitore.setText("Nuovo Fornitore");
        btn_nuovofornitore.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                btn_nuovofornitoreComponentShown(evt);
            }
        });
        btn_nuovofornitore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuovofornitoreActionPerformed(evt);
            }
        });

        btn_agg.setText("Aggiorna");
        btn_agg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aggActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FornitoriLayout = new javax.swing.GroupLayout(Fornitori);
        Fornitori.setLayout(FornitoriLayout);
        FornitoriLayout.setHorizontalGroup(
            FornitoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FornitoriLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FornitoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FornitoriLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(forn_cerca, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(forn_tabella))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FornitoriLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_agg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_nuovofornitore, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        FornitoriLayout.setVerticalGroup(
            FornitoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FornitoriLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(FornitoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_nuovofornitore)
                    .addComponent(btn_agg))
                .addGap(18, 18, 18)
                .addComponent(forn_tabella, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FornitoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forn_cerca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainMenu.addTab("Fornitori", Fornitori);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainMenu, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainMenu)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acquista_prodottoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acquista_prodottoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acquista_prodottoActionPerformed

    private void acquista_prezzoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acquista_prezzoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_acquista_prezzoActionPerformed

    private void acquista_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acquista_buttonActionPerformed
        // TODO add your handling code here:
        ArrayList<Acquisto> acquisto = new ArrayList<>();
        String fornitore = combo_acquista_fornitore.getSelectedItem().toString();

        String nome = "";
        int quantita = 0;
        String t = tot_car_ac.getText().toString().substring(tot_car_ac.getText().toString().indexOf(" "), tot_car_ac.getText().toString().indexOf("€"));
        double prezzo, totale_prodotto = 0, tot = Double.parseDouble(t);
        // Recupera tutti i dati della tabella
        for (int i = 0; i < model_s.getRowCount(); i++) {
            nome = (String) table_scontrino.getValueAt(i, 0);
            quantita = (int) table_scontrino.getValueAt(i, 1);
            prezzo = (double) table_scontrino.getValueAt(i, 2);
            totale_prodotto = (double) table_scontrino.getValueAt(i, 3); // Totale prodotto (unita * qnt)
            acquisto.add(new Acquisto(nome, quantita, prezzo, totale_prodotto));
        }
        try {
            db.aggiungiProdotto(scontrino);
            db.inserisci_acquisto(fornitore, tot);
            for (Acquisto a : acquisto) {
                db.inserisci_ac_prodotti(a.prodotto, a.quantita, a.prezzo_unita, a.totale_prodotto);
                db.aggiorna_prodotti_fornitori(fornitore, a.prodotto, a.prezzo_unita ); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        model_s.getDataVector().removeAllElements();
        model_s.fireTableDataChanged();
        model_m.getDataVector().removeAllElements();
        model_m.fireTableDataChanged();
        try {
            lista_magazzino();
        } catch (SQLException ex) {
            Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        scontrino.clear();
        tot_car_ac.setText("Totale: 0€");
        acquista_prodotto.setText("");
        acquista_barcode.setText("");
        acquista_quantita.setText("0");
        acquista_prezzo.setText("0");
        acquista_totale.setText("");
        acquisto = new ArrayList<Acquisto>();
    }//GEN-LAST:event_acquista_buttonActionPerformed


    private void vendi_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendi_buttonActionPerformed
        // TODO add your handling code here:
        ArrayList<VendiP> vendita = new ArrayList<>();
        String nome = "";
        int quantita = 0;
        double prezzo, totale_prodotto = 0;
        String t = tot_car.getText().toString().substring(tot_car.getText().toString().indexOf(" "), tot_car.getText().toString().indexOf("€"));
        double totale_vendita = Double.parseDouble(t);
        for (int i = 0; i < model_v.getRowCount(); i++) {
            nome = (String) table_vendi.getValueAt(i, 0);
            quantita = (int) table_vendi.getValueAt(i, 1);
            prezzo = (double) table_vendi.getValueAt(i, 2);
            totale_prodotto = (double) table_vendi.getValueAt(i, 3); // Totale prodotto (unita * qnt)
            System.out.println(nome + " " + quantita + " " + prezzo + " " + totale_prodotto);
            vendita.add(new VendiP(nome, quantita, totale_prodotto));
        }
        try {
            VendiP.salva_vendite(vendita, totale_vendita);
            for (VendiP v : vendita) {
                db.carica_ProdottiVendite(v.nome, v.quantita, v.totale_prodotto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        model_v.getDataVector().removeAllElements();
        model_v.fireTableDataChanged();
        vendita = new ArrayList<VendiP>();
        model_m.getDataVector().removeAllElements();
        model_m.fireTableDataChanged();
        box_qnt_vendi.removeAllItems();
        box_qnt_vendi.addItem(""+0);
        vendi_unitario.setText("0");
        v_flag = true;
        estrai_qtprodotto();
        try {
            vendi_prodotto();
            lista_magazzino();
        } catch (SQLException ex) {
            Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_vendi_buttonActionPerformed

    private void mag_cercabarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mag_cercabarcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mag_cercabarcodeActionPerformed

    private void acquista_storicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acquista_storicoActionPerformed
        // TODO add your handling code here:
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StroicoAcquisti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StroicoAcquisti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StroicoAcquisti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StroicoAcquisti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new StroicoAcquisti().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(StroicoAcquisti.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }//GEN-LAST:event_acquista_storicoActionPerformed

    private void vendi_storicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendi_storicoActionPerformed
        // TODO add your handling code here:
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StoricoVendite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StoricoVendite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StoricoVendite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StoricoVendite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new StoricoVendite().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }//GEN-LAST:event_vendi_storicoActionPerformed

    private void acquista_aggiungiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acquista_aggiungiActionPerformed
        // TODO add your handling code here:
        String nome = acquista_prodotto.getText();
        String barcode = acquista_barcode.getText();
        int qnt = Integer.parseInt(acquista_quantita.getText());
        double prezzo = Double.parseDouble(acquista_prezzo.getText());
        double totale = Double.parseDouble(acquista_totale.getText());
        scontrino.add(new Prodotto(nome, qnt, prezzo, totale, barcode));
        tot_ac += totale;
        tot_car_ac.setText("Totale: " + tot_ac + "€");
        model_s.insertRow(model_s.getRowCount(), new Object[]{nome, qnt, prezzo, totale});
    }//GEN-LAST:event_acquista_aggiungiActionPerformed

    private void acquista_prezzoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acquista_prezzoKeyPressed

    }//GEN-LAST:event_acquista_prezzoKeyPressed

    private void acquista_prezzoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acquista_prezzoKeyReleased
        // TODO add your handling code here:
        calcolo_totale();
    }//GEN-LAST:event_acquista_prezzoKeyReleased

    private void acquista_quantitaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acquista_quantitaKeyReleased
        // TODO add your handling code here:
        calcolo_totale();
    }//GEN-LAST:event_acquista_quantitaKeyReleased

    private void acquista_barcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acquista_barcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acquista_barcodeActionPerformed

    private void btn_nuovofornitoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuovofornitoreActionPerformed
        // TODO add your handling code here:
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Nuovo_fornitore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nuovo_fornitore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nuovo_fornitore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nuovo_fornitore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Nuovo_fornitore().setVisible(true);
            }
        });
    }//GEN-LAST:event_btn_nuovofornitoreActionPerformed

    private void btn_nuovofornitoreComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_btn_nuovofornitoreComponentShown
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_nuovofornitoreComponentShown

    //Campo ricerca
    private void forn_cercaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_forn_cercaKeyReleased
        // TODO add your handling code here:
        String cerca = forn_cerca.getText();
        try {
            lista_forn_cerca(cerca);
            
        } catch (SQLException ex) {
            Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_forn_cercaKeyReleased

    private void btn_aggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aggActionPerformed
        model_f.getDataVector().removeAllElements();
        model_f.fireTableDataChanged();
        try {
            lista_fornitori();
        } catch (SQLException ex) {
            Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_aggActionPerformed

    private void vendi_aggiungiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendi_aggiungiActionPerformed
        // TODO add your handling code here:
        String nome = vendi_nome_prodotto.getSelectedItem().toString();
        if (nome.equals("Seleziona prodotto...")) {
            JOptionPane.showMessageDialog(null, "Seleziona un prodotto!!");
        } else {
            model_v = (DefaultTableModel) table_vendi.getModel();
            String tmp = calcolo_totale_vendi(Integer.parseInt(box_qnt_vendi.getSelectedItem().toString()));
            nome = vendi_nome_prodotto.getSelectedItem().toString().substring(0, vendi_nome_prodotto.getSelectedItem().toString().indexOf("("));
            ResultSet rs = db.cerca_prodotto(nome);
            double prezzo = 0;
            int qnt = Integer.parseInt(box_qnt_vendi.getSelectedItem().toString());
            int qnt_maga = 0;
            try {
                while (rs.next()) {
                    prezzo = Double.parseDouble(rs.getString("prezzo"));
                    qnt_maga = Integer.parseInt(rs.getString("quantita"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            double totale = Double.parseDouble(tmp);
            tot += totale;
            tot_car.setText("Totale: " + tot + "€");
            model_v.insertRow(model_v.getRowCount(), new Object[]{nome, qnt, prezzo, totale});
        }


    }//GEN-LAST:event_vendi_aggiungiActionPerformed

    private void vendi_nome_prodottoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendi_nome_prodottoActionPerformed
        if(vendi == true) {
            estrai_qtprodotto();
            vendi_unitario.setText(calcolo_totale_vendi(Integer.parseInt(box_qnt_vendi.getSelectedItem().toString())));
            vendi = false;
            }
    }//GEN-LAST:event_vendi_nome_prodottoActionPerformed

    private void box_qnt_vendiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box_qnt_vendiActionPerformed
            
    }//GEN-LAST:event_box_qnt_vendiActionPerformed

    private void table_vendiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_vendiMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_table_vendiMouseEntered

    private void table_vendiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_vendiMouseClicked
        // TODO add your handling code here:
        if (table_vendi.getSelectedRow() != -1) {
            tot = tot - (double) table_vendi.getValueAt(table_vendi.getSelectedRow(), 3);
            tot_car.setText("Totale: " + tot + "€");
            // remove the selected row from the table model
            model_v.removeRow(table_vendi.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Prodotto eliminato");
        }
    }//GEN-LAST:event_table_vendiMouseClicked

    private void combo_acquista_fornitoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_acquista_fornitoreActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_combo_acquista_fornitoreActionPerformed

    private void table_scontrinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_scontrinoMouseClicked
        // TODO add your handling code here:
        if (table_scontrino.getSelectedRow() != -1) {
            tot_ac = tot_ac - (double) table_scontrino.getValueAt(table_scontrino.getSelectedRow(), 3);
            tot_car_ac.setText("Totale: " + tot_ac + "€");
            // remove the selected row from the table model
            model_s.removeRow(table_scontrino.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Prodotto eliminato");
        }
    }//GEN-LAST:event_table_scontrinoMouseClicked

    private void acquista_quantitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acquista_quantitaMouseClicked
        // TODO add your handling code here:
        acquista_quantita.selectAll();
    }//GEN-LAST:event_acquista_quantitaMouseClicked

    private void acquista_prezzoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acquista_prezzoMouseClicked
        // TODO add your handling code here:
        acquista_prezzo.selectAll();
    }//GEN-LAST:event_acquista_prezzoMouseClicked

    private void vendi_nome_prodottoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_vendi_nome_prodottoItemStateChanged
        // TODO add your handling code here:
        // TODO add your handling code here:
        
        

    }//GEN-LAST:event_vendi_nome_prodottoItemStateChanged

    private void mag_cercaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mag_cercaKeyReleased
        // TODO add your handling code here:
        
        String cerca = mag_cerca.getText();
        
        
        try {
            lista_mag_cerca(cerca);
            
        } catch (SQLException ex) {
            Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mag_cercaKeyReleased

    private void mag_cercaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mag_cercaMouseClicked
        // TODO add your handling code here:
        mag_cerca.selectAll();
    }//GEN-LAST:event_mag_cercaMouseClicked

    private void mag_cercaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mag_cercaKeyPressed
      
    }//GEN-LAST:event_mag_cercaKeyPressed

    private void mag_cercabarcodeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_mag_cercabarcodeInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_mag_cercabarcodeInputMethodTextChanged

    private void mag_cercabarcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mag_cercabarcodeKeyReleased
        // TODO add your handling code here:
        String cerca = mag_cercabarcode.getText();
        try {
            lista_mag_cerca_barcode(cerca);
            
        } catch (SQLException ex) {
            Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mag_cercabarcodeKeyReleased

    private void vendi_unitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendi_unitarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vendi_unitarioActionPerformed

    private void vendi_nome_prodottoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendi_nome_prodottoMouseClicked
        // TODO add your handling code here:
        vendi = true;
    }//GEN-LAST:event_vendi_nome_prodottoMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NegozioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NegozioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NegozioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NegozioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NegozioForm().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(NegozioForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        initCombo();
    }

    private static void initCombo() {
        box_qnt_vendi.addItemListener(event -> {
            // The item affected by the event.
            String item = (String) event.getItem();
            if (event.getStateChange() == ItemEvent.SELECTED) {
                String a = calcolo_totale_vendi(Integer.parseInt(box_qnt_vendi.getSelectedItem().toString()));
                vendi_unitario.setText(a);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Acquista;
    private javax.swing.JPanel Fornitori;
    private javax.swing.JPanel Magazzino;
    private javax.swing.JTabbedPane MainMenu;
    private javax.swing.JPanel Vendi;
    public javax.swing.JButton acquista_aggiungi;
    public javax.swing.JTextField acquista_barcode;
    public javax.swing.JButton acquista_button;
    public javax.swing.JTextField acquista_prezzo;
    public javax.swing.JTextField acquista_prodotto;
    public javax.swing.JTextField acquista_quantita;
    public javax.swing.JButton acquista_storico;
    public javax.swing.JTextField acquista_totale;
    public static javax.swing.JComboBox<String> box_qnt_vendi;
    public javax.swing.JButton btn_agg;
    public javax.swing.JButton btn_nuovofornitore;
    public javax.swing.JComboBox<String> combo_acquista_fornitore;
    public javax.swing.JTextField forn_cerca;
    private javax.swing.JScrollPane forn_tabella;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextField mag_cerca;
    public javax.swing.JTextField mag_cercabarcode;
    private javax.swing.JScrollPane mag_tabella;
    public javax.swing.JTable table_fornitori;
    public javax.swing.JTable table_magazzino;
    public javax.swing.JTable table_scontrino;
    public javax.swing.JTable table_vendi;
    private javax.swing.JLabel tot_car;
    private javax.swing.JLabel tot_car_ac;
    public javax.swing.JButton vendi_aggiungi;
    public javax.swing.JButton vendi_button;
    public static javax.swing.JComboBox<String> vendi_nome_prodotto;
    public javax.swing.JButton vendi_storico;
    public static javax.swing.JTextField vendi_unitario;
    // End of variables declaration//GEN-END:variables
}
