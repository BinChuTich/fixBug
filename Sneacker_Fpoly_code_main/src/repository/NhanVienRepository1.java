/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.ChucVu;
import model.NhanVien;
import utilitys.DBConnection;

/**
 *
 * @author Chien Duong
 */
public class NhanVienRepository1 {

    ChucVuRepository cvr = new ChucVuRepository();

    public ArrayList<NhanVien> getAllNV() {
        ChucVuRepository cvr = new ChucVuRepository();

        try {
            ArrayList<NhanVien> list = new ArrayList<>();
            java.sql.Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM NhanVien";
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                String maNV = rs.getString(2);
                String hoVaTen = rs.getString(3);
                String gioiTinh = rs.getString(5);
                Date NgaySinh = rs.getDate(4);
                String sdt = rs.getString(7);
                String email = rs.getString(8);
                String matKhau = rs.getString(9);
                ChucVu cv = this.cvr.getCVByID(rs.getString(10));
                String hinh = rs.getString(11);
                Date ngayTao = rs.getDate(12);
                Date ngaySua = rs.getDate(13);
                int trangThai = rs.getInt(14);
                list.add(new NhanVien(id, maNV, hoVaTen, NgaySinh, gioiTinh, hinh, sdt, email, maNV, cv, hinh, ngayTao, ngaySua, trangThai));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<NhanVien> getNVByID(String id) {
        ArrayList<NhanVien> nv = new ArrayList<>();

        String sql = "SELECT ID, maNhanVien,HOTEN FROM NhanVien WHERE ID=?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String idNV = rs.getString(1);
                String maNV = rs.getString(2);
                String hoVaTen = rs.getString(3);
                nv.add(new NhanVien(idNV, maNV, hoVaTen));
            }
            con.close();
            ps.close();
            rs.close();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NhanVien insertNV(NhanVien nv) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO NHANVIEN VALUES (NEWID(),?,?,?,?,?,?,?,?,?,?,GetDate(),null,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoVaTen());
            ps.setDate(3, new Date(nv.getNgaySinh().getTime()));
            ps.setString(4, nv.getGioiTinh());
            ps.setString(5, nv.getDiaChi());
            ps.setString(6, nv.getSdt());
            ps.setString(7, nv.getEmail());
            ps.setString(8, nv.getMatKhau());
            ps.setString(9, nv.getIdCV().getIdchucVu());
            ps.setString(10, nv.getHinh());
            ps.setInt(11, nv.getTrangThai());
            ps.executeUpdate();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NhanVien updateNV(NhanVien nv) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE NHANVIEN SET HovaTen=?,NGAYSINH=?,GIOITINH=?,DIACHI=?,SDT=?,EMAIL=?,MATKHAU=?,IDCV=?,HINH=?, TRANGTHAI=?,NGAYSUA=GETDATE() WHERE maNhanVien=?";
            PreparedStatement ps = con.prepareStatement(sql);
//        ps.setString(1, kh.getMaKH());
            ps.setString(1, nv.getHoVaTen());
            ps.setDate(2, new Date(nv.getNgaySinh().getTime()));
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getSdt());
            ps.setString(6, nv.getEmail());
            ps.setString(7, nv.getMatKhau());
            ps.setString(8, nv.getIdCV().getIdchucVu());
            ps.setString(9, nv.getHinh());
            ps.setInt(10, nv.getTrangThai());
            ps.setString(11, nv.getMaNV());

            // Thực thi câu lệnh UPDATE và kiểm tra số dòng được ảnh hưởng
            ps.executeUpdate();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<NhanVien> getNVLAM() {
        ChucVuRepository cvr = new ChucVuRepository();

        try {
            ArrayList<NhanVien> list = new ArrayList<>();
            java.sql.Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM NHANVIEN WHERE TRANGTHAI = 1";
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                String maNV = rs.getString(2);
                String hoVaTen = rs.getString(3);
                String gioiTinh = rs.getString(5);
                Date NgaySinh = rs.getDate(4);
                String diaChi = rs.getString(6);
                String sdt = rs.getString(7);
                String email = rs.getString(8);
                String matKhau = rs.getString(9);
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                String hinh = rs.getString(11);
                Date ngayTao = rs.getDate(12);
                Date ngaySua = rs.getDate(13);
                int trangThai = rs.getInt(14);
                list.add(new NhanVien(id, maNV, hoVaTen, NgaySinh, gioiTinh, hinh, sdt, email, maNV, cv, hinh, ngayTao, ngaySua, trangThai));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<NhanVien> getNVlamByCV(String tenCV) {
        ArrayList<NhanVien> nv = new ArrayList<>();
        String sql = "SELECT NHANVIEN.* FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.IDCV=CHUCVU.ID WHERE NHANVIEN.TRANGTHAI=1 AND TenChucVu LIKE ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + tenCV + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String maNV = rs.getString(2);
                String hoVaTen = rs.getString(3);
                String gioiTinh = rs.getString(5);
                String diaChi = rs.getString(6);
                Date NgaySinh = rs.getDate(4);
                String sdt = rs.getString(7);
                String email = rs.getString(8);
                String matKhau = rs.getString(9);
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                String hinh = rs.getString(11);
                Date ngayTao = rs.getDate(12);
                Date ngaySua = rs.getDate(13);
                int trangThai = rs.getInt(14);
                nv.add(new NhanVien(id, maNV, hoVaTen, NgaySinh, gioiTinh, hinh, sdt, email, maNV, cv, hinh, ngayTao, ngaySua, trangThai));
            }
            con.close();
            ps.close();
            rs.close();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<NhanVien> getNVlamBySdt(String sdt) {
        ArrayList<NhanVien> nv = new ArrayList<>();
        String sql = "SELECT NHANVIEN.* FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.IDCV=CHUCVU.ID WHERE NHANVIEN.TRANGTHAI=1 AND SDT LIKE ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + sdt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String maNV = rs.getString(2);
                String hoVaTen = rs.getString(3);
                String gioiTinh = rs.getString(5);
                String diaChi = rs.getString(6);
                Date NgaySinh = rs.getDate(4);
                sdt = rs.getString(7);
                String email = rs.getString(8);
                String matKhau = rs.getString(9);
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                String hinh = rs.getString(11);
                Date ngayTao = rs.getDate(12);
                Date ngaySua = rs.getDate(13);
                int trangThai = rs.getInt(14);
                nv.add(new NhanVien(id, maNV, hoVaTen, NgaySinh, gioiTinh, hinh, sdt, email, maNV, cv, hinh, ngayTao, ngaySua, trangThai));
            }
            con.close();
            ps.close();
            rs.close();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<NhanVien> getNVNGHI() {
        try {
            ArrayList<NhanVien> list = new ArrayList<>();
            java.sql.Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM NHANVIEN WHERE TRANGTHAI = 1";
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                String maNV = rs.getString(2);
                String hoVaTen = rs.getString(3);
                String gioiTinh = rs.getString(5);
                String diaChi = rs.getString(6);
                Date NgaySinh = rs.getDate(4);
                String sdt = rs.getString(7);
                String email = rs.getString(8);
                String matKhau = rs.getString(9);
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                String hinh = rs.getString(11);
                Date ngayTao = rs.getDate(12);
                Date ngaySua = rs.getDate(13);
                int trangThai = rs.getInt(14);
                list.add(new NhanVien(id, maNV, hoVaTen, NgaySinh, gioiTinh, hinh, sdt, email, maNV, cv, hinh, ngayTao, ngaySua, trangThai));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public ArrayList<NhanVien> getNVNghiByCV(String tenCV) {
        ArrayList<NhanVien> nv = new ArrayList<>();
        String sql = "SELECT NHANVIEN.* FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.IDCV=CHUCVU.ID WHERE NHANVIEN.TRANGTHAI=0 AND TenChucVu LIKE ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + tenCV + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String maNV = rs.getString(2);
                String hoVaTen = rs.getString(3);
                String gioiTinh = rs.getString(5);
                String diaChi = rs.getString(6);
                Date NgaySinh = rs.getDate(4);
                String sdt = rs.getString(7);
                String email = rs.getString(8);
                String matKhau = rs.getString(9);
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                String hinh = rs.getString(11);
                Date ngayTao = rs.getDate(12);
                Date ngaySua = rs.getDate(13);
                int trangThai = rs.getInt(14);
                nv.add(new NhanVien(id, maNV, hoVaTen, NgaySinh, gioiTinh, hinh, sdt, email, maNV, cv, hinh, ngayTao, ngaySua, trangThai));
            }
            con.close();
            ps.close();
            rs.close();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public ArrayList<NhanVien> getNVNghiBySdt(String sdt) {
        ArrayList<NhanVien> nv = new ArrayList<>();
        String sql = "SELECT NHANVIEN.* FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.IDCV=CHUCVU.ID WHERE NHANVIEN.TRANGTHAI=1 AND SDT LIKE ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + sdt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String maNV = rs.getString(2);
                String hoVaTen = rs.getString(3);
                String gioiTinh = rs.getString(5);
                String diaChi = rs.getString(6);
                Date NgaySinh = rs.getDate(4);
                sdt = rs.getString(7);
                String email = rs.getString(8);
                String matKhau = rs.getString(9);
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                String hinh = rs.getString(11);
                Date ngayTao = rs.getDate(12);
                Date ngaySua = rs.getDate(13);
                int trangThai = rs.getInt(14);
                nv.add(new NhanVien(id, maNV, hoVaTen, NgaySinh, gioiTinh, hinh, sdt, email, maNV, cv, hinh, ngayTao, ngaySua, trangThai));
            }
            con.close();
            ps.close();
            rs.close();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
