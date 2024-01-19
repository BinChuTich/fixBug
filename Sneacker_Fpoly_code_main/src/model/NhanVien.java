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
public class NhanVien {

    private String id;
    private String maNV;
    private String hoVaTen;
    private Date NgaySinh;
    private String gioiTinh;
    private String diaChi;
    private String sdt;
    private String email;
    private String matKhau;
    private ChucVu idCV;
    private String hinh;
    private Date ngayTao;
    private Date ngaySua;
    private int trangThai;

    public NhanVien() {
    }
public NhanVien(String idNV, String maNV,String hoTen) {
        this.id = idNV;
        this.maNV = maNV;
        this.hoVaTen=hoTen;
    }
    public NhanVien(String id, String maNV, String hoVaTen, Date NgaySinh, String gioiTinh, String diaChi, String sdt, String email, String matKhau, ChucVu idCV, String hinh, Date ngayTao, Date ngaySua, int trangThai) {
        this.id = id;
        this.maNV = maNV;
        this.hoVaTen = hoVaTen;
        this.NgaySinh = NgaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.matKhau = matKhau;
        this.idCV = idCV;
        this.hinh = hinh;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }
     public NhanVien(String idNV, String maNV, String hoTen, Date ngaySinh, String gioiTinh, String diaChi, String sdt, String email, String matKhau, ChucVu idCV, String hinh, int trangThai) {
        this.id = idNV;
        this.maNV = maNV;
        this.hoVaTen = hoTen;
        this.NgaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.matKhau = matKhau;
        this.idCV = idCV;
        this.hinh = hinh;
        this.trangThai = trangThai;
    }

 

  
     

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public ChucVu getIdCV() {
        return idCV;
    }

    public void setIdCV(ChucVu idCV) {
        this.idCV = idCV;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
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
        return hoVaTen;
    }
    public Object[] toDataRow() {
        return new Object[]{id, maNV,hoVaTen,NgaySinh,gioiTinh,diaChi,sdt,email,matKhau,idCV,hinh,trangThai==1?"Đi làm":"Nghỉ làm"};
    }

}
