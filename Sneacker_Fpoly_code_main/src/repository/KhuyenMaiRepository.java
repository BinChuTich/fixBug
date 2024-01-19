/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.KhuyenMai;
import utilitys.DBConnection;

/**
 *
 * @author Thuan
 */
public class KhuyenMaiRepository {

    public ArrayList<KhuyenMai> getAll() {
        try {
            ArrayList<KhuyenMai> list = new ArrayList<>();
            java.sql.Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM KhuyenMai";
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
//                String idKh = rs.getString(1);
                String maKhuyenMai = rs.getString(2);
                String tenKhuyenMai = rs.getString(3);
                String hinhThucApDung = rs.getString(8);
                String loaiGiamGia = rs.getString(10);
                BigDecimal giaTri = rs.getBigDecimal(4);
                BigDecimal giamToiDa = rs.getBigDecimal(5);
                Date ngayBatDau = rs.getDate(6);
                Date ngayKetThuc = rs.getDate(7);
                int trangThai = rs.getInt(13);
                list.add(new KhuyenMai(maKhuyenMai, tenKhuyenMai, hinhThucApDung, loaiGiamGia, giaTri, giamToiDa, ngayBatDau, ngayKetThuc, trangThai));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public KhuyenMai them(KhuyenMai k) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO [dbo].[KhuyenMai] ([Id],[maKhuyenMai], [tenKhuyenMai],[hinhThucApDung],[giaTri], [giamToiDa], [ngayBatDau],[ngayKetThuc],[trangThai]) \n"
                    + "VALUES(NewID(),?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, k.getMaKhuyenMai());
            ps.setString(2, k.getTenKhuyenMai());
            ps.setString(3, k.getHinhThucApDung());
            ps.setBigDecimal(4, k.getGiaTri());
            ps.setBigDecimal(5, k.getGiamToiDa());
            ps.setDate(6, new Date(k.getNgayBatDau().getTime()));
            ps.setDate(7, new Date(k.getNgayKetThuc().getTime()));
            ps.setInt(8, k.getTrangThai());
            ps.executeUpdate();
            return k;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public KhuyenMai updateKhuyenMai(KhuyenMai km) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE KhuyenMai SET tenKhuyenMai = ?, hinhThucApDung = ?, giaTri = ?, giamToiDa = ?, ngayBatDau = ?, ngayKetThuc = ?, trangThai = ? WHERE maKhuyenMai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, km.getTenKhuyenMai());
            ps.setString(2, km.getHinhThucApDung());
            ps.setBigDecimal(3, km.getGiaTri());
            ps.setBigDecimal(4, km.getGiamToiDa());
            ps.setDate(5, new Date(km.getNgayBatDau().getTime()));
            ps.setDate(6, new Date(km.getNgayKetThuc().getTime()));
            ps.setInt(7, km.getTrangThai());
            ps.setString(8, km.getMaKhuyenMai());

            ps.executeUpdate();
            return km;

            // Kiểm tra nếu có ít nhất một dòng bị ảnh hưởng, tức là câu lệnh UPDATE đã thành công
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
