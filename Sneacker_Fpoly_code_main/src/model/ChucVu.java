/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Chien Duong
 */
public class ChucVu {

    private String idchucVu;
    private String maChucVu;
    private String tenChucVu;
    private Date ngayTao;
    private Date ngaySua;
    private int trangThai;

    public ChucVu() {
    }

    public ChucVu(String idchucVu, String maChucVu, String tenChucVu, Date ngayTao, Date ngaySua, int trangThai) {
        this.idchucVu = idchucVu;
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }


    public ChucVu(String maChucVu, String tenChucVu, int trangThai) {
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
        this.trangThai = trangThai;
    }
     public ChucVu(String idCV, String maCV, String tenCV, int trangThai) {
        this.idchucVu = idCV;
        this.maChucVu = maCV;
        this.tenChucVu = tenCV;
        this.trangThai = trangThai;
    }

    public String getIdchucVu() {
        return idchucVu;
    }

    public void setIdchucVu(String idchucVu) {
        this.idchucVu = idchucVu;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
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

     @Override
    public String toString() {
        return tenChucVu ;
    }

}
