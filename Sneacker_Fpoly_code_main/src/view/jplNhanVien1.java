/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import model.ChucVu;
import model.NhanVien;
import repository.ChucVuRepository;
import repository.ChucVuRepository1;
import repository.NhanVienRepository1;
import service.ChucVuService1;
import service.NhanVienService1;
import utilitys.DBConnection;
import utilitys.JDBCHelper;

/**
 *
 * @author Chien Duong
 */
public class jplNhanVien1 extends javax.swing.JDialog {

    ChucVuService1 cvs;
    NhanVienService1 nvs;
    DefaultTableModel dtmCV;
    String fileanh = null;

    DefaultComboBoxModel<ChucVu> dcmCV;
    DefaultComboBoxModel<ChucVu> dcmCVM;
    DefaultTableModel dtmNVLam;
    DefaultTableModel dtmNVNghi;

    ChucVuRepository1 cvr = new ChucVuRepository1();
    NhanVienRepository1 nvr = new NhanVienRepository1();

    long count, soTrang, Trang = 1;
    long count1, soTrang1, Trang1 = 1;
    long count2, soTrang2, Trang2 = 1;

    /**
     * Creates new form jplNhanVien1
     */
    public jplNhanVien1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cvs = new ChucVuService1();
        nvs = new NhanVienService1();
        dtmCV = new DefaultTableModel();
        dtmCV = (DefaultTableModel) tblChucVu.getModel();
        dcmCV = new DefaultComboBoxModel<>();
        dcmCVM = new DefaultComboBoxModel<>();

        dtmNVLam = new DefaultTableModel();
        dtmNVNghi = new DefaultTableModel();
        dtmNVLam = (DefaultTableModel) tblNVLam.getModel();
        dtmNVNghi = (DefaultTableModel) tblNVNghi.getModel();

        cbbCV.setModel((DefaultComboBoxModel) dcmCV);

        cbbLocCV.setModel((DefaultComboBoxModel) dcmCV);
        cbbCV.setModel((DefaultComboBoxModel) dcmCV);

        cbbLocCV.setModel((DefaultComboBoxModel) dcmCVM);

        cbbLocCV.setModel((DefaultComboBoxModel) dcmCV);
        cbbCV.setModel((DefaultComboBoxModel) dcmCV);
        cbbLocCV.setModel((DefaultComboBoxModel) dcmCVM);

        loadComboCV();
        loadComboCVTim();

        //
        countSPNVLam();

        if (count % 5 == 0) {
            soTrang = count / 5;
        } else {
            soTrang = count / 5 + 1;
        }
        loadTableLam(1);
        lblTrang.setText("1");

        countSPNVNghi();
        loadTableNghi(1);

        if (count1 % 5 == 0) {
            soTrang1 = count1 / 5;
        } else {
            soTrang1 = count1 / 5 + 1;
        }
        lblTrangNghi.setText("1");

        countSPNVChuVu();
        loadTableCV(1);

        if (count2 % 5 == 0) {
            soTrang2 = count2 / 5;
        } else {
            soTrang2 = count2 / 5 + 1;
        }
        lblChucVu.setText("1");

    }

    public void loadDataTable() {
        ArrayList<NhanVien> list = getDLNVLam();
        dtmNVLam = (DefaultTableModel) tblNVLam.getModel();
        dtmNVLam.setRowCount(0);
        for (NhanVien nv : list) {
            dtmNVLam.addRow(new Object[]{
                nv.getId(),
                nv.getMaNV(),
                nv.getHoVaTen(),
                nv.getNgaySinh(),
                nv.getGioiTinh(),
                nv.getDiaChi(),
                nv.getSdt(),
                nv.getEmail(),
                nv.getMatKhau(),
                nv.getIdCV(),
                nv.getHinh(),
                nv.getTrangThai() == 1 ? "Hoạt động" : "Ngưng hoạt động",});
        }

    }

    public void countSPNVLam() {
        try {
            String query = "SELECT COUNT(*) FROM NhanVien WHERE TRANGTHAI = 1";
            java.sql.Connection con = DBConnection.getConnection();
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTableLam(long Trang) {
        dtmNVLam.setRowCount(0);
        ArrayList<NhanVien> list = getDLNVLam();
        Collections.sort(list, Comparator.comparing(NhanVien -> NhanVien.getMaNV()));
        for (NhanVien nhanVienM : list) {
            dtmNVLam.addRow(nhanVienM.toDataRow());
        }
    }

    public ArrayList<NhanVien> getDLNVLam() {
        ChucVuRepository cvr = new ChucVuRepository();
        try {
            ArrayList<NhanVien> list = new ArrayList<>();
            java.sql.Connection con = DBConnection.getConnection();
            String query = "SELECT TOP 5 * FROM NhanVien WHERE maNhanVien NOT IN (SELECT TOP " + (Trang * 5 - 5) + " maNhanVien FROM NhanVien WHERE TRANGTHAI = 1) AND TRANGTHAI = 1";
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
                list.add(new NhanVien(id, maNV, hoVaTen, NgaySinh, gioiTinh, diaChi, sdt, email, matKhau, cv, hinh, ngayTao, ngaySua, trangThai));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    private void loadTimLamByCV(String tenCV) {
        dtmNVLam.setRowCount(0);
        ArrayList<NhanVien> list = nvs.getDLNVLAMBYCV(tenCV);
        for (NhanVien nhanVienM : list) {
            dtmNVLam.addRow(nhanVienM.toDataRow());
        }
    }

    private void loadTimLamBySdt(String sdt) {
        dtmNVLam.setRowCount(0);
        ArrayList<NhanVien> list = nvs.getNVLamBySdt(sdt);
        for (NhanVien nhanVienM : list) {
            dtmNVLam.addRow(nhanVienM.toDataRow());
        }
    }

    public void countSPNVNghi() {
        try {
            String query = "SELECT COUNT(*) FROM NhanVien WHERE TRANGTHAI = 0";
            java.sql.Connection con = DBConnection.getConnection();
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTableNghi(long Trang1) {
        dtmNVNghi.setRowCount(0);
        ArrayList<NhanVien> list = getDLNVNghi();
        Collections.sort(list, Comparator.comparing(NhanVien -> NhanVien.getMaNV()));
        for (NhanVien nhanVienM : list) {
            dtmNVNghi.addRow(nhanVienM.toDataRow());
        }
    }

    public ArrayList<NhanVien> getDLNVNghi() {
        ChucVuRepository cvr = new ChucVuRepository();
        try {
            ArrayList<NhanVien> list = new ArrayList<>();
            java.sql.Connection con = DBConnection.getConnection();
            String query = "SELECT TOP 5 * FROM NhanVien WHERE maNhanVien NOT IN (SELECT TOP " + (Trang * 5 - 5) + " maNhanVien FROM NhanVien WHERE TRANGTHAI = 1) AND TRANGTHAI = 0";
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
                list.add(new NhanVien(id, maNV, hoVaTen, NgaySinh, gioiTinh, diaChi, sdt, email, matKhau, cv, hinh, ngayTao, ngaySua, trangThai));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void loadTimNghiByCV(String tenCV) {
        dtmNVNghi.setRowCount(0);
        ArrayList<NhanVien> list = nvs.getNVNghiBySdt(tenCV);
        for (NhanVien nhanVienM : list) {
            dtmNVNghi.addRow(nhanVienM.toDataRow());
        }
    }

    private void loadTimNghiBySdt(String sdt) {
        dtmNVNghi.setRowCount(0);
        ArrayList<NhanVien> list = nvs.getNVNghiBySdt(sdt);
        for (NhanVien nhanVienM : list) {
            dtmNVNghi.addRow(nhanVienM.toDataRow());
        }
    }

    public void countSPNVChuVu() {
        try {
            String sql = "SELECT COUNT(*) FROM ChucVu";
            ResultSet rs = JDBCHelper.excuteQuery(sql);
            while (rs.next()) {
                count2 = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<ChucVu> getChucVuDL() {
        try {
            ArrayList<ChucVu> list = new ArrayList<>();
            java.sql.Connection con = DBConnection.getConnection();
            String query = "SELECT TOP 5 * FROM ChucVu WHERE maChucVu NOT IN (SELECT TOP " + (Trang2 * 5 - 5) + " maChucVu FROM ChucVu) ";
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String idchucVu = rs.getString(1);
                String maChucVu = rs.getString(2);
                String tenChucVu = rs.getString(3);
                Date ngayTao = rs.getDate(4);
                Date ngaySua = rs.getDate(5);
                int trangThai = rs.getInt(6);
                list.add(new ChucVu(idchucVu, maChucVu, tenChucVu, trangThai));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    private void loadTableCV(long Trang2) {
        ArrayList<ChucVu> list = getChucVuDL();
        dtmCV.setRowCount(0);
        for (ChucVu x : list) {
            Object[] rowData = {
                x.getMaChucVu(), x.getTenChucVu(), x.getTrangThai() == 0 ? "còn" : "hủy"
            };
            dtmCV.addRow(rowData);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        txtHoVaTen = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        rdoNghiLam = new javax.swing.JRadioButton();
        rdoDiLam = new javax.swing.JRadioButton();
        btnUpAnh = new javax.swing.JButton();
        btnThemNV = new javax.swing.JButton();
        btnCapNhatNV = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbbLocCV = new javax.swing.JComboBox<>();
        btnTimSdt = new javax.swing.JButton();
        txtTimKiemSDT = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNVLam = new javax.swing.JTable();
        btnPre = new javax.swing.JButton();
        btnFist = new javax.swing.JButton();
        lblTrang = new javax.swing.JLabel();
        btnLast = new javax.swing.JButton();
        btnEnd = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNVNghi = new javax.swing.JTable();
        btnFist1 = new javax.swing.JButton();
        btnPre1 = new javax.swing.JButton();
        lblTrangNghi = new javax.swing.JLabel();
        btnLast1 = new javax.swing.JButton();
        btnEnd1 = new javax.swing.JButton();
        cbbCV = new javax.swing.JComboBox<>();
        txtMaNV = new javax.swing.JTextField();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNgayTao = new com.toedter.calendar.JDateChooser();
        txtNgaySua = new com.toedter.calendar.JDateChooser();
        lblHinh = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtMaCV = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtTenCV = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        rdoCon = new javax.swing.JRadioButton();
        rdoNghi = new javax.swing.JRadioButton();
        btnThemCV = new javax.swing.JButton();
        btnSuaCV = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChucVu = new javax.swing.JTable();
        btnClearCV = new javax.swing.JButton();
        btnPre2 = new javax.swing.JButton();
        btnFist2 = new javax.swing.JButton();
        btnLast2 = new javax.swing.JButton();
        btnEnd2 = new javax.swing.JButton();
        lblChucVu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel4.setText("SDT");

        jLabel9.setText("Chức vụ");

        jLabel5.setText("Điạ Chỉ");

        jLabel6.setText("Ngày Sinh");

        txtPass.setText("jPasswordField1");
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã NV");

        jLabel7.setText("Mật Khẩu");

        jLabel2.setText("Email");

        jLabel3.setText("Họ Và Tên");

        jLabel10.setText("Giới Tính");

        buttonGroup2.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup2.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel11.setText("Trạng Thái");

        buttonGroup3.add(rdoNghiLam);
        rdoNghiLam.setText("Nghỉ làm");

        buttonGroup3.add(rdoDiLam);
        rdoDiLam.setText("Đi Làm");

        btnUpAnh.setBackground(new java.awt.Color(153, 204, 255));
        btnUpAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Upload.png"))); // NOI18N
        btnUpAnh.setText("UpLoad Ảnh");
        btnUpAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpAnhActionPerformed(evt);
            }
        });

        btnThemNV.setBackground(new java.awt.Color(153, 204, 255));
        btnThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Create.png"))); // NOI18N
        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnCapNhatNV.setBackground(new java.awt.Color(153, 204, 255));
        btnCapNhatNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Refresh.png"))); // NOI18N
        btnCapNhatNV.setText("Cập Nhật");
        btnCapNhatNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatNVActionPerformed(evt);
            }
        });

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setBackground(new java.awt.Color(204, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/loupe.png"))); // NOI18N
        jLabel12.setText("Lọc Theo Chức Vụ");

        cbbLocCV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLocCVItemStateChanged(evt);
            }
        });

        btnTimSdt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Search.png"))); // NOI18N
        btnTimSdt.setText("Tìm Kiếm SDT");
        btnTimSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSdtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(cbbLocCV, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(268, 268, 268)
                .addComponent(txtTimKiemSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimSdt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbbLocCV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblNVLam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã NV", "Họ Và Tên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "SDT", "Email", "Mật Khẩu", "Chức Vụ", "Hình ", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNVLam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVLamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNVLam);

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2T.png"))); // NOI18N
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnFist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/nextT.png"))); // NOI18N
        btnFist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFistActionPerformed(evt);
            }
        });

        lblTrang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTrang.setText("12/12");

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/nextP.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnEnd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2P.png"))); // NOI18N
        btnEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEndActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnFist, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblTrang))
                    .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFist))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Đang làm", jPanel5);

        tblNVNghi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã NV", "Họ Và Tên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "SDT", "Email", "Mật Khẩu", "Chức Vụ", "Hình ", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNVNghi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVNghiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblNVNghi);

        btnFist1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/nextT.png"))); // NOI18N
        btnFist1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFist1ActionPerformed(evt);
            }
        });

        btnPre1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2T.png"))); // NOI18N
        btnPre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre1ActionPerformed(evt);
            }
        });

        lblTrangNghi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTrangNghi.setText("12/12");

        btnLast1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/nextP.png"))); // NOI18N
        btnLast1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLast1ActionPerformed(evt);
            }
        });

        btnEnd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2P.png"))); // NOI18N
        btnEnd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnd1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1053, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(btnPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnFist1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblTrangNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnLast1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEnd1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFist1)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(lblTrangNghi))
                        .addComponent(btnPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEnd1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLast1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Đã Nghỉ", jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbbCV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtNgaySinh.setDateFormatString("yyyy-MM-dd");

        jLabel8.setText("Ngày Tạo");

        jLabel13.setText("Ngày Sửa");

        txtNgayTao.setDateFormatString("yyyy-MM-dd");

        txtNgaySua.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel3)
                                                        .addGap(7, 7, 7))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel6)
                                                        .addGap(8, 8, 8))))))
                                    .addComponent(jLabel10))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                .addComponent(txtHoVaTen))
                                            .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel8))
                        .addGap(116, 116, 116)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEmail)
                                    .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                    .addComponent(cbbCV, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSDT)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoDiLam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoNghiLam))
                                    .addComponent(txtNgaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCapNhatNV, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnThemNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(btnUpAnh)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(txtHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(cbbCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel13))
                                    .addComponent(txtNgaySua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(rdoNam)
                                    .addComponent(rdoNu)
                                    .addComponent(jLabel11)
                                    .addComponent(rdoDiLam, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoNghiLam))
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnUpAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnCapNhatNV, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        jTabbedPane1.addTab("Quản lý nhân viên", jPanel1);

        jLabel14.setText("Mã chức vụ");

        jLabel15.setText("Tên chức vụ");

        jLabel16.setText("Trạng thái");

        buttonGroup1.add(rdoCon);
        rdoCon.setText("Còn");

        buttonGroup1.add(rdoNghi);
        rdoNghi.setText("Hủy");

        btnThemCV.setText("Thêm");
        btnThemCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCVActionPerformed(evt);
            }
        });

        btnSuaCV.setText("Sửa");
        btnSuaCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCVActionPerformed(evt);
            }
        });

        tblChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Chức vụ", "Tên chức vụ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChucVuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChucVu);

        btnClearCV.setText("Clear");

        btnPre2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2T.png"))); // NOI18N
        btnPre2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre2ActionPerformed(evt);
            }
        });

        btnFist2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/nextT.png"))); // NOI18N
        btnFist2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFist2ActionPerformed(evt);
            }
        });

        btnLast2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/nextP.png"))); // NOI18N
        btnLast2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLast2ActionPerformed(evt);
            }
        });

        btnEnd2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2P.png"))); // NOI18N
        btnEnd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnd2ActionPerformed(evt);
            }
        });

        lblChucVu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblChucVu.setText("12/12");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaCV, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(btnThemCV)))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenCV, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btnSuaCV)
                                .addGap(209, 209, 209)
                                .addComponent(btnClearCV)))
                        .addGap(41, 41, 41)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(rdoCon)
                        .addGap(31, 31, 31)
                        .addComponent(rdoNghi))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(btnPre2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnFist2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnLast2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEnd2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtMaCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtTenCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(rdoCon)
                    .addComponent(rdoNghi))
                .addGap(59, 59, 59)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCV)
                    .addComponent(btnSuaCV)
                    .addComponent(btnClearCV))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFist2)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(lblChucVu))
                        .addComponent(btnPre2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEnd2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLast2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(153, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chức vụ", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
        txtPass.setText("");
    }//GEN-LAST:event_txtPassActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
        Trang = 1;
        try {
            loadTableLam(Trang);
        } catch (Exception ex) {
            Logger.getLogger(jplKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblTrang.setText("1");

    }//GEN-LAST:event_btnPreActionPerformed

    private void btnFistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFistActionPerformed
        if (Trang > 1) {
            Trang--;
            try {
                loadTableLam(Trang);
            } catch (Exception ex) {
                Logger.getLogger(jplKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            lblTrang.setText("" + Trang);

        }
    }//GEN-LAST:event_btnFistActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        if (Trang < soTrang) {
            Trang++;
            try {
                loadTableLam(Trang);
            } catch (Exception ex) {
                Logger.getLogger(jplKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            lblTrang.setText("" + Trang);

        }
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEndActionPerformed
        // TODO add your handling code here:
        Trang = soTrang;
        try {
            loadTableLam(Trang);
        } catch (Exception ex) {
            Logger.getLogger(jplKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblTrang.setText("" + soTrang);

    }//GEN-LAST:event_btnEndActionPerformed

    private void tblNVLamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVLamMouseClicked
        // TODO add your handling code here:
        try {
            int row = tblNVLam.getSelectedRow();
            if (row < 0) {
                return;
            }
            txtMaNV.setText(tblNVLam.getValueAt(row, 1).toString());
            txtHoVaTen.setText(tblNVLam.getValueAt(row, 2).toString());
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tblNVLam.getValueAt(row, 3).toString());
            txtNgaySinh.setDate(date);
            String gt = tblNVLam.getValueAt(row, 4).toString();
            if (gt.equalsIgnoreCase("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            txtDiaChi.setText(tblNVLam.getValueAt(row, 5).toString());
            txtSDT.setText(tblNVLam.getValueAt(row, 6).toString());
            txtEmail.setText(tblNVLam.getValueAt(row, 7).toString());
            txtPass.setText(tblNVLam.getValueAt(row, 8).toString());
            String ChucVu = tblNVLam.getValueAt(row, 9).toString();
            cbbCV.getModel().setSelectedItem(getCV(row, ChucVu));
            String hinh = tblNVLam.getValueAt(row, 10).toString();

            ImageIcon icon = new ImageIcon(getClass().getResource("/AnhNV/" + hinh));
            Image img = icon.getImage();
            lblHinh.setIcon(new ImageIcon(img.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH)));
            String tt = tblNVLam.getValueAt(row, 11).toString();
            if (tt.equalsIgnoreCase("Đi làm")) {
                rdoDiLam.setSelected(true);
            } else {
                rdoNghiLam.setSelected(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_tblNVLamMouseClicked

    private void tblNVNghiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVNghiMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:

        try {
            int row = tblNVNghi.getSelectedRow();
            if (row < 0) {
                return;
            }
            txtMaNV.setText(tblNVNghi.getValueAt(row, 1).toString());
            txtHoVaTen.setText(tblNVNghi.getValueAt(row, 2).toString());
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tblNVNghi.getValueAt(row, 3).toString());
            txtNgaySinh.setDate(date);
            String gt = tblNVNghi.getValueAt(row, 4).toString();
            if (gt.equalsIgnoreCase("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            txtDiaChi.setText(tblNVNghi.getValueAt(row, 5).toString());
            txtSDT.setText(tblNVNghi.getValueAt(row, 6).toString());
            txtEmail.setText(tblNVNghi.getValueAt(row, 7).toString());
            txtPass.setText(tblNVNghi.getValueAt(row, 8).toString());
            String chucvu = tblNVNghi.getValueAt(row, 9).toString();
            cbbCV.getModel().setSelectedItem(getCV(row, chucvu));
            String hinh = tblNVNghi.getValueAt(row, 10).toString();
            ImageIcon icon = new ImageIcon(getClass().getResource("/AnhNV/" + hinh));
            Image img = icon.getImage();
            lblHinh.setIcon(new ImageIcon(img.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH)));
            String tt = tblNVNghi.getValueAt(row, 11).toString();
            if (tt.equalsIgnoreCase("Đi làm")) {
                rdoDiLam.setSelected(true);
            } else {
                rdoNghiLam.setSelected(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_tblNVNghiMouseClicked

    private void tblChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChucVuMouseClicked
        // TODO add your handling code here:

        int row = tblChucVu.getSelectedRow();
        if (row < 0) {
            return;
        }
        txtMaCV.setText(tblChucVu.getValueAt(row, 0).toString());
        txtTenCV.setText(tblChucVu.getValueAt(row, 1).toString());
        String tt = tblChucVu.getValueAt(row, 2).toString();
        if (tt.equalsIgnoreCase("còn")) {
            rdoCon.setSelected(true);
            JButton button = new JButton("Click me!");

        } else {
            rdoNghi.setSelected(true);
        }
    }//GEN-LAST:event_tblChucVuMouseClicked

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        NhanVien nvm = getNVForm();
        if (nvm == null) {
            return;
        }
        if (nvs.insertNV(nvm) != null) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại do trùng mã nhân viên");
        }
        loadTableLam(Trang);
        loadTableNghi(Trang1);
        clearNV();
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnThemCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCVActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        ChucVu cvm = getCVForm();
        if (cvm == null) {
            return;
        }
        if (cvs.InsertCV(cvm) != null) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadTableCV(Trang);
            clearCV();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại do trùng mã chức vụ");
        }
    }//GEN-LAST:event_btnThemCVActionPerformed

    private void btnSuaCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCVActionPerformed
        // TODO add your handling code here:
        int row = tblChucVu.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa");
            return;
        }
        ChucVu cvm = getCVForm();
        if (cvm == null) {
            return;
        }
        cvm.setMaChucVu(tblChucVu.getValueAt(row, 0).toString());
        if (cvs.UpdateCV(cvm) != null) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            loadTableCV(Trang);
            clearCV();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    }//GEN-LAST:event_btnSuaCVActionPerformed

    private void btnPre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre1ActionPerformed
        // TODO add your handling code here:
        Trang1 = 1;
        try {
            loadTableLam(Trang1);
        } catch (Exception ex) {
            Logger.getLogger(jplKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblTrangNghi.setText("1");
    }//GEN-LAST:event_btnPre1ActionPerformed

    private void btnFist1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFist1ActionPerformed
        // TODO add your handling code here:
        if (Trang1 > 1) {
            Trang1--;
            try {
                loadTableNghi(Trang1);
            } catch (Exception ex) {
                Logger.getLogger(jplKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            lblTrangNghi.setText("" + Trang1);

        }
    }//GEN-LAST:event_btnFist1ActionPerformed

    private void btnLast1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLast1ActionPerformed
        // TODO add your handling code here:
        if (Trang1 < soTrang1) {
            Trang1++;
            try {
                loadTableNghi(Trang1);
            } catch (Exception ex) {
                Logger.getLogger(jplKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            lblTrangNghi.setText("" + Trang1);

        }
    }//GEN-LAST:event_btnLast1ActionPerformed

    private void btnEnd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnd1ActionPerformed
        // TODO add your handling code here:
        if (Trang1 < soTrang1) {
            Trang1++;
            loadTableNghi(Trang1);
            lblTrangNghi.setText(Trang1 + "/" + soTrang1);

        }
    }//GEN-LAST:event_btnEnd1ActionPerformed

    private void btnPre2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre2ActionPerformed
        // TODO add your handling code here:
        Trang2 = 1;
        try {
            loadTableCV(Trang2);
        } catch (Exception ex) {
            Logger.getLogger(jplNhanVien1.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblChucVu.setText("1");
    }//GEN-LAST:event_btnPre2ActionPerformed

    private void btnFist2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFist2ActionPerformed
        // TODO add your handling code here:
        if (Trang2 > 1) {
            Trang2--;
            try {
                loadTableCV(Trang2);
            } catch (Exception ex) {
                Logger.getLogger(jplNhanVien1.class.getName()).log(Level.SEVERE, null, ex);
            }
            lblChucVu.setText("" + Trang2);

        }
    }//GEN-LAST:event_btnFist2ActionPerformed

    private void btnLast2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLast2ActionPerformed
        // TODO add your handling code here:
        if (Trang2 < soTrang2) {
            Trang2++;
            try {
                loadTableCV(Trang2);
            } catch (Exception ex) {
                Logger.getLogger(jplNhanVien1.class.getName()).log(Level.SEVERE, null, ex);
            }
            lblChucVu.setText("" + Trang2);

        }
    }//GEN-LAST:event_btnLast2ActionPerformed

    private void btnEnd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnd2ActionPerformed
        // TODO add your handling code here:
        Trang2 = soTrang2;
        try {
            loadTableCV(Trang2);
        } catch (Exception ex) {
            Logger.getLogger(jplNhanVien1.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblChucVu.setText("" + soTrang2);

    }//GEN-LAST:event_btnEnd2ActionPerformed

    private void btnUpAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpAnhActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser f = new JFileChooser("src\\AnhNV");
            f.showOpenDialog(null);
            File file = f.getSelectedFile();
            Image img = ImageIO.read(file);
            fileanh = file.getName();
            int w = lblHinh.getWidth();
            int h = lblHinh.getHeight();
            lblHinh.setIcon(new ImageIcon(img.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnUpAnhActionPerformed

    private void btnTimSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSdtActionPerformed
        // TODO add your handling code here:

        String sdt = txtTimKiemSDT.getText().trim();
        if (sdt.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại của nhân viên muốn tìm");
            txtTimKiemSDT.requestFocus();
            return;
        }

        loadTimLamBySdt(sdt);

        loadTimNghiBySdt(sdt);
    }//GEN-LAST:event_btnTimSdtActionPerformed

    private void cbbLocCVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLocCVItemStateChanged
        // TODO add your handling code here:
        String tenCV = cbbLocCV.getSelectedItem().toString();

        loadTimLamByCV(tenCV);

        loadTimNghiByCV(tenCV);
    }//GEN-LAST:event_cbbLocCVItemStateChanged

    private void btnCapNhatNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatNVActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jplNhanVien1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jplNhanVien1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jplNhanVien1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jplNhanVien1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jplNhanVien1 dialog = new jplNhanVien1(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatNV;
    private javax.swing.JButton btnClearCV;
    private javax.swing.JButton btnEnd;
    private javax.swing.JButton btnEnd1;
    private javax.swing.JButton btnEnd2;
    private javax.swing.JButton btnFist;
    private javax.swing.JButton btnFist1;
    private javax.swing.JButton btnFist2;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLast1;
    private javax.swing.JButton btnLast2;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnPre1;
    private javax.swing.JButton btnPre2;
    private javax.swing.JButton btnSuaCV;
    private javax.swing.JButton btnThemCV;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnTimSdt;
    private javax.swing.JButton btnUpAnh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbbCV;
    private javax.swing.JComboBox<String> cbbLocCV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblTrang;
    private javax.swing.JLabel lblTrangNghi;
    private javax.swing.JRadioButton rdoCon;
    private javax.swing.JRadioButton rdoDiLam;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghi;
    private javax.swing.JRadioButton rdoNghiLam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblChucVu;
    private javax.swing.JTable tblNVLam;
    private javax.swing.JTable tblNVNghi;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoVaTen;
    private javax.swing.JTextField txtMaCV;
    private javax.swing.JTextField txtMaNV;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private com.toedter.calendar.JDateChooser txtNgaySua;
    private com.toedter.calendar.JDateChooser txtNgayTao;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenCV;
    private javax.swing.JTextField txtTimKiemSDT;
    // End of variables declaration//GEN-END:variables

    private void clearCV() {
        txtMaCV.setText("");
        txtTenCV.setText("");
        rdoCon.setSelected(false);
        rdoNghi.setSelected(false);

    }

    private void clearNV() {
        txtMaNV.setText("");
        txtHoVaTen.setText("");
        txtNgaySinh.setDate(null);
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtPass.setText("");
        rdoNam.setSelected(true);
        rdoDiLam.setSelected(true);
        cbbCV.setSelectedIndex(0);
        lblHinh.setIcon(new ImageIcon("src\\AnhNV\\hinh_nv025.jpg"));
    }

    private ChucVu getCVForm() {
        String ma = txtMaCV.getText().trim();
        String ten = txtTenCV.getText().trim();
        int tt = rdoCon.isSelected() == true ? 0 : 1;
        if (ma.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã chức vụ");
            txtMaCV.requestFocus();
            return null;
        }
        if (ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên chức vụ");
            txtTenCV.requestFocus();
            return null;
        }
        return new ChucVu(ma, ten, tt);
    }

    private void loadComboCV() {
        ArrayList<ChucVu> list = cvs.getALLCV();
        for (ChucVu x : list) {
            dcmCV.addElement(new ChucVu(x.getMaChucVu(), x.getTenChucVu(), x.getTrangThai()));

        }
    }

    private NhanVien getNVForm() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date;
        ArrayList<NhanVien> listNV = nvs.getALLNV();
        String ma =txtMaNV.getText();
        String ten = txtHoVaTen.getText().trim();
        String nsinh = txtNgaySinh.getDate().toString();
        String gt = rdoNam.isSelected() == true ? "Nam" : "Nữ";
        String dchi = txtDiaChi.getText().trim();
        String sdt = txtSDT.getText().trim();
        String email = txtEmail.getText().trim();
        String mk = String.valueOf(txtPass.getPassword());
        ChucVu cv = (ChucVu) cbbCV.getModel().getSelectedItem();
        int tt = rdoDiLam.isSelected() == true ? 1 : 0;

        if (ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống họ tên nhân viên");
            txtHoVaTen.requestFocus();
            return null;
        }
        if (nsinh.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống ngày sinh nhân viên");
            txtNgaySinh.requestFocus();
            return null;
        } else {

            try {
                date = txtNgaySinh.getDate();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        if (dchi.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống địa chỉ nhân viên");
            txtDiaChi.requestFocus();
            return null;
        }
        if (sdt.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống số điện thoại nhân viên");
            txtDiaChi.requestFocus();
            return null;
        } else {
            try {
                int dienthoai = Integer.parseInt(txtSDT.getText());
                String ktsdt = "0\\d{9}";
                if (txtSDT.getText().matches(ktsdt) == false) {
                    JOptionPane.showMessageDialog(this, "Bạn nhập sai số điện thoại");
                    txtSDT.requestFocus();
                    return null;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại");
                txtSDT.requestFocus();
                e.printStackTrace();
                return null;
            }
        }
        if (email.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống email nhân viên");
            txtEmail.requestFocus();
            return null;
        } else {
            String ktemail = "\\w+@\\w+(\\.\\w+){1,2}";
            if (email.matches(ktemail) == false) {
                JOptionPane.showMessageDialog(this, "Sai định dạng email");
                txtEmail.requestFocus();
                return null;
            }
        }
        if (mk.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu");
            txtPass.requestFocus();
            return null;
        }
        if (fileanh == null) {
            fileanh = "hinh1.jpg";
        }
        NhanVien nvm = new NhanVien(null, ma, ten, date, gt, dchi, sdt, email, mk, cv, fileanh, tt);

        return nvm;
    }

    private ChucVu getCV(int row, String tenCV) {
        ArrayList<ChucVu> cvm = cvs.getALLCV();
        for (ChucVu cm : cvm) {
            if (cm.getTenChucVu().equals(tenCV)) {
                return new ChucVu(cm.getIdchucVu(), cm.getMaChucVu(), cm.getTenChucVu(), cm.getTrangThai());
            }
        }
        return null;
    }

    private void loadComboCVTim() {
        ArrayList<ChucVu> list = cvs.getALLCV();
        for (ChucVu x : list) {
            dcmCVM.addElement(new ChucVu(x.getIdchucVu(), x.getMaChucVu(), x.getTenChucVu(), x.getTrangThai()));

        }
    }

}
