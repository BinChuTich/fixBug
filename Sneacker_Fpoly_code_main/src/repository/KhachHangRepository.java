/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Date;
import java.sql.*;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import model.KhachHang;
import utilitys.DBConnection;

/**
 *
 * @author Thuan
 */
public class KhachHangRepository {

    public ArrayList<KhachHang> getAll() {
        try {
            ArrayList<KhachHang> list = new ArrayList<>();
            java.sql.Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM KhachHang";
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String idKh = rs.getString(1);
                String maKH = rs.getString(2);
                String tenKH = rs.getString(4);
                String sdt = rs.getString(8);
                String email = rs.getString(7);
                String diaChi = rs.getString(5);
                String gioiTinh = rs.getString(6);
                Date ngaySinh = rs.getDate(9);
                int trangThai = rs.getInt(15);
                list.add(new KhachHang(idKh, maKH, tenKH, sdt, email, diaChi, gioiTinh, ngaySinh, trangThai));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public KhachHang them(KhachHang k) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO [dbo].[KhachHang] ([ID],[maKhachHang], [tenKhachHang],[sdt],[email], [diaChi], [gioiTinh],[ngaySinh],[trangThai]) VALUES(NewID(),?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, k.getMaKH());
            ps.setString(2, k.getTenKH());
            ps.setString(3, k.getSdt());
            ps.setString(4, k.getEmail());
            ps.setString(5, k.getDiaChi());
            ps.setString(6, k.getGioiTinh());
            ps.setDate(7, new Date(k.getNgaySinh().getTime()));
            ps.setInt(8, k.getTrangThai());
            ps.executeUpdate();
            return k;
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        return null;
    }
    
    public ArrayList<KhachHang> getKhachHangByTenKH(String tenKH) {
        ArrayList<KhachHang> kh = new ArrayList<>();
        String sql = "SELECT ID, maKhachHang, tenKhachHang, sdt , email, diaChi, gioiTinh, ngaySinh, trangThai FROM KhachHang WHERE tenKhachHang like ?";
        try {
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, "%" + tenKH + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String idKh = rs.getString(1);
                String maKH = rs.getString(2);
                tenKH = rs.getString(3);
                String sdt = rs.getString(4);
                String email = rs.getString(5);
                String diaChi = rs.getString(6);
                String gioiTinh = rs.getString(7);
                Date ngaySinh = rs.getDate(8);
                int trangThai = rs.getInt(9);
                kh.add(new KhachHang(idKh,maKH, tenKH, sdt, email, diaChi, gioiTinh, ngaySinh, trangThai));
            }
            c.close();
            ps.close();
            rs.close();
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public KhachHang updateKhachHang(KhachHang kh) {
    try {
        Connection con = DBConnection.getConnection();
        String sql = "UPDATE KhuyenMai SET tenKhachHang = ?, sdt = ?, email = ?, diaChi = ?, gioiTinh = ?, ngaySinh = ?, trangThai = ? WHERE maKhachHang = ?";
        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setString(1, kh.getMaKH());
        ps.setString(1, kh.getTenKH());
        ps.setString(2, kh.getSdt());
        ps.setString(3, kh.getEmail());
        ps.setString(4, kh.getDiaChi());
        ps.setString(5, kh.getGioiTinh());
        ps.setDate(6, new Date(kh.getNgaySinh().getTime()));
        ps.setInt(7, kh.getTrangThai());
        ps.setString(8, kh.getMaKH());

        // Thực thi câu lệnh UPDATE và kiểm tra số dòng được ảnh hưởng
        ps.executeUpdate();
        return kh;

        // Kiểm tra nếu có ít nhất một dòng bị ảnh hưởng, tức là câu lệnh UPDATE đã thành công
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    // Trả về null nếu có lỗi xảy ra hoặc không có dòng nào được ảnh hưởng
    return null;
}

//    public KhachHang updateKhachHang(KhachHang kh) {
//
//        try {
//            Connection con = DBConnection.getConnection();
//            String sql = "update KhachHang set  tenKhachHang = ?, sdt =?,email =?, diaChi = ?, gioiTinh = ?, ngaySinh  = ?,trangThai = ? where ID = ?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, kh.getTenKH());
//            ps.setString(2, kh.getSdt());
//            ps.setString(3, kh.getEmail());
//            ps.setString(4, kh.getDiaChi());
//            ps.setString(5, kh.getGioiTinh());
//            ps.setDate(6, new Date(kh.getNgaySinh().getTime()));
//            ps.setInt(7, kh.getTrangThai());
//            ps.setString(8, kh.getIdKh());
//            return kh;
//        } catch (Exception e) {
//            e.printStackTrace();
//            
//        }
//        return null;
//    }
//    public KhachHang insertKhachHang(KhachHang kh) {
//        String sql = "INSERT INTO KhachHang(ID,MaKH,LoaiKH,TenKH,DiaChi,GioiTinh,Email,SDT,NgaySinh,NgayThamGia,NgayTao,TrangThai) \n"
//                + "VALUES(NewID(), ?, ?, ?, ?, ?, ?, ?, ?, getDate(), getDate(), ?)";
//        JDBCHelper.executeUpdate(sql,
//                kh.getMaKH(),
//                kh.getLoaiKH(),
//                kh.getTenKH(),
//                kh.getDiaChi(),
//                kh.getGioiTinh(),
//                kh.getEmail(),
//                kh.getSdt(),
//                kh.getNgaySinh(),
//                kh.getTrangThai()
//        );
//        return kh;
//    }
}
