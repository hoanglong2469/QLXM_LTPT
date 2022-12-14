use master
go
create database QuanLyBanHang
go

use QuanLyBanHang
go

create table KhachHang(
	Ma_KhachHang nvarchar(30) not null primary key,
	Ten_KhachHang nvarchar(30) unique not null,
	DiaChi_KhachHang nvarchar(100),
	SDT_KhachHang nvarchar(20)
)
go

create table Xe(
	Ma_Xe nvarchar(20) not null primary key,
	Ten_Xe nvarchar(30) not null,
	Loai_Xe nvarchar(40),
	Nam_SX int,
	So_PK int,
	So_Khung int,
	So_Suon int,
	Mau_Xe nvarchar(30),
	Gia_Xe int 
)
go

create table NhanVien(
	Ma_NV nvarchar(20) not null primary key,
	Ten_NV nvarchar(20),
	SDT_NV nvarchar(20),
	DiaChi_NV nvarchar(100),
	Email_NV nvarchar(40)
	)

create table HoaDon(
	Ma_HD nvarchar(20) not null primary key,
	Ma_KhachHang nvarchar(30) not null foreign key references KhachHang(Ma_KhachHang),
	Ma_Xe nvarchar(20) not null foreign key references Xe(Ma_Xe),
	Ma_NV nvarchar(20) not null foreign key references NhanVien(Ma_NV),
	NgayLap_HD datetime,
	DonGia money,
	SoLuong int,
	ThanhTien money
)

create table Account(
	Loai_Account nvarchar(30),
	Username nvarchar(20),
	Pass nvarchar(20)
)

INSERT [dbo].[KhachHang] ([Ma_KhachHang], [Ten_KhachHang], [DiaChi_KhachHang], [SDT_KhachHang]) VALUES (N'KH01', N'Lê Thị Thanh', N'12 Nguyễn Văn Bảo',N'0397922365')
INSERT [dbo].[KhachHang] ([Ma_KhachHang], [Ten_KhachHang], [DiaChi_KhachHang], [SDT_KhachHang]) VALUES (N'KH02', N'Lê Hoài Mỹ', N'56 Lê Lợi',N'0983245120')
INSERT [dbo].[KhachHang] ([Ma_KhachHang], [Ten_KhachHang], [DiaChi_KhachHang], [SDT_KhachHang]) VALUES (N'KH03', N'Phan Tấn Lợi', N'73 Phạm Ngũ Lão',N'0932452365')
INSERT [dbo].[KhachHang] ([Ma_KhachHang], [Ten_KhachHang], [DiaChi_KhachHang], [SDT_KhachHang]) VALUES (N'KH04', N'Mai Anh Tuấn', N'16 Phan Văn Trị',N'0967145220')
INSERT [dbo].[KhachHang] ([Ma_KhachHang], [Ten_KhachHang], [DiaChi_KhachHang], [SDT_KhachHang]) VALUES (N'KH05', N'Lê Tuấn Vỹ', N'14 Nguyễn Văn Trỗi',N'0357633365')
INSERT [dbo].[KhachHang] ([Ma_KhachHang], [Ten_KhachHang], [DiaChi_KhachHang], [SDT_KhachHang]) VALUES (N'KH06', N'Phạm Thanh Thanh', N'391 Lý Thái Tổ',N'0397824395')
INSERT [dbo].[KhachHang] ([Ma_KhachHang], [Ten_KhachHang], [DiaChi_KhachHang], [SDT_KhachHang]) VALUES (N'KH07', N'Trần Tuấn Tú', N'12 Nguyễn Văn Trỗi',N'0347881425')
INSERT [dbo].[KhachHang] ([Ma_KhachHang], [Ten_KhachHang], [DiaChi_KhachHang], [SDT_KhachHang]) VALUES (N'KH08', N'Lê Thị Thanh Huyền', N'214 Ngyễn Trãi',N'0987942362')


INSERT [dbo].[Xe]([Ma_Xe], [Ten_Xe], [Loai_Xe], [Nam_SX], [So_PK], [So_Khung], [So_Suon],[Mau_Xe],[Gia_Xe]) VALUES (N'XZ01', N'Air Blade', N'Tay Ga', 2016, 150, 182, 6711, N'Đỏ', 54000000)
INSERT [dbo].[Xe]([Ma_Xe], [Ten_Xe], [Loai_Xe], [Nam_SX], [So_PK], [So_Khung], [So_Suon],[Mau_Xe],[Gia_Xe]) VALUES (N'XZ02', N'Dream', N'Xe Số', 2015, 125, 161, 4677, N'Trắng', 34000000)
INSERT [dbo].[Xe]([Ma_Xe], [Ten_Xe], [Loai_Xe], [Nam_SX], [So_PK], [So_Khung], [So_Suon],[Mau_Xe],[Gia_Xe]) VALUES (N'XZ03', N'Air Blade', N'Tay Ga', 2017, 150, 173, 3389, N'Đỏ', 55000000)
INSERT [dbo].[Xe]([Ma_Xe], [Ten_Xe], [Loai_Xe], [Nam_SX], [So_PK], [So_Khung], [So_Suon],[Mau_Xe],[Gia_Xe]) VALUES (N'XZ04', N'HonDa', N'Tay Ga', 2016, 100, 158, 4353, N'Xanh', 50000000)
INSERT [dbo].[Xe]([Ma_Xe], [Ten_Xe], [Loai_Xe], [Nam_SX], [So_PK], [So_Khung], [So_Suon],[Mau_Xe],[Gia_Xe]) VALUES (N'XZ05', N'Winner', N'Tay Côn', 2019, 150, 138, 1232, N'Xanh', 55000000)

INSERT [dbo].[NhanVien] ([Ma_NV], [Ten_NV], [SDT_NV], [DiaChi_NV], [Email_NV]) VALUES (N'NV01', N'Lê Thị Thanh', N'0931231231', N'12 Nguyễn Văn Bảo', N'aa@gmail.com')
INSERT [dbo].[NhanVien] ([Ma_NV], [Ten_NV], [SDT_NV], [DiaChi_NV], [Email_NV]) VALUES (N'NV02', N'Phan Thị Thủy', N'0354043344', N'122 Nguyễn Thiện Thuật', N'ava@gmail.com')
INSERT [dbo].[NhanVien] ([Ma_NV], [Ten_NV], [SDT_NV], [DiaChi_NV], [Email_NV]) VALUES (N'NV03', N'Phạm Ngọc Phương', N'0967128364', N'13 Nguyễn Thế Hiền', N'aaxa@gmail.com')
INSERT [dbo].[NhanVien] ([Ma_NV], [Ten_NV], [SDT_NV], [DiaChi_NV], [Email_NV]) VALUES (N'NV04', N'Trần Tuấn Tú', N'0938475182', N'395 Lý Thái Tổ', N'aaqq@gmail.com')
INSERT [dbo].[NhanVien] ([Ma_NV], [Ten_NV], [SDT_NV], [DiaChi_NV], [Email_NV]) VALUES (N'NV05', N'Dương Thùy Linh', N'0967183412', N'124 Nguyễn Trung Trực', N'anna@gmail.com')
INSERT [dbo].[NhanVien] ([Ma_NV], [Ten_NV], [SDT_NV], [DiaChi_NV], [Email_NV]) VALUES (N'NV06', N'Đào Quỳnh Như', N'0989874651', N'223 Phạm Ngũ Lão', N'amma@gmail.com')
INSERT [dbo].[NhanVien] ([Ma_NV], [Ten_NV], [SDT_NV], [DiaChi_NV], [Email_NV]) VALUES (N'NV07', N'Lê Việt Thủy', N'096962771', N'112 Nguyễn Văn Bảo', N'aeea@gmail.com')

INSERT [dbo].[HoaDon]([Ma_HD], [Ma_KhachHang], [Ma_Xe], [Ma_NV], [NgayLap_HD], [DonGia], [SoLuong],[ThanhTien]) VALUES (N'HD01', N'KH01', N'XZ01',N'NV01', N'02-05-2020', 54000000, 1, 54000000)
INSERT [dbo].[HoaDon]([Ma_HD], [Ma_KhachHang], [Ma_Xe], [Ma_NV], [NgayLap_HD], [DonGia], [SoLuong],[ThanhTien]) VALUES (N'HD02', N'KH02', N'XZ02',N'NV01', N'02-06-2020', 34000000, 1, 34000000)
INSERT [dbo].[HoaDon]([Ma_HD], [Ma_KhachHang], [Ma_Xe], [Ma_NV], [NgayLap_HD], [DonGia], [SoLuong],[ThanhTien]) VALUES (N'HD03', N'KH03', N'XZ01',N'NV01', N'04-06-2020', 55000000, 1, 55000000)
INSERT [dbo].[HoaDon]([Ma_HD], [Ma_KhachHang], [Ma_Xe], [Ma_NV], [NgayLap_HD], [DonGia], [SoLuong],[ThanhTien]) VALUES (N'HD04', N'KH04', N'XZ01',N'NV01', N'07-08-2020', 50000000, 1, 50000000)
INSERT [dbo].[HoaDon]([Ma_HD], [Ma_KhachHang], [Ma_Xe], [Ma_NV], [NgayLap_HD], [DonGia], [SoLuong],[ThanhTien]) VALUES (N'HD05', N'KH05', N'XZ01',N'NV01', N'04-09-2020', 55000000, 1, 55000000)
INSERT [dbo].[HoaDon]([Ma_HD], [Ma_KhachHang], [Ma_Xe], [Ma_NV], [NgayLap_HD], [DonGia], [SoLuong],[ThanhTien]) VALUES (N'HD06', N'KH06', N'XZ01',N'NV01', N'02-10-2020', 54000000, 1, 54000000)


INSERT [dbo].[Account]([Loai_Account], [Username], [Pass]) VALUES (N'Admin',N'admin', N'123')
INSERT [dbo].[Account]([Loai_Account], [Username], [Pass]) VALUES (N'NhanVien',N'nv001', N'123')
INSERT [dbo].[Account]([Loai_Account], [Username], [Pass]) VALUES (N'NhanVien',N'nv002', N'123')
INSERT [dbo].[Account]([Loai_Account], [Username], [Pass]) VALUES (N'NhanVien',N'nv003', N'123')


