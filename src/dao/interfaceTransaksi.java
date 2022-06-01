package dao;

import model.ModelTransaksi;
import java.util.List;
 
public interface interfaceTransaksi {
     public void insert(ModelTransaksi transaksi);

    public void update(ModelTransaksi transaksi);

    public void delete(String id);

    public List<ModelTransaksi> getData();
}