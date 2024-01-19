/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.NhanVien;
import repository.ChucVuRepository1;
import repository.NhanVienRepository1;

/**
 *
 * @author Chien Duong
 */
public class NhanVienService1 {

    NhanVienRepository1 nhanVienRepository1 = new NhanVienRepository1();

    public ArrayList<NhanVien> getALLNV() {
        return nhanVienRepository1.getAllNV();
    }

    public ArrayList<NhanVien> getNVLAM() {
        return nhanVienRepository1.getNVLAM();
    }

    public ArrayList<NhanVien> getNVNGHI() {
        return nhanVienRepository1.getNVNGHI();
    }

    public ArrayList<NhanVien> getDLNVLAMBYCV(String tenCV) {
        return nhanVienRepository1.getNVlamByCV(tenCV);
    }

    public ArrayList<NhanVien> getNVbyID(String id) {
        return nhanVienRepository1.getNVByID(id);
    }

    public ArrayList<NhanVien> getNVLamBySdt(String sdt) {
        return nhanVienRepository1.getNVNghiBySdt(sdt);
    }
public ArrayList<NhanVien> getNVNghiByCV(String TenCV) {
        return nhanVienRepository1.getNVNghiBySdt(TenCV);
    }
    public ArrayList<NhanVien> getNVNghiBySdt(String sdt) {
        return nhanVienRepository1.getNVNghiBySdt(sdt);
    }

    public NhanVien insertNV(NhanVien nvm) {
        return nhanVienRepository1.insertNV(nvm);
    }

    public NhanVien UpdateNV(NhanVien nvm) {
        return nhanVienRepository1.updateNV(nvm);
    }
}
