package controller;

import dao.daoTransaksi;
import dao.interfaceTransaksi;
import model.ModelTransaksi;
import model.tableModelTransaksi;
import view.viewTransaksi;
import java.util.List;
import javax.swing.JOptionPane;

public class controllerTransaksi {
    viewTransaksi frame;
    interfaceTransaksi infcTrans;
    List<ModelTransaksi> listTransaksi;
    String idSebelum;

    public controllerTransaksi(viewTransaksi frame) {
        this.frame = frame;
        infcTrans = new daoTransaksi();
        listTransaksi = infcTrans.getData();
    }
    
    public void loadTable(){
        listTransaksi = infcTrans.getData();
        tableModelTransaksi tmt = new tableModelTransaksi(listTransaksi);
        frame.getTableTransaksi().setModel(tmt);
    }
    
    public void tampilkanData(int row){
        ModelTransaksi transaksi = listTransaksi.get(row);
        this.idSebelum = transaksi.getIdTransaksi();
        frame.settIdTransaksi(transaksi.getIdTransaksi());
        frame.settNamaKasir(transaksi.getNamaKasir());
        frame.settNamaBarang(transaksi.getNamaBarang());
        frame.setHarga(transaksi.getHarga());
        frame.setJumlah(transaksi.getKuantitas());
        frame.setDiskon(transaksi.getDiskon());
        frame.setHargaTotal(transaksi.getHargaTotal());
    }
    
    public void insertData(){
        boolean check = frame.gettIdTransaksi().getText().equals("")&&frame.gettNamaBarang().getText().equals("")&&frame.gettNamaKasir().getText().equals("")&&frame.getHarga().getValue().equals(0) && frame.getJumlah().getValue().equals(0);
        if(!check){
            ModelTransaksi transaksi = new ModelTransaksi();
            transaksi.setIdTransaksi(frame.gettIdTransaksi().getText());
            transaksi.setNamaKasir(frame.gettNamaKasir().getText());
            transaksi.setNamaBarang(frame.gettNamaBarang().getText());
            transaksi.setKuantitas((int)frame.getJumlah().getValue());
            transaksi.setHarga((int)frame.getHarga().getValue());
            transaksi.setDiskon((int)frame.getDiskon().getValue());
            transaksi.setHargaTotal((int)frame.getHargaTotal().getValue());
            infcTrans.insert(transaksi);
            JOptionPane.showMessageDialog(null, "Transaksi Berhasil Ditambahkan");
        }
        else{
            JOptionPane.showMessageDialog(null, "Data Transaksi tidak boleh kosong");
        }
    }
    
    public void updateData(){
     boolean check = frame.gettIdTransaksi().getText().equals("")&&frame.gettNamaBarang().getText().equals("")&&frame.gettNamaKasir().getText().equals("")&&frame.getHarga().getValue().equals(0) && frame.getJumlah().getValue().equals(0);
        if(!check){
            ModelTransaksi transaksi = new ModelTransaksi();
            transaksi.setIdTransaksi(frame.gettIdTransaksi().getText());
            transaksi.setNamaKasir(frame.gettNamaKasir().getText());
            transaksi.setNamaBarang(frame.gettNamaBarang().getText());
            transaksi.setKuantitas((int)frame.getJumlah().getValue());
            transaksi.setHarga((int)frame.getHarga().getValue());
            transaksi.setDiskon((int)frame.getDiskon().getValue());
            transaksi.setHargaTotal((int)frame.getHargaTotal().getValue());
            transaksi.setIdSebelum(idSebelum);
            infcTrans.update(transaksi);
            JOptionPane.showMessageDialog(null, "Transaksi Berhasil Diubah");
        }
        else{
            JOptionPane.showMessageDialog(null, "Data Transaksi tidak boleh kosong");
        }
    }
    
    public void hapus(int row){
        ModelTransaksi transaksiHapus = listTransaksi.get(row);
        int input = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin untuk Menghapus data Ini ?", "Konfimasi Hapus Data", JOptionPane.OK_CANCEL_OPTION);
        if(input == 0){
            infcTrans.delete(transaksiHapus.getIdTransaksi());
        }
        else{
            loadTable();
        }
    }
    public void hitungTotal(){
        int harga = (Integer)frame.getHarga().getValue();
        int jumlah = (Integer) frame.getJumlah().getValue();
        int bDiskon = (Integer) frame.getDiskon().getValue();
        double diskon = bDiskon/100.0;
        int hargaTotal = (int)Math.round(((1-diskon)*harga)*jumlah);
        frame.setHargaTotal(hargaTotal);
    }
    public void clearAll(){
        frame.settIdTransaksi("");
        frame.settNamaKasir("");
        frame.settNamaBarang("");
        frame.setHarga(0);
        frame.setJumlah(0);
        frame.setDiskon(0);
        frame.setHargaTotal(0);
    }
}
