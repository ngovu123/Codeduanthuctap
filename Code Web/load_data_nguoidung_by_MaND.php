<?php
$title = "Load data người dùng";
include("config.php");
include("check_session.php");

$MaND = addslashes($_GET['MaND']);
if ($MaND == "")
{
	$array_message['code'] = 0;
	$array_message['message'] = "Chưa nhập đầy đủ thông tin !!!";
	echo json_encode($array_message);
	exit;
}
$result = mysqli_query($conn, "	select chucdanh.TenCD, chucdanh.MaCD, nguoidung.MaND , nguoidung.MaDV, nguoidung.MaPQ, nguoidung.TenDangNhap, nguoidung.MatKhau, nguoidung.HoVaTen, nguoidung.Email, nguoidung.SoDienThoai, nguoidung.DiaChi, donvi.TenDV, phanquyen.TenPQ, nguoidung.NgayCapNhat, nguoidung.NgayTao from nguoidung
								INNER JOIN donvi ON nguoidung.MaDV = donvi.MaDV 
								INNER JOIN phanquyen ON nguoidung.MaPQ = phanquyen.MaPQ 
								INNER JOIN chucdanh ON nguoidung.MaCD = chucdanh.MaCD 
								Where nguoidung.MaND = '$MaND'");

if (mysqli_num_rows($result) == 0) 
{
	$array_message['code'] = 0;
	$array_message['data'] = NULL;
}else {
	$array_message['code'] = 1;
	$array_message['data'] = mysqli_fetch_array($result);

}

echo json_encode($array_message);
?>
