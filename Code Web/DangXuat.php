<?php 
include('config.php');
_add_data_nkhd($conn, $data['MaND'],"Đăng xuất", $data['TenDangNhap'] . " Vừa đăng xuất khỏi hệ thống",$time);
session_destroy();
header("Location: index.php");
?>