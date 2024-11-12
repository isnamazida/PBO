/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelass;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * @author na
 */
public class category {
    int category_id;
    String category_name;
    
    private Connection konek;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
        public category() throws SQLException{
    koneksi koneksi = new koneksi();
    konek = koneksi.konekDB();
     if (konek == null) {
        System.out.println("Koneksi ke database gagal");
        JOptionPane.showMessageDialog(null, "Koneksi ke database gagal");
    } else {
        System.out.println("Koneksi berhasil");
    }
}

    
    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    public int getCategory_id(){
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    
    public void tambahKategori(){
      query = "INSERT INTO category VALUES(?,?)";
      
        try {
            ps = konek.prepareStatement(query);
            ps.setInt(1, category_id);
            ps.setString(2, category_name);
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Data GagalDitambahkan");
        }
            
    }
   
    public ResultSet tampilKategori() {
        query = "SELECT * FROM category";
        try {
            st = konek.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Data Gagal Tampil");
        }
        return rs;
    }
    public void hapusKategori() {
        query = "DELETE FROM category WHERE category_id = ?";
        
        try {
            ps = konek.prepareStatement(query);
            ps.setInt(1, category_id);
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
        }
    }
    public void ubahKategori() {
        
        query = "UPDATE category SET"
                + "category_name = ?"
                + "WHERE category_id = ?";
        
        try {
            ps = konek.prepareStatement(query);
            ps.setString(1, category_name);
            ps.setInt(2, category_id);
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "data gagal diubah");
        }
    }
    
    

    public ResultSet tampilComboBox() {
        query = "SELECT category_name FROM category";
        try {
            st = konek.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Data Gagal Tampil");
        }
        return rs;
        }

    public ResultSet konversi() {
    query = "SELECT category_id FROM category WHERE category_name = ?";
        try {
            ps = konek.prepareStatement(query);
            ps.setString(1, category_name);
            
            rs = ps.executeQuery();
            System.out.println("data masuk");
        } catch (SQLException sQLException) {
            System.out.println("data tidak masuk");
        }
        return rs;
    }
 
    public ResultSet autoID() {
        query = "SELECT category_id AS ID FROM category ORDER BY category_id DESC LIMIT 1";
        
        try {
            st = konek.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Data Gagal Tampil");
        }
        return rs;
        }
}
