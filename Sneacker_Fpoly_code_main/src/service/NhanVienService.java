/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.NhanVien;
import repository.ChucVuRepository;
import repository.NhanVienRepository;
import view.jplNhanVien;

/**
 *
 * @author Chien Duong
 */
public class NhanVienService {
  NhanVienRepository nvr = new NhanVienRepository();
    public ArrayList<NhanVien> getAllNV() {
        ArrayList<NhanVien> list = new ArrayList<>();
        ArrayList<NhanVien> ds = nvr.getAllNV();
        for (NhanVien x : ds) {
            list.add(new NhanVien(x.getId(), x.getMaNV(), x.getHoVaTen(), x.getNgaySinh(), x.getGioiTinh(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getMatKhau(), x.getIdCV(), x.getHinh(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai()));
        }
        return list;
    }
    public ArrayList<NhanVien> getNVLam() {
        ArrayList<NhanVien> list = new ArrayList<>();
        ArrayList<NhanVien> ds = nvr.getNVLam();
        for (NhanVien x : ds) {
            list.add(new NhanVien(x.getId(), x.getMaNV(), x.getHoVaTen(), x.getNgaySinh(), x.getGioiTinh(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getMatKhau(), x.getIdCV(), x.getHinh(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai()));
        }
        return list;
    }
      public ArrayList<NhanVien> getNVLamByCV(String tenCV) {
        ArrayList<NhanVien> list = new ArrayList<>();
        ArrayList<NhanVien> ds = nvr.getNVLamByCV(tenCV);
        for (NhanVien x : ds) {
            list.add(new NhanVien(x.getId(), x.getMaNV(), x.getHoVaTen(), x.getNgaySinh(), x.getGioiTinh(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getMatKhau(), x.getIdCV(), x.getHinh(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai()));
        }
        return list;
    }
      public ArrayList<NhanVien> getNVLamBySdt(String sdt) {
        ArrayList<NhanVien> list = new ArrayList<>();
        ArrayList<NhanVien> ds = nvr.getNVLamBySdt(sdt);
        for (NhanVien x : ds) {
            list.add(new NhanVien(x.getId(), x.getMaNV(), x.getHoVaTen(), x.getNgaySinh(), x.getGioiTinh(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getMatKhau(), x.getIdCV(), x.getHinh(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai()));
        }
        return list;
    }
      public ArrayList<NhanVien> getNVNghi() {
        ArrayList<NhanVien> list = new ArrayList<>();
        ArrayList<NhanVien> ds = nvr.getNVNghi();
        for (NhanVien x : ds) {
            list.add(new NhanVien(x.getId(), x.getMaNV(), x.getHoVaTen(), x.getNgaySinh(), x.getGioiTinh(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getMatKhau(), x.getIdCV(), x.getHinh(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai()));
        }
        return list;
    }
        public ArrayList<NhanVien> getNVNghiBySdt(String sdt) {
        ArrayList<NhanVien> list = new ArrayList<>();
        ArrayList<NhanVien> ds = nvr.getNVNghiBySdt(sdt);
        for (NhanVien x : ds) {
            list.add(new NhanVien(x.getId(), x.getMaNV(), x.getHoVaTen(), x.getNgaySinh(), x.getGioiTinh(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getMatKhau(), x.getIdCV(), x.getHinh(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai()));
        }
        return list;
    }
          public ArrayList<NhanVien> getNVNghiByCV(String tenCV) {
        ArrayList<NhanVien> list = new ArrayList<>();
        ArrayList<NhanVien> ds = nvr.getNVNghiByCV(tenCV);
        for (NhanVien x : ds) {
            list.add(new NhanVien(x.getId(), x.getMaNV(), x.getHoVaTen(), x.getNgaySinh(), x.getGioiTinh(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getMatKhau(), x.getIdCV(), x.getHinh(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai()));
        }
        return list;
    }
    public NhanVien insertNV(NhanVien nvm) {
        ArrayList<NhanVien> ds = nvr.getAllNV();
        for (NhanVien nvien : ds) {
            if (nvien.getMaNV().equalsIgnoreCase(nvm.getMaNV())) {
                return null;
            }
        }
        var x = nvr.insertNV(new NhanVien(nvm.getId(), nvm.getMaNV(), nvm.getHoVaTen(), nvm.getNgaySinh(), nvm.getGioiTinh(), nvm.getDiaChi(), nvm.getSdt(), nvm.getEmail(), nvm.getMatKhau(), nvm.getIdCV(), nvm.getHinh(), nvm.getNgayTao(), nvm.getNgaySua(), nvm.getTrangThai()));
        return new NhanVien(x.getId(), x.getMaNV(), x.getHoVaTen(), x.getNgaySinh(), x.getGioiTinh(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getMatKhau(), x.getIdCV(), x.getHinh(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai());

    }

    public NhanVien updateNV(NhanVien nvm) {
        var x = nvr.updateNV(new NhanVien(nvm.getId(), nvm.getMaNV(), nvm.getHoVaTen(), nvm.getNgaySinh(), nvm.getGioiTinh(), nvm.getDiaChi(), nvm.getSdt(), nvm.getEmail(), nvm.getMatKhau(), nvm.getIdCV(), nvm.getHinh(), nvm.getNgayTao(), nvm.getNgaySua(), nvm.getTrangThai()));
        return new NhanVien(x.getId(), x.getMaNV(), x.getHoVaTen(), x.getNgaySinh(), x.getGioiTinh(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getMatKhau(), x.getIdCV(), x.getHinh(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai());

    }

     public Integer deleteNV(String id) {
        return nvr.deleteNV(id);
    }
}
