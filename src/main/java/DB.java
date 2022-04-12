
import com.mycompany.negozio.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luca
 */
public class DB {
    static ResultSet rs = null;
    static Connection connection = null;
    static Statement stm = null;
    private Connect conObj = new Connect();
    private Connection con = conObj.createConnection();
    
    public boolean cercaProdotto(String nome){
        boolean verifica = false;
        String query = "SELECT id FROM prodotto WHERE nome = ?";
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
    
    public void aggiungiProdotto(String nome, int qt, double prezzo, String barcode){
        if(this.cercaProdotto(nome)){
            this.aggiornaProdotto()
        }else{
            String query_aggiungi = "UPDATE prodotto (barcode,nome,quantita,listino";
        };
    }
    
    
}
