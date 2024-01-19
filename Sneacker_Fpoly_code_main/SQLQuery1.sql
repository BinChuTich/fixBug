use UngDungBanGiaySneaker
insert into ChucVu(maChucVu,tenChucVu,ngayTao,ngaySua,trangThai)
values ('CV1', 'Quản lý','9/1/2024',null,1)
insert into ChucVu(maChucVu,tenChucVu,ngayTao,ngaySua,trangThai)
values ('CV2', 'Nhân Viên','9/1/2024',null,1)
insert into NhanVien(maNhanVien,hoVaTen,ngaySinh,gioiTinh,diaChi,sdt,email,matKhau,IDCV,Hinh,NgayTao,NgaySua,TrangThai)
values ('NV100','Ngô Văn Tình','08-08-2003','Nam','Hà Nội','123123','Tinh@gmail.com','123','E321E58C-6231-4F6D-B755-7A733E542308','hinh1.jpg','9-1-2023',null,1)
select * from ChucVu
select * from nhanvien
INSERT INTO KhuyenMai (maKhuyenMai, tenKhuyenMai, giaTri, giamToiDa, ngayBatDau, ngayKetThuc, hinhThucApDung, apDungGiamGia, loaiGiamGia, ngayTao, ngaySua, trangThai)
VALUES 
('KM01', 'Giảm giá 20%', 20, 50000, '2024-01-10', '2024-01-20', 'Tất cả sản phẩm', 1, 'Phần trăm', GETDATE(), GETDATE(), 1),
('KM02', 'Mã giảm giá 50K', 0, 50000, '2024-02-05', '2024-02-15', 'Sản phẩm cụ thể', 1, 'Số tiền', GETDATE(), GETDATE(), 1),
('KM03', 'Mua 2 tặng 1', 0, 0, '2024-03-01', '2024-03-31', 'Sản phẩm cụ thể', 1, 'Khuyến mãi sản phẩm', GETDATE(), GETDATE(), 1),
('KM04', 'Giảm 30% ', 30, 0, '2024-04-15', '2024-04-30', 'Hóa đơn cụ thể', 1, 'Phần trăm', GETDATE(), GETDATE(), 1),
('KM05', 'Free ship', 0, 0, '2024-05-01', '2024-05-10', 'Phí vận chuyển', 1, 'Miễn phí vận chuyển', GETDATE(), GETDATE(), 1);
INSERT INTO KhachHang (maKhachHang, loaiKhachHang, tenKhachHang, diaChi, gioiTinh, email, ngaySinh, ngayThamGia, tichDiem, diemEXP, ngayTao, ngaySua, trangThai)
VALUES 
('KH01', 'Thường', 'Nguyễn Văn Nam', '123 Đường ABC', 'Nam', 'nguyenvana@email.com', '1990-01-01', '2023-01-01', 100, 50, GETDATE(), GETDATE(), 1),
('KH02', 'VIP', 'Trần ThịBinhf', '456 Đường XYZ', 'Nữ', 'tranthib@email.com', '1995-05-10', '2022-12-01', 500, 200, GETDATE(), GETDATE(), 1),
('KH03', 'Thành viên', 'Lê Văn Chiến', '789 Đường MNO', 'Nam', 'levanc@email.com', '1988-12-20', '2023-05-15', 300, 100, GETDATE(), GETDATE(), 1),
('KH04', 'Thường', 'Phạm Thị Dung', '111 Đường KLM', 'Nữ', 'phamthid@email.com', '1992-06-25', '2024-01-05', 200, 80, GETDATE(), GETDATE(), 1),
('KH05', 'VIP', 'Hoàng Văn anh', '222 Đường QRS', 'Nam', 'hoangvane@email.com', '1985-09-30', '2023-09-20', 700, 300, GETDATE(), GETDATE(), 1),
('KH06', 'Thành viên', 'Mai Thị Chung', '333 Đường UVW', 'Nữ', 'maithif@email.com', '1998-04-12', '2022-11-11', 400, 150, GETDATE(), GETDATE(), 1),
('KH07', 'Thường', 'Đặng Văn Giang', '444 Đường XYZ', 'Nam', 'dangvang@email.com', '1993-08-18', '2023-07-30', 150, 70, GETDATE(), GETDATE(), 1),
('KH08', 'VIP', 'Vũ Thị Huyền', '555 Đường PQR', 'Nữ', 'vuthih@email.com', '1989-12-03', '2024-03-10', 800, 400, GETDATE(), GETDATE(), 1),
('KH09', 'Thành viên', 'Nguyễn Văn Anh', '666 Đường LMN', 'Nam', 'nguyenvani@email.com', '1996-02-20', '2023-10-25', 600, 250, GETDATE(), GETDATE(), 1),
('KH10', 'Thường', 'Trần Thị Khuyên', '777 Đường STU', 'Nữ', 'tranthik@email.com', '1991-11-08', '2022-09-15', 250, 100, GETDATE(), GETDATE(), 1);
insert into ChiTietHoaDon(idHoaDon,idChiTietSanPham,soLuong,donGia,ngayBan,ngayTao,ngaySua,trangThai)
values (
select * from HoaDon
select * from nhanvien
select * from KhachHang
select * from KhuyenMai
INSERT INTO HoaDon (maHoaDon, idNhanVien, idKhachHang, ngayMua, thanhTien, idKhuyenMai, GhiChu, NgayTao, NgaySua, TrangThai)
VALUES 
('HD001','D73EDA44-52A1-4DFD-88AD-017D28B4E298', '41D6FDCD-155E-4797-9286-2DC4853F897E', '2024-01-01', 500000, '78662A4C-0BF4-498F-9387-164B7DC42CB3', 'Mua hàng ngày đầu năm', GETDATE(), GETDATE(), 1),
('HD002', 'EDD72C0D-3AA6-4A04-BC04-0759752EE33E', 'C91FEC36-B3AA-41B2-B727-2F5A1442317F', '2024-01-05', 750000, 'A243A414-9689-46B4-B015-2F7DD32D2780', 'Khách hàng VIP', GETDATE(), GETDATE(), 1),
('HD003', 'EDD72C0D-3AA6-4A04-BC04-0759752EE33E', '06976F89-5D6D-4ADE-913D-3B16C108F43C', '2024-01-10', 300000, '6DE92D11-E41E-4FA8-AA69-679CDF4E5A50', 'Khuyến mãi sản phẩm', GETDATE(), GETDATE(), 1),
('HD004', 'DB3EB2AD-34B9-4EC2-B7A4-09BE0D22C055', '08FC6E2A-2D73-493C-A014-569C53A9F096', '2024-01-15', 1000000, 'F06FFAB4-4102-4FE7-80DB-936A20416C74', 'Hóa đơn lớn', GETDATE(), GETDATE(), 1);
INSERT INTO MauSac (ma, ten, NgayTao, NgaySua, trangthai)
VALUES 
('MS001', 'Đen', GETDATE(), GETDATE(), 1),
('MS002', 'Trắng', GETDATE(), GETDATE(), 1),
('MS003', 'Đỏ', GETDATE(), GETDATE(), 1),
('MS004', 'Xanh lá', GETDATE(), GETDATE(), 1),
('MS005', 'Xanh dương', GETDATE(), GETDATE(), 1),
('MS006', 'Vàng', GETDATE(), GETDATE(), 1),
('MS007', 'Cam', GETDATE(), GETDATE(), 1),
('MS008', 'Hồng', GETDATE(), GETDATE(), 1),
('MS009', 'Tím', GETDATE(), GETDATE(), 1),
('MS010', 'Nâu', GETDATE(), GETDATE(), 1);
INSERT INTO Hang (maHang, tenHang, ngayTao, ngaySua, trangThai)
VALUES 
('H001', 'Nike', GETDATE(), GETDATE(), 1),
('H002', 'Adidas', GETDATE(), GETDATE(), 1),
('H003', 'Puma', GETDATE(), GETDATE(), 1),
('H004', 'Jordan', GETDATE(), GETDATE(), 1),
('H005', 'Converse', GETDATE(), GETDATE(), 1),
('H006', 'Vans', GETDATE(), GETDATE(), 1),
('H007', 'New Balance', GETDATE(), GETDATE(), 1),
('H008', 'Reebok', GETDATE(), GETDATE(), 1),
('H009', 'Under Armour', GETDATE(), GETDATE(), 1),
('H010', 'Asics', GETDATE(), GETDATE(), 1);
INSERT INTO DanhMuc (ma, Ten, NgayTao, NgaySua, TrangThai)
VALUES 
('DM001', 'Sneaker nam', GETDATE(), GETDATE(), 1),
('DM002', 'Sneaker nữ', GETDATE(), GETDATE(), 1),
('DM003', 'Boots nam', GETDATE(), GETDATE(), 1),
('DM004', 'Boots nữ', GETDATE(), GETDATE(), 1),
('DM005', 'Giày thể thao nam', GETDATE(), GETDATE(), 1),
('DM006', 'Giày thể thao nữ', GETDATE(), GETDATE(), 1),
('DM007', 'Giày cao gót nữ', GETDATE(), GETDATE(), 1),
('DM008', 'Giày lười nam', GETDATE(), GETDATE(), 1),
('DM009', 'Giày sandal nữ', GETDATE(), GETDATE(), 1),
('DM010', 'Giày búp bê nữ', GETDATE(), GETDATE(), 1);
DECLARE @maSize INT
SET @maSize = 36

WHILE @maSize <= 44
BEGIN
    INSERT INTO Size (ma, Ten, NgayTao, NgaySua, TrangThai)
    VALUES 
    ('SZ' + CAST(@maSize AS NVARCHAR(10)), 'sz' + CAST(@maSize AS NVARCHAR(10)), GETDATE(), GETDATE(), 1)

    SET @maSize = @maSize + 1
END
select * from Size
INSERT INTO ChatLieu (ma, Ten, NgayTao, NgaySua, TrangThai)
VALUES 
('CL001', 'Da tổng hợp', GETDATE(), GETDATE(), 1),
('CL002', 'Vải canvas', GETDATE(), GETDATE(), 1),
('CL003', 'Nỉ', GETDATE(), GETDATE(), 1),
('CL004', 'Da lộn', GETDATE(), GETDATE(), 1),
('CL005', 'Mesh', GETDATE(), GETDATE(), 1),
('CL006', 'Vải dệt', GETDATE(), GETDATE(), 1),
('CL007', 'Cao su tổng hợp', GETDATE(), GETDATE(), 1),
('CL008', 'Gamuza', GETDATE(), GETDATE(), 1),
('CL009', 'Polyester', GETDATE(), GETDATE(), 1),
('CL010', 'Nylon', GETDATE(), GETDATE(), 1);
ALTER TABLE SanPham
ALTER COLUMN tenSanPham nvarchar(100)

INSERT INTO SanPham (maSanPham, tenSanPham, NgayTao, NgaySua, TrangThai)
VALUES 
('SP001', 'Nike Air Force 1', GETDATE(), GETDATE(), 1),
('SP002', 'Adidas Superstar', GETDATE(), GETDATE(), 1),
('SP003', 'Converse Chuck Taylor All Star', GETDATE(), GETDATE(), 1),
('SP004', 'Vans Old Skool', GETDATE(), GETDATE(), 1),
('SP005', 'Jordan 1', GETDATE(), GETDATE(), 1),
('SP006', 'New Balance 574', GETDATE(), GETDATE(), 1),
('SP007', 'Puma Suede Classic', GETDATE(), GETDATE(), 1),
('SP008', 'Reebok Classic Leather', GETDATE(), GETDATE(), 1),
('SP009', 'Yeezy Boost 350', GETDATE(), GETDATE(), 1),
('SP010', 'Asics Gel-Lyte III', GETDATE(), GETDATE(), 1),
('SP011', 'Saucony Jazz Original', GETDATE(), GETDATE(), 1),
('SP012', 'Fila Disruptor', GETDATE(), GETDATE(), 1),
('SP013', 'Under Armour Curry', GETDATE(), GETDATE(), 1),
('SP014', 'Balenciaga Triple S', GETDATE(), GETDATE(), 1),
('SP015', 'Off-White x Nike Blazer', GETDATE(), GETDATE(), 1),
('SP016', 'Gucci Ace Sneaker', GETDATE(), GETDATE(), 1),
('SP017', 'Louis Vuitton Run Away Sneaker', GETDATE(), GETDATE(), 1),
('SP018', 'Christian Louboutin Red Runner', GETDATE(), GETDATE(), 1),
('SP019', 'Dior B23 High-Top Sneaker', GETDATE(), GETDATE(), 1),
('SP020', 'Prada Cloudbust Sneaker', GETDATE(), GETDATE(), 1),
('SP021', 'Versace Chain Reaction Sneaker', GETDATE(), GETDATE(), 1),
('SP022', 'Givenchy Jaw Low Sneaker', GETDATE(), GETDATE(), 1),
('SP023', 'Burberry Arthur Sneaker', GETDATE(), GETDATE(), 1),
('SP024', 'Alexander McQueen Oversized Sneaker', GETDATE(), GETDATE(), 1),
('SP025', 'Balmain B-Bold Sneaker', GETDATE(), GETDATE(), 1),
('SP026', 'Valentino Garavani Bounce Sneaker', GETDATE(), GETDATE(), 1),
('SP027', 'Prada Americas Cup Sneaker', GETDATE(), GETDATE(), 1),
('SP028', 'Fendi FF Sneaker', GETDATE(), GETDATE(), 1),
('SP029', 'Givenchy Spectre Runner', GETDATE(), GETDATE(), 1),
('SP030', 'Hermès Trail Sneaker', GETDATE(), GETDATE(), 1);
select * from SanPham
select * from hang
select * from Size
select * from DanhMuc
select * from ChatLieu
select * from MauSac
select * from KhuyenMai

INSERT INTO ChiTietSanPham (idSanPham, hang, Size, DanhMuc, ChatLieu, MauSac, idKhuyenMai, giaNhap, giaBan, QR, HinhAnh, SoLuong, MoTa, NgayTao, NgaySua, TrangThai)
VALUES 
('6178171D-F5C1-4C10-84E5-02FA7FE74B44','D47BDD63-D35B-44A8-8E80-0087150BA97F','DF4AA857-C453-4B97-8D28-28913F9CCBE3','63799B69-6EF3-4B8C-BC68-46524398EE9B','4963A3FA-4D5E-4DED-AA9D-37AC90AE76A8','07CC3FCD-031F-4BCB-B115-478DA87CC081','78662A4C-0BF4-498F-9387-164B7DC42CB3', 800000, 1200000, 'ABC123', 'nike_1.jpg', 50, 'Giày sneaker nam chất liệu mesh, màu đen, size 39', GETDATE(), GETDATE(), 1)
