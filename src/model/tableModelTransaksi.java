package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class tableModelTransaksi extends AbstractTableModel{
    List<ModelTransaksi> listTransaksi;

    public tableModelTransaksi(List<ModelTransaksi> listTransaksi) {
        this.listTransaksi = listTransaksi;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public int getRowCount() {
        return listTransaksi.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID Transaksi";
            case 1:
                return "Nama Kasir";
            case 2:
                return "Nama Barang";
            case 3:
                return "Jumlah Barang";
            case 4:
                return "Harga Satuan";
            case 5:
                return "Diskon";
            case 6:
                return "Harga Total";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return listTransaksi.get(row).getIdTransaksi();
            case 1:
                return listTransaksi.get(row).getNamaKasir();
            case 2:
                return listTransaksi.get(row).getNamaBarang();
            case 3:
                return listTransaksi.get(row).getKuantitas();
            case 4:
                return listTransaksi.get(row).getHarga();
            case 5:
                return listTransaksi.get(row).getDiskon();
            case 6:
                return listTransaksi.get(row).getHargaTotal();
            default:
                return null;
        }
    }
}
