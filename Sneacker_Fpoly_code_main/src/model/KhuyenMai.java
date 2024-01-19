/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class KhuyenMai {
 private String id;
 private String maKhuyenMai;
 private String tenKhuyenMai;
 private BigDecimal giaTri;
 private BigDecimal giamToiDa;
 private Date ngayBatDau;
 private Date ngayKetThuc;
 private String hinhThucApDung;
 private String apDungGiamGia;
 private String loaiGiamGia;
 private Date ngayTao;
 private Date ngaySua;
 private int trangThai;

    public KhuyenMai() {
    }

    public KhuyenMai(String id, String maKhuyenMai, String tenKhuyenMai, BigDecimal giaTri, BigDecimal giamToiDa, Date ngayBatDau, Date ngayKetThuc, String hinhThucApDung, String apDungGiamGia, String loaiGiamGia, Date ngayTao, Date ngaySua, int trangThai) {
        this.id = id;
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.giaTri = giaTri;
        this.giamToiDa = giamToiDa;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hinhThucApDung = hinhThucApDung;
        this.apDungGiamGia = apDungGiamGia;
        this.loaiGiamGia = loaiGiamGia;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, String hinhThucApDung, String loaiGiamGia, BigDecimal giaTri, BigDecimal giamToiDa, java.sql.Date ngayBatDau, java.sql.Date ngayKetThuc, int trangThai) {
        this.id = id;
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.giaTri = giaTri;
        this.giamToiDa = giamToiDa;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hinhThucApDung = hinhThucApDung;
        this.apDungGiamGia = apDungGiamGia;
        this.loaiGiamGia = loaiGiamGia;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, BigDecimal giaTri, BigDecimal giamToiDa, Date ngayBatDau, Date ngayKetThuc, String hinhThucApDung, int trangThai) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.giaTri = giaTri;
        this.giamToiDa = giamToiDa;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hinhThucApDung = hinhThucApDung;
        this.trangThai = trangThai;
    }


    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public BigDecimal getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(BigDecimal giaTri) {
        this.giaTri = giaTri;
    }

    public BigDecimal getGiamToiDa() {
        return giamToiDa;
    }

    public void setGiamToiDa(BigDecimal giamToiDa) {
        this.giamToiDa = giamToiDa;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getHinhThucApDung() {
        return hinhThucApDung;
    }

    public void setHinhThucApDung(String hinhThucApDung) {
        this.hinhThucApDung = hinhThucApDung;
    }

    public String getApDungGiamGia() {
        return apDungGiamGia;
    }

    public void setApDungGiamGia(String apDungGiamGia) {
        this.apDungGiamGia = apDungGiamGia;
    }

    public String getLoaiGiamGia() {
        return loaiGiamGia;
    }

    public void setLoaiGiamGia(String loaiGiamGia) {
        this.loaiGiamGia = loaiGiamGia;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
}
