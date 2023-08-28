-- Chọn kênh mặc định
use master
GO

IF EXISTS (SELECT * FROM sys.databases
	WHERE name = 'ASM_JAVA6')
	BEGIN
		-- Xóa db nếu đang có
		DROP DATABASE ASM_JAVA6
		END
	-- Sau đó tạo 1 db mới
	CREATE DATABASE ASM_JAVA6
GO
-- Chọn dùng db
USE ASM_JAVA6
GO 

-- tạo bảng phân loại
create table categories
(
	id varchar(10) primary key,
	name nvarchar(50) NOT NULL
)
GO

-- tạo bảng tài khoản
create table accounts
(
	username varchar(50) primary key,
	password varchar(50) NOT NULL,
	fullname nvarchar(50),
	email varchar(100),
	photo varchar(50),
	activated bit,
)
GO

create table roles
(
	id varchar(10) primary key,
	name nvarchar(50)
)
go

create table authorities
(
	id int primary key identity,
	username varchar(50) references accounts(username),
	roleid varchar(10) references roles(id)
)

go 
-- tạo bảng sản phẩm
create table products
(
	id int identity(1,1) primary key,
	name nvarchar(50),
	image varchar(50),
	price float,
	createDate date,
	available bit,
	outstanding bit,
	categoryid varchar(10) references categories(id)
)
GO

-- tạo bảng giỏ hàng
create table shoppingCarts
(
id int identity(1,1) primary key,
username varchar(50),
productid int references products(id),
quantity int
)
go

-- tạo bảng đặt hàng
create table orders
(
	id int identity(1,1) primary key,
	address nvarchar(100) NOT NULL,
	createDate date,
	status nvarchar(50),
	username varchar(50) references accounts(username)
)
GO

-- tạo bảng chi tiết đơn hàng
create table orderDetails
(
	id int identity(1,1) primary key,
	price float,
	quantity int,
	orderid int references orders(id),
	productid int references products(id)
)
GO

-- tạo bảng yêu thích
create table favorites(
	id int identity(1,1) primary key,
	userid varchar(50) references accounts(username),
	productid int references products(id),
	likedate date
)
GO

-- thêm dữ liệu vào các bảng
insert into categories values
('NO', N'Nón bảo hiểm'),
('QA', N'Quần áo'),
('GA', N'Găng tay'),
('GI', N'Giày'),
('BA', N'Balo'),
('KH', N'Phụ kiện khác')
GO

insert into accounts values
('tv02', 'ps20385', N'Đồng Võ Nghiệp', 'nghiepdvps20385@fpt.edu.vn', 'nd.jpg', 1),
('tv01', 'ps20362', N'Nguyễn Sĩ Trọng Phúc', 'phucnstps20362@fpt.edu.vn', 'pt.jpg', 1),
('tv03', 'ps20328', N'Đặng Quang Huy', 'huydqps20328@fpt.edu.vn', 'hq.jpg', 0),
('tv04', 'ps20346', N'Phạm Ngọc Sang', 'sangntps20346@fpt.edu.vn', 'st.jpg', 1),
('tv05', 'ps20954', N'Trần Văn Hiệp', 'hiepvcps20954@fpt.edu.vn', 'hc.jpg', 0)
GO

insert into roles values
('dire', 'director'),
('staf', 'staff'),
('cust', 'customer')
go

insert into authorities values
('tv01', 'dire'),
('tv02', 'dire'),
('tv03', 'staf'),
('tv04', 'cust'),
('tv05', 'cust')
go

insert into products values
(N'Giày bảo hộ 1', 'giay1.png', 550000, '5/23/2023', 1, 0, 'GI'),
(N'Giày bảo hộ 2', 'giay2.png', 450000, '5/23/2023', 1, 0, 'GI'),
(N'Giày bảo hộ 3', 'giay3.png', 850000, '5/23/2023', 1, 0, 'GI'),
(N'Giày bảo hộ 4', 'giay4.png', 950000, '5/23/2023', 1, 1, 'GI'),
(N'Giày bảo hộ 5', 'giay5.png', 1550000, '5/23/2023', 1, 0, 'GI'),
(N'Giày bảo hộ 6', 'giay6.png', 1200000, '5/23/2023', 1, 0, 'GI'),
(N'Lều cắm trại 1', 'leutrai1.png', 850000, '5/23/2023', 1, 0, 'KH'),
(N'Lều cắm trại 2', 'leutrai2.png', 850000, '5/23/2023', 1, 1, 'KH'),
(N'Lều cắm trại 3', 'leutrai3.png', 950000, '5/23/2023', 1, 0, 'KH'),
(N'Lều cắm trại 4', 'leutrai4.png', 750000, '5/23/2023', 1, 0, 'KH'),
(N'Lều cắm trại 5', 'leutrai5.png', 650000, '5/23/2023', 1, 1, 'KH'),
(N'Nón bảo hiểm Royal Helmet', 'mu3.png', 450000, '5/23/2023', 1, 1, 'NO'),
(N'Balo du lịch cao cấp', 'balo1.png', 450000, '5/23/2023', 1, 1, 'BA'),
(N'Quần giáp Riding Tribe JK37B', 'quan1.png', 900000, '5/24/2023', 1, 0, 'QA'),
(N'Nón fullface ROYAL M136 V2 ĐỎ', 'mu4.png', 450000, '5/24/2023', 1, 0, 'NO'),
(N'Nón bảo hiểm 1/2 + kính phi công', 'mu5.png', 450000, '5/24/2023', 1, 1, 'NO'),
(N'Nón bảo hiểm 3/4 kính âm màu vàng ROYAL M139', 'mu.png', 450000, '5/23/2023', 1, 1, 'NO'),
(N'Balo phượt du lịch thời trang cao cấp', 'balo.png', 500000, '5/23/2023', 1, 1, 'BA'),
(N'Balo du lịch 2', 'balo2.png', 500000, '5/23/2023', 1, 0, 'BA'),
(N'Balo du lịch 3', 'balo3.png', 600000, '5/23/2023', 1, 0, 'BA'),
(N'Balo du lịch 4', 'balo4.png', 700000, '5/23/2023', 1, 0, 'BA'),
(N'Balo du lịch 5', 'balo5.png', 800000, '5/23/2023', 1, 1, 'BA'),
(N'Găng tay xe máy Fox dài ngón cam', 'gangtay.png', 800000, '5/23/2023', 1, 1, 'GA'),
(N'Găng tay 1', 'gangtay1.png', 800000, '5/23/2023', 1, 0, 'GA'),
(N'Găng tay 2', 'gangtay2.png', 400000, '5/23/2023', 1, 0, 'GA'),
(N'Găng tay 3', 'gangtay3.png', 300000, '5/23/2023', 1, 0, 'GA'),
(N'Găng tay 4', 'gangtay4.png', 200000, '5/23/2023', 1, 0, 'GA'),
(N'Áo motor Komine jk63 gù Titanium', 'ao1.png', 1500000, '5/23/2023', 1, 0, 'QA')
GO

insert into orders values
(N'Quận 4, TP HCM', '5/25/2023',N'Đang xử lý', 'tv01'),
(N'Quận 10, TP HCM', '5/25/2023','', 'tv02'),
(N'Quận 2, TP HCM', '5/25/2023',N'Đang xử lý', 'tv03'),
(N'Quận 4, TP HCM', '5/25/2023','', 'tv01'),
(N'Phường Tân Chánh Hiệp, Quận 12, TP HCM', '5/25/2023','', 'tv05'),
(N'Phường Tân Chánh Hiệp, Quận 12, TP HCM','5/30/2023',N'Đang xử lý', 'tv04'),
(N'Phường Tân Chánh Hiệp, Quận 12, TP HCM', '6/2/2023','', 'tv04')
GO

insert into orderDetails values
(450000, 1, 1, 2),
(500000, 1, 2, 3),
(800000, 1, 3, 5),
(450000, 1, 4, 2),
(500000, 1, 5, 3),
(800000, 1, 6, 5),
(800000, 1, 7, 5)
GO

insert into favorites values
('tv02',1,GETDATE()),
('tv01',2,'7/18/2023'),
('tv01',3,'7/18/2023'),
('tv02',4,'6/11/2023'),
('tv04',5,'6/11/2023')
GO

insert into shoppingCarts values
('tv02', 5, 1),
('tv02', 6, 1)
go

/* Gọi những lệnh cần thiết */
use ASM_JAVA6
go
select * from products
select * from orders
select * from orderDetails
select * from favorites
select * from accounts
select * from shoppingCarts
drop table orders
drop table orderDetails