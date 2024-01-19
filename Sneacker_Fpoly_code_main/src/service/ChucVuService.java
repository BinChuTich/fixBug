/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.ChucVu;
import repository.ChucVuRepository;

/**
 *
 * @author Chien Duong
 */
public class ChucVuService {
    ChucVuRepository cvr = new ChucVuRepository();


    public ArrayList<ChucVu> getAllChucVu() {
        ArrayList<ChucVu> list = new ArrayList<>();
        ArrayList<ChucVu> cv = cvr.getAllChucVu();
        for (ChucVu x : cv) {
            list.add(new ChucVu(x.getIdchucVu(), x.getMaChucVu(), x.getTenChucVu(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai()));
        }
        return list;
    }
     public ChucVu insertCV(ChucVu cvm) {
        ArrayList<ChucVu> list = cvr.getAllChucVu();
        for (ChucVu cv : list) {
            if (cv.getMaChucVu().equalsIgnoreCase(cvm.getMaChucVu())) {
                return null;
            }
        }
        var x = cvr.insertCV(new ChucVu(cvm.getMaChucVu(), cvm.getTenChucVu(), cvm.getTrangThai()));
        return new ChucVu(x.getMaChucVu(), x.getTenChucVu(), x.getTrangThai());
    }

    public ChucVu updateCV(ChucVu cvm) {
        var x = cvr.updateCV(new ChucVu(cvm.getMaChucVu(), cvm.getTenChucVu(), cvm.getTrangThai()));
        return new ChucVu(x.getMaChucVu(), x.getTenChucVu(), x.getTrangThai());
    }

    public Integer deleteCV(String id) {
        return cvr.deleteCV(id);
    }
}
