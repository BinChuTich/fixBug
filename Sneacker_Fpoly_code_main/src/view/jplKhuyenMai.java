/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.sql.Connection;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.KhuyenMai;
import service.KhuyenMaiService;
import utilitys.DBConnection;
import view.jplKhachHang;

/**
 *
 * @author LENOVO
 */
public class jplKhuyenMai extends javax.swing.JPanel {

    /**
     * Creates new form jplKhuyenMai
     */
    private KhuyenMaiService kms = new KhuyenMaiService();

    private KhuyenMaiService khuyenMaiService = new KhuyenMaiService();
    DefaultTableModel defaultTableModelGetKM;
    DefaultTableModel dtmSP;
    DefaultTableModel dtmKM;

    Connection cn;
    long count, soTrang, trang = 1;

    public jplKhuyenMai() throws Exception {
        initComponents();
        countDb();
        if (count % 3 == 0) {
            soTrang = count / 3;
        } else {
            soTrang = count / 3 + 1;
        }
//        loadData(1);
        loadTableKM(1);

        lb_trang.setText("1");
    }

    public void loadTableKM(long trang) {
        ArrayList<KhuyenMai> list = getAllPTKM();
        defaultTableModelGetKM = (DefaultTableModel) tbl_KhuyenMai.getModel();
        defaultTableModelGetKM.setRowCount(0);
        for (KhuyenMai km : list) {
            defaultTableModelGetKM.addRow(new Object[]{
                km.getMaKhuyenMai(),
                km.getTenKhuyenMai(),
                km.getHinhThucApDung(),
                km.getLoaiGiamGia(),
                km.getGiaTri(),
                km.getGiamToiDa(),
                km.getNgayBatDau(),
                km.getNgayKetThuc(),
                km.getTrangThai() == 1 ? "Còn Hiệu Lực" : "Hết Hạn",});
        }
    }

    public void countDb() throws Exception {
        try {
            String query = "Select count(*) from KhuyenMai";
            cn = DBConnection.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(jplKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<KhuyenMai> getAllPTKM() {
        try {
            ArrayList<KhuyenMai> list = new ArrayList<>();
            java.sql.Connection con = DBConnection.getConnection();
            String query = "select top 3 * from KhuyenMai where maKhuyenMai not in (select top " + (trang * 3 - 3) + "maKhuyenMai from KhuyenMai )";
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
      public void lammoi() {
        txtMaKhuyenMai.setText("");
        txtTenKhuyenMai.setText("");
        txtBatDau.setDate(null);
        txtKetThuc.setDate(null);
        txtGiaTri.setText("");
        cbbHinhThucGiamGia.setSelectedIndex(0);
    }

    private KhuyenMai KiemTraDuLieu() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String makm = txtMaKhuyenMai.getText().trim();
        String tenkm = txtTenKhuyenMai.getText().trim();
        String hinhthuc = cbbHinhThucGiamGia.getSelectedIndex() == 0 ? "Giảm theo%" : "Giảm Theo Tiền";
        BigDecimal giatri = null;
        BigDecimal giamtoida = null;
        String ngayBD = txtBatDau.getDate().toString();
        String ngayKT = txtKetThuc.getDate().toString();
        java.util.Date NgayBatDau;
        java.util.Date NgayKetThuc;
        int trangthai = 0;
        if (makm.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã Khuyến Mại");
            return null;
        }
        if (tenkm.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên Khuyến Mại");
            return null;
        }
        if (txtGiaTri.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống giá trị");
            return null;
        } else {
            try {
                giatri = BigDecimal.valueOf(Double.parseDouble(txtGiaTri.getText()));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "sai định dạng giá trị");
                e.printStackTrace();
            }
        }

        if (txtGiamToiDa.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống giảm tối đa");
            return null;
        } else {
            try {
                giamtoida = BigDecimal.valueOf(Double.parseDouble(txtGiamToiDa.getText()));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "sai định dạng giảm tối đa");
                e.printStackTrace();
            }
        }

        if (ngayBD == null) {
            JOptionPane.showMessageDialog(this, "Không được để trống ngày bắt đầu");
            txtBatDau.requestFocus();
            return null;
        } else {

            NgayBatDau = txtBatDau.getDate();
        }
        if (ngayKT == null) {
            JOptionPane.showMessageDialog(this, "Không được để trống ngày kết thúc");
            txtKetThuc.requestFocus();
            return null;
        } else {

            NgayKetThuc = txtKetThuc.getDate();
        }

        int compare = NgayBatDau.compareTo(NgayKetThuc);

        if (compare > 0) {
            JOptionPane.showMessageDialog(this, "ngày kết thúc phải sau ngày bắt đầu");
            return null;
        } else if (compare == 0) {
            JOptionPane.showMessageDialog(this, "ngày kết thúc phải sau ngày bắt đầu");
            return null;
        } else {

            NgayBatDau = txtBatDau.getDate();
            NgayKetThuc = txtKetThuc.getDate();

        }
        java.util.Date currentDate = new java.util.Date();

        if (currentDate.before(NgayKetThuc)) {
            trangthai = 0;
        } else if (currentDate.after(NgayKetThuc)) {
            trangthai = 1;
        }
        KhuyenMai km = new KhuyenMai(makm, tenkm, giatri, giamtoida, NgayBatDau, NgayKetThuc, hinhthuc, trangthai);
        return km;
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        PanelaLL = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaKhuyenMai = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenKhuyenMai = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbbHinhThucGiamGia = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtGiaTri = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGiamToiDa = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtBatDau = new com.toedter.calendar.JDateChooser();
        txtKetThuc = new com.toedter.calendar.JDateChooser();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMOi = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_KhuyenMai = new javax.swing.JTable();
        btn_nho1 = new javax.swing.JButton();
        btn_nho2 = new javax.swing.JButton();
        btn_lon1 = new javax.swing.JButton();
        lb_trang = new javax.swing.JLabel();
        btn_lon2 = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        PanelaLL.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));

        jLabel1.setText("Tạo mã khuyến mại");

        jLabel2.setText("Tạo chương trình khuyến mãi");

        jLabel3.setText("Hình thức giảm giá");

        cbbHinhThucGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giảm theo%", "Giảm Theo Tiền" }));

        jLabel4.setText("Giá Trị");

        jLabel10.setText("Giảm tối đa");

        javax.swing.GroupLayout PanelaLLLayout = new javax.swing.GroupLayout(PanelaLL);
        PanelaLL.setLayout(PanelaLLLayout);
        PanelaLLLayout.setHorizontalGroup(
            PanelaLLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelaLLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelaLLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelaLLLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(114, 114, 114)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(177, 177, 177))
                    .addGroup(PanelaLLLayout.createSequentialGroup()
                        .addGroup(PanelaLLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelaLLLayout.createSequentialGroup()
                        .addGroup(PanelaLLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaKhuyenMai, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelaLLLayout.createSequentialGroup()
                                .addComponent(cbbHinhThucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGiamToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51))))
        );
        PanelaLLLayout.setVerticalGroup(
            PanelaLLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelaLLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelaLLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelaLLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbHinhThucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiamToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(232, 238, 238), null));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Thời gian sử dụng");

        jLabel6.setText("Thời gian bắt đầu giảm giá");

        jLabel7.setText("Thời gian kết thúc giảm giá");

        txtBatDau.setDateFormatString("yyyy-MM-dd");

        txtKetThuc.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(13, 13, 13))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(txtKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        btnLuu.setBackground(new java.awt.Color(255, 250, 205));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Save as.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 250, 205));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/iconUpload.png"))); // NOI18N
        btnSua.setText("Cập Nhật");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMOi.setBackground(new java.awt.Color(255, 250, 205));
        btnLamMOi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/cleaning.png"))); // NOI18N
        btnLamMOi.setText("Xóa");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelaLL, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMOi, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelaLL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLamMOi)
                            .addComponent(btnSua)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbl_KhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khuyến mại", "Tên chương trình", "Áp dụng", "Hình thức giảm giá", "Giá Trị", "Giảm tối đa", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_KhuyenMai.setSelectionBackground(new java.awt.Color(153, 255, 153));
        tbl_KhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_KhuyenMai);

        btn_nho1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2T.png"))); // NOI18N
        btn_nho1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nho1ActionPerformed(evt);
            }
        });

        btn_nho2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/nextT.png"))); // NOI18N
        btn_nho2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nho2ActionPerformed(evt);
            }
        });

        btn_lon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2P.png"))); // NOI18N
        btn_lon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lon1ActionPerformed(evt);
            }
        });

        lb_trang.setText("jLabel11");

        btn_lon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/nextP.png"))); // NOI18N
        btn_lon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lon2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(btn_nho1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_nho2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_trang)
                .addGap(18, 18, 18)
                .addComponent(btn_lon2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_lon1)
                .addContainerGap(575, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_lon1)
                    .addComponent(btn_lon2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lb_trang))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_nho1)
                        .addComponent(btn_nho2)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1151, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        ArrayList<KhuyenMai> listKM = kms.getAll();
        int row = tbl_KhuyenMai.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");
            return;
        }
        KhuyenMai km = KiemTraDuLieu();
        if (km == null) {
            return;
        }
        String ma = txtMaKhuyenMai.getText().trim();
        km.setMaKhuyenMai(ma);
        KhuyenMai k = new KhuyenMai();

        // set IDKM vào CTSP
       

        // Danh mục được chọn



        if (kms.update(km) != null) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            loadTableKM(trang);
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
        loadTableKM(trang);
        lammoi();
        
        
        
        
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btn_nho1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nho1ActionPerformed
        // TODO add your handling code here:
        trang = 1;
        try {
            loadTableKM(trang);
        } catch (Exception ex) {
            Logger.getLogger(jplKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        lb_trang.setText("1");
    }//GEN-LAST:event_btn_nho1ActionPerformed

    private void btn_nho2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nho2ActionPerformed
        // TODO add your handling code here:
        if (trang > 1) {
            trang--;
            try {
                loadTableKM(trang);
            } catch (Exception ex) {
                Logger.getLogger(jplKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            lb_trang.setText("" + trang);

        }
    }//GEN-LAST:event_btn_nho2ActionPerformed

    private void btn_lon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lon2ActionPerformed
        // TODO add your handling code here:
        if (trang < soTrang) {
            trang++;
            try {
                loadTableKM(trang);
            } catch (Exception ex) {
                Logger.getLogger(jplKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            lb_trang.setText("" + trang);

        }
    }//GEN-LAST:event_btn_lon2ActionPerformed

    private void btn_lon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lon1ActionPerformed
        // TODO add your handling code here:
        trang = soTrang;
        try {
            loadTableKM(trang);
        } catch (Exception ex) {
            Logger.getLogger(jplKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        lb_trang.setText("" + soTrang);
    }//GEN-LAST:event_btn_lon1ActionPerformed

    private void tbl_KhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KhuyenMaiMouseClicked
        // TODO add your handling code here:
        int row = tbl_KhuyenMai.getSelectedRow();
        if (row < 0) {

            return;
        }
        txtMaKhuyenMai.setText(tbl_KhuyenMai.getValueAt(row, 0).toString());
        txtTenKhuyenMai.setText(tbl_KhuyenMai.getValueAt(row, 1).toString());

        try {
            String ngaybd = tbl_KhuyenMai.getValueAt(row, 6).toString();
            String ngaykt = tbl_KhuyenMai.getValueAt(row, 7).toString();

            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngaybd);
            java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(ngaykt);
            txtBatDau.setDate(date);
            txtKetThuc.setDate(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtGiaTri.setText(tbl_KhuyenMai.getValueAt(row, 4).toString());
        txtGiamToiDa.setText(tbl_KhuyenMai.getValueAt(row, 5).toString());
        cbbHinhThucGiamGia.setSelectedItem(tbl_KhuyenMai.getValueAt(row, 3).toString());
        ArrayList<KhuyenMai> k = khuyenMaiService.getAll();
        String apDung = "";
        String loai = "";
        String idKM = "";
        for (KhuyenMai x : k) {
            if (x.getMaKhuyenMai() != null && x.getMaKhuyenMai().equals(tbl_KhuyenMai.getValueAt(row, 0).toString())) {
                apDung = x.getApDungGiamGia();
                loai = x.getLoaiGiamGia();
                idKM = x.getId();

            }
        }


    }//GEN-LAST:event_tbl_KhuyenMaiMouseClicked

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        ArrayList<KhuyenMai> listKM = kms.getAll();

        // Thêm khuyến mại
        KhuyenMai km = KiemTraDuLieu();
        if (km.getNgayKetThuc().before(new java.util.Date())) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày hiện tại");
            return;
        }
        for (KhuyenMai kmCheck : listKM) {
            if (km.getMaKhuyenMai().equals(kmCheck.getMaKhuyenMai())) {
                JOptionPane.showMessageDialog(this, "Mã đã tồn tại");
                return;
            }
        }
        if (kms.insert(km) != null) {
            JOptionPane.showMessageDialog(this, "Thêm Thành Công !!");

            loadTableKM(trang);

        } else {
            JOptionPane.showMessageDialog(this, "Thêm Thất bại !!");

        }

        // set IDKM vào CTSP
        // Danh mục được chọn

    }//GEN-LAST:event_btnLuuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelaLL;
    private javax.swing.JButton btnLamMOi;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btn_lon1;
    private javax.swing.JButton btn_lon2;
    private javax.swing.JButton btn_nho1;
    private javax.swing.JButton btn_nho2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbHinhThucGiamGia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb_trang;
    private javax.swing.JTable tbl_KhuyenMai;
    private com.toedter.calendar.JDateChooser txtBatDau;
    private javax.swing.JTextField txtGiaTri;
    private javax.swing.JTextField txtGiamToiDa;
    private com.toedter.calendar.JDateChooser txtKetThuc;
    private javax.swing.JTextField txtMaKhuyenMai;
    private javax.swing.JTextField txtTenKhuyenMai;
    // End of variables declaration//GEN-END:variables
}
