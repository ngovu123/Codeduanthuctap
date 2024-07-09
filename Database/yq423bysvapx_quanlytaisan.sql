-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 09, 2024 at 09:45 PM
-- Server version: 5.7.40-cll-lve
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `yq423bysvapx_quanlytaisan`
--

-- --------------------------------------------------------

--
-- Table structure for table `bankiemke`
--

CREATE TABLE `bankiemke` (
  `Mabkk` int(11) NOT NULL,
  `MaND` int(11) NOT NULL,
  `MaPhieu` int(11) NOT NULL,
  `NgayCapNhat` datetime NOT NULL,
  `NgayTao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `bankiemke`
--

INSERT INTO `bankiemke` (`Mabkk`, `MaND`, `MaPhieu`, `NgayCapNhat`, `NgayTao`) VALUES
(37, 37, 38, '2023-12-25 14:26:37', '2023-12-25 14:26:37'),
(38, 37, 39, '2024-01-30 08:57:17', '2024-01-30 08:57:17');

-- --------------------------------------------------------

--
-- Table structure for table `baoloi`
--

CREATE TABLE `baoloi` (
  `MaBL` int(11) NOT NULL,
  `MaPB` int(11) NOT NULL,
  `MaND` int(11) NOT NULL,
  `TinhTrang` smallint(6) NOT NULL DEFAULT '1',
  `Mota` text COLLATE utf8_vietnamese_ci NOT NULL,
  `HinhAnh` text COLLATE utf8_vietnamese_ci,
  `TrangThai` smallint(6) NOT NULL,
  `NgayCapNhat` datetime NOT NULL,
  `NgayTao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `baoloi`
--

INSERT INTO `baoloi` (`MaBL`, `MaPB`, `MaND`, `TinhTrang`, `Mota`, `HinhAnh`, `TrangThai`, `NgayCapNhat`, `NgayTao`) VALUES
(96, 72, 37, 2, 'sadsad', 'https://firebasestorage.googleapis.com/v0/b/baohongtaisan-2a741.appspot.com/o/BaoHong%2F1709019241724.jpg?alt=media&token=c8765ac2-1ca4-4afb-b7ab-d7f85f638670', 2, '2024-04-15 17:30:24', '2024-02-27 14:34:06'),
(97, 94, 37, 1, 'test', 'https://firebasestorage.googleapis.com/v0/b/baohongtaisan-2a741.appspot.com/o/BaoHong%2F1713177056877.jpg?alt=media&token=533dc6ec-692f-4257-8c96-d344635bce82', 4, '2024-04-17 19:16:10', '2024-04-15 17:30:59'),
(98, 54, 37, 1, 'qweqwe', 'https://firebasestorage.googleapis.com/v0/b/baohongtaisan-2a741.appspot.com/o/BaoHong%2F1713177164382.jpg?alt=media&token=253a0b81-5a47-44e7-b6ab-42e7d2c3a193', 2, '2024-04-17 19:14:11', '2024-04-15 17:32:47'),
(99, 93, 37, 2, 'asdasd', 'https://firebasestorage.googleapis.com/v0/b/baohongtaisan-2a741.appspot.com/o/BaoHong%2F1713268071120.jpg?alt=media&token=5c162dd1-122f-4ae0-96c4-eab426fbb1e1', 2, '2024-04-16 18:48:12', '2024-04-16 18:47:54'),
(100, 96, 37, 2, 'test', 'https://firebasestorage.googleapis.com/v0/b/baohongtaisan-2a741.appspot.com/o/BaoHong%2F1713355938006.jpg?alt=media&token=c7651d11-4e38-43a3-8758-f08389c15502', 1, '2024-04-17 19:12:21', '2024-04-17 19:12:21');

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieukiemke`
--

CREATE TABLE `chitietphieukiemke` (
  `MaCTPKK` int(11) NOT NULL,
  `MaPhieu` int(11) NOT NULL,
  `MaPB` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `SoLuongKiemKe` int(11) NOT NULL,
  `ConTot` int(11) NOT NULL,
  `KemPC` int(11) NOT NULL,
  `MaPC` int(11) NOT NULL,
  `GhiChu` varchar(300) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `NgayCapNhat` datetime NOT NULL,
  `NgayTao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `chitietphieukiemke`
--

INSERT INTO `chitietphieukiemke` (`MaCTPKK`, `MaPhieu`, `MaPB`, `SoLuong`, `SoLuongKiemKe`, `ConTot`, `KemPC`, `MaPC`, `GhiChu`, `NgayCapNhat`, `NgayTao`) VALUES
(154, 38, 52, 9, 9, 9, 0, 0, 'Không có', '2023-12-25 14:25:45', '2023-12-25 14:25:45'),
(155, 38, 53, 3, 3, 3, 0, 0, 'Không có', '2023-12-25 14:25:45', '2023-12-25 14:25:45'),
(156, 38, 54, 120, 120, 120, 0, 0, 'Không có', '2023-12-25 14:25:45', '2023-12-25 14:25:45'),
(157, 38, 55, 3, 3, 3, 0, 0, 'Không có', '2023-12-25 14:25:45', '2023-12-25 14:25:45'),
(158, 38, 56, 2, 2, 2, 0, 0, 'Không có', '2023-12-25 14:25:45', '2023-12-25 14:25:45'),
(159, 38, 57, 120, 120, 120, 0, 0, 'Không có', '2023-12-25 14:25:45', '2023-12-25 14:25:45'),
(160, 38, 89, 1, 1, 1, 0, 0, 'Không có', '2023-12-25 14:25:45', '2023-12-25 14:25:45'),
(161, 38, 90, 2, 2, 2, 0, 0, 'Không có', '2023-12-25 14:25:45', '2023-12-25 14:25:45'),
(162, 39, 77, 1, 0, 0, 0, 0, 'Không có', '2023-12-27 09:55:51', '2023-12-27 09:55:51');

-- --------------------------------------------------------

--
-- Table structure for table `chucdanh`
--

CREATE TABLE `chucdanh` (
  `MaCD` int(11) NOT NULL,
  `TenCD` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `MoTaCD` varchar(300) COLLATE utf8_vietnamese_ci NOT NULL,
  `NgayTao` datetime NOT NULL,
  `NgayCapNhat` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `chucdanh`
--

INSERT INTO `chucdanh` (`MaCD`, `TenCD`, `MoTaCD`, `NgayTao`, `NgayCapNhat`) VALUES
(3, 'Trưởng ban', '', '2023-02-21 08:58:07', '2023-12-22 09:39:38'),
(4, 'Chuyên viên', '', '2023-02-21 08:58:07', '2023-12-22 09:14:53'),
(7, 'Không có chức danh', '', '2023-03-18 22:21:01', '2023-12-22 09:14:52'),
(18, 'gg', 'Không có', '2024-07-09 21:40:36', '2024-07-09 21:40:36');

-- --------------------------------------------------------

--
-- Table structure for table `donvi`
--

CREATE TABLE `donvi` (
  `MaDV` int(11) NOT NULL,
  `TenDV` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `MoTaDV` varchar(300) COLLATE utf8_vietnamese_ci NOT NULL,
  `NgayTao` datetime NOT NULL,
  `NgayCapNhat` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `donvi`
--

INSERT INTO `donvi` (`MaDV`, `TenDV`, `MoTaDV`, `NgayTao`, `NgayCapNhat`) VALUES
(1, 'Phòng cơ sở vật chất', '', '2023-02-02 06:09:19', '2023-12-22 09:34:19'),
(2, 'Phòng thanh tra', 'Không có', '2023-02-02 06:09:19', '2023-10-22 21:29:09'),
(5, 'Trường đại học Thủ Dầu Một', '', '2023-03-18 22:21:22', '2023-12-22 09:39:45');

-- --------------------------------------------------------

--
-- Table structure for table `khuvucphong`
--

CREATE TABLE `khuvucphong` (
  `MaKVP` int(11) NOT NULL,
  `TenKVP` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `NgayCapNhat` datetime NOT NULL,
  `NgayTao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `khuvucphong`
--

INSERT INTO `khuvucphong` (`MaKVP`, `TenKVP`, `NgayCapNhat`, `NgayTao`) VALUES
(1, 'Dãy H', '2023-10-24 21:47:09', '2023-02-05 12:39:04'),
(2, 'Dãy E', '2023-02-05 14:05:28', '2023-02-05 14:05:28'),
(7, 'Dãy I', '2023-10-22 21:06:56', '2023-03-16 12:22:43'),
(8, 'Dãy F', '2023-03-16 12:22:52', '2023-03-16 12:22:52'),
(9, 'Dãy C2', '2023-12-24 07:23:45', '2023-03-16 12:23:34'),
(12, 'Dãy B', '2023-12-24 07:23:46', '2023-10-18 14:45:43');

-- --------------------------------------------------------

--
-- Table structure for table `loaiphong`
--

CREATE TABLE `loaiphong` (
  `MaLP` int(11) NOT NULL,
  `TenLP` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `NgayCapNhat` datetime NOT NULL,
  `NgayTao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `loaiphong`
--

INSERT INTO `loaiphong` (`MaLP`, `TenLP`, `NgayCapNhat`, `NgayTao`) VALUES
(1, 'Lý thuyết', '2023-12-22 21:21:48', '2023-02-05 06:24:16'),
(2, 'Thực hành', '2023-02-05 06:24:16', '2023-02-05 06:24:16');

-- --------------------------------------------------------

--
-- Table structure for table `loaitaisan`
--

CREATE TABLE `loaitaisan` (
  `MaLTS` int(11) NOT NULL,
  `TenLTS` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `NgayCapNhat` datetime NOT NULL,
  `NgayTao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `loaitaisan`
--

INSERT INTO `loaitaisan` (`MaLTS`, `TenLTS`, `NgayCapNhat`, `NgayTao`) VALUES
(1, 'Tài sản cố định', '2023-12-24 09:31:53', '2023-02-07 15:55:37'),
(2, 'Công cụ dụng cụ', '2023-02-07 15:55:57', '2023-02-07 15:55:57');

-- --------------------------------------------------------

--
-- Table structure for table `nguoidung`
--

CREATE TABLE `nguoidung` (
  `MaND` int(11) NOT NULL,
  `MaDV` int(11) NOT NULL,
  `MaCD` int(11) NOT NULL,
  `MaPQ` int(11) NOT NULL,
  `TenDangNhap` varchar(100) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `MatKhau` varchar(30) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `HoVaTen` varchar(50) COLLATE utf8_vietnamese_ci NOT NULL,
  `Email` varchar(50) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `SoDienThoai` varchar(15) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `DiaChi` varchar(300) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `uid` text COLLATE utf8_vietnamese_ci,
  `token` text COLLATE utf8_vietnamese_ci,
  `NgayTao` datetime NOT NULL,
  `NgayCapNhat` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `nguoidung`
--

INSERT INTO `nguoidung` (`MaND`, `MaDV`, `MaCD`, `MaPQ`, `TenDangNhap`, `MatKhau`, `HoVaTen`, `Email`, `SoDienThoai`, `DiaChi`, `uid`, `token`, `NgayTao`, `NgayCapNhat`) VALUES
(1, 1, 7, 1, 'admin', 'admin1234', 'Ngô Trường Vũ', 'admin@gmail.com', '928953139', 'Binh duong', 'uid', 'cX6auvWDRyKHbAd75Lrk6d:APA91bHSBleIFkRHhpAUoLdUEs0Lbo_3arH4LRgn63Gw4Y8E_T2jVyCp8B0LAHJhxRa5vmaKUI0pMa-Y0n850Cyc7pJ67c4g_FI7Oav7Z_0ar_uM_thLA-4URn8hee01tXIJGj1xL8dg', '2023-02-02 06:39:35', '2024-07-09 21:41:03'),
(37, 5, 7, 2, NULL, '1231232321', 'Đức Trần Văn', '2024802010403@student.tdmu.edu.vn', '0123456789', NULL, 'rI6MpwZdDte3Jn0fLtybTE9awKx1', 'cpYcnSasTZ6l5fn6-PVvoc:APA91bFz3zvwBL4wXIqVMNidHoj89lxsN-pdHMMbYtOux8DZo9UghDYh62l8cbSP0IzBWB_ytfvQ6Fslm34rPts5U5E0rKYcK3nDi8lflO42Y_Ok0zK8DNlRXnrmNdOfOcuYOOqJAK68', '2023-12-04 13:15:19', '2024-04-17 19:11:07'),
(48, 5, 7, 1, NULL, '213131232', 'Trần Đức', 'narutokun456789@gmail.com', '0323423540', NULL, '48mWntBM2fZXG3tbWJGnHr9XCoc2', 'd9YFNs3ITEayjD_jT74no7:APA91bHLxPVieJRC2BtIeT57J-9-oZiB_tTdGwyDhIUf_tYG1F4cJ1FTGYMFa3tK0m0drR8z3Kf7BDz4sNlWuEAk6TA_WEqkuzNAuzl-B98cl_VlOOuDejtkbDYkwNxEbs2zhkaRzo8H', '2023-12-05 19:38:31', '2024-01-03 20:36:12'),
(49, 1, 3, 1, 'sdasdweqe@gmail.com', '2133213213', 'duc tran', 'sdasdweqe@gmail.com', '0326393540', 'Khong Co', NULL, NULL, '2023-12-22 10:40:52', '2023-12-22 10:40:52'),
(50, 1, 3, 1, 'vanduc@gmail.com', '123456Duc@@', 'test', 'vanduc@gmail.com', '0326393540', 'Khong Co', NULL, NULL, '2023-12-22 10:43:27', '2023-12-22 10:43:27'),
(51, 1, 3, 1, 'anhtu123@gmail.com', '123123@@', 'anh tu', 'anhtu123@gmail.com', '0423263935', 'Khong Co', 'lvUMAdWyotX0Eil157Q31S3TPJ12', 'f9awQDupShG7_XROXFWtzk:APA91bHbBBUkBMQDmE1GzFpb2qHchvRZIqXY04JX-8ojPt2WAn4B0Yx9ziBF-6YmzTKTF3TmkLw80LksNWg6_2LNSUrKytkDWKLHEW27jFtYI2K4U1jlDaEINLuijt2zMwRgfazVAkgn', '2023-12-22 10:50:14', '2023-12-22 10:53:55'),
(52, 5, 7, 2, 'anhduc123@gmail.com', '123123@@', 'anh duc', 'anhduc123@gmail.com', '033436393', 'Khong Co', 'yKiPaXKGHiWPpD91Nq2RRqDokIU2', 'f9awQDupShG7_XROXFWtzk:APA91bHbBBUkBMQDmE1GzFpb2qHchvRZIqXY04JX-8ojPt2WAn4B0Yx9ziBF-6YmzTKTF3TmkLw80LksNWg6_2LNSUrKytkDWKLHEW27jFtYI2K4U1jlDaEINLuijt2zMwRgfazVAkgn', '2023-12-22 10:54:12', '2023-12-22 10:56:53'),
(53, 2, 7, 1, 'anhduc1234@gmail.com', '123123@@', 'anh duc1', 'anhduc1234@gmail.com', '0326393540', 'Khong Co', 'WdEBQI5TTvTXwjuaiaYdZLX9pka2', 'f9awQDupShG7_XROXFWtzk:APA91bHbBBUkBMQDmE1GzFpb2qHchvRZIqXY04JX-8ojPt2WAn4B0Yx9ziBF-6YmzTKTF3TmkLw80LksNWg6_2LNSUrKytkDWKLHEW27jFtYI2K4U1jlDaEINLuijt2zMwRgfazVAkgn', '2023-12-22 10:57:12', '2023-12-22 10:58:01'),
(54, 1, 3, 1, 'anhduc44@gmail.com', '123123@@', 'anh duc', 'anhduc44@gmail.com', '0156393540', 'Khong Co', 'hCr22yEZHHYShzq3JcbCvnELl8n2', 'f9awQDupShG7_XROXFWtzk:APA91bHbBBUkBMQDmE1GzFpb2qHchvRZIqXY04JX-8ojPt2WAn4B0Yx9ziBF-6YmzTKTF3TmkLw80LksNWg6_2LNSUrKytkDWKLHEW27jFtYI2K4U1jlDaEINLuijt2zMwRgfazVAkgn', '2023-12-22 10:58:12', '2023-12-25 10:45:13'),
(55, 5, 7, 2, 'asdasd@gmail.com', '123123@@', 'test', 'asdasd@gmail.com', '0326935235', 'Khong Co', 'hL6kIbWvpgcjt5hrULS0PNrTbcp1', 'dGxsW833SqW7OLx5PuOvb3:APA91bHMBZo8F0RSkKDR92sqw5pyUeghVblTi2xnX89u9Gysq6vBlhgYdf4-H4U1b5i-yTv_IzpiorjB3qZqt3Tt6sJsrAaOEQ93W8bB1YYcSOUtI6RZFMThu47HCftcCOJxW1-9J0jU', '2023-12-22 20:49:15', '2023-12-22 20:49:29'),
(56, 5, 7, 2, '2024802010403@student.tdmu.edu.vn', '1000002024802010403@student.td', 'Đức Trần Văn', '2024802010403@student.tdmu.edu.vn', '', '', 'rI6MpwZdDte3Jn0fLtybTE9awKx1', 'cpYcnSasTZ6l5fn6-PVvoc:APA91bFz3zvwBL4wXIqVMNidHoj89lxsN-pdHMMbYtOux8DZo9UghDYh62l8cbSP0IzBWB_ytfvQ6Fslm34rPts5U5E0rKYcK3nDi8lflO42Y_Ok0zK8DNlRXnrmNdOfOcuYOOqJAK68', '2024-01-10 08:37:42', '2024-04-17 19:11:07');

-- --------------------------------------------------------

--
-- Table structure for table `nhatkyhoatdong`
--

CREATE TABLE `nhatkyhoatdong` (
  `MaNKHD` int(11) NOT NULL,
  `MaND` int(11) NOT NULL,
  `HanhDong` text COLLATE utf8_vietnamese_ci NOT NULL,
  `ChiTietHanhDong` text COLLATE utf8_vietnamese_ci NOT NULL,
  `Time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `nhatkyhoatdong`
--

INSERT INTO `nhatkyhoatdong` (`MaNKHD`, `MaND`, `HanhDong`, `ChiTietHanhDong`, `Time`) VALUES
(402, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2023-12-05 13:39:06'),
(405, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2023-12-25 14:25:34'),
(406, 1, 'Thêm', 'Thêm mới phiếu kiểm kê có Mã phiếu là: 38', '2023-12-25 14:25:45'),
(407, 1, 'Sửa', 'Hoàn tất kiểm kê với Mã Phiếu là: 38', '2023-12-25 14:26:38'),
(408, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2023-12-26 11:17:23'),
(409, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2023-12-27 09:07:42'),
(410, 1, 'Thêm', 'Thêm mới phiếu kiểm kê có Mã phiếu là: 39', '2023-12-27 09:55:51'),
(411, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-02 09:42:43'),
(412, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-08 21:56:25'),
(413, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-09 09:09:01'),
(414, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-09 10:02:08'),
(415, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-09 10:04:10'),
(416, 1, 'Đăng xuất', 'admin Vừa đăng xuất khỏi hệ thống', '2024-01-09 10:04:17'),
(417, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-09 19:58:47'),
(418, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-14 21:54:30'),
(419, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-15 16:09:27'),
(420, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-16 08:00:27'),
(421, 1, 'Đăng xuất', 'admin Vừa đăng xuất khỏi hệ thống', '2024-01-16 08:02:23'),
(422, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-16 10:41:30'),
(423, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-17 07:54:45'),
(424, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-17 09:28:18'),
(425, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-17 12:12:40'),
(426, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-20 16:07:16'),
(427, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-21 08:05:25'),
(428, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-25 20:57:57'),
(429, 56, 'Đăng xuất', '2024802010403@student.tdmu.edu.vn Vừa đăng xuất khỏi hệ thống', '2024-01-29 23:07:37'),
(430, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-30 08:55:17'),
(431, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-01-30 09:43:38'),
(432, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-02-13 11:01:55'),
(433, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-02-21 22:43:05'),
(434, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-03-17 11:15:30'),
(435, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-03-21 08:12:46'),
(436, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-03-27 09:25:25'),
(437, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-04-15 21:13:34'),
(438, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-04-16 20:26:37'),
(439, 1, 'Đăng nhập', 'admin Vừa đăng nhập vào hệ thống', '2024-07-09 21:40:23'),
(440, 1, 'Thêm', 'Thêm mới chức danh có Tên CD là: gg', '2024-07-09 21:40:36'),
(441, 1, 'Sửa', 'Sửa thông tin người dùng có Mã ND: 1', '2024-07-09 21:41:03'),
(442, 1, 'Đăng xuất', 'admin Vừa đăng xuất khỏi hệ thống', '2024-07-09 21:41:21');

-- --------------------------------------------------------

--
-- Table structure for table `nhomtaisan`
--

CREATE TABLE `nhomtaisan` (
  `MaNTS` int(11) NOT NULL,
  `TenNTS` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `NgayCapNhat` datetime NOT NULL,
  `NgayTao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `nhomtaisan`
--

INSERT INTO `nhomtaisan` (`MaNTS`, `TenNTS`, `NgayCapNhat`, `NgayTao`) VALUES
(1, 'Máy điều hòa', '2023-10-24 21:38:56', '2023-02-07 16:02:14'),
(2, 'Camera', '2023-02-07 16:02:50', '2023-02-07 16:02:50'),
(3, 'Tivi', '2023-02-07 16:02:55', '2023-02-07 16:02:55'),
(4, 'Tủ lạnh', '2023-10-25 15:32:41', '2023-02-07 16:03:00'),
(5, 'Máy in', '2023-02-23 17:51:41', '2023-02-07 16:03:04'),
(6, 'Bộ máy vi tính', '2023-02-07 16:03:11', '2023-02-07 16:03:11'),
(7, 'Kệ', '2023-02-07 16:03:19', '2023-02-07 16:03:19'),
(8, 'Tủ', '2023-02-07 16:03:23', '2023-02-07 16:03:23'),
(9, 'Ghế', '2023-02-07 16:03:27', '2023-02-07 16:03:27'),
(10, 'Bàn', '2023-02-23 17:51:39', '2023-02-07 16:57:08'),
(13, 'Máy chiếu', '2023-03-18 22:32:19', '2023-03-18 22:32:19'),
(14, 'Máy bán hàng', '2023-10-24 11:06:02', '2023-10-24 11:06:02');

-- --------------------------------------------------------

--
-- Table structure for table `phanbo`
--

CREATE TABLE `phanbo` (
  `MaPB` int(11) NOT NULL,
  `MaTS` int(11) NOT NULL,
  `MaND` int(11) NOT NULL,
  `MaP` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `GhiChu` varchar(300) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `NgayCapNhat` datetime NOT NULL,
  `NgayTao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `phanbo`
--

INSERT INTO `phanbo` (`MaPB`, `MaTS`, `MaND`, `MaP`, `SoLuong`, `GhiChu`, `NgayCapNhat`, `NgayTao`) VALUES
(47, 4, 1, 23, 40, 'Không có', '2023-03-18 22:35:16', '2023-03-18 22:35:16'),
(48, 8, 1, 23, 40, 'Không có', '2023-03-18 22:35:27', '2023-03-18 22:35:27'),
(49, 10, 1, 23, 40, 'Không có', '2023-03-18 22:35:35', '2023-03-18 22:35:35'),
(50, 27, 1, 23, 2, 'Không có', '2023-03-18 22:36:26', '2023-03-18 22:36:26'),
(51, 20, 1, 23, 3, 'Không có', '2023-03-18 22:36:36', '2023-03-18 22:36:36'),
(52, 21, 37, 1, 9, 'Không có', '2023-12-06 11:08:29', '2023-03-18 22:40:25'),
(53, 24, 1, 1, 3, 'Không có', '2023-03-18 22:40:33', '2023-03-18 22:40:33'),
(54, 8, 1, 1, 120, 'Không có', '2023-03-18 22:40:41', '2023-03-18 22:40:41'),
(55, 9, 1, 1, 3, 'Không có', '2023-03-18 22:40:45', '2023-03-18 22:40:45'),
(56, 11, 1, 1, 2, 'Không có', '2023-03-18 22:40:50', '2023-03-18 22:40:50'),
(57, 10, 1, 1, 120, 'Không có', '2023-03-18 22:40:55', '2023-03-18 22:40:55'),
(58, 24, 1, 15, 1, 'Không có', '2023-03-18 22:43:22', '2023-03-18 22:43:22'),
(59, 9, 1, 15, 1, 'Không có', '2023-03-18 22:43:31', '2023-03-18 22:43:31'),
(60, 11, 1, 15, 1, 'Không có', '2023-03-18 22:43:35', '2023-03-18 22:43:35'),
(61, 8, 1, 15, 60, 'Không có', '2023-03-18 22:43:50', '2023-03-18 22:43:50'),
(62, 10, 1, 15, 60, 'Không có', '2023-03-18 22:44:06', '2023-03-18 22:44:00'),
(63, 6, 1, 22, 5, 'Không có', '2023-04-15 08:39:30', '2023-04-15 08:39:30'),
(64, 24, 1, 2, 1, 'Không có', '2023-04-19 20:24:17', '2023-04-19 20:24:17'),
(65, 7, 1, 2, 20, 'Không có', '2023-04-19 20:24:24', '2023-04-19 20:24:24'),
(66, 8, 1, 2, 10, 'Không có', '2023-04-19 20:24:30', '2023-04-19 20:24:30'),
(67, 10, 1, 2, 50, 'Không có', '2023-04-19 20:24:37', '2023-04-19 20:24:37'),
(68, 8, 1, 4, 10, 'Không có', '2023-04-19 20:25:14', '2023-04-19 20:25:14'),
(69, 10, 1, 4, 50, 'Không có', '2023-04-19 20:25:19', '2023-04-19 20:25:19'),
(70, 9, 1, 4, 2, 'Không có', '2023-04-19 20:25:28', '2023-04-19 20:25:28'),
(72, 21, 1, 5, 2, 'Không có', '2023-04-19 20:29:15', '2023-04-19 20:29:15'),
(74, 10, 1, 5, 30, 'Không có', '2023-04-19 20:29:25', '2023-04-19 20:29:25'),
(75, 4, 1, 2, 1, 'Không có', '2023-07-26 18:07:33', '2023-07-26 18:07:33'),
(76, 18, 1, 23, 1, 'Không có', '2023-07-26 18:09:45', '2023-07-26 18:09:45'),
(77, 4, 1, 3, 1, 'Không có', '2023-07-26 19:08:14', '2023-07-26 19:08:14'),
(78, 5, 1, 15, 2, 'Không có', '2023-08-26 08:55:15', '2023-08-26 08:55:15'),
(79, 18, 1, 15, 1, 'Không có', '2023-08-26 08:55:25', '2023-08-26 08:55:25'),
(80, 4, 1, 17, 1, 'Không có', '2023-08-26 10:33:20', '2023-08-26 10:33:20'),
(81, 5, 1, 17, 2, 'Không có', '2023-08-26 10:33:30', '2023-08-26 10:33:30'),
(82, 19, 1, 17, 1, 'Không có', '2023-08-26 10:33:37', '2023-08-26 10:33:37'),
(89, 4, 37, 1, 1, 'Không có', '2023-12-06 11:08:24', '2023-12-06 11:08:24'),
(90, 18, 37, 1, 2, 'Không có', '2023-12-06 11:08:27', '2023-12-06 11:08:27'),
(91, 18, 37, 2, 1, 'Không có', '2023-12-06 11:08:32', '2023-12-06 11:08:32'),
(92, 21, 37, 2, 2, 'Không có', '2023-12-06 11:08:34', '2023-12-06 11:08:34'),
(93, 23, 37, 21, 1, 'Không có', '2023-12-06 11:08:41', '2023-12-06 11:08:41'),
(94, 19, 37, 21, 2, 'Không có', '2023-12-06 11:08:42', '2023-12-06 11:08:42'),
(95, 22, 1, 14, 1, 'test', '2023-12-24 19:08:47', '2023-12-24 19:08:47'),
(96, 24, 1, 5, 1, 'asdsd', '2023-12-24 19:10:03', '2023-12-24 19:10:03'),
(97, 31, 1, 12, 3, 'asdsad', '2023-12-24 19:10:24', '2023-12-24 19:10:24'),
(98, 6, 1, 5, 1, '1', '2023-12-24 19:12:36', '2023-12-24 19:12:36'),
(99, 31, 1, 5, 2, 'Không có', '2023-12-24 19:12:55', '2023-12-24 19:12:46'),
(100, 18, 1, 4, 1, 'Không có', '2024-04-17 19:13:38', '2024-04-17 19:13:38');

-- --------------------------------------------------------

--
-- Table structure for table `phanquyen`
--

CREATE TABLE `phanquyen` (
  `MaPQ` int(11) NOT NULL,
  `TenPQ` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `MoTaPQ` varchar(300) COLLATE utf8_vietnamese_ci NOT NULL,
  `NgayTao` datetime NOT NULL,
  `NgayCapNhat` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `phanquyen`
--

INSERT INTO `phanquyen` (`MaPQ`, `TenPQ`, `MoTaPQ`, `NgayTao`, `NgayCapNhat`) VALUES
(1, 'Admin', '', '2023-02-02 06:10:31', '2023-02-02 06:10:31'),
(2, 'User', '', '2023-02-02 06:10:31', '2023-02-02 06:10:31'),
(3, 'KiemKe', '', '2023-04-19 11:39:18', '2023-04-19 11:39:18');

-- --------------------------------------------------------

--
-- Table structure for table `phieukiemke`
--

CREATE TABLE `phieukiemke` (
  `MaPhieu` int(11) NOT NULL,
  `MaP` int(11) NOT NULL,
  `MaND` int(11) NOT NULL,
  `GhiChu` varchar(300) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `TrangThai` smallint(6) NOT NULL,
  `NgayCapNhat` datetime NOT NULL,
  `NgayTao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `phieukiemke`
--

INSERT INTO `phieukiemke` (`MaPhieu`, `MaP`, `MaND`, `GhiChu`, `TrangThai`, `NgayCapNhat`, `NgayTao`) VALUES
(38, 1, 1, 'Không có', 1, '2023-12-25 14:25:45', '2023-12-25 14:25:45'),
(39, 3, 1, 'Không có', 0, '2023-12-27 09:55:51', '2023-12-27 09:55:51');

-- --------------------------------------------------------

--
-- Table structure for table `phong`
--

CREATE TABLE `phong` (
  `MaP` int(11) NOT NULL,
  `MaLP` int(11) NOT NULL,
  `MaKVP` int(11) NOT NULL,
  `TenP` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `NgayCapNhat` datetime NOT NULL,
  `NgayTao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `phong`
--

INSERT INTO `phong` (`MaP`, `MaLP`, `MaKVP`, `TenP`, `NgayCapNhat`, `NgayTao`) VALUES
(1, 1, 2, 'Phòng E3-103', '2023-10-30 12:59:12', '2023-02-05 13:59:35'),
(2, 1, 2, 'Phòng E3-104', '2023-02-05 14:05:41', '2023-02-05 14:05:41'),
(3, 1, 2, 'Phòng E3-105', '2023-02-05 14:05:47', '2023-02-05 14:05:47'),
(4, 1, 2, 'Phòng E3-302', '2023-02-05 14:05:53', '2023-02-05 14:05:53'),
(5, 1, 1, 'Phòng H1-202', '2023-10-25 15:17:21', '2023-02-05 14:06:03'),
(12, 1, 2, 'Phòng E3-301', '2023-03-16 12:24:56', '2023-03-13 15:59:56'),
(13, 1, 2, 'Phòng E3-102', '2023-03-16 12:24:25', '2023-03-16 12:24:25'),
(14, 1, 2, 'Phòng E3-101', '2023-03-16 12:24:34', '2023-03-16 12:24:34'),
(15, 1, 7, 'Phòng I1-101', '2023-03-16 12:24:48', '2023-03-16 12:24:48'),
(16, 1, 7, 'Phòng I1-102', '2023-03-16 12:25:16', '2023-03-16 12:25:16'),
(17, 1, 7, 'Phòng I1-103', '2023-03-16 12:25:26', '2023-03-16 12:25:26'),
(18, 1, 7, 'Phòng I1-104', '2023-03-16 12:25:34', '2023-03-16 12:25:34'),
(19, 1, 7, 'Phòng I1-105', '2023-12-24 07:58:56', '2023-03-16 12:25:42'),
(20, 1, 2, 'Phòng E2-101', '2023-03-16 12:25:50', '2023-03-16 12:25:50'),
(21, 1, 2, 'Phòng E2-102', '2023-03-16 12:26:18', '2023-03-16 12:26:18'),
(22, 1, 8, 'Phòng F2-101', '2023-03-16 12:30:49', '2023-03-16 12:30:49'),
(23, 2, 9, 'Phòng C2.103-P3', '2023-03-18 19:00:07', '2023-03-18 18:59:19'),
(24, 2, 9, 'Phòng C2.201-P5', '2023-10-24 21:38:40', '2023-03-18 18:59:42');

-- --------------------------------------------------------

--
-- Table structure for table `taisan`
--

CREATE TABLE `taisan` (
  `MaTS` int(11) NOT NULL,
  `MaNTS` int(11) NOT NULL,
  `MaLTS` int(11) NOT NULL,
  `TenTS` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `GiaTri` int(11) DEFAULT NULL,
  `SLNhapVao` int(11) NOT NULL,
  `SLHienCon` int(11) NOT NULL,
  `HangSanXuat` varchar(100) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `NuocSanXuat` varchar(50) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `NamSanXuat` year(4) DEFAULT NULL,
  `LinkHinhAnh` text COLLATE utf8_vietnamese_ci,
  `GhiChu` varchar(300) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `NgayCapNhat` datetime NOT NULL,
  `NgayTao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `taisan`
--

INSERT INTO `taisan` (`MaTS`, `MaNTS`, `MaLTS`, `TenTS`, `GiaTri`, `SLNhapVao`, `SLHienCon`, `HangSanXuat`, `NuocSanXuat`, `NamSanXuat`, `LinkHinhAnh`, `GhiChu`, `NgayCapNhat`, `NgayTao`) VALUES
(4, 6, 1, 'Bộ máy vi tính - LG', 0, 50, 3, 'Không có', 'Không có', 2022, NULL, 'Không có', '2023-02-07 16:55:37', '2023-02-07 16:55:37'),
(5, 6, 1, 'Bộ máy vi tính - Samsung', 0, 50, 46, 'Không có', 'Không có', 2022, NULL, 'Không có', '2023-02-07 16:56:00', '2023-02-07 16:56:00'),
(6, 8, 2, 'Tủ sắt', 0, 50, 44, 'Không có', 'Không có', 0000, NULL, 'Không có', '2023-02-07 16:56:09', '2023-02-07 16:56:09'),
(7, 8, 2, 'Tủ gỗ 4 ngăn', 0, 50, 28, 'Không có', 'Không có', 0000, NULL, 'Không có', '2023-02-07 16:56:16', '2023-02-07 16:56:16'),
(8, 9, 2, 'Ghế nhựa', 0, 500, 228, 'Không có', 'Không có', 0000, NULL, 'Không có', '2023-03-23 21:29:59', '2023-02-07 16:56:27'),
(9, 1, 2, 'Ghế gỗ', 0, 50, 44, 'Không có', 'Không có', 2022, NULL, 'Không có', '2023-02-07 16:56:38', '2023-02-07 16:56:38'),
(10, 10, 2, 'Bàn học', 0, 500, 150, 'Không có', 'Không có', 0000, NULL, 'Không có', '2023-02-07 16:57:25', '2023-02-07 16:57:25'),
(11, 10, 2, 'Bàn làm việc', 0, 50, 47, 'Không có', 'Không có', 0000, NULL, 'Không có', '2023-02-07 16:57:32', '2023-02-07 16:57:32'),
(12, 10, 2, 'Bàn uống nước', 0, 50, 50, 'Không có', 'Không có', 0000, NULL, 'Không có', '2023-02-07 16:57:39', '2023-02-07 16:57:39'),
(18, 5, 1, 'Máy In laser Trắng Đen Brother HL L2321D', 0, 50, 42, 'Không có', 'Không có', 2022, NULL, 'Không có', '2023-03-18 22:29:36', '2023-03-18 22:29:36'),
(19, 5, 1, 'Máy in Laser Trắng Đen HP 107w WiFi (4ZB78A)', 0, 50, 40, 'Không có', 'Không có', 2022, NULL, 'Không có', '2023-03-18 22:30:07', '2023-03-18 22:30:07'),
(20, 1, 1, 'Toshiba Inverter 1 HP RAS-H10C4KCVG-V', 0, 30, 27, 'Không có', 'Không có', 2022, NULL, 'Không có', '2023-03-18 22:30:59', '2023-03-18 22:30:59'),
(21, 1, 1, 'Toshiba Inverter 1.5 HP RAS-H13C4KCVG-V', 0, 50, 35, 'Không có', 'Không có', 2022, NULL, 'Không có', '2023-03-18 22:31:10', '2023-03-18 22:31:10'),
(22, 3, 1, 'LG Smart TV 43UQ8000PSC', 0, 10, 9, 'Không có', 'Không có', 0000, NULL, 'Không có', '2023-03-18 22:31:35', '2023-03-18 22:31:35'),
(23, 3, 1, 'LG Smart TV 55UQ8000PSC', 0, 10, 9, 'Không có', 'Không có', 0000, NULL, 'Không có', '2023-03-18 22:31:47', '2023-03-18 22:31:47'),
(24, 13, 1, 'Máy chiếu Led Viewsonic M1 Pro', 0, 20, 11, 'Không có', 'Không có', 0000, NULL, 'Không có', '2023-03-18 22:32:42', '2023-03-18 22:32:42'),
(25, 1, 1, 'Máy chiếu Vimgo P10 Full HD', 0, 30, 30, 'Không có', 'Không có', 0000, NULL, 'Không có', '2023-03-18 22:32:51', '2023-03-18 22:32:51'),
(26, 1, 1, 'Máy chiếu Wanbo T6 Max Full HD', 0, 50, 50, 'Không có', 'Không có', 0000, NULL, 'Không có', '2023-03-18 22:32:58', '2023-03-18 22:32:58'),
(27, 2, 1, 'Camera IP 360 Độ 1536P TP-Link Tapo C210', 0, 30, 28, 'Không có', 'Không có', 0000, NULL, 'Không có', '2023-03-18 22:36:00', '2023-03-18 22:36:00'),
(28, 1, 1, 'Camera IP Ngoài Trời 2MP Imou Bullet 2E-D', 0, 30, 30, 'Không có', 'Không có', 0000, NULL, 'Không có', '2023-03-18 22:36:13', '2023-03-18 22:36:13'),
(31, 1, 1, 'ABC', 0, 25, 20, 'Không có', 'Không có', 2022, NULL, 'Không có', '2023-11-07 12:06:54', '2023-11-07 12:06:54'),
(32, 13, 2, 'duc test', 123, 24, 24, 'duc', 'duc', 2002, NULL, 'testt', '2023-12-24 10:10:29', '2023-12-24 10:10:29');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bankiemke`
--
ALTER TABLE `bankiemke`
  ADD PRIMARY KEY (`Mabkk`) USING BTREE,
  ADD KEY `MaND` (`MaND`),
  ADD KEY `MaPhieu` (`MaPhieu`);

--
-- Indexes for table `baoloi`
--
ALTER TABLE `baoloi`
  ADD PRIMARY KEY (`MaBL`),
  ADD KEY `MaPB` (`MaPB`),
  ADD KEY `MaND` (`MaND`),
  ADD KEY `MaND_2` (`MaND`);

--
-- Indexes for table `chitietphieukiemke`
--
ALTER TABLE `chitietphieukiemke`
  ADD PRIMARY KEY (`MaCTPKK`),
  ADD KEY `MaPhieu` (`MaPhieu`,`MaPB`),
  ADD KEY `MaPB` (`MaPB`);

--
-- Indexes for table `chucdanh`
--
ALTER TABLE `chucdanh`
  ADD PRIMARY KEY (`MaCD`);

--
-- Indexes for table `donvi`
--
ALTER TABLE `donvi`
  ADD PRIMARY KEY (`MaDV`);

--
-- Indexes for table `khuvucphong`
--
ALTER TABLE `khuvucphong`
  ADD PRIMARY KEY (`MaKVP`);

--
-- Indexes for table `loaiphong`
--
ALTER TABLE `loaiphong`
  ADD PRIMARY KEY (`MaLP`);

--
-- Indexes for table `loaitaisan`
--
ALTER TABLE `loaitaisan`
  ADD PRIMARY KEY (`MaLTS`);

--
-- Indexes for table `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`MaND`),
  ADD KEY `MaDV` (`MaDV`,`MaCD`,`MaPQ`),
  ADD KEY `MaCD` (`MaCD`),
  ADD KEY `MaPQ` (`MaPQ`);

--
-- Indexes for table `nhatkyhoatdong`
--
ALTER TABLE `nhatkyhoatdong`
  ADD PRIMARY KEY (`MaNKHD`),
  ADD KEY `MaND` (`MaND`);

--
-- Indexes for table `nhomtaisan`
--
ALTER TABLE `nhomtaisan`
  ADD PRIMARY KEY (`MaNTS`);

--
-- Indexes for table `phanbo`
--
ALTER TABLE `phanbo`
  ADD PRIMARY KEY (`MaPB`),
  ADD KEY `MaTS` (`MaTS`,`MaND`,`MaP`),
  ADD KEY `MaND` (`MaND`),
  ADD KEY `MaP` (`MaP`);

--
-- Indexes for table `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`MaPQ`);

--
-- Indexes for table `phieukiemke`
--
ALTER TABLE `phieukiemke`
  ADD PRIMARY KEY (`MaPhieu`),
  ADD KEY `MaP` (`MaP`,`MaND`),
  ADD KEY `MaND` (`MaND`);

--
-- Indexes for table `phong`
--
ALTER TABLE `phong`
  ADD PRIMARY KEY (`MaP`),
  ADD KEY `MaLP` (`MaLP`,`MaKVP`),
  ADD KEY `MaKVP` (`MaKVP`);

--
-- Indexes for table `taisan`
--
ALTER TABLE `taisan`
  ADD PRIMARY KEY (`MaTS`),
  ADD KEY `MaNTS` (`MaNTS`,`MaLTS`),
  ADD KEY `MaLTS` (`MaLTS`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bankiemke`
--
ALTER TABLE `bankiemke`
  MODIFY `Mabkk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `baoloi`
--
ALTER TABLE `baoloi`
  MODIFY `MaBL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `chitietphieukiemke`
--
ALTER TABLE `chitietphieukiemke`
  MODIFY `MaCTPKK` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=163;

--
-- AUTO_INCREMENT for table `chucdanh`
--
ALTER TABLE `chucdanh`
  MODIFY `MaCD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `donvi`
--
ALTER TABLE `donvi`
  MODIFY `MaDV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `khuvucphong`
--
ALTER TABLE `khuvucphong`
  MODIFY `MaKVP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `loaiphong`
--
ALTER TABLE `loaiphong`
  MODIFY `MaLP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `loaitaisan`
--
ALTER TABLE `loaitaisan`
  MODIFY `MaLTS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `nguoidung`
--
ALTER TABLE `nguoidung`
  MODIFY `MaND` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `nhatkyhoatdong`
--
ALTER TABLE `nhatkyhoatdong`
  MODIFY `MaNKHD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=443;

--
-- AUTO_INCREMENT for table `nhomtaisan`
--
ALTER TABLE `nhomtaisan`
  MODIFY `MaNTS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `phanbo`
--
ALTER TABLE `phanbo`
  MODIFY `MaPB` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `phanquyen`
--
ALTER TABLE `phanquyen`
  MODIFY `MaPQ` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `phieukiemke`
--
ALTER TABLE `phieukiemke`
  MODIFY `MaPhieu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `phong`
--
ALTER TABLE `phong`
  MODIFY `MaP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;

--
-- AUTO_INCREMENT for table `taisan`
--
ALTER TABLE `taisan`
  MODIFY `MaTS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bankiemke`
--
ALTER TABLE `bankiemke`
  ADD CONSTRAINT `bankiemke_ibfk_1` FOREIGN KEY (`MaND`) REFERENCES `nguoidung` (`MaND`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `bankiemke_ibfk_2` FOREIGN KEY (`MaPhieu`) REFERENCES `phieukiemke` (`MaPhieu`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `baoloi`
--
ALTER TABLE `baoloi`
  ADD CONSTRAINT `baoloi_ibfk_1` FOREIGN KEY (`MaPB`) REFERENCES `phanbo` (`MaPB`),
  ADD CONSTRAINT `baoloi_ibfk_2` FOREIGN KEY (`MaND`) REFERENCES `nguoidung` (`MaND`),
  ADD CONSTRAINT `baoloi_ibfk_3` FOREIGN KEY (`MaPB`) REFERENCES `phanbo` (`MaPB`);

--
-- Constraints for table `chitietphieukiemke`
--
ALTER TABLE `chitietphieukiemke`
  ADD CONSTRAINT `chitietphieukiemke_ibfk_1` FOREIGN KEY (`MaPhieu`) REFERENCES `phieukiemke` (`MaPhieu`),
  ADD CONSTRAINT `chitietphieukiemke_ibfk_2` FOREIGN KEY (`MaPB`) REFERENCES `phanbo` (`MaPB`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD CONSTRAINT `nguoidung_ibfk_1` FOREIGN KEY (`MaCD`) REFERENCES `chucdanh` (`MaCD`),
  ADD CONSTRAINT `nguoidung_ibfk_2` FOREIGN KEY (`MaDV`) REFERENCES `donvi` (`MaDV`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `nguoidung_ibfk_3` FOREIGN KEY (`MaPQ`) REFERENCES `phanquyen` (`MaPQ`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `nhatkyhoatdong`
--
ALTER TABLE `nhatkyhoatdong`
  ADD CONSTRAINT `nhatkyhoatdong_ibfk_1` FOREIGN KEY (`MaND`) REFERENCES `nguoidung` (`MaND`);

--
-- Constraints for table `phanbo`
--
ALTER TABLE `phanbo`
  ADD CONSTRAINT `phanbo_ibfk_1` FOREIGN KEY (`MaTS`) REFERENCES `taisan` (`MaTS`),
  ADD CONSTRAINT `phanbo_ibfk_2` FOREIGN KEY (`MaND`) REFERENCES `nguoidung` (`MaND`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `phanbo_ibfk_3` FOREIGN KEY (`MaP`) REFERENCES `phong` (`MaP`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieukiemke`
--
ALTER TABLE `phieukiemke`
  ADD CONSTRAINT `phieukiemke_ibfk_1` FOREIGN KEY (`MaP`) REFERENCES `phong` (`MaP`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `phieukiemke_ibfk_2` FOREIGN KEY (`MaND`) REFERENCES `nguoidung` (`MaND`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phong`
--
ALTER TABLE `phong`
  ADD CONSTRAINT `phong_ibfk_1` FOREIGN KEY (`MaKVP`) REFERENCES `khuvucphong` (`MaKVP`),
  ADD CONSTRAINT `phong_ibfk_2` FOREIGN KEY (`MaLP`) REFERENCES `loaiphong` (`MaLP`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `taisan`
--
ALTER TABLE `taisan`
  ADD CONSTRAINT `taisan_ibfk_1` FOREIGN KEY (`MaLTS`) REFERENCES `loaitaisan` (`MaLTS`),
  ADD CONSTRAINT `taisan_ibfk_2` FOREIGN KEY (`MaNTS`) REFERENCES `nhomtaisan` (`MaNTS`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
