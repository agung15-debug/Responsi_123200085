package dao;

import controller.ConnectorTransaksi;
import model.ModelTransaksi;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class daoTransaksi implements interfaceTransaksi{
Connection connection;
    final String insert = "INSERT INTO transactions (id_trans, nama_barang, nama_kasir, qty, price_per_qty, discount, price_total) VALUES (?, ?, ?, ?, ?, ?, ?);";
    final String update = "UPDATE transactions SET id_trans = ?, nama_barang = ?, nama_kasir = ?, qty = ?, price_per_qty = ?, discount = ?, price_total = ? WHERE id_trans=? ;";
    final String delete = "DELETE FROM transactions WHERE id_trans=? ;";
    final String select = "SELECT * FROM transactions ORDER BY id_trans ASC;";
    
    public daoTransaksi(){
        connection = ConnectorTransaksi.connection();
    }
    
    
    @Override
    public void insert(ModelTransaksi transaksi) {
       PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, transaksi.getIdTransaksi());
            statement.setString(2, transaksi.getNamaBarang());
            statement.setString(3, transaksi.getNamaKasir());
            statement.setInt(4, transaksi.getKuantitas());
            statement.setInt(5, transaksi.getHarga());
            statement.setInt(6, transaksi.getDiskon());
            statement.setInt(7, transaksi.getHargaTotal());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(ModelTransaksi transaksi) {
         PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, transaksi.getIdTransaksi());
            statement.setString(2, transaksi.getNamaBarang());
            statement.setString(3, transaksi.getNamaKasir());
            statement.setInt(4, transaksi.getKuantitas());
            statement.setInt(5, transaksi.getHarga());
            statement.setInt(6, transaksi.getDiskon());
            statement.setInt(7, transaksi.getHargaTotal());
            statement.setString(8, transaksi.getIdSebelum());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<ModelTransaksi> getData() {
         List<ModelTransaksi> listTransaksi = null;
       try{
            listTransaksi = new ArrayList<ModelTransaksi>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                ModelTransaksi transaksi = new ModelTransaksi();
                transaksi.setIdTransaksi(rs.getString("id_trans"));
                transaksi.setNamaBarang(rs.getString("nama_barang"));
                transaksi.setNamaKasir(rs.getString("nama_kasir"));
                transaksi.setKuantitas(rs.getInt("qty"));
                transaksi.setHarga(rs.getInt("price_per_qty"));
                transaksi.setDiskon(rs.getInt("discount"));
                transaksi.setHargaTotal(rs.getInt("price_total"));
                listTransaksi.add(transaksi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listTransaksi;
    }
}
