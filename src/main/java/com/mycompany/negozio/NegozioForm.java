/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.negozio;

/**
 *
 * @author luca
 */
public class NegozioForm extends javax.swing.JFrame {

    /**
     * Creates new form NegozioForm
     */
    public NegozioForm() {
        initComponents();
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
        vendi_nome = new javax.swing.JTextField();
        vendi_quantità = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        vendi_prezzo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        vendi_totale = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        vendi_button = new javax.swing.JButton();
        vendi_aggiungi = new javax.swing.JButton();
        vendi_storico = new javax.swing.JButton();
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
        jTable1 = new javax.swing.JTable();
        acquista_button = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        acquista_fornitore = new javax.swing.JComboBox<>();
        acquista_storico = new javax.swing.JButton();
        Magazzino = new javax.swing.JPanel();
        mag_tabella = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        mag_cerca = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        mag_cercabarcode = new javax.swing.JTextField();
        Fornitori = new javax.swing.JPanel();
        forn_tabella = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        forn_cerca = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        vendi_nome.setText("nome prodotto");
        vendi_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendi_nomeActionPerformed(evt);
            }
        });

        vendi_quantità.setText("quantità");

        jLabel1.setText("nome prodotto");

        jLabel2.setText("quantità");

        jLabel3.setText("prezzo listino");

        vendi_prezzo.setText("prezzo listino");

        jLabel4.setText("totale");

        vendi_totale.setText("totale");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "prodotto", "quantità", "prezzo", "totale"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        vendi_button.setText("Vendi");
        vendi_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendi_buttonActionPerformed(evt);
            }
        });

        vendi_aggiungi.setText("Aggiungi");

        vendi_storico.setText("Visualizza Storico Vendite");
        vendi_storico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendi_storicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout VendiLayout = new javax.swing.GroupLayout(Vendi);
        Vendi.setLayout(VendiLayout);
        VendiLayout.setHorizontalGroup(
            VendiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VendiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VendiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vendi_aggiungi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vendi_totale, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(vendi_nome, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vendi_quantità)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vendi_prezzo)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(VendiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VendiLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(VendiLayout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(vendi_storico))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VendiLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vendi_button, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        VendiLayout.setVerticalGroup(
            VendiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VendiLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(VendiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(VendiLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vendi_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vendi_quantità, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vendi_prezzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vendi_totale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(VendiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vendi_button)
                    .addComponent(vendi_aggiungi))
                .addGap(18, 18, 18)
                .addComponent(vendi_storico)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        MainMenu.addTab("Vendi", Vendi);

        jLabel5.setText("nome prodotto");

        jLabel6.setText("quantità");

        jLabel7.setText("prezzo unitario");

        acquista_prezzo.setText("prezzo unitario");
        acquista_prezzo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acquista_prezzoActionPerformed(evt);
            }
        });

        jLabel8.setText("totale");

        acquista_totale.setText("totale");

        acquista_prodotto.setText("nome prodotto");
        acquista_prodotto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acquista_prodottoActionPerformed(evt);
            }
        });

        acquista_quantita.setText("quantità");

        acquista_aggiungi.setText("Aggiungi");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "prodotto", "quantità", "prezzo", "totale"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        acquista_button.setText("Acquista");
        acquista_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acquista_buttonActionPerformed(evt);
            }
        });

        jLabel9.setText("Fornitore");

        acquista_fornitore.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        acquista_storico.setText("Visualizza Storico Acquisti");
        acquista_storico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acquista_storicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AcquistaLayout = new javax.swing.GroupLayout(Acquista);
        Acquista.setLayout(AcquistaLayout);
        AcquistaLayout.setHorizontalGroup(
            AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AcquistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(acquista_totale, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(acquista_prodotto, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(acquista_quantita)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(acquista_prezzo)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(acquista_aggiungi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(acquista_fornitore, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AcquistaLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AcquistaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(acquista_storico)
                            .addComponent(acquista_button, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))))
        );
        AcquistaLayout.setVerticalGroup(
            AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AcquistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AcquistaLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acquista_fornitore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acquista_prodotto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acquista_quantita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acquista_prezzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acquista_totale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(AcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AcquistaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(acquista_button)
                        .addGap(18, 18, 18)
                        .addComponent(acquista_storico)
                        .addContainerGap(19, Short.MAX_VALUE))
                    .addGroup(AcquistaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(acquista_aggiungi)
                        .addGap(16, 16, 16))))
        );

        MainMenu.addTab("Acquista", Acquista);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "prodotto", "quantità", "prezzo listino", "barcode"
            }
        ));
        mag_tabella.setViewportView(jTable3);

        mag_cerca.setText("Cerca nome");

        jLabel10.setText("Cerca Prodotto");

        mag_cercabarcode.setText("Cerca barcode");
        mag_cercabarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mag_cercabarcodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MagazzinoLayout = new javax.swing.GroupLayout(Magazzino);
        Magazzino.setLayout(MagazzinoLayout);
        MagazzinoLayout.setHorizontalGroup(
            MagazzinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MagazzinoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MagazzinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mag_tabella, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
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
                .addContainerGap(47, Short.MAX_VALUE))
        );

        MainMenu.addTab("Magazzino", Magazzino);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "nome", "indirizzo", "città", "nazione"
            }
        ));
        forn_tabella.setViewportView(jTable4);

        forn_cerca.setText("Cerca nome");

        jLabel11.setText("Cerca Fornitore");

        javax.swing.GroupLayout FornitoriLayout = new javax.swing.GroupLayout(Fornitori);
        Fornitori.setLayout(FornitoriLayout);
        FornitoriLayout.setHorizontalGroup(
            FornitoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FornitoriLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FornitoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(forn_tabella, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                    .addGroup(FornitoriLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(forn_cerca, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        FornitoriLayout.setVerticalGroup(
            FornitoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FornitoriLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(forn_tabella, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(FornitoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forn_cerca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(34, Short.MAX_VALUE))
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

    private void vendi_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendi_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vendi_nomeActionPerformed

    private void acquista_prodottoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acquista_prodottoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acquista_prodottoActionPerformed

    private void acquista_prezzoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acquista_prezzoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acquista_prezzoActionPerformed

    private void acquista_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acquista_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acquista_buttonActionPerformed

    private void vendi_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendi_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vendi_buttonActionPerformed

    private void mag_cercabarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mag_cercabarcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mag_cercabarcodeActionPerformed

    private void acquista_storicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acquista_storicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acquista_storicoActionPerformed

    private void vendi_storicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendi_storicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vendi_storicoActionPerformed

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
                new NegozioForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Acquista;
    private javax.swing.JPanel Fornitori;
    private javax.swing.JPanel Magazzino;
    private javax.swing.JTabbedPane MainMenu;
    private javax.swing.JPanel Vendi;
    private javax.swing.JButton acquista_aggiungi;
    private javax.swing.JButton acquista_button;
    private javax.swing.JComboBox<String> acquista_fornitore;
    private javax.swing.JTextField acquista_prezzo;
    private javax.swing.JTextField acquista_prodotto;
    private javax.swing.JTextField acquista_quantita;
    private javax.swing.JButton acquista_storico;
    private javax.swing.JTextField acquista_totale;
    private javax.swing.JTextField forn_cerca;
    private javax.swing.JScrollPane forn_tabella;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField mag_cerca;
    private javax.swing.JTextField mag_cercabarcode;
    private javax.swing.JScrollPane mag_tabella;
    private javax.swing.JButton vendi_aggiungi;
    private javax.swing.JButton vendi_button;
    private javax.swing.JTextField vendi_nome;
    private javax.swing.JTextField vendi_prezzo;
    private javax.swing.JTextField vendi_quantità;
    private javax.swing.JButton vendi_storico;
    private javax.swing.JTextField vendi_totale;
    // End of variables declaration//GEN-END:variables
}
