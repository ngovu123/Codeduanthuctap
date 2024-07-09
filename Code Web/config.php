<?php
session_start();
date_default_timezone_set('Asia/Ho_Chi_Minh');
require_once 'vendor/autoload.php';

$time = date('Y-m-d H:i:s');

// Database connection details
$host = 'localhost';
$user = 'yq423bysvapx_quanlytaisan';
$pass = 'Vietnamcolen2020@';
$database = 'yq423bysvapx_quanlytaisan';

// Establish a MySQLi connection
$conn = mysqli_connect($host, $user, $pass, $database);

// Check if the connection was successful
if (!$conn) {
    die('Connect DB Failed: ' . mysqli_connect_error());
}

// Set character set to UTF-8
mysqli_set_charset($conn, 'utf8');

if (isset($_SESSION['TenDangNhap'])) {
    $TenDangNhap = $_SESSION['TenDangNhap'];
    
    $query = "SELECT nguoidung.*, donvi.TenDV, phanquyen.TenPQ, chucdanh.TenCD 
              FROM nguoidung
              INNER JOIN donvi ON nguoidung.MaDV = donvi.MaDV 
              INNER JOIN phanquyen ON nguoidung.MaPQ = phanquyen.MaPQ 
              INNER JOIN chucdanh ON nguoidung.MaCD = chucdanh.MaCD 
              WHERE TenDangNhap = '$TenDangNhap'";
              
    $result = mysqli_query($conn, $query);
    $data = mysqli_fetch_array($result);
    
    $query = "SELECT * FROM baoloi WHERE TrangThai = 0";
    $result = mysqli_query($conn, $query);
    $count_baoloi = mysqli_num_rows($result);
} else {
    $google_client = new Google_Client();
    $google_client->setClientId('655932795733-0iudaqm2egn8c8dal1am1uffokk71knr.apps.googleusercontent.com');
    $google_client->setClientSecret('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');
    $google_client->setRedirectUri('http://localhost:8080/NCKH/KKTS/DangNhap_Google_Ajax.php');
    $google_client->addScope('email');
    $google_client->addScope('profile');
}

function _add_data_nkhd($conn, $MaND_add, $HanhDong, $ChiTietHanhDong, $time)
{
    $query = "INSERT INTO nhatkyhoatdong (MaNKHD, MaND, HanhDong, ChiTietHanhDong, Time) 
              VALUES ('', '$MaND_add', '$HanhDong', '$ChiTietHanhDong', '$time')";
    return mysqli_query($conn, $query);
}
