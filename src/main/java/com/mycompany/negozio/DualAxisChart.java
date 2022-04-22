/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.negozio;

import static com.mycompany.negozio.NegozioForm.db;
import static com.mycompany.negozio.PieChartForm.createDemoPanel;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author luca
 */
public class DualAxisChart extends javax.swing.JFrame {

    /**
     * Creates new form DualAxisChart
     */
    public DualAxisChart() throws SQLException { 
      this.add(createDemoPanel( ));
      
   }
    
    public static String getMonth(int month) {
    return new DateFormatSymbols().getMonths()[month-1];
    }
    
    public static CategoryDataset createDataset1(){
       DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       String etichetta = "Vendite";
       String etichetta2 = "Guadagni";
        

        // create the dataset...
        for(int i=1; i<13; i++){
            dataset.addValue(db.totaleVenditeMese(i), etichetta, getMonth(i));
            dataset.addValue(db.totaleGuadagniMese(i), etichetta2, getMonth(i));
        }

       return dataset;
    }
    
    
    private static JFreeChart createChart() {
      CategoryDataset dataset1 = createDataset1();
      
      JFreeChart chart = ChartFactory.createBarChart(
            "Dual Axis Chart",        // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset1,                 // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips?
            false                     // URL generator?  Not required...
        );
      
        chart.setBackgroundPaint(Color.white);
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

//        CategoryDataset dataset2 = createDataset2();
//        plot.setDataset(1, dataset2);
//        plot.mapDatasetToRangeAxis(1, 1);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
        final ValueAxis axis2 = new NumberAxis("Secondary");
        plot.setRangeAxis(1, axis2);

//        LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
//        renderer2.setToolTipGenerator(new StandardCategoryToolTipGenerator());
//        plot.setRenderer(1, renderer2);
//        plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
//        // OPTIONAL CUSTOMISATION COMPLETED.

        // add the chart to a panel...
        
        

      return chart;
   }
   
   public static JPanel createDemoPanel( ) throws SQLException {
      JFreeChart chart = createChart();
      System.out.println(chart.getTitle());
      ChartPanel chartPanel = new ChartPanel(chart);
      chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
      return chartPanel;
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DualAxisChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DualAxisChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DualAxisChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DualAxisChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DualAxisChart().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DualAxisChart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}